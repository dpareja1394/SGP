package com.dsdsoft.sgp.modelo;
// Generated 24/06/2016 11:19:56 PM by Hibernate Tools 4.0.0


import java.util.Date;

/**
 * Actividad generated by hbm2java
 */
public class Actividad  implements java.io.Serializable {


     private Integer actiId;
     private Usuario usuario;
     private TipoActividad tipoActividad;
     private String descripcionActividad;
     private Date fechaHoraInicio;
     private Date fechaHoraFin;
     private HistoriaDeUsuario historiaDeUsuario;

    public Actividad() {
    }

    public Actividad(Integer actiId, Usuario usuario, TipoActividad tipoActividad, String descripcionActividad, Date fechaHoraInicio, Date fechaHoraFin, HistoriaDeUsuario historiaDeUsuario) {
       this.actiId = actiId;
       this.usuario = usuario;
       this.tipoActividad = tipoActividad;
       this.descripcionActividad = descripcionActividad;
       this.fechaHoraInicio = fechaHoraInicio;
       this.fechaHoraFin = fechaHoraFin;
       this.historiaDeUsuario = historiaDeUsuario;
    }
   
    public Integer getActiId() {
        return this.actiId;
    }
    
    public void setActiId(Integer actiId) {
        this.actiId = actiId;
    }
    public Usuario getUsuario() {
        return this.usuario;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public TipoActividad getTipoActividad() {
        return this.tipoActividad;
    }
    
    public void setTipoActividad(TipoActividad tipoActividad) {
        this.tipoActividad = tipoActividad;
    }
    public String getDescripcionActividad() {
        return this.descripcionActividad;
    }
    
    public void setDescripcionActividad(String descripcionActividad) {
        this.descripcionActividad = descripcionActividad;
    }
    public Date getFechaHoraInicio() {
        return this.fechaHoraInicio;
    }
    
    public void setFechaHoraInicio(Date fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }
    public Date getFechaHoraFin() {
        return this.fechaHoraFin;
    }
    
    public void setFechaHoraFin(Date fechaHoraFin) {
        this.fechaHoraFin = fechaHoraFin;
    }
    
    public HistoriaDeUsuario getHistoriaDeUsuario() {
    	return this.historiaDeUsuario;
    }
    
    public void setHistoriaDeUsuario(HistoriaDeUsuario historiaDeUsuario) {
    	this.historiaDeUsuario = historiaDeUsuario;
    }

}


