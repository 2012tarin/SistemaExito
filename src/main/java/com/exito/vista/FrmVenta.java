package com.exito.vista;

import com.exito.dao.ClienteDAO;
import com.exito.dao.ProductoDAO;
import com.exito.modelo.Cliente;
import com.exito.modelo.Producto;
import javax.swing.table.DefaultTableModel;
import com.exito.dao.VentaDAO;
import com.exito.dao.DetalleVentaDAO;
import com.exito.modelo.DetalleVenta;

import java.sql.Date;
import java.util.ArrayList;

import javax.swing.*;
import java.util.List;

import com.exito.modelo.Venta;
import com.exito.modelo.DetalleVenta;
import com.exito.dao.VentaDAO;
import com.exito.dao.DetalleVentaDAO;

public class FrmVenta extends JFrame {


    JComboBox<Cliente> cboCliente;
    JComboBox<Producto> cboProducto;

    JTextField txtCantidad;
    JTextField txtPrecio;
    JTextField txtSubtotal;

    JTable tablaDetalle;
    DefaultTableModel modelo;

    double totalVenta = 0;
    List<DetalleVenta> detalles = new ArrayList<>();


    ClienteDAO clienteDAO = new ClienteDAO();
    ProductoDAO productoDAO = new ProductoDAO();


    public FrmVenta(){

        setTitle("Ventas - Sistema Éxito");
        setSize(800,600);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        iniciarComponentes();

        cargarClientes();
        cargarProductos();

    }


    private void iniciarComponentes(){


        JLabel lblCliente = new JLabel("Cliente:");
        lblCliente.setBounds(30,30,100,25);
        add(lblCliente);


        cboCliente = new JComboBox<>();
        cboCliente.setBounds(130,30,200,25);
        add(cboCliente);



        JLabel lblProducto = new JLabel("Producto:");
        lblProducto.setBounds(30,70,100,25);
        add(lblProducto);


        cboProducto = new JComboBox<>();
        cboProducto.setBounds(130,70,200,25);
        add(cboProducto);

        cboProducto.addActionListener(e -> {

            Producto producto = (Producto)cboProducto.getSelectedItem();

            if(producto != null){

                txtPrecio.setText(
                        String.valueOf(producto.getPrecio())
                );

            }

        });



        JLabel lblCantidad = new JLabel("Cantidad:");
        lblCantidad.setBounds(30,110,100,25);
        add(lblCantidad);


        txtCantidad = new JTextField();
        txtCantidad.setBounds(130,110,100,25);
        add(txtCantidad);



        JLabel lblPrecio = new JLabel("Precio:");
        lblPrecio.setBounds(350,70,100,25);
        add(lblPrecio);


        txtPrecio = new JTextField();
        txtPrecio.setBounds(450,70,120,25);
        txtPrecio.setEditable(false);
        add(txtPrecio);



        JLabel lblSubtotal = new JLabel("Subtotal:");
        lblSubtotal.setBounds(350,110,100,25);
        add(lblSubtotal);


        txtSubtotal = new JTextField();
        txtSubtotal.setBounds(450,110,120,25);
        txtSubtotal.setEditable(false);
        add(txtSubtotal);

        // Botón Agregar producto
        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.setBounds(600,70,100,30);
        add(btnAgregar);

        btnAgregar.addActionListener(e -> agregarProducto());


// Tabla detalle de venta
        modelo = new DefaultTableModel();

        modelo.addColumn("Producto");
        modelo.addColumn("Cantidad");
        modelo.addColumn("Precio");
        modelo.addColumn("Subtotal");


        tablaDetalle = new JTable(modelo);

        JScrollPane scroll = new JScrollPane(tablaDetalle);
        scroll.setBounds(30,200,700,200);

        add(scroll);


// Botón Guardar Venta
        JButton btnGuardarVenta = new JButton("Guardar Venta");
        btnGuardarVenta.setBounds(300,430,150,35);
        add(btnGuardarVenta);

        btnGuardarVenta.addActionListener(e -> guardarVenta());


    }



    private void cargarClientes(){

        List<Cliente> lista = clienteDAO.listar();


        for(Cliente c : lista){

            cboCliente.addItem(c);

        }

    }



    private void cargarProductos(){

        List<Producto> lista = productoDAO.listar();


        for(Producto p : lista){

            cboProducto.addItem(p);

        }

    }


    private void agregarProducto(){

        Producto producto = (Producto)cboProducto.getSelectedItem();

        int cantidad = Integer.parseInt(txtCantidad.getText());

        double precio = producto.getPrecio();

        double subtotal = cantidad * precio;


        modelo.addRow(new Object[]{

                producto.getNombre(),
                cantidad,
                precio,
                subtotal

        });


        totalVenta = totalVenta + subtotal;


        txtSubtotal.setText(String.valueOf(subtotal));

    }





    private void guardarVenta(){

        if(modelo.getRowCount() == 0){

            JOptionPane.showMessageDialog(this,
                    "Agregue productos a la venta");

            return;
        }


        Cliente cliente = (Cliente)cboCliente.getSelectedItem();


        Venta venta = new Venta();

        venta.setFecha(new java.sql.Date(System.currentTimeMillis()));
        venta.setIdCliente(cliente.getIdCliente());
        venta.setIdEmpleado(1);
        venta.setTotal(totalVenta);


        VentaDAO ventaDAO = new VentaDAO();

        int idVenta = ventaDAO.guardar(venta);


        if(idVenta > 0){

            DetalleVentaDAO detalleDAO = new DetalleVentaDAO();


            for(int i = 0; i < modelo.getRowCount(); i++){

                DetalleVenta detalle = new DetalleVenta();

                detalle.setIdVenta(idVenta);

                Producto producto = (Producto)cboProducto.getSelectedItem();

                detalle.setIdProducto(producto.getIdProducto());

                detalle.setCantidad(
                        Integer.parseInt(modelo.getValueAt(i,1).toString())
                );

                detalle.setPrecioUnitario(
                        Double.parseDouble(modelo.getValueAt(i,2).toString())
                );

                detalle.setSubtotal(
                        Double.parseDouble(modelo.getValueAt(i,3).toString())
                );


                detalleDAO.guardar(detalle);

                productoDAO.actualizarStock(
                        producto.getIdProducto(),
                        detalle.getCantidad()
                );

            }


            JOptionPane.showMessageDialog(this,
                    "Venta guardada correctamente");


            modelo.setRowCount(0);
            totalVenta = 0;


        }else{

            JOptionPane.showMessageDialog(this,
                    "Error al guardar venta");

        }

    }

}