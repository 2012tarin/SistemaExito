package com.exito.vista;

import com.exito.dao.CategoriaDAO;
import com.exito.dao.ProductoDAO;
import com.exito.modelo.Categoria;
import com.exito.modelo.Producto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class FrmProducto extends JFrame {

    private JTextField txtNombre;
    private JTextField txtMarca;
    private JTextField txtPrecio;
    private JTextField txtStock;

    private JComboBox<Categoria> cbCategoria;

    private JButton btnGuardar;
    private JButton btnNuevo;
    private JButton btnEditar;
    private JButton btnEliminar;

    private JTable tablaProductos;
    private DefaultTableModel modelo;

    private ProductoDAO productoDAO = new ProductoDAO();
    private CategoriaDAO categoriaDAO = new CategoriaDAO();

    public FrmProducto() {

        initComponents();

        cargarCategorias();

        cargarTabla();
        btnGuardar.addActionListener(e -> guardar());

        btnNuevo.addActionListener(e -> limpiar());

    }

    private void initComponents() {

        setTitle("Gestión de Productos");
        setSize(900,500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(20,20,100,25);
        add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(120,20,180,25);
        add(txtNombre);

        JLabel lblMarca = new JLabel("Marca:");
        lblMarca.setBounds(20,60,100,25);
        add(lblMarca);

        txtMarca = new JTextField();
        txtMarca.setBounds(120,60,180,25);
        add(txtMarca);

        JLabel lblPrecio = new JLabel("Precio:");
        lblPrecio.setBounds(20,100,100,25);
        add(lblPrecio);

        txtPrecio = new JTextField();
        txtPrecio.setBounds(120,100,180,25);
        add(txtPrecio);

        JLabel lblStock = new JLabel("Stock:");
        lblStock.setBounds(20,140,100,25);
        add(lblStock);

        txtStock = new JTextField();
        txtStock.setBounds(120,140,180,25);
        add(txtStock);

        JLabel lblCategoria = new JLabel("Categoría:");
        lblCategoria.setBounds(20,180,100,25);
        add(lblCategoria);

        cbCategoria = new JComboBox<>();
        cbCategoria.setBounds(120,180,180,25);
        add(cbCategoria);

        btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(20,230,100,30);
        add(btnGuardar);

        btnNuevo = new JButton("Nuevo");
        btnNuevo.setBounds(130,230,100,30);
        add(btnNuevo);

        btnEditar = new JButton("Editar");
        btnEditar.setBounds(240,230,100,30);
        add(btnEditar);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(350,230,100,30);
        add(btnEliminar);

        modelo = new DefaultTableModel();

        modelo.addColumn("ID");
        modelo.addColumn("Nombre");
        modelo.addColumn("Marca");
        modelo.addColumn("Precio");
        modelo.addColumn("Stock");
        modelo.addColumn("Categoría");

        tablaProductos = new JTable(modelo);

        JScrollPane scroll = new JScrollPane(tablaProductos);
        scroll.setBounds(330,20,540,380);

        add(scroll);
    }
    // Cargar categorías en el JComboBox
    private void cargarCategorias() {

        cbCategoria.removeAllItems();

        List<Categoria> lista = categoriaDAO.listar();

        for (Categoria categoria : lista) {

            cbCategoria.addItem(categoria);

        }

    }

    // Cargar productos en la tabla
    private void cargarTabla() {

        modelo.setRowCount(0);

        List<Producto> lista = productoDAO.listar();

        for (Producto p : lista) {

            Categoria categoria = categoriaDAO.buscar(p.getIdCategoria());

            String nombreCategoria = "";

            if (categoria != null) {
                nombreCategoria = categoria.getNombre_categoria();
            }

            modelo.addRow(new Object[]{
                    p.getIdProducto(),
                    p.getNombre(),
                    p.getMarca(),
                    p.getPrecio(),
                    p.getStock(),
                    nombreCategoria
            });

        }

    }

    // Limpiar campos
    private void limpiar() {

        txtNombre.setText("");
        txtMarca.setText("");
        txtPrecio.setText("");
        txtStock.setText("");

        if (cbCategoria.getItemCount() > 0) {
            cbCategoria.setSelectedIndex(0);
        }

    }
    private void guardar() {

        try {

            Producto producto = new Producto();

            producto.setNombre(txtNombre.getText());

            producto.setMarca(txtMarca.getText());

            producto.setPrecio(Double.parseDouble(txtPrecio.getText()));

            producto.setStock(Integer.parseInt(txtStock.getText()));

            Categoria categoria = (Categoria) cbCategoria.getSelectedItem();

            producto.setIdCategoria(categoria.getId_categoria());

            if (productoDAO.guardar(producto)) {

                JOptionPane.showMessageDialog(this, "Producto guardado correctamente");

                limpiar();

                cargarTabla();

            } else {

                JOptionPane.showMessageDialog(this, "Error al guardar");

            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(this, e.getMessage());

        }

    }
}
