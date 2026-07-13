package com.exito.vista;

import javax.swing.*;
import java.awt.*;
import javax.swing.JOptionPane;

public class Login extends JFrame {

    private JTextField txtUsuario;
    private JPasswordField txtPassword;
    private JButton btnIngresar;

    public Login() {

        setTitle("Sistema Éxito - Login");
        setSize(400,250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel lblUsuario = new JLabel("Usuario:");
        lblUsuario.setBounds(40,40,100,25);
        panel.add(lblUsuario);

        txtUsuario = new JTextField();
        txtUsuario.setBounds(150,40,180,25);
        panel.add(txtUsuario);

        JLabel lblPassword = new JLabel("Contraseña:");
        lblPassword.setBounds(40,90,100,25);
        panel.add(lblPassword);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(150,90,180,25);
        panel.add(txtPassword);

        btnIngresar = new JButton("Ingresar");
        btnIngresar.setBounds(130,150,120,30);
        panel.add(btnIngresar);

        add(panel);

        btnIngresar.addActionListener(e -> {

            String usuario = txtUsuario.getText();
            String password = String.valueOf(txtPassword.getPassword());

            if (usuario.equals("admin") && password.equals("123456")) {

                MenuPrincipal menu = new MenuPrincipal();
                menu.setVisible(true);

                dispose();

            } else {

                JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos");

            }

        });

    }

}