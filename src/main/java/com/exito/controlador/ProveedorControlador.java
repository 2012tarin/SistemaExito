package com.exito.controlador;

import com.exito.dao.ProveedorDAO;
import com.exito.modelo.Proveedor;

import java.util.List;

public class ProveedorControlador {

    ProveedorDAO proveedorDAO = new ProveedorDAO();


    // Guardar proveedor
    public boolean guardar(Proveedor proveedor){

        return proveedorDAO.guardar(proveedor);

    }


    // Listar proveedores
    public List<Proveedor> listar(){

        return proveedorDAO.listar();

    }


    // Actualizar proveedor
    public boolean actualizar(Proveedor proveedor){

        return proveedorDAO.actualizar(proveedor);

    }


    // Eliminar proveedor
    public boolean eliminar(int id){

        return proveedorDAO.eliminar(id);

    }

}