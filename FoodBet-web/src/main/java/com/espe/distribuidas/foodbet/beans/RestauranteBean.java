/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espe.distribuidas.foodbet.beans;

import com.espe.distribuidas.foodbet.modelo.Restaurante;
import com.espe.distribuidas.foodbet.servicios.RestauranteServicio;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

/**
 *
 * @author david
 */
@ManagedBean
@ViewScoped
public class RestauranteBean implements Serializable{
    
    @EJB
    private RestauranteServicio restService;
    
    private List<Restaurante> restaurantes;
    
    private Restaurante restSelected;
    
    @PostConstruct
    public void init(){
        this.restaurantes = this.restService.obtenerTodosRestaurantes();
    }

    public RestauranteServicio getRestService() {
        return restService;
    }

    public void setRestService(RestauranteServicio restService) {
        this.restService = restService;
    }

    public List<Restaurante> getRestaurantes() {
        return restaurantes;
    }

    public void setRestaurantes(List<Restaurante> restaurantes) {
        this.restaurantes = restaurantes;
    }

    public Restaurante getRestSelected() {
        return restSelected;
    }

    public void setRestSelected(Restaurante restSelected) {
        this.restSelected = restSelected;
    }
    
    
    public void onRowSelect(SelectEvent event) {
        FacesMessage msg = new FacesMessage("Restaurante seleccionado", ((Restaurante) event.getObject()).getNombre());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
 
    public void onRowUnselect(UnselectEvent event) {
        FacesMessage msg = new FacesMessage("Car Unselected", ((Restaurante) event.getObject()).getNombre());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
}
