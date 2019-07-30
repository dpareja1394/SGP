package com.dsdsoft.sgp.dataaccess.dao;

import java.util.List;

import com.dsdsoft.sgp.dataaccess.api.Dao;
import com.dsdsoft.sgp.modelo.HistoriaDeUsuario;
import com.dsdsoft.sgp.modelo.dto.HistoriaDeUsuarioDTO;

/**
 * Interface for HistoriaDeUsuarioDAO.
 *
 */
public interface IHistoriaDeUsuarioDAO extends Dao<HistoriaDeUsuario, Integer> {

	/**
	 * 
	 *
	 *
	 * @author Daniel Pareja Londoño
	 * @version may. 28, 2019
	 * @since 1.8
	 * @param usuaId
	 * @param proyId
	 * @param requId
	 * @param eshiId
	 * @return
	 * @throws Exception
	 * @return <b>{@code }</b> Start here...
	 *
	 */
	public List<HistoriaDeUsuarioDTO> consultarHistoriasUsuarioPorFiltros(Integer usuaId, Integer proyId,
			Integer requId, Integer eshiId) throws Exception;

}
