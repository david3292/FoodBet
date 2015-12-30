/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espe.distribuidas.foodbet.dao;

import com.espe.distribuidas.commons.dao.DefaultGenericDAOImple;
import com.espe.distribuidas.foodbet.modelo.Menu;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author david
 */
@LocalBean
@Stateless 
public class MenuDAO extends DefaultGenericDAOImple<Menu, Integer>{

    public MenuDAO() {
        super(Menu.class);
    }
    
}
