/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espe.distribuidas.foodbet.beans;

import com.espe.distribuidas.foodbet.modelo.EventoDeportivo;
import com.espe.distribuidas.foodbet.modelo.TipoDeporte;
import com.espe.distribuidas.foodbet.servicios.EquipoEventoServicio;
import com.espe.distribuidas.foodbet.servicios.EquipoServicio;
import com.espe.distribuidas.foodbet.servicios.EventoDeportivoServicio;
import com.espe.distribuidas.foodbet.servicios.TipoDeporteServicio;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author david
 */
@ManagedBean
@ViewScoped
public class EventoBean implements Serializable{
    
    @EJB
    private EventoDeportivoServicio eventService;
    private List<EventoDeportivo> eventos;
    private List<EventoDeportivo> eventosFiltered;
    private EventoDeportivo evento;
    private Integer codDeporte;
    private String nombreEvento;
    private Date fechaEvento;
    private String descEvento;
    
    @EJB
    private TipoDeporteServicio deporteService;
    private List<TipoDeporte> deportes;
    
    @EJB
    private EquipoServicio equipoService;
    
    @EJB
    private EquipoEventoServicio eqEvService;
    
    @PostConstruct
    public void init(){
        this.eventos = this.eventService.obtenerEventDeportivos();
        this.deportes = this.deporteService.obtenerTiposDeporte();
    }
    
    public void nuevo(){
        this.evento = new EventoDeportivo();
    }
    
    public void aceptar(){
        this.evento.setCodDeporte(this.codDeporte);
        this.evento.setNombreEvento(this.nombreEvento);
        this.evento.setFechaEvento(this.fechaEvento);
        this.evento.setDescripcionEvento(this.descEvento);
        this.eventService.ingresarEventoDeportivo(this.evento);
        this.evento = null;
        this.reset();
        this.eventos = this.eventService.obtenerEventDeportivos();
    }
    
    public void cancelar(){
        this.evento = null;
        this.reset();
    }
    
    public void reset(){
        this.codDeporte = null;
        this.nombreEvento = null;
        this.fechaEvento = null;
        this.descEvento = null;
    }
    
    public void onRowEditEvento(RowEditEvent event){
        
    }
    
    public void onRowCancelEvento(RowEditEvent event){
        
    }

    public Integer getCodDeporte() {
        return codDeporte;
    }

    public void setCodDeporte(Integer codDeporte) {
        this.codDeporte = codDeporte;
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

    public String getDescEvento() {
        return descEvento;
    }

    public void setDescEvento(String descEvento) {
        this.descEvento = descEvento;
    }

    public List<EventoDeportivo> getEventos() {
        return eventos;
    }

    public void setEventos(List<EventoDeportivo> eventos) {
        this.eventos = eventos;
    }

    public EventoDeportivo getEvento() {
        return evento;
    }

    public void setEvento(EventoDeportivo evento) {
        this.evento = evento;
    }

    public List<TipoDeporte> getDeportes() {
        return deportes;
    }

    public void setDeportes(List<TipoDeporte> deportes) {
        this.deportes = deportes;
    }

    public List<EventoDeportivo> getEventosFiltered() {
        return eventosFiltered;
    }

    public void setEventosFiltered(List<EventoDeportivo> eventosFiltered) {
        this.eventosFiltered = eventosFiltered;
    }
    
}
