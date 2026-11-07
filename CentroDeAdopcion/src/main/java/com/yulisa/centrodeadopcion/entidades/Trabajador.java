/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.yulisa.centrodeadopcion.entidades;

/**
 *
 * @author YULI
 */
public class Trabajador extends Usuario {
    private String cargo; 

    public Trabajador(String nombre, int edad, String documento, String contacto, String cargo) {
        super(nombre, edad, documento, contacto);
        this.cargo = cargo;
    }

    @Override
    public void mostrarRolEnSistema() {
        System.out.println("Rol: Trabajador del centro. Cargo: " + cargo);
    }

    // Getters y Setters 
    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
}