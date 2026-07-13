package com.exito.dao;

import com.exito.conexion.Conexion;
import com.exito.modelo.Empleado;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoDAO {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    // Guardar
    public boolean guardar(Empleado empleado) {

        String sql = "INSERT INTO empleados(nombre,apellido,cargo,telefono,correo,salario) VALUES(?,?,?,?,?,?)";

        try {

            con = Conexion.conectar();

            ps = con.prepareStatement(sql);

            ps.setString(1, empleado.getNombre());
            ps.setString(2, empleado.getApellido());
            ps.setString(3, empleado.getCargo());
            ps.setString(4, empleado.getTelefono());
            ps.setString(5, empleado.getCorreo());
            ps.setDouble(6, empleado.getSalario());

            ps.executeUpdate();

            return true;

        } catch (SQLException e) {

            System.out.println(e.getMessage());

            return false;

        }

    }

    // Listar
    public List<Empleado> listar() {

        List<Empleado> lista = new ArrayList<>();

        String sql = "SELECT * FROM empleados";

        try {

            con = Conexion.conectar();

            ps = con.prepareStatement(sql);

            rs = ps.executeQuery();

            while (rs.next()) {

                Empleado e = new Empleado();

                e.setIdEmpleado(rs.getInt("id_empleado"));
                e.setNombre(rs.getString("nombre"));
                e.setApellido(rs.getString("apellido"));
                e.setCargo(rs.getString("cargo"));
                e.setTelefono(rs.getString("telefono"));
                e.setCorreo(rs.getString("correo"));
                e.setSalario(rs.getDouble("salario"));

                lista.add(e);

            }

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        }

        return lista;

    }

    // Buscar
    public Empleado buscar(int id) {

        Empleado empleado = null;

        String sql = "SELECT * FROM empleados WHERE id_empleado=?";

        try {

            con = Conexion.conectar();

            ps = con.prepareStatement(sql);

            ps.setInt(1, id);

            rs = ps.executeQuery();

            if (rs.next()) {

                empleado = new Empleado();

                empleado.setIdEmpleado(rs.getInt("id_empleado"));
                empleado.setNombre(rs.getString("nombre"));
                empleado.setApellido(rs.getString("apellido"));
                empleado.setCargo(rs.getString("cargo"));
                empleado.setTelefono(rs.getString("telefono"));
                empleado.setCorreo(rs.getString("correo"));
                empleado.setSalario(rs.getDouble("salario"));

            }

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        }

        return empleado;

    }

    // Actualizar
    public boolean actualizar(Empleado empleado) {

        String sql = "UPDATE empleados SET nombre=?,apellido=?,cargo=?,telefono=?,correo=?,salario=? WHERE id_empleado=?";

        try {

            con = Conexion.conectar();

            ps = con.prepareStatement(sql);

            ps.setString(1, empleado.getNombre());
            ps.setString(2, empleado.getApellido());
            ps.setString(3, empleado.getCargo());
            ps.setString(4, empleado.getTelefono());
            ps.setString(5, empleado.getCorreo());
            ps.setDouble(6, empleado.getSalario());
            ps.setInt(7, empleado.getIdEmpleado());

            ps.executeUpdate();

            return true;

        } catch (SQLException e) {

            System.out.println(e.getMessage());

            return false;

        }

    }

    // Eliminar
    public boolean eliminar(int id) {

        String sql = "DELETE FROM empleados WHERE id_empleado=?";

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