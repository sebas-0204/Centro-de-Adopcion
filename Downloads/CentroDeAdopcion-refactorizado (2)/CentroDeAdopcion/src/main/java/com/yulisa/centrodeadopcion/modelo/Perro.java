package com.yulisa.centrodeadopcion.modelo;

import java.io.Serial;

public final class Perro extends Animal {

    @Serial
    private static final long serialVersionUID = 1L;

    private final boolean entrenado;
    private final boolean protector;
    private final boolean tieneDiscapacidad;

    public Perro(DatosAnimal datos) {
        super(datos);
        this.entrenado = datos.entrenado();
        this.protector = datos.protector();
        this.tieneDiscapacidad = datos.tieneDiscapacidad();
    }

    @Override
    public TipoAnimal getTipo() {
        return TipoAnimal.PERRO;
    }

    @Override
    public String descripcionCuidados() {
        StringBuilder sb = new StringBuilder("Paseos diarios y control veterinario regular.");
        if (entrenado) {
            sb.append(" Cuenta con adiestramiento previo.");
        }
        if (protector) {
            sb.append(" Es protector: requiere socialización gradual.");
        }
        if (tieneDiscapacidad) {
            sb.append(" Tiene una discapacidad: necesita atención especial.");
        }
        return sb.toString();
    }

    public boolean isEntrenado() { return entrenado; }
    public boolean isProtector() { return protector; }
    public boolean tieneDiscapacidad() { return tieneDiscapacidad; }
}
