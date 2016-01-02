/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espe.distribuidas.foodbet.servicios;

import com.espe.distribuidas.foodbet.dao.EventoDeportivoDAO;
import com.espe.distribuidas.foodbet.exceptions.ValidacionException;
import com.espe.distribuidas.foodbet.modelo.EventoDeportivo;
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
public class EventoDeportivoServicio {
    
    @EJB
    private EventoDeportivoDAO eventDeportivoDAO;
    
    public List<EventoDeportivo> obtenerEventDeportivos(){
        return this.eventDeportivoDAO.findAll();
    }
    
    public EventoDeportivo obtenerEventDepPorID(Integer codEveDep){
        return this.eventDeportivoDAO.findById(codEveDep, false);
    }
    
    public void ingresarEventoDeportivo(EventoDeportivo ed){
        this.eventDeportivoDAO.insert(ed);
    }
    
    public void actualizarEventDeportivo(EventoDeportivo ed){
        this.eventDeportivoDAO.update(ed);
    }
    
    public void eliminarEventoDeportivo(Integer codED){
        try {
            EventoDeportivo ed = this.eventDeportivoDAO.findById(codED, false);
            this.eventDeportivoDAO.remove(ed);
        } catch (Exception e) {
            throw new ValidacionException("No se pudo eliminar el evendo deportivo");
        }
    }
}
