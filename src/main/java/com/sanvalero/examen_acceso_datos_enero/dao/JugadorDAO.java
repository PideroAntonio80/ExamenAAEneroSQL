package com.sanvalero.examen_acceso_datos_enero.dao;

import com.sanvalero.examen_acceso_datos_enero.domain.Jugador;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Creado por @ author: Pedro Orós
 * el 27/01/2021
 */
public class JugadorDAO extends BaseDAO {

    public void guardar(Jugador jugador) throws SQLException {
        String sql = "INSERT INTO jugadores (nombre, apellidos, dorsal, equipo) VALUES (?, ?, null, ?)";

        PreparedStatement sentencia = null;

        sentencia = conexion.prepareStatement(sql);

        sentencia.setString(1,jugador.getNombre());
        sentencia.setString(2, jugador.getApellidos());
        sentencia.setInt(3, jugador.getId_equipo());

        sentencia.executeUpdate();
    }

    public void modificar(int idEquipo, Jugador jugador) throws SQLException {
        String sql = "UPDATE jugadores SET id_equipo = ? WHERE nombre = ? AND apellidos = ?";
        PreparedStatement sentencia = null;

        sentencia = conexion.prepareStatement(sql);

        sentencia.setInt(1, idEquipo);
        sentencia.setString(2, jugador.getNombre());
        sentencia.setString(2, jugador.getApellidos());

        sentencia.executeUpdate();

    }
}
