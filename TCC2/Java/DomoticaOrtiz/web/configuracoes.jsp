<%--
    Document   : configuracoes
    Created on : 11/06/2015, 02:52:25
    Author     : Ortiz
--%>

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

        <% HttpSession sessao = request.getSession();


        %>

        <title>Domotica Ortiz</title>
    </head>

    <body>
        <div id="wrapper">
            <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
                <div class="navbar-header">

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
                        <li><a href='cozinha.jsp'>Cozinha</a></li>
                        <li><a href='registros.jsp'>Registros</a></li>
                    </ul>
                </div>
            </div>




            <div class="row clearfix">
                <div class="col-md-4 column centralizado">


                    <form  action="./ServletCadastro">

                        <div class="form-group">
                            <label class="control-label" for="nome">Endereço</label>
                            <input class="form-control" id="nome" name="end" type="endereco" placeholder="<%=sessao.getAttribute("endereco")%>" />
                        </div>
                        <div class="form-group">
                            <br><br>
                            <button id="submit" name="alt" value="end" class="btn btn-default">Alterar Endereço</button>
                        </div>
                    </form>

                        <hr>


                    <form  action="./ServletCadastro">

                        <div class="form-group">
                            <label class="control-label" for="nome">Email</label>
                            <input class="form-control" id="nome" name="em" type="email" placeholder="<%=sessao.getAttribute("email")%>" />
                        </div>
                        <div class="form-group">
                            <br><br>
                            <button id="submit" name="alt" value="em" class="btn btn-default">Alterar Email</button>
                        </div>
                    </form>
                        
                        
                        
                        <hr>
                        
                        
                        
                        
                         <form  action="./ServletCadastro">

                        <div class="form-group">
                            <label class="control-label" for="nome">Senha</label>
                            <input class="form-control" id="nome" name="senha" type="senha" placeholder="********" />
                        </div>
                        <div class="form-group">
                            <br><br>
                            <button id="submit" name="alt" value="senha" class="btn btn-default">Alterar Senha</button>
                        </div>
                    </form>



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
