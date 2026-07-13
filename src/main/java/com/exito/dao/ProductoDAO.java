package com.exito.dao;

import com.exito.conexion.Conexion;
import com.exito.modelo.Producto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    // Guardar Producto
    public boolean guardar(Producto producto) {

        String sql = "INSERT INTO productos(nombre,marca,precio,stock,id_categoria) VALUES(?,?,?,?,?)";

        try {

            con = Conexion.conectar();

            ps = con.prepareStatement(sql);

            ps.setString(1, producto.getNombre());
            ps.setString(2, producto.getMarca());
            ps.setDouble(3, producto.getPrecio());
            ps.setInt(4, producto.getStock());
            ps.setInt(5, producto.getIdCategoria());

            ps.executeUpdate();

            return true;

        } catch (SQLException e) {

            System.out.println("Error: " + e.getMessage());

            return false;

        }

    }

    // Listar Productos
    public List<Producto> listar() {

        List<Producto> lista = new ArrayList<>();

        String sql = "SELECT * FROM productos";

        try {

            con = Conexion.conectar();

            ps = con.prepareStatement(sql);

            rs = ps.executeQuery();

            while (rs.next()) {

                Producto p = new Producto();

                p.setIdProducto(rs.getInt("id_producto"));
                p.setNombre(rs.getString("nombre"));
                p.setMarca(rs.getString("marca"));
                p.setPrecio(rs.getDouble("precio"));
                p.setStock(rs.getInt("stock"));
                p.setIdCategoria(rs.getInt("id_categoria"));

                lista.add(p);

            }

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        }

        return lista;

    }

    // Buscar Producto
    public Producto buscar(int id) {

        Producto producto = null;

        String sql = "SELECT * FROM productos WHERE id_producto=?";

        try {

            con = Conexion.conectar();

            ps = con.prepareStatement(sql);

            ps.setInt(1, id);

            rs = ps.executeQuery();

            if (rs.next()) {

                producto = new Producto();

                producto.setIdProducto(rs.getInt("id_producto"));
                producto.setNombre(rs.getString("nombre"));
                producto.setMarca(rs.getString("marca"));
                producto.setPrecio(rs.getDouble("precio"));
                producto.setStock(rs.getInt("stock"));
                producto.setIdCategoria(rs.getInt("id_categoria"));

            }

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        }

        return producto;

    }

    // Actualizar Producto
    public boolean actualizar(Producto producto) {

        String sql = "UPDATE productos SET nombre=?,marca=?,precio=?,stock=?,id_categoria=? WHERE id_producto=?";

        try {

            con = Conexion.conectar();

            ps = con.prepareStatement(sql);

            ps.setString(1, producto.getNombre());
            ps.setString(2, producto.getMarca());
            ps.setDouble(3, producto.getPrecio());
            ps.setInt(4, producto.getStock());
            ps.setInt(5, producto.getIdCategoria());
            ps.setInt(6, producto.getIdProducto());

            ps.executeUpdate();

            return true;

        } catch (SQLException e) {

            System.out.println(e.getMessage());

            return false;

        }

    }

    // Eliminar Producto
    public boolean eliminar(int id) {

        String sql = "DELETE FROM productos WHERE id_producto=?";

        try {

            con = Conexion.conectar();

            ps = con.prepareStatement(sql);

            ps.setInt(1, id);

            ps.executeUpdate();

            return true;

        } catch (SQLException e) {

            System.out.println(e.getMessage());

            return false;

        }

    }

    // Actualizar stock del producto
    public boolean actualizarStock(int idProducto, int cantidad) {

        String sql = "UPDATE productos SET stock = stock - ? WHERE id_producto = ?";

        try {

            con = Conexion.conectar();

            ps = con.prepareStatement(sql);

            ps.setInt(1, cantidad);
            ps.setInt(2, idProducto);

            ps.executeUpdate();

            return true;

        } catch (SQLException e) {

            System.out.println(e.getMessage());

            return false;
        }

    }

}