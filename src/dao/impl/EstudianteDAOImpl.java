package dao.impl;

import dao.EstudianteDAO;
import model.Estudiante;
import util.ConexionBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EstudianteDAOImpl implements EstudianteDAO {

    @Override
    public int crear(Estudiante e) {
        String sql = "INSERT INTO estudiantes(nombre, edad, carrera) VALUES (?,?,?)";
        try (Connection cn = ConexionBD.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, e.getNombre());
            ps.setInt(2, e.getEdad());
            ps.setString(3, e.getCarrera());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                return rs.next() ? rs.getInt(1) : -1;
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Error creando estudiante", ex);
        }
    }

    @Override
    public List<Estudiante> listar() {
        String sql = "SELECT id, nombre, edad, carrera FROM estudiantes ORDER BY id";
        List<Estudiante> out = new ArrayList<>();
        try (Connection cn = ConexionBD.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                out.add(new Estudiante(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getInt("edad"),
                        rs.getString("carrera")
                ));
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Error listando estudiantes", ex);
        }
        return out;
    }

    @Override
    public Optional<Estudiante> porId(int id) {
        String sql = "SELECT id, nombre, edad, carrera FROM estudiantes WHERE id=?";
        try (Connection cn = ConexionBD.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new Estudiante(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getInt("edad"),
                            rs.getString("carrera")
                    ));
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Error buscando estudiante", ex);
        }
        return Optional.empty();
    }

    @Override
    public boolean actualizar(Estudiante e) {
        String sql = "UPDATE estudiantes SET nombre=?, edad=?, carrera=? WHERE id=?";
        try (Connection cn = ConexionBD.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, e.getNombre());
            ps.setInt(2, e.getEdad());
            ps.setString(3, e.getCarrera());
            ps.setInt(4, e.getId());
            return ps.executeUpdate() == 1;
        } catch (SQLException ex) {
            throw new RuntimeException("Error actualizando estudiante", ex);
        }
    }

    @Override
    public boolean eliminar(int id) {
        String sql = "DELETE FROM estudiantes WHERE id=?";
        try (Connection cn = ConexionBD.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() == 1;
        } catch (SQLException ex) {
            throw new RuntimeException("Error eliminando estudiante", ex);
        }
    }
}
