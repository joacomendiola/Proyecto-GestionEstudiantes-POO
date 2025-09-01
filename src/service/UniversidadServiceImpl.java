package service;

import util.ConexionBD;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UniversidadServiceImpl implements UniversidadService {

    @Override
    public boolean inscribir(int idEstudiante, int idMateria, java.time.LocalDate fecha) {
        String checkEst = "SELECT 1 FROM estudiantes WHERE id=?";
        String checkMat = "SELECT 1 FROM materias WHERE id=?";
        String insert   = "INSERT INTO inscripciones(id_estudiante, id_materia, fecha) VALUES (?,?,?)";

        try (Connection cn = ConexionBD.getConnection()) {
            cn.setAutoCommit(false);
            try (PreparedStatement psEst = cn.prepareStatement(checkEst);
                 PreparedStatement psMat = cn.prepareStatement(checkMat);
                 PreparedStatement psIns = cn.prepareStatement(insert)) {

                // Valida estudiante
                psEst.setInt(1, idEstudiante);
                if (!psEst.executeQuery().next())
                    throw new SQLException("Estudiante no existe: " + idEstudiante);

                // Valida materia
                psMat.setInt(1, idMateria);
                if (!psMat.executeQuery().next())
                    throw new SQLException("Materia no existe: " + idMateria);

                // Inserta inscripción
                psIns.setInt(1, idEstudiante);
                psIns.setInt(2, idMateria);
                psIns.setDate(3, Date.valueOf(fecha));
                int n = psIns.executeUpdate();

                cn.commit();
                return n == 1;

            } catch (SQLException ex) {
                cn.rollback();
                // Duplicado por UNIQUE (id_estudiante, id_materia)
                if ("23000".equals(ex.getSQLState())) {
                    System.out.println("⚠ Ya estaba inscripto (duplicado).");
                    return false;
                }
                throw ex;
            } finally {
                cn.setAutoCommit(true);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error en inscripción", e);
        }
    }
}
