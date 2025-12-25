/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servicios.Empresa;

import Controlador.Empresa.CRUDEmpresa;
import Modelo.Empresa;
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
@WebServlet(name = "EditarEmpresa", urlPatterns = {"/EditarEmpresa"})
public class EditarEmpresa extends HttpServlet {

    private CRUDEmpresa crud = new CRUDEmpresa();
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
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();
        String idEmpresa = request.getParameter("id");

        JSONObject json = new JSONObject();
        
        Empresa empresa = crud.obtnerEmpresaPorID(idEmpresa);
        
        JSONObject data = new JSONObject();
        
        data.put("nombreEmpresa", empresa.getNombreEmpresa());
        data.put("descripcion", empresa.getDescripcion());
        data.put("porcentaje", empresa.getPorcentajeEspecifico());
        data.put("idComision", empresa.getIdPorcentajeGlobal());
        
        json.put("exito", true);
        json.put("empresa", data);
        
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

        String body = request.getReader().lines().reduce("", (acc, line) -> acc + line);

        JSONObject json = new JSONObject(body);
        
        JSONObject respuestaJson = new JSONObject();
         
        int codigoRespuesta = crud.actualizarEmpresa(json);
       
        switch (codigoRespuesta) {
            case 200:
                response.setStatus(HttpServletResponse.SC_OK);
                respuestaJson.put("mensaje", "Empresa actualizada con éxito");
                respuestaJson.put("exito", true);
                break;
            case 400:
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                respuestaJson.put("mensaje", "El valor ingresado no es un valor valido");
                respuestaJson.put("exito", false);
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
