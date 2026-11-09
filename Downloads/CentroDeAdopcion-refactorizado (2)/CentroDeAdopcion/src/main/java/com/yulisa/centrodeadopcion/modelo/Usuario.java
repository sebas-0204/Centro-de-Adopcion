package com.yulisa.centrodeadopcion.modelo;

import java.io.Serial;
import java.io.Serializable;

/** Datos comunes de una persona registrada en el centro. */
public abstract class Usuario implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private final String nombre;
    private final int edad;
    private final String documento;
    private final String contacto;

    protected Usuario(String nombre, int edad, String documento, String contacto) {
        this.nombre = nombre;
        this.edad = edad;
        this.documento = documento;
        this.contacto = contacto;
    }

    public String getNombre() { return nombre; }
    public int getEdad() { return edad; }
    public String getDocumento() { return documento; }
    public String getContacto() { return contacto; }
}
