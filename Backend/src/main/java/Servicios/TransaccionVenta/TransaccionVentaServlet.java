/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servicios.TransaccionVenta;

import Controlador.Juego.CRUDJuego;
import Controlador.TransaccionVenta.Transaccion;
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
@WebServlet(name = "TransaccionVenta", urlPatterns = {"/TransaccionVenta"})
public class TransaccionVentaServlet extends HttpServlet {


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

        PrintWriter out = response.getWriter();
        String idJuego = request.getParameter("id");

        JSONObject json = new JSONObject();

        Juego juego = crud.obtenerJuegoPorId(idJuego);

        JSONObject data = new JSONObject();
        data.put("idEmpresa", juego.getIdEmpresa());
        data.put("titulo", juego.getTitulo());
        data.put("descripcion", juego.getDescripcion());
        data.put("precio", juego.getPrecio());
        data.put("recursosMinimos", juego.getRecursosMinimos());
        data.put("clasificacion", juego.getClasificacion());

        json.put("exito", true);
        json.put("juego", data);

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
        Transaccion venta = new Transaccion();
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        String body = request.getReader().lines().reduce("", (acc, line) -> acc + line);

        JSONObject json = new JSONObject(body);
        
        int codigoRespuesta = venta.realizaCompra(json);
        
       JSONObject respuesta = new JSONObject();
        
        
        switch (codigoRespuesta) {
            case 200:
                response.setStatus(HttpServletResponse.SC_OK);
                respuesta.put("mensaje", "Videojueo comprado con exit");
                respuesta.put("exito", true);
                break;
            case 400:
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                respuesta.put("mensaje", "Error: No tiene el saldo necesario para comprar este juego");
                respuesta.put("exito", false);
                break;
            case 402:
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                respuesta.put("mensaje", "Error: Este juego ya lo haz comprado antes, y lo tienes disponible en tu biblioteca");
                respuesta.put("exito", false);
                break;
            case 403:
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                respuesta.put("mensaje", "Error: NO ERES MAYOR DE EDAD, no puedes comprar este juego");
                respuesta.put("exito", false);
                break;
            default:
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                respuesta.put("mensaje", "Error interno en la base de datos");
                respuesta.put("exito", false);
                break;
        }

        response.getWriter().write(respuesta.toString());
        
    }
}
