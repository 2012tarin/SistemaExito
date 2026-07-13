package com.exito.vista;

import com.exito.dao.ProveedorDAO;
import com.exito.modelo.Proveedor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class FrmProveedor extends JFrame {

    JTextField txtNombre;
    JTextField txtEmpresa;
    JTextField txtTelefono;
    JTextField txtDireccion;
    JTextField txtCorreo;

    JTable tablaProveedores;
    DefaultTableModel modelo;

    ProveedorDAO proveedorDAO = new ProveedorDAO();


    public FrmProveedor(){

        setTitle("Proveedores - Sistema Éxito");
        setSize(700,500);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        iniciarComponentes();
        listarProveedores();

    }


    private void iniciarComponentes(){


        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(30,30,100,25);
        add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(130,30,150,25);
        add(txtNombre);



        JLabel lblEmpresa = new JLabel("Empresa:");
        lblEmpresa.setBounds(30,70,100,25);
        add(lblEmpresa);

        txtEmpresa = new JTextField();
        txtEmpresa.setBounds(130,70,150,25);
        add(txtEmpresa);



        JLabel lblTelefono = new JLabel("Teléfono:");
        lblTelefono.setBounds(330,30,100,25);
        add(lblTelefono);

        txtTelefono = new JTextField();
        txtTelefono.setBounds(430,30,150,25);
        add(txtTelefono);



        JLabel lblDireccion = new JLabel("Dirección:");
        lblDireccion.setBounds(330,70,100,25);
        add(lblDireccion);

        txtDireccion = new JTextField();
        txtDireccion.setBounds(430,70,150,25);
        add(txtDireccion);



        JLabel lblCorreo = new JLabel("Correo:");
        lblCorreo.setBounds(30,110,100,25);
        add(lblCorreo);

        txtCorreo = new JTextField();
        txtCorreo.setBounds(130,110,150,25);
        add(txtCorreo);



        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(120,160,100,30);
        add(btnGuardar);

        btnGuardar.addActionListener(e -> guardarProveedor());



        JButton btnActualizar = new JButton("Actualizar");
        btnActualizar.setBounds(240,160,100,30);
        add(btnActualizar);

        btnActualizar.addActionListener(e -> actualizarProveedor());



        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(360,160,100,30);
        add(btnEliminar);

        btnEliminar.addActionListener(e -> eliminarProveedor());



        JButton btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setBounds(480,160,100,30);
        add(btnLimpiar);

        btnLimpiar.addActionListener(e -> limpiar());



        modelo = new DefaultTableModel();

        modelo.addColumn("ID");
        modelo.addColumn("Nombre");
        modelo.addColumn("Empresa");
        modelo.addColumn("Teléfono");
        modelo.addColumn("Dirección");
        modelo.addColumn("Correo");


        tablaProveedores = new JTable(modelo);


        tablaProveedores.getSelectionModel().addListSelectionListener(e -> {

            int fila = tablaProveedores.getSelectedRow();

            if(fila >=0){

                txtNombre.setText(modelo.getValueAt(fila,1).toString());
                txtEmpresa.setText(modelo.getValueAt(fila,2).toString());
                txtTelefono.setText(modelo.getValueAt(fila,3).toString());
                txtDireccion.setText(modelo.getValueAt(fila,4).toString());
                txtCorreo.setText(modelo.getValueAt(fila,5).toString());

            }

        });



        JScrollPane scroll = new JScrollPane(tablaProveedores);
        scroll.setBounds(30,220,620,200);

        add(scroll);

    }



    private void guardarProveedor(){

        Proveedor proveedor = new Proveedor();

        proveedor.setNombre(txtNombre.getText());
        proveedor.setEmpresa(txtEmpresa.getText());
        proveedor.setTelefono(txtTelefono.getText());
        proveedor.setDireccion(txtDireccion.getText());
        proveedor.setCorreo(txtCorreo.getText());


        if(proveedorDAO.guardar(proveedor)){

            JOptionPane.showMessageDialog(this,
                    "Proveedor guardado");

            listarProveedores();
            limpiar();

        }

    }



    private void listarProveedores(){

        modelo.setRowCount(0);

        List<Proveedor> lista = proveedorDAO.listar();


        for(Proveedor p: lista){

            modelo.addRow(new Object[]{

                    p.getIdProveedor(),
                    p.getNombre(),
                    p.getEmpresa(),
                    p.getTelefono(),
                    p.getDireccion(),
                    p.getCorreo()

            });

        }

    }



    private void actualizarProveedor(){

        int fila = tablaProveedores.getSelectedRow();


        if(fila == -1){

            JOptionPane.showMessageDialog(this,
                    "Seleccione un proveedor");

            return;

        }


        Proveedor proveedor = new Proveedor();

        proveedor.setIdProveedor(
                Integer.parseInt(modelo.getValueAt(fila,0).toString())
        );


        proveedor.setNombre(txtNombre.getText());
        proveedor.setEmpresa(txtEmpresa.getText());
        proveedor.setTelefono(txtTelefono.getText());
        proveedor.setDireccion(txtDireccion.getText());
        proveedor.setCorreo(txtCorreo.getText());


        if(proveedorDAO.actualizar(proveedor)){

            JOptionPane.showMessageDialog(this,
                    "Proveedor actualizado");

            listarProveedores();
            limpiar();

        }

    }



    private void eliminarProveedor(){

        int fila = tablaProveedores.getSelectedRow();


        if(fila == -1){

            JOptionPane.showMessageDialog(this,
                    "Seleccione un proveedor");

            return;

        }


        int id = Integer.parseInt(
                modelo.getValueAt(fila,0).toString()
        );


        if(proveedorDAO.eliminar(id)){

            JOptionPane.showMessageDialog(this,
                    "Proveedor eliminado");

            listarProveedores();
            limpiar();

        }

    }



    private void limpiar(){

        txtNombre.setText("");
        txtEmpresa.setText("");
        txtTelefono.setText("");
        txtDireccion.setText("");
        txtCorreo.setText("");

    }

}
