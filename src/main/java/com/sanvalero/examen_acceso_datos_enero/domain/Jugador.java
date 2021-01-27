package com.sanvalero.examen_acceso_datos_enero.domain;

/**
 * Creado por @ author: Pedro Orós
 * el 27/01/2021
 */
public class Jugador {

    private int id;
    private String nombre;
    private String apellidos;
    private String dorsal;
    private int id_equipo;

    public Jugador() {
    }

    public Jugador(String nombre, String apellidos) {
        this.nombre = nombre;
        this.apellidos = apellidos;
    }

    public Jugador(String nombre, String apellidos, int id_equipo) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.id_equipo = id_equipo;
    }

    public Jugador(int id, String nombre, String apellidos, String dorsal, int id_equipo) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dorsal = dorsal;
        this.id_equipo = id_equipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDorsal() {
        return dorsal;
    }

    public void setDorsal(String dorsal) {
        this.dorsal = dorsal;
    }

    public int getId_equipo() {
        return id_equipo;
    }

    public void setId_equipo(int id_equipo) {
        this.id_equipo = id_equipo;
    }
}
