package com.exito.controlador;

import com.exito.dao.ProductoDAO;
import com.exito.modelo.Producto;

import java.util.List;

public class ProductoControlador {

    private ProductoDAO dao = new ProductoDAO();

    public boolean guardar(Producto producto){
        return dao.guardar(producto);
    }

    public List<Producto> listar(){
        return dao.listar();
    }

    public Producto buscar(int id){
        return dao.buscar(id);
    }

    public boolean actualizar(Producto producto){
        return dao.actualizar(producto);
    }

    public boolean eliminar(int id){
        return dao.eliminar(id);
    }

}