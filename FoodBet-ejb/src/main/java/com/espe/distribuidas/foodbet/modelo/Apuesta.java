/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espe.distribuidas.foodbet.modelo;

import java.io.Serializable;
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
@Table(name = "APUESTA")
public class Apuesta implements Serializable{
    
    @Id
    @Column(name = "COD_APUESTA", nullable = false)
    private Integer codApuesta;
    
    @JoinColumn(name = "ID_PARTICIPANTE", referencedColumnName = "ID_PARTICIPANTE", insertable = false, updatable = false)
    @ManyToOne
    private ParticipanteApuesta participante;
    
    @JoinColumn(name = "ID_PARTICIPANTE2", referencedColumnName = "ID_PARTICIPANTE", insertable = false, updatable = false)
    @ManyToOne
    private ParticipanteApuesta participante2;
    
    @JoinColumn(name = "COD_EVENTO", referencedColumnName = "COD_EVENTO", insertable = false, updatable = false)
    @ManyToOne
    private EventoDeportivo eventoDeportivo;
    
    @Column(name = "ID_PARTICIPANTE", nullable = false)
    private Integer idParticipante;
    
    @Column(name = "ID_PARTICIPANTE2", nullable = true)
    private Integer idParticipante2;
    
    @Column(name = "COD_EVENTO", nullable = false)
    private Integer codEvento;
    
    @Column(name = "GANADOR_APUESTA", nullable = true)
    private Integer ganadorApuesta;
    
    @Column(name = "PERDEDOR_APUESTA", nullable = true)
    private Integer perdedorApuesta;
    
    @Column(name = "FECHA_PAGO", nullable = true)
    @Temporal(TemporalType.DATE)
    private Date fechaPago;
    
    @Column(name = "PAGO_ESTADO", nullable = true)
    private String pagoEstado;
    
    @Column(name = "COD_EQUIPOP1", nullable = true)
    private Integer codEquipo1;
    
    @Column(name = "COD_EQUIPOP2", nullable = true)
    private Integer codEquipo2;
    
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "apuesta")
    List<ApuestaMenu> menusApuesta;

    public Apuesta() {
    }

    public Integer getCodApuesta() {
        return codApuesta;
    }

    public void setCodApuesta(Integer codApuesta) {
        this.codApuesta = codApuesta;
    }

    public ParticipanteApuesta getParticipante() {
        return participante;
    }

    public void setParticipante(ParticipanteApuesta participante) {
        this.participante = participante;
    }

    public ParticipanteApuesta getParticipante2() {
        return participante2;
    }

    public void setParticipante2(ParticipanteApuesta participante2) {
        this.participante2 = participante2;
    }

    public EventoDeportivo getEventoDeportivo() {
        return eventoDeportivo;
    }

    public void setEventoDeportivo(EventoDeportivo eventoDeportivo) {
        this.eventoDeportivo = eventoDeportivo;
    }

    public Integer getGanadorApuesta() {
        return ganadorApuesta;
    }

    public void setGanadorApuesta(Integer ganadorApuesta) {
        this.ganadorApuesta = ganadorApuesta;
    }

    public Integer getPerdedorApuesta() {
        return perdedorApuesta;
    }

    public void setPerdedorApuesta(Integer perdedorApuesta) {
        this.perdedorApuesta = perdedorApuesta;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public String getPagoEstado() {
        return pagoEstado;
    }

    public void setPagoEstado(String pagoEstado) {
        this.pagoEstado = pagoEstado;
    }

    public Integer getIdParticipante() {
        return idParticipante;
    }

    public void setIdParticipante(Integer idParticipante) {
        this.idParticipante = idParticipante;
    }

    public Integer getIdParticipante2() {
        return idParticipante2;
    }

    public void setIdParticipante2(Integer idParticipante2) {
        this.idParticipante2 = idParticipante2;
    }

    public Integer getCodEvento() {
        return codEvento;
    }

    public void setCodEvento(Integer codEvento) {
        this.codEvento = codEvento;
    }

    public List<ApuestaMenu> getMenusApuesta() {
        return menusApuesta;
    }

    public void setMenusApuesta(List<ApuestaMenu> menusApuesta) {
        this.menusApuesta = menusApuesta;
    }

    public Integer getCodEquipo1() {
        return codEquipo1;
    }

    public void setCodEquipo1(Integer codEquipo1) {
        this.codEquipo1 = codEquipo1;
    }

    public Integer getCodEquipo2() {
        return codEquipo2;
    }

    public void setCodEquipo2(Integer codEquipo2) {
        this.codEquipo2 = codEquipo2;
    }        
        

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + (this.codApuesta != null ? this.codApuesta.hashCode() : 0);
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
        final Apuesta other = (Apuesta) obj;
        if (this.codApuesta != other.codApuesta && (this.codApuesta == null || !this.codApuesta.equals(other.codApuesta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Apuesta{" + "codApuesta=" + codApuesta + ", participante=" + participante + ", participante2=" + participante2 + ", eventoDeportivo=" + eventoDeportivo + ", idParticipante=" + idParticipante + ", idParticipante2=" + idParticipante2 + ", codEvento=" + codEvento + ", ganadorApuesta=" + ganadorApuesta + ", perdedorApuesta=" + perdedorApuesta + ", fechaPago=" + fechaPago + ", pagoEstado=" + pagoEstado + ", codEquipo1=" + codEquipo1 + ", codEquipo2=" + codEquipo2 + '}';
    }

    
        
}