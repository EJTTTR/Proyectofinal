package com.sg.proyectofinal.models;

public class Servicio {
    private String nombre;
    private String descripcion;
    private int icono; // Ahora será un recurso drawable local

    public Servicio(String nombre, String descripcion, int icono) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.icono = icono;
    }

    // Getters
    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
    public int getIcono() { return icono; }
}