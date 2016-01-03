/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espe.distribuidas.foodbet.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "MENU")
public class Menu implements Serializable{
    
    @Id
    @Column(name = "COD_MENU", nullable = false)
    private Integer codMenu;
    
    @Column(name = "COD_RESTAURANTE", nullable = false)
    private Integer codRestaurante;
    
    @JoinColumn(name = "COD_RESTAURANTE", referencedColumnName = "COD_RESTAURANTE", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Restaurante restaurante;
    
    @Column(name = "NOMBRE", nullable = false)
    private String nombre;
    
    @Column(name = "PRECIO", nullable = false)
    private BigDecimal precio;
    
    @Column(name = "DESCRIPCION", nullable = true)
    private String descripcion;

    public Integer getCodMenu() {
        return codMenu;
    }

    public void setCodMenu(Integer codMenu) {
        this.codMenu = codMenu;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + (this.codMenu != null ? this.codMenu.hashCode() : 0);
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
        final Menu other = (Menu) obj;
        if (this.codMenu != other.codMenu && (this.codMenu == null || !this.codMenu.equals(other.codMenu))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Menu{" + "codMenu=" + codMenu + ", restaurante=" + restaurante + ", nombre=" + nombre + ", precio=" + precio + ", descripcion=" + descripcion + '}';
    }
    
}
