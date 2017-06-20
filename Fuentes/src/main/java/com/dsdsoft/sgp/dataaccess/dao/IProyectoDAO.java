package com.dsdsoft.sgp.dataaccess.dao;

import java.util.List;

import com.dsdsoft.sgp.dataaccess.api.Dao;
import com.dsdsoft.sgp.modelo.Proyecto;


/**
* Interface for   ProyectoDAO.
*
*/
public interface IProyectoDAO extends Dao<Proyecto, Integer> {
	public List<Proyecto> listaProyectosDadoCliente(Integer clieId) throws Exception;
}
