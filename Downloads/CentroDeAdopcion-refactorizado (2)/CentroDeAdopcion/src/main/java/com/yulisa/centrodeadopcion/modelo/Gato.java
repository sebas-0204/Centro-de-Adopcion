package com.yulisa.centrodeadopcion.modelo;

import java.io.Serial;

public final class Gato extends Animal {

    @Serial
    private static final long serialVersionUID = 1L;

    private final String cuidadosEspeciales;

    public Gato(DatosAnimal datos) {
        super(datos);
        String cuidados = datos.cuidadosEspeciales();
        this.cuidadosEspeciales = (cuidados == null) ? "" : cuidados.trim();
    }

    @Override
    public TipoAnimal getTipo() {
        return TipoAnimal.GATO;
    }

    @Override
    public String descripcionCuidados() {
        String base = "Arenero limpio, rascador y control veterinario regular.";
        return cuidadosEspeciales.isEmpty() ? base : base + " Cuidados especiales: " + cuidadosEspeciales;
    }

    public String getCuidadosEspeciales() { return cuidadosEspeciales; }
}
