/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servicios.Juego;

import Controlador.Juego.CRUDJuego;
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
@WebServlet(name = "DesactivarComentariosJuego", urlPatterns = {"/DesactivarComentariosJuego"})
public class DesactivarComentariosJuego extends HttpServlet {


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

        CRUDJuego crud = new CRUDJuego();

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String idJuego = request.getParameter("id");

        int codigoRespuesta = crud.desactivarComentarioJuego(idJuego);

        JSONObject respuestaJson = new JSONObject();

        switch (codigoRespuesta) {
            case 200:
                response.setStatus(HttpServletResponse.SC_OK);
                respuestaJson.put("mensaje", "Los comentarios han sido desactivados con éxito");
                respuestaJson.put("exito", true);
                break;
            default:
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                respuestaJson.put("mensaje", "Error interno en la base de datos");
                respuestaJson.put("exito", false);
                break;
        }
        
        response.getWriter().write(respuestaJson.toString());

    }

}
