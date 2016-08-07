package com.dsdsoft.sgp.modelo.control;

import com.dsdsoft.sgp.dataaccess.dao.*;
import com.dsdsoft.sgp.exceptions.*;
import com.dsdsoft.sgp.modelo.*;
import com.dsdsoft.sgp.modelo.dto.ClienteDTO;
import com.dsdsoft.sgp.utilities.Utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Scope;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.apache.commons.validator.UrlValidator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @author Zathura Code Generator http://code.google.com/p/zathura
 *         www.zathuracode.org
 *
 */
@Scope("singleton")
@Service("ClienteLogic")
public class ClienteLogic implements IClienteLogic {
	private static final Logger log = LoggerFactory.getLogger(ClienteLogic.class);

	/**
	 * DAO injected by Spring that manages Cliente entities
	 *
	 */
	@Autowired
	private IClienteDAO clienteDAO;

	/**
	 * DAO injected by Spring that manages Proyecto entities
	 *
	 */
	@Autowired
	private IProyectoDAO proyectoDAO;

	@Transactional(readOnly = true)
	public List<Cliente> getCliente() throws Exception {
		log.debug("finding all Cliente instances");

		List<Cliente> list = new ArrayList<Cliente>();

		try {
			list = clienteDAO.findAll();
		} catch (Exception e) {
			log.error("finding all Cliente failed", e);
			throw new ZMessManager().new GettingException(ZMessManager.ALL + "Cliente");
		} finally {
		}

		return list;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveCliente(Cliente entity) throws Exception {
		log.debug("Guardando un cliente, " + entity.getNombreEmpresa());

		try {
			if (entity.getCelularContacto() == null) {
				throw new ZMessManager().new EmptyFieldException("celularContacto");
			}

			if ((entity.getCelularContacto() != null)
					&& (Utilities.checkWordAndCheckWithlength(entity.getCelularContacto(), 200) == false)) {
				throw new ZMessManager().new NotValidFormatException("celularContacto");
			}

			if ((entity.getDireccionContacto() != null)
					&& (Utilities.checkWordAndCheckWithlength(entity.getDireccionContacto(), 200) == false)) {
				throw new ZMessManager().new NotValidFormatException("direccionContacto");
			}

			if ((entity.getEnlaceWeb() != null)
					&& (Utilities.checkWordAndCheckWithlength(entity.getEnlaceWeb(), 200) == false)) {
				throw new ZMessManager().new NotValidFormatException("enlaceWeb");
			}

			if ((entity.getNit() != null) && (Utilities.checkWordAndCheckWithlength(entity.getNit(), 30) == false)) {
				throw new ZMessManager().new NotValidFormatException("nit");
			}

			if (entity.getNombreContacto() == null) {
				throw new ZMessManager().new EmptyFieldException("nombreContacto");
			}

			if ((entity.getNombreContacto() != null)
					&& (Utilities.checkWordAndCheckWithlength(entity.getNombreContacto(), 200) == false)) {
				throw new ZMessManager().new NotValidFormatException("nombreContacto");
			}

			if ((entity.getNombreEmpresa() != null)
					&& (Utilities.checkWordAndCheckWithlength(entity.getNombreEmpresa(), 200) == false)) {
				throw new ZMessManager().new NotValidFormatException("nombreEmpresa");
			}

			if (entity.getTelefonoContacto() == null) {
				throw new ZMessManager().new EmptyFieldException("telefonoContacto");
			}

			if ((entity.getTelefonoContacto() != null)
					&& (Utilities.checkWordAndCheckWithlength(entity.getTelefonoContacto(), 20) == false)) {
				throw new ZMessManager().new NotValidFormatException("telefonoContacto");
			}

			clienteDAO.save(entity);

			log.info("Se ha registrado el cliente, " + entity.getNombreEmpresa());
		} catch (Exception e) {
			log.error("Error guardando el cliente, " + entity.getNombreEmpresa(), e);
			throw e;
		} finally {
		}
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteCliente(Cliente entity) throws Exception {
		log.debug("deleting Cliente instance");

		if (entity == null) {
			throw new ZMessManager().new NullEntityExcepcion("Cliente");
		}

		if (entity.getClieId() == null) {
			throw new ZMessManager().new EmptyFieldException("clieId");
		}

		List<Proyecto> proyectos = null;

		try {
			proyectos = proyectoDAO.findByProperty("cliente.clieId", entity.getClieId());

			if (Utilities.validationsList(proyectos) == true) {
				throw new ZMessManager().new DeletingException("proyectos");
			}

			clienteDAO.delete(entity);

			log.debug("delete Cliente successful");
		} catch (Exception e) {
			log.error("delete Cliente failed", e);
			throw e;
		} finally {
		}
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateCliente(Cliente entity) throws Exception {
		log.debug("Actualizando un cliente");

		try {
			if (entity == null) {
				throw new ZMessManager().new NullEntityExcepcion("Cliente");
			}

			if (entity.getCelularContacto() == null) {
				throw new ZMessManager().new EmptyFieldException("Celular Contacto");
			}

			if ((entity.getCelularContacto() != null)
					&& (Utilities.checkWordAndCheckWithlength(entity.getCelularContacto(), 200) == false)) {
				throw new ZMessManager().new NotValidFormatException("Celular Contacto");
			}

			if (entity.getClieId() == null) {
				throw new ZMessManager().new EmptyFieldException("clieId");
			}

			if ((entity.getDireccionContacto() != null)
					&& (Utilities.checkWordAndCheckWithlength(entity.getDireccionContacto(), 200) == false)) {
				throw new ZMessManager().new NotValidFormatException("Dirección Contacto");
			}

			if ((entity.getEnlaceWeb() != null)
					&& (Utilities.checkWordAndCheckWithlength(entity.getEnlaceWeb(), 200) == false)) {
				throw new ZMessManager().new NotValidFormatException("Enlace Web");
			}

			if ((entity.getNit() != null) && (Utilities.checkWordAndCheckWithlength(entity.getNit(), 30) == false)) {
				throw new ZMessManager().new NotValidFormatException("Nit de la Empresa");
			}

			if (entity.getNombreContacto() == null) {
				throw new ZMessManager().new EmptyFieldException("Nombre del Contacto");
			}

			if ((entity.getNombreContacto() != null)
					&& (Utilities.checkWordAndCheckWithlength(entity.getNombreContacto(), 200) == false)) {
				throw new ZMessManager().new NotValidFormatException("Nombre del Contacto");
			}

			if ((entity.getNombreEmpresa() != null)
					&& (Utilities.checkWordAndCheckWithlength(entity.getNombreEmpresa(), 200) == false)) {
				throw new ZMessManager().new NotValidFormatException("Nombre de la Empresa");
			}

			if (entity.getTelefonoContacto() == null) {
				throw new ZMessManager().new EmptyFieldException("Teléfono Contacto");
			}

			if ((entity.getTelefonoContacto() != null)
					&& (Utilities.checkWordAndCheckWithlength(entity.getTelefonoContacto(), 20) == false)) {
				throw new ZMessManager().new NotValidFormatException("Teléfono Contacto");
			}

			if(entity.getEnlaceWeb()!=null){
				UrlValidator urlValidator = new UrlValidator();
	        	if(urlValidator.isValid(entity.getEnlaceWeb())==false){
	        		throw new Exception("Enlace Web no es correcto");
	        	}
			}
			
			clienteDAO.update(entity);

			log.debug("Cliente actualizado correctamente");
		} catch (Exception e) {
			log.error("Error actualizando un cliente", e);
			throw e;
		} finally {
		}
	}

	@Transactional(readOnly = true)
	public List<ClienteDTO> getDataCliente() throws Exception {
		try {
			List<Cliente> cliente = clienteDAO.findAll();

			List<ClienteDTO> clienteDTO = new ArrayList<ClienteDTO>();

			for (Cliente clienteTmp : cliente) {
				ClienteDTO clienteDTO2 = new ClienteDTO();

				clienteDTO2.setClieId(clienteTmp.getClieId());
				clienteDTO2.setCelularContacto(
						(clienteTmp.getCelularContacto() != null) ? clienteTmp.getCelularContacto() : null);
				clienteDTO2.setDireccionContacto(
						(clienteTmp.getDireccionContacto() != null) ? clienteTmp.getDireccionContacto() : null);
				clienteDTO2.setEnlaceWeb((clienteTmp.getEnlaceWeb() != null) ? clienteTmp.getEnlaceWeb() : null);
				clienteDTO2.setNit((clienteTmp.getNit() != null) ? clienteTmp.getNit() : null);
				clienteDTO2.setNombreContacto(
						(clienteTmp.getNombreContacto() != null) ? clienteTmp.getNombreContacto() : null);
				clienteDTO2.setNombreEmpresa(
						(clienteTmp.getNombreEmpresa() != null) ? clienteTmp.getNombreEmpresa() : null);
				clienteDTO2.setTelefonoContacto(
						(clienteTmp.getTelefonoContacto() != null) ? clienteTmp.getTelefonoContacto() : null);
				clienteDTO.add(clienteDTO2);
			}

			return clienteDTO;
		} catch (Exception e) {
			throw e;
		}
	}

	@Transactional(readOnly = true)
	public Cliente getCliente(Integer clieId) throws Exception {
		log.debug("getting Cliente instance");

		Cliente entity = null;

		try {
			entity = clienteDAO.findById(clieId);
		} catch (Exception e) {
			log.error("get Cliente failed", e);
			throw new ZMessManager().new FindingException("Cliente");
		} finally {
		}

		return entity;
	}

	@Transactional(readOnly = true)
	public List<Cliente> findPageCliente(String sortColumnName, boolean sortAscending, int startRow, int maxResults)
			throws Exception {
		List<Cliente> entity = null;

		try {
			entity = clienteDAO.findPage(sortColumnName, sortAscending, startRow, maxResults);
		} catch (Exception e) {
			throw new ZMessManager().new FindingException("Cliente Count");
		} finally {
		}

		return entity;
	}

	@Transactional(readOnly = true)
	public Long findTotalNumberCliente() throws Exception {
		Long entity = null;

		try {
			entity = clienteDAO.count();
		} catch (Exception e) {
			throw new ZMessManager().new FindingException("Cliente Count");
		} finally {
		}

		return entity;
	}

	/**
	 *
	 * @param varibles
	 *            este arreglo debera tener:
	 *
	 *            [0] = String variable = (String) varibles[i]; representa como
	 *            se llama la variable en el pojo
	 *
	 *            [1] = Boolean booVariable = (Boolean) varibles[i + 1];
	 *            representa si el valor necesita o no ''(comillas simples)usado
	 *            para campos de tipo string
	 *
	 *            [2] = Object value = varibles[i + 2]; representa el valor que
	 *            se va a buscar en la BD
	 *
	 *            [3] = String comparator = (String) varibles[i + 3]; representa
	 *            que tipo de busqueda voy a hacer.., ejemplo: where
	 *            nombre=william o where nombre<>william, en este campo iria el
	 *            tipo de comparador que quiero si es = o <>
	 *
	 *            Se itera de 4 en 4..., entonces 4 registros del arreglo
	 *            representan 1 busqueda en un campo, si se ponen mas pues el
	 *            continuara buscando en lo que se le ingresen en los otros 4
	 *
	 *
	 * @param variablesBetween
	 *
	 *            la diferencia son estas dos posiciones
	 *
	 *            [0] = String variable = (String) varibles[j]; la variable ne
	 *            la BD que va a ser buscada en un rango
	 *
	 *            [1] = Object value = varibles[j + 1]; valor 1 para buscar en
	 *            un rango
	 *
	 *            [2] = Object value2 = varibles[j + 2]; valor 2 para buscar en
	 *            un rango ejempolo: a > 1 and a < 5 --> 1 seria value y 5 seria
	 *            value2
	 *
	 *            [3] = String comparator1 = (String) varibles[j + 3];
	 *            comparador 1 ejemplo: a comparator1 1 and a < 5
	 *
	 *            [4] = String comparator2 = (String) varibles[j + 4];
	 *            comparador 2 ejemplo: a comparador1>1 and a comparador2<5 (el
	 *            original: a > 1 and a < 5) *
	 * @param variablesBetweenDates(en
	 *            este caso solo para mysql) [0] = String variable = (String)
	 *            varibles[k]; el nombre de la variable que hace referencia a
	 *            una fecha
	 *
	 *            [1] = Object object1 = varibles[k + 2]; fecha 1 a
	 *            comparar(deben ser dates)
	 *
	 *            [2] = Object object2 = varibles[k + 3]; fecha 2 a
	 *            comparar(deben ser dates)
	 *
	 *            esto hace un between entre las dos fechas.
	 *
	 * @return lista con los objetos que se necesiten
	 * @throws Exception
	 */
	@Transactional(readOnly = true)
	public List<Cliente> findByCriteria(Object[] variables, Object[] variablesBetween, Object[] variablesBetweenDates)
			throws Exception {
		List<Cliente> list = new ArrayList<Cliente>();
		String where = new String();
		String tempWhere = new String();

		if (variables != null) {
			for (int i = 0; i < variables.length; i++) {
				if ((variables[i] != null) && (variables[i + 1] != null) && (variables[i + 2] != null)
						&& (variables[i + 3] != null)) {
					String variable = (String) variables[i];
					Boolean booVariable = (Boolean) variables[i + 1];
					Object value = variables[i + 2];
					String comparator = (String) variables[i + 3];

					if (booVariable.booleanValue()) {
						tempWhere = (tempWhere.length() == 0)
								? ("(model." + variable + " " + comparator + " \'" + value + "\' )")
								: (tempWhere + " AND (model." + variable + " " + comparator + " \'" + value + "\' )");
					} else {
						tempWhere = (tempWhere.length() == 0)
								? ("(model." + variable + " " + comparator + " " + value + " )")
								: (tempWhere + " AND (model." + variable + " " + comparator + " " + value + " )");
					}
				}

				i = i + 3;
			}
		}

		if (variablesBetween != null) {
			for (int j = 0; j < variablesBetween.length; j++) {
				if ((variablesBetween[j] != null) && (variablesBetween[j + 1] != null)
						&& (variablesBetween[j + 2] != null) && (variablesBetween[j + 3] != null)
						&& (variablesBetween[j + 4] != null)) {
					String variable = (String) variablesBetween[j];
					Object value = variablesBetween[j + 1];
					Object value2 = variablesBetween[j + 2];
					String comparator1 = (String) variablesBetween[j + 3];
					String comparator2 = (String) variablesBetween[j + 4];
					tempWhere = (tempWhere.length() == 0)
							? ("(" + value + " " + comparator1 + " " + variable + " and " + variable + " " + comparator2
									+ " " + value2 + " )")
							: (tempWhere + " AND (" + value + " " + comparator1 + " " + variable + " and " + variable
									+ " " + comparator2 + " " + value2 + " )");
				}

				j = j + 4;
			}
		}

		if (variablesBetweenDates != null) {
			for (int k = 0; k < variablesBetweenDates.length; k++) {
				if ((variablesBetweenDates[k] != null) && (variablesBetweenDates[k + 1] != null)
						&& (variablesBetweenDates[k + 2] != null)) {
					String variable = (String) variablesBetweenDates[k];
					Object object1 = variablesBetweenDates[k + 1];
					Object object2 = variablesBetweenDates[k + 2];
					String value = null;
					String value2 = null;

					try {
						Date date1 = (Date) object1;
						Date date2 = (Date) object2;
						value = Utilities.formatDateWithoutTimeInAStringForBetweenWhere(date1);
						value2 = Utilities.formatDateWithoutTimeInAStringForBetweenWhere(date2);
					} catch (Exception e) {
						list = null;
						throw e;
					}

					tempWhere = (tempWhere.length() == 0)
							? ("(model." + variable + " between \'" + value + "\' and \'" + value2 + "\')")
							: (tempWhere + " AND (model." + variable + " between \'" + value + "\' and \'" + value2
									+ "\')");
				}

				k = k + 2;
			}
		}

		if (tempWhere.length() == 0) {
			where = null;
		} else {
			where = "(" + tempWhere + ")";
		}

		try {
			list = clienteDAO.findByCriteria(where);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
		}

		return list;
	}

	// DPL20160806 Registro de nuevo cliente con la lógica pedida en las
	// revisiones
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void registrarNuevoCliente(Cliente cliente) throws Exception {
		log.info("Registrando un nuevo cliente, " + cliente.getNombreEmpresa());
		Cliente clienteTemporal;
		try {

			if (cliente.getNombreEmpresa() == null) {
				throw new ZMessManager().new EmptyFieldException("Nombre de la Empresa");
			}

			if ((cliente.getNombreEmpresa() != null)
					&& Utilities.checkWordAndCheckWithlength(cliente.getNombreEmpresa(), 200) == false) {
				throw new ZMessManager().new NotValidFormatException("Nombre de la Empresa");
			}

			clienteTemporal = clienteDAO.findEntityByProperty("nombreEmpresa", cliente.getNombreEmpresa());
			if (clienteTemporal != null) {
				throw new Exception("Ya existe una empresa con el nombre: " + clienteTemporal.getNombreEmpresa());
			}

			if (cliente.getNit() == null) {
				throw new ZMessManager().new EmptyFieldException("Nit de la Empresa");
			}

			if ((cliente.getNit() != null) && Utilities.checkWordAndCheckWithlength(cliente.getNit(), 30) == false) {
				throw new ZMessManager().new NotValidFormatException("Nit de la Empresa");
			}

			clienteTemporal = clienteDAO.findEntityByProperty("nit", cliente.getNit());
			if (clienteTemporal != null) {
				throw new Exception("Ya existe una empresa con el nit: " + clienteTemporal.getNit());
			}

			if ((cliente.getEnlaceWeb() != null)
					&& Utilities.checkWordAndCheckWithlength(cliente.getEnlaceWeb(), 200) == false) {
				throw new ZMessManager().new NotValidFormatException("Enlace Web");
			}

			if (cliente.getNombreContacto() == null) {
				throw new ZMessManager().new EmptyFieldException("Nombre Contacto");
			}

			if ((cliente.getNombreContacto() != null)
					&& Utilities.checkWordAndCheckWithlength(cliente.getNombreContacto(), 200) == false) {
				throw new ZMessManager().new NotValidFormatException("Nombre Contacto");
			}

			if (cliente.getCelularContacto() == null) {
				throw new ZMessManager().new EmptyFieldException("Celular Contacto");
			}

			if ((cliente.getCelularContacto() != null)
					&& Utilities.checkWordAndCheckWithlength(cliente.getCelularContacto(), 200) == false) {
				throw new ZMessManager().new NotValidFormatException("Celular Contacto");
			}

			if (cliente.getTelefonoContacto() == null) {
				throw new ZMessManager().new EmptyFieldException("Teléfono Contacto");
			}

			if ((cliente.getTelefonoContacto() != null)
					&& Utilities.checkWordAndCheckWithlength(cliente.getTelefonoContacto(), 20) == false) {
				throw new ZMessManager().new NotValidFormatException("Teléfono Contacto");
			}

			
			if (cliente.getDireccionContacto() == null) {
				throw new ZMessManager().new EmptyFieldException("Dirección Contacto");
			}

			if ((cliente.getDireccionContacto() != null)
					&& Utilities.checkWordAndCheckWithlength(cliente.getDireccionContacto(), 200) == false) {
				throw new ZMessManager().new NotValidFormatException("Dirección Contacto");
			}
			
			if(cliente.getEnlaceWeb()!=null){
				UrlValidator urlValidator = new UrlValidator();
	        	if(urlValidator.isValid(cliente.getEnlaceWeb())==false){
	        		throw new Exception("Enlace Web no es correcto");
	        	}
			}

			// DPL 20160806 Llamado al DAO para registrar el nuevo cliente
			// despues
			// de haber pasado las validaciones en la lógica
			clienteDAO.save(cliente);
			clienteTemporal = null;
			log.info("Se ha registrado el cliente, " + cliente.getNombreEmpresa());
		} catch (Exception e) {
			log.error("Error guardando el cliente, " + cliente.getNombreEmpresa(), e);
			clienteTemporal = null;
			throw e;
		} finally {
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Cliente buscarClientePorNit(String nit) throws Exception {
		if (nit.trim().equals("") == true) {
			throw new Exception("No hay nit para buscar empresa");
		}
		Cliente cliente = clienteDAO.findEntityByProperty("nit", nit);
		return cliente;
	}

}
