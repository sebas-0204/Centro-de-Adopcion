package com.yulisa.centrodeadopcion.modelo;

import java.io.Serial;

public final class Trabajador extends Usuario {

    @Serial
    private static final long serialVersionUID = 1L;

    private final String cargo;

    public Trabajador(String nombre, int edad, String documento, String contacto, String cargo) {
        super(nombre, edad, documento, contacto);
        this.cargo = cargo;
    }

    public String getCargo() { return cargo; }
}
