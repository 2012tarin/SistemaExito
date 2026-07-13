package com.exito.modelo;

import java.sql.Date;

public class Venta {

    private int idVenta;
    private Date fecha;
    private int idCliente;
    private int idEmpleado;
    private double total;

    public Venta() {
    }

    public Venta(int idVenta, Date fecha,
                 int idCliente,
                 int idEmpleado,
                 double total) {

        this.idVenta = idVenta;
        this.fecha = fecha;
        this.idCliente = idCliente;
        this.idEmpleado = idEmpleado;
        this.total = total;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Venta N° " + idVenta;
    }
}