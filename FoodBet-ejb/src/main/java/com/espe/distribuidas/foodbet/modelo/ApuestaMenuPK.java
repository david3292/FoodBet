/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espe.distribuidas.foodbet.modelo;

import java.io.Serializable;

/**
 *
 * @author david
 */
public class ApuestaMenuPK implements Serializable{
    private Integer codApuesta;
    private Integer codMenu;

    public ApuestaMenuPK() {
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

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 31 * hash + (this.codApuesta != null ? this.codApuesta.hashCode() : 0);
        hash = 31 * hash + (this.codMenu != null ? this.codMenu.hashCode() : 0);
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
        final ApuestaMenuPK other = (ApuestaMenuPK) obj;
        if (this.codApuesta != other.codApuesta && (this.codApuesta == null || !this.codApuesta.equals(other.codApuesta))) {
            return false;
        }
        if (this.codMenu != other.codMenu && (this.codMenu == null || !this.codMenu.equals(other.codMenu))) {
            return false;
        }
        return true;
    }
    
    
}
