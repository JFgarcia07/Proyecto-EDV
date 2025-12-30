/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servicios.GrupoFamiliar;

import Controlador.GrupoFamiliar.GrupFamiliar;
import Modelo.MiembroGrupo;
import com.google.gson.Gson;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import org.json.JSONObject;

/**
 *
 * @author jfgarcianata
 */
@WebServlet(name = "AgregarUsuarioGrupo", urlPatterns = {"/AgregarUsuarioGrupo"})
public class AgregarUsuarioGrupo extends HttpServlet {

    private GrupFamiliar grupo = new GrupFamiliar();

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
        
        String idGrupo = request.getParameter("id");
        
        List<MiembroGrupo> listaMiembroGrupo = grupo.listaMiembrosGrupo(idGrupo);
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        String json = new Gson().toJson(listaMiembroGrupo);
        
        response.getWriter().write(json);
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
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String body = request.getReader().lines().reduce("", (acc, line) -> acc + line);

        JSONObject json = new JSONObject(body);

        int codigoRespuesta = grupo.agregarMiembroGrupo(json);

        JSONObject respuesta = new JSONObject();

        switch (codigoRespuesta) {
            case 200:
                response.setStatus(HttpServletResponse.SC_OK);
                respuesta.put("mensaje", "El usuario ha sido agregado al grupo con éxito");
                respuesta.put("exito", true);
                break;
            case 400:
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                respuesta.put("mensaje", "No se ha podido agregar el usuario al grupo");
                respuesta.put("exito", false);
                break;
            case 401:
                response.setStatus(HttpServletResponse.SC_CONFLICT);
                respuesta.put("mensaje", "Este usuario ya esta agregado al grupo");
                respuesta.put("exito", false);
                break;
            case 402:
                response.setStatus(HttpServletResponse.SC_CONFLICT);
                respuesta.put("mensaje", "Este usuario no existe en el registro");
                respuesta.put("exito", false);
                break;
            case 403:
                response.setStatus(HttpServletResponse.SC_CONFLICT);
                respuesta.put("mensaje", "Ya no hay cupo en el grupo");
                respuesta.put("exito", false);
                break;
            case 405:
                response.setStatus(HttpServletResponse.SC_CONFLICT);
                respuesta.put("mensaje", "Tu ya eres parte del grupo, no te puedes agregar");
                respuesta.put("exito", false);
                break;
            default:
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                respuesta.put("mensaje", "Error interno en la baase de datos");
                respuesta.put("exito", false);
                break;
        }
        response.getWriter().write(respuesta.toString());
    }
}
