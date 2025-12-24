/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servicios.Categoria;

import Controlador.Categoria.CRUDCategoria;
import Modelo.Categoria;
import Modelo.Juego;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "EditarCategoria", urlPatterns = {"/EditarCategoria"})
public class EditarCategoria extends HttpServlet {

    private CRUDCategoria crud = new CRUDCategoria();

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
        String idCategoria = request.getParameter("id");

        JSONObject json = new JSONObject();

        try {
            Categoria categoria = crud.obtenerCategoriaPorId(idCategoria);

            JSONObject data = new JSONObject();
            data.put("nombreCategoria", categoria.getNombreCategoria());

            json.put("exito", true);
            json.put("juego", data);

        } catch (Exception e) {
            json.put("exito", false);
            json.put("mensaje", "Error servidor: " + e.getMessage());
            e.printStackTrace();
        }

        out.print(json.toString());
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

        int codigoRespuesta = crud.editarCategoria(json);

        JSONObject respuesta = new JSONObject();

        switch (codigoRespuesta) {
            case 200:
                respuesta.put("mensaje", "Categoria actualizada correctamente");
                break;
            default:
                respuesta.put("mensaje", "Error desconocido");
                break;
        }

        response.getWriter().write(respuesta.toString());
    }

}
