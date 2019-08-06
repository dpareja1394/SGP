/**
 *
 * @author Daniel Pareja Londoño
 * @version ago. 05, 2019
 * @since 1.8
 *
 */
package com.dsdsoft.sgp.modelo.mapper;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dsdsoft.sgp.modelo.EstadoProyecto;
import com.dsdsoft.sgp.modelo.Proyecto;
import com.dsdsoft.sgp.modelo.Usuario;
import com.dsdsoft.sgp.modelo.control.IClienteLogic;
import com.dsdsoft.sgp.modelo.control.IEstadoProyectoLogic;
import com.dsdsoft.sgp.modelo.control.IProyectoLogic;
import com.dsdsoft.sgp.modelo.control.IUsuarioLogic;
import com.dsdsoft.sgp.modelo.dto.ProyectoDTO;

/**
 *
 * @author Daniel Pareja Londoño
 * @version ago. 05, 2019
 * @since 1.8
 *
 */
@Scope("singleton")
@Service("ProyectoMapper")
public class ProyectoMapper implements IProyectoMapper {

	private static final Logger log = LoggerFactory.getLogger(ProyectoMapper.class);

	@Autowired
	private IClienteLogic clienteLogic;
	@Autowired
	private IEstadoProyectoLogic estadoProyectoLogic;
	@Autowired
	private IUsuarioLogic usuarioLogic;

	/**
	 * @author Daniel Pareja Londoño
	 * @version ago. 05, 2019
	 *
	 * @see com.dsdsoft.sgp.modelo.mapper.IProyectoMapper#toDTO(com.dsdsoft.sgp.modelo.Proyecto)
	 *
	 */
	@Override
	@Transactional(readOnly = true)
	public ProyectoDTO toDTO(Proyecto proyecto) throws Exception {
		ProyectoDTO proyectoDTO = null;
		try {
			if (proyecto != null) {
				proyectoDTO = new ProyectoDTO();
				proyectoDTO.setDescProyecto(proyecto.getDescProyecto());
				proyectoDTO.setProyId(proyecto.getProyId());
				proyectoDTO.setClieId_Cliente(proyecto.getCliente().getClieId());
				proyectoDTO.setEsprId_EstadoProyecto(proyecto.getEstadoProyecto().getEsprId());

				EstadoProyecto estadoProyecto = estadoProyectoLogic
						.getEstadoProyecto(proyecto.getEstadoProyecto().getEsprId());

				proyectoDTO
						.setDescripcionEstado((estadoProyecto != null && estadoProyecto.getDescripcionEstado() != null
								&& !estadoProyecto.getDescripcionEstado().trim().equals(""))
										? estadoProyecto.getDescripcionEstado()
										: null);

				proyectoDTO.setFechaCreacion(proyecto.getFechaCreacion());
				proyectoDTO.setFechaModificacion(proyecto.getFechaModificacion());

				Usuario usuarioCreacion = usuarioLogic.getUsuario(proyecto.getUsuarioByUsuarioCreacion().getUsuaId());

				proyectoDTO.setUsuarioCreacion((usuarioCreacion != null && usuarioCreacion.getEmailUsuario() != null
						&& !usuarioCreacion.getEmailUsuario().trim().equals("")) ? usuarioCreacion.getEmailUsuario()
								: null);
				Usuario usuarioModificacion = (proyecto.getUsuarioByUsuarioModificacion() != null)?usuarioLogic
						.getUsuario(proyecto.getUsuarioByUsuarioModificacion().getUsuaId()):null;

				proyectoDTO.setUsuarioModificacion(
						(usuarioModificacion != null && usuarioModificacion.getEmailUsuario() != null
								&& !usuarioModificacion.getEmailUsuario().trim().equals(""))
										? usuarioModificacion.getEmailUsuario()
										: null);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
		return proyectoDTO;
	}

	/**
	 * @author Daniel Pareja Londoño
	 * @version ago. 05, 2019
	 *
	 * @see com.dsdsoft.sgp.modelo.mapper.IProyectoMapper#toEntity(com.dsdsoft.sgp.modelo.dto.ProyectoDTO)
	 *
	 */
	@Override
	@Transactional(readOnly = true)
	public Proyecto toEntity(ProyectoDTO proyectoDTO) throws Exception {
		Proyecto proyecto = null;
		try {
			if (proyectoDTO != null) {
				proyecto = new Proyecto();
				proyecto.setProyId(proyectoDTO.getProyId());
				proyecto.setEstadoProyecto((proyectoDTO.getEsprId_EstadoProyecto() != null)
						? estadoProyectoLogic.getEstadoProyecto(proyectoDTO.getEsprId_EstadoProyecto())
						: null);
				proyecto.setCliente((proyectoDTO.getClieId_Cliente() != null)
						? clienteLogic.getCliente(proyectoDTO.getClieId_Cliente())
						: null);
				proyecto.setDescProyecto(proyectoDTO.getDescProyecto());
				proyecto.setUsuarioByUsuarioCreacion((proyectoDTO.getUsuarioCreacion() != null)
						? usuarioLogic.buscarUsuarioPorEmail(proyectoDTO.getUsuarioCreacion())
						: null);
				proyecto.setUsuarioByUsuarioModificacion((proyectoDTO.getUsuarioModificacion() != null)
						? usuarioLogic.buscarUsuarioPorEmail(proyectoDTO.getUsuarioModificacion())
						: null);
				proyecto.setFechaCreacion(proyectoDTO.getFechaCreacion());
				proyecto.setFechaModificacion(proyectoDTO.getFechaModificacion());
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
		return proyecto;
	}

	/**
	 * @author Daniel Pareja Londoño
	 * @version ago. 05, 2019
	 *
	 * @see com.dsdsoft.sgp.modelo.mapper.IProyectoMapper#toDTOList(java.util.List)
	 *
	 */
	@Override
	@Transactional(readOnly = true)
	public List<ProyectoDTO> toDTOList(List<Proyecto> listProyecto) throws Exception {
		List<ProyectoDTO> dtoList = null;
		try {
			if (listProyecto != null && !listProyecto.isEmpty()) {
				dtoList = new ArrayList<ProyectoDTO>();
				for (Proyecto proyecto : listProyecto) {
					dtoList.add(toDTO(proyecto));
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
		return dtoList;
	}

	/**
	 * @author Daniel Pareja Londoño
	 * @version ago. 05, 2019
	 *
	 * @see com.dsdsoft.sgp.modelo.mapper.IProyectoMapper#toEntityList(java.util.List)
	 *
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Proyecto> toEntityList(List<ProyectoDTO> listProyectoDTO) throws Exception {
		List<Proyecto> entityList = null;
		try {
			if (listProyectoDTO != null && !listProyectoDTO.isEmpty()) {
				entityList = new ArrayList<Proyecto>();
				for (ProyectoDTO proyectoDTO : listProyectoDTO) {
					entityList.add(toEntity(proyectoDTO));
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
		return entityList;
	}

}
