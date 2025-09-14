/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import Modelo.Palabra;
import Modelo.PalabraDAO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author USER
 */
@WebServlet(name = "Controlador", urlPatterns = {"/Controlador"})
public class Controlador extends HttpServlet {

    PalabraDAO palabraDAO = new PalabraDAO();
    Validar validar = new Validar();

    private String escapeJson(String text) {
        if (text == null) {
            return "";
        }
        return text.replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\t", "\\t");
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        if ("login".equals(accion)) {
            String correo = request.getParameter("correo");
            String password = request.getParameter("password");

            if (validar.correoValido(correo) && validar.contraValida(password)) {
                HttpSession sesion = request.getSession();
                sesion.setAttribute("usuario", correo);

                response.sendRedirect("Controlador?accion=jugar");
            } else {
                request.setAttribute("error", "Credenciales inválidas");
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }

        } else if ("jugar".equals(accion)) {
            List<Palabra> lista = palabraDAO.listar();
            request.setAttribute("palabras", lista);
            request.getRequestDispatcher("ahorcado.jsp").forward(request, response);

        } else if ("Listar".equals(accion)) {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            List<Palabra> lista = palabraDAO.listar();


            StringBuilder json = new StringBuilder();
            json.append("[");

            for (int i = 0; i < lista.size(); i++) {
                Palabra p = lista.get(i);
                json.append("{");
                json.append("\"codigoPalabra\":").append(p.getCodigoPalabra()).append(",");
                json.append("\"nombre\":\"").append(escapeJson(p.getNombre())).append("\",");
                json.append("\"cualidadUno\":\"").append(escapeJson(p.getCualidadUno())).append("\",");
                json.append("\"cualidadDos\":\"").append(escapeJson(p.getCualidadDos())).append("\",");
                json.append("\"cualidadTres\":\"").append(escapeJson(p.getCualidadTres())).append("\"");
                json.append("}");

                if (i < lista.size() - 1) {
                    json.append(",");
                }
            }

            json.append("]");
            response.getWriter().write(json.toString());

        } else {
            request.getRequestDispatcher("index.jsp").forward(request, response);
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

}
