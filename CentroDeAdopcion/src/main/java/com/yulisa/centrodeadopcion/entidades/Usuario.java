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
public abstract class Usuario implements Serializable {
    private String nombre;
    private int edad;
    private String documento;
    private String contacto;
    
    public Usuario(String nombre,int edad, String documento, String contacto){
        this.nombre = nombre;
        this.edad = edad;
        this.documento = documento;
        this.contacto = contacto;
    }
    public abstract void mostrarRolEnSistema();
    
    public String getContacto(){
        return contacto;
    }
    public void setContacto(String contacto){
        this.contacto = contacto;
    }
    public String getNombre(){
        return nombre;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public int getEdad(){
        return edad;
    }
    public void setEdad(int edad){
        this.edad = edad;
    }
    public String getDocumento(){
        return documento;
    }
    public void setDocumento(String documento){
        this.documento = documento;
    }
}
