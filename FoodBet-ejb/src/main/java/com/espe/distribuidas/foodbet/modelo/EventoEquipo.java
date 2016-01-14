/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espe.distribuidas.foodbet.modelo;

import java.io.Serializable;
import java.util.List;
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
@Table(name = "EVENTO_EQUIPO")
@IdClass(EventoEquipoPK.class)
public class EventoEquipo implements Serializable{
    
    @Id
    @Column(name = "COD_EVENTO", nullable = false)
    private Integer codEvento;
    
    @Id
    @Column(name = "COD_EQUIPO", nullable = false)
    private Integer codEquipo;
    
    @Column(name = "GANADOR", nullable = true)
    private Integer ganador;
    
    @JoinColumn(name = "COD_EQUIPO", referencedColumnName = "COD_EQUIPO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Equipo equipo;
    
    @JoinColumn(name = "COD_EVENTO", referencedColumnName = "COD_EVENTO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private EventoDeportivo eventoDeportivo;
    

    public EventoEquipo() {
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

    public Integer getGanador() {
        return ganador;
    }

    public void setGanador(Integer ganador) {
        this.ganador = ganador;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public EventoDeportivo getEventoDeportivo() {
        return eventoDeportivo;
    }

    public void setEventoDeportivo(EventoDeportivo eventoDeportivo) {
        this.eventoDeportivo = eventoDeportivo;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + (this.codEvento != null ? this.codEvento.hashCode() : 0);
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
        final EventoEquipo other = (EventoEquipo) obj;
        if (this.codEvento != other.codEvento && (this.codEvento == null || !this.codEvento.equals(other.codEvento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EventoEquipo{" + "codEvento=" + codEvento + ", codEquipo=" + codEquipo + ", ganador=" + ganador + ", equipo=" + equipo + '}';
    }    
    
}
