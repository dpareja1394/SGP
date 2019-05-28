package com.dsdsoft.sgp.dataaccess.dao;

import java.util.List;

import com.dsdsoft.sgp.dataaccess.api.Dao;
import com.dsdsoft.sgp.modelo.Proyecto;
import com.dsdsoft.sgp.modelo.dto.ProyectoDTO;


/**
* Interface for   ProyectoDAO.
*
*/
public interface IProyectoDAO extends Dao<Proyecto, Integer> {
	public List<Proyecto> listaProyectosDadoCliente(Integer clieId) throws Exception;
	/**
	 * @author Daniel Pareja Londoño
	 * @version may. 28, 2019
	 * @since 1.8
	 * @param usuaId
	 * @return
	 * @throws Exception
	 * @return <b>{@code }</b> Start here...
	 *
	 */
	public List<ProyectoDTO> consultarProyectosClientesPorUsuario(Integer usuaId) throws Exception;
}
