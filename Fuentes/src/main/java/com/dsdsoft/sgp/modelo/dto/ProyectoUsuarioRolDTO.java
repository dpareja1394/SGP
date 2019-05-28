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
public class ProyectoUsuarioRolDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(ProyectoUsuarioRolDTO.class);
    private Long usuaId;
    private Long proyId;
    private Integer proyId_Proyecto;
    private Integer rolId_Rol;
    private Integer usuaId_Usuario;
    
    private String nombreCompletoUsuario, emailUsuario, descripcionRol, descripcionProyecto;

    public Long getUsuaId() {
        return usuaId;
    }

    public void setUsuaId(Long usuaId) {
        this.usuaId = usuaId;
    }

    public Long getProyId() {
        return proyId;
    }

    public void setProyId(Long proyId) {
        this.proyId = proyId;
    }

    public Integer getProyId_Proyecto() {
        return proyId_Proyecto;
    }

    public void setProyId_Proyecto(Integer proyId_Proyecto) {
        this.proyId_Proyecto = proyId_Proyecto;
    }

    public Integer getRolId_Rol() {
        return rolId_Rol;
    }

    public void setRolId_Rol(Integer rolId_Rol) {
        this.rolId_Rol = rolId_Rol;
    }

    public Integer getUsuaId_Usuario() {
        return usuaId_Usuario;
    }

    public void setUsuaId_Usuario(Integer usuaId_Usuario) {
        this.usuaId_Usuario = usuaId_Usuario;
    }

	public String getNombreCompletoUsuario() {
		return nombreCompletoUsuario;
	}

	public void setNombreCompletoUsuario(String nombreCompletoUsuario) {
		this.nombreCompletoUsuario = nombreCompletoUsuario;
	}

	public String getEmailUsuario() {
		return emailUsuario;
	}

	public void setEmailUsuario(String emailUsuario) {
		this.emailUsuario = emailUsuario;
	}

	public String getDescripcionRol() {
		return descripcionRol;
	}

	public void setDescripcionRol(String descripcionRol) {
		this.descripcionRol = descripcionRol;
	}

	public String getDescripcionProyecto() {
		return descripcionProyecto;
	}

	public void setDescripcionProyecto(String descripcionProyecto) {
		this.descripcionProyecto = descripcionProyecto;
	}
}
