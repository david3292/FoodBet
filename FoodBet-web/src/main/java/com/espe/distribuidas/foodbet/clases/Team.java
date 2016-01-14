/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espe.distribuidas.foodbet.clases;

import com.espe.distribuidas.foodbet.modelo.Equipo;
import com.espe.distribuidas.foodbet.servicios.EquipoServicio;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author david
 */
public class Team {
    
    private static List<Equipo> teams;
    
    public Team (){
    }
    
    public Equipo obtenerEquipoPorID(Integer id){
        Equipo e = null;
        for(Equipo ee : teams){
            if(ee.getCodEquipo() == id){
                e = ee;
            }
        }
        return e;
    }

    public List<Equipo> getTeams() {
        return teams;
    }

    public void setTeams(List<Equipo> teams) {
        System.out.println("metodo estatico: " +teams);
        this.teams = teams;
    }
    
    
}
