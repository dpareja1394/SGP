package com.dsdsoft.sgp.modelo.control;

import com.dsdsoft.sgp.modelo.CasoSoporte;
import com.dsdsoft.sgp.modelo.dto.CasoSoporteDTO;

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
public interface ICasoSoporteLogic {
    public List<CasoSoporte> getCasoSoporte() throws Exception;

    /**
         * Save an new CasoSoporte entity
         */
    public void saveCasoSoporte(CasoSoporte entity) throws Exception;

    /**
         * Delete an existing CasoSoporte entity
         *
         */
    public void deleteCasoSoporte(CasoSoporte entity) throws Exception;

    /**
        * Update an existing CasoSoporte entity
        *
        */
    public void updateCasoSoporte(CasoSoporte entity) throws Exception;

    /**
         * Load an existing CasoSoporte entity
         *
         */
    public CasoSoporte getCasoSoporte(Integer casoId) throws Exception;

    public List<CasoSoporte> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<CasoSoporte> findPageCasoSoporte(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberCasoSoporte() throws Exception;

    public List<CasoSoporteDTO> getDataCasoSoporte() throws Exception;
}
