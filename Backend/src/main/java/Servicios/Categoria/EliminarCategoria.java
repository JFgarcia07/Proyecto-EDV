/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servicios.Categoria;

import Controlador.Categoria.CRUDCategoria;
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
@WebServlet(name = "EliminarCategoria", urlPatterns = {"/EliminarCategoria"})
public class EliminarCategoria extends HttpServlet {


    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        CRUDCategoria crud = new CRUDCategoria();
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String idCategoria = request.getParameter("id");
        
        crud.eliminarCategoria(idCategoria);
        
        JSONObject respuestaJson = new JSONObject();

        respuestaJson.put("mensaje", "Categoria eliminada con éxito");
        respuestaJson.put("exito", true);
        
        response.getWriter().write(respuestaJson.toString());
        
    }


}
