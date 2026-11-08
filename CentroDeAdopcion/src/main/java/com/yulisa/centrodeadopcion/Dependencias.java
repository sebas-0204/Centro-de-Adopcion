/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.yulisa.centrodeadopcion;

/**
 *
 * @author YULI
 */

import java.nio.file.Path;


import com.yulisa.centrodeadopcion.repositorios.TrabajadorRepositorio;
import com.yulisa.centrodeadopcion.repositorios.AnimalRepositorio;
import com.yulisa.centrodeadopcion.repositorios.ClienteRepositorio;


import com.yulisa.centrodeadopcion.repositorios.archivo.TrabajadorRepositorioArchivo;
import com.yulisa.centrodeadopcion.repositorios.archivo.AnimalRepositorioArchivo;
import com.yulisa.centrodeadopcion.repositorios.archivo.ClienteRepositorioArchivo;
import com.yulisa.centrodeadopcion.repositorios.archivo.ArchivoSerializadoStore;

public final class Dependencias {
    private static final TrabajadorRepositorio TRABAJADORES =
        new TrabajadorRepositorioArchivo(new ArchivoSerializadoStore<>(Path.of("trabajadores.dat")));
    private static final AnimalRepositorio ANIMALES =
        new AnimalRepositorioArchivo(new ArchivoSerializadoStore<>(Path.of("animales.dat")));
    private static final ClienteRepositorio CLIENTES =
        new ClienteRepositorioArchivo(new ArchivoSerializadoStore<>(Path.of("clientes.dat")));

    private Dependencias() { }

    public static TrabajadorRepositorio trabajadores() { return TRABAJADORES; }
    public static AnimalRepositorio animales()         { return ANIMALES; }
    public static ClienteRepositorio clientes()        { return CLIENTES; }
}