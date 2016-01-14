/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espe.distribuidas.foodbet.servicios;

import com.espe.distribuidas.foodbet.dao.EventoEquipoDAO;
import com.espe.distribuidas.foodbet.exceptions.ValidacionException;
import com.espe.distribuidas.foodbet.modelo.EventoEquipo;
import com.espe.distribuidas.foodbet.modelo.EventoEquipoPK;
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
public class EquipoEventoServicio {
    
    @EJB
    private EventoEquipoDAO eventEquipoDAO;
    
    public List<EventoEquipo> obtenerTodosEventoEquipo(){
        return this.eventEquipoDAO.findAll();
    }
    
    public EventoEquipo obtenerEventoEquipoPorID(EventoEquipoPK ee){
        return this.eventEquipoDAO.findById(ee, false);
    }
    
    public List<EventoEquipo> obtenerEventoEquipoPorC(EventoEquipo ee){
        return this.eventEquipoDAO.find(ee);
    }
    
    public void ingresarEventoEquipo(EventoEquipo ee){
        this.eventEquipoDAO.insert(ee);
    }
    
    public void actualizatEventoEquipo(EventoEquipo ee){
        this.eventEquipoDAO.update(ee);
    }
    
    public void eliminarEventoEquipo(EventoEquipo ee){
        try {
            this.eventEquipoDAO.remove(ee);
        } catch (Exception e) {
            throw new ValidacionException("error al aeliminar EventoEquipo");
        }
    }
    
}
