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
@Table(name = "TIPO_DEPORTE")
public class TipoDeporte implements Serializable{
    
    @Id
    @Column(name = "COD_DEPORTE", nullable = false)
    private Integer codTipoDeporte;
    
    @Column(name = "NOMBRE", nullable = false)
    private String nombre;
    
    @Column(name = "DESCRIPCION", nullable = true)
    private String descripcion;

    public Integer getCodTipoDeporte() {
        return codTipoDeporte;
    }

    public void setCodTipoDeporte(Integer codTipoDeporte) {
        this.codTipoDeporte = codTipoDeporte;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
        hash = 47 * hash + (this.codTipoDeporte != null ? this.codTipoDeporte.hashCode() : 0);
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
        final TipoDeporte other = (TipoDeporte) obj;
        if (this.codTipoDeporte != other.codTipoDeporte && (this.codTipoDeporte == null || !this.codTipoDeporte.equals(other.codTipoDeporte))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TipoDeporte{" + "codTipoDeporte=" + codTipoDeporte + ", nombre=" + nombre + ", descripcion=" + descripcion + '}';
    }
    
    
}