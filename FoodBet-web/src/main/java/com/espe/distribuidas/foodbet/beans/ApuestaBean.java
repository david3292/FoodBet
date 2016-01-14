/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espe.distribuidas.foodbet.beans;

import com.espe.distribuidas.foodbet.clases.ManejoSesion;
import com.espe.distribuidas.foodbet.modelo.Apuesta;
import com.espe.distribuidas.foodbet.modelo.ApuestaMenu;
import com.espe.distribuidas.foodbet.modelo.EventoDeportivo;
import com.espe.distribuidas.foodbet.modelo.Menu;
import com.espe.distribuidas.foodbet.modelo.ParticipanteApuesta;
import com.espe.distribuidas.foodbet.modelo.Restaurante;
import com.espe.distribuidas.foodbet.modelo.Usuario;
import com.espe.distribuidas.foodbet.servicios.ApuestaMenuServicio;
import com.espe.distribuidas.foodbet.servicios.ApuestaServicio;
import com.espe.distribuidas.foodbet.servicios.EventoDeportivoServicio;
import com.espe.distribuidas.foodbet.servicios.MenuServicio;
import com.espe.distribuidas.foodbet.servicios.ParticipanteApuestaServicio;
import com.espe.distribuidas.foodbet.servicios.RestauranteServicio;
import com.espe.distribuidas.foodbet.servicios.UsuarioServicio;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
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

/**
 *
 * @author Juanjo
 */
@ManagedBean
@ViewScoped
public class ApuestaBean extends BaseBean implements Serializable {

    private List<Apuesta> apuestasEnCurso;
    private List<Apuesta> apuestasPorUsuario;
    private Apuesta apuestaSeleccionada;
    private List<EventoDeportivo> eventos;
    private EventoDeportivo eventoSeleccionado;
    private Usuario usuario;
    private ParticipanteApuesta participante;
    private List<ApuestaMenu> apuestasMenus;
    private ApuestaMenu apuestaMenu;
    private ApuestaMenu apuestaMenuSeleccionada;
    private String codigoEvento;
    private Menu menuSeleccionado;
    private List<Menu> menus;
    private String cantidad;
    private Restaurante restauranteSeleccionado;
    private List<Restaurante> restaurantes;
    private String codigoRestaurante;
    private String codigoMenu;

    @EJB
    private ApuestaServicio apuestaService;

    @EJB
    private EventoDeportivoServicio eventoService;

    @EJB
    private ParticipanteApuestaServicio participanteService;

    @EJB
    private UsuarioServicio usuarioService;

    @EJB
    private ApuestaMenuServicio apuestaMenuService;

    @EJB
    private MenuServicio menuService;

    @EJB
    private RestauranteServicio restauranteServicio;

    @PostConstruct
    public void init() {
        this.eventos = new ArrayList<EventoDeportivo>();
        this.eventos = this.eventoService.obtenerEventDeportivos();
        this.apuestasMenus = new ArrayList<ApuestaMenu>();
        this.apuestasMenus = this.apuestaMenuService.obtenerApuestasMenus();
        this.restaurantes = new ArrayList<Restaurante>();
        this.restaurantes = this.restauranteServicio.obtenerTodosRestaurantes();
        this.menus = new ArrayList<Menu>();
    }

    @Override
    public void nuevo() {
        super.nuevo();
        this.apuestaMenu = new ApuestaMenu();
        RequestContext context = RequestContext.getCurrentInstance();
        context.update(":form:apuestaMenuDetail");
    }

    public void unirse() {
        super.modificar();
        this.apuestaMenu = new ApuestaMenu();
        try {
            BeanUtils.copyProperties(this.apuestaMenu, this.apuestaMenuSeleccionada);
            RequestContext context = RequestContext.getCurrentInstance();
            context.update(":form:apuestaMenuDetail");
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ApuestaBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(ApuestaBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void cancelar() {
        super.cancelar();
    }

    public void cargarMenu() {
        this.restauranteSeleccionado = this.restauranteServicio.obtenerRestaurantePorID(Integer.parseInt(codigoRestaurante));
        Menu newMenu = new Menu();
        newMenu.setRestaurante(restauranteSeleccionado);
        this.menus = menuService.obtenerMenuPorR(newMenu);
        RequestContext context = RequestContext.getCurrentInstance();
        context.update("form:somMenu");
    }

    public void onItemSelectEvento(org.primefaces.event.ItemSelectEvent event) {
        this.restauranteSeleccionado = this.restauranteServicio.obtenerRestaurantePorID(Integer.parseInt(codigoRestaurante));
        Menu newMenu = new Menu();
        newMenu.setRestaurante(restauranteSeleccionado);
        this.menus = menuService.obtenerMenuPorR(newMenu);
        RequestContext context = RequestContext.getCurrentInstance();
        context.update(":form:apuestaMenuDetail:somMenu");
    }
    //iniciar apuesta

    public void aceptar() {
        if (super.isEnNuevo()) {
            try {
                registrarApuestaMenu();
                mostrarMensaje(FacesMessage.SEVERITY_INFO, "Apuesta creada con éxito");
            } catch (Exception e) {
                mostrarMensaje(FacesMessage.SEVERITY_ERROR, "No se puede crear la apuesta " + e);
            }
        } else {
            try {
                //Llamar a modificar no a crear
                Apuesta apuestaOpononete = new Apuesta();
                Usuario usuarioApuesta = new Usuario();
                ManejoSesion sesion = new ManejoSesion();
                apuestaOpononete = apuestaMenu.getApuesta();
                apuestaMenu.setApuesta(apuestaSeleccionada);
                usuarioApuesta = usuarioService.obtenerUsuarioPorID(sesion.Usuario());
                apuestaOpononete.setParticipante2(usuarioApuesta.getParticipanteApuesta());
                apuestaOpononete.setIdParticipante2(usuarioApuesta.getParticipanteApuesta().getIdParticipante());
                this.apuestaMenuService.actualizarApuestaMenu(apuestaMenu);
                mostrarMensaje(FacesMessage.SEVERITY_ERROR, "Se ha unido a la apuesta ");
            } catch (Exception e) {
                mostrarMensaje(FacesMessage.SEVERITY_ERROR, "No puede unirse a la apuesta " + e);
            }
        }

        this.init();
        this.setApuestaMenuSeleccionada(null);
    }

    public void registrarApuestaMenu() {
        ApuestaMenu am = new ApuestaMenu();
        Apuesta apuestaNueva = new Apuesta();
        Usuario usuarioApuesta = new Usuario();
        ManejoSesion sesion = new ManejoSesion();
        List<Apuesta> apuestaIngresada = new ArrayList<Apuesta>();

        usuarioApuesta = usuarioService.obtenerUsuarioPorID(sesion.Usuario());
        eventoSeleccionado = eventoService.obtenerEventDepPorID(Integer.parseInt(codigoEvento));
        apuestaNueva.setParticipante(usuarioApuesta.getParticipanteApuesta());
        apuestaNueva.setIdParticipante(usuarioApuesta.getParticipanteApuesta().getIdParticipante());
        apuestaNueva.setEventoDeportivo(eventoSeleccionado);
        apuestaNueva.setCodEvento(eventoSeleccionado.getCodEvento());
        apuestaNueva.setPagoEstado("1");
        apuestaService.ingresarApuesta(apuestaNueva);
        apuestaIngresada = apuestaService.obtenerApuestaPorParticipanteEvento(apuestaNueva.getIdParticipante(), apuestaNueva.getCodEvento());
        if (apuestaIngresada == null) {
            mostrarMensaje(FacesMessage.SEVERITY_ERROR, "No se puede crear la apuesta");
        } else {
            menuSeleccionado = menuService.obtenerMenuPorID(Integer.parseInt(codigoMenu));
            am.setApuesta(apuestaIngresada.get(0));
            am.setCodApuesta(apuestaIngresada.get(0).getCodApuesta());
            am.setMenu(menuSeleccionado);
            am.setCodMenu(menuSeleccionado.getCodMenu());
            am.setCantidad(Integer.parseInt(cantidad));
            apuestaMenuService.ingresarApuestaMenu(am);
        }

    }

    public void mostrarMensaje(FacesMessage.Severity severityMessage, String mensaje) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(severityMessage, "Mensaje:", mensaje));
    }

    public List<Apuesta> getApuestasEnCurso() {
        return apuestasEnCurso;
    }

    public void setApuestasEnCurso(List<Apuesta> apuestasEnCurso) {
        this.apuestasEnCurso = apuestasEnCurso;
    }

    public List<Apuesta> getApuestasPorUsuario() {
        return apuestasPorUsuario;
    }

    public void setApuestasPorUsuario(List<Apuesta> apuestasPorUsuario) {
        this.apuestasPorUsuario = apuestasPorUsuario;
    }

    public List<EventoDeportivo> getEventos() {
        return eventos;
    }

    public void setEventos(List<EventoDeportivo> eventos) {
        this.eventos = eventos;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public ParticipanteApuesta getParticipante() {
        return participante;
    }

    public void setParticipante(ParticipanteApuesta participante) {
        this.participante = participante;
    }

    public Apuesta getApuestaSeleccionada() {
        return apuestaSeleccionada;
    }

    public void setApuestaSeleccionada(Apuesta apuestaSeleccionada) {
        this.apuestaSeleccionada = apuestaSeleccionada;
    }

    public EventoDeportivo getEventoSeleccionado() {
        return eventoSeleccionado;
    }

    public void setEventoSeleccionado(EventoDeportivo eventoSeleccionado) {
        this.eventoSeleccionado = eventoSeleccionado;
    }

    public ApuestaServicio getApuestaService() {
        return apuestaService;
    }

    public void setApuestaService(ApuestaServicio apuestaService) {
        this.apuestaService = apuestaService;
    }

    public EventoDeportivoServicio getEventoService() {
        return eventoService;
    }

    public void setEventoService(EventoDeportivoServicio eventoService) {
        this.eventoService = eventoService;
    }

    public ParticipanteApuestaServicio getParticipanteService() {
        return participanteService;
    }

    public void setParticipanteService(ParticipanteApuestaServicio participanteService) {
        this.participanteService = participanteService;
    }

    public UsuarioServicio getUsuarioService() {
        return usuarioService;
    }

    public void setUsuarioService(UsuarioServicio usuarioService) {
        this.usuarioService = usuarioService;
    }

    public List<ApuestaMenu> getApuestasMenus() {
        return apuestasMenus;
    }

    public void setApuestasMenus(List<ApuestaMenu> apuestasMenus) {
        this.apuestasMenus = apuestasMenus;
    }

    public ApuestaMenu getApuestaMenu() {
        return apuestaMenu;
    }

    public void setApuestaMenu(ApuestaMenu apuestaMenu) {
        this.apuestaMenu = apuestaMenu;
    }

    public String getCodigoEvento() {
        return codigoEvento;
    }

    public void setCodigoEvento(String codigoEvento) {
        this.codigoEvento = codigoEvento;
    }

    public ApuestaMenu getApuestaMenuSeleccionada() {
        return apuestaMenuSeleccionada;
    }

    public void setApuestaMenuSeleccionada(ApuestaMenu apuestaMenuSeleccionada) {
        this.apuestaMenuSeleccionada = apuestaMenuSeleccionada;
    }

    public Menu getMenuSeleccionado() {
        return menuSeleccionado;
    }

    public void setMenuSeleccionado(Menu menuSeleccionado) {
        this.menuSeleccionado = menuSeleccionado;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public Restaurante getRestauranteSeleccionado() {
        return restauranteSeleccionado;
    }

    public void setRestauranteSeleccionado(Restaurante restauranteSeleccionado) {
        this.restauranteSeleccionado = restauranteSeleccionado;
    }

    public List<Restaurante> getRestaurantes() {
        return restaurantes;
    }

    public void setRestaurantes(List<Restaurante> restaurantes) {
        this.restaurantes = restaurantes;
    }

    public String getCodigoRestaurante() {
        return codigoRestaurante;
    }

    public void setCodigoRestaurante(String codigoRestaurante) {
        this.codigoRestaurante = codigoRestaurante;
    }

    public String getCodigoMenu() {
        return codigoMenu;
    }

    public void setCodigoMenu(String codigoMenu) {
        this.codigoMenu = codigoMenu;
    }

}
