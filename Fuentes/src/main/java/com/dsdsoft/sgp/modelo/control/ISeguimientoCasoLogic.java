package com.dsdsoft.sgp.modelo.control;

import com.dsdsoft.sgp.modelo.SeguimientoCaso;
import com.dsdsoft.sgp.modelo.dto.SeguimientoCasoDTO;

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
public interface ISeguimientoCasoLogic {
    public List<SeguimientoCaso> getSeguimientoCaso() throws Exception;

    /**
         * Save an new SeguimientoCaso entity
         */
    public void saveSeguimientoCaso(SeguimientoCaso entity)
        throws Exception;

    /**
         * Delete an existing SeguimientoCaso entity
         *
         */
    public void deleteSeguimientoCaso(SeguimientoCaso entity)
        throws Exception;

    /**
        * Update an existing SeguimientoCaso entity
        *
        */
    public void updateSeguimientoCaso(SeguimientoCaso entity)
        throws Exception;

    /**
         * Load an existing SeguimientoCaso entity
         *
         */
    public SeguimientoCaso getSeguimientoCaso(Integer secaId)
        throws Exception;

    public List<SeguimientoCaso> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<SeguimientoCaso> findPageSeguimientoCaso(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception;

    public Long findTotalNumberSeguimientoCaso() throws Exception;

    public List<SeguimientoCasoDTO> getDataSeguimientoCaso()
        throws Exception;
}
