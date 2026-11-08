/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.yulisa.centrodeadopcion.repositorios.archivo;

/**
 *
 * @author YULI
 */

import com.yulisa.centrodeadopcion.entidades.Cliente;
import com.yulisa.centrodeadopcion.repositorios.ClienteRepositorio;
public final class ClienteRepositorioArchivo
        extends RepositorioEnArchivo<Cliente, String> implements ClienteRepositorio {

    public ClienteRepositorioArchivo(ArchivoSerializadoStore<Cliente> store) { super(store); }

    @Override protected String idDe(Cliente c) { return c.getUsuario(); }

    @Override public boolean existeNombreUsuario(String nombreUsuario) {
        return listar().stream().anyMatch(c -> c.getUsuario().equalsIgnoreCase(nombreUsuario));
    }
}
