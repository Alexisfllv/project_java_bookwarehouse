
drop database if exists bookswjunior;
create database bookswjunior;

-- Trigger para guardar en "registros" cada vez que se inserta un libro
DELIMITER //
CREATE TRIGGER after_insert_libro
AFTER INSERT ON libros
FOR EACH ROW
BEGIN
    INSERT INTO registros (codigo_libro) VALUES (NEW.codigo_libro);
END;
//
DELIMITER ;

use bookswjunior;

describe registros;

select * from registros;

select * from libros;

ALTER TABLE registros 
MODIFY COLUMN fecha_registro TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP;


--

-- Insertar Categorías
INSERT INTO categorias (nombre_categoria) VALUES ('Ciencia Ficción');
INSERT INTO categorias (nombre_categoria) VALUES ('Fantasía');
INSERT INTO categorias (nombre_categoria) VALUES ('Terror');

-- Verificar Categorías
SELECT * FROM categorias;

-- Insertar Editoriales
INSERT INTO editoriales (nombre_editorial) VALUES ('Planeta');
INSERT INTO editoriales (nombre_editorial) VALUES ('Penguin Random House');

-- Verificar Editoriales
SELECT * FROM editoriales;

-- Insertar Almacenes
INSERT INTO almacenes (nombre_almacen, capacidad) VALUES ('Almacén Central', 500);
INSERT INTO almacenes (nombre_almacen, capacidad) VALUES ('Almacén Secundario', 300);

-- Verificar Almacenes
SELECT * FROM almacenes;

-- Insertar Autores
INSERT INTO autores (nombre_autor, apellido_autor) VALUES ('Isaac', 'Asimov');
INSERT INTO autores (nombre_autor, apellido_autor) VALUES ('J.K.', 'Rowling');
INSERT INTO autores (nombre_autor, apellido_autor) VALUES ('Stephen', 'King');

-- Verificar Autores
SELECT * FROM autores;

select * from libros;
select * from registros;


-- 
CREATE VIEW vista_libros_global AS
SELECT 
	l.id_libro,
    l.titulo AS Libro,
    l.peso AS Peso,
    l.numero_paginas AS "Número de Páginas",
    l.fecha_publicacion AS "Fecha de Publicación",
    l.precio AS Precio,
    e.nombre_editorial AS Editorial,
    a.nombre_almacen AS Almacen,
    c.nombre_categoria AS Categoria,
    GROUP_CONCAT(CONCAT(au.nombre_autor, ' ', au.apellido_autor) SEPARATOR ', ') AS Autores
FROM libros l
JOIN editoriales e ON l.id_editorial = e.id_editorial
JOIN almacenes a ON l.id_almacen = a.id_almacen
JOIN categorias c ON l.id_categoria = c.id_categoria
JOIN libro_autor la ON l.id_libro = la.id_libro
JOIN autores au ON la.id_autor = au.id_autor
GROUP BY l.id_libro;


select * from vista_libros_global;

select * from registros;

select * from libros;

CREATE VIEW vista_libros_global2 AS
SELECT 
    l.id_libro AS idLibro,
    l.titulo AS Libro,
    l.peso AS Peso,
    l.numero_paginas AS "Número de Páginas",
    l.fecha_publicacion AS "Fecha de Publicación",
    l.precio AS Precio,
    e.nombre_editorial AS Editorial,
    a.nombre_almacen AS Almacen,
    c.nombre_categoria AS Categoria,
    JSON_ARRAYAGG(CONCAT(au.nombre_autor, ' ', au.apellido_autor)) AS Autores
FROM libros l
JOIN editoriales e ON l.id_editorial = e.id_editorial
JOIN almacenes a ON l.id_almacen = a.id_almacen
JOIN categorias c ON l.id_categoria = c.id_categoria
JOIN libro_autor la ON l.id_libro = la.id_libro
JOIN autores au ON la.id_autor = au.id_autor
GROUP BY l.id_libro;


select * from vista_libros_global2;
