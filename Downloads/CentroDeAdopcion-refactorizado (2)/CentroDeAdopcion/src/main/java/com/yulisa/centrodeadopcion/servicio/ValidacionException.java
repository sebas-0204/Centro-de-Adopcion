package com.yulisa.centrodeadopcion.servicio;

import java.util.List;

/**
 * Datos inválidos o regla de negocio incumplida. Lleva todos los mensajes
 * para que la vista los muestre juntos, en vez de fallar en silencio.
 */
public class ValidacionException extends Exception {

    @java.io.Serial
    private static final long serialVersionUID = 1L;

    private final List<String> errores;

    public ValidacionException(List<String> errores) {
        super(String.join(" ", errores));
        this.errores = List.copyOf(errores);
    }

    public ValidacionException(String error) {
        this(List.of(error));
    }

    public List<String> getErrores() {
        return errores;
    }
}
