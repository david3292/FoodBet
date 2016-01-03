/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espe.distribuidas.foodbet.modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author david
 */
@Entity
@Table(name = "SUCURSAL")
public class Sucursal implements Serializable{
    
    @Id
    @Column(name = "COD_SUCURSAL", nullable = false)
    private Integer codSucursal;
    
    @Column(name = "COD_RESTAURANTE", nullable = false)
    private Integer codRestaurante;
    
    @JoinColumn(name = "COD_RESTAURANTE", referencedColumnName = "COD_RESTAURANTE", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Restaurante restaurante;
    
    @Column(name = "DIRECCION", nullable = false)
    private String direccion;
    
    @Column(name = "TELEFONO1", nullable = false)
    private String telefono1;
    
    @Column(name = "TELEFONO2", nullable = true)
    private String telefono2;

    public Integer getCodSucursal() {
        return codSucursal;
    }

    public void setCodSucursal(Integer codSucursal) {
        this.codSucursal = codSucursal;
    }

    public Integer getCodRestaurante() {
        return codRestaurante;
    }

    public void setCodRestaurante(Integer codRestaurante) {
        this.codRestaurante = codRestaurante;
    }

    public Restaurante getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono1() {
        return telefono1;
    }

    public void setTelefono1(String telefono1) {
        this.telefono1 = telefono1;
    }

    public String getTelefono2() {
        return telefono2;
    }

    public void setTelefono2(String telefono2) {
        this.telefono2 = telefono2;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + (this.codSucursal != null ? this.codSucursal.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Sucursal other = (Sucursal) obj;
        if (this.codSucursal != other.codSucursal && (this.codSucursal == null || !this.codSucursal.equals(other.codSucursal))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Sucursal{" + "codSucursal=" + codSucursal + ", restaurante=" + restaurante + ", direccion=" + direccion + ", telefono1=" + telefono1 + ", telefono2=" + telefono2 + '}';
    }
    
}
