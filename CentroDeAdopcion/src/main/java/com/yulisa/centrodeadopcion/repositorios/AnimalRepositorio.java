/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.yulisa.centrodeadopcion.repositorios;

/**
 *
 * @author YULI
 */

import java.util.List;
import java.util.Optional;
import com.yulisa.centrodeadopcion.entidades.Animal;

public interface AnimalRepositorio {
    List<Animal> listar();
    Optional<Animal> buscarPorId(String id);
    void guardar(Animal animal);
}

