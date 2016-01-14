/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espe.distribuidas.foodbet.beans;

import com.espe.distribuidas.foodbet.modelo.Menu;
import com.espe.distribuidas.foodbet.modelo.Restaurante;
import com.espe.distribuidas.foodbet.modelo.Rol;
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
import org.primefaces.event.RowEditEvent;
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
    private List<Restaurante> restaurantesFiltered;
    private Restaurante restSelected;
    private Restaurante restaurante;
    private String nombre;
    private String direccion;
    private String telefono;
    private String telefonoSecundario;
    private String especialidad;

    private List<Sucursal> sucursales;
    private List<Sucursal> sucursalesFiltered;
    private Sucursal sucursalSelected;
    private Sucursal sucursal;
    private boolean enNuevoSucursal;
    private boolean enModificarSucursal;
    private String dirSucursal = "";
    private String tel1Sucursal = "";
    private String tel2Sucursal = "";

    private List<Menu> menus;
    private List<Menu> menusFiltered;
    private Menu menuSelected;
    private Menu menu;
    private boolean enNuevoMenu;
    private boolean enModificarMenu;
    private String menuNombre = "";
    private String menuPrecio = null;
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
        Sucursal s = new Sucursal();
        s.setCodRestaurante(this.restSelected.getCodRestaurante());
        this.sucursales = this.sucursalServicio.obtenerSucursalPorR(s);
        RequestContext context = RequestContext.getCurrentInstance();
        context.update(":form:sucursalDetail");
    }
    
    public void nuevoMenu(){
        this.enNuevoMenu = true;
        System.out.println("Ingreso al metodo nuevo menu");
        this.menu = new Menu();
        this.menu.setCodRestaurante(this.restSelected.getCodRestaurante());
        RequestContext context = RequestContext.getCurrentInstance();
        context.update(":form:menuDetail");
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
    
    public void actualizarMenu(){
        this.enModificarMenu = true;
        this.menu = new Menu();
        try {
            BeanUtils.copyProperties(this.menu, this.menuSelected);
            System.out.println("Actualizar menu: " + this.menu.toString());
            this.menuNombre = this.menu.getNombre();
            this.menuPrecio = this.menu.getPrecio().toString();
            this.menuDescripcion = this.menu.getDescripcion();
            RequestContext context = RequestContext.getCurrentInstance();
            context.update(":form:menuDetail");
        } catch (IllegalAccessException ex) {
            Logger.getLogger(RestauranteBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(RestauranteBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void onRowEditRestaurante(RowEditEvent event) {
        this.restSelected = ((Restaurante)event.getObject());
        this.restService.actualizarRestaurante(this.restSelected);
        FacesMessage msg = new FacesMessage("Restaurante Modificado: ",this.restSelected.getNombre());
        FacesContext.getCurrentInstance().addMessage(null, msg);
        this.restaurantes = this.restService.obtenerTodosRestaurantes();
    }

    public void onRowCancelRestaurante(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edición Cancelada", ((Restaurante)event.getObject()).getNombre());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void onRowEditSucursal(RowEditEvent event){
        this.sucursalSelected = ((Sucursal)event.getObject());
        this.sucursalServicio.actualizarSucursal(this.sucursalSelected);
        FacesMessage msg = new FacesMessage("Sucursal Modificada: ",this.sucursalSelected.getDireccion());
        FacesContext.getCurrentInstance().addMessage(null, msg);
        Sucursal s = new Sucursal();
        s.setCodRestaurante(this.restSelected.getCodRestaurante());
        this.sucursales  = this.sucursalServicio.obtenerSucursalPorR(s);
        this.restSelected.setSucursales(this.sucursales);
    }
    
    public void onRowCancelSucursal(RowEditEvent event){
        FacesMessage msg = new FacesMessage("Edición Cancelada", ((Sucursal)event.getObject()).getDireccion());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void onRowEditMenu(RowEditEvent event){
        this.menuSelected = ((Menu)event.getObject());
        this.menuServicio.actualizarMenu(this.menuSelected);
        FacesMessage msg = new FacesMessage("Menu Modificado: ",this.menuSelected.getNombre());
        FacesContext.getCurrentInstance().addMessage(null, msg);
        Menu m = new Menu();
        m.setCodRestaurante(this.restSelected.getCodRestaurante());
        this.menus = this.menuServicio.obtenerMenuPorR(m);
        this.restSelected.setMenus(this.menus);
        
        
    }
    
    public void onRowCancelMenu(RowEditEvent event){
        FacesMessage msg = new FacesMessage("Edición Cancelada", ((Menu)event.getObject()).getNombre());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void eliminarSucursal(){
        try {
            System.out.println("eliminar sucursal: " + this.sucursal);
            this.sucursalServicio.eliminarSucursal(this.sucursal.getCodSucursal());
            Sucursal s = new Sucursal();
            s.setCodRestaurante(this.restSelected.getCodRestaurante());
            this.sucursales = this.sucursalServicio.obtenerSucursalPorR(s);
            this.restSelected.setSucursales(this.sucursales);
            this.sucursal = null;
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage("Error en la eliminacion", this.sucursal.getDireccion());
            FacesContext.getCurrentInstance().addMessage(null, msg);
            this.sucursal = null;
        }
    }    
    
    public void eliminarMenu(){
        try {
            System.out.println("eliminar menu: " + this.menu);
            this.menuServicio.eliminarMenu(this.menu.getCodMenu());
            Menu m = new Menu();
            m.setCodRestaurante(this.restSelected.getCodRestaurante());
            this.menus = this.menuServicio.obtenerMenuPorR(m);
            this.restSelected.setMenus(this.menus);
            this.menuSelected = null;
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage("Error en la eliminacion", this.menu.getNombre());
            FacesContext.getCurrentInstance().addMessage(null, msg);
            this.menuSelected = null;
        }
    }

    public void mostrarMensaje(FacesMessage.Severity severityMessage, String mensaje) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(severityMessage, "Mensaje:", mensaje));
    }
    
    public void restauranteSelect(){
        Sucursal s = new Sucursal();
        s.setCodRestaurante(this.restSelected.getCodRestaurante());
        this.sucursales = this.sucursalServicio.obtenerSucursalPorR(s);
        this.restSelected.setSucursales(this.sucursales);
        
        Menu m = new Menu();
        m.setCodRestaurante(this.restSelected.getCodRestaurante());
        this.menus = this.menuServicio.obtenerMenuPorR(m);
        this.restSelected.setMenus(this.menus);
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
    
    public void cancelarMenu(){
        this.resetMenu();
        this.menuNombre = "";
        this.menuPrecio = null;
        this.menuDescripcion = "";
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
            Sucursal s = new Sucursal();
            s.setCodRestaurante(this.restSelected.getCodRestaurante());
            this.sucursales = this.sucursalServicio.obtenerSucursalPorR(s);
            this.restSelected.setSucursales(this.sucursales);

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
    
    public void aceptarMenu(){
        if(this.isEnNuevoMenu()){
            System.out.println("Ingresa a la funcion aceptarMenu");
            this.menu.setNombre(menuNombre);
            this.menu.setPrecio(new BigDecimal(menuPrecio));
            this.menu.setDescripcion(this.menuDescripcion);
            this.menuServicio.ingresarMenu(menu);
            
            this.restSelected = this.restService.obtenerRestaurantePorID(this.restSelected.getCodRestaurante());
            RequestContext context = RequestContext.getCurrentInstance();
            context.update(":form:menuDetail");
            
            Menu m = new Menu();
            m.setCodRestaurante(this.restSelected.getCodRestaurante());
            this.menus = this.menuServicio.obtenerMenuPorR(m);
            this.restSelected.setMenus(this.menus);
        }else{
            System.out.println("Funcion actualizar menu" + this.menu.toString());
            this.menu.setNombre(this.menuNombre);
            this.menu.setPrecio(new BigDecimal(this.menuPrecio));
            this.menu.setDescripcion(this.menuDescripcion);
            this.menuServicio.actualizarMenu(menu);
        }
        this.resetMenu();
    }

    public void resetSucursal() {
        this.enNuevoSucursal = false;
        this.enModificarSucursal = false;
    }
    
    public void resetMenu(){
        this.enNuevoMenu = false;
        this.enModificarMenu = false;
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

    public String getMenuPrecio() {
        return menuPrecio;
    }

    public void setMenuPrecio(String menuPrecio) {
        this.menuPrecio = menuPrecio;
    }

    public String getMenuDescripcion() {
        return menuDescripcion;
    }

    public void setMenuDescripcion(String menuDescripcion) {
        this.menuDescripcion = menuDescripcion;
    }

    public List<Restaurante> getRestaurantesFiltered() {
        return restaurantesFiltered;
    }

    public void setRestaurantesFiltered(List<Restaurante> restaurantesFiltered) {
        this.restaurantesFiltered = restaurantesFiltered;
    }

    public List<Sucursal> getSucursalesFiltered() {
        return sucursalesFiltered;
    }

    public void setSucursalesFiltered(List<Sucursal> sucursalesFiltered) {
        this.sucursalesFiltered = sucursalesFiltered;
    }

    public List<Menu> getMenusFiltered() {
        return menusFiltered;
    }

    public void setMenusFiltered(List<Menu> menusFiltered) {
        this.menusFiltered = menusFiltered;
    }
    
}
