/*
 * Use and copying for commercial purposes 
 * only with the author's permission
 */
package ru.myservlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Yuri Tveritin
 * @version 1.0
 */
@WebServlet(name = "MyServlet1", urlPatterns = {"/MyServlet1"})
public class MyServlet1 extends HttpServlet {
    CountriesTableReader ctr;
    private TreeMap <Integer, String> countriesList;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet MyServlet1</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("");
            out.println("<h1>Servlet MyServlet1 at " + request.getContextPath() + "</h1>");
            out.println("<h1>Servlet MyServlet1 at " + request.getProtocol() + "</h1>");
            ctr=new CountriesTableReader();
            ctr.readData();
            countriesList=ctr.getCountriesList();
            Set<Map.Entry<Integer, String>> countrynames = countriesList.entrySet();//(treemap: ключ=url, значение=site_id)
            for (Map.Entry<Integer, String> itemcountry: countrynames) {
                out.println("<h1>Hi"+itemcountry.getValue()+"</h1>");
            }          
            out.println("</body>");
            out.println("</html>");
        }
    }

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
        processRequest(request, response);
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
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "My servlet for tests";
    }// </editor-fold>

}
