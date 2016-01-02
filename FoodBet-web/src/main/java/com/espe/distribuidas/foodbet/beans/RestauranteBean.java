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
import org.primefaces.context.RequestContext;
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
    private String nombre;
    private String direccion;
    private String telefono;
    private String telefonoSecundario;
    private String especialidad;
    
    private String nombreActualizar;
    private String direccionActualizar;
    private String telefonoActualizar;
    private String telefonoSecundarioActualizar;
    private String especialidadActualizar;

    
    @PostConstruct
    public void init(){
        this.restaurantes = this.restService.obtenerTodosRestaurantes();
    }

    public void registrarRestaurante(){
        Restaurante rest = new Restaurante();
        rest.setNombre(nombre);
        rest.setEspecialidad(especialidad);
        rest.setDireccion(direccion);
        if (!telefono.equals("") || telefono != null){
            rest.setTelefono1(telefono);
        }
        if (!telefonoSecundario.equals("") || telefonoSecundario != null){
            rest.setTelefono2(telefonoSecundario);
        }
        restService.ingresarRestaurante(rest);
        init();
    }
    
    public void cargarNuevoRest(){
        RequestContext.getCurrentInstance().execute("form:formRest:dlgRestaurant.show()");
    }
    
    public void cargarDatosActualizar(){
        if (restSelected == null) {
            mostrarMensaje(FacesMessage.SEVERITY_WARN, "Seleccionar un Registro");
        } else {
            nombreActualizar = restSelected.getNombre();
            direccionActualizar = restSelected.getDireccion();
            telefonoActualizar = restSelected.getTelefono1();
            telefonoSecundarioActualizar = restSelected.getTelefono2();
            especialidadActualizar = restSelected.getEspecialidad();
        }
    }
    
    public void actualizarRest(){
        restSelected.setNombre(nombreActualizar);
        restSelected.setDireccion(direccionActualizar);
        restSelected.setTelefono1(telefonoActualizar);
        restSelected.setTelefono2(telefonoSecundarioActualizar);
        restSelected.setEspecialidad(especialidadActualizar);
        restService.actualizarRestaurante(restSelected);
        
        init();
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

    public String getNombreActualizar() {
        return nombreActualizar;
    }

    public void setNombreActualizar(String nombreActualizar) {
        this.nombreActualizar = nombreActualizar;
    }

    public String getDireccionActualizar() {
        return direccionActualizar;
    }

    public void setDireccionActualizar(String direccionActualizar) {
        this.direccionActualizar = direccionActualizar;
    }

    public String getTelefonoActualizar() {
        return telefonoActualizar;
    }

    public void setTelefonoActualizar(String telefonoActualizar) {
        this.telefonoActualizar = telefonoActualizar;
    }

    public String getTelefonoSecundarioActualizar() {
        return telefonoSecundarioActualizar;
    }

    public void setTelefonoSecundarioActualizar(String telefonoSecundarioActualizar) {
        this.telefonoSecundarioActualizar = telefonoSecundarioActualizar;
    }

    public String getEspecialidadActualizar() {
        return especialidadActualizar;
    }

    public void setEspecialidadActualizar(String especialidadActualizar) {
        this.especialidadActualizar = especialidadActualizar;
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
