/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.yulisa.centrodeadopcion.entidades;

/**
 *
 * @author YULI
 */

public class Gato extends Animal {
    private String cuidadosEspeciales; 
    private boolean requisitos;

    
    public Gato(String nombre, int edad, String fechaDeIngreso, String raza, boolean estadoDeAdopcion, String descripcion) {
        
        super(nombre, edad, fechaDeIngreso, raza, estadoDeAdopcion, descripcion);
        
    }


    @Override
    public void mostrarDescripcionCuidados() {
        System.out.println("Cuidados especiales para el gato " + getNombre() + ": " + cuidadosEspeciales);
    }

    // Getters y Setters
    public String getCuidadosEspeciales() {
        return cuidadosEspeciales;
    }

    public void setCuidadosEspeciales(String cuidadosEspeciales) {
        this.cuidadosEspeciales = cuidadosEspeciales;
    }

    public boolean isRequisitos() {
        return requisitos;
    }

    public void setRequisitos(boolean requisitos) {
        this.requisitos = requisitos;
    }
}
