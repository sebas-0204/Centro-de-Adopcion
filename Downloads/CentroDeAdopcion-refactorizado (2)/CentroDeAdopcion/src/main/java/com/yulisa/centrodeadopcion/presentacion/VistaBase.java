package com.yulisa.centrodeadopcion.presentacion;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/** Base de todas las pantallas: mensajes al usuario y utilidades de maquetado comunes. */
public abstract class VistaBase extends JPanel implements MuestraMensajes {

    protected VistaBase() {
        super(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
    }

    /** Se llama cada vez que la pantalla se muestra (recargar tablas, limpiar campos). */
    protected void alMostrar() {
    }

    @Override
    public void mostrarInfo(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Información", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Revise los datos", JOptionPane.WARNING_MESSAGE);
    }

    protected static JLabel titulo(String texto) {
        JLabel etiqueta = new JLabel(texto, SwingConstants.CENTER);
        etiqueta.setFont(etiqueta.getFont().deriveFont(Font.BOLD, 20f));
        return etiqueta;
    }

    protected static JPanel filaDeBotones(JButton... botones) {
        JPanel fila = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 0));
        for (JButton boton : botones) {
            fila.add(boton);
        }
        return fila;
    }

    /** Columna de botones del mismo ancho, centrada (para los menús). */
    protected static JPanel columnaDeBotones(JButton... botones) {
        JPanel columna = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(6, 0, 6, 0);
        c.ipady = 8;
        for (JButton boton : botones) {
            columna.add(boton, c);
        }
        return columna;
    }

    /** Formulario de dos columnas: etiqueta a la izquierda, campo a la derecha. */
    protected static final class Formulario extends JPanel {

        private int fila;

        public Formulario() {
            super(new GridBagLayout());
        }

        public Formulario campo(String etiqueta, Component componente) {
            GridBagConstraints c = new GridBagConstraints();
            c.gridy = fila++;
            c.insets = new Insets(5, 5, 5, 5);
            c.gridx = 0;
            c.anchor = GridBagConstraints.LINE_END;
            add(new JLabel(etiqueta), c);
            c.gridx = 1;
            c.anchor = GridBagConstraints.LINE_START;
            c.fill = GridBagConstraints.HORIZONTAL;
            c.weightx = 1;
            add(componente, c);
            return this;
        }

        public Formulario fila(Component componente) {
            GridBagConstraints c = new GridBagConstraints();
            c.gridy = fila++;
            c.gridx = 1;
            c.insets = new Insets(3, 5, 3, 5);
            c.anchor = GridBagConstraints.LINE_START;
            add(componente, c);
            return this;
        }
    }
}
