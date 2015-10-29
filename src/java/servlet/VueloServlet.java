/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceRef;
import vuelo.VueloWS_Service;

/**
 *
 * @author Rio
 */
@WebServlet(name = "VueloServlet", urlPatterns = {"/VueloServlet"})
public class VueloServlet extends HttpServlet {
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/AgenciaWS/VueloWS.wsdl")
    private VueloWS_Service service;

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
            out.println("<title>Servlet VueloServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet VueloServlet at " + request.getContextPath() + "</h1>");
            /* Servlet per provar VueloWS */
            String p = request.getParameter("idVuelo");
            int idVuelo = Integer.parseInt(p);
            p = request.getParameter("fecha");
            int fecha = Integer.parseInt(p);
            int lliures = 0;
            /* Crida a les operacions consulta i reserva */
            lliures = consultaLibres(idVuelo,fecha);
            out.println("Consulta del vol "+idVuelo+" "+fecha+": Hi ha " +lliures+ " lliures");
            int ocupades = 0;
            ocupades = reservaPlaza(idVuelo,fecha);
            out.println("<br>");
            out.println("-------------------------");
            out.println("<br>");
            out.println("S'ha reservat un vol despres de la consulta. Ara n'hi ha "+ocupades+" ocupades");
            out.println("<br>");      
            out.println("<br> <h3><a href=\"index.html\">Tornar al menu</a></h3>");
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
        return "Short description";
    }// </editor-fold>

    private int consultaLibres(int idVuelo, int fecha) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        service = new vuelo.VueloWS_Service();
        vuelo.VueloWS port = service.getVueloWSPort();
        return port.consultaLibres(idVuelo, fecha);
    }

    private int reservaPlaza(int idVuelo, int fecha) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        service = new vuelo.VueloWS_Service();
        vuelo.VueloWS port = service.getVueloWSPort();
        return port.reservaPlaza(idVuelo, fecha);
    }

}
