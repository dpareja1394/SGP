package com.dsdsoft.sgp.modelo.control;

import com.dsdsoft.sgp.modelo.HistoriaDeUsuario;
import com.dsdsoft.sgp.modelo.dto.HistoriaDeUsuarioDTO;

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
public interface IHistoriaDeUsuarioLogic {
    public List<HistoriaDeUsuario> getHistoriaDeUsuario()
        throws Exception;

    /**
         * Save an new HistoriaDeUsuario entity
         */
    public void saveHistoriaDeUsuario(HistoriaDeUsuario entity)
        throws Exception;

    /**
         * Delete an existing HistoriaDeUsuario entity
         *
         */
    public void deleteHistoriaDeUsuario(HistoriaDeUsuario entity)
        throws Exception;

    /**
        * Update an existing HistoriaDeUsuario entity
        *
        */
    public void updateHistoriaDeUsuario(HistoriaDeUsuario entity)
        throws Exception;

    /**
         * Load an existing HistoriaDeUsuario entity
         *
         */
    public HistoriaDeUsuario getHistoriaDeUsuario(Integer hiusId)
        throws Exception;

    public List<HistoriaDeUsuario> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<HistoriaDeUsuario> findPageHistoriaDeUsuario(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception;

    public Long findTotalNumberHistoriaDeUsuario() throws Exception;

    public List<HistoriaDeUsuarioDTO> getDataHistoriaDeUsuario()
        throws Exception;
}
