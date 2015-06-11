/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import java.io.IOException;
import DAO.SensorDAO;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 *
 * @author Ortiz
 */
@WebServlet(name = "ServletSensores", urlPatterns = {"/ServletSensores"})
public class ServletSensores extends HttpServlet {

    SensorDAO SDAO = new SensorDAO();
    //abrindo comunicacao serial
    SerialInterface si = new SerialInterface("COM3", 9600);
    String tu, t, u = "";
    StringBuilder sb = new StringBuilder();

    protected void processRequest(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        HttpSession sessao = request.getSession();

        response.setContentType("text/html;charset=UTF-8");

        System.out.println("entrou na servlet sensores");

        //CHUVA
        if (request.getParameter("sensores").equalsIgnoreCase("chuva")) {
            System.out.println("CAIU EM chuva");
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
            //registrando action de leitura que irásimplesmente printar os dados lidos
            si.read(new SerialReadAction() {
                public void read(byte b) {
                    //System.out.print((char)b);
                    sb.append((char) b);
                }
            });
            System.out.println(sb.toString());
            String chuva = sb.toString();
            sessao.setAttribute("chuva", chuva);

            int uid = Integer.parseInt(sessao.getAttribute("id").toString());
            Date dataf = new Date();
            SimpleDateFormat ft = new SimpleDateFormat("E yyyy.MM.dd 'às' hh:mm:ss");
            //System.out.println("Current Date: " + ft.format(data));            
            SDAO.inserir(uid, "chuva", chuva, ft.format(dataf));
            response.sendRedirect("./cozinha.jsp");
        }


        //GÁS
        if (request.getParameter("sensores").equalsIgnoreCase("gas")) {
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
            //registrando action de leitura que irásimplesmente printar os dados lidos
            si.read(new SerialReadAction() {
                public void read(byte b) {
                    //System.out.print((char)b);
                    sb.append((char) b);
                }
            });
            System.out.println(sb.toString());
            String gas = sb.toString();
            sessao.setAttribute("gas", gas);
            int uid = Integer.parseInt(sessao.getAttribute("id").toString());
            Date dataf = new Date();
            SimpleDateFormat ft = new SimpleDateFormat("E yyyy.MM.dd 'às' hh:mm:ss");
            //System.out.println("Current Date: " + ft.format(data));            
            SDAO.inserir(uid, "gas", gas, ft.format(dataf));

            response.sendRedirect("./cozinha.jsp");
        }



        //MOVIMENTO
        if (request.getParameter("sensores").equalsIgnoreCase("movimento")) {
            System.out.println("CAIU EM movimento");
            sb = new StringBuilder();
            //Comando para ler informação do DHT11
            byte[] data = new byte[1];
            data[0] = 'm';
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
            //registrando action de leitura que irásimplesmente printar os dados lidos
            si.read(new SerialReadAction() {
                public void read(byte b) {
                    //System.out.print((char)b);
                    sb.append((char) b);
                }
            });
            System.out.println(sb.toString());
            String movimento = sb.toString();
            sessao.setAttribute("movimento", movimento);
            int uid = Integer.parseInt(sessao.getAttribute("id").toString());
            Date dataf = new Date();
            SimpleDateFormat ft = new SimpleDateFormat("E yyyy.MM.dd 'às' hh:mm:ss");
            //System.out.println("Current Date: " + ft.format(data));            
            SDAO.inserir(uid, "movimento", movimento, ft.format(dataf));
            response.sendRedirect("./sala.jsp");
        }





    }

    // <editor-fold defaultstate="collapsed" desc="HttpServletmethods. Click on the + sign on the left to edit the code.">
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
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
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
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
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