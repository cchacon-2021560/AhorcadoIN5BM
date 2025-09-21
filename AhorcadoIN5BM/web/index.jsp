<%-- 
    Document   : index (login)
    Created on : 13/09/2025, 23:40:25
    Author     : USER
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Iniciar Sesión</title>
        <link rel="stylesheet" href="./Styles/login.css">
    </head>
    <body>
        <form class="form" action="Controlador" method="POST">
            <input type="hidden" name="accion" value="login">
            <div class="form-title"><span>Inicia Sesión</span></div>
            <div class="title-2"><span>AHORCADO</span></div>
            <div class="input-container">
                <input id="correo" placeholder="Email" type="email" class="input-mail" name="txtCorreo" required />
                <span> </span>
            </div>

            <section class="bg-stars">
                <span class="star"></span>
                <span class="star"></span>
                <span class="star"></span>
                <span class="star"></span>
            </section>

            <div class="input-container">
                <input id="password" placeholder="Password" type="password" class="input-pwd" name="txtContrasena" required />
            </div>
            
            <button id="login-btn" class="submit" type="submit" name="btnIngresar" value="Ingresar">
                <span class="sign-text">INGRESAR</span>
            </button>


        </form>
    </body>
</html>