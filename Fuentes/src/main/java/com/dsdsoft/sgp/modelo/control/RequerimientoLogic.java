package com.dsdsoft.sgp.modelo.control;

import com.dsdsoft.sgp.dataaccess.dao.*;
import com.dsdsoft.sgp.exceptions.*;
import com.dsdsoft.sgp.modelo.*;
import com.dsdsoft.sgp.modelo.dto.RequerimientoDTO;
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
@Service("RequerimientoLogic")
public class RequerimientoLogic implements IRequerimientoLogic {
    private static final Logger log = LoggerFactory.getLogger(RequerimientoLogic.class);

    /**
     * DAO injected by Spring that manages Requerimiento entities
     *
     */
    @Autowired
    private IRequerimientoDAO requerimientoDAO;

    /**
    * DAO injected by Spring that manages HistoriaDeUsuario entities
    *
    */
    @Autowired
    private IHistoriaDeUsuarioDAO historiaDeUsuarioDAO;

    /**
    * Logic injected by Spring that manages Proyecto entities
    *
    */
    @Autowired
    IProyectoLogic logicProyecto1;

    @Transactional(readOnly = true)
    public List<Requerimiento> getRequerimiento() throws Exception {
        log.debug("finding all Requerimiento instances");

        List<Requerimiento> list = new ArrayList<Requerimiento>();

        try {
            list = requerimientoDAO.findAll();
        } catch (Exception e) {
            log.error("finding all Requerimiento failed", e);
            throw new ZMessManager().new GettingException(ZMessManager.ALL +
                "Requerimiento");
        } finally {
        }

        return list;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void saveRequerimiento(Requerimiento entity)
        throws Exception {
        log.debug("saving Requerimiento instance");

        try {
            if (entity.getProyecto() == null) {
                throw new ZMessManager().new ForeignException("proyecto");
            }

            if (entity.getDescripcionRequerimiento() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "descripcionRequerimiento");
            }

            if ((entity.getDescripcionRequerimiento() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getDescripcionRequerimiento(), 2000) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "descripcionRequerimiento");
            }

            if (entity.getNombreRequerimiento() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "nombreRequerimiento");
            }

            if ((entity.getNombreRequerimiento() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getNombreRequerimiento(), 200) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "nombreRequerimiento");
            }

            if (entity.getProyecto().getProyId() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "proyId_Proyecto");
            }

            requerimientoDAO.save(entity);

            log.debug("save Requerimiento successful");
        } catch (Exception e) {
            log.error("save Requerimiento failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deleteRequerimiento(Requerimiento entity)
        throws Exception {
        log.debug("deleting Requerimiento instance");

        if (entity == null) {
            throw new ZMessManager().new NullEntityExcepcion("Requerimiento");
        }

        if (entity.getRequId() == null) {
            throw new ZMessManager().new EmptyFieldException("requId");
        }

        List<HistoriaDeUsuario> historiaDeUsuarios = null;

        try {
            historiaDeUsuarios = historiaDeUsuarioDAO.findByProperty("requerimiento.requId",
                    entity.getRequId());

            if (Utilities.validationsList(historiaDeUsuarios) == true) {
                throw new ZMessManager().new DeletingException(
                    "historiaDeUsuarios");
            }

            requerimientoDAO.delete(entity);

            log.debug("delete Requerimiento successful");
        } catch (Exception e) {
            log.error("delete Requerimiento failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void updateRequerimiento(Requerimiento entity)
        throws Exception {
        log.debug("updating Requerimiento instance");

        try {
            if (entity == null) {
                throw new ZMessManager().new NullEntityExcepcion(
                    "Requerimiento");
            }

            if (entity.getProyecto() == null) {
                throw new ZMessManager().new ForeignException("proyecto");
            }

            if (entity.getDescripcionRequerimiento() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "descripcionRequerimiento");
            }

            if ((entity.getDescripcionRequerimiento() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getDescripcionRequerimiento(), 2000) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "descripcionRequerimiento");
            }

            if (entity.getNombreRequerimiento() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "nombreRequerimiento");
            }

            if ((entity.getNombreRequerimiento() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getNombreRequerimiento(), 200) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "nombreRequerimiento");
            }

            if (entity.getRequId() == null) {
                throw new ZMessManager().new EmptyFieldException("requId");
            }

            if (entity.getProyecto().getProyId() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "proyId_Proyecto");
            }

            requerimientoDAO.update(entity);

            log.debug("update Requerimiento successful");
        } catch (Exception e) {
            log.error("update Requerimiento failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = true)
    public List<RequerimientoDTO> getDataRequerimiento()
        throws Exception {
        try {
            List<Requerimiento> requerimiento = requerimientoDAO.findAll();

            List<RequerimientoDTO> requerimientoDTO = new ArrayList<RequerimientoDTO>();

            for (Requerimiento requerimientoTmp : requerimiento) {
                RequerimientoDTO requerimientoDTO2 = new RequerimientoDTO();

                requerimientoDTO2.setRequId(requerimientoTmp.getRequId());
                requerimientoDTO2.setDescripcionRequerimiento((requerimientoTmp.getDescripcionRequerimiento() != null)
                    ? requerimientoTmp.getDescripcionRequerimiento() : null);
                requerimientoDTO2.setNombreRequerimiento((requerimientoTmp.getNombreRequerimiento() != null)
                    ? requerimientoTmp.getNombreRequerimiento() : null);
                requerimientoDTO2.setProyId_Proyecto((requerimientoTmp.getProyecto()
                                                                      .getProyId() != null)
                    ? requerimientoTmp.getProyecto().getProyId() : null);
                requerimientoDTO.add(requerimientoDTO2);
            }

            return requerimientoDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public Requerimiento getRequerimiento(Integer requId)
        throws Exception {
        log.debug("getting Requerimiento instance");

        Requerimiento entity = null;

        try {
            entity = requerimientoDAO.findById(requId);
        } catch (Exception e) {
            log.error("get Requerimiento failed", e);
            throw new ZMessManager().new FindingException("Requerimiento");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public List<Requerimiento> findPageRequerimiento(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        List<Requerimiento> entity = null;

        try {
            entity = requerimientoDAO.findPage(sortColumnName, sortAscending,
                    startRow, maxResults);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("Requerimiento Count");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public Long findTotalNumberRequerimiento() throws Exception {
        Long entity = null;

        try {
            entity = requerimientoDAO.count();
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("Requerimiento Count");
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
    public List<Requerimiento> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        List<Requerimiento> list = new ArrayList<Requerimiento>();
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
            list = requerimientoDAO.findByCriteria(where);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
        }

        return list;
    }
}
