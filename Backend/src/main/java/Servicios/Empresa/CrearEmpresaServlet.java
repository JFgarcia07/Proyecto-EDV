/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servicios.Empresa;

import Controlador.Empresa.CrearEmpresa;
import DB.Empresa.ComisionGlobalDB;
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
@WebServlet(name = "CrearEmpresa", urlPatterns = {"/CrearEmpresa"})
public class CrearEmpresaServlet extends HttpServlet {

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
        CrearEmpresa crearEmpresa = new CrearEmpresa();

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String body = request.getReader().lines().reduce("", (acc, line) -> acc + line);

        JSONObject json = new JSONObject(body);

        int codigoRespuesta = crearEmpresa.crearEmpresa(json);

        JSONObject respuesta = new JSONObject();

        switch (codigoRespuesta) {
            case 200:
                respuesta.put("mensaje", "Empresa creada correctamente");
                break;
            case 400:
                respuesta.put("mensaje", "Error: El ID ya existe");
                break;
            case 401: 
                respuesta.put("mensaje", "No autorizado");
                break;
            default:
                respuesta.put("mensaje", "Error desconocido");
                break;
        }

        response.getWriter().write(respuesta.toString());
    }
}
