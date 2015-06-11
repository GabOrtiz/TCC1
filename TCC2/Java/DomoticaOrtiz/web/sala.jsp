<%--
    Document   : sala
    Created on : 26/08/2014, 02:03:32
    Author     : Ortiz
--%>

<%@page import="javax.mail.Session"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="Classes.Sensor"%>
<%@page import="DAO.SensorDAO"%>
<%@page import="Servlets.SerialReadAction"%>
<%@page import="Servlets.SerialInterface"%>
<%@page import="Servlets.ServletSala"%>
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
        
        
        <script type="text/javascript" src="js/jquery-1.7.1.min.js" ></script>
		<script>

			$(document).ready(function() {

				setInterval( $.mandaTudo, 2000 );

			});
			
			$.movimento = function(){
						
				var comando = $('#movimento').attr('href');
				
				$.ajax({
					url: './ServletSala',
					type: 'POST',
					data: {sala : "movimento"},
					dataType: 'html',
					async: false,
					success: function(ret) {
						$('#mensagem1').html('<br>Enviou '+comando);
					},
					error: function (xhr, desc, er) {
						alert(xhr.responseText);
					}
				});
			}
			
			$.mandaDados2 = function(){
			
				var comando = $('#comando2').attr('href');
				
				$.ajax({
					url: '',
					type: 'POST',
					data: {valor : comando},
					dataType: 'html',
					async: false,
					success: function(ret) {
						$('#mensagem2').html('<br>Enviou '+comando);
					},
					error: function (xhr, desc, er) {
						alert(xhr.responseText);
					}
				});
			}
			
			$.mandaDados3 = function(){
			
				var comando = $('#comando3').attr('href');
			
				$.ajax({
					url: '',
					type: 'POST',
					data: {valor : comando},
					dataType: 'html',
					async: false,
					success: function(ret) {
						$('#mensagem3').html('<br>Enviou '+comando);
					},
					error: function (xhr, desc, er) {
						alert(xhr.responseText);
					}
				});
			}
			
			$.mandaTudo = function(){
				$.movimento();
				

				location.reload();
			}
			
		</script>

        <% HttpSession sessao = request.getSession(true);
            //Sensor s = new Sensor();
            int id = Integer.parseInt(String.valueOf(sessao.getAttribute("id")));
            SensorDAO sdao = new SensorDAO();
            List<Sensor> sensores = new ArrayList<Sensor>();
            
             sessao.setAttribute("movimento", "nada detectado");
             sessao.setAttribute("tuu", "45%");
             sessao.setAttribute("tut", "14°C");
             

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
                        <li class="active"><a href='sala.jsp'>Sala</a></li>
                        <li><a href='dormum.jsp'>Dormitório 1</a></li>                       
                        <li><a href='cozinha.jsp'>Cozinha</a></li>
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



                <%-- ##########################              TRAVA ELÉTRICA --%>
                <div class="col-lg-4 col-md-4">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-3">
                                    <i class="fa fa-unlock-alt fa-5x"></i>
                                </div>
                                <div class="col-xs-9 text-right">
                                    <div class="medium">Porta</div>
                                    <div>Porta de entrada</div>
                                </div>
                            </div>
                        </div>
                        <div class="panel-footer">
                            <span class="pull-left">
                                <form action="./ServletSala">
                                    <label for="porta1">Destrancar (10 segundos)</label>        
                                    <input type="submit"  name="sala" value="por" id="porta1">       
                                </form>
                            </span>  
                            <span class="pull-right"><i class="fa fa fa-adjust"></i></span>
                            <div class="clearfix"></div>
                        </div>
                        </a>
                    </div>
                </div>

                <%-- ##########################                  VENTILADOR --%>
                <div class="col-lg-4 col-md-4">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-3">
                                    <i class="fa fa-plus fa-spin fa-5x"></i>
                                </div>
                                <div class="col-xs-9 text-right">
                                    <div class="medium">Ventilador</div>
                                    <div>Ventilador de teto</div>
                                </div>
                            </div>
                        </div>
                        <div class="panel-footer">
                            <span class="pull-left">
                                <form action="./ServletSala">
                                    <label for="vent1">Ligar / Desligar</label>        
                                    <input type="submit"  name="sala" value="vum" id="vent1">       
                                </form>
                            </span>  
                            <span class="pull-right"><i class="fa fa fa-adjust"></i></span>
                            <div class="clearfix"></div>
                        </div>
                        </a>
                    </div>
                </div>
            </div>

            <div class ="row">

                <%-- ##########################       DETECTOR DE MOVIMENTO --%>
                <div class="col-lg-4 col-md-4">
                    <div class="panel panel-green">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-3">
                                    <i class="fa fa-eye fa-5x"></i>
                                </div>
                                <div class="col-xs-9 text-right">
                                   
                                    <div class="medium"><%=session.getAttribute("movimento")%></div>

                                    <div>Detector de movimento</div>
                                </div>
                            </div>
                        </div>

                        <div class="panel-footer">
                            <span class="pull-left">
                                
                                <%-- <a href="movimento" id="movimento"> Atualizar </a> --%>
                                <form action="./ServletSala">
                                    <label for="movimento">Atualizar</label>        
                                    <input type="submit"  name="sala" value="movimento" id="movimento">       
                                </form>
                            </span>  
                            <span class="pull-right"><i class="fa fa-spinner"></i></span>
                            <div class="clearfix"></div>
                        </div>

                    </div>
                </div>



                <%-- ##########################       TEMPERATURA E UMIDADE --%>


                <div class="col-lg-4 col-md-4">
                    <div class="panel panel-green">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-3">
                                    <i class="fa fa-sun-o fa-5x"></i>
                                </div>
                                <div class="col-xs-9 text-right">
                                    <div class="medium"><%=session.getAttribute("tut")%></div>
                                    <div>Temperatura</div>
                                </div>
                            </div>
                        </div>
                        <div class="panel-footer">
                            <span class="pull-left">
                                <form id="ae" action="./ServletSala">
                                    <label for="tu">Atualizar</label>        
                                    <input type="submit"  name="sala" value="tu" id="tu">       
                                </form>                              
                            </span>  
                            <span class="pull-right"><i class="fa fa-spinner"></i></span>
                            <div class="clearfix"></div>
                        </div>
                        </a>
                    </div>
                </div>
                    
                    
                <div class="col-lg-4 col-md-4">
                    <div class="panel panel-green">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-3">
                                    <i class="fa fa-cloud fa-5x"></i>
                                </div>
                                <div class="col-xs-9 text-right">
                                    <div class="medium"><%=session.getAttribute("tuu")%></div>
                                    <div>Umidade</div>
                                </div>
                            </div>
                        </div>
                        <div class="panel-footer">
                            <span class="pull-left">
                                <form id="ae" action="./ServletSala">
                                    <label for="tu">Atualizar</label>        
                                    <input type="submit"  name="sala" value="tu" id="tu">       
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

        <%-- #################                                       MODAL --%>
        <div class="modal fade" id="basicModal" tabindex="-1" role="dialog" aria-labelledby="basicModal" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title" id="myModalLabel">Histórico</h4>
                        <%
                            sensores = sdao.visualizarUID(id);

                        %>
                    </div>
                    <div class="modal-body">
                        <%
                            for (Sensor s : sensores) {%>
                        <div>
                            <%s.getTipo().toString();%> <%s.getValor();%> <%s.getData();%>
                            <% System.out.println(s.toString());%>
                            <hr>
                        </div>
                        <%}%>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        <%-- <button type="button" class="btn btn-primary">Save changes</button> --%>
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


