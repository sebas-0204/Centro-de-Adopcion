package com.yulisa.centrodeadopcion.repositorio;

import com.yulisa.centrodeadopcion.modelo.Animal;
import java.util.List;
import java.util.UUID;

public interface RepositorioAnimales extends Repositorio<Animal, UUID> {

    default List<Animal> listarDisponibles() {
        return listar().stream().filter(a -> !a.isAdoptado()).toList();
    }
}
