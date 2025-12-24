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
import java.io.BufferedReader;
import org.json.JSONObject;

/**
 *
 * @author jfgarcianata
 */
@WebServlet(name = "CrearJuegoServlet", urlPatterns = {"/CrearJuegoServlet"})
public class CrearJuegoServlet extends HttpServlet {


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
     
       
        StringBuilder sb = new StringBuilder();
        String line;
        try (BufferedReader reader = request.getReader()) {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        }
        String body = sb.toString();

        JSONObject respuestaJson = new JSONObject();

        try {
            JSONObject json = new JSONObject(body);

            CRUDJuego crud = new CRUDJuego();

            int codigoRespuesta = crud.crearJuego(json);

            switch (codigoRespuesta) {
                case 200:
                    response.setStatus(HttpServletResponse.SC_OK);
                    respuestaJson.put("mensaje", "Videojuego registrado con éxito");
                    respuestaJson.put("exito", true);
                    break;
                case 400:
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    respuestaJson.put("mensaje", "Error: El ID del juego ya existe");
                    respuestaJson.put("exito", false);
                    break;
                default:
                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    respuestaJson.put("mensaje", "Error interno en la base de datos");
                    respuestaJson.put("exito", false);
                    break;
            }

        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            respuestaJson.put("mensaje", "Error al procesar los datos: " + e.getMessage());
            e.printStackTrace();
        }
        response.getWriter().write(respuestaJson.toString());
        
    }

}
