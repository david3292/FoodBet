/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espe.distribuidas.foodbet.beans;

import com.espe.distribuidas.foodbet.modelo.Rol;
import com.espe.distribuidas.foodbet.modelo.Usuario;
import com.espe.distribuidas.foodbet.servicios.RolServicio;
import com.espe.distribuidas.foodbet.servicios.UsuarioServicio;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author david
 */
@ManagedBean
@ViewScoped
public class UsuarioBean implements Serializable {

    private static String[] estados = {"Activo", "Deshabilitado"};

    @EJB
    private UsuarioServicio userService;
    private List<Usuario> usuarios;
    private Usuario user;
    private String usuario;
    private String clave;
    private String estado;
    private Integer codRol;

    @EJB
    private RolServicio rolService;
    private List<Rol> roles;
    private Rol rol;

    @PostConstruct
    public void init() {
        this.iniciarUsuarios();
        this.roles = this.rolService.obtenerTodosRoles();
    }

    public void iniciarUsuarios() {
        this.usuarios = this.userService.obtenerTodosLosUsuarios();
        List<Usuario> auxU = new ArrayList<Usuario>();
        for (Usuario u : this.usuarios) {
            if (u.getParticipanteApuesta() == null) {
                auxU.add(u);
            }
        }
        this.usuarios = auxU;
    }

    public void nuevo() {
        System.out.println("crea un nuevo usuario");
        this.user = new Usuario();
    }
    
    public void aceptar(){
        this.user.setUsuario(this.usuario);
        this.user.setClave(this.clave);
        this.user.cambiarEstado(this.estado);
        this.user.setCodRol(this.codRol);
        this.userService.ingresarUsuario(this.user);
        this.usuarios = this.userService.obtenerTodosLosUsuarios();
        this.user = null;
    }
    
    public void cancelar(){
        this.user = null;
        this.reset();
    }

    public void onRowEditUser(RowEditEvent event) {
        System.out.println("estado: " + this.estado);
        this.user = (Usuario) event.getObject();
        System.out.println("antes: " + this.user.toString());
        this.user.cambiarEstado(estado);
        System.out.println("despues: " + this.user.toString());
        this.userService.actualizarUsuario(this.user);
        this.iniciarUsuarios();
        this.estado = null;
        FacesMessage msg = new FacesMessage("Usuario editado: ", this.user.getUsuario());
        FacesContext.getCurrentInstance().addMessage(null, msg);
        this.user = null;
    }

    public void onRowCancelUser(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edición Cancelada", ((Usuario) event.getObject()).getUsuario());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void reset(){
        this.usuario = null;
        this.clave = null;
        this.estado = null;
        this.codRol = null;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public List<Rol> getRoles() {
        return roles;
    }

    public void setRoles(List<Rol> roles) {
        this.roles = roles;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public String[] getEstados() {
        return estados;
    }

    public void setEstados(String[] estados) {
        UsuarioBean.estados = estados;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Integer getCodRol() {
        return codRol;
    }

    public void setCodRol(Integer codRol) {
        this.codRol = codRol;
    }

}
