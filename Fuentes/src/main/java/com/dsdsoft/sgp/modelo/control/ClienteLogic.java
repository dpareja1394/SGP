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
import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.validator.routines.UrlValidator;

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

	@Autowired
	private IUsuarioLogic usuarioLogic;
	
	@Autowired
	private ICiudadLogic ciudadLogic;
	
	@Autowired
	private IDepartamentoLogic departamentoLogic;
	
	@Autowired
	private IPaisLogic paisLogic;
	

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

			if (entity.getEnlaceWeb() != null && entity.getEnlaceWeb().trim().equals("") == false) {
				if (entity.getEnlaceWeb().trim().substring(0, 4).equals("http")) {
					UrlValidator urlValidator = new UrlValidator();
					if (urlValidator.isValid(entity.getEnlaceWeb()) == false) {
						throw new Exception("Enlace Web no es correcto");
					}
				} else {
					String enlaceWeb = "https://" + entity.getEnlaceWeb().trim();
					UrlValidator urlValidator = new UrlValidator();
					if (urlValidator.isValid(enlaceWeb) == false) {
						throw new Exception("Enlace Web no es correcto");
					}
					entity.setEnlaceWeb(enlaceWeb);
				}
			}

			if (entity.getEmailContacto() != null && entity.getEmailContacto().trim().equals("") == false) {
				if (EmailValidator.getInstance().isValid(entity.getEmailContacto()) == false) {
					throw new Exception("Debe ingresar un correo electrónico para el contacto.");
				}
			}

			if (entity.getUsuarioByUsuarioModificacion() == null) {
				throw new Exception("No ha llegado el usuario para la modificación");
			}
			Usuario usuarioModifica = usuarioLogic.getUsuario(entity.getUsuarioByUsuarioModificacion().getUsuaId());
			if (usuarioModifica == null) {
				throw new Exception("El usuario de modificación no está registrado en la base de datos");
			}
			entity.setFechaModificacion(new Date());

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

			// DPL 26 Mayo 2017 Validación de teléfonos, debe haber alguno de
			// los dos
			if ((cliente.getTelefonoContacto() == null || cliente.getTelefonoContacto().trim().equals(""))
					&& (cliente.getCelularContacto() == null || cliente.getCelularContacto().trim().equals(""))) {
				throw new Exception("Debe ingresar un teléfono o un celular");
			}

			if (cliente.getDireccionContacto() == null) {
				throw new ZMessManager().new EmptyFieldException("Dirección Contacto");
			}

			if ((cliente.getDireccionContacto() != null)
					&& Utilities.checkWordAndCheckWithlength(cliente.getDireccionContacto(), 200) == false) {
				throw new ZMessManager().new NotValidFormatException("Dirección Contacto");
			}

			if (cliente.getEnlaceWeb() != null && cliente.getEnlaceWeb().trim().equals("") == false) {
				if (cliente.getEnlaceWeb().trim().substring(0, 4).equals("http")) {
					UrlValidator urlValidator = new UrlValidator();
					if (urlValidator.isValid(cliente.getEnlaceWeb()) == false) {
						throw new Exception("Enlace Web no es correcto");
					}
				} else {
					String enlaceWeb = "http://" + cliente.getEnlaceWeb().trim();
					UrlValidator urlValidator = new UrlValidator();
					if (urlValidator.isValid(enlaceWeb) == false) {
						throw new Exception("Enlace Web no es correcto");
					}
					cliente.setEnlaceWeb(enlaceWeb);
				}
				Cliente clienteEnlaceWeb = clienteDAO.buscarClientesPorEnlaceWeb(cliente.getEnlaceWeb());
				if (clienteEnlaceWeb != null) {
					throw new Exception("Ya existe un cliente con el enlace web ingresado, " + cliente.getEnlaceWeb());
				}
				clienteEnlaceWeb = null;

			}

			if (cliente.getEmailContacto() != null && cliente.getEmailContacto().trim().equals("") == false) {
				if (EmailValidator.getInstance().isValid(cliente.getEmailContacto()) == false) {
					throw new Exception("El correo " + cliente.getEmailContacto()
							+ " no es una dirección de correo electrónico correcta.");
				}
			}
			if (cliente.getCiudad() == null || cliente.getCiudad().getCiudId().equals(0)) {
				throw new Exception("No ha seleccionado ubicación correcta");
			}
			if (cliente.getUsuarioByUsuarioCreacion() == null) {
				throw new Exception("No ha llegado el usuario para la creación del Cliente");
			}

			Usuario usuarioCreacion = usuarioLogic.getUsuario(cliente.getUsuarioByUsuarioCreacion().getUsuaId());
			if (usuarioCreacion == null) {
				throw new Exception("El usuario de creación no está registrado en la base de datos");
			}

			// DPL 20160806 Llamado al DAO para registrar el nuevo cliente
			// despues
			// de haber pasado las validaciones en la lógica
			cliente.setFechaCreacion(new Date());
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

	@Override
	@Transactional(readOnly = true)
	public List<Cliente> listaClienteOrdenadasPorEmpresa() throws Exception {
		return clienteDAO.listaClienteOrdenadasPorEmpresa();
	}

	@Override
	@Transactional(readOnly = true)
	public List<ClienteDTO> listaClientesDTOConCiudad() throws Exception {
		List<ClienteDTO> lista = null;
		try {
			List<Cliente> listaClientes = getCliente();
			lista = new ArrayList<ClienteDTO>();
			for (Cliente cliente : listaClientes) {
				ClienteDTO clienteDTO = new ClienteDTO();
				clienteDTO.setCelularContacto(cliente.getCelularContacto());
				clienteDTO.setClieId(cliente.getClieId());
				clienteDTO.setDireccionContacto(cliente.getDireccionContacto());
				clienteDTO.setEnlaceWeb(cliente.getEnlaceWeb());
				clienteDTO.setNit(cliente.getNit());
				clienteDTO.setNombreContacto(cliente.getNombreContacto());
				clienteDTO.setNombreEmpresa(cliente.getNombreEmpresa());
				clienteDTO.setTelefonoContacto(cliente.getTelefonoContacto());
				Ciudad ciudad = ciudadLogic.getCiudad(cliente.getCiudad().getCiudId());
				Departamento departamento = departamentoLogic.getDepartamento(ciudad.getDepartamento().getDepaId());
				Pais pais = paisLogic.getPais(departamento.getPais().getPaisId());
				
				clienteDTO.setCiudadDepartamentoPais(ciudad.getNombreCiudad().toUpperCase()+" - "
							+departamento.getNombreDepartamento().toUpperCase()+" - "
							+pais.getNombrePais().toUpperCase());
				
				lista.add(clienteDTO);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
		return lista;
	}

}
