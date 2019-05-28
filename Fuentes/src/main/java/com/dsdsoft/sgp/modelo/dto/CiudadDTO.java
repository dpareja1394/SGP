package com.dsdsoft.sgp.modelo.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

import java.sql.*;

import java.util.Date;


/**
*
* @author Zathura Code Generator http://zathuracode.org/
* www.zathuracode.org
*
*/
public class CiudadDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(CiudadDTO.class);
    private Integer ciudId;
    private String nombreCiudad;
    private Integer depaId_Departamento;

    public Integer getCiudId() {
        return ciudId;
    }

    public void setCiudId(Integer ciudId) {
        this.ciudId = ciudId;
    }

    public String getNombreCiudad() {
        return nombreCiudad;
    }

    public void setNombreCiudad(String nombreCiudad) {
        this.nombreCiudad = nombreCiudad;
    }

    public Integer getDepaId_Departamento() {
        return depaId_Departamento;
    }

    public void setDepaId_Departamento(Integer depaId_Departamento) {
        this.depaId_Departamento = depaId_Departamento;
    }
}
