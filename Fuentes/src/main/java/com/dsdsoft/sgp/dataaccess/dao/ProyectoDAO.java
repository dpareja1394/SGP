package com.dsdsoft.sgp.dataaccess.dao;

import com.dsdsoft.sgp.dataaccess.api.HibernateDaoImpl;
import com.dsdsoft.sgp.modelo.Proyecto;

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

import java.math.BigDecimal;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;


/**
 * A data access object (DAO) providing persistence and search support for
 * Proyecto entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 *
 * @see lidis.Proyecto
 */
@Scope("singleton")
@Repository("ProyectoDAO")
public class ProyectoDAO extends HibernateDaoImpl<Proyecto, Integer>
    implements IProyectoDAO {
    private static final Logger log = LoggerFactory.getLogger(ProyectoDAO.class);
    @Resource
    private SessionFactory sessionFactory;

    public static IProyectoDAO getFromApplicationContext(ApplicationContext ctx) {
        return (IProyectoDAO) ctx.getBean("ProyectoDAO");
    }

	@Override
	public List<Proyecto> listaProyectosDadoCliente(Integer clieId) throws Exception {
		List<Proyecto> listaProyectos = null;
		try {
			String hql = "SELECT p FROM Proyecto p WHERE p.cliente.clieId = "+clieId+" ORDER BY p.descProyecto ASC ";
			Query query = getSession().createQuery(hql);
			listaProyectos = query.list();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
		return listaProyectos;
	}
}
