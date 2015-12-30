/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espe.distribuidas.foodbet.modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author david
 */
@Entity
@Table(name = "USUARIO")
public class Usuario implements Serializable{
    
    @Id
    @Column(name = "USUARIO", nullable = false)
    private String usuario;
    
    @Column(name = "CLAVE", nullable = false)
    private String clave;
    
    @Column(name = "ESTADO", nullable = false)
    private Integer estado;
    
    @JoinColumn(name = "COD_ROL", referencedColumnName = "COD_ROL", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Rol rol;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PARTICIPANTE")
    private ParticipanteApuesta participanteApuesta;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public ParticipanteApuesta getParticipanteApuesta() {
        return participanteApuesta;
    }

    public void setParticipanteApuesta(ParticipanteApuesta participanteApuesta) {
        this.participanteApuesta = participanteApuesta;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + (this.usuario != null ? this.usuario.hashCode() : 0);
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
        final Usuario other = (Usuario) obj;
        if ((this.usuario == null) ? (other.usuario != null) : !this.usuario.equals(other.usuario)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Usuario{" + "usuario=" + usuario + ", clave=" + clave + ", estado=" + estado + ", rol=" + rol + '}';
    }
    
}
