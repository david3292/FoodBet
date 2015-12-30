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
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author david
 */
@Entity
@Table(name = "APUESTA_MENU")
@IdClass(ApuestaMenuPK.class)
public class ApuestaMenu implements Serializable{
    
    @Id
    @Column(name = "COD_APUESTA", nullable = false)
    private Integer codApuesta;
    
    @Id
    @Column(name = "COD_MENU", nullable = false)
    private Integer codMenu;
    
    @Column(name = "CANTIDAD", nullable = false)
    private Integer cantidad;
    
    @JoinColumn(name = "COD_APUESTA", referencedColumnName = "COD_APUESTA", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Apuesta apuesta;

    public ApuestaMenu() {
    }

    public Integer getCodApuesta() {
        return codApuesta;
    }

    public void setCodApuesta(Integer codApuesta) {
        this.codApuesta = codApuesta;
    }

    public Integer getCodMenu() {
        return codMenu;
    }

    public void setCodMenu(Integer codMenu) {
        this.codMenu = codMenu;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Apuesta getApuesta() {
        return apuesta;
    }

    public void setApuesta(Apuesta apuesta) {
        this.apuesta = apuesta;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + (this.codApuesta != null ? this.codApuesta.hashCode() : 0);
        hash = 79 * hash + (this.codMenu != null ? this.codMenu.hashCode() : 0);
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
        final ApuestaMenu other = (ApuestaMenu) obj;
        if (this.codApuesta != other.codApuesta && (this.codApuesta == null || !this.codApuesta.equals(other.codApuesta))) {
            return false;
        }
        if (this.codMenu != other.codMenu && (this.codMenu == null || !this.codMenu.equals(other.codMenu))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ApuestaMenu{" + "codApuesta=" + codApuesta + ", codMenu=" + codMenu + ", cantidad=" + cantidad + ", apuesta=" + apuesta + '}';
    }
    
}
