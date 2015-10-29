/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import hotel.HotelWS_Service;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceRef;

/**
 *
 * @author Rio
 */
@WebServlet(name = "HotelServlet", urlPatterns = {"/HotelServlet"})
public class HotelServlet extends HttpServlet {
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/AgenciaWS/HotelWS.wsdl")
    private HotelWS_Service service;

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
            out.println("<title>Servlet HotelServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet HotelServlet at " + request.getContextPath() + "</h1>");
            /* Servlet per provar HotelWS */
            String p = request.getParameter("idHotel");
            int idHotel = Integer.parseInt(p);
            p = request.getParameter("fecha");
            int fecha = Integer.parseInt(p);
            int lliures = 0;
            /* Crida a les operacions consulta i reserva */
            lliures = consultaLibres(idHotel,fecha);
            out.println("Consulta de l'hotel "+idHotel+" "+fecha+": Hi ha " +lliures+ " lliures");
            int ocupades = 0;
            ocupades = reservaHabitacion(idHotel,fecha);
            out.println("<br>");
            out.println("-------------------------");
            out.println("<br>");
            out.println("S'ha reservat una habitacio despres de la consulta. Ara n'hi ha "+ocupades+" ocupades");
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

    private int consultaLibres(int idHotel, int fecha) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        service = new hotel.HotelWS_Service();
        hotel.HotelWS port = service.getHotelWSPort();
        return port.consultaLibres(idHotel, fecha);
    }

    private int reservaHabitacion(int idHotel, int fecha) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        service = new hotel.HotelWS_Service();
        hotel.HotelWS port = service.getHotelWSPort();
        return port.reservaHabitacion(idHotel, fecha);
    }

}
