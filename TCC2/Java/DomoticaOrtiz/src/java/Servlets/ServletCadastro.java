/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Classes.Usuario;
import DAO.UsuarioDAO;
import Criptografia.Cryptography;
import Criptografia.CryptographySHA512;
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
@WebServlet(name = "ServletCadastro", urlPatterns = {"/ServletCadastro"})
public class ServletCadastro extends HttpServlet {

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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        UsuarioDAO UDAO = new UsuarioDAO();
        
        Cryptography cryptography;
        cryptography = new CryptographySHA512();
        String senhac = "";

        if(request.getParameter("nome")!= null && request.getParameter("endereco")!= null && request.getParameter("cpf")!= null && request.getParameter("email")!= null && request.getParameter("senha")!= null && 
           request.getParameter("nome")!= ""   && request.getParameter("endereco")!= ""   && request.getParameter("cpf")!= "" && request.getParameter("cpf").length()==11 && request.getParameter("email")!= ""   && request.getParameter("senha")!= ""){
        
        String nome = request.getParameter("nome");
        String endereco = request.getParameter("endereco");
        String cpf = request.getParameter("cpf");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha"); 
        
        
                    try {
                        senhac = cryptography.encrypt(senha);
                    } catch (NoSuchAlgorithmException ex) {
                        Logger.getLogger(ServletCadastro.class.getName()).log(Level.SEVERE, null, ex);
                    }
        
        UDAO.inserir(nome, endereco, cpf, email, senhac);
        
        System.out.println(nome+" "+ email+" "+ senha);
        
        
        
        response.sendRedirect("./inicial.jsp");
        }
        
        else if(request.getParameter("alt").equalsIgnoreCase("end")){
            System.out.println("CAIU EM ALTERA END !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            
            HttpSession sessao = request.getSession(true);
            int id = Integer.parseInt(sessao.getAttribute("id").toString());
            String novoEnd = request.getParameter("end");
            
            
            System.out.println(id +" "+ novoEnd);
            UDAO.alterarEnd(id, novoEnd);
            sessao.setAttribute("endereco", novoEnd);
            response.sendRedirect("./configuracoes.jsp");
        }
        
        
        
        
         else if(request.getParameter("alt").equalsIgnoreCase("em")){
            System.out.println("CAIU EM ALTERA EMAIL !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            
            HttpSession sessao = request.getSession(true);
            int id = Integer.parseInt(sessao.getAttribute("id").toString());
            String novoEm = request.getParameter("em");
            
            
            System.out.println(id +" "+ novoEm);
            UDAO.alterarEm(id, novoEm);
            sessao.setAttribute("email", novoEm);
            response.sendRedirect("./configuracoes.jsp");
        }
         
         
         
         
         else if(request.getParameter("alt").equalsIgnoreCase("senha")){
            System.out.println("CAIU EM ALTERA EMAIL !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            
            HttpSession sessao = request.getSession(true);
            int id = Integer.parseInt(sessao.getAttribute("id").toString());
            String novaSenha = request.getParameter("senha");
            
            
            try {
                        senhac = cryptography.encrypt(novaSenha);
                    } catch (NoSuchAlgorithmException ex) {
                        Logger.getLogger(ServletCadastro.class.getName()).log(Level.SEVERE, null, ex);
                    }
            
            
            System.out.println(id +" "+ novaSenha);
            UDAO.alterarSenha(id, senhac);
            response.sendRedirect("./configuracoes.jsp");
        }
        
        
        
        
        else
            response.sendRedirect("./erro.jsp");
        
        
        
        
//        try {
//            senhac = cryptography.encrypt(senha);
//        } catch (NoSuchAlgorithmException ex) {
//            Logger.getLogger(ServletCadastro.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        
//        UDAO.inserir(nome, email, senhac);
//        
//        if(UDAO.buscar(email, senhac) != null){
//
//        HttpSession sessao = request.getSession(true);
//        //JOptionPane.showMessageDialog(null, UDAO.buscar(email, senha));
//
//        System.out.print(UDAO.buscar(email, senhac));
//        //sessao.setAttribute("Usuario", UDAO.buscar(email, senha));
//        Usuario u = UDAO.buscar(email, senhac);
//
//        sessao.setAttribute("id", u.getId());
//        sessao.setAttribute("nome", u.getNome());
//        sessao.setAttribute("email", u.getEmail());
//
//        //response.sendRedirect("./teste.jsp");
//        response.sendRedirect("./inicial.jsp");
//    
//        }else{
//            PrintWriter out = response.getWriter();
//        out.println("Username ou senha inválido!");
//        //request.getRequestDispatcher("./erro.jsp").include(request, response);
//        response.sendRedirect("./erro.jsp");
//    }
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
