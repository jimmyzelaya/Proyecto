package com.grupo5.proyecto.Objects;

public class Users {
    public String uid;
    public String usuario;
    public String correo;
    public String rol;
    public String verificado;
    public String dni;
    public String nombres;
    public String apellidos;
    public String telefono;
    public String direccion;
    public String idGenero;
    public String genero;
    public String perfil;

    public Users() {
    }

    public Users(String uid, String usuario, String correo, String rol, String verificado, String dni, String nombres, String apellidos, String telefono, String direccion, String idGenero, String genero, String perfil) {
        this.uid = uid;
        this.usuario = usuario;
        this.correo = correo;
        this.rol = rol;
        this.verificado = verificado;
        this.dni = dni;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.direccion = direccion;
        this.idGenero = idGenero;
        this.genero = genero;
        this.perfil = perfil;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getVerificado() {
        return verificado;
    }

    public void setVerificado(String verificado) {
        this.verificado = verificado;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(String idGenero) {
        this.idGenero = idGenero;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }
}
