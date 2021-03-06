package com.dsdsoft.sgp.dataaccess.dao;

import com.dsdsoft.sgp.dataaccess.api.HibernateDaoImpl;
import com.dsdsoft.sgp.modelo.Cliente;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import org.hibernate.criterion.Example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import org.springframework.stereotype.Repository;

import static org.hamcrest.CoreMatchers.nullValue;

import java.math.BigDecimal;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;


/**
 * A data access object (DAO) providing persistence and search support for
 * Cliente entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 *
 * @see lidis.Cliente
 */
@Scope("singleton")
@Repository("ClienteDAO")
public class ClienteDAO extends HibernateDaoImpl<Cliente, Integer>
    implements IClienteDAO {
    private static final Logger log = LoggerFactory.getLogger(ClienteDAO.class);
    @Resource
    private SessionFactory sessionFactory;

    public static IClienteDAO getFromApplicationContext(ApplicationContext ctx) {
        return (IClienteDAO) ctx.getBean("ClienteDAO");
    }

	@Override
	public Cliente buscarClientesPorEnlaceWeb(String enlaceWeb) throws Exception {
		Cliente cliente = null;
		try {
			String hql = "SELECT c FROM Cliente c WHERE c.enlaceWeb = '"+enlaceWeb+"'";
			Query query = getSession().createQuery(hql);
			List<Cliente> listaTemporal = query.list();
			cliente = listaTemporal.size()>0 ? listaTemporal.get(0) : null;
		} catch (RuntimeException re) {
			log.error(re.getMessage(), re);
			throw re;
		}
		return cliente;
	}

	@Override
	public List<Cliente> listaClienteOrdenadasPorEmpresa() throws Exception {
		List<Cliente> listaClientesOrdenadaPorEmpresa = null;
		try {
			String hql = "SELECT c FROM Cliente c ORDER BY c.nombreEmpresa ASC ";
			Query query = getSession().createQuery(hql);
			listaClientesOrdenadaPorEmpresa = query.list();
		} catch (RuntimeException re) {
			log.error(re.getMessage(), re);
			throw re;
		}
		return listaClientesOrdenadaPorEmpresa;
	}
}
