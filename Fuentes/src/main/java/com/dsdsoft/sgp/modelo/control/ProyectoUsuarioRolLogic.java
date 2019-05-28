package com.dsdsoft.sgp.modelo.control;

import com.dsdsoft.sgp.dataaccess.dao.*;
import com.dsdsoft.sgp.exceptions.*;
import com.dsdsoft.sgp.modelo.*;
import com.dsdsoft.sgp.modelo.dto.ProyectoUsuarioRolDTO;
import com.dsdsoft.sgp.utilities.Utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Scope;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;


/**
* @author Zathura Code Generator http://code.google.com/p/zathura
* www.zathuracode.org
*
*/
@Scope("singleton")
@Service("ProyectoUsuarioRolLogic")
public class ProyectoUsuarioRolLogic implements IProyectoUsuarioRolLogic {
    private static final Logger log = LoggerFactory.getLogger(ProyectoUsuarioRolLogic.class);

    /**
     * DAO injected by Spring that manages ProyectoUsuarioRol entities
     *
     */
    @Autowired
    private IProyectoUsuarioRolDAO proyectoUsuarioRolDAO;

    /**
    * Logic injected by Spring that manages Proyecto entities
    *
    */
    @Autowired
    IProyectoLogic logicProyecto1;

    /**
    * Logic injected by Spring that manages Rol entities
    *
    */
    @Autowired
    IRolLogic logicRol2;

    /**
    * Logic injected by Spring that manages Usuario entities
    *
    */
    @Autowired
    IUsuarioLogic logicUsuario3;

    @Transactional(readOnly = true)
    public List<ProyectoUsuarioRol> getProyectoUsuarioRol()
        throws Exception {
        log.debug("finding all ProyectoUsuarioRol instances");

        List<ProyectoUsuarioRol> list = new ArrayList<ProyectoUsuarioRol>();

        try {
            list = proyectoUsuarioRolDAO.findAll();
        } catch (Exception e) {
            log.error("finding all ProyectoUsuarioRol failed", e);
            throw new ZMessManager().new GettingException(ZMessManager.ALL +
                "ProyectoUsuarioRol");
        } finally {
        }

        return list;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void saveProyectoUsuarioRol(ProyectoUsuarioRol entity)
        throws Exception {
        log.debug("saving ProyectoUsuarioRol instance");

        try {
            if (entity.getProyecto() == null) {
                throw new ZMessManager().new ForeignException("proyecto");
            }

            if (entity.getRol() == null) {
                throw new ZMessManager().new ForeignException("rol");
            }

            if (entity.getUsuario() == null) {
                throw new ZMessManager().new ForeignException("usuario");
            }

            if (entity.getId().getUsuaId() == null) {
                throw new ZMessManager().new EmptyFieldException("usuaId");
            }

            if (entity.getId().getProyId() == null) {
                throw new ZMessManager().new EmptyFieldException("proyId");
            }

            if (entity.getProyecto().getProyId() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "proyId_Proyecto");
            }

            if (entity.getRol().getRolId() == null) {
                throw new ZMessManager().new EmptyFieldException("rolId_Rol");
            }

            if (entity.getUsuario().getUsuaId() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "usuaId_Usuario");
            }

            if (getProyectoUsuarioRol(entity.getId()) != null) {
                throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
            }

            proyectoUsuarioRolDAO.save(entity);

            log.debug("save ProyectoUsuarioRol successful");
        } catch (Exception e) {
            log.error("save ProyectoUsuarioRol failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deleteProyectoUsuarioRol(ProyectoUsuarioRol entity)
        throws Exception {
        log.debug("deleting ProyectoUsuarioRol instance");

        if (entity == null) {
            throw new ZMessManager().new NullEntityExcepcion(
                "ProyectoUsuarioRol");
        }

        if (entity.getId().getUsuaId() == null) {
            throw new ZMessManager().new EmptyFieldException("usuaId");
        }

        if (entity.getId().getProyId() == null) {
            throw new ZMessManager().new EmptyFieldException("proyId");
        }

        try {
            proyectoUsuarioRolDAO.delete(entity);

            log.debug("delete ProyectoUsuarioRol successful");
        } catch (Exception e) {
            log.error("delete ProyectoUsuarioRol failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void updateProyectoUsuarioRol(ProyectoUsuarioRol entity)
        throws Exception {
        log.debug("updating ProyectoUsuarioRol instance");

        try {
            if (entity == null) {
                throw new ZMessManager().new NullEntityExcepcion(
                    "ProyectoUsuarioRol");
            }

            if (entity.getProyecto() == null) {
                throw new ZMessManager().new ForeignException("proyecto");
            }

            if (entity.getRol() == null) {
                throw new ZMessManager().new ForeignException("rol");
            }

            if (entity.getUsuario() == null) {
                throw new ZMessManager().new ForeignException("usuario");
            }

            if (entity.getId().getUsuaId() == null) {
                throw new ZMessManager().new EmptyFieldException("usuaId");
            }

            if (entity.getId().getProyId() == null) {
                throw new ZMessManager().new EmptyFieldException("proyId");
            }

            if (entity.getProyecto().getProyId() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "proyId_Proyecto");
            }

            if (entity.getRol().getRolId() == null) {
                throw new ZMessManager().new EmptyFieldException("rolId_Rol");
            }

            if (entity.getUsuario().getUsuaId() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "usuaId_Usuario");
            }

            proyectoUsuarioRolDAO.update(entity);

            log.debug("update ProyectoUsuarioRol successful");
        } catch (Exception e) {
            log.error("update ProyectoUsuarioRol failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = true)
    public List<ProyectoUsuarioRolDTO> getDataProyectoUsuarioRol()
        throws Exception {
        try {
            List<ProyectoUsuarioRol> proyectoUsuarioRol = proyectoUsuarioRolDAO.findAll();

            List<ProyectoUsuarioRolDTO> proyectoUsuarioRolDTO = new ArrayList<ProyectoUsuarioRolDTO>();

            for (ProyectoUsuarioRol proyectoUsuarioRolTmp : proyectoUsuarioRol) {
                ProyectoUsuarioRolDTO proyectoUsuarioRolDTO2 = new ProyectoUsuarioRolDTO();

                proyectoUsuarioRolDTO2.setUsuaId(proyectoUsuarioRolTmp.getId()
                                                                      .getUsuaId());
                proyectoUsuarioRolDTO2.setProyId(proyectoUsuarioRolTmp.getId()
                                                                      .getProyId());
                proyectoUsuarioRolDTO2.setProyId_Proyecto((proyectoUsuarioRolTmp.getProyecto()
                                                                                .getProyId() != null)
                    ? proyectoUsuarioRolTmp.getProyecto().getProyId() : null);
                proyectoUsuarioRolDTO2.setRolId_Rol((proyectoUsuarioRolTmp.getRol()
                                                                          .getRolId() != null)
                    ? proyectoUsuarioRolTmp.getRol().getRolId() : null);
                proyectoUsuarioRolDTO2.setUsuaId_Usuario((proyectoUsuarioRolTmp.getUsuario()
                                                                               .getUsuaId() != null)
                    ? proyectoUsuarioRolTmp.getUsuario().getUsuaId() : null);
                proyectoUsuarioRolDTO.add(proyectoUsuarioRolDTO2);
            }

            return proyectoUsuarioRolDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public ProyectoUsuarioRol getProyectoUsuarioRol(ProyectoUsuarioRolId id)
        throws Exception {
        log.debug("getting ProyectoUsuarioRol instance");

        ProyectoUsuarioRol entity = null;

        try {
            entity = proyectoUsuarioRolDAO.findById(id);
        } catch (Exception e) {
            log.error("get ProyectoUsuarioRol failed", e);
            throw new ZMessManager().new FindingException("ProyectoUsuarioRol");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public List<ProyectoUsuarioRol> findPageProyectoUsuarioRol(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception {
        List<ProyectoUsuarioRol> entity = null;

        try {
            entity = proyectoUsuarioRolDAO.findPage(sortColumnName,
                    sortAscending, startRow, maxResults);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException(
                "ProyectoUsuarioRol Count");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public Long findTotalNumberProyectoUsuarioRol() throws Exception {
        Long entity = null;

        try {
            entity = proyectoUsuarioRolDAO.count();
        } catch (Exception e) {
            throw new ZMessManager().new FindingException(
                "ProyectoUsuarioRol Count");
        } finally {
        }

        return entity;
    }

    /**
    *
    * @param varibles
    *            este arreglo debera tener:
    *
    * [0] = String variable = (String) varibles[i]; representa como se llama la
    * variable en el pojo
    *
    * [1] = Boolean booVariable = (Boolean) varibles[i + 1]; representa si el
    * valor necesita o no ''(comillas simples)usado para campos de tipo string
    *
    * [2] = Object value = varibles[i + 2]; representa el valor que se va a
    * buscar en la BD
    *
    * [3] = String comparator = (String) varibles[i + 3]; representa que tipo
    * de busqueda voy a hacer.., ejemplo: where nombre=william o where nombre<>william,
        * en este campo iria el tipo de comparador que quiero si es = o <>
            *
            * Se itera de 4 en 4..., entonces 4 registros del arreglo representan 1
            * busqueda en un campo, si se ponen mas pues el continuara buscando en lo
            * que se le ingresen en los otros 4
            *
            *
            * @param variablesBetween
            *
            * la diferencia son estas dos posiciones
            *
            * [0] = String variable = (String) varibles[j]; la variable ne la BD que va
            * a ser buscada en un rango
            *
            * [1] = Object value = varibles[j + 1]; valor 1 para buscar en un rango
            *
            * [2] = Object value2 = varibles[j + 2]; valor 2 para buscar en un rango
            * ejempolo: a > 1 and a < 5 --> 1 seria value y 5 seria value2
                *
                * [3] = String comparator1 = (String) varibles[j + 3]; comparador 1
                * ejemplo: a comparator1 1 and a < 5
                    *
                    * [4] = String comparator2 = (String) varibles[j + 4]; comparador 2
                    * ejemplo: a comparador1>1  and a comparador2<5  (el original: a > 1 and a <
                            * 5) *
                            * @param variablesBetweenDates(en
                            *            este caso solo para mysql)
                            *  [0] = String variable = (String) varibles[k]; el nombre de la variable que hace referencia a
                            *            una fecha
                            *
                            * [1] = Object object1 = varibles[k + 2]; fecha 1 a comparar(deben ser
                            * dates)
                            *
                            * [2] = Object object2 = varibles[k + 3]; fecha 2 a comparar(deben ser
                            * dates)
                            *
                            * esto hace un between entre las dos fechas.
                            *
                            * @return lista con los objetos que se necesiten
                            * @throws Exception
                            */
    @Transactional(readOnly = true)
    public List<ProyectoUsuarioRol> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        List<ProyectoUsuarioRol> list = new ArrayList<ProyectoUsuarioRol>();
        String where = new String();
        String tempWhere = new String();

        if (variables != null) {
            for (int i = 0; i < variables.length; i++) {
                if ((variables[i] != null) && (variables[i + 1] != null) &&
                        (variables[i + 2] != null) &&
                        (variables[i + 3] != null)) {
                    String variable = (String) variables[i];
                    Boolean booVariable = (Boolean) variables[i + 1];
                    Object value = variables[i + 2];
                    String comparator = (String) variables[i + 3];

                    if (booVariable.booleanValue()) {
                        tempWhere = (tempWhere.length() == 0)
                            ? ("(model." + variable + " " + comparator + " \'" +
                            value + "\' )")
                            : (tempWhere + " AND (model." + variable + " " +
                            comparator + " \'" + value + "\' )");
                    } else {
                        tempWhere = (tempWhere.length() == 0)
                            ? ("(model." + variable + " " + comparator + " " +
                            value + " )")
                            : (tempWhere + " AND (model." + variable + " " +
                            comparator + " " + value + " )");
                    }
                }

                i = i + 3;
            }
        }

        if (variablesBetween != null) {
            for (int j = 0; j < variablesBetween.length; j++) {
                if ((variablesBetween[j] != null) &&
                        (variablesBetween[j + 1] != null) &&
                        (variablesBetween[j + 2] != null) &&
                        (variablesBetween[j + 3] != null) &&
                        (variablesBetween[j + 4] != null)) {
                    String variable = (String) variablesBetween[j];
                    Object value = variablesBetween[j + 1];
                    Object value2 = variablesBetween[j + 2];
                    String comparator1 = (String) variablesBetween[j + 3];
                    String comparator2 = (String) variablesBetween[j + 4];
                    tempWhere = (tempWhere.length() == 0)
                        ? ("(" + value + " " + comparator1 + " " + variable +
                        " and " + variable + " " + comparator2 + " " + value2 +
                        " )")
                        : (tempWhere + " AND (" + value + " " + comparator1 +
                        " " + variable + " and " + variable + " " +
                        comparator2 + " " + value2 + " )");
                }

                j = j + 4;
            }
        }

        if (variablesBetweenDates != null) {
            for (int k = 0; k < variablesBetweenDates.length; k++) {
                if ((variablesBetweenDates[k] != null) &&
                        (variablesBetweenDates[k + 1] != null) &&
                        (variablesBetweenDates[k + 2] != null)) {
                    String variable = (String) variablesBetweenDates[k];
                    Object object1 = variablesBetweenDates[k + 1];
                    Object object2 = variablesBetweenDates[k + 2];
                    String value = null;
                    String value2 = null;

                    try {
                        Date date1 = (Date) object1;
                        Date date2 = (Date) object2;
                        value = Utilities.formatDateWithoutTimeInAStringForBetweenWhere(date1);
                        value2 = Utilities.formatDateWithoutTimeInAStringForBetweenWhere(date2);
                    } catch (Exception e) {
                        list = null;
                        throw e;
                    }

                    tempWhere = (tempWhere.length() == 0)
                        ? ("(model." + variable + " between \'" + value +
                        "\' and \'" + value2 + "\')")
                        : (tempWhere + " AND (model." + variable +
                        " between \'" + value + "\' and \'" + value2 + "\')");
                }

                k = k + 2;
            }
        }

        if (tempWhere.length() == 0) {
            where = null;
        } else {
            where = "(" + tempWhere + ")";
        }

        try {
            list = proyectoUsuarioRolDAO.findByCriteria(where);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
        }

        return list;
    }

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void guardarOActualizarProyectoUsuarioRol(Integer usuaId, Integer proyId, Integer rolId) throws Exception {
		try {
			if(usuaId == null){
				throw new Exception("No ha llegado el id del usuario");
			}
			if(proyId == null){
				throw new Exception("No ha llegado el id del proyecto");
			}
			if(rolId == null){
				throw new Exception("No ha llegado el id del rol para guardar o actualizar");
			}
			
			Usuario usuario = logicUsuario3.getUsuario(usuaId);
			if(usuario == null){
				throw new Exception("No se ha encontrado el usuario con el Id "+usuaId);
			}
			
			Proyecto proyecto = logicProyecto1.getProyecto(proyId);
			if(proyecto == null){
				throw new Exception("No se ha encontrado un proyecto con el id "+proyId);
			}
			Rol rol = logicRol2.getRol(rolId);
			if(rol == null){
				throw new Exception("No se ha encontrado el rol con el id "+rolId);
			}
			
			ProyectoUsuarioRolId proyectoUsuarioRolId = new ProyectoUsuarioRolId(usuario.getUsuaId().longValue(), proyecto.getProyId().longValue());
			ProyectoUsuarioRol proyectoUsuarioRol = getProyectoUsuarioRol(proyectoUsuarioRolId);
			if(proyectoUsuarioRol != null){
				proyectoUsuarioRol.setRol(rol);
				updateProyectoUsuarioRol(proyectoUsuarioRol);
			}else{
				proyectoUsuarioRol = new ProyectoUsuarioRol();
				proyectoUsuarioRol.setId(proyectoUsuarioRolId);
				proyectoUsuarioRol.setProyecto(proyecto);
				proyectoUsuarioRol.setRol(rol);
				proyectoUsuarioRol.setUsuario(usuario);
				
				saveProyectoUsuarioRol(proyectoUsuarioRol);
			}
			
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<ProyectoUsuarioRolDTO> listaProyectoUsuarioRolDadoProyecto(Integer proyId) throws Exception {
		List<ProyectoUsuarioRolDTO> lista = null;
		try {
			if(proyId == null){
				throw new Exception("No ha llegado el id del proyecto");
			}
			lista = proyectoUsuarioRolDAO.listaProyectoUsuarioRolDadoProyecto(proyId);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
		return lista;
	}
}
