/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servicios.Usuario;

import DB.SesionGlobal;
import DB.Usuario.UsuarioDB;
import Modelo.Usuario;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Optional;
import org.json.JSONObject;

/**
 *
 * @author jfgarcianata
 */
@WebServlet(name = "PerfilSevlet", urlPatterns = {"/PerfilSevlet"})
public class PerfilSevlet extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        JSONObject json = new JSONObject();

        try {
            UsuarioDB userDB = new UsuarioDB();
            String idUsuario = request.getParameter("id");

            if (idUsuario != null && !idUsuario.isEmpty() && !"undefined".equals(idUsuario)) {
                Optional<Usuario> perfil = userDB.obtenerUsuarioPorId(idUsuario);
               
                
                if (perfil.isPresent()) {
                    json.put("idUsuario", perfil.get().getIdUsuario());
                    json.put("nombre", perfil.get().getNombreUsuario());
                    json.put("email", perfil.get().getEmail());
                    json.put("fecha", perfil.get().getFechaNacimiento());
                    json.put("pais", perfil.get().getPais());
                    json.put("telefono", perfil.get().getTelefono());
                    json.put("billetera", perfil.get().getSaldo());
                    json.put("rol", perfil.get().getNombreRol());
                    json.put("found", true);
                } else {
                    json.put("found", false);
                    json.put("message", "Usuario no encontrado");
                }
            } else {
                json.put("found", false);
                json.put("message", "ID invalido");
            }

        } catch (Exception e) {
            e.printStackTrace();
            json.put("found", false);
        }

        out.print(json.toString());
        out.flush();
        out.close();
    }


}
