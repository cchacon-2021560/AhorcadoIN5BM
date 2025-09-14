<%-- 
    Document   : index
    Created on : 2/09/2025, 13:22:13
    Author     : USER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="utf-8" />
        <title>Ahorcado|IN5BM</title>
        <link rel="stylesheet" href="./Styles/index.css" />
        <script src="./JS/Index.js"></script>
    </head>
    <body>
        <h1>Ahorcado</h1>
        <p>Tiempo restante: <span id="contador">01:30</span></p>

        <div id="contenedorJuego">
            <div id="imagen">
                <img id="ahorcado-img" src="./img/ahorcado0.png" alt="Ahorcado">
            </div>

            <div id="informaichon">
                <div>Intentos: <span id="intentos">0</span>/<span id="maxIntentos">4</span></div>
                <div>Letras usadas: <span id="letrasUsadas"></span></div>
                <div>Pistas:<span id="cualidades"></span></div>
            </div>

            <div id="palabraOculta">Pulsa Inicio</div>

            <div id="inputLetra">
                <input id="letra" type="text" maxlength="1" placeholder="A" disabled>
                <button id="btnProbar" disabled>Probar</button>
            </div>

            <p id="mensaje" class="msg"></p>

            <div id="botones">
                <button id="btnIniciar">Inicio</button>
                <button id="btnReiniciar">Reiniciar</button>
                <button id ="btnPausar">Pausar/Continuar</button>
                <button id ="btnRendirse">Rendirse</button>
            </div>
        </div>
    </body>
</html>


