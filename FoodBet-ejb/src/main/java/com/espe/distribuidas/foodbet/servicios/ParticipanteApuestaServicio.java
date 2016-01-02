/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espe.distribuidas.foodbet.servicios;

import com.espe.distribuidas.foodbet.dao.ParticipanteApuestaDAO;
import com.espe.distribuidas.foodbet.exceptions.ValidacionException;
import com.espe.distribuidas.foodbet.modelo.ParticipanteApuesta;
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
public class ParticipanteApuestaServicio {
    
    @EJB
    private ParticipanteApuestaDAO paDAO;
    
    public List<ParticipanteApuesta> obtenerParticipantesApuesta(){
        return this.paDAO.findAll();
    }
    
    public ParticipanteApuesta obtenerParApuestaPorID(Integer codPA){
        return this.paDAO.findById(codPA, false);
    }
    
    public void ingresarParticipante(ParticipanteApuesta pa){
        this.paDAO.insert(pa);
    }
    
    public void actualizarParticipante(ParticipanteApuesta pa){
        this.paDAO.update(pa);
    }
    
    public void eliminarParticipanteApuesta(Integer codPA){
        try {
            ParticipanteApuesta ps = this.paDAO.findById(codPA, false);
            this.paDAO.remove(ps);
        } catch (Exception e) {
            throw  new ValidacionException("No se puede eliminar el participante");
        }
    }
}
