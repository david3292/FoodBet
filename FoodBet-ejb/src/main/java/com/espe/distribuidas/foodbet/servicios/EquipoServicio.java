/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espe.distribuidas.foodbet.servicios;

import com.espe.distribuidas.foodbet.dao.EquipoDAO;
import com.espe.distribuidas.foodbet.exceptions.ValidacionException;
import com.espe.distribuidas.foodbet.modelo.Equipo;
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
public class EquipoServicio {
    
    @EJB
    private EquipoDAO equipoDAO;
    
    public List<Equipo> obtenerTodosLosEquipos(){
        return this.equipoDAO.findAll();
    }
    
    public Equipo obtenerEquipoPorID(Integer codEquipo){
        return this.equipoDAO.findById(codEquipo, false);
    }
    
    public void insertarEquipo(Equipo e){
        this.equipoDAO.insert(e);
    }
    
    public void actualizarEquipo(Equipo e){
        this.equipoDAO.update(e);
    }
    
    public void eliminarEquipo(Integer codEquipo){
        try {
            Equipo equipo = this.equipoDAO.findById(codEquipo, false);
            this.equipoDAO.remove(equipo);
        } catch (Exception e) {
            throw new ValidacionException("No se puede eliminar el equipo, porque tiene dependencias");
        }
    }
    
}
