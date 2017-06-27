package com.dsdsoft.sgp.dataaccess.dao;

import java.util.List;

import com.dsdsoft.sgp.dataaccess.api.Dao;
import com.dsdsoft.sgp.modelo.Rol;


/**
* Interface for   RolDAO.
*
*/
public interface IRolDAO extends Dao<Rol, Integer> {
	public List<Rol> listaRolesOrdenadaPorDescripcionAscendente() throws Exception;
}
