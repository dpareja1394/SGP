package com.dsdsoft.sgp.modelo;
// Generated 24/06/2016 11:19:56 PM by Hibernate Tools 4.0.0

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Cliente generated by hbm2java
 */
public class Cliente implements java.io.Serializable {

	private Integer clieId;
	private Ciudad ciudad;
	private String nombreEmpresa;
	private String nit;
	private String telefonoContacto;
	private String direccionContacto;
	private String nombreContacto;
	private String celularContacto;
	private String enlaceWeb;
	private Usuario usuarioByUsuarioCreacion;
	private Usuario usuarioByUsuarioModificacion;
	private Date fechaCreacion;
	private Date fechaModificacion;
	private String emailContacto;
	private Set<Proyecto> proyectos = new HashSet<Proyecto>(0);

	public Cliente() {
	}

	public Cliente(Integer clieId, Ciudad ciudad, String telefonoContacto, String nombreContacto,
			String celularContacto, Date fechaCreacion, Usuario usuarioByUsuarioCreacion) {
		this.clieId = clieId;
		this.telefonoContacto = telefonoContacto;
		this.ciudad = ciudad;
		this.nombreContacto = nombreContacto;
		this.celularContacto = celularContacto;
		this.fechaCreacion = fechaCreacion;
		this.usuarioByUsuarioCreacion = usuarioByUsuarioCreacion;
	}

	public Cliente(Integer clieId, Ciudad ciudad, String nombreEmpresa, String nit, String telefonoContacto,
			String direccionContacto, String nombreContacto, String celularContacto, String enlaceWeb,
			Set<Proyecto> proyectos, Date fechaCreacion, Date fechaModificacion, Usuario usuarioByUsuarioCreacion,
			Usuario usuarioByUsuarioModificacion, String emailContacto) {
		this.clieId = clieId;
		this.ciudad = ciudad;
		this.nombreEmpresa = nombreEmpresa;
		this.nit = nit;
		this.telefonoContacto = telefonoContacto;
		this.direccionContacto = direccionContacto;
		this.nombreContacto = nombreContacto;
		this.celularContacto = celularContacto;
		this.enlaceWeb = enlaceWeb;
		this.proyectos = proyectos;
		this.fechaCreacion = fechaCreacion;
		this.fechaModificacion = fechaModificacion;
		this.usuarioByUsuarioCreacion = usuarioByUsuarioCreacion;
		this.usuarioByUsuarioModificacion = usuarioByUsuarioModificacion;
		this.emailContacto = emailContacto;
	}

	public Integer getClieId() {
		return this.clieId;
	}

	public void setClieId(Integer clieId) {
		this.clieId = clieId;
	}

	public Ciudad getCiudad() {
		return this.ciudad;
	}

	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}

	public String getNombreEmpresa() {
		return this.nombreEmpresa;
	}

	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}

	public String getNit() {
		return this.nit;
	}

	public void setNit(String nit) {
		this.nit = nit;
	}

	public String getTelefonoContacto() {
		return this.telefonoContacto;
	}

	public void setTelefonoContacto(String telefonoContacto) {
		this.telefonoContacto = telefonoContacto;
	}

	public String getDireccionContacto() {
		return this.direccionContacto;
	}

	public void setDireccionContacto(String direccionContacto) {
		this.direccionContacto = direccionContacto;
	}

	public String getNombreContacto() {
		return this.nombreContacto;
	}

	public void setNombreContacto(String nombreContacto) {
		this.nombreContacto = nombreContacto;
	}

	public String getCelularContacto() {
		return this.celularContacto;
	}

	public void setCelularContacto(String celularContacto) {
		this.celularContacto = celularContacto;
	}

	public String getEnlaceWeb() {
		return this.enlaceWeb;
	}

	public void setEnlaceWeb(String enlaceWeb) {
		this.enlaceWeb = enlaceWeb;
	}

	public Set<Proyecto> getProyectos() {
		return this.proyectos;
	}

	public void setProyectos(Set<Proyecto> proyectos) {
		this.proyectos = proyectos;
	}

	public Usuario getUsuarioByUsuarioCreacion() {
		return usuarioByUsuarioCreacion;
	}

	public void setUsuarioByUsuarioCreacion(Usuario usuarioByUsuarioCreacion) {
		this.usuarioByUsuarioCreacion = usuarioByUsuarioCreacion;
	}

	public Usuario getUsuarioByUsuarioModificacion() {
		return usuarioByUsuarioModificacion;
	}

	public void setUsuarioByUsuarioModificacion(Usuario usuarioByUsuarioModificacion) {
		this.usuarioByUsuarioModificacion = usuarioByUsuarioModificacion;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public String getEmailContacto() {
		return emailContacto;
	}

	public void setEmailContacto(String emailContacto) {
		this.emailContacto = emailContacto;
	}

}
