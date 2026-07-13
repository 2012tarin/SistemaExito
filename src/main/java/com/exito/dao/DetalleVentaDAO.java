package com.exito.dao;

import com.exito.conexion.Conexion;
import com.exito.modelo.DetalleVenta;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DetalleVentaDAO {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    // Guardar
    public boolean guardar(DetalleVenta detalle) {

        String sql = "INSERT INTO detalle_venta(id_venta,id_producto,cantidad,precio_unitario,subtotal) VALUES(?,?,?,?,?)";

        try {

            con = Conexion.conectar();

            ps = con.prepareStatement(sql);

            ps.setInt(1, detalle.getIdVenta());
            ps.setInt(2, detalle.getIdProducto());
            ps.setInt(3, detalle.getCantidad());
            ps.setDouble(4, detalle.getPrecioUnitario());
            ps.setDouble(5, detalle.getSubtotal());

            ps.executeUpdate();

            return true;

        } catch (SQLException e) {

            System.out.println(e.getMessage());

            return false;

        }

    }

    // Listar
    public List<DetalleVenta> listar() {

        List<DetalleVenta> lista = new ArrayList<>();

        String sql = "SELECT * FROM detalle_venta";

        try {

            con = Conexion.conectar();

            ps = con.prepareStatement(sql);

            rs = ps.executeQuery();

            while (rs.next()) {

                DetalleVenta d = new DetalleVenta();

                d.setIdDetalle(rs.getInt("id_detalle"));
                d.setIdVenta(rs.getInt("id_venta"));
                d.setIdProducto(rs.getInt("id_producto"));
                d.setCantidad(rs.getInt("cantidad"));
                d.setPrecioUnitario(rs.getDouble("precio_unitario"));
                d.setSubtotal(rs.getDouble("subtotal"));

                lista.add(d);

            }

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        }

        return lista;

    }

    // Buscar
    public DetalleVenta buscar(int id) {

        DetalleVenta detalle = null;

        String sql = "SELECT * FROM detalle_venta WHERE id_detalle=?";

        try {

            con = Conexion.conectar();

            ps = con.prepareStatement(sql);

            ps.setInt(1, id);

            rs = ps.executeQuery();

            if (rs.next()) {

                detalle = new DetalleVenta();

                detalle.setIdDetalle(rs.getInt("id_detalle"));
                detalle.setIdVenta(rs.getInt("id_venta"));
                detalle.setIdProducto(rs.getInt("id_producto"));
                detalle.setCantidad(rs.getInt("cantidad"));
                detalle.setPrecioUnitario(rs.getDouble("precio_unitario"));
                detalle.setSubtotal(rs.getDouble("subtotal"));

            }

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        }

        return detalle;

    }

    // Actualizar
    public boolean actualizar(DetalleVenta detalle) {

        String sql = "UPDATE detalle_venta SET id_venta=?,id_producto=?,cantidad=?,precio_unitario=?,subtotal=? WHERE id_detalle=?";

        try {

            con = Conexion.conectar();

            ps = con.prepareStatement(sql);

            ps.setInt(1, detalle.getIdVenta());
            ps.setInt(2, detalle.getIdProducto());
            ps.setInt(3, detalle.getCantidad());
            ps.setDouble(4, detalle.getPrecioUnitario());
            ps.setDouble(5, detalle.getSubtotal());
            ps.setInt(6, detalle.getIdDetalle());

            ps.executeUpdate();

            return true;

        } catch (SQLException e) {

            System.out.println(e.getMessage());

            return false;

        }

    }

    // Eliminar
    public boolean eliminar(int id) {

        String sql = "DELETE FROM detalle_venta WHERE id_detalle=?";

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

}