package com.exito.controlador;

import com.exito.dao.DetalleVentaDAO;
import com.exito.modelo.DetalleVenta;

import java.util.List;

public class DetalleVentaControlador {

    private DetalleVentaDAO dao = new DetalleVentaDAO();

    public boolean guardar(DetalleVenta detalle){
        return dao.guardar(detalle);
    }

    public List<DetalleVenta> listar(){
        return dao.listar();
    }

    public DetalleVenta buscar(int id){
        return dao.buscar(id);
    }

    public boolean actualizar(DetalleVenta detalle){
        return dao.actualizar(detalle);
    }

    public boolean eliminar(int id){
        return dao.eliminar(id);
    }

}