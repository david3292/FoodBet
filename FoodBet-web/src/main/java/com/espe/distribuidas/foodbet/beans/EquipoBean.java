/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espe.distribuidas.foodbet.beans;

import com.espe.distribuidas.foodbet.modelo.Equipo;
import com.espe.distribuidas.foodbet.servicios.EquipoServicio;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author david
 */
@ViewScoped
@ManagedBean
public class EquipoBean implements Serializable{

    @EJB
    private EquipoServicio equipoService;
    
    private List<Equipo> equipos;
    private Equipo equipo;
    private String codEquipo;
    private String nombreEquipo;
    
    @PostConstruct
    public void init(){
        this.equipos = this.equipoService.obtenerTodosLosEquipos();
    }
    
    public void nuevo(){
        this.equipo = new Equipo();
        System.out.println("Se ejecuta el metodo nuevo");
        RequestContext context = RequestContext.getCurrentInstance();
        context.update(":form:equipoDetail");
    }
    
    public void aceptar(){
        System.out.println("Datos: aceptar");
        this.equipo.setNombre(nombreEquipo);        
        this.equipoService.insertarEquipo(this.equipo);
        this.equipo = null;
        this.init();
    }

    public List<Equipo> getEquipos() {
        return equipos;
    }

    public void setEquipos(List<Equipo> equipos) {
        this.equipos = equipos;
    }    

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public String getNombreEquipo() {
        return nombreEquipo;
    }

    public void setNombreEquipo(String nombreEquipo) {
        this.nombreEquipo = nombreEquipo;
    }

    public String getCodEquipo() {
        return codEquipo;
    }

    public void setCodEquipo(String codEquipo) {
        this.codEquipo = codEquipo;
    }
    
    public void onRowEdit(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Equipo Eitado: ", ((Equipo) event.getObject()).getNombre());
        FacesContext.getCurrentInstance().addMessage(null, msg);
        this.codEquipo = ((Equipo) event.getObject()).getCodEquipo().toString();
        this.nombreEquipo = ((Equipo) event.getObject()).getNombre();
        System.out.println("codEquipo: " + this.codEquipo + " nombre: " + this.nombreEquipo);
        this.equipo = new Equipo();
        this.equipo.setCodEquipo(Integer.parseInt(this.codEquipo));
        this.equipo.setNombre(this.nombreEquipo);
        this.equipoService.actualizarEquipo(equipo);
        this.equipo = null;
        this.init();
        
    }
     
    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edición Cancelada", ((Equipo) event.getObject()).getNombre());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}
