/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espe.distribuidas.foodbet.modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author david
 */
@Entity
@Table(name = "RESTAURANTE")
public class Restaurante implements Serializable{
    
    @Id
    @Column(name = "COD_RESTAURANTE", nullable = false)
    private Integer codRestaurante;
    
    @Column(name = "NOMBRE", nullable = false)
    private String nombre;
    
    @Column(name = "DIRECCION", nullable = false)
    private String direccion;
    
    @Column(name = "TELEFONO1", nullable = false)
    private String telefono1;
    
    @Column(name = "TELEFONO2", nullable = true)
    private String telefono2;
    
    @Column(name = "ESPECIALIDAD", nullable = false)
    private String especialidad;
    
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "restaurante")
    private List<Sucursal> sucursales;
    
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "restaurante")
    private List<Menu> menus;

    public Integer getCodRestaurante() {
        return codRestaurante;
    }

    public void setCodRestaurante(Integer codRestaurante) {
        this.codRestaurante = codRestaurante;
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

    public String getTelefono1() {
        return telefono1;
    }

    public void setTelefono1(String telefono1) {
        this.telefono1 = telefono1;
    }

    public String getTelefono2() {
        return telefono2;
    }

    public void setTelefono2(String telefono2) {
        this.telefono2 = telefono2;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }
    
    public List<Sucursal> getSucursales() {
        return sucursales;
    }

    public void setSucursales(List<Sucursal> sucursales) {
        this.sucursales = sucursales;
    }
    
    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + (this.codRestaurante != null ? this.codRestaurante.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Restaurante other = (Restaurante) obj;
        if (this.codRestaurante != other.codRestaurante && (this.codRestaurante == null || !this.codRestaurante.equals(other.codRestaurante))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Restaurante{" + "codRestaurante=" + codRestaurante + ", nombre=" + nombre + ", direccion=" + direccion + ", telefono1=" + telefono1 + ", telefono2=" + telefono2 + ", especialidad=" + especialidad + '}';
    }
    
}
