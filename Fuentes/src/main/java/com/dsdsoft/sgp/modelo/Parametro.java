package com.dsdsoft.sgp.modelo;
// Generated 24/06/2016 11:19:56 PM by Hibernate Tools 4.0.0



/**
 * Parametro generated by hbm2java
 */
public class Parametro  implements java.io.Serializable {


     private Integer idPara;
     private String descripcionParametro;
     private String valorParametro;
     private String nombreCorto;

    public Parametro() {
    }

    public Parametro(Integer idPara, String descripcionParametro, String valorParametro, String nombreCorto) {
       this.idPara = idPara;
       this.descripcionParametro = descripcionParametro;
       this.valorParametro = valorParametro;
       this.nombreCorto = nombreCorto;
    }
   
    public Integer getIdPara() {
        return this.idPara;
    }
    
    public void setIdPara(Integer idPara) {
        this.idPara = idPara;
    }
    public String getDescripcionParametro() {
        return this.descripcionParametro;
    }
    
    public void setDescripcionParametro(String descripcionParametro) {
        this.descripcionParametro = descripcionParametro;
    }
    public String getValorParametro() {
        return this.valorParametro;
    }
    
    public void setValorParametro(String valorParametro) {
        this.valorParametro = valorParametro;
    }
    public String getNombreCorto() {
        return this.nombreCorto;
    }
    
    public void setNombreCorto(String nombreCorto) {
        this.nombreCorto = nombreCorto;
    }




}


