package com.dsdsoft.sgp.dataaccess.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.dsdsoft.sgp.dataaccess.api.HibernateDaoImpl;
import com.dsdsoft.sgp.modelo.HistoriaDeUsuario;
import com.dsdsoft.sgp.modelo.dto.HistoriaDeUsuarioDTO;


/**
 * A data access object (DAO) providing persistence and search support for
 * HistoriaDeUsuario entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 *
 * @see lidis.HistoriaDeUsuario
 */
@Scope("singleton")
@Repository("HistoriaDeUsuarioDAO")
public class HistoriaDeUsuarioDAO extends HibernateDaoImpl<HistoriaDeUsuario, Integer>
    implements IHistoriaDeUsuarioDAO {
    private static final Logger log = LoggerFactory.getLogger(HistoriaDeUsuarioDAO.class);
    @Resource
    private SessionFactory sessionFactory;

    public static IHistoriaDeUsuarioDAO getFromApplicationContext(
        ApplicationContext ctx) {
        return (IHistoriaDeUsuarioDAO) ctx.getBean("HistoriaDeUsuarioDAO");
    }

	/**
	 * @author Daniel Pareja Londoño
	 * @version may. 28, 2019
	 *
	 * @see com.dsdsoft.sgp.dataaccess.dao.IHistoriaDeUsuarioDAO#consultarHistoriasUsuarioPorFiltros(java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 *
	 */
	@Override
	public List<HistoriaDeUsuarioDTO> consultarHistoriasUsuarioPorFiltros(Integer usuaId, Integer proyId,
			Integer requId, Integer eshiId) throws Exception {
		List<HistoriaDeUsuarioDTO> lista = null;
		
		try {
			Query query = getSession().getNamedQuery("consultarHistoriasUsuarioPorFiltros");
			query.setParameter("pUsuaId", usuaId);
			query.setParameter("pProyId", proyId);
			query.setParameter("pRequId", requId);
			query.setParameter("pEshiId", eshiId);

			query.setResultTransformer(Transformers.aliasToBean(HistoriaDeUsuarioDTO.class));
			lista = query.list();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
		return lista;
	}
}
