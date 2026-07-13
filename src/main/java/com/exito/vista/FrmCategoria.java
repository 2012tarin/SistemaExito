package com.exito.vista;

import com.exito.dao.CategoriaDAO;
import com.exito.modelo.Categoria;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;

public class FrmCategoria extends JFrame {

    JTextField txtNombre;
    JTextField txtDescripcion;

    JButton btnGuardar;
    JButton btnEditar;
    JButton btnEliminar;
    JButton btnNuevo;

    JTable tablaCategorias;
    DefaultTableModel modelo;

    CategoriaDAO dao = new CategoriaDAO();


    public FrmCategoria() {

        setTitle("Gestión de Categorías");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);


        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(30,30,100,25);
        add(lblNombre);


        txtNombre = new JTextField();
        txtNombre.setBounds(130,30,200,25);
        add(txtNombre);


        JLabel lblDescripcion = new JLabel("Descripción:");
        lblDescripcion.setBounds(30,70,100,25);
        add(lblDescripcion);


        txtDescripcion = new JTextField();
        txtDescripcion.setBounds(130,70,200,25);
        add(txtDescripcion);


        btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(30,120,100,30);
        add(btnGuardar);


        btnNuevo = new JButton("Nuevo");
        btnNuevo.setBounds(150,120,100,30);
        add(btnNuevo);



        // TABLA

        modelo = new DefaultTableModel();

        modelo.addColumn("ID");
        modelo.addColumn("Nombre");
        modelo.addColumn("Descripción");


        tablaCategorias = new JTable(modelo);

        JScrollPane scroll = new JScrollPane(tablaCategorias);

        scroll.setBounds(30,180,520,150);

        add(scroll);



        cargarTabla();



        // BOTON GUARDAR

        btnGuardar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {


                String nombre = txtNombre.getText();
                String descripcion = txtDescripcion.getText();


                Categoria categoria = new Categoria();

                categoria.setNombre_categoria(nombre);
                categoria.setDescripcion(descripcion);



                if(dao.guardar(categoria)){

                    JOptionPane.showMessageDialog(null,
                            "Categoría guardada correctamente");

                    limpiar();
                    cargarTabla();

                }else{

                    JOptionPane.showMessageDialog(null,
                            "Error al guardar");

                }

            }

        });



        // BOTON NUEVO

        btnNuevo.addActionListener(e -> {

            limpiar();

        });



    }



    // CARGAR TABLA

    public void cargarTabla(){


        modelo.setRowCount(0);


        for(Categoria c : dao.listar()){


            modelo.addRow(new Object[]{

                    c.getId_categoria(),
                    c.getNombre_categoria(),
                    c.getDescripcion()

            });


        }

    }



    public void limpiar(){

        txtNombre.setText("");
        txtDescripcion.setText("");

    }



}