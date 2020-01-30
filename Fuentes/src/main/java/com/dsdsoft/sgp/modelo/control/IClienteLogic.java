package com.dsdsoft.sgp.modelo.control;

import com.dsdsoft.sgp.modelo.Cliente;
import com.dsdsoft.sgp.modelo.dto.ClienteDTO;

import java.math.BigDecimal;

import java.util.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @author Zathura Code Generator http://code.google.com/p/zathura
 *         www.zathuracode.org
 *
 */
public interface IClienteLogic {

	/**
	 * Método para obtener la lista de todos los clientes
	 * 
	 * @author Daniel Pareja Londoño
	 * @version ene. 29, 2020
	 * @since 1.8
	 * @return
	 * @throws Exception
	 * @return <b>{@code List<Cliente>}</b> Lista con todos los clientes del sistema
	 *
	 */
	public List<Cliente> obtenerTodosLosClientes() throws Exception;

	/**
	 * Registrar un cliente nuevo
	 * 
	 * @author Daniel Pareja Londoño
	 * @version ene. 29, 2020
	 * @since 1.8
	 * @param cliente
	 * @throws Exception
	 *
	 */
	public void guardarCliente(Cliente cliente) throws Exception;

	/**
	 * Delete an existing Cliente entity
	 *
	 */
	public void deleteCliente(Cliente entity) throws Exception;

	/**
	 * Modificar un cliente existente
	 * 
	 * @author Daniel Pareja Londoño
	 * @version ene. 30, 2020
	 * @since 1.8
	 * @param cliente
	 * @throws Exception
	 *
	 */
	public void modificarCliente(Cliente cliente) throws Exception;

	/**
	 * Load an existing Cliente entity
	 *
	 */
	public Cliente getCliente(Integer clieId) throws Exception;

	public List<Cliente> findByCriteria(Object[] variables, Object[] variablesBetween, Object[] variablesBetweenDates)
			throws Exception;

	public List<Cliente> findPageCliente(String sortColumnName, boolean sortAscending, int startRow, int maxResults)
			throws Exception;

	public Long findTotalNumberCliente() throws Exception;

	public List<ClienteDTO> getDataCliente() throws Exception;

	public void registrarNuevoCliente(Cliente cliente) throws Exception;

	public Cliente buscarClientePorNit(String nit) throws Exception;

	public List<Cliente> listaClienteOrdenadasPorEmpresa() throws Exception;

	public List<ClienteDTO> listaClientesDTOConCiudad() throws Exception;
}
