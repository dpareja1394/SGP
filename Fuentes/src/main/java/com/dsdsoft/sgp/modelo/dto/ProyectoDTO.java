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
public class ProyectoDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(ProyectoDTO.class);
    private String descProyecto;
    private Integer proyId;
    private Integer clieId_Cliente;
    private Integer esprId_EstadoProyecto;
    private String descripcionEstado;

    public String getDescProyecto() {
        return descProyecto;
    }

    public void setDescProyecto(String descProyecto) {
        this.descProyecto = descProyecto;
    }

    public Integer getProyId() {
        return proyId;
    }

    public void setProyId(Integer proyId) {
        this.proyId = proyId;
    }

    public Integer getClieId_Cliente() {
        return clieId_Cliente;
    }

    public void setClieId_Cliente(Integer clieId_Cliente) {
        this.clieId_Cliente = clieId_Cliente;
    }

    public Integer getEsprId_EstadoProyecto() {
        return esprId_EstadoProyecto;
    }

    public void setEsprId_EstadoProyecto(Integer esprId_EstadoProyecto) {
        this.esprId_EstadoProyecto = esprId_EstadoProyecto;
    }

	public String getDescripcionEstado() {
		return descripcionEstado;
	}

	public void setDescripcionEstado(String descripcionEstado) {
		this.descripcionEstado = descripcionEstado;
	}
}
