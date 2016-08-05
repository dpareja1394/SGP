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
public class ParametroDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(ParametroDTO.class);
    private String descripcionParametro;
    private Integer idPara;
    private String nombreCorto;
    private String valorParametro;

    public String getDescripcionParametro() {
        return descripcionParametro;
    }

    public void setDescripcionParametro(String descripcionParametro) {
        this.descripcionParametro = descripcionParametro;
    }

    public Integer getIdPara() {
        return idPara;
    }

    public void setIdPara(Integer idPara) {
        this.idPara = idPara;
    }

    public String getNombreCorto() {
        return nombreCorto;
    }

    public void setNombreCorto(String nombreCorto) {
        this.nombreCorto = nombreCorto;
    }

    public String getValorParametro() {
        return valorParametro;
    }

    public void setValorParametro(String valorParametro) {
        this.valorParametro = valorParametro;
    }
}
