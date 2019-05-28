package com.dsdsoft.sgp.modelo.control;

import com.dsdsoft.sgp.modelo.Requerimiento;
import com.dsdsoft.sgp.modelo.dto.RequerimientoDTO;

import java.math.BigDecimal;

import java.util.*;
import java.util.Date;
import java.util.List;
import java.util.Set;


/**
* @author Zathura Code Generator http://code.google.com/p/zathura
* www.zathuracode.org
*
*/
public interface IRequerimientoLogic {
    public List<Requerimiento> getRequerimiento() throws Exception;

    /**
         * Save an new Requerimiento entity
         */
    public void saveRequerimiento(Requerimiento entity)
        throws Exception;

    /**
         * Delete an existing Requerimiento entity
         *
         */
    public void deleteRequerimiento(Requerimiento entity)
        throws Exception;

    /**
        * Update an existing Requerimiento entity
        *
        */
    public void updateRequerimiento(Requerimiento entity)
        throws Exception;

    /**
         * Load an existing Requerimiento entity
         *
         */
    public Requerimiento getRequerimiento(Integer requId)
        throws Exception;

    public List<Requerimiento> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Requerimiento> findPageRequerimiento(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberRequerimiento() throws Exception;

    public List<RequerimientoDTO> getDataRequerimiento()
        throws Exception;
    
    public List<RequerimientoDTO> listaRequerimientosDTOPorIdProyecto(Integer proyId) throws Exception;
}
