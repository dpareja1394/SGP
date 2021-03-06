package com.dsdsoft.sgp.modelo;
// Generated 24/06/2016 11:19:56 PM by Hibernate Tools 4.0.0


import java.util.HashSet;
import java.util.Set;

/**
 * TipoActividad generated by hbm2java
 */
public class TipoActividad  implements java.io.Serializable {


     private Integer tiacId;
     private String descripcionTiact;
     private String nombreCorto;
     private Set<Actividad> actividads = new HashSet<Actividad>(0);

    public TipoActividad() {
    }

	
    public TipoActividad(Integer tiacId, String descripcionTiact, String nombreCorto) {
        this.tiacId = tiacId;
        this.descripcionTiact = descripcionTiact;
        this.nombreCorto = nombreCorto;
    }
    public TipoActividad(Integer tiacId, String descripcionTiact, String nombreCorto, Set<Actividad> actividads) {
       this.tiacId = tiacId;
       this.descripcionTiact = descripcionTiact;
       this.nombreCorto = nombreCorto;
       this.actividads = actividads;
    }
   
    public Integer getTiacId() {
        return this.tiacId;
    }
    
    public void setTiacId(Integer tiacId) {
        this.tiacId = tiacId;
    }
    public String getDescripcionTiact() {
        return this.descripcionTiact;
    }
    
    public void setDescripcionTiact(String descripcionTiact) {
        this.descripcionTiact = descripcionTiact;
    }
    public String getNombreCorto() {
        return this.nombreCorto;
    }
    
    public void setNombreCorto(String nombreCorto) {
        this.nombreCorto = nombreCorto;
    }
    public Set<Actividad> getActividads() {
        return this.actividads;
    }
    
    public void setActividads(Set<Actividad> actividads) {
        this.actividads = actividads;
    }




}


