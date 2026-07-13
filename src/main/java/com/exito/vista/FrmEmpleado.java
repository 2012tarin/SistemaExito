package com.exito.vista;

import com.exito.dao.EmpleadoDAO;
import com.exito.modelo.Empleado;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class FrmEmpleado extends JFrame {

    JTextField txtNombre;
    JTextField txtApellido;
    JTextField txtCargo;
    JTextField txtTelefono;
    JTextField txtCorreo;
    JTextField txtSalario;

    JTable tablaEmpleados;
    DefaultTableModel modelo;

    EmpleadoDAO empleadoDAO = new EmpleadoDAO();


    public FrmEmpleado(){

        setTitle("Empleados - Sistema Éxito");
        setSize(750,550);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        iniciarComponentes();
        listarEmpleados();

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



        JLabel lblCargo = new JLabel("Cargo:");
        lblCargo.setBounds(30,110,100,25);
        add(lblCargo);

        txtCargo = new JTextField();
        txtCargo.setBounds(130,110,150,25);
        add(txtCargo);



        JLabel lblTelefono = new JLabel("Teléfono:");
        lblTelefono.setBounds(330,30,100,25);
        add(lblTelefono);

        txtTelefono = new JTextField();
        txtTelefono.setBounds(430,30,150,25);
        add(txtTelefono);



        JLabel lblCorreo = new JLabel("Correo:");
        lblCorreo.setBounds(330,70,100,25);
        add(lblCorreo);

        txtCorreo = new JTextField();
        txtCorreo.setBounds(430,70,150,25);
        add(txtCorreo);



        JLabel lblSalario = new JLabel("Salario:");
        lblSalario.setBounds(330,110,100,25);
        add(lblSalario);

        txtSalario = new JTextField();
        txtSalario.setBounds(430,110,150,25);
        add(txtSalario);



        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(100,160,100,30);
        add(btnGuardar);


        JButton btnActualizar = new JButton("Actualizar");
        btnActualizar.setBounds(220,160,100,30);
        add(btnActualizar);


        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(340,160,100,30);
        add(btnEliminar);


        JButton btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setBounds(460,160,100,30);
        add(btnLimpiar);



        btnGuardar.addActionListener(e -> guardarEmpleado());

        btnActualizar.addActionListener(e -> actualizarEmpleado());

        btnEliminar.addActionListener(e -> eliminarEmpleado());

        btnLimpiar.addActionListener(e -> limpiar());



        modelo = new DefaultTableModel();

        modelo.addColumn("ID");
        modelo.addColumn("Nombre");
        modelo.addColumn("Apellido");
        modelo.addColumn("Cargo");
        modelo.addColumn("Teléfono");
        modelo.addColumn("Correo");
        modelo.addColumn("Salario");


        tablaEmpleados = new JTable(modelo);


        JScrollPane scroll = new JScrollPane(tablaEmpleados);
        scroll.setBounds(30,220,680,220);

        add(scroll);

        tablaEmpleados.getSelectionModel().addListSelectionListener(e -> {

            int fila = tablaEmpleados.getSelectedRow();

            if(fila >= 0){

                txtNombre.setText(modelo.getValueAt(fila,1).toString());
                txtApellido.setText(modelo.getValueAt(fila,2).toString());
                txtCargo.setText(modelo.getValueAt(fila,3).toString());
                txtTelefono.setText(modelo.getValueAt(fila,4).toString());
                txtCorreo.setText(modelo.getValueAt(fila,5).toString());
                txtSalario.setText(modelo.getValueAt(fila,6).toString());

            }

        });


    }


    private void guardarEmpleado(){

        if(txtNombre.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(this,"Ingrese el nombre");
            txtNombre.requestFocus();
            return;
        }

        if(txtApellido.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(this,"Ingrese el apellido");
            txtApellido.requestFocus();
            return;
        }

        if(txtCargo.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(this,"Ingrese el cargo");
            txtCargo.requestFocus();
            return;
        }

        if(txtTelefono.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(this,"Ingrese el teléfono");
            txtTelefono.requestFocus();
            return;
        }

        if(txtCorreo.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(this,"Ingrese el correo");
            txtCorreo.requestFocus();
            return;
        }

        if(txtSalario.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(this,"Ingrese el salario");
            txtSalario.requestFocus();
            return;
        }

        Empleado empleado = new Empleado();

        empleado.setNombre(txtNombre.getText());
        empleado.setApellido(txtApellido.getText());
        empleado.setCargo(txtCargo.getText());
        empleado.setTelefono(txtTelefono.getText());
        empleado.setCorreo(txtCorreo.getText());
        empleado.setSalario(Double.parseDouble(txtSalario.getText()));

        if(empleadoDAO.guardar(empleado)){

            JOptionPane.showMessageDialog(this,
                    "Empleado guardado correctamente");

            listarEmpleados();
            limpiar();

        }else{

            JOptionPane.showMessageDialog(this,
                    "Error al guardar el empleado");

        }



    }



    private void listarEmpleados(){

        modelo.setRowCount(0);

        List<Empleado> lista = empleadoDAO.listar();


        for(Empleado e : lista){

            modelo.addRow(new Object[]{

                    e.getIdEmpleado(),
                    e.getNombre(),
                    e.getApellido(),
                    e.getCargo(),
                    e.getTelefono(),
                    e.getCorreo(),
                    e.getSalario()

            });

        }

    }



    private void actualizarEmpleado(){

        int fila = tablaEmpleados.getSelectedRow();


        if(fila == -1){

            JOptionPane.showMessageDialog(this,
                    "Seleccione un empleado");

            return;

        }


        Empleado empleado = new Empleado();


        empleado.setIdEmpleado(
                Integer.parseInt(modelo.getValueAt(fila,0).toString())
        );


        empleado.setNombre(txtNombre.getText());
        empleado.setApellido(txtApellido.getText());
        empleado.setCargo(txtCargo.getText());
        empleado.setTelefono(txtTelefono.getText());
        empleado.setCorreo(txtCorreo.getText());
        empleado.setSalario(
                Double.parseDouble(txtSalario.getText())
        );


        empleadoDAO.actualizar(empleado);

        listarEmpleados();
        limpiar();

    }



    private void eliminarEmpleado(){

        int fila = tablaEmpleados.getSelectedRow();


        if(fila == -1){

            JOptionPane.showMessageDialog(this,
                    "Seleccione un empleado");

            return;

        }


        int id = Integer.parseInt(
                modelo.getValueAt(fila,0).toString()
        );


        empleadoDAO.eliminar(id);

        listarEmpleados();
        limpiar();

    }



    private void limpiar(){

        txtNombre.setText("");
        txtApellido.setText("");
        txtCargo.setText("");
        txtTelefono.setText("");
        txtCorreo.setText("");
        txtSalario.setText("");

    }

}