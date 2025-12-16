/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servicios.Usuario;

import Controlador.Usuario.RecargarSaldo;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 *
 * @author jfgarcianata
 */
@WebServlet(name = "BilleteraServlet", urlPatterns = {"/BilleteraServlet"})
public class BilleteraServlet extends HttpServlet {

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
        RecargarSaldo recarga = new RecargarSaldo();
        
        response.setContentType("text/plain;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String idUsuario = request.getParameter("id");
        String montoString = request.getParameter("monto");
        
        try {
            double monto = Double.parseDouble(montoString);
            
            recarga.recargarSaldo(idUsuario, monto);
            
            out.print("Recarga realizada con exito");
            
        } catch (NumberFormatException e) {
            out.print("Error: El monto ingresado no es un numero");
        } catch (Exception e) {
            e.printStackTrace();
            out.print("Error interno en el servidor: " + e.getMessage());
        }
        
        out.flush();
    }
}
