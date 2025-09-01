USE universidad;

-- 1. Eliminar claves foráneas viejas
ALTER TABLE inscripciones DROP FOREIGN KEY fk_insc_est;
ALTER TABLE inscripciones DROP FOREIGN KEY fk_insc_mat;

-- 2. Crear claves foráneas nuevas con CASCADE
ALTER TABLE inscripciones
  ADD CONSTRAINT fk_insc_est
    FOREIGN KEY (id_estudiante) REFERENCES estudiantes(id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  ADD CONSTRAINT fk_insc_mat
    FOREIGN KEY (id_materia) REFERENCES materias(id)
    ON DELETE CASCADE
    ON UPDATE CASCADE;

-- 3. Verificar 
SHOW CREATE TABLE inscripciones;