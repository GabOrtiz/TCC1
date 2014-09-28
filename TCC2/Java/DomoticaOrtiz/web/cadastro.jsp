<%-- 
    Document   : cadastro
    Created on : 28/09/2014, 12:22:12
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
            
            <div class="row clearfix">
                <div class="col-md-4 column centralizado">


                    <form  action="./ServletCadastro">
                            <div class="form-group">
                                <label class="control-label" for="nome">nome</label>
                                <input class="form-control" id="nome" name="nome" type="nome" />
                            </div>
                            <div class="form-group">
                                <label class="control-label" for="email">email</label>
                                <input class="form-control" id="email" name="email" type="email" />
                            </div>
                            <div class="form-group">
                                <label class="control-label" for="senha">senha</label>
                                <input class="form-control" id="senha" name="senha" type="password" />
                            </div>
                            <div class="form-group">
                                <br><br>
                                <button id="submit" name="submit" class="btn btn-default">Cadastrar</button>
                            </div>
                    </form>


                </div>
            </div>
        </div>


        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
        <!-- Include all compiled plugins (below), or include individual files as needed -->
        <script src="js/bootstrap.min.js"></script>
    </body>
</html>
