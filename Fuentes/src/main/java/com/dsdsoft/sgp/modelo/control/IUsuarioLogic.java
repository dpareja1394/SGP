package com.dsdsoft.sgp.modelo.control;

import com.dsdsoft.sgp.modelo.Usuario;
import com.dsdsoft.sgp.modelo.dto.UsuarioDTO;

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
public interface IUsuarioLogic {
    public List<Usuario> getUsuario() throws Exception;

    /**
         * Save an new Usuario entity
         */
    public void saveUsuario(Usuario entity) throws Exception;

    /**
         * Delete an existing Usuario entity
         *
         */
    public void deleteUsuario(Usuario entity) throws Exception;

    /**
        * Update an existing Usuario entity
        *
        */
    public void updateUsuario(Usuario entity) throws Exception;

    /**
         * Load an existing Usuario entity
         *
         */
    public Usuario getUsuario(Integer usuaId) throws Exception;

    public List<Usuario> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Usuario> findPageUsuario(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberUsuario() throws Exception;

    public List<UsuarioDTO> getDataUsuario() throws Exception;
    
    public Usuario buscarUsuarioPorEmail(String emailUsuario) throws Exception;
}
