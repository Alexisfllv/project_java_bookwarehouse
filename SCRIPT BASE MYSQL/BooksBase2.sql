USE BOOKSW2;

-- Tabla de Categorías
CREATE TABLE categorias (
    id_categoria INT PRIMARY KEY AUTO_INCREMENT,
    nombre_categoria VARCHAR(40) NOT NULL
);

-- Tabla de Editoriales
CREATE TABLE editoriales (
    id_editorial INT PRIMARY KEY AUTO_INCREMENT,
    nombre_editorial VARCHAR(40) NOT NULL
);

-- Tabla de Almacenes
CREATE TABLE almacenes (
    id_almacen INT PRIMARY KEY AUTO_INCREMENT,
    nombre_almacen VARCHAR(40) NOT NULL,
    capacidad INT NOT NULL
);

-- Tabla de Autores
CREATE TABLE autores (
    id_autor INT PRIMARY KEY AUTO_INCREMENT,
    nombre_autor VARCHAR(40) NOT NULL,
    apellido_autor VARCHAR(40) NOT NULL
);

-- Tabla de Libros
CREATE TABLE libros (
    id_libro INT PRIMARY KEY AUTO_INCREMENT,
    codigo_libro VARCHAR(20) UNIQUE NOT NULL,
    titulo VARCHAR(100) NOT NULL,
    peso DECIMAL(5,2) NOT NULL,
    numero_paginas INT NOT NULL,
    fecha_publicacion DATE NOT NULL,
    precio DECIMAL(10,2) NOT NULL,
    id_editorial INT,
    id_almacen INT,
    id_categoria INT,
    FOREIGN KEY (id_editorial) REFERENCES editoriales(id_editorial),
    FOREIGN KEY (id_almacen) REFERENCES almacenes(id_almacen),
    FOREIGN KEY (id_categoria) REFERENCES categorias(id_categoria)
);

-- Relación muchos a muchos: Un libro puede tener varios autores
CREATE TABLE libro_autor (
    id_libro INT,
    id_autor INT,
    PRIMARY KEY (id_libro, id_autor),
    FOREIGN KEY (id_libro) REFERENCES libros(id_libro),
    FOREIGN KEY (id_autor) REFERENCES autores(id_autor)
);

-- Tabla de Registros (para auditoría)
CREATE TABLE registros (
    id_registro INT PRIMARY KEY AUTO_INCREMENT,
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    codigo_libro VARCHAR(20),
    FOREIGN KEY (codigo_libro) REFERENCES libros(codigo_libro)
);

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


-- INSERCION DE DATOS 

-- Insertando Categorías
INSERT INTO categorias (nombre_categoria) VALUES 
('Ciencia Ficción'), 
('Fantasía'), 
('Historia');

-- Insertando Editoriales
INSERT INTO editoriales (nombre_editorial) VALUES 
('Penguin Books'), 
('HarperCollins'), 
('Planeta');

-- Insertando Almacenes
INSERT INTO almacenes (nombre_almacen, capacidad) VALUES 
('Almacén Central', 500), 
('Depósito Norte', 300), 
('Bodega Sur', 200);

-- Insertando Autores
INSERT INTO autores (nombre_autor, apellido_autor) VALUES 
('Isaac', 'Asimov'), 
('J.R.R.', 'Tolkien'), 
('Yuval', 'Harari');

-- SELECT
select * from categorias;
select * from editoriales;
select * from almacenes;
select * from autores;


-- TRANSACCION CON DATOS INCIALES

START TRANSACTION;

-- Crear una nueva editorial
INSERT INTO editoriales (nombre_editorial) VALUES ('Nueva Editorial');
SET @id_editorial = LAST_INSERT_ID();

-- Crear un nuevo almacén
INSERT INTO almacenes (nombre_almacen, capacidad) VALUES ('Almacén Oeste', 250);
SET @id_almacen = LAST_INSERT_ID();

-- Crear una nueva categoría
INSERT INTO categorias (nombre_categoria) VALUES ('Terror');
SET @id_categoria = LAST_INSERT_ID();

-- Crear un nuevo autor
INSERT INTO autores (nombre_autor, apellido_autor) VALUES ('Stephen', 'King');
SET @id_autor = LAST_INSERT_ID();

-- Crear un nuevo libro asociado a lo que se acaba de crear
INSERT INTO libros (codigo_libro, titulo, peso, numero_paginas, fecha_publicacion, precio, id_editorial, id_almacen, id_categoria)
VALUES ('LIB003', 'El Resplandor', 0.8, 447, '1977-01-28', 55.90, @id_editorial, @id_almacen, @id_categoria);
SET @id_libro = LAST_INSERT_ID();

-- Relacionar el libro con el autor
INSERT INTO libro_autor (id_libro, id_autor) VALUES (@id_libro, @id_autor);

COMMIT;

select * from libros;

-- PROBAR AUDITORIA
select * from registros;


-- probar con 2 autores

START TRANSACTION;

-- Crear una nueva editorial
INSERT INTO editoriales (nombre_editorial) VALUES ('Editorial Dual');
SET @id_editorial = LAST_INSERT_ID();

-- Crear un nuevo almacén
INSERT INTO almacenes (nombre_almacen, capacidad) VALUES ('Almacén Central', 300);
SET @id_almacen = LAST_INSERT_ID();

-- Crear una nueva categoría
INSERT INTO categorias (nombre_categoria) VALUES ('Ciencia Ficción');
SET @id_categoria = LAST_INSERT_ID();

-- Crear el primer autor
INSERT INTO autores (nombre_autor, apellido_autor) VALUES ('Maybe', 'Paks');
SET @id_autor1 = LAST_INSERT_ID();

-- Crear el segundo autor
INSERT INTO autores (nombre_autor, apellido_autor) VALUES ('Arthur', 'Clarke');
SET @id_autor2 = LAST_INSERT_ID();

-- Crear un nuevo libro asociado a la editorial, almacén y categoría
INSERT INTO libros (codigo_libro, titulo, peso, numero_paginas, fecha_publicacion, precio, id_editorial, id_almacen, id_categoria)
VALUES ('LIB004', 'El Futuro de la Humanidad', 1.2, 520, '1985-06-15', 70.50, @id_editorial, @id_almacen, @id_categoria);
SET @id_libro = LAST_INSERT_ID();

-- Relacionar el libro con ambos autores
INSERT INTO libro_autor (id_libro, id_autor) VALUES (@id_libro, @id_autor1);
INSERT INTO libro_autor (id_libro, id_autor) VALUES (@id_libro, @id_autor2);

COMMIT;

select * from libros;
select * from autores;
select * from libro_autor;

-- ver los autores en los libros
SELECT 
    l.id_libro,
    l.titulo,
    a.id_autor,
    a.nombre_autor,
    a.apellido_autor
FROM libros as l
join libro_autor as la
on l.id_libro = la.id_libro
join autores  as a 
on la.id_autor = a.id_autor;

-- usando concat para los autores
SELECT 
    l.id_libro,
    l.titulo,
	l.peso,
    l.numero_paginas,
    l.fecha_publicacion,
    precio,
    GROUP_CONCAT(CONCAT(a.nombre_autor, ' ', a.apellido_autor) SEPARATOR ', ') AS autores
FROM libros l
JOIN libro_autor la ON l.id_libro = la.id_libro
JOIN autores a ON la.id_autor = a.id_autor
GROUP BY l.id_libro;

-- vista global de un libro 
SELECT 
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

select * from almacenes;

select * from registros;
-- TRANSACCION CON DATOS PRINCIPALES DE LIBRO
START TRANSACTION;

select * from almacenes;
select * from editoriales;
select * from categorias;
select * from autores;
select * from libros;

-- Insertar el libro usando datos existentes
INSERT INTO libros (codigo_libro, titulo, peso, numero_paginas, fecha_publicacion, precio, id_editorial, id_almacen, id_categoria)
VALUES ('SPR-001','Spring Avanzado', 1.3, 600, '2024-03-14', 180.00, 
        (SELECT id_editorial FROM editoriales WHERE nombre_editorial = 'Planeta'),
        (SELECT id_almacen FROM almacenes WHERE nombre_almacen = 'Bodega Sur'),
        (SELECT id_categoria FROM categorias WHERE nombre_categoria = 'Terror'));

-- Obtener el ID del libro recién insertado
SET @id_libro = LAST_INSERT_ID();

-- Relacionar el libro con sus autores existentes
INSERT INTO libro_autor (id_libro, id_autor)
VALUES 
    (@id_libro, (SELECT id_autor FROM autores WHERE nombre_autor = 'Maybe' AND apellido_autor = 'Paks')),
    (@id_libro, (SELECT id_autor FROM autores WHERE nombre_autor = 'Arthur' AND apellido_autor = 'Clarke'));


COMMIT;


select * from registros;


-- crear vistas para vista global

CREATE VIEW vista_libros_global AS
SELECT 
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


-- vista de autores

CREATE VIEW vista_autores as
SELECT 
    l.id_libro,
    l.titulo,
	l.peso,
    l.numero_paginas,
    l.fecha_publicacion,
    precio,
    GROUP_CONCAT(CONCAT(a.nombre_autor, ' ', a.apellido_autor) SEPARATOR ', ') AS autores
FROM libros l
JOIN libro_autor la ON l.id_libro = la.id_libro
JOIN autores a ON la.id_autor = a.id_autor
GROUP BY l.id_libro;

select * from vista_libros_global;

select * from vista_autores;

-- vista de libros en base al autor
create view vista_autor_libro as 
SELECT 
    l.id_libro,
    l.titulo,
    a.id_autor,
    a.nombre_autor,
    a.apellido_autor
FROM libros as l
join libro_autor as la
on l.id_libro = la.id_libro
join autores  as a 
on la.id_autor = a.id_autor;

select * from vista_autores;

select * from libros;

-- 


