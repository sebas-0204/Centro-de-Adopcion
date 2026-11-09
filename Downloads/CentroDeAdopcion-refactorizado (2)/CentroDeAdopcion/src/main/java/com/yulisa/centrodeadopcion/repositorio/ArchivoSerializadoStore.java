package com.yulisa.centrodeadopcion.repositorio;

import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.AtomicMoveNotSupportedException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

/**
 * Lee y escribe una lista de objetos en un archivo serializado.
 * Escritura atómica: primero a un .tmp y luego move, así un corte a mitad
 * de escritura nunca deja el .dat corrupto.
 */
public final class ArchivoSerializadoStore<T extends Serializable> {

    private final Path archivo;
    private final Class<T> tipo;

    public ArchivoSerializadoStore(Path archivo, Class<T> tipo) {
        this.archivo = archivo;
        this.tipo = tipo;
    }

    public List<T> leer() {
        if (Files.notExists(archivo)) {
            return new ArrayList<>();
        }
        try (ObjectInputStream in = new ObjectInputStream(Files.newInputStream(archivo))) {
            List<?> crudo = (List<?>) in.readObject();
            List<T> resultado = new ArrayList<>(crudo.size());
            for (Object o : crudo) {
                resultado.add(tipo.cast(o));
            }
            return resultado;
        } catch (InvalidClassException | ClassNotFoundException | ClassCastException e) {
            throw new PersistenciaException(
                    "El archivo " + archivo + " tiene un formato antiguo o incompatible. "
                    + "Muévalo o bórrelo para empezar con datos nuevos.", e);
        } catch (IOException e) {
            throw new PersistenciaException("No se pudo leer " + archivo, e);
        }
    }

    public void escribir(List<T> datos) {
        Path tmp = archivo.resolveSibling(archivo.getFileName() + ".tmp");
        try {
            Path carpeta = archivo.toAbsolutePath().getParent();
            if (carpeta != null) {
                Files.createDirectories(carpeta);
            }
            try (ObjectOutputStream out = new ObjectOutputStream(Files.newOutputStream(tmp))) {
                out.writeObject(new ArrayList<>(datos));
            }
            mover(tmp);
        } catch (IOException e) {
            throw new PersistenciaException("No se pudo guardar " + archivo, e);
        }
    }

    private void mover(Path tmp) throws IOException {
        try {
            Files.move(tmp, archivo, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.ATOMIC_MOVE);
        } catch (AtomicMoveNotSupportedException e) {
            Files.move(tmp, archivo, StandardCopyOption.REPLACE_EXISTING);
        }
    }
}
