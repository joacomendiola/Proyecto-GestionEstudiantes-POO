
-- Borrar base anterior y crear de nuevo
DROP DATABASE IF EXISTS universidad;
CREATE DATABASE universidad;
USE universidad;

-- TABLAS
CREATE TABLE estudiantes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    edad INT NOT NULL,
    carrera VARCHAR(100) NOT NULL
) ENGINE=InnoDB;

CREATE TABLE materias (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    creditos INT NOT NULL
) ENGINE=InnoDB;

CREATE TABLE inscripciones (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_estudiante INT NOT NULL,
    id_materia INT NOT NULL,
    fecha DATE NOT NULL,
    CONSTRAINT fk_insc_est FOREIGN KEY (id_estudiante) REFERENCES estudiantes(id) 
        ON UPDATE CASCADE ON DELETE CASCADE,   
    CONSTRAINT fk_insc_mat FOREIGN KEY (id_materia) REFERENCES materias(id)
        ON UPDATE CASCADE ON DELETE CASCADE,   
    CONSTRAINT uk_est_mat UNIQUE (id_estudiante, id_materia)
) ENGINE=InnoDB;

-- ÍNDICES ÚTILES
CREATE INDEX idx_insc_est ON inscripciones(id_estudiante);
CREATE INDEX idx_insc_mat ON inscripciones(id_materia);

-- DATOS DE PRUEBA
INSERT INTO estudiantes (nombre, edad, carrera) VALUES
('Juan Pérez', 21, 'Ingeniería en Sistemas'),
('María López', 22, 'Matemáticas'),
('Pedro Gómez', 20, 'Arquitectura');

INSERT INTO materias (nombre, creditos) VALUES
('Base de Datos', 6),
('Programación', 8),
('Álgebra', 5);

INSERT INTO inscripciones (id_estudiante, id_materia, fecha) VALUES
(1, 1, '2025-03-01'),
(1, 2, '2025-03-02'),
(2, 3, '2025-03-05');

-- VISTA PARA REPORTES
CREATE OR REPLACE VIEW v_inscripciones AS
SELECT i.id,
       e.nombre AS estudiante,
       m.nombre AS materia,
       i.fecha
FROM inscripciones i
JOIN estudiantes e ON e.id = i.id_estudiante
JOIN materias m ON m.id = i.id_materia;