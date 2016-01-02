/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espe.distribuidas.foodbet.clases;

import java.io.Serializable;
import java.util.Enumeration;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Juanjo
 */
public class ManejoSesion implements Serializable {
    
    private HttpSession sesion;
    
    public ManejoSesion() {
        sesion = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
    }

    public ManejoSesion(HttpSession _sesion) {
        sesion = _sesion;
    }
    
    public HttpSession getSesion() {
        return sesion;
    }

    public void setSesion(HttpSession sesion) {
        this.sesion = sesion;
    }
    
    public String getUsuario() {
        return sesion.getAttribute("usuario").toString();
    }

    public void setUsuario(String username) {
        sesion.setAttribute("usuario", username);
    }

    public String getPerfil() {
        return sesion.getAttribute("perfil").toString();
    }

    public void setPerfil(String perfil) {
        sesion.setAttribute("perfil", perfil);
    }

    public String getNombreUsuario() {
        return sesion.getAttribute("nombreUsuario").toString();
    }

    public void setNombreUsuario(String nombreUsuario) {
        sesion.setAttribute("nombreUsuario", nombreUsuario);
    }

    public void setEstalogeado(String logeado) {
        sesion.setAttribute("estaLogeado", logeado);
    }

    public String getEstaLogeado() {
        return sesion.getAttribute("estaLogeado").toString();
    }

    /**
     * Método que añade los atributos a la sesion http
     *
     * @param perfil
     * @param usuario
     */
    public void iniciarSesion(String usuario, String perfil) {
        setUsuario(usuario);
        setPerfil(perfil);
        setEstalogeado("activo");
    }

    /**
     * Metodo que elimina los atributos de la sesion http
     */
    public void cerrarSesion() {
        Enumeration<String> enume = sesion.getAttributeNames();
        while (enume.hasMoreElements()) {
            sesion.removeAttribute(enume.nextElement());
        }
    }

    /**
     * Obtiene el nombre de usuario en sesion
     *
     * @return el nombre de usuario
     */
    public String nombreUsuarioActual() {
        if (estadoSesion()) {
            return getNombreUsuario();
        } else {
            return null;
        }
    }

    /**
     * Obtiene el id del perfil del usuario en sesion
     *
     * @return idPerfil de usuario
     */
    public String perfilActual() {
        if (estadoSesion()) {
            return getPerfil();
        } else {
            return null;
        }
    }

    /**
     * Obtiene el usuario de la sesion
     * 
     * @return 
     */
    public String Usuario() {
        if (estadoSesion()) {
            return getUsuario();
        } else {
            return null;
        }
    }

    /**
     * Obtiene el estado de la sesion
     *
     * @return true si existe sesion, de lo contrario false
     */
    public boolean estadoSesion() {
        boolean bandera = false;
        if (sesion != null) {
            try {
                if (!getUsuario().equals("")) {
                    bandera = true;
                }
            } catch (Exception e) {
            }
        }
        return bandera;
    }
    
}
