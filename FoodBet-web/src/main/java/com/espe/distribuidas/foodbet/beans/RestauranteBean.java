/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espe.distribuidas.foodbet.beans;

import com.espe.distribuidas.foodbet.modelo.Menu;
import com.espe.distribuidas.foodbet.modelo.Restaurante;
import com.espe.distribuidas.foodbet.modelo.Sucursal;
import com.espe.distribuidas.foodbet.servicios.MenuServicio;
import com.espe.distribuidas.foodbet.servicios.RestauranteServicio;
import com.espe.distribuidas.foodbet.servicios.SucursalServicio;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
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

    @EJB
    private SucursalServicio sucursalServicio;

    @EJB
    private MenuServicio menuServicio;

    private List<Restaurante> restaurantes;
    private Restaurante restSelected;
    private Restaurante restaurante;
    private String nombre;
    private String direccion;
    private String telefono;
    private String telefonoSecundario;
    private String especialidad;

    private List<Sucursal> sucursales;
    private Sucursal sucursalSelected;
    private Sucursal sucursal;
    private boolean enNuevoSucursal;
    private boolean enModificarSucursal;
    private String dirSucursal = "";
    private String tel1Sucursal = "";
    private String tel2Sucursal = "";

    private List<Menu> menus;
    private Menu menuSelected;
    private boolean enNuevoMenu;
    private boolean enModificarMenu;
    private String menuNombre = "";
    private BigDecimal menuPrecio = new BigDecimal(0);
    private String menuDescripcion = "";

    @PostConstruct
    public void init() {
        super.reset();
        this.restaurantes = this.restService.obtenerTodosRestaurantes();
        nombre = "";
        direccion = "";
        telefono = "";
        telefonoSecundario = "";
        especialidad = "";

        if (this.restSelected != null) {
            this.sucursales = this.restSelected.getSucursales();
        }
        if (this.menuSelected != null) {
            this.menus = this.restSelected.getMenus();
        }

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

    @Override
    public void nuevo() {
        super.nuevo();
        this.restaurante = new Restaurante();
        RequestContext context = RequestContext.getCurrentInstance();
        context.update(":form:restDetail");
    }

    public void nuevaSucursal() {
        System.out.println("ingreso al metodo nuevaSucursal");
        this.enNuevoSucursal = true;
        this.sucursal = new Sucursal();
        this.sucursal.setCodRestaurante(this.restSelected.getCodRestaurante());
        System.out.println("Sucursal: " + this.sucursal.getDireccion());
        RequestContext context = RequestContext.getCurrentInstance();
        context.update(":form:sucursalDetail");
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

    public void actualizarSucursal() {
        this.enModificarSucursal = true;
        this.sucursal = new Sucursal();
        try {
            
            BeanUtils.copyProperties(this.sucursal, this.sucursalSelected);
            System.out.println("Actualizar sucursal: " + this.sucursal.toString());
            this.dirSucursal = this.sucursal.getDireccion();
            this.tel1Sucursal = this.sucursal.getTelefono1();
            this.tel2Sucursal = this.sucursal.getTelefono2();
            RequestContext context = RequestContext.getCurrentInstance();
            context.update(":form:sucursalDetail");
        } catch (IllegalAccessException ex) {
            Logger.getLogger(RestauranteBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(RestauranteBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void cancelar() {
        super.cancelar();
        //this.setRestSelected(null);
    }

    public void cancelarSucursal() {
        this.resetSucursal();
        this.dirSucursal = "";
        this.tel1Sucursal = "";
        this.tel2Sucursal = "";
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

    public void aceptarSucursal() {
        if (this.isEnNuevoSucursal()) {
            System.out.println("Ingresa a la funcion aceptarSucursal");
            this.sucursal.setDireccion(this.dirSucursal);
            this.sucursal.setTelefono1(this.tel1Sucursal);
            this.sucursal.setTelefono2(this.tel2Sucursal);
            this.sucursalServicio.ingresarSucursal(sucursal);

            Restaurante auxR = this.restService.obtenerRestaurantePorID(this.restSelected.getCodRestaurante());
            this.restSelected = auxR;
            System.out.println("Tamaño sucursales: " + auxR.getNombre() + " " + auxR.getSucursales().size());
            RequestContext context = RequestContext.getCurrentInstance();
            context.update(":form:sucursalDetail");
            System.out.println("Termina la funcion aceptarSucursal");

        } else {
            System.out.println("Funcion actualizar sucursal"+this.sucursal.toString());
            this.sucursal.setDireccion(this.dirSucursal);
            this.sucursal.setTelefono1(this.tel1Sucursal);
            this.sucursal.setTelefono2(this.tel2Sucursal);
            this.sucursalServicio.actualizarSucursal(sucursal);
        }
        this.resetSucursal();
        init();
    }

    public void resetSucursal() {
        this.enNuevoSucursal = false;
        this.enModificarSucursal = false;
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

    public List<Sucursal> getSucursales() {
        return sucursales;
    }

    public void setSucursales(List<Sucursal> sucursales) {
        this.sucursales = sucursales;
    }

    public Sucursal getSucursalSelected() {
        return sucursalSelected;
    }

    public void setSucursalSelected(Sucursal sucursalSelected) {
        this.sucursalSelected = sucursalSelected;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

    public Menu getMenuSelected() {
        return menuSelected;
    }

    public void setMenuSelected(Menu menuSelected) {
        this.menuSelected = menuSelected;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    public boolean isEnNuevoSucursal() {
        return enNuevoSucursal;
    }

    public void setEnNuevoSucursal(boolean enNuevoSucursal) {
        this.enNuevoSucursal = enNuevoSucursal;
    }

    public boolean isEnModificarSucursal() {
        return enModificarSucursal;
    }

    public void setEnModificarSucursal(boolean enModificarSucursal) {
        this.enModificarSucursal = enModificarSucursal;
    }

    public boolean isEnNuevoMenu() {
        return enNuevoMenu;
    }

    public void setEnNuevoMenu(boolean enNuevoMenu) {
        this.enNuevoMenu = enNuevoMenu;
    }

    public boolean isEnModificarMenu() {
        return enModificarMenu;
    }

    public void setEnModificarMenu(boolean enModificarMenu) {
        this.enModificarMenu = enModificarMenu;
    }

    public String getDirSucursal() {
        return dirSucursal;
    }

    public void setDirSucursal(String dirSucursal) {
        this.dirSucursal = dirSucursal;
    }

    public String getTel1Sucursal() {
        return tel1Sucursal;
    }

    public void setTel1Sucursal(String tel1Sucursal) {
        this.tel1Sucursal = tel1Sucursal;
    }

    public String getTel2Sucursal() {
        return tel2Sucursal;
    }

    public void setTel2Sucursal(String tel2Sucursal) {
        this.tel2Sucursal = tel2Sucursal;
    }

    public String getMenuNombre() {
        return menuNombre;
    }

    public void setMenuNombre(String menuNombre) {
        this.menuNombre = menuNombre;
    }

    public BigDecimal getMenuPrecio() {
        return menuPrecio;
    }

    public void setMenuPrecio(BigDecimal menuPrecio) {
        this.menuPrecio = menuPrecio;
    }

    public String getMenuDescripcion() {
        return menuDescripcion;
    }

    public void setMenuDescripcion(String menuDescripcion) {
        this.menuDescripcion = menuDescripcion;
    }

    public void onRowSelect(SelectEvent event) {
        FacesMessage msg = new FacesMessage("Restaurante seleccionado", ((Restaurante) event.getObject()).getNombre());
        FacesContext.getCurrentInstance().addMessage(null, msg);
        System.out.println(this.restSelected.toString());
        init();
    }

    public void onRowSucursalSelect(SelectEvent event) {
        FacesMessage msg = new FacesMessage("Sucursal seleccionada", ((Sucursal) event.getObject()).getDireccion());
        FacesContext.getCurrentInstance().addMessage(null, msg);
        System.out.println("Seleccion Sucursal: " + this.sucursalSelected.toString());
        init();
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
