package com.yulisa.centrodeadopcion.repositorio;

import com.yulisa.centrodeadopcion.modelo.Trabajador;

/** El id de un trabajador es su documento. */
public interface RepositorioTrabajadores extends Repositorio<Trabajador, String> {

    default boolean existeDocumento(String documento) {
        return buscarPorId(documento).isPresent();
    }
}
