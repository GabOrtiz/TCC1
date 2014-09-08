package Servlets;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ortiz
 */
@WebServlet(name = "ServletSala", urlPatterns = {"/ServletSala"})
public class ServletSala extends HttpServlet {

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
    
    
    //abrindo comunicacao serial 
    SerialInterface si = new SerialInterface("COM5", 9600);
    
    String t,u = "";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        System.out.println("entrou na servlet");
        
         if(request.getParameter("sala").equalsIgnoreCase("lum")){
            System.out.println("CAIU EM Lâmpada 1");       
                        
                //registrando action de leitura que irá simplesmente printar os dados lidos
                si.read( new SerialReadAction() {                        
                        public void read(byte b) {
                                System.out.print((char)b);
                        }                        
                });
                //Comando para ligar ou desligar a lâmpada 1                
                        byte[] data = new byte[1];
                        data[0] = 'a';
                        
                        try {
                                //enviando dados via porta serial
                                si.write(data);
                                //dormindo por 1 segundo
                                Thread.sleep(1000);
                        } catch (IOException e) {
                                e.printStackTrace();
                        } catch (InterruptedException e) {
                                e.printStackTrace();
                        }           
                
            response.sendRedirect("./sala.jsp");
        }
         
         
         
         
         if(request.getParameter("sala").equalsIgnoreCase("ldois")){
            System.out.println("CAIU EM Lâmpada 2");     
                        
                //registrando action de leitura que irá simplesmente printar os dados lidos
                si.read( new SerialReadAction() {                        
                        public void read(byte b) {
                            System.out.print(((char)b));
                        }                        
                });
                           
                
            response.sendRedirect("./sala.jsp");
        }
         
         
         
         
         if(request.getParameter("sala").equalsIgnoreCase("ac")){
            System.out.println("CAIU EM Condicionador de ar");       
                        
                //registrando action de leitura que irá simplesmente printar os dados lidos
                si.read( new SerialReadAction() {                        
                        public void read(byte b) {
                                System.out.print((char)b);
                        }                        
                });
                //Comando para ligar ou desligar a lâmpada 1                
                        byte[] data = new byte[1];
                        data[0] = 'a';
                        try {
                                //enviando dados via porta serial
                                si.write(data);
                                //dormindo por 1 segundo
                                Thread.sleep(1000);
                        } catch (IOException e) {
                                e.printStackTrace();
                        } catch (InterruptedException e) {
                                e.printStackTrace();
                        }           
                
            response.sendRedirect("./sala.jsp");
        }
         
         
         
         
         
         if(request.getParameter("sala").equalsIgnoreCase("tp")){
            System.out.println("CAIU EM Trava da Porta");    
                        
                //registrando action de leitura que irá simplesmente printar os dados lidos
                si.read( new SerialReadAction() {                        
                        public void read(byte b) {
                                System.out.print((char)b);
                        }                        
                });
                //Comando para ligar ou desligar a lâmpada 1                
                        byte[] data = new byte[1];
                        data[0] = 'a';
                        try {
                                //enviando dados via porta serial
                                si.write(data);
                                //dormindo por 1 segundo
                                Thread.sleep(1000);
                        } catch (IOException e) {
                                e.printStackTrace();
                        } catch (InterruptedException e) {
                                e.printStackTrace();
                        }           
                
            response.sendRedirect("./sala.jsp");
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
