package com.yulisa.centrodeadopcion.modelo;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

/**
 * Animal del centro. Todos los datos son inmutables salvo el estado de adopción,
 * que solo puede pasar de "disponible" a "adoptado" (nunca al revés).
 */
public abstract class Animal implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private final UUID id;
    private final String nombre;
    private final int edad;
    private final String raza;
    private final LocalDate fechaIngreso;
    private final String descripcion;
    private boolean adoptado;

    protected Animal(DatosAnimal datos) {
        Objects.requireNonNull(datos, "datos");
        this.id = UUID.randomUUID();
        this.nombre = datos.nombre();
        this.edad = datos.edad();
        this.raza = datos.raza();
        this.fechaIngreso = datos.fechaIngreso();
        this.descripcion = datos.descripcion();
        this.adoptado = false;
    }

    /** Regla de dominio: un animal no se puede adoptar dos veces. */
    public void marcarComoAdoptado() {
        if (adoptado) {
            throw new IllegalStateException(nombre + " ya fue adoptado.");
        }
        adoptado = true;
    }

    public abstract TipoAnimal getTipo();

    public abstract String descripcionCuidados();

    public UUID getId() { return id; }
    public String getNombre() { return nombre; }
    public int getEdad() { return edad; }
    public String getRaza() { return raza; }
    public LocalDate getFechaIngreso() { return fechaIngreso; }
    public String getDescripcion() { return descripcion; }
    public boolean isAdoptado() { return adoptado; }
}
