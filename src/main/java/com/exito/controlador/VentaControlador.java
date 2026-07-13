package com.exito.controlador;

import com.exito.dao.VentaDAO;
import com.exito.modelo.Venta;

import java.util.List;

public class VentaControlador {

    private VentaDAO dao = new VentaDAO();

    public int guardar(Venta venta){
        return dao.guardar(venta);
    }

    public List<Venta> listar(){
        return dao.listar();
    }

    public Venta buscar(int id){
        return dao.buscar(id);
    }

    public boolean actualizar(Venta venta){
        return dao.actualizar(venta);
    }

    public boolean eliminar(int id){
        return dao.eliminar(id);
    }

}