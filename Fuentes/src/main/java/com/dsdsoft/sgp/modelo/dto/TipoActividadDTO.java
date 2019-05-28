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
public class TipoActividadDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(TipoActividadDTO.class);
    private String descripcionTiact;
    private String nombreCorto;
    private Integer tiacId;

    public String getDescripcionTiact() {
        return descripcionTiact;
    }

    public void setDescripcionTiact(String descripcionTiact) {
        this.descripcionTiact = descripcionTiact;
    }

    public String getNombreCorto() {
        return nombreCorto;
    }

    public void setNombreCorto(String nombreCorto) {
        this.nombreCorto = nombreCorto;
    }

    public Integer getTiacId() {
        return tiacId;
    }

    public void setTiacId(Integer tiacId) {
        this.tiacId = tiacId;
    }
}
