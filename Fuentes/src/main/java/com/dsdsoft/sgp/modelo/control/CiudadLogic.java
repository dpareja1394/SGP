package com.dsdsoft.sgp.modelo.control;

import com.dsdsoft.sgp.dataaccess.dao.*;
import com.dsdsoft.sgp.exceptions.*;
import com.dsdsoft.sgp.modelo.*;
import com.dsdsoft.sgp.modelo.dto.CiudadDTO;
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
* @author Zathura Code Generator http://zathuracode.org/
* www.zathuracode.org
*
*/
@Scope("singleton")
@Service("CiudadLogic")
public class CiudadLogic implements ICiudadLogic {
    private static final Logger log = LoggerFactory.getLogger(CiudadLogic.class);

    /**
     * DAO injected by Spring that manages Ciudad entities
     *
     */
    @Autowired
    private ICiudadDAO ciudadDAO;

    /**
    * DAO injected by Spring that manages Cliente entities
    *
    */
    @Autowired
    private IClienteDAO clienteDAO;

    /**
    * Logic injected by Spring that manages Departamento entities
    *
    */
    @Autowired
    IDepartamentoLogic logicDepartamento1;

    @Transactional(readOnly = true)
    public List<Ciudad> getCiudad() throws Exception {
        log.debug("finding all Ciudad instances");

        List<Ciudad> list = new ArrayList<Ciudad>();

        try {
            list = ciudadDAO.findAll();
        } catch (Exception e) {
            log.error("finding all Ciudad failed", e);
            throw new ZMessManager().new GettingException(ZMessManager.ALL +
                "Ciudad");
        } finally {
        }

        return list;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void saveCiudad(Ciudad entity) throws Exception {
        log.debug("saving Ciudad instance");

        try {
            if (entity.getDepartamento() == null) {
                throw new ZMessManager().new ForeignException("departamento");
            }

            if (entity.getCiudId() == null) {
                throw new ZMessManager().new EmptyFieldException("ciudId");
            }

            if (entity.getNombreCiudad() == null) {
                throw new ZMessManager().new EmptyFieldException("nombreCiudad");
            }

            if ((entity.getNombreCiudad() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getNombreCiudad(), 200) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "nombreCiudad");
            }

            if (entity.getDepartamento().getDepaId() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "depaId_Departamento");
            }

            if (getCiudad(entity.getCiudId()) != null) {
                throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
            }

            ciudadDAO.save(entity);

            log.debug("save Ciudad successful");
        } catch (Exception e) {
            log.error("save Ciudad failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deleteCiudad(Ciudad entity) throws Exception {
        log.debug("deleting Ciudad instance");

        if (entity == null) {
            throw new ZMessManager().new NullEntityExcepcion("Ciudad");
        }

        if (entity.getCiudId() == null) {
            throw new ZMessManager().new EmptyFieldException("ciudId");
        }

        List<Cliente> clientes = null;

        try {
            clientes = clienteDAO.findByProperty("ciudad.ciudId",
                    entity.getCiudId());

            if (Utilities.validationsList(clientes) == true) {
                throw new ZMessManager().new DeletingException("clientes");
            }

            ciudadDAO.delete(entity);

            log.debug("delete Ciudad successful");
        } catch (Exception e) {
            log.error("delete Ciudad failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void updateCiudad(Ciudad entity) throws Exception {
        log.debug("updating Ciudad instance");

        try {
            if (entity == null) {
                throw new ZMessManager().new NullEntityExcepcion("Ciudad");
            }

            if (entity.getDepartamento() == null) {
                throw new ZMessManager().new ForeignException("departamento");
            }

            if (entity.getCiudId() == null) {
                throw new ZMessManager().new EmptyFieldException("ciudId");
            }

            if (entity.getNombreCiudad() == null) {
                throw new ZMessManager().new EmptyFieldException("nombreCiudad");
            }

            if ((entity.getNombreCiudad() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getNombreCiudad(), 200) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "nombreCiudad");
            }

            if (entity.getDepartamento().getDepaId() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "depaId_Departamento");
            }

            ciudadDAO.update(entity);

            log.debug("update Ciudad successful");
        } catch (Exception e) {
            log.error("update Ciudad failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = true)
    public List<CiudadDTO> getDataCiudad() throws Exception {
        try {
            List<Ciudad> ciudad = ciudadDAO.findAll();

            List<CiudadDTO> ciudadDTO = new ArrayList<CiudadDTO>();

            for (Ciudad ciudadTmp : ciudad) {
                CiudadDTO ciudadDTO2 = new CiudadDTO();

                ciudadDTO2.setCiudId(ciudadTmp.getCiudId());
                ciudadDTO2.setNombreCiudad((ciudadTmp.getNombreCiudad() != null)
                    ? ciudadTmp.getNombreCiudad() : null);
                ciudadDTO2.setDepaId_Departamento((ciudadTmp.getDepartamento()
                                                            .getDepaId() != null)
                    ? ciudadTmp.getDepartamento().getDepaId() : null);
                ciudadDTO.add(ciudadDTO2);
            }

            return ciudadDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public Ciudad getCiudad(Integer ciudId) throws Exception {
        log.debug("getting Ciudad instance");

        Ciudad entity = null;

        try {
            entity = ciudadDAO.findById(ciudId);
        } catch (Exception e) {
            log.error("get Ciudad failed", e);
            throw new ZMessManager().new FindingException("Ciudad");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public List<Ciudad> findPageCiudad(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        List<Ciudad> entity = null;

        try {
            entity = ciudadDAO.findPage(sortColumnName, sortAscending,
                    startRow, maxResults);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("Ciudad Count");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public Long findTotalNumberCiudad() throws Exception {
        Long entity = null;

        try {
            entity = ciudadDAO.count();
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("Ciudad Count");
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
    public List<Ciudad> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        List<Ciudad> list = new ArrayList<Ciudad>();
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
            list = ciudadDAO.findByCriteria(where);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
        }

        return list;
    }

	@Override
	@Transactional(readOnly = true)
	public List<Ciudad> buscarCiudadPorDepartamento(Integer depaId) throws Exception {
		List<Ciudad> listaCiudadPorDepartamento = null;
		try {
			if(depaId == null || depaId.equals(0)){
				throw new Exception("No ha seleccionado un Departamento para hacer la búsqueda de la Ciudad");
			}
			listaCiudadPorDepartamento = ciudadDAO.buscarCiudadPorDepartamento(depaId);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
		return listaCiudadPorDepartamento;
	}
}
