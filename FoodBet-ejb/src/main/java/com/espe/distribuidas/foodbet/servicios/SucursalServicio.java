/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espe.distribuidas.foodbet.servicios;

import com.espe.distribuidas.foodbet.dao.SucursalDAO;
import com.espe.distribuidas.foodbet.exceptions.ValidacionException;
import com.espe.distribuidas.foodbet.modelo.Sucursal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author david
 */
@LocalBean
@Stateless
public class SucursalServicio {
    
    @EJB
    private SucursalDAO sucursalDAO;
    
    public List<Sucursal> obtenerTodasSucursales(){
        return this.sucursalDAO.findAll();
    }
    
    public Sucursal obtenerSucursalPorID(Integer codSucursal){
        return this.sucursalDAO.findById(codSucursal, false);
    }
    
    public void ingresarSucursal(Sucursal sucursal){
        this.sucursalDAO.insert(sucursal);
    }
    
    public List<Sucursal> obtenerSucursalPorR(Sucursal s){
        return this.sucursalDAO.find(s);
    }
    
    public void actualizarSucursal(Sucursal sucursal){
        this.sucursalDAO.update(sucursal);
    }
    
    public void eliminarSucursal(Integer codSucursal){
        try{
            Sucursal sucursal = this.sucursalDAO.findById(codSucursal, false);
            this.sucursalDAO.remove(sucursal);
        }catch(Exception e){
            throw new ValidacionException("No se puede eliminar la sucursal");
        }
    }
    
}
