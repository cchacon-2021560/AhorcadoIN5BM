// Variables que usaré
let palabrasOriginal = [];
let palabrasDisponibles = [];
let palabraSecreta = "";
let palabraMostrar = [];
let usadas = [];
let intentos = 0;
let maxIntentos = 4;
let tiempo = 90;
let intervalo = null;
let jugando = true;
let palabraActual = null;

async function cargarPalabrasDesdeDB() {
    try {
        const timestamp = new Date().getTime();
        const response = await fetch(`Controlador?accion=Listar&t=${timestamp}`);
        
        if (!response.ok) {
            throw new Error('Error: ' + response.status);
        }
        
        const palabrasDB = await response.json();
        
        if (!palabrasDB || palabrasDB.length === 0) {
            throw new Error('No se encontraron palabras en la base de datos');
        }
        
        palabrasOriginal = palabrasDB.map(palabra => ({
            texto: palabra.nombre,
            cualidades: [
                palabra.cualidadUno,
                palabra.cualidadDos,
                palabra.cualidadTres
            ]
        }));
        
        console.log('Palabras cargadas desde BD:', palabrasOriginal);
        return true;
    } catch (error) {
        console.error('Error al cargar palabras desde BD:', error);
        alert('Error al cargar las palabras desde la base de datos: ' + error.message);
        return false;
    }
}

//Método para corregir el problema null
document.addEventListener('DOMContentLoaded', async function () {
    // Cargar palabras desde la base de datos
    await cargarPalabrasDesdeDB();
    
    const imgEl = document.getElementById('ahorcado-img');
    const palabraEl = document.getElementById('palabraOculta');
    const letrasUsadasEl = document.getElementById('letrasUsadas');
    const intentosEl = document.getElementById('intentos');
    const mensajeEl = document.getElementById('mensaje');
    const inputLetra = document.getElementById('letra');
    const btnProbar = document.getElementById('btnProbar');
    const btnIniciar = document.getElementById('btnIniciar');
    const btnReiniciar = document.getElementById('btnReiniciar');
    const btnPausar = document.getElementById('btnPausar');
    const btnRendirse = document.getElementById('btnRendirse');

    // Eventos para los botones
    btnIniciar.addEventListener('click', iniciarJuego);
    btnReiniciar.addEventListener('click', reiniciarJuego);
    btnProbar.addEventListener('click', probarLetra);
    btnPausar.addEventListener('click', pausarJuego);
    btnRendirse.addEventListener('click', rendirse);
    inputLetra.addEventListener('keydown', function (e) {
        //Hacer funcionar el enter :)
        if (e.key === 'Enter')
            probarLetra();
    });

    function elegirYRemoverPalabra() {
        if (palabrasDisponibles.length === 0)
            return null;
        let idx = Math.floor(Math.random() * palabrasDisponibles.length);
        return palabrasDisponibles.splice(idx, 1)[0];
    }

    function mostrarPalabraEnPantalla() {
        palabraEl.textContent = palabraMostrar.join(' ');
    }

    function actualizarUI() {
        intentosEl.textContent = intentos;
        letrasUsadasEl.textContent = usadas.length ? usadas.join(', ') : ' ';
        imgEl.src = `img/ahorcado${Math.min(intentos, maxIntentos)}.png`;
    }

    function deshabilitarEntrada() {
        inputLetra.disabled = true;
        btnProbar.disabled = true;
    }
    function habilitarEntrada() {
        inputLetra.disabled = false;
        btnProbar.disabled = false;
        inputLetra.focus();
    }

    //contador
    function iniciarContador() {
        clearInterval(intervalo);
        intervalo = setInterval(() => {
            if (tiempo > 0) {
                tiempo--;

                let min = Math.floor(tiempo / 60);
                let sec = tiempo % 60;

                min = min < 10 ? "0" + min : min;
                sec = sec < 10 ? "0" + sec : sec;

                document.getElementById("contador").innerText = min + ":" + sec;
            } else {
                clearInterval(intervalo);
                mensajeEl.textContent = "El tiempo ha terminado. La palabra era: " + palabraSecreta;
                palabraMostrar = palabraSecreta.split('');
                mostrarPalabraEnPantalla();
                deshabilitarEntrada();

                setTimeout(configurarSiguientePalabra, 1200);
            }
        }, 1000);
    }

    function reiniciarContador() {
        clearInterval(intervalo);
        tiempo = 90;
        document.getElementById("contador").innerText = "01:30";
    }

    function configurarSiguientePalabra() {
        palabraActual = elegirYRemoverPalabra();
        if (!palabraActual) {
            mensajeEl.textContent = "Has completado todas las palabras! Felicidades.";
            deshabilitarEntrada();
            btnIniciar.disabled = false;
            return;
        }
        
        palabraSecreta = palabraActual.texto;
        mostrarCualidades();

        intentos = 0;
        usadas = [];
        palabraMostrar = [];
        for (let i = 0; i < palabraSecreta.length; i++) {
            palabraMostrar.push("_");
        }

        mensajeEl.textContent = "Falso suerte, comienza a adivinar letra por letra.";
        btnIniciar.disabled = true;
        actualizarUI();
        mostrarPalabraEnPantalla();
        habilitarEntrada();
        reiniciarContador();
        iniciarContador();
    }

    function mostrarCualidades() {
        const cualidadesEl = document.getElementById('cualidades');
        cualidadesEl.innerHTML = '<ul>' + palabraActual.cualidades.map(c => `<li>${c}</li>`).join('') + '</ul>';
    }

    function probarLetra() {
        if (!jugando) {
            mensajeEl.textContent = "El juego está en pausa.";
            return;
        }
        
        let letra = inputLetra.value.trim().toLowerCase();
        inputLetra.value = "";

        if (!letra || letra.length !== 1 || !letra.match(/^[a-zñáéíóúü]$/i)) {
            alert("Por favor ingresa solo una letra válida.");
            return;
        }

        if (usadas.includes(letra)) {
            mensajeEl.textContent = "Ya usaste la letra '" + letra + "'. Prueba otra.";
            return;
        }

        usadas.push(letra);
        let acierto = false;
        for (let i = 0; i < palabraSecreta.length; i++) {
            if (palabraSecreta[i].toLowerCase() === letra) {
                palabraMostrar[i] = palabraSecreta[i];
                acierto = true;
            }
        }

        if (!acierto) {
            intentos++;
            mensajeEl.textContent = "Letra incorrecta.";
        } else {
            mensajeEl.textContent = "¡Letra correcta!";
        }

        actualizarUI();
        mostrarPalabraEnPantalla();

        // Ganar
        if (!palabraMostrar.includes("_")) {
            mensajeEl.textContent = "¡Has ganado! Palabra: " + palabraSecreta;
            deshabilitarEntrada();
            // siguiente palabra:
            setTimeout(configurarSiguientePalabra, 1200);
            return;
        }

        // Perder
        if (intentos >= maxIntentos) {
            mensajeEl.textContent = "Has perdido. La palabra era: " + palabraSecreta;
            palabraMostrar = palabraSecreta.split('');
            mostrarPalabraEnPantalla();
            deshabilitarEntrada();
            // siguiente palabra:
            setTimeout(configurarSiguientePalabra, 1200);
            return;
        }

        inputLetra.focus();
    }

    function pausarJuego() {
        jugando = !jugando;
        if (jugando) {
            iniciarContador();
            mensajeEl.textContent = "Juego continuado.";
        } else {
            clearInterval(intervalo);
            mensajeEl.textContent = "Juego en pausa.";
        }
    }

    function rendirse() {
        clearInterval(intervalo);
        mensajeEl.textContent = "Te rendiste. La palabra era: " + palabraSecreta;
        palabraMostrar = palabraSecreta.split('');
        mostrarPalabraEnPantalla();
        deshabilitarEntrada();
        setTimeout(configurarSiguientePalabra, 1200);
    }

    async function iniciarJuego() {
        const cargaExitosa = await cargarPalabrasDesdeDB();
        if (!cargaExitosa) {
            mensajeEl.textContent = "No se pudo iniciar el juego. Error en la base de datos.";
            return; // No continuar si no se pudieron cargar las palabras
        }
        
        if (palabrasOriginal.length === 0) {
            mensajeEl.textContent = "No hay palabras disponibles en la base de datos.";
            return;
        }
        
        palabrasDisponibles = palabrasOriginal.slice();
        configurarSiguientePalabra();
    }

    async function reiniciarJuego() {
        const cargaExitosa = await cargarPalabrasDesdeDB();
        if (!cargaExitosa) {
            mensajeEl.textContent = "No se pudo reiniciar el juego. Error en la base de datos.";
            return; // No continuar si no se pudieron cargar las palabras
        }
        
        if (palabrasOriginal.length === 0) {
            mensajeEl.textContent = "No hay palabras disponibles en la base de datos.";
            return;
        }
        
        palabrasDisponibles = palabrasOriginal.slice();
        configurarSiguientePalabra();
        reiniciarContador();
        iniciarContador();
    }

    window.probarLetra = probarLetra;

    deshabilitarEntrada();
    document.getElementById('maxIntentos').textContent = maxIntentos;
    palabraEl.textContent = "Pulsa Inicio";
});