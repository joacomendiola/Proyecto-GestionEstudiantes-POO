package dao;

import model.Estudiante;

import java.util.List;
import java.util.Optional;

public interface EstudianteDAO {
    int crear(Estudiante e);
    List<Estudiante> listar();
    Optional<Estudiante> porId(int id);
    boolean actualizar(Estudiante e);
    boolean eliminar(int id);
}
