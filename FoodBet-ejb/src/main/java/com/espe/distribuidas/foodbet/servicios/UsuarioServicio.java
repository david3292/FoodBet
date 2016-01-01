/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espe.distribuidas.foodbet.servicios;

import com.espe.distribuidas.foodbet.dao.UsuarioDAO;
import com.espe.distribuidas.foodbet.exceptions.ValidacionException;
import com.espe.distribuidas.foodbet.modelo.Usuario;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author david
 */
@LocalBean
@Stateless
public class UsuarioServicio {

    /**
     * variable que permite la conecion con el DAO usuario
     */
    @EJB
    private UsuarioDAO usuarioDAO;

    public Usuario obtenerUsuarioPorID(String usuario) {
        return this.usuarioDAO.findById(usuario, false);
    }

    public void ingresarUsuario(Usuario usuario) throws ValidacionException {
        Usuario user = this.obtenerUsuarioPorID(usuario.getUsuario());
        if (user == null) {
            String claveMd5 = DigestUtils.md5Hex(usuario.getClave());
            usuario.setClave(claveMd5);
            this.usuarioDAO.insert(user);
        } else {
            throw new ValidacionException(null);
        }
    }
    
    public boolean login(String usuario, String clave){
        boolean correcto = false;
        
        Usuario user = this.usuarioDAO.findById(usuario, false);
        if(user != null){
            String claveMd5 = DigestUtils.md5Hex(clave);
            if(user.getClave().equals(claveMd5))
                correcto = true;
        }else{
            throw new ValidacionException("No se encontro el usuario");
        }
        return correcto;
    }
}
