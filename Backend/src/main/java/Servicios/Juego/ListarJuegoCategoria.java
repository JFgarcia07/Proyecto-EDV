/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servicios.Juego;

import Controlador.Juego.ListarJuegosFiltros;
import Modelo.Juego;
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
@WebServlet(name = "ListarJuegoCategoria", urlPatterns = {"/ListarJuegoCategoria"})
public class ListarJuegoCategoria extends HttpServlet {

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
        ListarJuegosFiltros juego = new ListarJuegosFiltros();
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String idCategoria = request.getParameter("id");

        List<Juego> juegos = juego.juegoPorCategoria(idCategoria);
        String json = new Gson().toJson(juegos);

        response.getWriter().write(json);
    }
}
