/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servicios.Biblioteca;

import Controlador.Biblioteca.Biblioteca;
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
@WebServlet(name = "InstalarJuegoBiblioteca", urlPatterns = {"/InstalarJuegoBiblioteca"})
public class InstalarJuegoBiblioteca extends HttpServlet {



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
        Biblioteca biblio = new Biblioteca();
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        String idJuego = request.getParameter("id");
        
        biblio.instalarJuego(idJuego);
        
        JSONObject respuestaJson = new JSONObject();
        
        respuestaJson.put("mensaje", "Juego instalado con éxito");
        respuestaJson.put("exito", true);
        
        response.getWriter().write(respuestaJson.toString());
    }




}
