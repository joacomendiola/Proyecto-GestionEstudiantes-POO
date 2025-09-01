package dao;

import util.ConexionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReporteRepository {
    public List<InscripcionVista> listarInscripciones() {
        String sql = """
            SELECT e.nombre AS estudiante, m.nombre AS materia, i.fecha
            FROM inscripciones i
            JOIN estudiantes e ON e.id = i.id_estudiante
            JOIN materias    m ON m.id = i.id_materia
            ORDER BY e.nombre, m.nombre
        """;
        List<InscripcionVista> out = new ArrayList<>();
        try (Connection cn = ConexionBD.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                out.add(new InscripcionVista(
                        rs.getString("estudiante"),
                        rs.getString("materia"),
                        rs.getDate("fecha").toLocalDate()
                ));
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Error listando inscripciones", ex);
        }
        return out;
    }
}
