/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servicios.Juego;

import Controlador.Juego.CRUDJuego;
import Modelo.Juego;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import org.json.JSONObject;

/**
 *
 * @author jfgarcianata
 */
@WebServlet(name = "InfoJuegoPorID", urlPatterns = {"/InfoJuegoPorID"})
public class InfoJuegoPorID extends HttpServlet {


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

        Juego juego = crud.inforJuegoPorID(idJuego);

        JSONObject data = new JSONObject();
        data.put("titulo", juego.getTitulo());
        data.put("descripcion", juego.getDescripcion());
        data.put("precio", juego.getPrecio());
        data.put("recursosMinimos", juego.getRecursosMinimos());
        data.put("clasificacion", juego.getClasificacion());
        data.put("fechaLanzamiento", juego.getFechaLanzamiento().toString());
        data.put("nombreEmpresa", juego.getNombreEmpresa());

        json.put("exito", true);
        json.put("juego", data);

        out.print(json.toString());
    }

}
