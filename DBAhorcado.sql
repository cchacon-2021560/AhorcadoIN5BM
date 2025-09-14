drop database if exists db_Ahorcado;
create database db_Ahorcado;
use db_Ahorcado;

create table Palabras(
	codigoPalabra int auto_increment,
	nombre varchar(20) not null,
	cualidadUno varchar(100) not null,
	cualidadDos varchar(100) not null,
	cualidadTres varchar(100) not null,
    primary key PK_Palabra(codigoPalabra)
);

delimiter $$
create procedure sp_AgregarPalabra(
	texto varchar(20),
	cualidadU varchar(100),
	cualidadD varchar(100),
	cualidadT varchar(100)
)
	begin
		insert into Palabras (nombre, cualidadUno, cualidadDos, cualidadTres)
			values(texto, cualidadU, cualidadD, cualidadT);
	end$$
delimiter ;

call sp_AgregarPalabra('elefante', 'si funca', 'Vive en manadas', 'Tiene trompa');
call sp_AgregarPalabra('javascript', 'si funca', 'Se ejecuta en navegador', 'Se usa para web');
call sp_AgregarPalabra('computadora', 'si funca', 'Procesa datos', 'Se conecta a internet');
call sp_AgregarPalabra('programacion', 'si funca', 'Resuelve problemas', 'Usa algoritmos');
call sp_AgregarPalabra('universidad', 'si funca', 'Clases y laboratorios', 'Grados académicos');


delimiter $$
	create procedure sp_ListarPalabras()
		begin
			select*from Palabras;
		end$$
delimiter ;
call sp_ListarPalabras();


