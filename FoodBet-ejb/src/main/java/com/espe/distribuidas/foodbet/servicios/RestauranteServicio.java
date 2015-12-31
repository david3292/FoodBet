/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espe.distribuidas.foodbet.servicios;

import com.espe.distribuidas.foodbet.dao.RestauranteDAO;
import com.espe.distribuidas.foodbet.exceptions.ValidacionException;
import com.espe.distribuidas.foodbet.modelo.Restaurante;
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
public class RestauranteServicio {
    
    @EJB
    private RestauranteDAO restauranteDAO;
    
    public List<Restaurante> obtenerTodosRestaurantes(){
        return this.restauranteDAO.findAll();
    }
    
    public Restaurante obtenerRestaurantePorID(Integer codRestaurante){
        return this.restauranteDAO.findById(codRestaurante, false);
    }
    
    public void ingresarRestaurante(Restaurante restaurante){
        this.restauranteDAO.insert(restaurante);
    }
    
    public void actualizarRestaurante(Restaurante restaurante){
        this.restauranteDAO.update(restaurante);
    }
    
    public void eliminarRestaurante(Integer codRestaurante){
        try{
            Restaurante rest = this.restauranteDAO.findById(codRestaurante, false);
            this.restauranteDAO.remove(rest);
        }catch(Exception e){
            throw new ValidacionException("Es restaurante no se puede eliminar porque esta asociado");
        }
    }
}
