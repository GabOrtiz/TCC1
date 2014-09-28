<%-- 
    Document   : inicial
    Created on : 26/08/2014, 01:21:00
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
        <link type="text/css" rel="stylesheet" href="css.css">
        
        <title>Domotica</title>
    </head>
    
    <body>
    
        <div class="container">
            <div class="row clearfix">
                <div class="col-md-12 column centralizado">
                    <ul class="nav nav-tabs">
                        <li class="active"><a href='inicial.jsp'>Inicial</a></li>
                        <li><a href='sala.jsp'>Sala</a></li>
                        <li><a href='dormum.jsp'>Dormitório 1</a></li>
                        <li><a href='dormdois.jsp'>Dormitório 2</a></li>
                        <li><a href='cozinha.jsp'>Cozinha</a></li>
                    </ul>
                </div>
            </div>
            
            
            
                <%  HttpSession sessao = request.getSession();
                    
                    if(sessao.getAttribute("id") == null){
                %>
                        <div class="row clearfix">
                <div class="col-md-4 column centralizado">

                    
                    
			<form role="form" action="./ServletLogin">
				<div class="form-group">
                                    <label for="email">Email</label>
                                    <input class="form-control" id="email" name="email" type="email" />
				</div>
                            
				<div class="form-group">
                                    <label for="senha">Senha</label>
                                    <input class="form-control" id="senha" name="senha" type="password" />
				</div>
                            
                                <button type="submit" class="btn btn-default">Login</button>
                                <br>
                                <br><br>
                                <a href="cadastro.jsp">ou cadastre-se</a>
			</form>
                            
                            
                </div>
            </div>
                        <%
                    }else{
                    
                    %>
                    Bem vindo(a) <%=sessao.getAttribute("nome")%>
                    
                    
                    
                    <br><br><br><br>
                <fieldset>
                            	
                            <form action="./ServletSair" >

                                

                                <input name="sair" type="submit" value="Sair"/>

                            </form>						
                        </fieldset>
                    <%
                    }
            
                %>
                
            
            
            
            
        </div>
    
        
        
        
        
        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
    </body>
    
    
    
</html>
