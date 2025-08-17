package com.sg.proyectofinal.models;

public class AreaProtegida {
    private String nombre;
    private int visitas;
    private String descripcion;
    private String ubicacion;
    private String extension;
    private String clima;
    private String floraFauna;
    private String actividades;
    private int imagen;

    public AreaProtegida(String nombre, int visitas, String descripcion, String ubicacion,
                         String extension, String clima, String floraFauna, String actividades, int imagen) {
        this.nombre = nombre;
        this.visitas = visitas;
        this.descripcion = descripcion;
        this.ubicacion = ubicacion;
        this.extension = extension;
        this.clima = clima;
        this.floraFauna = floraFauna;
        this.actividades = actividades;
        this.imagen = imagen;
    }

    // Getters
    public String getNombre() { return nombre; }
    public int getVisitas() { return visitas; }
    public String getDescripcion() { return descripcion; }
    public String getUbicacion() { return ubicacion; }
    public String getExtension() { return extension; }
    public String getClima() { return clima; }
    public String getFloraFauna() { return floraFauna; }
    public String getActividades() { return actividades; }
    public int getImagen() { return imagen; }
}