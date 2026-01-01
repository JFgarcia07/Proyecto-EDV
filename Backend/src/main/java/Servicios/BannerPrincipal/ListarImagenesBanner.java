/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servicios.BannerPrincipal;

import Controlador.BannerPrincipal.CRUDBanner;
import Modelo.BannerPrincipal;
import com.google.gson.Gson;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author jfgarcianata
 */
@WebServlet(name = "ListarImagenesBanner", urlPatterns = {"/ListarImagenesBanner"})
public class ListarImagenesBanner extends HttpServlet {


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
        
        CRUDBanner banner = new CRUDBanner();
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        List<BannerPrincipal> listaImagenes = banner.listaImagenesBanner();
        String json = new Gson().toJson(listaImagenes);

        response.getWriter().write(json);
    }

}
