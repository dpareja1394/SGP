package com.dsdsoft.sgp.modelo.control;

import com.dsdsoft.sgp.modelo.ProyectoUsuarioRol;
import com.dsdsoft.sgp.modelo.ProyectoUsuarioRolId;
import com.dsdsoft.sgp.modelo.dto.ProyectoUsuarioRolDTO;

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
public interface IProyectoUsuarioRolLogic {
    public List<ProyectoUsuarioRol> getProyectoUsuarioRol()
        throws Exception;

    /**
         * Save an new ProyectoUsuarioRol entity
         */
    public void saveProyectoUsuarioRol(ProyectoUsuarioRol entity)
        throws Exception;

    /**
         * Delete an existing ProyectoUsuarioRol entity
         *
         */
    public void deleteProyectoUsuarioRol(ProyectoUsuarioRol entity)
        throws Exception;

    /**
        * Update an existing ProyectoUsuarioRol entity
        *
        */
    public void updateProyectoUsuarioRol(ProyectoUsuarioRol entity)
        throws Exception;

    /**
         * Load an existing ProyectoUsuarioRol entity
         *
         */
    public ProyectoUsuarioRol getProyectoUsuarioRol(ProyectoUsuarioRolId id)
        throws Exception;

    public List<ProyectoUsuarioRol> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<ProyectoUsuarioRol> findPageProyectoUsuarioRol(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception;

    public Long findTotalNumberProyectoUsuarioRol() throws Exception;

    public List<ProyectoUsuarioRolDTO> getDataProyectoUsuarioRol()
        throws Exception;
    
    public void guardarOActualizarProyectoUsuarioRol(Integer usuaId, Integer proyId, Integer rolId) throws Exception;
    
}
