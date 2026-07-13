package com.exito.vista;

import javax.swing.*;
import com.exito.vista.FrmCliente;

public class MenuPrincipal extends JFrame {

    public MenuPrincipal() {

        initComponents();

    }

    private void initComponents() {

        setTitle("Sistema Éxito");
        setSize(500, 400);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Botón Categorías
        JButton btnCategorias = new JButton("Categorías");
        btnCategorias.setBounds(50, 50, 150, 40);

        btnCategorias.addActionListener(e -> {
            FrmCategoria frm = new FrmCategoria();
            frm.setVisible(true);
        });

        add(btnCategorias);

        // Botón Productos
        JButton btnProductos = new JButton("Productos");
        btnProductos.setBounds(50, 110, 150, 40);

        btnProductos.addActionListener(e -> {
            FrmProducto frm = new FrmProducto();
            frm.setVisible(true);
        });

        add(btnProductos);

        // Botón Clientes
        JButton btnCliente = new JButton("Cliente");
        btnCliente.setBounds(50,170,150,40);

        btnCliente.addActionListener(e -> {
            FrmCliente ventana = new FrmCliente();
            ventana.setVisible(true);
        });

        add(btnCliente);

        // Botón Proveedores
        JButton btnProveedores = new JButton("Proveedores");
        btnProveedores.setBounds(50,230,150,40);

        btnProveedores.addActionListener(e -> {

            FrmProveedor ventana = new FrmProveedor();
            ventana.setVisible(true);

        });

        add(btnProveedores);

        // Botón Ventas
        JButton btnVentas = new JButton("Ventas");
        btnVentas.setBounds(50,290,150,40);

        btnVentas.addActionListener(e -> {

            FrmVenta ventana = new FrmVenta();
            ventana.setVisible(true);

        });

        add(btnVentas);

        JButton btnEmpleados = new JButton("Empleados");

        btnEmpleados.setBounds(200,200,120,40);

        add(btnEmpleados);


        btnEmpleados.addActionListener(e -> {

            FrmEmpleado ventana = new FrmEmpleado();

            ventana.setVisible(true);

        });

        JButton btnEmpleado = new JButton("Empleados");

        btnEmpleado.setBounds(200,200,120,40);

        add(btnEmpleado);


        btnEmpleado.addActionListener(e -> {

            FrmEmpleado ventana = new FrmEmpleado();

            ventana.setVisible(true);

        });



    }


    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new MenuPrincipal().setVisible(true);
        });

    }

}