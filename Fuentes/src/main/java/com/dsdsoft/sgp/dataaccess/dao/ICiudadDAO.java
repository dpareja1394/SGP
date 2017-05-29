package com.dsdsoft.sgp.dataaccess.dao;

import java.util.List;

import com.dsdsoft.sgp.dataaccess.api.Dao;
import com.dsdsoft.sgp.modelo.Ciudad;


/**
* Interface for   CiudadDAO.
*
*/
public interface ICiudadDAO extends Dao<Ciudad, Integer> {
	public List<Ciudad> buscarCiudadPorDepartamento(Integer depaId) throws Exception;
}
