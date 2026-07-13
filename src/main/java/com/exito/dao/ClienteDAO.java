package com.exito.dao;

import com.exito.conexion.Conexion;
import com.exito.modelo.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    // Guardar cliente
    public boolean guardar(Cliente cliente) {

        String sql = "INSERT INTO clientes(nombre, apellido, cedula, telefono, direccion, correo) VALUES(?,?,?,?,?,?)";

        try {

            con = Conexion.conectar();
            ps = con.prepareStatement(sql);

            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido());
            ps.setString(3, cliente.getCedula());
            ps.setString(4, cliente.getTelefono());
            ps.setString(5, cliente.getDireccion());
            ps.setString(6, cliente.getCorreo());

            ps.executeUpdate();

            return true;

        } catch (Exception e) {

            System.out.println(e.getMessage());
            return false;

        }

    }

    // Listar clientes
    public List<Cliente> listar() {

        List<Cliente> lista = new ArrayList<>();

        String sql = "SELECT * FROM clientes";

        try {

            con = Conexion.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {

                Cliente c = new Cliente();

                c.setIdCliente(rs.getInt("id_cliente"));
                c.setNombre(rs.getString("nombre"));
                c.setApellido(rs.getString("apellido"));
                c.setCedula(rs.getString("cedula"));
                c.setTelefono(rs.getString("telefono"));
                c.setDireccion(rs.getString("direccion"));
                c.setCorreo(rs.getString("correo"));

                lista.add(c);

            }

        } catch (Exception e) {

            System.out.println(e.getMessage());

        }

        return lista;

    }

    // Actualizar cliente
    public boolean actualizar(Cliente cliente) {

        String sql = "UPDATE clientes SET nombre=?, apellido=?, cedula=?, telefono=?, direccion=?, correo=? WHERE id_cliente=?";

        try {

            con = Conexion.conectar();
            ps = con.prepareStatement(sql);

            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido());
            ps.setString(3, cliente.getCedula());
            ps.setString(4, cliente.getTelefono());
            ps.setString(5, cliente.getDireccion());
            ps.setString(6, cliente.getCorreo());
            ps.setInt(7, cliente.getIdCliente());

            ps.executeUpdate();

            return true;

        } catch (Exception e) {

            System.out.println(e.getMessage());
            return false;

        }

    }

    // Eliminar cliente
    public boolean eliminar(int id) {

        String sql = "DELETE FROM clientes WHERE id_cliente=?";

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