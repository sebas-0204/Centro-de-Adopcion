/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.yulisa.centrodeadopcion.entidades;

/**
 *
 * @author YULI
 */
public class Cliente extends Usuario {
    private boolean capacitado;
    private boolean ingresos;
    private boolean espacioVivienda;
    private boolean mascotasAntes;
    private String usuario;
    private String password;
    
    public Cliente(String nombre, int edad, String documento, String contacto, boolean ingresos, boolean espacioVivienda, boolean mascotasAntes, String usuario, String password) {
        super(nombre, edad, documento, contacto);
        this.ingresos = ingresos;
        this.espacioVivienda = espacioVivienda;
        this.mascotasAntes = mascotasAntes;
        this.usuario=usuario;
        this.password=password;
        this.capacitado = (ingresos && espacioVivienda);
    }
    public boolean isIngresos() { 
        return ingresos; 
    }
    public void setIngresos(boolean ingresos) { 
        this.ingresos = ingresos; 
    }
    
    public String getUsuario() { 
        return usuario; 
    }
    public void setUsuario(String usuario) { 
        this.usuario = usuario; 
    }
    
     public String getPassword() { 
        return password; 
    }
    public void setPassword(String password) { 
        this.password = password; 
    }
    
    public boolean isEspacioVivienda() { 
        return espacioVivienda; 
    }
    public void setEspacioVivienda(boolean espacioVivienda) { 
        this.espacioVivienda = espacioVivienda; 
    }
    
    public boolean isMascotasAntes() { 
        return mascotasAntes; 
    }
    public void setMascotasAntes(boolean mascotasAntes) { 
        this.mascotasAntes = mascotasAntes; 
    }
    
    @Override
    public void mostrarRolEnSistema() {
        System.out.println("Rol: Cliente solicitante de adopción.");
    }
    public boolean isCapacitado(){
        return capacitado;
    }
    public void setCapacitado(boolean capacitado){
        this.capacitado = capacitado;
    }
}