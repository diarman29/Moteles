/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.diego.entidades;

import java.io.Serializable;


/**
 *
 * @author DIEGO
 */

public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;

    private String codigoCliente;

    private String nombreCliente;

    private String apellidoCliente;

    private String direccionCliente;

    private String telefonoCliente;

    private String correoCliente;

    //private List<Reserva> reservaList;

    public Cliente() {
    }

    public Cliente(String codigoCliente, String nombreCliente, String apellidoCliente, String direccionCliente, String telefonoCliente, String correoCliente) {
        this.codigoCliente=codigoCliente;
        this.nombreCliente=nombreCliente;
        this.apellidoCliente=apellidoCliente;
        this.direccionCliente=direccionCliente;
        this.telefonoCliente=telefonoCliente;
        this.correoCliente=correoCliente;
    }

    public Cliente(String codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public Cliente(String codigoCliente, String nombreCliente, String apellidoCliente, String direccionCliente) {
        this.codigoCliente = codigoCliente;
        this.nombreCliente = nombreCliente;
        this.apellidoCliente = apellidoCliente;
        this.direccionCliente = direccionCliente;
    }

    public String getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(String codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getApellidoCliente() {
        return apellidoCliente;
    }

    public void setApellidoCliente(String apellidoCliente) {
        this.apellidoCliente = apellidoCliente;
    }

    public String getDireccionCliente() {
        return direccionCliente;
    }

    public void setDireccionCliente(String direccionCliente) {
        this.direccionCliente = direccionCliente;
    }

    public String getTelefonoCliente() {
        return telefonoCliente;
    }

    public void setTelefonoCliente(String telefonoCliente) {
        this.telefonoCliente = telefonoCliente;
    }

    public String getCorreoCliente() {
        return correoCliente;
    }

    public void setCorreoCliente(String correoCliente) {
        this.correoCliente = correoCliente;
    }

    /*@XmlTransient
    public List<Reserva> getReservaList() {
        return reservaList;
    }

    public void setReservaList(List<Reserva> reservaList) {
        this.reservaList = reservaList;
    }*/

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoCliente != null ? codigoCliente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cliente)) {
            return false;
        }
        Cliente other = (Cliente) object;
        if ((this.codigoCliente == null && other.codigoCliente != null) || (this.codigoCliente != null && !this.codigoCliente.equals(other.codigoCliente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.nombreCliente + " " + this.apellidoCliente;
    }
    
}
