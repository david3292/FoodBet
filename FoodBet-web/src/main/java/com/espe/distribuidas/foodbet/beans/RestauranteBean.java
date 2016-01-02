/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espe.distribuidas.foodbet.beans;

import com.espe.distribuidas.foodbet.modelo.Restaurante;
import com.espe.distribuidas.foodbet.servicios.RestauranteServicio;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
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
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

/**
 *
 * @author david
 */
@ManagedBean
@ViewScoped
public class RestauranteBean extends BaseBean implements Serializable {

    @EJB
    private RestauranteServicio restService;

    private List<Restaurante> restaurantes;

    private Restaurante restSelected;
    private Restaurante restaurante;
    private String nombre;
    private String direccion;
    private String telefono;
    private String telefonoSecundario;
    private String especialidad;

    @PostConstruct
    public void init() {
        super.reset();
        this.restaurantes = this.restService.obtenerTodosRestaurantes();
        nombre = "";
        direccion = "";
        telefono = "";
        telefonoSecundario = "";
        especialidad = "";
    }

    public void registrarRestaurante() {
        Restaurante rest = new Restaurante();
        rest.setNombre(nombre);
        rest.setEspecialidad(especialidad);
        rest.setDireccion(direccion);
        if (!telefono.equals("") || telefono != null) {
            rest.setTelefono1(telefono);
        }
        if (!telefonoSecundario.equals("") || telefonoSecundario != null) {
            rest.setTelefono2(telefonoSecundario);
        }
        restService.ingresarRestaurante(rest);
        init();
    }

    public void nuevo() {
        super.nuevo();
        this.restaurante = new Restaurante();
        RequestContext context = RequestContext.getCurrentInstance();
        context.update(":form:restDetail");
    }

    public void actualizar() {
        super.modificar();
        this.restaurante = new Restaurante();
        try {
            BeanUtils.copyProperties(this.restaurante, this.restSelected);
            nombre = restaurante.getNombre();
            direccion = restaurante.getDireccion();
            telefono = restaurante.getTelefono1();
            telefonoSecundario = restaurante.getTelefono2();
            especialidad = restaurante.getEspecialidad();
            RequestContext context = RequestContext.getCurrentInstance();
            context.update(":form:restDetail");
        } catch (IllegalAccessException ex) {
            Logger.getLogger(RestauranteBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(RestauranteBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void cancelar() {
        super.cancelar();
        this.setRestSelected(null);
    }

    //para ingresar nuevo o actualizar 
    public void aceptar() {
//        FacesContext context = FacesContext.getCurrentInstance();
        if (super.isEnNuevo()) {
            try {
                registrarRestaurante();
                //   this.restaurantes.add(0, this.restaurante);
//                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Se registro el restaurante: " + this.restaurante.getNombre(), null));
            } catch (Exception e) {
//                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
            }
        } else {
            try {
                //Llamar a modificar no a crear
                restaurante.setNombre(nombre);
                restaurante.setEspecialidad(especialidad);
                restaurante.setDireccion(direccion);
                restaurante.setTelefono1(telefono);
                restaurante.setTelefono2(telefonoSecundario);
                this.restService.actualizarRestaurante(this.restaurante);
//                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Se modifico el restaurante: " + this.restaurante.getNombre(), null));
            } catch (Exception e) {
//                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
            }
        }

        this.init();
        this.setRestSelected(null);
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getTelefonoSecundario() {
        return telefonoSecundario;
    }

    public void setTelefonoSecundario(String telefonoSecundario) {
        this.telefonoSecundario = telefonoSecundario;
    }

    public Restaurante getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }

    public void onRowSelect(SelectEvent event) {
        FacesMessage msg = new FacesMessage("Restaurante seleccionado", ((Restaurante) event.getObject()).getNombre());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowUnselect(UnselectEvent event) {
        FacesMessage msg = new FacesMessage("Restaurante deseleccionado", ((Restaurante) event.getObject()).getNombre());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void mostrarMensaje(FacesMessage.Severity severityMessage, String mensaje) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(severityMessage, "Mensaje:", mensaje));
    }
}
