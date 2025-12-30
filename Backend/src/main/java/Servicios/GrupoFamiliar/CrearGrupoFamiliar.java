/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servicios.GrupoFamiliar;

import Controlador.GrupoFamiliar.GrupFamiliar;
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
@WebServlet(name = "CrearGrupoFamiliar", urlPatterns = {"/CrearGrupoFamiliar"})
public class CrearGrupoFamiliar extends HttpServlet {

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
        GrupFamiliar grupo = new GrupFamiliar();
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String body = request.getReader().lines().reduce("", (acc, line) -> acc + line);

        JSONObject json = new JSONObject(body);
        
        int codigoRespuesta = grupo.crearGrupo(json);
        
       JSONObject respuesta = new JSONObject();
       
        switch (codigoRespuesta) {
            case 200:
                response.setStatus(HttpServletResponse.SC_OK);
                respuesta.put("mensaje", "Grupo creado con éxito");
                respuesta.put("exito", true);
                break;
            case 400:
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                respuesta.put("mensaje", "No se ha podido crear el grupo");
                respuesta.put("exito", false);
                break;
            default:
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                respuesta.put("mensaje", "Error interno en la base de datos");
                respuesta.put("exito", false);
                break;
        }
        response.getWriter().write(respuesta.toString());
    }
    
}
