/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espe.distribuidas.foodbet.servicios;

import com.espe.distribuidas.foodbet.dao.TipoDeporteDAO;
import com.espe.distribuidas.foodbet.exceptions.ValidacionException;
import com.espe.distribuidas.foodbet.modelo.TipoDeporte;
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
public class TipoDeporteServicio {
    
    @EJB
    private TipoDeporteDAO tipoDeporteDAO;
    
    public List<TipoDeporte> obtenerTiposDeporte(){
        return this.tipoDeporteDAO.findAll();
    }
    
    public TipoDeporte obtenerTipoDeportePorID(Integer codTD){
        return this.tipoDeporteDAO.findById(codTD, false);
    }
    
    public void ingresarTipoDeporte(TipoDeporte tipoDeport){
        this.tipoDeporteDAO.insert(tipoDeport);
    }
    
    public void actualizarTipoDeporte(TipoDeporte tipoDeporte){
        this.tipoDeporteDAO.update(tipoDeporte);
    }
    
    public void eliminarTipoDeporte(Integer codTipoDep){
        try {
            TipoDeporte td = this.tipoDeporteDAO.findById(codTipoDep, false);
            this.tipoDeporteDAO.remove(td);
        } catch (Exception e) {
            throw new ValidacionException("La registro tiene dependencias");
        }
    }
}
