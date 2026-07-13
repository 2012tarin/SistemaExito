package com.exito.controlador;

import com.exito.dao.EmpleadoDAO;
import com.exito.modelo.Empleado;

import java.util.List;

public class EmpleadoControlador {

    private EmpleadoDAO dao = new EmpleadoDAO();

    public boolean guardar(Empleado empleado){
        return dao.guardar(empleado);
    }

    public List<Empleado> listar(){
        return dao.listar();
    }

    public Empleado buscar(int id){
        return dao.buscar(id);
    }

    public boolean actualizar(Empleado empleado){
        return dao.actualizar(empleado);
    }

    public boolean eliminar(int id){
        return dao.eliminar(id);
    }

}