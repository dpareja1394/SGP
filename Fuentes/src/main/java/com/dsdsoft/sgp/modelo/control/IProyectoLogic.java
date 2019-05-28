package com.dsdsoft.sgp.modelo.control;

import com.dsdsoft.sgp.modelo.Proyecto;
import com.dsdsoft.sgp.modelo.dto.ProyectoDTO;

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
public interface IProyectoLogic {
    public List<Proyecto> getProyecto() throws Exception;

    /**
         * Save an new Proyecto entity
         */
    public void saveProyecto(Proyecto entity) throws Exception;

    /**
         * Delete an existing Proyecto entity
         *
         */
    public void deleteProyecto(Proyecto entity) throws Exception;

    /**
        * Update an existing Proyecto entity
        *
        */
    public void updateProyecto(Proyecto entity) throws Exception;

    /**
         * Load an existing Proyecto entity
         *
         */
    public Proyecto getProyecto(Integer proyId) throws Exception;

    public List<Proyecto> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Proyecto> findPageProyecto(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberProyecto() throws Exception;

    public List<ProyectoDTO> getDataProyecto() throws Exception;
    
    public List<Proyecto> listaProyectosDadoCliente(Integer clieId) throws Exception;
    
    public List<ProyectoDTO> listaProyectosDTODadoCliente(Integer clieId) throws Exception;
    
    /**
     * @author Daniel Pareja Londoño
     * @version may. 28, 2019
     * @since 1.8
     * @param usuaId
     * @return
     * @throws Exception
     * @return <b>{@code }</b> Start here...
     */
    public List<ProyectoDTO> consultarProyectosClientesPorUsuario(Integer usuaId) throws Exception;
}
