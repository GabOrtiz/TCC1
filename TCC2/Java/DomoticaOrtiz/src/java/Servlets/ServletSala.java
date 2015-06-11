package Servlets;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import DAO.SensorDAO;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    //casa pc COM5
    //notebrooklin COM3
    SerialInterface si = new SerialInterface("COM3", 9600);
    String tu, t, u = "";
    StringBuilder sb = new StringBuilder();
    SensorDAO SDAO = new SensorDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession sessao = request.getSession();

        response.setContentType("text/html;charset=UTF-8");

        System.out.println("entrou na servlet");

        try {

            if (request.getParameter("sala").equalsIgnoreCase("lum")) {
                System.out.println("CAIU EM Lâmpada 1");

                //registrando action de leitura que irá simplesmente printar os dados lidos
                si.read(new SerialReadAction() {
                    public void read(byte b) {
                        System.out.print((char) b);
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

            if (request.getParameter("sala").equalsIgnoreCase("ldois")) {
                System.out.println("CAIU EM Lâmpada 2");

                //registrando action de leitura que irá simplesmente printar os dados lidos
                si.read(new SerialReadAction() {
                    public void read(byte b) {
                        System.out.print(((char) b));
                    }
                });

                response.sendRedirect("./sala.jsp");
            }


            if (request.getParameter("sala").equalsIgnoreCase("por")) {
                System.out.println("CAIU EM porta");

                //registrando action de leitura que irá simplesmente printar os dados lidos
                si.read(new SerialReadAction() {
                    public void read(byte b) {
                        System.out.print((char) b);
                    }
                });
                //Comando para ligar ou desligar a lâmpada 1                
                byte[] data = new byte[1];
                data[0] = 'p';

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

            //                                                            TEMPERATURA
            if (request.getParameter("sala").equalsIgnoreCase("tut")) {
                System.out.println("CAIU EM Temperatura");
                sb = new StringBuilder();
                //Comando para ler informação do DHT11               
                byte[] data = new byte[1];
                data[0] = 't';
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
                si.read(new SerialReadAction() {
                    public void read(byte b) {
                        //System.out.print((char)b);
                        sb.append((char) b);
                    }
                });
                System.out.println(sb.toString());
                String tut = sb.toString();
                sessao.setAttribute("tut", tut);
                int uid = Integer.parseInt(sessao.getAttribute("id").toString());
                Date dataf = new Date();
                SimpleDateFormat ft = new SimpleDateFormat("E dd.MM.yyyy 'às' hh:mm:ss");
                //System.out.println("Current Date: " + ft.format(data));            
                SDAO.inserir(uid, "temperatura", tut, ft.format(dataf));
                response.sendRedirect("./sala.jsp");            
            }
            
            //                                                         UMIDADE
             if (request.getParameter("sala").equalsIgnoreCase("tuu")) {
                System.out.println("CAIU EM Temperatura");
                sb = new StringBuilder();
                //Comando para ler informação do DHT11               
                byte[] data = new byte[1];
                data[0] = 'u';
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
                si.read(new SerialReadAction() {
                    public void read(byte b) {
                        //System.out.print((char)b);
                        sb.append((char) b);
                    }
                });
                System.out.println(sb.toString());
                String tuu = sb.toString();
                sessao.setAttribute("tuu", tuu);
                int uid = Integer.parseInt(sessao.getAttribute("id").toString());
                Date dataf = new Date();
                SimpleDateFormat ft = new SimpleDateFormat("E dd.MM.yyyy 'às' hh:mm:ss");
                //System.out.println("Current Date: " + ft.format(data));            
                SDAO.inserir(uid, "umidade", tuu, ft.format(dataf));
                response.sendRedirect("./sala.jsp");            
            }

            //MOVIMENTO
            if (request.getParameter("sala").equalsIgnoreCase("movimento")) {
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
                //registrando action de leitura que irá simplesmente printar os dados lidos
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
                SimpleDateFormat ft = new SimpleDateFormat("E dd.MM.yyyy 'às' hh:mm:ss");
                //System.out.println("Current Date: " + ft.format(data));            
                SDAO.inserir(uid, "movimento", movimento, ft.format(dataf));
                response.sendRedirect("./sala.jsp");
            }

//         //CHUVA
            if (request.getParameter("sala").equalsIgnoreCase("chuva")) {
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
                //registrando action de leitura que irá simplesmente printar os dados lidos
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
                SimpleDateFormat ft = new SimpleDateFormat("E dd.MM.yyyy 'às' hh:mm:ss");
                //System.out.println("Current Date: " + ft.format(data));            
                SDAO.inserir(uid, "chuva", chuva, ft.format(dataf));
                response.sendRedirect("./cozinha.jsp");
            }

            //GÁS
            if (request.getParameter("sala").equalsIgnoreCase("gas")) {
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
                SimpleDateFormat ft = new SimpleDateFormat("E dd.MM.yyyy 'às' hh:mm:ss");
                //System.out.println("Current Date: " + ft.format(data));            
                SDAO.inserir(uid, "gas", gas, ft.format(dataf));

                response.sendRedirect("./cozinha.jsp");
            }

        } catch (NullPointerException e) {
            response.sendRedirect("./erro.jsp");
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
