package com.dsdsoft.sgp.modelo.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

import java.sql.*;

import java.util.Date;


/**
*
* @author Zathura Code Generator http://code.google.com/p/zathura
* www.zathuracode.org
*
*/
public class UsuarioDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(UsuarioDTO.class);
    private String apellidoUsuario;
    private String emailUsuario;
    private String estadoRegistroUsuario;
    private String nombreUsuario;
    private String nudocUsuario;
    private String passwordUsuario;
    private Integer usuaId;

    public String getApellidoUsuario() {
        return apellidoUsuario;
    }

    public void setApellidoUsuario(String apellidoUsuario) {
        this.apellidoUsuario = apellidoUsuario;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public String getEstadoRegistroUsuario() {
        return estadoRegistroUsuario;
    }

    public void setEstadoRegistroUsuario(String estadoRegistroUsuario) {
        this.estadoRegistroUsuario = estadoRegistroUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getNudocUsuario() {
        return nudocUsuario;
    }

    public void setNudocUsuario(String nudocUsuario) {
        this.nudocUsuario = nudocUsuario;
    }

    public String getPasswordUsuario() {
        return passwordUsuario;
    }

    public void setPasswordUsuario(String passwordUsuario) {
        this.passwordUsuario = passwordUsuario;
    }

    public Integer getUsuaId() {
        return usuaId;
    }

    public void setUsuaId(Integer usuaId) {
        this.usuaId = usuaId;
    }
}
