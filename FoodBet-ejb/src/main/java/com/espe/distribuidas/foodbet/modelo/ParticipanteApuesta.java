/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espe.distribuidas.foodbet.modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author david
 */
@Entity
@Table(name = "PARTICIPANTE_APUESTA")
public class ParticipanteApuesta implements Serializable{
    
    @Id
    @Column(name = "ID_PARTICIPANTE", nullable = false)
    private Integer idParticipante;
    
    @Column(name = "NOMBRE", nullable = false)
    private String nombre;
    
    @Column(name = "APELLIDO", nullable = false)
    private String apellido;
    
    @Column(name = "DIRECCION", nullable = false)
    private String direccion;
    
    @Column(name = "CELULAR", nullable = false)
    private String celular;
    
    @Column(name = "TELEFONO", nullable = false)
    private String telefono;
    
    @Column(name = "EMAIL", nullable = false)
    private String email;

    public Integer getIdParticipante() {
        return idParticipante;
    }

    public void setIdParticipante(Integer idParticipante) {
        this.idParticipante = idParticipante;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + (this.idParticipante != null ? this.idParticipante.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ParticipanteApuesta other = (ParticipanteApuesta) obj;
        if (this.idParticipante != other.idParticipante && (this.idParticipante == null || !this.idParticipante.equals(other.idParticipante))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ParticipanteApuesta{" + "idParticipante=" + idParticipante + ", nombre=" + nombre + ", apellido=" + apellido + ", direccion=" + direccion + ", celular=" + celular + ", telefono=" + telefono + ", email=" + email + '}';
    }
    
}
