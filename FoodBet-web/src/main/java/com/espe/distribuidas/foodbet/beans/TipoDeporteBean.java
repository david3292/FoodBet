/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espe.distribuidas.foodbet.beans;

import com.espe.distribuidas.foodbet.modelo.Equipo;
import com.espe.distribuidas.foodbet.modelo.TipoDeporte;
import com.espe.distribuidas.foodbet.servicios.TipoDeporteServicio;
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
public class TipoDeporteBean implements Serializable{
    
    @EJB
    private TipoDeporteServicio tipoDeporteService;
    
    private List<TipoDeporte> tipoDeportes;
    private TipoDeporte tipoDeporte;
    private String codTD;
    private String nombreTD;
    private String descripcionTD;
    
    @PostConstruct
    public void init(){
        System.out.println("Inicializar tipo deporte");
        this.tipoDeportes = this.tipoDeporteService.obtenerTiposDeporte();
    }
    
    public void nuevo(){
        System.out.println("Se ejecuta primero");
        this.tipoDeporte = new TipoDeporte();
        RequestContext context = RequestContext.getCurrentInstance();
        context.update(":form:tipoDeporteDetail");
    }
    
    public  void aceptar(){
        System.out.println("Se ejecuta segundo");
        this.tipoDeporte.setNombre(this.nombreTD);
        this.tipoDeporte.setDescripcion(this.descripcionTD);
        this.tipoDeporteService.ingresarTipoDeporte(this.tipoDeporte);
        this.tipoDeporte = null;
        this.nombreTD = null;
        this.descripcionTD = null;
        this.init();
    }

    public List<TipoDeporte> getTipoDeportes() {
        return tipoDeportes;
    }

    public void setTipoDeportes(List<TipoDeporte> tipoDeportes) {
        this.tipoDeportes = tipoDeportes;
    }

    public TipoDeporte getTipoDeporte() {
        return tipoDeporte;
    }

    public void setTipoDeporte(TipoDeporte tipoDeporte) {
        this.tipoDeporte = tipoDeporte;
    }

    public String getCodTD() {
        return codTD;
    }

    public void setCodTD(String codTD) {
        this.codTD = codTD;
    }

    public String getNombreTD() {
        return nombreTD;
    }

    public void setNombreTD(String nombreTD) {
        this.nombreTD = nombreTD;
    }

    public String getDescripcionTD() {
        return descripcionTD;
    }

    public void setDescripcionTD(String descripcionTD) {
        this.descripcionTD = descripcionTD;
    }
        
    public void onRowEdit(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Deporte editado: ", ((TipoDeporte) event.getObject()).getNombre());
        FacesContext.getCurrentInstance().addMessage(null, msg);
        this.codTD = ((TipoDeporte) event.getObject()).getCodTipoDeporte().toString();
        this.nombreTD = ((TipoDeporte) event.getObject()).getNombre();
        this.descripcionTD = ((TipoDeporte) event.getObject()).getDescripcion();
        this.tipoDeporte = new TipoDeporte();
        this.tipoDeporte.setCodTipoDeporte(Integer.parseInt(this.codTD));
        this.tipoDeporte.setNombre(this.nombreTD);
        this.tipoDeporte.setDescripcion(this.descripcionTD);
        this.tipoDeporteService.actualizarTipoDeporte(tipoDeporte);
        this.init();
        
    }
     
    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edición Cancelada", ((Equipo) event.getObject()).getNombre());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}
