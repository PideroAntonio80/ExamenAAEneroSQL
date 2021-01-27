package com.sanvalero.examen_acceso_datos_enero.dao;

import com.sanvalero.examen_acceso_datos_enero.domain.Equipo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Creado por @ author: Pedro Orós
 * el 27/01/2021
 */

public class EquipoDAO extends BaseDAO {

    public void guardar(Equipo equipo) throws SQLException {
        String sql = "INSERT INTO equipos (nombre, patrocinador, categoria) VALUES (?, ?, ?)";
        PreparedStatement sentencia = null;

        sentencia = conexion.prepareStatement(sql);

        sentencia.setString(1,equipo.getNombre());
        sentencia.setString(2, equipo.getPatrocinador());
        sentencia.setString(3, equipo.getCategoria());

        sentencia.executeUpdate();
    }

    public void eliminar(Equipo equipo) throws SQLException {
        String sql = "DELETE FROM equipos WHERE nombre = ? AND patrocinador = ?";
        PreparedStatement sentencia = null;

        sentencia = conexion.prepareStatement(sql);

        sentencia.setString(1,equipo.getNombre());
        sentencia.setString(2, equipo.getPatrocinador());

        sentencia.executeUpdate();

    }

    public List<Equipo> listar() throws SQLException {
        String sql = "SELECT * FROM equipos";
        PreparedStatement sentencia = null;

        List<Equipo> lista = new ArrayList<>();

        sentencia = conexion.prepareStatement(sql);
        ResultSet resultado = sentencia.executeQuery();

        while (resultado.next()) {
            Equipo equipo = new Equipo(
                    resultado.getInt(1),
                    resultado.getString(2),
                    resultado.getString(3),
                    resultado.getString(3)
            );
            lista.add(equipo);

        }

        return  lista;
    }

    public int listarEquiposId(Equipo equipo) throws SQLException {
        int id = 0;
        String sql = "SELECT id FROM equipos WHERE nombre = ? AND patrocinador = ? AND categoria = ?";
        PreparedStatement sentencia = null;

        sentencia = conexion.prepareStatement(sql);
        sentencia.setString(1, equipo.getNombre());
        sentencia.setString(2,equipo.getPatrocinador());
        sentencia.setString(3, equipo.getCategoria());
        ResultSet resultado = sentencia.executeQuery();

        while (resultado.next()) {
            id = resultado.getInt(1);
        }
        return id;
    }
}
