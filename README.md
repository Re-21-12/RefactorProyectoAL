# SCRIPT BD
-- create database proyecto
USE proyecto;


CREATE TABLE Alfabeto (
    Id INT AUTO_INCREMENT PRIMARY KEY, 
    Simbolo CHAR(10),
    Tipo VARCHAR(20),
    Descripcion VARCHAR(500)
);


/* -- Tipo de dato */
INSERT INTO Alfabeto (Simbolo, Tipo, Descripcion) 
VALUES 
    ('number', 'Tipo de dato', 'Tipo de dato número conjunto de los números reales'),
    ('string', 'Tipo de dato', 'Tipo de dato cadena de caracteres'),
    ('boolean', 'Tipo de dato', 'Tipo de dato booleano');

/* -- Espacio */
INSERT INTO Alfabeto (Simbolo, Tipo, Descripcion) 
VALUES 
    (' ', 'Espacio', 'Espacio en blanco');

/* -- Números */
INSERT INTO Alfabeto (Simbolo, Tipo, Descripcion) 
VALUES 
    ('0', 'Número', 'Cero'),
    ('1', 'Número', 'Uno'),
    ('2', 'Número', 'Dos'),
    ('3', 'Número', 'Tres'),
    ('4', 'Número', 'Cuatro'),
    ('5', 'Número', 'Cinco'),
    ('6', 'Número', 'Seis'),
    ('7', 'Número', 'Siete'),
    ('8', 'Número', 'Ocho'),
    ('9', 'Número', 'Nueve');

/* -- Inserción de letras minúsculas del abecedario en inglés con descripción actualizada */
INSERT INTO Alfabeto (Simbolo, Tipo, Descripcion) 
VALUES 
    ('a', 'letra', 'letra a'),
    ('b', 'letra', 'letra b'),
    ('c', 'letra', 'letra c'),
    ('d', 'letra', 'letra d'),
    ('e', 'letra', 'letra e'),
    ('f', 'letra', 'letra f'),
    ('g', 'letra', 'letra g'),
    ('h', 'letra', 'letra h'),
    ('i', 'letra', 'letra i'),
    ('j', 'letra', 'letra j'),
    ('k', 'letra', 'letra k'),
    ('l', 'letra', 'letra l'),
    ('m', 'letra', 'letra m'),
    ('n', 'letra', 'letra n'),
    ('o', 'letra', 'letra o'),
    ('p', 'letra', 'letra p'),
    ('q', 'letra', 'letra q'),
    ('r', 'letra', 'letra r'),
    ('s', 'letra', 'letra s'),
    ('t', 'letra', 'letra t'),
    ('u', 'letra', 'letra u'),
    ('v', 'letra', 'letra v'),
    ('w', 'letra', 'letra w'),
    ('x', 'letra', 'letra x'),
    ('y', 'letra', 'letra y'),
    ('z', 'letra', 'letra z');

/* -- Asignación */
INSERT INTO Alfabeto (Simbolo, Tipo, Descripcion) 
VALUES 
    ('=', 'Asignación', 'Operador de asignación');

/* -- Operadores */
INSERT INTO Alfabeto (Simbolo, Tipo, Descripcion) 
VALUES 
    ('+', 'Operador', 'Operador de adición'),
    ('-', 'Operador', 'Operador de sustracción'),
    ('*', 'Operador', 'Operador de multiplicación'),
    ('/', 'Operador', 'Operador de división');

/* -- Delimitador */
INSERT INTO Alfabeto (Simbolo, Tipo, Descripcion) 
VALUES 
    (';', 'Delimitador', 'Delimitador de instrucciones');

/* -- Estado inicial */
INSERT INTO Alfabeto (Simbolo, Tipo, Descripcion) 
VALUES 
    ('%', 'Estado inicial', 'Indicador de estado inicial');

/* -- Consulta */
SELECT Simbolo, Tipo, Descripcion 
FROM Alfabeto 
WHERE Simbolo LIKE '1';
