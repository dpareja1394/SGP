package com.dsdsoft.sgp.dataaccess.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.dsdsoft.sgp.dataaccess.api.HibernateDaoImpl;
import com.dsdsoft.sgp.modelo.Ciudad;

/**
 * A data access object (DAO) providing persistence and search support for
 * Ciudad entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 *
 * @see lidis.Ciudad
 */
@Scope("singleton")
@Repository("CiudadDAO")
public class CiudadDAO extends HibernateDaoImpl<Ciudad, Integer> implements ICiudadDAO {
	private static final Logger log = LoggerFactory.getLogger(CiudadDAO.class);
	@Resource
	private SessionFactory sessionFactory;

	public static ICiudadDAO getFromApplicationContext(ApplicationContext ctx) {
		return (ICiudadDAO) ctx.getBean("CiudadDAO");
	}

	@Override
	public List<Ciudad> buscarCiudadPorDepartamento(Integer depaId) throws Exception {
		try {
			String hql = "SELECT ciud FROM Ciudad ciud WHERE ciud.departamento.depaId = "+depaId;
			Query query = getSession().createQuery(hql);
			return query.list();
		} catch (RuntimeException re) {
			log.error(re.getMessage(), re);
			throw re;
		}
	}
}
