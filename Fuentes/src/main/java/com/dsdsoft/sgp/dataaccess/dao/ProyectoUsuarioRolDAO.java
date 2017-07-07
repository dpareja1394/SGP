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
import com.dsdsoft.sgp.modelo.ProyectoUsuarioRol;
import com.dsdsoft.sgp.modelo.ProyectoUsuarioRolId;
import com.dsdsoft.sgp.modelo.dto.ProyectoUsuarioRolDTO;


/**
 * A data access object (DAO) providing persistence and search support for
 * ProyectoUsuarioRol entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 *
 * @see lidis.ProyectoUsuarioRol
 */
@Scope("singleton")
@Repository("ProyectoUsuarioRolDAO")
public class ProyectoUsuarioRolDAO extends HibernateDaoImpl<ProyectoUsuarioRol, ProyectoUsuarioRolId>
    implements IProyectoUsuarioRolDAO {
    private static final Logger log = LoggerFactory.getLogger(ProyectoUsuarioRolDAO.class);
    @Resource
    private SessionFactory sessionFactory;

    public static IProyectoUsuarioRolDAO getFromApplicationContext(
        ApplicationContext ctx) {
        return (IProyectoUsuarioRolDAO) ctx.getBean("ProyectoUsuarioRolDAO");
    }

	@Override
	public List<ProyectoUsuarioRolDTO> listaProyectoUsuarioRolDadoProyecto(Integer proyId) throws Exception {
		List<ProyectoUsuarioRolDTO> lista = null;
		try {
			Query query = getSession().getNamedQuery("consulta_proyecto_usuario_rol_dado_proyecto");
			query.setParameter("pProyId", proyId);
			
			query.setResultTransformer(Transformers.aliasToBean(ProyectoUsuarioRolDTO.class));
			lista = query.list();
					
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
		return lista;
	}
}
