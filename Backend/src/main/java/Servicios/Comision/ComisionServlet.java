/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servicios.Comision;

import Controlador.Comision.ComisionGlob;
import DB.Comision.ComisionGlobalDB;
import Modelo.ComisionGlobal;
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
@WebServlet(name = "ComisionServlet", urlPatterns = {"/ComisionServlet"})
public class ComisionServlet extends HttpServlet {

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
        ComisionGlobalDB comisionDB = new ComisionGlobalDB();
        List<ComisionGlobal> listaComision = comisionDB.listarComisiones();

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String json = new Gson().toJson(listaComision);
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

        ComisionGlob comision = new ComisionGlob();

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String body = request.getReader().lines().reduce("", (acc, line) -> acc + line);

        JSONObject json = new JSONObject(body);
        JSONObject respuesta = new JSONObject();

        double valor = json.getDouble("porcentaje");

        int codigoRespuesta = comision.cambiarPorcentaje(valor);

        switch (codigoRespuesta) {
            case 200:
                response.setStatus(HttpServletResponse.SC_OK);
                respuesta.put("exito", true);
                respuesta.put("mensaje", "Comisión actualizada correctamente.");
                break;
            case 400:
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                respuesta.put("exito", false);
                respuesta.put("mensaje", "Dato inválido. El porcentaje debe ser un número válido (y en rango permitido).");
                break;

            case 401:
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                respuesta.put("exito", false);
                respuesta.put("mensaje", "El valor ingresado no es un numero.");
                break;

            default:
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                respuesta.put("exito", false);
                respuesta.put("mensaje", "Error interno al actualizar la comisión.");
                break;
        }
        response.getWriter().write(respuesta.toString());
    }
}
