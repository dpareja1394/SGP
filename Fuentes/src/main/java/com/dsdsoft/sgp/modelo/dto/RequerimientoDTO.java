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
public class RequerimientoDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(RequerimientoDTO.class);
    private String descripcionRequerimiento;
    private String nombreRequerimiento;
    private Integer requId;
    private Integer proyId_Proyecto;

    public String getDescripcionRequerimiento() {
        return descripcionRequerimiento;
    }

    public void setDescripcionRequerimiento(String descripcionRequerimiento) {
        this.descripcionRequerimiento = descripcionRequerimiento;
    }

    public String getNombreRequerimiento() {
        return nombreRequerimiento;
    }

    public void setNombreRequerimiento(String nombreRequerimiento) {
        this.nombreRequerimiento = nombreRequerimiento;
    }

    public Integer getRequId() {
        return requId;
    }

    public void setRequId(Integer requId) {
        this.requId = requId;
    }

    public Integer getProyId_Proyecto() {
        return proyId_Proyecto;
    }

    public void setProyId_Proyecto(Integer proyId_Proyecto) {
        this.proyId_Proyecto = proyId_Proyecto;
    }
}
