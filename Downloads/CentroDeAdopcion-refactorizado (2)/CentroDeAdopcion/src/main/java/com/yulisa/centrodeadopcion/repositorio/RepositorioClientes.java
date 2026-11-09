package com.yulisa.centrodeadopcion.repositorio;

import com.yulisa.centrodeadopcion.modelo.Cliente;

/** El id de un cliente es su nombre de usuario normalizado (ver Credenciales). */
public interface RepositorioClientes extends Repositorio<Cliente, String> {

    default boolean existeNombreUsuario(String nombreUsuario) {
        return buscarPorId(nombreUsuario).isPresent();
    }
}
