/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.yulisa.centrodeadopcion.repositorios.archivo;

/**
 *
 * @author YULI
 */
import java.io.Serializable;
import java.util.List;
import java.util.Optional;
public abstract class RepositorioEnArchivo<T extends Serializable, ID> {
    private final ArchivoSerializadoStore<T> store;
    private final List<T> elementos;

    protected RepositorioEnArchivo(ArchivoSerializadoStore<T> store) {
        this.store = store;
        this.elementos = store.cargar();
    }

    /** Cada subclase dice cuál es la identidad de su entidad. */
    protected abstract ID idDe(T elemento);

    public List<T> listar() {
        return List.copyOf(elementos);       
    }

    public Optional<T> buscarPorId(ID id) {
        return elementos.stream().filter(e -> idDe(e).equals(id)).findFirst();
    }

    /** Inserta o actualiza, y SIEMPRE persiste. */
    public void guardar(T elemento) {
        int i = indiceDe(idDe(elemento));
        if (i >= 0) elementos.set(i, elemento); else elementos.add(elemento);
        store.guardar(elementos);
    }

    private int indiceDe(ID id) {
        for (int i = 0; i < elementos.size(); i++)
            if (idDe(elementos.get(i)).equals(id)) return i;
        return -1;
    }
}
