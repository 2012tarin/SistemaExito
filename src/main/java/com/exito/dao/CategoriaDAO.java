package com.exito.dao;

import com.exito.conexion.Conexion;
import com.exito.modelo.Categoria;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class CategoriaDAO {


    Connection con;
    PreparedStatement ps;
    ResultSet rs;


    // GUARDAR CATEGORIA
    public boolean guardar(Categoria categoria) {

        String sql = "INSERT INTO categorias(nombre_categoria, descripcion) VALUES (?, ?)";


        try {

            con = Conexion.conectar();

            ps = con.prepareStatement(sql);

            ps.setString(1, categoria.getNombre_categoria());

            ps.setString(2, categoria.getDescripcion());


            ps.executeUpdate();


            return true;


        } catch (SQLException e) {

            System.out.println(e.getMessage());

            return false;

        }

    }



    // LISTAR CATEGORIAS
    public List<Categoria> listar() {


        List<Categoria> lista = new ArrayList<>();


        String sql = "SELECT * FROM categorias";


        try {


            con = Conexion.conectar();

            ps = con.prepareStatement(sql);

            rs = ps.executeQuery();



            while (rs.next()) {


                Categoria categoria = new Categoria();


                categoria.setId_categoria(
                        rs.getInt("id_categoria")
                );


                categoria.setNombre_categoria(
                        rs.getString("nombre_categoria")
                );


                categoria.setDescripcion(
                        rs.getString("descripcion")
                );



                lista.add(categoria);


            }



        } catch (SQLException e) {


            System.out.println(e.getMessage());


        }


        return lista;


    }



    // ELIMINAR CATEGORIA
    public boolean eliminar(int id_categoria) {


        String sql = "DELETE FROM categorias WHERE id_categoria=?";


        try {


            con = Conexion.conectar();

            ps = con.prepareStatement(sql);


            ps.setInt(1, id_categoria);


            ps.executeUpdate();


            return true;


        } catch(SQLException e) {


            System.out.println(e.getMessage());

            return false;


        }


    }


    // BUSCAR CATEGORIA POR ID
    public Categoria buscar(int id_categoria) {

        Categoria categoria = null;

        String sql = "SELECT * FROM categorias WHERE id_categoria=?";

        try {

            con = Conexion.conectar();

            ps = con.prepareStatement(sql);

            ps.setInt(1, id_categoria);

            rs = ps.executeQuery();

            if (rs.next()) {

                categoria = new Categoria();

                categoria.setId_categoria(rs.getInt("id_categoria"));
                categoria.setNombre_categoria(rs.getString("nombre_categoria"));
                categoria.setDescripcion(rs.getString("descripcion"));

            }

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        }

        return categoria;
    }
}