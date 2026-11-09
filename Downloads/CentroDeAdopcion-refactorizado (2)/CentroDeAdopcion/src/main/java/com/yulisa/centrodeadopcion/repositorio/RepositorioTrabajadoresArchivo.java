package com.yulisa.centrodeadopcion.repositorio;

import com.yulisa.centrodeadopcion.modelo.Trabajador;
import java.nio.file.Path;

public final class RepositorioTrabajadoresArchivo extends RepositorioEnArchivo<Trabajador, String>
        implements RepositorioTrabajadores {

    public RepositorioTrabajadoresArchivo(Path archivo) {
        super(new ArchivoSerializadoStore<>(archivo, Trabajador.class));
    }

    @Override
    protected String idDe(Trabajador trabajador) {
        return trabajador.getDocumento();
    }

    @Override
    protected String normalizarId(String documento) {
        return documento.trim();
    }
}
