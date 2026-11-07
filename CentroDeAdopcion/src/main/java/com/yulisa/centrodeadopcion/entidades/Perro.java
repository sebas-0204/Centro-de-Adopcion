/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.yulisa.centrodeadopcion.entidades;

/**
 *
 * @author YULI
 */

public class Perro extends Animal {
    private boolean entrenado;
    private boolean discapacitado;
    private boolean protector;

  
    public Perro(String nombre, int edad, String fechaDeIngreso, String raza, boolean estadoDeAdopcion, String descripcion) {
        super(nombre, edad, fechaDeIngreso, raza, estadoDeAdopcion, descripcion);
        this.entrenado = true;
        this.discapacitado = true;
        this.protector = true;
    }
    
    @Override
    public void mostrarDescripcionCuidados() {
        System.out.println("Cuidados para el perro " + getNombre() + ": Requiere paseos diarios y atención veterinaria regular.");
        if (entrenado) {
            System.out.println("El canino cuenta con adiestramiento previo.");
        }
    }

    // Getters y Setters
    public boolean isEntrenado() {
        return entrenado;
    }

    public void setEntrenado(boolean entrenado) {
        this.entrenado = entrenado;
    }

    public boolean isDiscapacitado() {
        return discapacitado;
    }

    public void setDiscapacitado(boolean discapacitado) {
        this.discapacitado = discapacitado;
    }

    public boolean isProtector() {
        return protector;
    }

    public void setProtector(boolean protector) {
        this.protector = protector;
    }
}
