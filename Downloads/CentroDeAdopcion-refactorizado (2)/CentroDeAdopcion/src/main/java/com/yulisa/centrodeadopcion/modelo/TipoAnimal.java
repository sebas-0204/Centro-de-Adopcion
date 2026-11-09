package com.yulisa.centrodeadopcion.modelo;

import java.util.function.Function;

/**
 * Especies soportadas. Cada constante sabe cómo construir su animal (Factory),
 * así ninguna vista ni servicio necesita un if por especie ni instanceof.
 * Agregar una especie = una clase nueva + una línea aquí.
 */
public enum TipoAnimal {

    PERRO("Perro", Perro::new),
    GATO("Gato", Gato::new);

    private final String etiqueta;
    private final Function<DatosAnimal, Animal> fabrica;

    TipoAnimal(String etiqueta, Function<DatosAnimal, Animal> fabrica) {
        this.etiqueta = etiqueta;
        this.fabrica = fabrica;
    }

    public Animal crear(DatosAnimal datos) {
        return fabrica.apply(datos);
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    /** Usado por JComboBox para mostrar el texto. */
    @Override
    public String toString() {
        return etiqueta;
    }
}
