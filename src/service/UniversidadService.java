package service;

import java.time.LocalDate;

public interface UniversidadService {
    boolean inscribir(int idEstudiante, int idMateria, LocalDate fecha);
}
