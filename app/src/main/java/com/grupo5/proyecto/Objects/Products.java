package com.grupo5.proyecto.Objects;

public class Products {
    private String pid;
    private String nombre;
    private String descripcion;
    private int idCategoria;
    private String categoria;
    private double precio;
    private String imagenProducto;
    private int cantidad;

    public Products() {
    }

    public Products(String pid, String nombre, String descripcion, int idCategoria, String categoria, double precio, String imagenProducto, int cantidad) {
        this.pid = pid;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.idCategoria = idCategoria;
        this.categoria = categoria;
        this.precio = precio;
        this.imagenProducto = imagenProducto;
        this.cantidad = cantidad;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getImagenProducto() {
        return imagenProducto;
    }

    public void setImagenProducto(String imagenProducto) {
        this.imagenProducto = imagenProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
