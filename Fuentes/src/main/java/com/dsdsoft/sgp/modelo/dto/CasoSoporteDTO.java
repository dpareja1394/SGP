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
public class CasoSoporteDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(CasoSoporteDTO.class);
    private Integer casoId;
    private String descripcionCaso;
    private String tituloCaso;
    private Integer proyId_Proyecto;
    private Integer usuaId_Usuario;

    public Integer getCasoId() {
        return casoId;
    }

    public void setCasoId(Integer casoId) {
        this.casoId = casoId;
    }

    public String getDescripcionCaso() {
        return descripcionCaso;
    }

    public void setDescripcionCaso(String descripcionCaso) {
        this.descripcionCaso = descripcionCaso;
    }

    public String getTituloCaso() {
        return tituloCaso;
    }

    public void setTituloCaso(String tituloCaso) {
        this.tituloCaso = tituloCaso;
    }

    public Integer getProyId_Proyecto() {
        return proyId_Proyecto;
    }

    public void setProyId_Proyecto(Integer proyId_Proyecto) {
        this.proyId_Proyecto = proyId_Proyecto;
    }

    public Integer getUsuaId_Usuario() {
        return usuaId_Usuario;
    }

    public void setUsuaId_Usuario(Integer usuaId_Usuario) {
        this.usuaId_Usuario = usuaId_Usuario;
    }
}
