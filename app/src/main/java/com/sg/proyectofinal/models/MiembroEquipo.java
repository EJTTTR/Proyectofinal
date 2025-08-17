package com.sg.proyectofinal.models;

import java.io.Serializable;

public class MiembroEquipo implements Serializable {
    private final String nombre;
    private final String cargo;
    private final String departamento;
    private final String descripcionCorta;
    private final String descripcionCompleta;
    private final int imagenPerfil;
    private final int imagenCompleta;

    public MiembroEquipo(String nombre, String cargo, String departamento,
                         String descripcionCorta, String descripcionCompleta,
                         int imagenPerfil, int imagenCompleta) {
        this.nombre = nombre;
        this.cargo = cargo;
        this.departamento = departamento;
        this.descripcionCorta = descripcionCorta;
        this.descripcionCompleta = descripcionCompleta;
        this.imagenPerfil = imagenPerfil;
        this.imagenCompleta = imagenCompleta;
    }

    // Getters
    public String getNombre() { return nombre; }
    public String getCargo() { return cargo; }
    public String getDepartamento() { return departamento; }
    public String getDescripcionCorta() { return descripcionCorta; }
    public String getDescripcionCompleta() { return descripcionCompleta; }
    public int getImagenPerfil() { return imagenPerfil; }
    public int getImagenCompleta() { return imagenCompleta; }
}