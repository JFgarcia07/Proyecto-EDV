/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servicios;

import Controlador.ValidarLogIn;
import DB.SesionGlobal;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONObject;

/**
 *
 * @author jfgarcianata
 */
public class LogInServlet extends HttpServlet {

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
        ValidarLogIn validar = new ValidarLogIn();

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String body = request.getReader().lines().reduce("", (acc, line) -> acc + line);

        JSONObject json = new JSONObject(body);

        String email = json.getString("email");
        String password = json.getString("password");

        boolean validacion = validar.validarCredenciales(email, password);

        JSONObject respuesta = new JSONObject();

        if (validacion == true) {
            respuesta.put("succes", validacion);
            respuesta.put("idUsuario", SesionGlobal.idPersonal);
        } else {
            respuesta.put("error", validacion);
        }

        response.getWriter().write(respuesta.toString());
    }

}
