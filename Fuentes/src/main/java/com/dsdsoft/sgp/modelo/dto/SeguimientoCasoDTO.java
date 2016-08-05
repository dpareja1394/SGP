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
public class SeguimientoCasoDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(SeguimientoCasoDTO.class);
    private String descripcionActividad;
    private Integer secaId;
    private Integer casoId_CasoSoporte;
    private Integer usuaId_Usuario;

    public String getDescripcionActividad() {
        return descripcionActividad;
    }

    public void setDescripcionActividad(String descripcionActividad) {
        this.descripcionActividad = descripcionActividad;
    }

    public Integer getSecaId() {
        return secaId;
    }

    public void setSecaId(Integer secaId) {
        this.secaId = secaId;
    }

    public Integer getCasoId_CasoSoporte() {
        return casoId_CasoSoporte;
    }

    public void setCasoId_CasoSoporte(Integer casoId_CasoSoporte) {
        this.casoId_CasoSoporte = casoId_CasoSoporte;
    }

    public Integer getUsuaId_Usuario() {
        return usuaId_Usuario;
    }

    public void setUsuaId_Usuario(Integer usuaId_Usuario) {
        this.usuaId_Usuario = usuaId_Usuario;
    }
}
