/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;





import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Gabriel
 */
@WebServlet(name = "ServletCozinha", urlPatterns = {"/ServletCozinha"})
public class ServletCozinha extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    
    SerialInterface si = new SerialInterface("COM3", 9600);
    
    String tu, t,u = "";
    StringBuilder sb = new StringBuilder();
    
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        response.setContentType("text/html;charset=UTF-8");
        HttpSession sessao = request.getSession();
        response.setContentType("text/html;charset=UTF-8");
        System.out.println("entrou na servlet cozinha");
        
        
         //CHUVA
         if(request.getParameter("cozinha").equalsIgnoreCase("chuva")){
            System.out.println("CAIU EM Temperatura e Umidade");                    
                sb = new StringBuilder();
                //Comando para ler informação do DHT11               
                        byte[] data = new byte[1];
                        data[0] = 'c';
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
                        //registrando action de leitura que irá simplesmente printar os dados lidos
                si.read( new SerialReadAction() {                     
                        public void read(byte b) {                               
                                //System.out.print((char)b);
                                sb.append((char)b);
                        }                
                }); 
               System.out.println(sb.toString());
               String chuva = sb.toString();
               sessao.setAttribute("chuva", chuva);
            response.sendRedirect("./sala.jsp");
        }
         
         
         //GÁS
         if(request.getParameter("cozinha").equalsIgnoreCase("gas")){
            System.out.println("CAIU EM gas");                    
                sb = new StringBuilder();
                //Comando para ler informação do DHT11               
                        byte[] data = new byte[1];
                        data[0] = 'g';
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
                        //registrando action de leitura que irá simplesmente printar os dados lidos
                si.read( new SerialReadAction() {                     
                        public void read(byte b) {                               
                                //System.out.print((char)b);
                                sb.append((char)b);
                        }                
                }); 
               System.out.println(sb.toString());
               String gas = sb.toString();
               sessao.setAttribute("gas", gas);
            response.sendRedirect("./sala.jsp");
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
