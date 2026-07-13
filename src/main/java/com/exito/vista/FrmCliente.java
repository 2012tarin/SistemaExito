package com.exito.vista;

import com.exito.dao.ClienteDAO;
import com.exito.modelo.Cliente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class FrmCliente extends JFrame {

    JTextField txtNombre;
    JTextField txtApellido;
    JTextField txtCedula;
    JTextField txtTelefono;
    JTextField txtDireccion;
    JTextField txtCorreo;

    JTable tablaClientes;
    DefaultTableModel modelo;

    ClienteDAO clienteDAO = new ClienteDAO();

    public FrmCliente() {

        setTitle("Clientes - Sistema Éxito");
        setSize(700,500);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        iniciarComponentes();
        listarClientes();
    }


    private void iniciarComponentes(){

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(30,30,100,25);
        add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(130,30,150,25);
        add(txtNombre);


        JLabel lblApellido = new JLabel("Apellido:");
        lblApellido.setBounds(30,70,100,25);
        add(lblApellido);

        txtApellido = new JTextField();
        txtApellido.setBounds(130,70,150,25);
        add(txtApellido);


        JLabel lblCedula = new JLabel("Cédula:");
        lblCedula.setBounds(30,110,100,25);
        add(lblCedula);

        txtCedula = new JTextField();
        txtCedula.setBounds(130,110,150,25);
        add(txtCedula);


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
        lblCorreo.setBounds(330,110,100,25);
        add(lblCorreo);

        txtCorreo = new JTextField();
        txtCorreo.setBounds(430,110,150,25);
        add(txtCorreo);


        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(150,160,100,30);
        add(btnGuardar);

        JButton btnActualizar = new JButton("Actualizar");
        btnActualizar.setBounds(260,160,100,30);
        add(btnActualizar);


        btnActualizar.addActionListener(e -> actualizarCliente());


        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(370,160,100,30);
        add(btnEliminar);


        btnEliminar.addActionListener(e -> eliminarCliente());


        btnGuardar.addActionListener(e -> guardarCliente());


        JButton btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setBounds(480,160,100,30);
        add(btnLimpiar);

        btnLimpiar.addActionListener(e -> limpiar());


        modelo = new DefaultTableModel();

        modelo.addColumn("ID");
        modelo.addColumn("Nombre");
        modelo.addColumn("Apellido");
        modelo.addColumn("Cédula");
        modelo.addColumn("Teléfono");
        modelo.addColumn("Dirección");
        modelo.addColumn("Correo");


        tablaClientes = new JTable(modelo);

        tablaClientes.getSelectionModel().addListSelectionListener(e -> {

            int fila = tablaClientes.getSelectedRow();

            if(fila >= 0){

                txtNombre.setText(modelo.getValueAt(fila,1).toString());
                txtApellido.setText(modelo.getValueAt(fila,2).toString());
                txtCedula.setText(modelo.getValueAt(fila,3).toString());
                txtTelefono.setText(modelo.getValueAt(fila,4).toString());
                txtDireccion.setText(modelo.getValueAt(fila,5).toString());
                txtCorreo.setText(modelo.getValueAt(fila,6).toString());

            }

        });

        JScrollPane scroll = new JScrollPane(tablaClientes);
        scroll.setBounds(30,220,620,200);

        add(scroll);

    }


    private void guardarCliente(){

        Cliente cliente = new Cliente();

        cliente.setNombre(txtNombre.getText());
        cliente.setApellido(txtApellido.getText());
        cliente.setCedula(txtCedula.getText());
        cliente.setTelefono(txtTelefono.getText());
        cliente.setDireccion(txtDireccion.getText());
        cliente.setCorreo(txtCorreo.getText());


        if(clienteDAO.guardar(cliente)){

            JOptionPane.showMessageDialog(this,
                    "Cliente guardado correctamente");

            listarClientes();
            limpiar();

        }else{

            JOptionPane.showMessageDialog(this,
                    "Error al guardar cliente");
        }

    }


    private void listarClientes(){

        modelo.setRowCount(0);

        List<Cliente> lista = clienteDAO.listar();

        for(Cliente c: lista){

            modelo.addRow(new Object[]{
                    c.getIdCliente(),
                    c.getNombre(),
                    c.getApellido(),
                    c.getCedula(),
                    c.getTelefono(),
                    c.getDireccion(),
                    c.getCorreo()
            });

        }

    }


    private void limpiar(){

        txtNombre.setText("");
        txtApellido.setText("");
        txtCedula.setText("");
        txtTelefono.setText("");
        txtDireccion.setText("");
        txtCorreo.setText("");

    }

    private void actualizarCliente(){

        int fila = tablaClientes.getSelectedRow();

        if(fila == -1){

            JOptionPane.showMessageDialog(this,
                    "Seleccione un cliente de la tabla");

            return;
        }


        Cliente cliente = new Cliente();

        cliente.setIdCliente(
                Integer.parseInt(modelo.getValueAt(fila,0).toString())
        );

        cliente.setNombre(txtNombre.getText());
        cliente.setApellido(txtApellido.getText());
        cliente.setCedula(txtCedula.getText());
        cliente.setTelefono(txtTelefono.getText());
        cliente.setDireccion(txtDireccion.getText());
        cliente.setCorreo(txtCorreo.getText());


        if(clienteDAO.actualizar(cliente)){

            JOptionPane.showMessageDialog(this,
                    "Cliente actualizado");

            listarClientes();
            limpiar();

        }

    }
    private void eliminarCliente(){

        int fila = tablaClientes.getSelectedRow();

        if(fila == -1){

            JOptionPane.showMessageDialog(this,
                    "Seleccione un cliente");

            return;
        }


        int id = Integer.parseInt(
                modelo.getValueAt(fila,0).toString()
        );


        if(clienteDAO.eliminar(id)){

            JOptionPane.showMessageDialog(this,
                    "Cliente eliminado");

            listarClientes();
            limpiar();

        }

    }
}