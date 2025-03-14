-- 

CREATE DATABASE BOOKSW;

USE BOOKSW;

--

DROP TABLE IF EXISTS editoriales,autores,almacenes,libros;

CREATE TABLE editoriales(
	id_editorial int primary key auto_increment,
    nombre_editorial varchar(40) not null
);

CREATE TABLE autores(
	id_autor int primary key auto_increment,
    nombre_autor varchar(40) not null,
    apellido_autor varchar(40) not null,
    -- fk
    id_editorial int,
    FOREIGN KEY (id_editorial) REFERENCES
    editoriales(id_editorial)
);

CREATE TABLE almacenes(
	id_almacen int primary key auto_increment,
    numero_almacen varchar(20) not null,
    espacio_almacen int not null
);


CREATE TABLE libros(
	id_libro int primary key auto_increment,
    nombre_libro varchar(40) not null,
    cantidad_libro int not null,
    -- fk
    id_almacen int,
    FOREIGN KEY (id_almacen) REFERENCES
    almacenes(id_almacen),
    -- fk
    id_autor int,
    FOREIGN KEY (id_autor) REFERENCES
    autores (id_autor)
);


-- insercion de datos
START TRANSACTION;

-- Insertamos una editorial
INSERT INTO editoriales (nombre_editorial) VALUES ('Penguin Random House');
SET @editorial_id = LAST_INSERT_ID();

-- Insertamos un autor
INSERT INTO autores (nombre_autor, apellido_autor, id_editorial) 
VALUES ('Gabriel', 'García Márquez', @editorial_id);
SET @autor_id = LAST_INSERT_ID();

-- Insertamos un almacén
INSERT INTO almacenes (numero_almacen, espacio_almacen) 
VALUES ('A1', 100);
SET @almacen_id = LAST_INSERT_ID();

-- Insertamos un libro con claves foráneas correctas
INSERT INTO libros (nombre_libro, cantidad_libro, id_almacen, id_autor) 
VALUES ('Cien años de soledad', 50, @almacen_id, @autor_id);

-- Si todo está correcto, confirmamos los cambios
COMMIT;


-- prueba de error probar rollback
START TRANSACTION;

-- Insertamos un libro con un id_autor inválido
INSERT INTO libros (nombre_libro, cantidad_libro, id_almacen, id_autor) 
VALUES ('El coronel no tiene quien le escriba', 30, 999, 999);

-- Esto fallará, así que hacemos rollback
ROLLBACK;


-- select

select * from autores;
select * from editoriales;
select * from almacenes;
select * from libros;


