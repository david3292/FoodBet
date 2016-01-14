/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espe.distribuidas.foodbet.servicios;

import com.espe.distribuidas.foodbet.dao.ApuestaDAO;
import com.espe.distribuidas.foodbet.exceptions.ValidacionException;
import com.espe.distribuidas.foodbet.modelo.Apuesta;
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
public class ApuestaServicio {
    
    @EJB
    private ApuestaDAO apuestaDAO;
    
    public List<Apuesta> obtenerTodasLasApuestas(){
        return this.apuestaDAO.findAll();
    }
    
    public Apuesta obteneApuestaPorID(Integer codApuesta){
        return this.apuestaDAO.findById(codApuesta, false);
    }
    
    public void ingresarApuesta(Apuesta a){
        this.apuestaDAO.insert(a);
    }
    
    public void actualizarApuesta(Apuesta a){
        this.apuestaDAO.update(a);
    }
    
    public List<Apuesta> obtenerApuestaPorParticipanteEvento(Integer p, Integer e){
        Apuesta a = new Apuesta();
        a.setIdParticipante(p);
        a.setCodEvento(e);
        return this.apuestaDAO.find(a);
    }
    
    public List<Apuesta> obtenerApuestaCodGanador(Integer c){
        Apuesta a = new Apuesta();
        a.setGanadorApuesta(c);
        return this.apuestaDAO.find(a);
    }
    
    public List<Apuesta> obtenerApuestaCodPerdedor(Integer c){
        Apuesta a = new Apuesta();
        a.setPerdedorApuesta(c);
        return this.apuestaDAO.find(a);
    }
    
    public void eliminarApuesta(Integer codApuesta){
        try {
            Apuesta apuesta = this.apuestaDAO.findById(codApuesta, false);
            this.apuestaDAO.remove(apuesta);
        } catch (Exception e) {
            throw  new ValidacionException("No se puede eliminar la apuesta");
        }
    }
}