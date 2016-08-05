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
public class EstadoProyectoDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(EstadoProyectoDTO.class);
    private String descripcionEstado;
    private Integer esprId;
    private String nombreCorto;

    public String getDescripcionEstado() {
        return descripcionEstado;
    }

    public void setDescripcionEstado(String descripcionEstado) {
        this.descripcionEstado = descripcionEstado;
    }

    public Integer getEsprId() {
        return esprId;
    }

    public void setEsprId(Integer esprId) {
        this.esprId = esprId;
    }

    public String getNombreCorto() {
        return nombreCorto;
    }

    public void setNombreCorto(String nombreCorto) {
        this.nombreCorto = nombreCorto;
    }
}
