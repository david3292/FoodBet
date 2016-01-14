/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espe.distribuidas.foodbet.servicios;

import com.espe.distribuidas.foodbet.dao.ApuestaMenuDAO;
import com.espe.distribuidas.foodbet.exceptions.ValidacionException;
import com.espe.distribuidas.foodbet.modelo.ApuestaMenu;
import com.espe.distribuidas.foodbet.modelo.ApuestaMenuPK;
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
public class ApuestaMenuServicio {

    @EJB
    private ApuestaMenuDAO apuestaManuDAO;

    public List<ApuestaMenu> obtenerApuestasMenus() {
        return this.apuestaManuDAO.findAll();
    }

    public ApuestaMenu obtenerApuestaMenuPorID(ApuestaMenuPK am) {
        return this.apuestaManuDAO.findById(am, false);
    }

    public void ingresarApuestaMenu(ApuestaMenu am) {
        this.apuestaManuDAO.insert(am);
    }

    public void actualizarApuestaMenu(ApuestaMenu am) {
        System.out.println("Ingresa a la actualizacion");
        try {
            this.apuestaManuDAO.update(am);
        } catch (Exception e) {
            throw new ValidacionException("No se pudoa actualizar la apuesta menu "+e);
        }
    }

    public void eliminarApuestaMenu(ApuestaMenuPK codAM) {
        try {
            ApuestaMenu ap = this.apuestaManuDAO.findById(codAM, false);
            this.apuestaManuDAO.remove(ap);
        } catch (Exception e) {
            throw new ValidacionException("No se pudo eliminar el menu apostado");
        }
    }

}
