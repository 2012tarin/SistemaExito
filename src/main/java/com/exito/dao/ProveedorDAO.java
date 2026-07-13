package com.exito.dao;

import com.exito.conexion.Conexion;
import com.exito.modelo.Proveedor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProveedorDAO {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;


    // Guardar proveedor
    public boolean guardar(Proveedor proveedor) {

        String sql = "INSERT INTO proveedores(nombre, empresa, telefono, direccion, correo) VALUES(?,?,?,?,?)";

        try {

            con = Conexion.conectar();
            ps = con.prepareStatement(sql);

            ps.setString(1, proveedor.getNombre());
            ps.setString(2, proveedor.getEmpresa());
            ps.setString(3, proveedor.getTelefono());
            ps.setString(4, proveedor.getDireccion());
            ps.setString(5, proveedor.getCorreo());

            ps.executeUpdate();

            return true;

        } catch (Exception e) {

            System.out.println(e.getMessage());
            return false;

        }

    }


    // Listar proveedores
    public List<Proveedor> listar() {

        List<Proveedor> lista = new ArrayList<>();

        String sql = "SELECT * FROM proveedores";

        try {

            con = Conexion.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();


            while (rs.next()) {

                Proveedor p = new Proveedor();

                p.setIdProveedor(rs.getInt("id_proveedor"));
                p.setNombre(rs.getString("nombre"));
                p.setEmpresa(rs.getString("empresa"));
                p.setTelefono(rs.getString("telefono"));
                p.setDireccion(rs.getString("direccion"));
                p.setCorreo(rs.getString("correo"));

                lista.add(p);

            }

        } catch (Exception e) {

            System.out.println(e.getMessage());

        }

        return lista;

    }


    // Actualizar proveedor
    public boolean actualizar(Proveedor proveedor) {

        String sql = "UPDATE proveedores SET nombre=?, empresa=?, telefono=?, direccion=?, correo=? WHERE id_proveedor=?";


        try {

            con = Conexion.conectar();
            ps = con.prepareStatement(sql);

            ps.setString(1, proveedor.getNombre());
            ps.setString(2, proveedor.getEmpresa());
            ps.setString(3, proveedor.getTelefono());
            ps.setString(4, proveedor.getDireccion());
            ps.setString(5, proveedor.getCorreo());
            ps.setInt(6, proveedor.getIdProveedor());

            ps.executeUpdate();

            return true;

        } catch (Exception e) {

            System.out.println(e.getMessage());
            return false;

        }

    }


    // Eliminar proveedor
    public boolean eliminar(int id) {

        String sql = "DELETE FROM proveedores WHERE id_proveedor=?";


        try {

            con = Conexion.conectar();
            ps = con.prepareStatement(sql);

            ps.setInt(1, id);

            ps.executeUpdate();

            return true;

        } catch (Exception e) {

            System.out.println(e.getMessage());
            return false;

        }

    }

}