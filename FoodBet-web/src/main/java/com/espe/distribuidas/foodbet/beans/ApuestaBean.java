/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espe.distribuidas.foodbet.beans;

import com.espe.distribuidas.foodbet.modelo.Apuesta;
import com.espe.distribuidas.foodbet.modelo.EventoDeportivo;
import com.espe.distribuidas.foodbet.modelo.ParticipanteApuesta;
import com.espe.distribuidas.foodbet.modelo.Usuario;
import com.espe.distribuidas.foodbet.servicios.ApuestaServicio;
import com.espe.distribuidas.foodbet.servicios.EventoDeportivoServicio;
import com.espe.distribuidas.foodbet.servicios.ParticipanteApuestaServicio;
import com.espe.distribuidas.foodbet.servicios.UsuarioServicio;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.CloseEvent;
import org.primefaces.event.ToggleEvent;

/**
 *
 * @author Juanjo
 */
@ManagedBean
@ViewScoped
public class ApuestaBean implements Serializable {
    
    private List<Apuesta> apuestasEnCurso;
    private List<Apuesta> apuestasPorUsuario;
    private Apuesta apuestaSeleccionada;
    private List<EventoDeportivo> eventos;
    private EventoDeportivo eventoSeleccionado;
    private Usuario usuario;
    private ParticipanteApuesta participante;

    @EJB
    private ApuestaServicio apuestaService;
    
    @EJB
    private EventoDeportivoServicio eventoService;
    
    @EJB
    private ParticipanteApuestaServicio participanteService;
    
    @EJB
    private UsuarioServicio usuarioService;
    
    
    public void onClose(CloseEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Panel Closed", "Closed panel id:'" + event.getComponent().getId() + "'");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
     
    public void onToggle(ToggleEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, event.getComponent().getId() + " toggled", "Status:" + event.getVisibility().name());
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    public void onCloseGanadas(CloseEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Panel Closed", "Closed panel id:'" + event.getComponent().getId() + "'");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
     
    public void onToggleGanadas(ToggleEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, event.getComponent().getId() + " toggled", "Status:" + event.getVisibility().name());
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    public void onClosePerdidas(CloseEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Panel Closed", "Closed panel id:'" + event.getComponent().getId() + "'");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
     
    public void onTogglePerdidas(ToggleEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, event.getComponent().getId() + " toggled", "Status:" + event.getVisibility().name());
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    public List<Apuesta> getApuestasEnCurso() {
        return apuestasEnCurso;
    }

    public void setApuestasEnCurso(List<Apuesta> apuestasEnCurso) {
        this.apuestasEnCurso = apuestasEnCurso;
    }

    public List<Apuesta> getApuestasPorUsuario() {
        return apuestasPorUsuario;
    }

    public void setApuestasPorUsuario(List<Apuesta> apuestasPorUsuario) {
        this.apuestasPorUsuario = apuestasPorUsuario;
    }

    public List<EventoDeportivo> getEventos() {
        return eventos;
    }

    public void setEventos(List<EventoDeportivo> eventos) {
        this.eventos = eventos;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public ParticipanteApuesta getParticipante() {
        return participante;
    }

    public void setParticipante(ParticipanteApuesta participante) {
        this.participante = participante;
    }

    public Apuesta getApuestaSeleccionada() {
        return apuestaSeleccionada;
    }

    public void setApuestaSeleccionada(Apuesta apuestaSeleccionada) {
        this.apuestaSeleccionada = apuestaSeleccionada;
    }

    public EventoDeportivo getEventoSeleccionado() {
        return eventoSeleccionado;
    }

    public void setEventoSeleccionado(EventoDeportivo eventoSeleccionado) {
        this.eventoSeleccionado = eventoSeleccionado;
    }

    public ApuestaServicio getApuestaService() {
        return apuestaService;
    }

    public void setApuestaService(ApuestaServicio apuestaService) {
        this.apuestaService = apuestaService;
    }

    public EventoDeportivoServicio getEventoService() {
        return eventoService;
    }

    public void setEventoService(EventoDeportivoServicio eventoService) {
        this.eventoService = eventoService;
    }

    public ParticipanteApuestaServicio getParticipanteService() {
        return participanteService;
    }

    public void setParticipanteService(ParticipanteApuestaServicio participanteService) {
        this.participanteService = participanteService;
    }

    public UsuarioServicio getUsuarioService() {
        return usuarioService;
    }

    public void setUsuarioService(UsuarioServicio usuarioService) {
        this.usuarioService = usuarioService;
    }
    
}
