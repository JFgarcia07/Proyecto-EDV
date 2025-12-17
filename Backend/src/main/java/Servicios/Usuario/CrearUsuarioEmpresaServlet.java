/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servicios.Usuario;

import Controlador.Usuario.CrearUsuarioEmpresa;
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
@WebServlet(name = "CrearUsuarioEmpresaServlet", urlPatterns = {"/CrearUsuarioEmpresaServlet"})
public class CrearUsuarioEmpresaServlet extends HttpServlet {

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
        CrearUsuarioEmpresa empresaUser = new CrearUsuarioEmpresa();
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
     
       
        String body = request.getReader().lines().reduce("", (acc, line) -> acc + line);
        
        JSONObject json = new JSONObject(body);
        
        String idEmpresa = json.optString("idEmpresa", "");
        
        int codigoRespuesta = empresaUser.crearUsuarioEmpresa(json, idEmpresa);
        
        JSONObject respuesta = new JSONObject();
        
        if(codigoRespuesta == 200){
            response.setStatus(HttpServletResponse.SC_OK);
            json.put("mensaje", "Usuario creado con exito");
        } else if (codigoRespuesta == 400) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            json.put("mensaje", "Error: El id de usuario ya existe");
        } else if (codigoRespuesta == 401) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            json.put("mensaje", "Error: El usuario ya ha sido agregado a esta empresa");
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            json.put("mensaje", "Error en la base de datos");
        }
        
        response.getWriter().write(respuesta.toString());
    }

}
