package com.dsdsoft.sgp.modelo.control;

import com.dsdsoft.sgp.modelo.EstadoProyecto;
import com.dsdsoft.sgp.modelo.dto.EstadoProyectoDTO;

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
public interface IEstadoProyectoLogic {
    public List<EstadoProyecto> getEstadoProyecto() throws Exception;

    /**
         * Save an new EstadoProyecto entity
         */
    public void saveEstadoProyecto(EstadoProyecto entity)
        throws Exception;

    /**
         * Delete an existing EstadoProyecto entity
         *
         */
    public void deleteEstadoProyecto(EstadoProyecto entity)
        throws Exception;

    /**
        * Update an existing EstadoProyecto entity
        *
        */
    public void updateEstadoProyecto(EstadoProyecto entity)
        throws Exception;

    /**
         * Load an existing EstadoProyecto entity
         *
         */
    public EstadoProyecto getEstadoProyecto(Integer esprId)
        throws Exception;

    public List<EstadoProyecto> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<EstadoProyecto> findPageEstadoProyecto(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberEstadoProyecto() throws Exception;

    public List<EstadoProyectoDTO> getDataEstadoProyecto()
        throws Exception;
    
    public List<EstadoProyecto> listaEstadoProyectoOrdenadaPorDescripcionEstado() throws Exception;
}
