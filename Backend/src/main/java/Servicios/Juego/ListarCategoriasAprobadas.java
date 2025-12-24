/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servicios.Juego;

import Controlador.Juego.JuegoCategorias;
import Modelo.CategoriasAprobadas;
import com.google.gson.Gson;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author jfgarcianata
 */
@WebServlet(name = "CategoriasAprobadas", urlPatterns = {"/CategoriasAprobadas"})
public class ListarCategoriasAprobadas extends HttpServlet {

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
        
        JuegoCategorias juegoCategoria = new JuegoCategorias();
        
        String idJuego = request.getParameter("id");
        
        List<CategoriasAprobadas> listCategoriasAprobadas = juegoCategoria.catoriasAprobadas(idJuego);
        
        String json = new Gson().toJson(listCategoriasAprobadas);
        
        response.getWriter().write(json);
    }

}
