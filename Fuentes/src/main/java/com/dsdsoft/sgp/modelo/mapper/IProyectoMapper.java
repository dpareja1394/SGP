/**
 *
 * @author Daniel Pareja Londoño
 * @version ago. 05, 2019
 * @since 1.8
 *
 */
package com.dsdsoft.sgp.modelo.mapper;

import java.util.List;

import com.dsdsoft.sgp.modelo.Proyecto;
import com.dsdsoft.sgp.modelo.dto.ProyectoDTO;

/**
 *
 * @author Daniel Pareja Londoño
 * @version ago. 05, 2019
 * @since 1.8
 *
 */
public interface IProyectoMapper {
	/**
	 * 
	 *
	 *
	 * @author Daniel Pareja Londoño
	 * @version ago. 05, 2019
	 * @since 1.8
	 * @param proyecto
	 * @return
	 * @throws Exception
	 * @return <b>{@code }</b> Start here...
	 *
	 */
	ProyectoDTO toDTO(Proyecto proyecto) throws Exception;
	/**
	 * 
	 *
	 *
	 * @author Daniel Pareja Londoño
	 * @version ago. 05, 2019
	 * @since 1.8
	 * @param proyectoDTO
	 * @return
	 * @throws Exception
	 * @return <b>{@code }</b> Start here...
	 *
	 */
	Proyecto toEntity(ProyectoDTO proyectoDTO) throws Exception;
	/**
	 * 
	 *
	 *
	 * @author Daniel Pareja Londoño
	 * @version ago. 05, 2019
	 * @since 1.8
	 * @param listProyecto
	 * @return
	 * @throws Exception
	 * @return <b>{@code }</b> Start here...
	 *
	 */
	List<ProyectoDTO> toDTOList(List<Proyecto> listProyecto) throws Exception;
	/**
	 * 
	 *
	 *
	 * @author Daniel Pareja Londoño
	 * @version ago. 05, 2019
	 * @since 1.8
	 * @param listProyectoDTO
	 * @return
	 * @throws Exception
	 * @return <b>{@code }</b> Start here...
	 *
	 */
	List<Proyecto> toEntityList(List<ProyectoDTO> listProyectoDTO) throws Exception;	
}
