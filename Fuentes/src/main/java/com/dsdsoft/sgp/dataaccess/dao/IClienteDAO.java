package com.dsdsoft.sgp.dataaccess.dao;

import java.util.List;

import com.dsdsoft.sgp.dataaccess.api.Dao;
import com.dsdsoft.sgp.modelo.Cliente;


/**
* Interface for   ClienteDAO.
*
*/
public interface IClienteDAO extends Dao<Cliente, Integer> {
	public Cliente buscarClientesPorEnlaceWeb(String enlaceWeb) throws Exception;
	
	public List<Cliente> listaClienteOrdenadasPorEmpresa() throws Exception;
}
