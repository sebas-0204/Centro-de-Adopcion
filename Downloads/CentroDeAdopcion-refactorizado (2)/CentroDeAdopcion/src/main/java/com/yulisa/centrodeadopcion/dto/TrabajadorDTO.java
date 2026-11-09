package com.yulisa.centrodeadopcion.dto;

import com.yulisa.centrodeadopcion.modelo.Trabajador;

/** Fila de la tabla de trabajadores. */
public record TrabajadorDTO(String nombre, int edad, String documento, String contacto, String cargo) {

    public static TrabajadorDTO desde(Trabajador t) {
        return new TrabajadorDTO(t.getNombre(), t.getEdad(), t.getDocumento(), t.getContacto(), t.getCargo());
    }
}
