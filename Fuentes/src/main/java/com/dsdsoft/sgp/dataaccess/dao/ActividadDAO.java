package com.dsdsoft.sgp.dataaccess.dao;

import com.dsdsoft.sgp.dataaccess.api.HibernateDaoImpl;
import com.dsdsoft.sgp.modelo.Actividad;
import com.dsdsoft.sgp.modelo.dto.ActividadDTO;
import com.dsdsoft.sgp.modelo.dto.HistoriaDeUsuarioDTO;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import org.hibernate.criterion.Example;
import org.hibernate.transform.Transformers;
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
 * Actividad entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 *
 * @see lidis.Actividad
 */
@Scope("singleton")
@Repository("ActividadDAO")
public class ActividadDAO extends HibernateDaoImpl<Actividad, Integer>
    implements IActividadDAO {
    private static final Logger log = LoggerFactory.getLogger(ActividadDAO.class);
    @Resource
    private SessionFactory sessionFactory;

    public static IActividadDAO getFromApplicationContext(
        ApplicationContext ctx) {
        return (IActividadDAO) ctx.getBean("ActividadDAO");
    }

	/**
	 * @author Daniel Pareja Londoño
	 * @version sept. 29, 2019
	 *
	 * @see com.dsdsoft.sgp.dataaccess.dao.IActividadDAO#consultarActividadesDeUsuario(java.lang.String)
	 *
	 */
	@Override
	public List<ActividadDTO> consultarActividadesDeUsuario(String emailUsuario) throws Exception {
		List<ActividadDTO> lista = null;
		try {
			Query query = getSession().getNamedQuery("consultarActividadesDeUsuario");
			query.setParameter("pEmail", emailUsuario);
			query.setResultTransformer(Transformers.aliasToBean(ActividadDTO.class));
			lista = query.list();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
		return lista;
	}
}
