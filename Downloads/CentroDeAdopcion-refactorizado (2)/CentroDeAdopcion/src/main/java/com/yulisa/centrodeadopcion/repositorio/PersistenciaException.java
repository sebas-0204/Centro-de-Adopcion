package com.yulisa.centrodeadopcion.repositorio;

/** Error al leer o escribir datos. Reemplaza los printStackTrace() que ocultaban fallos. */
public class PersistenciaException extends RuntimeException {

    @java.io.Serial
    private static final long serialVersionUID = 1L;

    public PersistenciaException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
