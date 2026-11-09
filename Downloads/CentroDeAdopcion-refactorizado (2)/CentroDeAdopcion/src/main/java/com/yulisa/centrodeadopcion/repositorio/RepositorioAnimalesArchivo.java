package com.yulisa.centrodeadopcion.repositorio;

import com.yulisa.centrodeadopcion.modelo.Animal;
import java.nio.file.Path;
import java.util.UUID;

public final class RepositorioAnimalesArchivo extends RepositorioEnArchivo<Animal, UUID>
        implements RepositorioAnimales {

    public RepositorioAnimalesArchivo(Path archivo) {
        super(new ArchivoSerializadoStore<>(archivo, Animal.class));
    }

    @Override
    protected UUID idDe(Animal animal) {
        return animal.getId();
    }
}
