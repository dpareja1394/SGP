package com.dsdsoft.sgp.modelo;
// Generated 24/06/2016 11:19:56 PM by Hibernate Tools 4.0.0


import java.util.HashSet;
import java.util.Set;

/**
 * Usuario generated by hbm2java
 */
public class Usuario  implements java.io.Serializable {


     private Integer usuaId;
     private String nombreUsuario;
     private String apellidoUsuario;
     private String emailUsuario;
     private String passwordUsuario;
     private String estadoRegistroUsuario;
     private String nudocUsuario;
     private Set<SeguimientoCaso> seguimientoCasos = new HashSet<SeguimientoCaso>(0);
     private Set<HistoriaDeUsuario> historiaDeUsuarios = new HashSet<HistoriaDeUsuario>(0);
     private Set<CasoSoporte> casoSoportes = new HashSet<CasoSoporte>(0);
     private Set<ProyectoUsuarioRol> proyectoUsuarioRols = new HashSet<ProyectoUsuarioRol>(0);
     private Set<Actividad> actividads = new HashSet<Actividad>(0);

    public Usuario() {
    }

	
    public Usuario(Integer usuaId, String nombreUsuario, String apellidoUsuario, String emailUsuario, String passwordUsuario, String estadoRegistroUsuario, String nudocUsuario) {
        this.usuaId = usuaId;
        this.nombreUsuario = nombreUsuario;
        this.apellidoUsuario = apellidoUsuario;
        this.emailUsuario = emailUsuario;
        this.passwordUsuario = passwordUsuario;
        this.estadoRegistroUsuario = estadoRegistroUsuario;
        this.nudocUsuario = nudocUsuario;
    }
    public Usuario(Integer usuaId, String nombreUsuario, String apellidoUsuario, String emailUsuario, String passwordUsuario, String estadoRegistroUsuario, String nudocUsuario, Set<SeguimientoCaso> seguimientoCasos, Set<HistoriaDeUsuario> historiaDeUsuarios, Set<CasoSoporte> casoSoportes, Set<ProyectoUsuarioRol> proyectoUsuarioRols, Set<Actividad> actividads) {
       this.usuaId = usuaId;
       this.nombreUsuario = nombreUsuario;
       this.apellidoUsuario = apellidoUsuario;
       this.emailUsuario = emailUsuario;
       this.passwordUsuario = passwordUsuario;
       this.estadoRegistroUsuario = estadoRegistroUsuario;
       this.nudocUsuario = nudocUsuario;
       this.seguimientoCasos = seguimientoCasos;
       this.historiaDeUsuarios = historiaDeUsuarios;
       this.casoSoportes = casoSoportes;
       this.proyectoUsuarioRols = proyectoUsuarioRols;
       this.actividads = actividads;
    }
   
    public Integer getUsuaId() {
        return this.usuaId;
    }
    
    public void setUsuaId(Integer usuaId) {
        this.usuaId = usuaId;
    }
    public String getNombreUsuario() {
        return this.nombreUsuario;
    }
    
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
    public String getApellidoUsuario() {
        return this.apellidoUsuario;
    }
    
    public void setApellidoUsuario(String apellidoUsuario) {
        this.apellidoUsuario = apellidoUsuario;
    }
    public String getEmailUsuario() {
        return this.emailUsuario;
    }
    
    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }
    public String getPasswordUsuario() {
        return this.passwordUsuario;
    }
    
    public void setPasswordUsuario(String passwordUsuario) {
        this.passwordUsuario = passwordUsuario;
    }
    public String getEstadoRegistroUsuario() {
        return this.estadoRegistroUsuario;
    }
    
    public void setEstadoRegistroUsuario(String estadoRegistroUsuario) {
        this.estadoRegistroUsuario = estadoRegistroUsuario;
    }
    public String getNudocUsuario() {
        return this.nudocUsuario;
    }
    
    public void setNudocUsuario(String nudocUsuario) {
        this.nudocUsuario = nudocUsuario;
    }
    public Set<SeguimientoCaso> getSeguimientoCasos() {
        return this.seguimientoCasos;
    }
    
    public void setSeguimientoCasos(Set<SeguimientoCaso> seguimientoCasos) {
        this.seguimientoCasos = seguimientoCasos;
    }
    public Set<HistoriaDeUsuario> getHistoriaDeUsuarios() {
        return this.historiaDeUsuarios;
    }
    
    public void setHistoriaDeUsuarios(Set<HistoriaDeUsuario> historiaDeUsuarios) {
        this.historiaDeUsuarios = historiaDeUsuarios;
    }
    public Set<CasoSoporte> getCasoSoportes() {
        return this.casoSoportes;
    }
    
    public void setCasoSoportes(Set<CasoSoporte> casoSoportes) {
        this.casoSoportes = casoSoportes;
    }
    public Set<ProyectoUsuarioRol> getProyectoUsuarioRols() {
        return this.proyectoUsuarioRols;
    }
    
    public void setProyectoUsuarioRols(Set<ProyectoUsuarioRol> proyectoUsuarioRols) {
        this.proyectoUsuarioRols = proyectoUsuarioRols;
    }
    public Set<Actividad> getActividads() {
        return this.actividads;
    }
    
    public void setActividads(Set<Actividad> actividads) {
        this.actividads = actividads;
    }

}


