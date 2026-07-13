package com.exito.controlador;

import com.exito.dao.ClienteDAO;
import com.exito.modelo.Cliente;

import java.util.List;

public class ClienteControlador {

    private ClienteDAO dao = new ClienteDAO();

    // Guardar
    public boolean guardar(Cliente cliente) {
        return dao.guardar(cliente);
    }

    // Listar
    public List<Cliente> listar() {
        return dao.listar();
    }

    // Actualizar
    public boolean actualizar(Cliente cliente) {
        return dao.actualizar(cliente);
    }

    // Eliminar
    public boolean eliminar(int id) {
        return dao.eliminar(id);
    }
}