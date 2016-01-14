/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espe.distribuidas.foodbet.modelo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author david
 */
@Entity
@Table(name = "EVENTO_DEPORTIVO")
public class EventoDeportivo implements Serializable{
    
    @Id
    @Column(name = "COD_EVENTO", nullable = false)
    private Integer codEvento;
    
    @Column(name = "COD_DEPORTE", nullable = false)
    private Integer codDeporte;
    
    @JoinColumn(name = "COD_DEPORTE", referencedColumnName = "COD_DEPORTE", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TipoDeporte tipoDeporte;
    
    @Column(name = "NOMBRE_EVENTO", nullable = false)
    private String nombreEvento;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "FECHA_EVENTO", nullable = false)
    private Date fechaEvento;
    
    @Column(name = "DESCRIPCION_EVENTO", nullable = true)
    private String descripcionEvento;
    
    @OneToMany(fetch = FetchType.EAGER ,mappedBy = "eventoDeportivo")
    private List<EventoEquipo> eventoEquipos;

    public Integer getCodEvento() {
        return codEvento;
    }

    public void setCodEvento(Integer codEvento) {
        this.codEvento = codEvento;
    }

    public TipoDeporte getTipoDeporte() {
        return tipoDeporte;
    }

    public void setTipoDeporte(TipoDeporte tipoDeporte) {
        this.tipoDeporte = tipoDeporte;
    }

    public String getNombreEvento() {
        return nombreEvento;
    }

    public void setNombreEvento(String nombreEvento) {
        this.nombreEvento = nombreEvento;
    }

    public Date getFechaEvento() {
        return fechaEvento;
    }

    public void setFechaEvento(Date fechaEvento) {
        this.fechaEvento = fechaEvento;
    }

    public String getDescripcionEvento() {
        return descripcionEvento;
    }

    public void setDescripcionEvento(String descripcionEvento) {
        this.descripcionEvento = descripcionEvento;
    }

    public Integer getCodDeporte() {
        return codDeporte;
    }

    public void setCodDeporte(Integer codDeporte) {
        this.codDeporte = codDeporte;
    }
    
    public String getFormatDate(){
        SimpleDateFormat f = new SimpleDateFormat("dd-MM-yy");
        return f.format(fechaEvento);
    }

    public List<EventoEquipo> getEventoEquipos() {
        return eventoEquipos;
    }

    public void setEventoEquipos(List<EventoEquipo> eventoEquipos) {
        this.eventoEquipos = eventoEquipos;
    }
    
    public String getGanador(){
        for(EventoEquipo ee : eventoEquipos){
            if(ee.getGanador() == 1){
                return ee.getEquipo().getNombre();
            }
        }
        return null;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + (this.codEvento != null ? this.codEvento.hashCode() : 0);
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
        final EventoDeportivo other = (EventoDeportivo) obj;
        if (this.codEvento != other.codEvento && (this.codEvento == null || !this.codEvento.equals(other.codEvento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EventoDeportivo{" + "codEvento=" + codEvento + ", codDeporte=" + codDeporte + ", tipoDeporte=" + tipoDeporte + ", nombreEvento=" + nombreEvento + ", fechaEvento=" + fechaEvento + ", descripcionEvento=" + descripcionEvento + '}';
    }
}