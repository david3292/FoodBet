/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espe.distribuidas.foodbet.beans;

import com.espe.distribuidas.foodbet.clases.Team;
import com.espe.distribuidas.foodbet.mail.Mail;
import com.espe.distribuidas.foodbet.modelo.Apuesta;
import com.espe.distribuidas.foodbet.modelo.Equipo;
import com.espe.distribuidas.foodbet.modelo.EventoDeportivo;
import com.espe.distribuidas.foodbet.modelo.EventoEquipo;
import com.espe.distribuidas.foodbet.modelo.TipoDeporte;
import com.espe.distribuidas.foodbet.servicios.ApuestaServicio;
import com.espe.distribuidas.foodbet.servicios.EquipoEventoServicio;
import com.espe.distribuidas.foodbet.servicios.EquipoServicio;
import com.espe.distribuidas.foodbet.servicios.EventoDeportivoServicio;
import com.espe.distribuidas.foodbet.servicios.TipoDeporteServicio;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.apache.commons.beanutils.BeanUtils;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.DualListModel;

/**
 *
 * @author david
 */
@ManagedBean
@ViewScoped
public class EventoBean implements Serializable {

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
    private List<Equipo> equiposDisponibles;
    private List<Equipo> equiposParticipantes;
    private List<Equipo> equiposEstatic;
    private DualListModel<Equipo> equipos;

    @EJB
    private EquipoEventoServicio eqEvService;
    private List<EventoEquipo> eventoEquipos;

    private boolean detail;

    private Integer codGanador;
    private EventoEquipo ganador;
    private String nomGanador;

    @EJB
    private ApuestaServicio apuestaService;

    @PostConstruct
    public void init() {
        this.detail = false;
        this.eventos = this.eventService.obtenerEventDeportivos();
        this.deportes = this.deporteService.obtenerTiposDeporte();
    }

    public void nuevo() {
        this.evento = new EventoDeportivo();
    }

    public void aceptar() {
        this.evento.setCodDeporte(this.codDeporte);
        this.evento.setNombreEvento(this.nombreEvento);
        this.evento.setFechaEvento(this.fechaEvento);
        this.evento.setDescripcionEvento(this.descEvento);
        this.eventService.ingresarEventoDeportivo(this.evento);
        this.evento = null;
        this.reset();
        this.eventos = this.eventService.obtenerEventDeportivos();
    }

    public void cancelar() {
        this.evento = null;
        this.reset();
    }

    public void reset() {
        this.codDeporte = null;
        this.nombreEvento = null;
        this.fechaEvento = null;
        this.descEvento = null;
    }

    public void onRowEditEvento(RowEditEvent event) {

    }

    public void onRowCancelEvento(RowEditEvent event) {

    }

    public void selectedEvento() {
        this.nomGanador = null;
        this.detail = true;
        this.equiposDisponibles = null;
        this.equiposParticipantes = null;
        this.equipos = null;
        this.equiposDisponibles = this.equipoService.obtenerTodosLosEquipos();

        Team t = new Team();
        this.equiposEstatic = this.equipoService.obtenerTodosLosEquipos();
        t.setTeams(equiposEstatic);
        EventoEquipo ee = new EventoEquipo();
        System.out.println("Datos del evento: " + this.evento.toString());
        ee.setCodEvento(this.evento.getCodEvento());
        System.out.println("Datos del eventoo: " + ee.toString());
        this.eventoEquipos = this.eqEvService.obtenerEventoEquipoPorC(ee);
        this.selectGanador();

        if (!this.eventoEquipos.isEmpty()) {
            System.out.println("Entra a la senencia");
            int i;
            int j;
            List<Equipo> auxEquipos = this.equipoService.obtenerTodosLosEquipos();
            this.equiposParticipantes = new ArrayList<Equipo>();
            for (i = 0; i < this.eventoEquipos.size(); i++) {
                System.out.println("For i : " + i + "tamaño: " + this.eventoEquipos.size());
                for (j = 0; j < auxEquipos.size(); j++) {
                    System.out.println("For j : " + j + "tamaño: " + auxEquipos.size());
                    System.out.println("en el for j: " + j);
                    if ((int) this.eventoEquipos.get(i).getCodEquipo() == (int) auxEquipos.get(j).getCodEquipo()) {
                        this.equiposParticipantes.add(auxEquipos.get(j));
                        this.equiposDisponibles.remove(auxEquipos.get(j));
                        System.out.println("Ingresa al segundo if j: " + j);

                    }
                }
                if (this.eventoEquipos.get(i).getGanador() == 1) {
                    this.nomGanador = this.eventoEquipos.get(i).getEquipo().getNombre();
                    System.out.println("Ganador:----------------> " + this.nomGanador);
                }

            }
            System.out.println("finalizan los for");
            this.equipos = new DualListModel<Equipo>(this.equiposDisponibles, this.equiposParticipantes);
        } else {
            this.nomGanador = null;
            System.out.println("No existen participantes en el evento");
            this.equiposParticipantes = new ArrayList<Equipo>();
            this.equipos = new DualListModel<Equipo>(this.equiposDisponibles, this.equiposParticipantes);
        }
        System.out.println("fin del metodo");
    }

    private void selectGanador() {
        this.nomGanador = null;
        if (!this.eventoEquipos.isEmpty()) {

            for (EventoEquipo ee : this.eventoEquipos) {
                if (ee.getGanador() == 1) {
                    this.nomGanador = ee.getEquipo().getNombre();
                }
            }
        }
    }

    public void aceptarDetalles() {
        System.out.println("entra al metodo Aceptar detalles");
        this.equiposDisponibles = this.equipos.getSource();
        System.out.println("prueba prueba disponible: " + this.equiposDisponibles);
        List<Equipo> disponibleP = this.equipos.getSource();
        System.out.println("prueba prueba disponible prueba: " + disponibleP);
        this.equiposParticipantes = this.equipos.getTarget();
        System.out.println("prueba prueba participando: " + this.equiposParticipantes);
        List<Equipo> prueba = this.equipos.getTarget();
        System.out.println("prueba prueba participando prueba: " + prueba);
        if (this.eventoEquipos.isEmpty()) {
            if (this.equiposParticipantes.isEmpty()) {
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage("Avertencia", "Cero participantes agregados"));
            } else {
                for (int i = 0; i < this.equiposParticipantes.size(); i++) {
                    Integer codEquipo = this.equiposParticipantes.get(i).getCodEquipo();
                    EventoEquipo ee = new EventoEquipo();
                    ee.setCodEvento(this.evento.getCodEvento());
                    ee.setCodEquipo(codEquipo);
                    ee.setGanador(0);
                    System.out.println("ingresa");
                    this.eqEvService.ingresarEventoEquipo(ee);
                }
            }
        } else {
            System.out.println("tamaño disponible: " + this.equiposDisponibles.get(0).getNombre());
            System.out.println("tamaño participando: " + this.equiposParticipantes);
            this.listaAgregar();
            this.listaEliminar();

        }
        this.detail = false;
    }

    private void listaAgregar() {
        int cont = 0;
        boolean agregar = false;
        List<EventoEquipo> agregarEquipos = new ArrayList<EventoEquipo>();
        for (int i = 0; i < this.equiposParticipantes.size(); i++) {
            for (int j = 0; j < this.eventoEquipos.size(); j++) {
                if ((this.equiposParticipantes.get(i).getCodEquipo() == this.eventoEquipos.get(j).getCodEquipo())) {
                    break;
                } else {
                    cont++;
                    if (cont == this.eventoEquipos.size()) {
                        agregar = true;
                    }
                }
            }
            cont = 0;
            if (agregar) {
                EventoEquipo ee = new EventoEquipo();
                ee.setCodEquipo(this.equiposParticipantes.get(i).getCodEquipo());
                ee.setCodEvento(this.evento.getCodEvento());
                ee.setGanador(0);
                agregarEquipos.add(ee);
                agregar = false;
            }
        }
        System.out.println("Lista para agregar: " + agregarEquipos);
        for (EventoEquipo ee : agregarEquipos) {
            this.eqEvService.ingresarEventoEquipo(ee);
        }
    }

    private void listaEliminar() {
        int cont = 0;
        boolean agregar = false;
        List<EventoEquipo> eliminarEquipos = new ArrayList<EventoEquipo>();
        for (int i = 0; i < this.eventoEquipos.size(); i++) {
            for (int j = 0; j < this.equiposParticipantes.size(); j++) {
                if ((this.eventoEquipos.get(i).getCodEquipo() == this.equiposParticipantes.get(j).getCodEquipo())) {
                    break;
                } else {
                    cont++;
                    if (cont == this.equiposParticipantes.size()) {
                        agregar = true;
                    }
                }
            }
            cont = 0;
            if (agregar) {
                eliminarEquipos.add(this.eventoEquipos.get(i));
                agregar = false;
            }
        }
        System.out.println("Lista para eliminar: " + eliminarEquipos);
        for (EventoEquipo ee : eliminarEquipos) {
            this.eqEvService.eliminarEventoEquipo(ee);
        }
    }

    public void aceptarGanador() {
        System.out.println("evento: " + this.evento.getCodEvento());
        System.out.println("ganador: " + this.codGanador);
        this.eventoEquipos = this.eqEvService.obtenerTodosEventoEquipo();
        System.out.println("Evento equipos: " + this.eventoEquipos);
        for (EventoEquipo ee : this.eventoEquipos) {
            System.out.println("ee codEquipo: " + this.codGanador);
            System.out.println("ee codEvento: " + this.evento.getCodEvento());

            if ((ee.getCodEquipo() == this.codGanador) && (ee.getCodEvento() == this.evento.getCodEvento())) {
                ee.setGanador(1);
                System.out.println("actualiza: " + ee);
                this.eqEvService.actualizatEventoEquipo(ee);
            }
        }
        this.eventos = this.eventService.obtenerEventDeportivos();
        System.out.println("antes mail");
        this.enviarMails();
        System.out.println("despues mail");

    }

    public void enviarMails() {
        List<Apuesta> apuestas = this.apuestaService.obtenerApuestasPorEvento(this.evento.getCodEvento());
        System.out.println("Apuestas para el mail: " + apuestas);
        for (Apuesta a : apuestas) {
            System.out.println("cod1: " + a.getCodEquipo1());
            System.out.println("cod2: " + this.codGanador);
            System.out.println("IdParticipante: " + a.getIdParticipante2());
            if (a.getCodEquipo1() == this.codGanador && a.getIdParticipante2() != null) {
                System.out.println("correo 1: " + a.getParticipante().getEmail());
                Mail m = new Mail();
                m.setTo(a.getParticipante().getEmail());
                m.setSubject("Ganador de la apuesta");
                m.setMessage("Gracias por participar con nosotros");
                m.SEND();

                a.setGanadorApuesta(a.getParticipante().getIdParticipante());
                m = new Mail();
                m.setTo(a.getParticipante2().getEmail());
                m.setSubject("Ha perdido la apuesta");
                m.setMessage("Gracias por participar con nosotros");
                m.SEND();

                a.setPerdedorApuesta(a.getParticipante2().getIdParticipante());
                this.apuestaService.actualizarApuesta(a);
            } else {
                if (a.getCodEquipo2() == this.codGanador) {
                    System.out.println("coreo2: " + a.getParticipante2().getEmail());
                    Mail m = new Mail();
                    m.setTo(a.getParticipante2().getEmail());
                    m.setSubject("Ganador de la apuesta");
                    m.setMessage("Gracias por participar con nosotros");
                    m.SEND();

                    a.setGanadorApuesta(a.getParticipante2().getIdParticipante());

                    m = new Mail();
                    m.setTo(a.getParticipante().getEmail());
                    m.setSubject("Ha perdido la apuesta");
                    m.setMessage("Gracias por participar con nosotros");
                    m.SEND();
                    a.setPerdedorApuesta(a.getParticipante().getIdParticipante());
                    
                    a.setGanadorApuesta(a.getParticipante().getIdParticipante());
                    this.apuestaService.actualizarApuesta(a);
                }
            }
        }
    }

    public void cancelarDetalles() {
        this.detail = false;
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

    public List<Equipo> getEquiposDisponibles() {
        return equiposDisponibles;
    }

    public void setEquiposDisponibles(List<Equipo> equiposDisponibles) {
        this.equiposDisponibles = equiposDisponibles;
    }

    public List<Equipo> getEquiposParticipantes() {
        return equiposParticipantes;
    }

    public void setEquiposParticipantes(List<Equipo> equiposParticipantes) {
        this.equiposParticipantes = equiposParticipantes;
    }

    public List<EventoEquipo> getEventoEquipos() {
        return eventoEquipos;
    }

    public void setEventoEquipos(List<EventoEquipo> eventoEquipos) {
        this.eventoEquipos = eventoEquipos;
    }

    public DualListModel<Equipo> getEquipos() {
        return equipos;
    }

    public void setEquipos(DualListModel<Equipo> equipos) {
        this.equipos = equipos;
    }

    public boolean isDetail() {
        return detail;
    }

    public void setDetail(boolean detail) {
        this.detail = detail;
    }

    public Integer getCodGanador() {
        return codGanador;
    }

    public void setCodGanador(Integer codGanador) {
        this.codGanador = codGanador;
    }

    public EventoEquipo getGanador() {
        return ganador;
    }

    public void setGanador(EventoEquipo ganador) {
        this.ganador = ganador;
    }

    public String getNomGanador() {
        return nomGanador;
    }

    public void setNomGanador(String nomGanador) {
        this.nomGanador = nomGanador;
    }

}
