package com.dsdsoft.sgp.dataaccess.dao;

import java.util.List;

import com.dsdsoft.sgp.dataaccess.api.Dao;
import com.dsdsoft.sgp.modelo.ProyectoUsuarioRol;
import com.dsdsoft.sgp.modelo.ProyectoUsuarioRolId;
import com.dsdsoft.sgp.modelo.dto.ProyectoUsuarioRolDTO;


/**
* Interface for   ProyectoUsuarioRolDAO.
*
*/
public interface IProyectoUsuarioRolDAO extends Dao<ProyectoUsuarioRol, ProyectoUsuarioRolId> {
	public List<ProyectoUsuarioRolDTO> listaProyectoUsuarioRolDadoProyecto(Integer proyId) throws Exception;
}
