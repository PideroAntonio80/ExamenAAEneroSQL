package com.sanvalero.examen_acceso_datos_enero.dao;

import com.sanvalero.examen_acceso_datos_enero.util.R;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Creado por @ author: Pedro Orós
 * el 27/01/2021
 */

public class BaseDAO {

    protected Connection conexion;

    public void conectar() throws ClassNotFoundException, SQLException, IOException {
        Properties configuration = new Properties();
        configuration.load(R.getProperties("database.properties"));
        String host = configuration.getProperty("host");
        String port = configuration.getProperty("port");
        String name = configuration.getProperty("name");
        String username = configuration.getProperty("username");
        String password = configuration.getProperty("password");
        String driver = configuration.getProperty("driver");

        Class.forName(driver);

        conexion = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + name + "?serverTimezone=UTC", username, password);
    }

    public void desconectar() throws SQLException {
        conexion.close();
        conexion = null;
    }
}
