/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servicios.Usuario;

import Controlador.Usuario.CrearUsuario;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONObject;

/**
 *
 * @author jfgarcianata
 */
@WebServlet(name = "RegistrarUsuarioServlet", urlPatterns = {"/RegistrarUsuarioServlet"})
public class RegistrarUsuarioServlet extends HttpServlet {


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
        
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CrearUsuario crearUsuario = new CrearUsuario();
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        String body = request.getReader().lines().reduce("", (acc, line) -> acc + line);
        
        JSONObject json = new JSONObject(body);
        
        int codigoRespuesta = crearUsuario.crearUsuario(json);
        System.out.println(codigoRespuesta);
        
        JSONObject respuesta = new JSONObject();
       
        if (codigoRespuesta == 200) {
            response.setStatus(HttpServletResponse.SC_OK);
            json.put("mensaje", "¡Bienvenido! Usuario creado con exito.");
        } else if (codigoRespuesta == 400) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            json.put("mensaje", "Error: El id de usuerio ya existe.");
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            json.put("mensaje", "Error en la base de datos.");
        }
        
        response.getWriter().write(respuesta.toString());
    }

}
