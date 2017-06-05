package com.dsdsoft.sgp.dataaccess.dao;

import java.util.List;

import com.dsdsoft.sgp.dataaccess.api.Dao;
import com.dsdsoft.sgp.modelo.EstadoProyecto;


/**
* Interface for   EstadoProyectoDAO.
*
*/
public interface IEstadoProyectoDAO extends Dao<EstadoProyecto, Integer> {
	public List<EstadoProyecto> listaEstadoProyectoOrdenadaPorDescripcionEstado() throws Exception;
	
}
