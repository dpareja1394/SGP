package com.dsdsoft.sgp.modelo.dto;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
*
* @author Zathura Code Generator http://code.google.com/p/zathura
* www.zathuracode.org
*
*/
public class HistoriaDeUsuarioDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(HistoriaDeUsuarioDTO.class);
    private String detalleHistoria;
    private Integer hiusId;
    private String tituloHistoria;
    private Integer eshiId_EstadoHistoriaUsuario;
    private Integer requId_Requerimiento;
    private Integer usuaId_Usuario;
    
    private String nombreRequerimiento, descripcionRequerimiento;

    public String getDetalleHistoria() {
        return detalleHistoria;
    }

    public void setDetalleHistoria(String detalleHistoria) {
        this.detalleHistoria = detalleHistoria;
    }

    public Integer getHiusId() {
        return hiusId;
    }

    public void setHiusId(Integer hiusId) {
        this.hiusId = hiusId;
    }

    public String getTituloHistoria() {
        return tituloHistoria;
    }

    public void setTituloHistoria(String tituloHistoria) {
        this.tituloHistoria = tituloHistoria;
    }

    public Integer getEshiId_EstadoHistoriaUsuario() {
        return eshiId_EstadoHistoriaUsuario;
    }

    public void setEshiId_EstadoHistoriaUsuario(
        Integer eshiId_EstadoHistoriaUsuario) {
        this.eshiId_EstadoHistoriaUsuario = eshiId_EstadoHistoriaUsuario;
    }

    public Integer getRequId_Requerimiento() {
        return requId_Requerimiento;
    }

    public void setRequId_Requerimiento(Integer requId_Requerimiento) {
        this.requId_Requerimiento = requId_Requerimiento;
    }

    public Integer getUsuaId_Usuario() {
        return usuaId_Usuario;
    }

    public void setUsuaId_Usuario(Integer usuaId_Usuario) {
        this.usuaId_Usuario = usuaId_Usuario;
    }

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version may. 28, 2019
	 * @since 1.8
	 * @return El/La nombreRequerimiento
	 *
	 */
	public String getNombreRequerimiento() {
		return nombreRequerimiento;
	}

	/**
	 *
	 * @param nombreRequerimiento El/La nombreRequerimiento a setear
	 * @author Daniel Pareja Londoño
	 * @version may. 28, 2019
	 * @since 1.8
	 *
	 */
	public void setNombreRequerimiento(String nombreRequerimiento) {
		this.nombreRequerimiento = nombreRequerimiento;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version may. 28, 2019
	 * @since 1.8
	 * @return El/La descripcionRequerimiento
	 *
	 */
	public String getDescripcionRequerimiento() {
		return descripcionRequerimiento;
	}

	/**
	 *
	 * @param descripcionRequerimiento El/La descripcionRequerimiento a setear
	 * @author Daniel Pareja Londoño
	 * @version may. 28, 2019
	 * @since 1.8
	 *
	 */
	public void setDescripcionRequerimiento(String descripcionRequerimiento) {
		this.descripcionRequerimiento = descripcionRequerimiento;
	}
}
