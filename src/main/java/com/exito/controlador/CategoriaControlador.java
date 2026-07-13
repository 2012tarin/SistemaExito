package com.exito.controlador;

import com.exito.dao.CategoriaDAO;
import com.exito.modelo.Categoria;

import java.util.List;


public class CategoriaControlador {


    CategoriaDAO dao = new CategoriaDAO();



    public boolean guardar(Categoria categoria){

        return dao.guardar(categoria);

    }



    public List<Categoria> listar(){

        return dao.listar();

    }


}