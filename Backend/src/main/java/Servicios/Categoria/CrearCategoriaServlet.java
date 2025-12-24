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
@WebServlet(name = "CrearCategoriaServlet", urlPatterns = {"/CrearCategoriaServlet"})
public class CrearCategoriaServlet extends HttpServlet {


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
        CRUDCategoria crud = new CRUDCategoria();
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String body = request.getReader().lines().reduce("", (acc, line) -> acc + line);

        JSONObject json = new JSONObject(body);

        int codigoRespuesta = crud.crearCategoria(json);

        JSONObject respuesta = new JSONObject();

        switch (codigoRespuesta) {
            case 200:
                respuesta.put("mensaje", "Categoria creada correctamente");
                break;
            case 400:
                respuesta.put("mensaje", "Error: El ID ya existe");
                break;
            default:
                respuesta.put("mensaje", "Error desconocido");
                break;
        }

        response.getWriter().write(respuesta.toString());
    }

}
