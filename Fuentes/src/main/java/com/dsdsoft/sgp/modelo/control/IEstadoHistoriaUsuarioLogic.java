package com.dsdsoft.sgp.modelo.control;

import com.dsdsoft.sgp.modelo.EstadoHistoriaUsuario;
import com.dsdsoft.sgp.modelo.dto.EstadoHistoriaUsuarioDTO;

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
public interface IEstadoHistoriaUsuarioLogic {
    public List<EstadoHistoriaUsuario> getEstadoHistoriaUsuario()
        throws Exception;

    /**
         * Save an new EstadoHistoriaUsuario entity
         */
    public void saveEstadoHistoriaUsuario(EstadoHistoriaUsuario entity)
        throws Exception;

    /**
         * Delete an existing EstadoHistoriaUsuario entity
         *
         */
    public void deleteEstadoHistoriaUsuario(EstadoHistoriaUsuario entity)
        throws Exception;

    /**
        * Update an existing EstadoHistoriaUsuario entity
        *
        */
    public void updateEstadoHistoriaUsuario(EstadoHistoriaUsuario entity)
        throws Exception;

    /**
         * Load an existing EstadoHistoriaUsuario entity
         *
         */
    public EstadoHistoriaUsuario getEstadoHistoriaUsuario(Integer eshiId)
        throws Exception;

    public List<EstadoHistoriaUsuario> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<EstadoHistoriaUsuario> findPageEstadoHistoriaUsuario(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception;

    public Long findTotalNumberEstadoHistoriaUsuario()
        throws Exception;

    public List<EstadoHistoriaUsuarioDTO> getDataEstadoHistoriaUsuario()
        throws Exception;
}
