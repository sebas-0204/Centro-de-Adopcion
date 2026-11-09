package com.yulisa.centrodeadopcion.repositorio;

import java.util.List;
import java.util.Optional;

/** Contrato mínimo de persistencia. Los servicios dependen de esto, no del archivo. */
public interface Repositorio<T, ID> {

    /** Inserta o reemplaza (upsert) y persiste de inmediato. */
    void guardar(T entidad);

    Optional<T> buscarPorId(ID id);

    /** Copia inmodificable: nadie puede alterar la lista sin pasar por guardar(). */
    List<T> listar();
}
