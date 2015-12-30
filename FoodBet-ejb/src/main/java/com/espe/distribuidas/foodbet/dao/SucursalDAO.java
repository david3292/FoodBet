/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espe.distribuidas.foodbet.dao;

import com.espe.distribuidas.commons.dao.DefaultGenericDAOImple;
import com.espe.distribuidas.foodbet.modelo.Sucursal;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author david
 */
@LocalBean
@Stateless 
public class SucursalDAO extends DefaultGenericDAOImple<Sucursal, Integer>{

    public SucursalDAO() {
        super(Sucursal.class);
    }
    
}
