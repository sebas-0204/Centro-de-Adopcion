/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.yulisa.centrodeadopcion.repositorios;

/**
 *
 * @author YULI
 */
import com.yulisa.centrodeadopcion.entidades.Trabajador;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ControladorTrabajadores {
    private static List<Trabajador> listaTrabajadores = cargarDatos();
    private static final String ARCHIVO = "trabajadores.dat";

    public static void agregarTrabajador(Trabajador trabajador) {
        listaTrabajadores.add(trabajador);
    }

    public static List<Trabajador> getListaTrabajadores() {
        return listaTrabajadores;
    }
    public static void guardarDatos() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO))) {
            oos.writeObject(listaTrabajadores);
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
    