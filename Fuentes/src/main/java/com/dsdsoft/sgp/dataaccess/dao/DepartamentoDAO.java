package com.dsdsoft.sgp.dataaccess.dao;

import com.dsdsoft.sgp.dataaccess.api.HibernateDaoImpl;
import com.dsdsoft.sgp.modelo.Departamento;

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
 * Departamento entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 *
 * @see lidis.Departamento
 */
@Scope("singleton")
@Repository("DepartamentoDAO")
public class DepartamentoDAO extends HibernateDaoImpl<Departamento, Integer>
    implements IDepartamentoDAO {
    private static final Logger log = LoggerFactory.getLogger(DepartamentoDAO.class);
    @Resource
    private SessionFactory sessionFactory;

    public static IDepartamentoDAO getFromApplicationContext(
        ApplicationContext ctx) {
        return (IDepartamentoDAO) ctx.getBean("DepartamentoDAO");
    }

	@Override
	public List<Departamento> buscarDepartamentoPorPais(Integer paisId) throws Exception {
		try {
			String hql = "SELECT depa FROM Departamento depa WHERE depa.pais.paisId = "+paisId;
			Query query = getSession().createQuery(hql);
			return query.list();
		} catch (RuntimeException re) {
			log.error(re.getMessage(), re);
			throw re;			
		}
	}
}
