package com.yulisa.centrodeadopcion.repositorio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Base común de los repositorios en archivo (Template Method):
 * cada subclase solo indica cuál es el id de su entidad.
 */
public abstract class RepositorioEnArchivo<T extends Serializable, ID> implements Repositorio<T, ID> {

    private final ArchivoSerializadoStore<T> store;
    private final List<T> cache;

    protected RepositorioEnArchivo(ArchivoSerializadoStore<T> store) {
        this.store = store;
        this.cache = new ArrayList<>(store.leer());
    }

    protected abstract ID idDe(T entidad);

    /** Permite normalizar el id buscado (por ejemplo, a minúsculas). */
    protected ID normalizarId(ID id) {
        return id;
    }

    @Override
    public synchronized void guardar(T entidad) {
        Objects.requireNonNull(entidad, "entidad");
        ID id = idDe(entidad);
        cache.removeIf(e -> idDe(e).equals(id));
        cache.add(entidad);
        store.escribir(cache);
    }

    @Override
    public synchronized Optional<T> buscarPorId(ID id) {
        if (id == null) {
            return Optional.empty();
        }
        ID buscado = normalizarId(id);
        return cache.stream().filter(e -> idDe(e).equals(buscado)).findFirst();
    }

    @Override
    public synchronized List<T> listar() {
        return List.copyOf(cache);
    }
}
