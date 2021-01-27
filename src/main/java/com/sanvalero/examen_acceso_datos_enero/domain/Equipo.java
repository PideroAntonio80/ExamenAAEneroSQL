package com.sanvalero.examen_acceso_datos_enero.domain;

/**
 * Creado por @ author: Pedro Orós
 * el 27/01/2021
 */
public class Equipo {
    private int id;
    private String nombre;
    private String patrocinador;
    private String categoria;

    public Equipo() {
    }

    public Equipo(String nombre, String patrocinador) {
        this.nombre = nombre;
        this.patrocinador = patrocinador;
    }

    public Equipo(String nombre, String patrocinador, String categoria) {
        this.nombre = nombre;
        this.patrocinador = patrocinador;
        this.categoria = categoria;
    }

    public Equipo(int id, String nombre, String patrocinador, String categoria) {
        this.id = id;
        this.nombre = nombre;
        this.patrocinador = patrocinador;
        this.categoria = categoria;
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

    public String getPatrocinador() {
        return patrocinador;
    }

    public void setPatrocinador(String patrocinador) {
        this.patrocinador = patrocinador;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
