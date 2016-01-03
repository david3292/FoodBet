/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espe.distribuidas.foodbet.servicios;

import com.espe.distribuidas.foodbet.dao.MenuDAO;
import com.espe.distribuidas.foodbet.exceptions.ValidacionException;
import com.espe.distribuidas.foodbet.modelo.Menu;
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
public class MenuServicio {
    
    @EJB
    private MenuDAO menuDAO;
    
    public List<Menu> obtenerTodoElMenu(){
        return this.menuDAO.findAll();
    }
    
    public Menu obtenerMenuPorID(Integer codMenu){
        return this.menuDAO.findById(codMenu, false);
    }
    
    public void ingresarMenu(Menu manu){
        this.menuDAO.insert(manu);
    }
    
    public void actualizarMenu(Menu menu){
        this.menuDAO.update(menu);
    }
    
    public void eliminarMenu(Integer codMenu){
        try{
            Menu menu = this.menuDAO.findById(codMenu, false);
            this.menuDAO.remove(menu);
        }catch(Exception e){
            throw new ValidacionException("No se puede eliminar el menu");
        }
    }
    
}
