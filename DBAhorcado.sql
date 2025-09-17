drop database if exists db_Ahorcado;
create database db_Ahorcado;
use db_Ahorcado;
 
create table Palabras(
	codigo_palabra int auto_increment,
	nombre varchar(20) not null,
	cualidad_uno varchar(100) not null,
	cualidad_dos varchar(100) not null,
	cualidad_tres varchar(100) not null,
    primary key PK_Palabra(codigo_palabra)
);

create table Usuarios(
	codigo_usuario int auto_increment,
    correo varchar(100) not null,
    contraseña varchar(20) not null,
    primary key PK_Usuario(codigo_usuario)
);
 
delimiter $$
create procedure sp_AgregarPalabra(
	in texto varchar(20),
	in cualidadU varchar(100),
	in cualidadD varchar(100),
	in cualidadT varchar(100)
)
	begin
		insert into Palabras (nombre, cualidad_uno, cualidad_dos, cualidad_tres)
			values(texto, cualidadU, cualidadD, cualidadT);
	end$$
delimiter ;
 
call sp_AgregarPalabra('elefante', 'Mamífero terrestre', 'Vive en manadas', 'Tiene trompa');
call sp_AgregarPalabra('javascript', 'Lenguaje de programación', 'Se ejecuta en navegador', 'Se usa para web');
call sp_AgregarPalabra('computadora', 'Dispositivo electrónico', 'Procesa datos', 'Se conecta a internet');
call sp_AgregarPalabra('programacion', 'Proceso creativo', 'Resuelve problemas', 'Usa algoritmos');
call sp_AgregarPalabra('universidad', 'Centro de estudios superiores', 'Clases y laboratorios', 'Grados académicos');
call sp_AgregarPalabra('musica', 'Una forma de arte', 'Se compone de melodía y ritmo', 'Puede evocar emociones y recuerdos');
call sp_AgregarPalabra('viaje', 'Ir a otro lugar', 'Puede ser por placer o trabajo', 'Implica un desplazamiento');
call sp_AgregarPalabra('historia', 'El estudio del pasado', 'Se transmite a través de relatos y documentos', 'Nos ayuda a entender el presente');
call sp_AgregarPalabra('silencio', 'Ausencia de sonido', 'A veces es más revelador que las palabras', 'Puede ser incómodo o tranquilizador');
call sp_AgregarPalabra('espejo', 'Muestra una imagen', 'Se usa para ver el reflejo', 'Se encuentra en baños y dormitorios');
call sp_AgregarPalabra('sueño', 'Ocurre mientras duermes', 'A veces es difícil de recordar', 'Puede ser lúcido o confuso');
call sp_AgregarPalabra('sombra', 'Se forma por la luz', 'Siempre está en un día soleado', 'Puede ser larga o corta');
 

delimiter $$
	create procedure sp_ListarPalabras()
		begin
			select*from Palabras;
		end$$
delimiter ;
call sp_ListarPalabras();
 
delimiter $$
create procedure sp_ActualizarPalabra(
	in codP int,
    in texto varchar(20),
    in cualidadU varchar(100),
    in cualidadD varchar(100),
    in cualidadT varchar(100)
)
	begin
    update Palabras p
	set p.nombre = texto,
		p.cualidad_uno = cualidadU,
		p.cualidad_dos= cualidadD,
        p.cualidad_tres = cualidadT
	where codigo_palabra = codP;
    end$$
delimiter ;
call sp_ActualizarPalabra(1, "Elefantin", "Grande", "Vive en manadas", "Tiene trompa");
drop procedure sp_ActualizarPalabra;
 
delimiter $$
create procedure sp_EliminarPalabra( in codP int)
	begin
		delete from Palabras p 
        where p.codigo_palabra=codP;
    end$$
delimiter ;

-- --------------- USARIOS -------------------
delimiter $$
create procedure sp_AgregarUsuario(
	in p_correo varchar(100),
	in p_contrasena varchar(20)
)
	begin
		insert into Usuarios (correo, contraseña)
			values(p_correo, p_contrasena);
	end$$
delimiter ;

call sp_AgregarUsuario('pepito@correo.com', 'onetu');
call sp_AgregarUsuario('andres@correo.com', 'trifor');
call sp_AgregarUsuario('bartolome@correo.com', 'siseven');

delimiter $$
create procedure sp_ListarUsuarios()
	begin
		select * from Usuarios;
	end$$
delimiter ;

call sp_ListarUsuarios();

delimiter $$
create procedure sp_ActualizarUsuario(
	in p_codigo int,
	in p_correo varchar(100),
	in p_contrasena varchar(20)
)
	begin
		update Usuarios u
		set
			u.correo = p_correo,
			u.contraseña = p_contrasena
		where u.codigo_usuario = p_codigo;
	end$$
delimiter ;

call sp_ActualizarUsuario(1, 'nuevo_correo@correo.com', 'nuevaClave');

drop procedure sp_ActualizarUsuario;

delimiter $$
create procedure sp_EliminarUsuario(
	in p_codigo int
)
	begin
		delete from Usuarios
		where codigo_usuario = p_codigo;
	end$$
delimiter ;

call sp_EliminarUsuario(2);

 