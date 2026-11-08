/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.yulisa.centrodeadopcion.entidades;
import java.io.Serializable;
/**
 *
 * @author YULI
 */
public abstract class Animal implements Serializable {
    // Encapsulamiento
    private String nombre;
    private int edad;
    private String fechaDeIngreso;
    private String raza;
    private boolean estadoDeAdopcion;
    private String descripcion;

    
    public Animal(String nombre, int edad, String fechaDeIngreso, String raza, boolean estadoDeAdopcion, String descripcion) {
        this.nombre = nombre;
        this.edad = edad;
        this.fechaDeIngreso = fechaDeIngreso;
        this.raza = raza;
        this.estadoDeAdopcion = estadoDeAdopcion;
        this.descripcion = descripcion;
    }

    private static final long serialVersionUID = 1L;
    private final String id = java.util.UUID.randomUUID().toString();

    public String getId() { return id; }
    
    public abstract void mostrarDescripcionCuidados();

    //  Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getFechaDeIngreso() {
        return fechaDeIngreso;
    }

    public void setFechaDeIngreso(String fechaDeIngreso) {
        this.fechaDeIngreso = fechaDeIngreso;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public boolean isEstadoDeAdopcion() {
        return estadoDeAdopcion;
    }

    public void setEstadoDeAdopcion(boolean estadoDeAdopcion) {
        this.estadoDeAdopcion = estadoDeAdopcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
