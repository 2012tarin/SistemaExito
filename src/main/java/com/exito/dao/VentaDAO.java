package com.exito.dao;

import com.exito.conexion.Conexion;
import com.exito.modelo.Venta;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VentaDAO {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    // Guardar Venta
    public int guardar(Venta venta){

        int idGenerado = 0;

        String sql = "INSERT INTO ventas(fecha,id_cliente,id_empleado,total) VALUES(?,?,?,?)";


        try {

            con = Conexion.conectar();

            ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);


            ps.setDate(1, venta.getFecha());
            ps.setInt(2, venta.getIdCliente());
            ps.setInt(3, venta.getIdEmpleado());
            ps.setDouble(4, venta.getTotal());


            ps.executeUpdate();


            rs = ps.getGeneratedKeys();


            if(rs.next()){

                idGenerado = rs.getInt(1);

            }


        } catch(SQLException e){

            System.out.println(e.getMessage());

        }


        return idGenerado;

    }

    // Listar
    public List<Venta> listar() {

        List<Venta> lista = new ArrayList<>();

        String sql = "SELECT * FROM ventas";

        try {

            con = Conexion.conectar();

            ps = con.prepareStatement(sql);

            rs = ps.executeQuery();

            while (rs.next()) {

                Venta v = new Venta();

                v.setIdVenta(rs.getInt("id_venta"));
                v.setFecha(rs.getDate("fecha"));
                v.setIdCliente(rs.getInt("id_cliente"));
                v.setIdEmpleado(rs.getInt("id_empleado"));
                v.setTotal(rs.getDouble("total"));

                lista.add(v);

            }

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        }

        return lista;

    }

    // Buscar
    public Venta buscar(int id) {

        Venta venta = null;

        String sql = "SELECT * FROM ventas WHERE id_venta=?";

        try {

            con = Conexion.conectar();

            ps = con.prepareStatement(sql);

            ps.setInt(1, id);

            rs = ps.executeQuery();

            if (rs.next()) {

                venta = new Venta();

                venta.setIdVenta(rs.getInt("id_venta"));
                venta.setFecha(rs.getDate("fecha"));
                venta.setIdCliente(rs.getInt("id_cliente"));
                venta.setIdEmpleado(rs.getInt("id_empleado"));
                venta.setTotal(rs.getDouble("total"));

            }

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        }

        return venta;

    }

    // Actualizar
    public boolean actualizar(Venta venta) {

        String sql = "UPDATE ventas SET fecha=?,id_cliente=?,id_empleado=?,total=? WHERE id_venta=?";

        try {

            con = Conexion.conectar();

            ps = con.prepareStatement(sql);

            ps.setDate(1, venta.getFecha());
            ps.setInt(2, venta.getIdCliente());
            ps.setInt(3, venta.getIdEmpleado());
            ps.setDouble(4, venta.getTotal());
            ps.setInt(5, venta.getIdVenta());

            ps.executeUpdate();

            return true;

        } catch (SQLException e) {

            System.out.println(e.getMessage());

            return false;

        }

    }

    // Eliminar
    public boolean eliminar(int id) {

        String sql = "DELETE FROM ventas WHERE id_venta=?";

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