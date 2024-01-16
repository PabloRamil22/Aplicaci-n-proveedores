package com.ceica.Modelos;

public class Proveedores {
    private int id;
    private String cif;
    private String nombre;
    private String direccion;
    private String localidad;
    private String provincia;

    public Proveedores() {
    }

    public Proveedores(String cif, String nombre) {
        this.cif = cif;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    @Override
    public String toString() {
        return "Proveedores{" +"\n"+
                "id=" + id +"\n"+
                ", cif='" + cif + '\'' +"\n"+
                ", nombre='" + nombre + '\'' +"\n"+
                ", direccion='" + direccion + '\'' +"\n"+
                ", localidad='" + localidad + '\'' +"\n"+
                ", provincia='" + provincia + '\'' +"\n"+
                '}';
    }
}
