/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espe.distribuidas.foodbet.beans;

import com.espe.distribuidas.foodbet.clases.ManejoSesion;
import com.espe.distribuidas.foodbet.modelo.Rol;
import com.espe.distribuidas.foodbet.modelo.Usuario;
import com.espe.distribuidas.foodbet.servicios.RolServicio;
import com.espe.distribuidas.foodbet.servicios.UsuarioServicio;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author david
 */
@ManagedBean
@ViewScoped
public class LoginBean implements Serializable {

    private String usuario;
    private String clave;
    private ManejoSesion miSesion;
    private Usuario usuarioSesion;

    @EJB
    private UsuarioServicio userService;

    public String doLogin() {
        String bandera = "login";
        try {
            if (this.userService.login(usuario, clave)) {
                System.out.println("ingresa");
                usuarioSesion = this.userService.obtenerUsuarioPorID(usuario);
                miSesion = new ManejoSesion();
                miSesion.iniciarSesion(usuarioSesion.getUsuario().toString(), usuarioSesion.getRol().getNombre().toString());
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Bienvenido " + miSesion.Usuario()));
                if (miSesion.getPerfil().equals("Administrador")) {
                    bandera = "admin";
                } else if (miSesion.getPerfil().equals("Cliente")) {
                    bandera = "apuesta";
                } else {
                    bandera = "login";
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Usuario Incorrecto"));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Usuario Incorrecto e"));
        }
        return bandera;
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

}
