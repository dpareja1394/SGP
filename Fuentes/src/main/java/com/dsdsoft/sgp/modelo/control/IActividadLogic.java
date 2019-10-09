package com.dsdsoft.sgp.modelo.control;

import com.dsdsoft.sgp.modelo.Actividad;
import com.dsdsoft.sgp.modelo.dto.ActividadDTO;

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
public interface IActividadLogic {
    public List<Actividad> getActividad() throws Exception;

    /**
         * Save an new Actividad entity
         */
    public void saveActividad(Actividad entity) throws Exception;

    /**
         * Delete an existing Actividad entity
         *
         */
    public void deleteActividad(Actividad entity) throws Exception;

    /**
        * Update an existing Actividad entity
        *
        */
    public void updateActividad(Actividad entity) throws Exception;

    /**
         * Load an existing Actividad entity
         *
         */
    public Actividad getActividad(Integer actiId) throws Exception;

    public List<Actividad> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Actividad> findPageActividad(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberActividad() throws Exception;

    public List<ActividadDTO> getDataActividad() throws Exception;
    
    /**
     * Consulta una lista de Actividades por el email del usuario
     * @author Daniel Pareja Londoño
     * @version oct. 06, 2019
     * @since 1.8
     * @param emailUsuario
     * @return
     * @throws Exception
     * @return <b>{@code }</b> Start here...
     *
     */
    public List<ActividadDTO> consultarActividadesDeUsuario(String emailUsuario) throws Exception;
}
