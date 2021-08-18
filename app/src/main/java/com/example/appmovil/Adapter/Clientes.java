package com.example.appmovil.Adapter;

public class Clientes {
    private  int id;
    private String nombreCliente;
    private String informacionCliente;
    private String foto;

    public Clientes(int id, String nombreCliente, String informacionCliente, String foto) {
        this.id = id;
        this.nombreCliente = nombreCliente;
        this.informacionCliente = informacionCliente;
        this.foto = foto;
    }

    public Clientes(int id, String nombreCliente, String informacionCliente) {
        this.id = id;
        this.nombreCliente = nombreCliente;
        this.informacionCliente = informacionCliente;
    }

    public Clientes(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getInformacionCliente() {
        return informacionCliente;
    }

    public void setInformacionCliente(String informacionCliente) {
        this.informacionCliente = informacionCliente;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    @Override
    public String toString() {
        return "Clientes{" +
                "id=" + id +
                ", nombreCliente='" + nombreCliente + '\'' +
                ", informacionCliente='" + informacionCliente + '\'' +
                ", foto='" + foto + '\'' +
                '}';
    }
}
