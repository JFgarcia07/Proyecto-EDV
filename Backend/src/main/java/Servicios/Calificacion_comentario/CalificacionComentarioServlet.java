/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servicios.Calificacion_comentario;

import Controlador.Calificacion_Comentario.CalificacionComentario;
import Modelo.Comentario;
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
@WebServlet(name = "CalificacionComentarioServlet", urlPatterns = {"/CalificacionComentarioServlet"})
public class CalificacionComentarioServlet extends HttpServlet {


    private CalificacionComentario calificacionComentario = new CalificacionComentario();
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
        
        String idJuego = request.getParameter("id");

        List<Comentario> listaComentarios = calificacionComentario.listaComentarios(idJuego);
        
        response.setContentType("application/json"); 
        response.setCharacterEncoding("UTF-8");
        
        String json = new Gson().toJson(listaComentarios);
        
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

        JSONObject resp = new JSONObject();

        String body = request.getReader().lines().reduce("", (acc, line) -> acc + line);
        JSONObject json = new JSONObject(body);

        if (!json.has("comentarioPadre") || json.isNull("comentarioPadre")) {
            json.put("comentarioPadre", 0);
        }

        calificacionComentario.comentarJuego(json);

        response.setStatus(HttpServletResponse.SC_OK);
        resp.put("exito", true);
        resp.put("mensaje", "Guardado correctamente.");

        response.getWriter().write(resp.toString());
    }

}
