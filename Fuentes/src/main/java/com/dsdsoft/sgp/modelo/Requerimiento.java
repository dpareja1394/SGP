package com.dsdsoft.sgp.modelo;
// Generated 24/06/2016 11:19:56 PM by Hibernate Tools 4.0.0


import java.util.HashSet;
import java.util.Set;

/**
 * Requerimiento generated by hbm2java
 */
public class Requerimiento  implements java.io.Serializable {


     private Integer requId;
     private Proyecto proyecto;
     private String nombreRequerimiento;
     private String descripcionRequerimiento;
     private Set<HistoriaDeUsuario> historiaDeUsuarios = new HashSet<HistoriaDeUsuario>(0);

    public Requerimiento() {
    }

	
    public Requerimiento(Integer requId, Proyecto proyecto, String nombreRequerimiento, String descripcionRequerimiento) {
        this.requId = requId;
        this.proyecto = proyecto;
        this.nombreRequerimiento = nombreRequerimiento;
        this.descripcionRequerimiento = descripcionRequerimiento;
    }
    public Requerimiento(Integer requId, Proyecto proyecto, String nombreRequerimiento, String descripcionRequerimiento, Set<HistoriaDeUsuario> historiaDeUsuarios) {
       this.requId = requId;
       this.proyecto = proyecto;
       this.nombreRequerimiento = nombreRequerimiento;
       this.descripcionRequerimiento = descripcionRequerimiento;
       this.historiaDeUsuarios = historiaDeUsuarios;
    }
   
    public Integer getRequId() {
        return this.requId;
    }
    
    public void setRequId(Integer requId) {
        this.requId = requId;
    }
    public Proyecto getProyecto() {
        return this.proyecto;
    }
    
    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }
    public String getNombreRequerimiento() {
        return this.nombreRequerimiento;
    }
    
    public void setNombreRequerimiento(String nombreRequerimiento) {
        this.nombreRequerimiento = nombreRequerimiento;
    }
    public String getDescripcionRequerimiento() {
        return this.descripcionRequerimiento;
    }
    
    public void setDescripcionRequerimiento(String descripcionRequerimiento) {
        this.descripcionRequerimiento = descripcionRequerimiento;
    }
    public Set<HistoriaDeUsuario> getHistoriaDeUsuarios() {
        return this.historiaDeUsuarios;
    }
    
    public void setHistoriaDeUsuarios(Set<HistoriaDeUsuario> historiaDeUsuarios) {
        this.historiaDeUsuarios = historiaDeUsuarios;
    }




}


