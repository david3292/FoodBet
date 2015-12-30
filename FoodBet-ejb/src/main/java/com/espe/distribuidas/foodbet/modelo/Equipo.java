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
import javax.persistence.Table;

/**
 *
 * @author david
 */
@Entity
@Table(name = "EQUIPO")
public class Equipo implements Serializable{
    
    @Id
    @Column(name = "COD_EQUIPO", nullable = false)
    private Integer codEquipo;
    
    @Column(name = "NOMBRE", nullable = false)
    private String nombre;

    public Integer getCodEquipo() {
        return codEquipo;
    }

    public void setCodEquipo(Integer codEquipo) {
        this.codEquipo = codEquipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + (this.codEquipo != null ? this.codEquipo.hashCode() : 0);
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
        final Equipo other = (Equipo) obj;
        if (this.codEquipo != other.codEquipo && (this.codEquipo == null || !this.codEquipo.equals(other.codEquipo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Equipo{" + "codEquipo=" + codEquipo + ", nombre=" + nombre + '}';
    }
    
}
