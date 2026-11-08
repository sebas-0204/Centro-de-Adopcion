/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.yulisa.centrodeadopcion.repositorios.archivo;

import com.yulisa.centrodeadopcion.entidades.Animal;
import com.yulisa.centrodeadopcion.repositorios.AnimalRepositorio;

/**
 *
 * @author YULI
 */

public final class AnimalRepositorioArchivo
        extends RepositorioEnArchivo<Animal, String> implements AnimalRepositorio {

    public AnimalRepositorioArchivo(ArchivoSerializadoStore<Animal> store) { super(store); }

    @Override protected String idDe(Animal a) { return a.getId(); }
}
