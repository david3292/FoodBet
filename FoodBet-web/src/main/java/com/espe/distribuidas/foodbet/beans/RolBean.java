/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espe.distribuidas.foodbet.beans;

import com.espe.distribuidas.foodbet.modelo.Equipo;
import com.espe.distribuidas.foodbet.modelo.Rol;
import com.espe.distribuidas.foodbet.servicios.RolServicio;
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
public class RolBean implements Serializable{
    
    @EJB
    private RolServicio rolService;
    
    private List<Rol> roles;
    private Rol rol;
    private String codRol;
    private String nombreRol;
    private String descRol;
    
    @PostConstruct
    public void init(){
        this.rol = new Rol();
        this.roles = this.rolService.obtenerTodosRoles();
    }
    
    public void nuevo(){
        this.reset();
        this.rol = new Rol();
        RequestContext context = RequestContext.getCurrentInstance();
        context.update(":form:rolDetail");
    }
    
    public void reset(){
        this.codRol = null;
        this.nombreRol = null;
        this.descRol = null;
    }
    
    public void aceptar(){
        this.rol.setNombre(this.nombreRol);
        this.rol.setDescripcion(this.descRol);
        this.rolService.ingresarRol(this.rol);
        this.rol = null;
        this.nombreRol = null;
        this.descRol = null;
        this.init();
    }
    
    public void eliminar(){
        System.out.println("rolllll : "+ rol.toString());
        
        System.out.println("Se elimina el rol" + this.rol);
        this.rolService.eliminarRol(rol.getCodRol());
        this.roles = this.rolService.obtenerTodosRoles();
        this.addMessageDelete("Rol eliminado", this.rol.getNombre());
        
    }

    public List<Rol> getRoles() {
        return roles;
    }

    public void setRoles(List<Rol> roles) {
        this.roles = roles;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public String getCodRol() {
        return codRol;
    }

    public void setCodRol(String codRol) {
        this.codRol = codRol;
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }

    public String getDescRol() {
        return descRol;
    }

    public void setDescRol(String descRol) {
        this.descRol = descRol;
    }
    
    public void onRowEdit(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Rol editado: ", ((Rol) event.getObject()).getNombre());
        FacesContext.getCurrentInstance().addMessage(null, msg);
        this.codRol = ((Rol) event.getObject()).getCodRol().toString();
        this.nombreRol = ((Rol) event.getObject()).getNombre();
        this.descRol = ((Rol) event.getObject()).getDescripcion();
        
        this.rol = new Rol();
        this.rol.setCodRol(Integer.parseInt(this.codRol));
        this.rol.setNombre(this.nombreRol);
        this.rol.setDescripcion(this.descRol);
        this.rolService.actualizarRol(this.rol);
        this.rol = null;
        this.init();
        
    }
     
    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edición Cancelada", ((Rol) event.getObject()).getNombre());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void addMessageDelete(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
}
