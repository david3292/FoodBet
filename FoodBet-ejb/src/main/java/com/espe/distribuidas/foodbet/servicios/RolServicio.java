/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espe.distribuidas.foodbet.servicios;

import com.espe.distribuidas.foodbet.dao.RolDAO;
import com.espe.distribuidas.foodbet.exceptions.ValidacionException;
import com.espe.distribuidas.foodbet.modelo.Rol;
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
public class RolServicio {
    
    /**
     * Esta variable permite la conexion con el DAO del Rol
     */    
    @EJB
    private RolDAO rolDAO;
    
    /**
     * Permite obtener todos los roles que existen para ingresar
     * al sistema
     * @return devuelve una lista con todos los roles existentes
     */
    public List<Rol> obtenerTodosRoles(){
        return this.rolDAO.findAll();
    }
    
    public Rol obtenerRolPorID(Integer id){
        return this.rolDAO.findById(id, false);
    }
    
    /**
     * Permite ingresar un nuevo rol a la base de datos
     * @param rol es el nuevo rol
     */
    public void ingresarRol(Rol rol){
        this.rolDAO.insert(rol);
    }
    
    /**
     * Permite modificar el rol en la base de datos
     * @param rol es el parametro con los nuevos datos
     */
    public void actualizarRol(Rol rol){
        this.rolDAO.update(rol);
    }
    
    /**
     * Permite eliminar un rol de la base de datos
     * @param codRol es la clave primaria 
     */   
    public void eliminarRol(Integer codRol){
        try{
            Rol rol = this.rolDAO.findById(codRol, false);
            this.rolDAO.remove(rol);
        }catch(Exception e){
            throw new ValidacionException("El rol que intenta eliminar se encuentra asociado ");
        }
    }
}
