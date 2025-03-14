
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
