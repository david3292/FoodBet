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
public class EventoEquipoPK implements Serializable{
    
    private Integer codEvento;
    private Integer codEquipo;

    public EventoEquipoPK() {
    }

    public Integer getCodEvento() {
        return codEvento;
    }

    public void setCodEvento(Integer codEvento) {
        this.codEvento = codEvento;
    }

    public Integer getCodEquipo() {
        return codEquipo;
    }

    public void setCodEquipo(Integer codEquipo) {
        this.codEquipo = codEquipo;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + (this.codEvento != null ? this.codEvento.hashCode() : 0);
        hash = 53 * hash + (this.codEquipo != null ? this.codEquipo.hashCode() : 0);
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
        final EventoEquipoPK other = (EventoEquipoPK) obj;
        if (this.codEvento != other.codEvento && (this.codEvento == null || !this.codEvento.equals(other.codEvento))) {
            return false;
        }
        if (this.codEquipo != other.codEquipo && (this.codEquipo == null || !this.codEquipo.equals(other.codEquipo))) {
            return false;
        }
        return true;
    }

    
}
