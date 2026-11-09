package com.yulisa.centrodeadopcion.presentacion;

import com.yulisa.centrodeadopcion.dto.RegistroTrabajadorDTO;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JTextField;

public class RegistroTrabajadorPanel extends VistaBase {

    private final JTextField txtNombre = new JTextField(20);
    private final JTextField txtEdad = new JTextField(5);
    private final JTextField txtDocumento = new JTextField(15);
    private final JTextField txtContacto = new JTextField(20);
    private final JTextField txtCargo = new JTextField(15);

    public RegistroTrabajadorPanel(TrabajadorController controller) {
        JButton btnRegistrar = new JButton("Registrar");
        JButton btnVolver = new JButton("Volver");
        btnRegistrar.addActionListener(e -> {
            RegistroTrabajadorDTO datos = new RegistroTrabajadorDTO(
                    txtNombre.getText(), txtEdad.getText(), txtDocumento.getText(),
                    txtContacto.getText(), txtCargo.getText());
            if (controller.registrar(datos, this)) {
                limpiar();
            }
        });
        btnVolver.addActionListener(e -> controller.volverAlMenu());

        add(titulo("Registro de trabajador"), BorderLayout.NORTH);
        add(new Formulario()
                .campo("Nombre:", txtNombre)
                .campo("Edad:", txtEdad)
                .campo("Documento:", txtDocumento)
                .campo("Contacto:", txtContacto)
                .campo("Cargo:", txtCargo), BorderLayout.CENTER);
        add(filaDeBotones(btnRegistrar, btnVolver), BorderLayout.SOUTH);
    }

    @Override
    protected void alMostrar() {
        limpiar();
    }

    private void limpiar() {
        txtNombre.setText("");
        txtEdad.setText("");
        txtDocumento.setText("");
        txtContacto.setText("");
        txtCargo.setText("");
    }
}
