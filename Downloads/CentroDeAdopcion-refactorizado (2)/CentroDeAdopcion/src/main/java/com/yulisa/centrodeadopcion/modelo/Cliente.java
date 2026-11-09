package com.yulisa.centrodeadopcion.modelo;

import java.io.Serial;
import java.util.Objects;

/** Persona que solicita adoptar. Sus credenciales viven en un objeto aparte. */
public final class Cliente extends Usuario {

    @Serial
    private static final long serialVersionUID = 1L;

    private final Credenciales credenciales;
    private final boolean tieneIngresosPropios;
    private final boolean tieneViviendaPropia;
    private final boolean tuvoMascotas;

    public Cliente(String nombre, int edad, String documento, String contacto,
                   Credenciales credenciales,
                   boolean tieneIngresosPropios, boolean tieneViviendaPropia, boolean tuvoMascotas) {
        super(nombre, edad, documento, contacto);
        this.credenciales = Objects.requireNonNull(credenciales, "credenciales");
        this.tieneIngresosPropios = tieneIngresosPropios;
        this.tieneViviendaPropia = tieneViviendaPropia;
        this.tuvoMascotas = tuvoMascotas;
    }

    /** Regla de dominio: puede adoptar si tiene ingresos propios y vivienda propia. */
    public boolean estaCapacitado() {
        return tieneIngresosPropios && tieneViviendaPropia;
    }

    public Credenciales getCredenciales() { return credenciales; }
    public String getNombreUsuario() { return credenciales.nombreUsuario(); }
    public boolean tieneIngresosPropios() { return tieneIngresosPropios; }
    public boolean tieneViviendaPropia() { return tieneViviendaPropia; }
    public boolean tuvoMascotas() { return tuvoMascotas; }
}
