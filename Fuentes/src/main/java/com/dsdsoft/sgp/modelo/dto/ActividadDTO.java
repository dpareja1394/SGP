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
public class ActividadDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(ActividadDTO.class);
    private Integer actiId;
    private String descripcionActividad;
    private Date fechaHoraFin;
    private Date fechaHoraInicio;
    private Integer tiacId_TipoActividad;
    private Integer usuaId_Usuario;

    public Integer getActiId() {
        return actiId;
    }

    public void setActiId(Integer actiId) {
        this.actiId = actiId;
    }

    public String getDescripcionActividad() {
        return descripcionActividad;
    }

    public void setDescripcionActividad(String descripcionActividad) {
        this.descripcionActividad = descripcionActividad;
    }

    public Date getFechaHoraFin() {
        return fechaHoraFin;
    }

    public void setFechaHoraFin(Date fechaHoraFin) {
        this.fechaHoraFin = fechaHoraFin;
    }

    public Date getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    public void setFechaHoraInicio(Date fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }

    public Integer getTiacId_TipoActividad() {
        return tiacId_TipoActividad;
    }

    public void setTiacId_TipoActividad(Integer tiacId_TipoActividad) {
        this.tiacId_TipoActividad = tiacId_TipoActividad;
    }

    public Integer getUsuaId_Usuario() {
        return usuaId_Usuario;
    }

    public void setUsuaId_Usuario(Integer usuaId_Usuario) {
        this.usuaId_Usuario = usuaId_Usuario;
    }
}
