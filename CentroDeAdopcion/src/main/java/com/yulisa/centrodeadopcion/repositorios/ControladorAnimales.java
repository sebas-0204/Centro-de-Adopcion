/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.yulisa.centrodeadopcion.repositorios;
/**
 *
 * @author YULI
 */
import java.io.*;
import com.yulisa.centrodeadopcion.entidades.Animal;
import java.util.ArrayList;
import java.util.List;

public class ControladorAnimales {
    private static List<Animal> listaAnimales = cargarDatos();
    private static final String ARCHIVO = "animales.dat";

    public static void agregarAnimal(Animal animal) {
        listaAnimales.add(animal);
        guardarDatos();
    }

    public static List<Animal> getListaAnimales() {
        return listaAnimales;
    }
    public static void guardarDatos() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO))) {
            oos.writeObject(listaAnimales);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static List cargarDatos() {
        File archivo = new File(ARCHIVO);
        if (!archivo.exists()) {
            return new ArrayList<>(); 
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARCHIVO))) {
            return (List) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}