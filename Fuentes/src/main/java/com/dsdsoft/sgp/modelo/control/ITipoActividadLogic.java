package com.dsdsoft.sgp.modelo.control;

import com.dsdsoft.sgp.modelo.TipoActividad;
import com.dsdsoft.sgp.modelo.dto.TipoActividadDTO;

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
public interface ITipoActividadLogic {
    public List<TipoActividad> getTipoActividad() throws Exception;

    /**
         * Save an new TipoActividad entity
         */
    public void saveTipoActividad(TipoActividad entity)
        throws Exception;

    /**
         * Delete an existing TipoActividad entity
         *
         */
    public void deleteTipoActividad(TipoActividad entity)
        throws Exception;

    /**
        * Update an existing TipoActividad entity
        *
        */
    public void updateTipoActividad(TipoActividad entity)
        throws Exception;

    /**
         * Load an existing TipoActividad entity
         *
         */
    public TipoActividad getTipoActividad(Integer tiacId)
        throws Exception;

    public List<TipoActividad> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<TipoActividad> findPageTipoActividad(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberTipoActividad() throws Exception;

    public List<TipoActividadDTO> getDataTipoActividad()
        throws Exception;
}
