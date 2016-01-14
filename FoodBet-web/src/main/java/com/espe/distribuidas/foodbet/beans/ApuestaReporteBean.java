/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espe.distribuidas.foodbet.beans;

import com.espe.distribuidas.foodbet.clases.ManejoSesion;
import com.espe.distribuidas.foodbet.modelo.Apuesta;
import com.espe.distribuidas.foodbet.modelo.ApuestaMenu;
import com.espe.distribuidas.foodbet.modelo.Usuario;
import com.espe.distribuidas.foodbet.servicios.ApuestaMenuServicio;
import com.espe.distribuidas.foodbet.servicios.ApuestaServicio;
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
import org.primefaces.context.RequestContext;

/**
 *
 * @author Juanjo
 */
@ManagedBean
@ViewScoped

public class ApuestaReporteBean implements Serializable {

    private List<ApuestaMenu> apuestasGanadas;
    private List<ApuestaMenu> apuestasPerdidas;
    private List<ApuestaMenu> apuestasPorPagar;
    private List<ApuestaMenu> apuestasTotales;
    private ApuestaMenu apuestaPagada;
    private Apuesta apuestaPagada1;

    @EJB
    private ApuestaMenuServicio apuestaServicio;

    @EJB
    private UsuarioServicio userService;

    @EJB
    private ApuestaServicio apuestaPagadaServicio;

    @PostConstruct
    public void init() {
        apuestasGanadas = new ArrayList<ApuestaMenu>();
        apuestasPerdidas = new ArrayList<ApuestaMenu>();
        apuestasPorPagar = new ArrayList<ApuestaMenu>();
        apuestasTotales = new ArrayList<ApuestaMenu>();
        CargarApuestas();
    }

    public void CargarApuestas() {
        apuestasTotales = apuestaServicio.obtenerApuestasMenus();
        ManejoSesion sesion = new ManejoSesion();
        Usuario user = new Usuario();
        user = userService.obtenerUsuarioPorID(sesion.getUsuario());
        for (ApuestaMenu am : apuestasTotales) {
            if (user.getParticipanteApuesta().getIdParticipante() == am.getApuesta().getGanadorApuesta()) {
                apuestasGanadas.add(am);
            } else if (user.getParticipanteApuesta().getIdParticipante() == am.getApuesta().getPerdedorApuesta()) {
                apuestasPerdidas.add(am);
                if (am.getApuesta().getPagoEstado().equals("3")) {
                    apuestasPorPagar.add(am);
                }
            }

        }
    }

    public void pagarApuesta() {
        Apuesta apuesta = new Apuesta();
        if (apuestaPagada == null) {
            mostrarMensaje(FacesMessage.SEVERITY_WARN, "Debe seleccionar una apuesta");
        } else {
            
            apuesta = apuestaPagada.getApuesta();
            apuesta.setPagoEstado("4");
//            apuestaPagada1.setPagoEstado("4");
            apuestaPagadaServicio.actualizarApuesta(apuesta);
            mostrarMensaje(FacesMessage.SEVERITY_INFO, "La apuesta ha sido pagada");
        }
    }

    public void mostrarMensaje(FacesMessage.Severity severityMessage, String mensaje) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(severityMessage, "Mensaje:", mensaje));
    }

    public List<ApuestaMenu> getApuestasGanadas() {
        return apuestasGanadas;
    }

    public void setApuestasGanadas(List<ApuestaMenu> apuestasGanadas) {
        this.apuestasGanadas = apuestasGanadas;
    }

    public List<ApuestaMenu> getApuestasPerdidas() {
        return apuestasPerdidas;
    }

    public void setApuestasPerdidas(List<ApuestaMenu> apuestasPerdidas) {
        this.apuestasPerdidas = apuestasPerdidas;
    }

    public List<ApuestaMenu> getApuestasPorPagar() {
        return apuestasPorPagar;
    }

    public void setApuestasPorPagar(List<ApuestaMenu> apuestasPorPagar) {
        this.apuestasPorPagar = apuestasPorPagar;
    }

    public List<ApuestaMenu> getApuestasTotales() {
        return apuestasTotales;
    }

    public void setApuestasTotales(List<ApuestaMenu> apuestasTotales) {
        this.apuestasTotales = apuestasTotales;
    }

    public ApuestaMenu getApuestaPagada() {
        return apuestaPagada;
    }

    public void setApuestaPagada(ApuestaMenu apuestaPagada) {
        this.apuestaPagada = apuestaPagada;
    }

    public Apuesta getApuestaPagada1() {
        return apuestaPagada1;
    }

    public void setApuestaPagada1(Apuesta apuestaPagada1) {
        this.apuestaPagada1 = apuestaPagada1;
    }

    
}
