package com.dsdsoft.sgp.modelo.control;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dsdsoft.sgp.dataaccess.dao.IHistoriaDeUsuarioDAO;
import com.dsdsoft.sgp.exceptions.ZMessManager;
import com.dsdsoft.sgp.modelo.HistoriaDeUsuario;
import com.dsdsoft.sgp.modelo.dto.HistoriaDeUsuarioDTO;
import com.dsdsoft.sgp.utilities.Utilities;


/**
* @author Zathura Code Generator http://code.google.com/p/zathura
* www.zathuracode.org
*
*/
@Scope("singleton")
@Service("HistoriaDeUsuarioLogic")
public class HistoriaDeUsuarioLogic implements IHistoriaDeUsuarioLogic {
    private static final Logger log = LoggerFactory.getLogger(HistoriaDeUsuarioLogic.class);

    /**
     * DAO injected by Spring that manages HistoriaDeUsuario entities
     *
     */
    @Autowired
    private IHistoriaDeUsuarioDAO historiaDeUsuarioDAO;

    /**
    * Logic injected by Spring that manages EstadoHistoriaUsuario entities
    *
    */
    @Autowired
    IEstadoHistoriaUsuarioLogic logicEstadoHistoriaUsuario1;

    /**
    * Logic injected by Spring that manages Requerimiento entities
    *
    */
    @Autowired
    IRequerimientoLogic logicRequerimiento2;

    /**
    * Logic injected by Spring that manages Usuario entities
    *
    */
    @Autowired
    IUsuarioLogic logicUsuario3;

    @Transactional(readOnly = true)
    public List<HistoriaDeUsuario> getHistoriaDeUsuario()
        throws Exception {
        log.debug("finding all HistoriaDeUsuario instances");

        List<HistoriaDeUsuario> list = new ArrayList<HistoriaDeUsuario>();

        try {
            list = historiaDeUsuarioDAO.findAll();
        } catch (Exception e) {
            log.error("finding all HistoriaDeUsuario failed", e);
            throw new ZMessManager().new GettingException(ZMessManager.ALL +
                "HistoriaDeUsuario");
        } finally {
        }

        return list;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void saveHistoriaDeUsuario(HistoriaDeUsuario entity)
        throws Exception {
        log.debug("saving HistoriaDeUsuario instance");

        try {
            if (entity.getEstadoHistoriaUsuario() == null) {
                throw new ZMessManager().new ForeignException(
                    "estadoHistoriaUsuario");
            }

            if (entity.getRequerimiento() == null) {
                throw new ZMessManager().new ForeignException("requerimiento");
            }

            if (entity.getUsuario() == null) {
                throw new ZMessManager().new ForeignException("usuario");
            }

            if (entity.getDetalleHistoria() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "detalleHistoria");
            }

            if ((entity.getDetalleHistoria() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getDetalleHistoria(), 2000) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "detalleHistoria");
            }

            if (entity.getTituloHistoria() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "tituloHistoria");
            }

            if ((entity.getTituloHistoria() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getTituloHistoria(), 150) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "tituloHistoria");
            }

            if (entity.getEstadoHistoriaUsuario().getEshiId() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "eshiId_EstadoHistoriaUsuario");
            }

            if (entity.getRequerimiento().getRequId() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "requId_Requerimiento");
            }

            if (entity.getUsuario().getUsuaId() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "usuaId_Usuario");
            }

            historiaDeUsuarioDAO.save(entity);

            log.debug("save HistoriaDeUsuario successful");
        } catch (Exception e) {
            log.error("save HistoriaDeUsuario failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deleteHistoriaDeUsuario(HistoriaDeUsuario entity)
        throws Exception {
        log.debug("deleting HistoriaDeUsuario instance");

        if (entity == null) {
            throw new ZMessManager().new NullEntityExcepcion(
                "HistoriaDeUsuario");
        }

        if (entity.getHiusId() == null) {
            throw new ZMessManager().new EmptyFieldException("hiusId");
        }

        try {
            historiaDeUsuarioDAO.delete(entity);

            log.debug("delete HistoriaDeUsuario successful");
        } catch (Exception e) {
            log.error("delete HistoriaDeUsuario failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void updateHistoriaDeUsuario(HistoriaDeUsuario entity)
        throws Exception {
        log.debug("updating HistoriaDeUsuario instance");

        try {
            if (entity == null) {
                throw new ZMessManager().new NullEntityExcepcion(
                    "HistoriaDeUsuario");
            }

            if (entity.getEstadoHistoriaUsuario() == null) {
                throw new ZMessManager().new ForeignException(
                    "estadoHistoriaUsuario");
            }

            if (entity.getRequerimiento() == null) {
                throw new ZMessManager().new ForeignException("requerimiento");
            }

            if (entity.getUsuario() == null) {
                throw new ZMessManager().new ForeignException("usuario");
            }

            if (entity.getDetalleHistoria() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "detalleHistoria");
            }

            if ((entity.getDetalleHistoria() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getDetalleHistoria(), 2000) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "detalleHistoria");
            }

            if (entity.getHiusId() == null) {
                throw new ZMessManager().new EmptyFieldException("hiusId");
            }

            if (entity.getTituloHistoria() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "tituloHistoria");
            }

            if ((entity.getTituloHistoria() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getTituloHistoria(), 150) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "tituloHistoria");
            }

            if (entity.getEstadoHistoriaUsuario().getEshiId() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "eshiId_EstadoHistoriaUsuario");
            }

            if (entity.getRequerimiento().getRequId() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "requId_Requerimiento");
            }

            if (entity.getUsuario().getUsuaId() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "usuaId_Usuario");
            }

            historiaDeUsuarioDAO.update(entity);

            log.debug("update HistoriaDeUsuario successful");
        } catch (Exception e) {
            log.error("update HistoriaDeUsuario failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = true)
    public List<HistoriaDeUsuarioDTO> getDataHistoriaDeUsuario()
        throws Exception {
        try {
            List<HistoriaDeUsuario> historiaDeUsuario = historiaDeUsuarioDAO.findAll();

            List<HistoriaDeUsuarioDTO> historiaDeUsuarioDTO = new ArrayList<HistoriaDeUsuarioDTO>();

            for (HistoriaDeUsuario historiaDeUsuarioTmp : historiaDeUsuario) {
                HistoriaDeUsuarioDTO historiaDeUsuarioDTO2 = new HistoriaDeUsuarioDTO();

                historiaDeUsuarioDTO2.setHiusId(historiaDeUsuarioTmp.getHiusId());
                historiaDeUsuarioDTO2.setDetalleHistoria((historiaDeUsuarioTmp.getDetalleHistoria() != null)
                    ? historiaDeUsuarioTmp.getDetalleHistoria() : null);
                historiaDeUsuarioDTO2.setTituloHistoria((historiaDeUsuarioTmp.getTituloHistoria() != null)
                    ? historiaDeUsuarioTmp.getTituloHistoria() : null);
                historiaDeUsuarioDTO2.setEshiId_EstadoHistoriaUsuario((historiaDeUsuarioTmp.getEstadoHistoriaUsuario()
                                                                                           .getEshiId() != null)
                    ? historiaDeUsuarioTmp.getEstadoHistoriaUsuario().getEshiId()
                    : null);
                historiaDeUsuarioDTO2.setRequId_Requerimiento((historiaDeUsuarioTmp.getRequerimiento()
                                                                                   .getRequId() != null)
                    ? historiaDeUsuarioTmp.getRequerimiento().getRequId() : null);
                historiaDeUsuarioDTO2.setUsuaId_Usuario((historiaDeUsuarioTmp.getUsuario()
                                                                             .getUsuaId() != null)
                    ? historiaDeUsuarioTmp.getUsuario().getUsuaId() : null);
                historiaDeUsuarioDTO.add(historiaDeUsuarioDTO2);
            }

            return historiaDeUsuarioDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public HistoriaDeUsuario getHistoriaDeUsuario(Integer hiusId)
        throws Exception {
        log.debug("getting HistoriaDeUsuario instance");

        HistoriaDeUsuario entity = null;

        try {
            entity = historiaDeUsuarioDAO.findById(hiusId);
        } catch (Exception e) {
            log.error("get HistoriaDeUsuario failed", e);
            throw new ZMessManager().new FindingException("HistoriaDeUsuario");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public List<HistoriaDeUsuario> findPageHistoriaDeUsuario(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception {
        List<HistoriaDeUsuario> entity = null;

        try {
            entity = historiaDeUsuarioDAO.findPage(sortColumnName,
                    sortAscending, startRow, maxResults);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException(
                "HistoriaDeUsuario Count");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public Long findTotalNumberHistoriaDeUsuario() throws Exception {
        Long entity = null;

        try {
            entity = historiaDeUsuarioDAO.count();
        } catch (Exception e) {
            throw new ZMessManager().new FindingException(
                "HistoriaDeUsuario Count");
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
    public List<HistoriaDeUsuario> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        List<HistoriaDeUsuario> list = new ArrayList<HistoriaDeUsuario>();
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
            list = historiaDeUsuarioDAO.findByCriteria(where);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
        }

        return list;
    }

	/**
	 * @author Daniel Pareja Londoño
	 * @version may. 28, 2019
	 *
	 * @see com.dsdsoft.sgp.modelo.control.IHistoriaDeUsuarioLogic#consultarHistoriasUsuarioPorFiltros(java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 *
	 */
	@Override
	@Transactional(readOnly = true)
	public List<HistoriaDeUsuarioDTO> consultarHistoriasUsuarioPorFiltros(Integer usuaId, Integer proyId,
			Integer requId, Integer eshiId) throws Exception {
		try {
			proyId = proyId==null?-1:proyId;
			requId = requId==null?-1:requId;
			eshiId = eshiId==null?-1:eshiId;
			
			return historiaDeUsuarioDAO.consultarHistoriasUsuarioPorFiltros(usuaId, proyId, requId, eshiId);
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}
}
