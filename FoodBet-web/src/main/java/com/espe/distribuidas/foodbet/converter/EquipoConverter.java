/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espe.distribuidas.foodbet.converter;

import com.espe.distribuidas.foodbet.modelo.Equipo;
import com.espe.distribuidas.foodbet.servicios.EquipoServicio;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author david
 */

@FacesConverter("equipoConverter")
public class EquipoConverter implements Converter{

//    @EJB
//    private EquipoServicio equipoService;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if(value != null){
            System.out.println("Entra al converter: valor: " + value);
            Integer codEquipo = Integer.parseInt(value);
//            Equipo team = equipoService.obtenerEquipoPorID(codEquipo);
//            return team;
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return ((Equipo) value).getCodEquipo().toString();
    }
    
}
