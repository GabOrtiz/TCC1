<%-- 
    Document   : cozinha
    Created on : 16/04/2015, 19:58:56
    Author     : Ortiz
--%>

<%@page import="Servlets.SerialReadAction"%>
<%@page import="Servlets.SerialInterface"%>
<%@page import="Servlets.ServletSensores"%>
<%@page import="javax.sql.rowset.serial.SerialArray"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/sb-admin-2.css" rel="stylesheet">
        <link href="css/icons.css" rel="stylesheet">
        <link type="text/css" rel="stylesheet" href="css.css">
        <link href="fonts/font-awesome.min.css" rel="stylesheet" type="text/css">

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
        <script type="text/javascript" src="js/bootstrap.min.js"></script>
        <script type="text/javascript" src="js/jquery-1.9.1.min.js"></script>
        <script src="https://code.jquery.com/jquery.js"></script>      
        <script src="js/bootstrap.min.js"></script>
        
        <% HttpSession sessao = request.getSession(true);
            sessao.setAttribute("gas", "nada detectado");
            sessao.setAttribute("chuva", "sensor seco");
        %>
        
        <title>Domotica Ortiz</title>
    </head>

    <body>
        <div id="wrapper">
            <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="inicial.jsp">Domotica Ortiz</a>
                </div>
                <!-- /.navbar-header -->

                <ul class="nav navbar-top-links navbar-right">     
                    <!-- /.dropdown -->
                    <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                            <l><%=sessao.getAttribute("nome")%></l>
                            <i class="fa fa-user fa-fw"></i>  <i class="fa fa-caret-down"></i>
                        </a>
                        <ul class="dropdown-menu dropdown-user">
                            <li><a href="inicial.jsp"><i class="fa fa-user fa-fw"></i> Inicial</a>
                            </li>
                            <li><a href="configuracoes.jsp"><i class="fa fa-gear fa-fw"></i> Configurações</a>
                            </li>
                            <li class="divider"></li>
                            <li><a>
                                    <span class="pull-right">
                                        <form action="./ServletSair">
                                            <label for="sair">Sair</label>        
                                            <input type="submit"  name="sair" value="Sair" id="sair">   
                                        </form>
                                    </span>  
                                    <i class="fa fa-sign-out fa-fw"></i>  </a>

                            </li>
                        </ul>
                    </li>
                </ul>
            </nav>
        </div>

        <div class="container">
            <div class="row clearfix">
                <div class="col-md-6 column centralizado">
                    <ul class="nav nav-tabs">
                        <li><a href='inicial.jsp'>Inicial</a></li>
                        <li><a href='sala.jsp'>Sala</a></li>
                        <li><a href='dormum.jsp'>Dormitório 1</a></li>                       
                        <li class="active"><a href='cozinha.jsp'>Cozinha</a></li>
                        <li><a href='registros.jsp'>Registros</a></li>
                    </ul>
                </div>
            </div>
            
            <br>
            <br>
            
            <div class="row">


                <%-- ##########################                LÂMPADA TETO --%>
                <div class="col-lg-4 col-md-4">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-3">
                                    <i class="fa fa-lightbulb-o fa-5x"></i>
                                </div>
                                <div class="col-xs-9 text-right">
                                    <div class="medium">Teto</div>
                                    <div>Lâmpada do teto</div>
                                </div>
                            </div>
                        </div>
                        <div class="panel-footer">
                            <span class="pull-left">
                                <form action="./ServletSala">
                                    <label for="lamp1">Ligar / Desligar</label>        
                                    <input type="submit"  name="sala" value="lum" id="lamp1">       
                                </form>
                            </span>  
                            <span class="pull-right"><i class="fa fa-adjust"></i></span>
                            <div class="clearfix"></div>
                        </div>
                    </div>     
                </div>

                <%-- ##########################                       CHUVA --%>
                <div class="col-lg-4 col-md-4">
                    <div class="panel panel-red">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-3">
                                    <i class="fa fa-umbrella fa-5x"></i>
                                </div>
                                <div class="col-xs-9 text-right">
                                    <div class="medium"><%=session.getAttribute("chuva")%></div>
                                    <div>Detector de chuva</div>
                                </div>
                            </div>
                        </div>
                        <div class="panel-footer">
                            <span class="pull-left">
                                <form action="./ServletSala">
                                    <label for="ch1">Atualizar</label>      
                                    <input type="submit"  name="sala" value="chuva" id="ch1">       
                                </form>
                            </span>  
                            <span class="pull-right"><i class="fa fa-spinner"></i></span>
                            <div class="clearfix"></div>
                        </div>
                        </a>
                    </div>
                </div>

               



                <%-- ##########################                         GÁS --%>
                <div class="col-lg-4 col-md-4">
                    <div class="panel panel-red">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-3">
                                    <i class="fa fa-fire-extinguisher fa-5x"></i>
                                </div>
                                <div class="col-xs-9 text-right">
                                    <div class="medium"><%=session.getAttribute("gas")%></div>
                                    <div>Detector de gases</div>
                                </div>
                            </div>
                        </div>
                        <div class="panel-footer">
                            <span class="pull-left">
                                <form action="./ServletSala">
                                    <label for="gas1">Atualizar</label>        
                                    <input type="submit"  name="sala" value="gas" id="gas1">       
                                </form>
                            </span>  
                            <span class="pull-right"><i class="fa fa-spinner"></i></span>
                            <div class="clearfix"></div>
                        </div>
                        </a>
                    </div>
                </div>



            </div>
        </div>




        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
        <!-- Include all compiled plugins (below), or include individual files as needed -->
        <script src="js/bootstrap.min.js"></script>

        <footer class="footer">
            <br>
                <a href='www.google.com'>   Sistema desenvolvido por Gabriel Ortiz de Fraga                /                SENACRS</a>
        </footer>


    </body>




</html>
