package com.dsdsoft.sgp.modelo.control;

import com.dsdsoft.sgp.modelo.Ciudad;
import com.dsdsoft.sgp.modelo.dto.CiudadDTO;

import java.math.BigDecimal;

import java.util.*;
import java.util.Date;
import java.util.List;
import java.util.Set;


/**
* @author Zathura Code Generator http://zathuracode.org/
* www.zathuracode.org
*
*/
public interface ICiudadLogic {
    public List<Ciudad> getCiudad() throws Exception;

    /**
         * Save an new Ciudad entity
         */
    public void saveCiudad(Ciudad entity) throws Exception;

    /**
         * Delete an existing Ciudad entity
         *
         */
    public void deleteCiudad(Ciudad entity) throws Exception;

    /**
        * Update an existing Ciudad entity
        *
        */
    public void updateCiudad(Ciudad entity) throws Exception;

    /**
         * Load an existing Ciudad entity
         *
         */
    public Ciudad getCiudad(Integer ciudId) throws Exception;

    public List<Ciudad> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Ciudad> findPageCiudad(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberCiudad() throws Exception;

    public List<CiudadDTO> getDataCiudad() throws Exception;
    
    public List<Ciudad> buscarCiudadPorDepartamento(Integer depaId) throws Exception;
}
