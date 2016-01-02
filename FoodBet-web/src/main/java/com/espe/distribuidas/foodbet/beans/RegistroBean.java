/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espe.distribuidas.foodbet.beans;

import com.espe.distribuidas.foodbet.modelo.ParticipanteApuesta;
import com.espe.distribuidas.foodbet.modelo.Rol;
import com.espe.distribuidas.foodbet.modelo.Usuario;
import com.espe.distribuidas.foodbet.servicios.ParticipanteApuestaServicio;
import com.espe.distribuidas.foodbet.servicios.RolServicio;
import com.espe.distribuidas.foodbet.servicios.UsuarioServicio;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Juanjo
 */
@ManagedBean
@ViewScoped
public class RegistroBean implements Serializable {

    private String nickname;
    private String clave;
    private String nombre;
    private String apellido;
    private String direccion;
    private String celular;
    private String telefono;
    private String mail;

    private List<ParticipanteApuesta> participantes;
    private List<Usuario> usuarios;

    @EJB
    private UsuarioServicio userService;

    @EJB
    private ParticipanteApuestaServicio participanteService;

    @EJB
    private RolServicio rolServicio;

    public String registrarUsuario() {
        String direccionar = "registro";
        participantes = new ArrayList<ParticipanteApuesta>();
        participantes = participanteService.obtenerParticipantePorMAIL(mail);
        if (participantes.size() > 0) {
            mostrarMensaje(FacesMessage.SEVERITY_ERROR, "El correo utilizado ya existe");
        } else {
            Usuario usuario = userService.obtenerUsuarioPorID(nickname);
            if (usuario != null) {
                mostrarMensaje(FacesMessage.SEVERITY_ERROR, "El usuario ingresado ya existe");
            } else {
                nuevoParticipante();
                participantes = participanteService.obtenerParticipantePorMAIL(mail);
                if (participantes.size() == 0) {
                    mostrarMensaje(FacesMessage.SEVERITY_INFO, "Participante no ingresado");
                } else {
                    try {
                        nuevoUsuario(participantes.get(0));
                        mostrarMensaje(FacesMessage.SEVERITY_INFO, "Usuario ingresado correctamente");
                        direccionar = "login";
                    } catch (Exception e) {
                        mostrarMensaje(FacesMessage.SEVERITY_ERROR, "Usuario no ingresado " + e);
                        participanteService.eliminarParticipanteApuesta(participantes.get(0).getIdParticipante());
                    }
                }

            }
        }
        return direccionar;
    }

    public String cancelar() {
        return "login";
    }

    private void nuevoUsuario(ParticipanteApuesta participante) {
        Usuario user = new Usuario();
        user.setUsuario(nickname);
        user.setClave(clave);
        user.setEstado(1);// estado 1 activo, 0 inactivo
        user.setParticipanteApuesta(participante);
        userService.ingresarUsuario(user);
    }

    private void nuevoParticipante() {
        ParticipanteApuesta participante = new ParticipanteApuesta();
        participante.setNombre(nombre);
        participante.setApellido(apellido);
        participante.setDireccion(direccion);
        participante.setCelular(celular);
        participante.setTelefono(telefono);
        participante.setEmail(mail);
        participanteService.ingresarParticipante(participante);
    }

    public void mostrarMensaje(FacesMessage.Severity severityMessage, String mensaje) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(severityMessage, "Mensaje:", mensaje));
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

}
