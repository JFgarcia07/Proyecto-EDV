/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servicios.Juego;

import Controlador.Juego.ListarJuegosFiltros;
import Modelo.Juego;
import com.google.gson.Gson;
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
@WebServlet(name = "BuscarJuegoPorTitulo", urlPatterns = {"/BuscarJuegoPorTitulo"})
public class BuscarJuegoPorTitulo extends HttpServlet {

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
        
        ListarJuegosFiltros juegos = new ListarJuegosFiltros();
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String entrada = request.getParameter("entrada");
        JSONObject resp = new JSONObject();

        if (entrada == null || entrada.trim().isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.put("exito", false);
            resp.put("mensaje", "Debe enviar un valor para buscar.");
            response.getWriter().write(resp.toString());
            return;
        }

        Juego juego = juegos.buscarJuego(entrada);

        if (juego != null) {
            response.setStatus(HttpServletResponse.SC_OK);
            resp.put("exito", true);
            resp.put("juego", new JSONObject(new Gson().toJson(juego)));
        } else {
            response.setStatus(HttpServletResponse.SC_OK);
            resp.put("exito", false);
            resp.put("mensaje", "No se encontró el juego.");
        }

        response.getWriter().write(resp.toString());
    }

}
