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
    <div class="login-container">
        <h2>Iniciar Sesión</h2>
        <form action="Controlador" method="POST">
            <input type="hidden" name="accion" value="login">

            <div class="form-group">
                <label for="correo">Correo:</label>
                <input type="text" id="correo" name="correo" required placeholder="Ingresa tu correo">
            </div>
            <div class="form-group">
                <label for="password">Contraseña:</label>
                <input type="password" id="password" name="password" required placeholder="Ingresa tu contraseña">
            </div>
            <button type="submit" class="login-btn">Iniciar Sesión</button>
        </form>
    </div>
</body>
</html>
