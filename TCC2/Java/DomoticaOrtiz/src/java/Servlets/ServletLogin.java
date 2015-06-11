/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Classes.Usuario;
import Criptografia.Cryptography;
import Criptografia.CryptographySHA512;
import DAO.UsuarioDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 *
 * @author Ortiz
 */
@WebServlet(name = "ServletLogin", urlPatterns = {"/ServletLogin"})
public class ServletLogin extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    Cryptography cryptography;
 
        //Criptografia usando MD5
        
    
    public String Criptografar(String s) throws NoSuchAlgorithmException{
        cryptography = new CryptographySHA512();
        String senhacrip = "";
        senhacrip = cryptography.encrypt(s);
        return senhacrip;
    }



    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        try {
            response.setContentType("text/html;charset=UTF-8");

            UsuarioDAO UDAO = new UsuarioDAO();

            String email = request.getParameter("email");
            //String senha = request.getParameter("senha");
            String senhac = Criptografar(request.getParameter("senha"));


             System.out.println(email);
             System.out.println(senhac);
             
             
             
            if(email==null || senhac==null){
                response.sendRedirect("./erro.jsp");
            }
            
            
            else if(UDAO.buscar(email, senhac).email.equalsIgnoreCase(email) && UDAO.buscar(email, senhac).getSenha().equalsIgnoreCase(senhac) ){
                HttpSession sessao = request.getSession(true);
                //JOptionPane.showMessageDialog(null, UDAO.buscar(email, senha));
                
                System.out.print(UDAO.buscar(email,senhac));
                //sessao.setAttribute("Usuario", UDAO.buscar(email, senha));
                Usuario u = UDAO.buscar(email, senhac);
                
                sessao.setAttribute("id", u.getId());
                sessao.setAttribute("nome", u.getNome());
                sessao.setAttribute("endereco", u.getEndereco());
                sessao.setAttribute("cpf", u.getCpf());
                sessao.setAttribute("email", u.getEmail());
                //response.sendRedirect("./teste.jsp");
                response.sendRedirect("./inicial.jsp");
            }
            else{
                PrintWriter out = response.getWriter();
                out.println("Username ou senha inválido!");
                //request.getRequestDispatcher("./erro.jsp").include(request, response);
                response.sendRedirect("./erro.jsp");
            }
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(ServletLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
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
     * Handles the HTTP
     * <code>POST</code> method.
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
