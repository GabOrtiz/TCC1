<%-- 
    Document   : dormum
    Created on : 26/08/2014, 02:46:32
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
                        <li><a href='inicial.jsp'>Inicial</a></li>
                        <li><a href='sala.jsp'>Sala</a></li>
                        <li class="active"><a href='dormum.jsp'>Dormitório 1</a></li>
                        <li><a href='dormdois.jsp'>Dormitório 2</a></li>
                        <li><a href='cozinha.jsp'>Cozinha</a></li>
                    </ul>
                </div>
            </div>
            <div class="row clearfix">
                <div class="col-md-12 column centralizado">
                    <button type="button" class="btn btn-primary btn-lg">Lâmpada 1</button> 
                    <hr>
                    <button type="button" class="btn btn-primary btn-lg">Lâmpada 2</button> 
                    <hr>
                    <button type="button" class="btn btn-primary btn-lg">Condicionador de ar</button>
                </div>
            </div>
        </div>
    
        
        
        
        
        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
    </body>


</html>
