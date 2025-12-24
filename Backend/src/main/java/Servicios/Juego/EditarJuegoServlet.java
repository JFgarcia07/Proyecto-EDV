/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servicios.Juego;

import Controlador.Juego.CRUDJuego;
import Modelo.Juego;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "EditarJuegoServlet", urlPatterns = {"/EditarJuegoServlet"})
public class EditarJuegoServlet extends HttpServlet {

    
    private CRUDJuego crud = new CRUDJuego();
    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();
        String idJuego = request.getParameter("id");

        JSONObject json = new JSONObject();

        try {
            Juego juego = crud.obtenerJuegoPorId(idJuego);

            JSONObject data = new JSONObject();
            data.put("titulo", juego.getTitulo());
            data.put("descripcion", juego.getDescripcion());
            data.put("precio", juego.getPrecio());
            data.put("recursosMinimos", juego.getRecursosMinimos());
            data.put("clasificacion", juego.getClasificacion());
            data.put("fechaLanzamiento", juego.getFechaLanzamiento().toString());
            data.put("estadoVenta", juego.isEstadoVenta());

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
            
            int codigoRespuesta = crud.editarJuego(json);

            if (codigoRespuesta == 200) {
                response.setStatus(HttpServletResponse.SC_OK); 
                respuestaJson.put("mensaje", "Videojuego actualizado con éxito");
                respuestaJson.put("exito", true);
            } else {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); 
                respuestaJson.put("mensaje", "Error interno en la base de datos");
                respuestaJson.put("exito", false);
            }

        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            respuestaJson.put("mensaje", "Error al procesar los datos: " + e.getMessage());
            e.printStackTrace();
        }
        response.getWriter().write(respuestaJson.toString());
    }


}
