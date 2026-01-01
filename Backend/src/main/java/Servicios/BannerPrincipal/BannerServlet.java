/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servicios.BannerPrincipal;

import Controlador.BannerPrincipal.CRUDBanner;
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
@WebServlet(name = "BannerServlet", urlPatterns = {"/BannerServlet"})
public class BannerServlet extends HttpServlet {

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

        CRUDBanner crud = new CRUDBanner();

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String body = request.getReader().lines().reduce("", (acc, line) -> acc + line);
        JSONObject json = new JSONObject(body);

        int codigo = crud.agregarImagen(json);

        JSONObject resp = new JSONObject();

        switch (codigo) {
            case 200:
                response.setStatus(HttpServletResponse.SC_OK);
                resp.put("exito", true);
                resp.put("mensaje", "Imagen agregada al banner.");
                break;
            case 400:
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.put("exito", false);
                resp.put("mensaje", "Datos inválidos (idJuego/imagen/orden).");
                break;
            default:
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                resp.put("exito", false);
                resp.put("mensaje", "Error interno al guardar la imagen.");
                break;
        }

        response.getWriter().write(resp.toString());
    }


}
