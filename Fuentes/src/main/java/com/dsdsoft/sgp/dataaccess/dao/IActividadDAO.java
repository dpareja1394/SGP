package com.dsdsoft.sgp.dataaccess.dao;

import java.util.List;

import com.dsdsoft.sgp.dataaccess.api.Dao;
import com.dsdsoft.sgp.modelo.Actividad;
import com.dsdsoft.sgp.modelo.dto.ActividadDTO;


/**
* Interface for   ActividadDAO.
*
*/
public interface IActividadDAO extends Dao<Actividad, Integer> {
	public List<ActividadDTO> consultarActividadesDeUsuario(String emailUsuario) throws Exception;
}
