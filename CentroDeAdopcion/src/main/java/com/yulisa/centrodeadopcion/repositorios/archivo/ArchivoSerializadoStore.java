/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.yulisa.centrodeadopcion.repositorios.archivo;

/**
 *
 * @author YULI
 */
import com.yulisa.centrodeadopcion.repositorios.PersistenciaException;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
public final class ArchivoSerializadoStore<T extends Serializable> {
    private final Path archivo;

    public ArchivoSerializadoStore(Path archivo) {
        this.archivo = Objects.requireNonNull(archivo);
    }

    @SuppressWarnings("unchecked")          
    public List<T> cargar() {
        if (Files.notExists(archivo)) return new ArrayList<>();
        try (ObjectInputStream in = new ObjectInputStream(Files.newInputStream(archivo))) {
            return new ArrayList<>((List<T>) in.readObject());
        } catch (IOException | ClassNotFoundException e) {
            throw new PersistenciaException("No se pudo leer " + archivo, e);
        }
    }

    public void guardar(List<T> datos) {
        Path temporal = archivo.resolveSibling(archivo.getFileName() + ".tmp");
        try {
            try (ObjectOutputStream out = new ObjectOutputStream(Files.newOutputStream(temporal))) {
                out.writeObject(new ArrayList<>(datos));
            }
           
            Files.move(temporal, archivo, StandardCopyOption.REPLACE_EXISTING,
                                          StandardCopyOption.ATOMIC_MOVE);
        } catch (IOException e) {
            throw new PersistenciaException("No se pudo escribir " + archivo, e);
        }
    }
}