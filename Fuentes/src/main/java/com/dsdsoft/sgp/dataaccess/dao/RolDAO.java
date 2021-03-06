package com.dsdsoft.sgp.dataaccess.dao;

import com.dsdsoft.sgp.dataaccess.api.HibernateDaoImpl;
import com.dsdsoft.sgp.modelo.Rol;

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
 * Rol entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 *
 * @see lidis.Rol
 */
@Scope("singleton")
@Repository("RolDAO")
public class RolDAO extends HibernateDaoImpl<Rol, Integer> implements IRolDAO {
    private static final Logger log = LoggerFactory.getLogger(RolDAO.class);
    @Resource
    private SessionFactory sessionFactory;

    public static IRolDAO getFromApplicationContext(ApplicationContext ctx) {
        return (IRolDAO) ctx.getBean("RolDAO");
    }

	@Override
	public List<Rol> listaRolesOrdenadaPorDescripcionAscendente() throws Exception {
		List<Rol> listaRoles = null;
		try {
			String hql = "SELECT r FROM Rol r ORDER BY r.descRol ASC";
			Query query = getSession().createQuery(hql);
			listaRoles = query.list();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
		return listaRoles;
	}

	@Override
	public Rol rolDeUnUsuarioEnUnProyecto(Integer usuaId, Integer proyId) throws Exception {
		Rol rol = null;
		try {
			Query query = getSession().getNamedQuery("consultar_rol_dado_usuario_proyecto");
			query.setParameter("pUsuaId", usuaId);
			query.setParameter("pProyId", proyId);
			rol = (Rol) (query.list().size()>0?query.list().get(0):null);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
		return rol;
	}

}
