package com.dsdsoft.sgp.dataaccess.dao;

import java.util.List;

import com.dsdsoft.sgp.dataaccess.api.Dao;
import com.dsdsoft.sgp.modelo.Departamento;


/**
* Interface for   DepartamentoDAO.
*
*/
public interface IDepartamentoDAO extends Dao<Departamento, Integer> {
	public List<Departamento> buscarDepartamentoPorPais(Integer paisId) throws Exception;
	
}
