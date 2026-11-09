package com.yulisa.centrodeadopcion.repositorio;

import com.yulisa.centrodeadopcion.modelo.Cliente;
import com.yulisa.centrodeadopcion.modelo.Credenciales;
import java.nio.file.Path;

public final class RepositorioClientesArchivo extends RepositorioEnArchivo<Cliente, String>
        implements RepositorioClientes {

    public RepositorioClientesArchivo(Path archivo) {
        super(new ArchivoSerializadoStore<>(archivo, Cliente.class));
    }

    @Override
    protected String idDe(Cliente cliente) {
        return cliente.getNombreUsuario();
    }

    @Override
    protected String normalizarId(String nombreUsuario) {
        return Credenciales.normalizar(nombreUsuario);
    }
}
