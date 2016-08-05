package com.dsdsoft.sgp.modelo.control;

import com.dsdsoft.sgp.dataaccess.dao.*;
import com.dsdsoft.sgp.exceptions.*;
import com.dsdsoft.sgp.modelo.*;
import com.dsdsoft.sgp.modelo.dto.UsuarioDTO;
import com.dsdsoft.sgp.utilities.Utilities;

import org.apache.commons.validator.EmailValidator;
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
@Service("UsuarioLogic")
public class UsuarioLogic implements IUsuarioLogic {
    private static final Logger log = LoggerFactory.getLogger(UsuarioLogic.class);
    
    
    
    /**
     * DAO injected by Spring that manages Usuario entities
     *
     */
    @Autowired
    private IUsuarioDAO usuarioDAO;

    /**
    * DAO injected by Spring that manages Actividad entities
    *
    */
    @Autowired
    private IActividadDAO actividadDAO;

    /**
    * DAO injected by Spring that manages CasoSoporte entities
    *
    */
    @Autowired
    private ICasoSoporteDAO casoSoporteDAO;

    /**
    * DAO injected by Spring that manages HistoriaDeUsuario entities
    *
    */
    @Autowired
    private IHistoriaDeUsuarioDAO historiaDeUsuarioDAO;

    /**
    * DAO injected by Spring that manages ProyectoUsuarioRol entities
    *
    */
    @Autowired
    private IProyectoUsuarioRolDAO proyectoUsuarioRolDAO;

    /**
    * DAO injected by Spring that manages SeguimientoCaso entities
    *
    */
    @Autowired
    private ISeguimientoCasoDAO seguimientoCasoDAO;
    
    @Transactional(readOnly = true)
    public List<Usuario> getUsuario() throws Exception {
        log.debug("finding all Usuario instances");

        List<Usuario> list = new ArrayList<Usuario>();

        try {
            list = usuarioDAO.findAll();
        } catch (Exception e) {
            log.error("finding all Usuario failed", e);
            throw new ZMessManager().new GettingException(ZMessManager.ALL +
                "Usuario");
        } finally {
        }

        return list;
    }

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void saveUsuario(Usuario entity) throws Exception {
        log.debug("saving Usuario instance");

        try {
            if (entity.getApellidoUsuario() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "Apellidos: *");
            }
            
            if(entity.getApellidoUsuario().trim().equals("")){
            	throw new ZMessManager().new EmptyFieldException(
                        "Apellidos: *");
            }
            

            if ((entity.getApellidoUsuario() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getApellidoUsuario(), 150) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "Apellidos: *");
            }

            if (entity.getEmailUsuario() == null) {
                throw new ZMessManager().new EmptyFieldException("Correo electrónico: *");
            }

            if ((entity.getEmailUsuario() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getEmailUsuario(), 150) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "Correo electrónico: *");
            }
            
            if(!EmailValidator.getInstance().isValid(entity.getEmailUsuario().trim())){
            	 throw new Exception("Correo electrónico no válido "+entity.getEmailUsuario());
            }

            if (entity.getEstadoRegistroUsuario() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "estadoRegistroUsuario");
            }

            if ((entity.getEstadoRegistroUsuario() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getEstadoRegistroUsuario(), 1) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "estadoRegistroUsuario");
            }

            if (entity.getNombreUsuario() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "Nombres: *");
            }
            
            if(entity.getNombreUsuario().trim().equals("")){
            	throw new ZMessManager().new EmptyFieldException(
                        "Nombres: *");
            }

            if ((entity.getNombreUsuario() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getNombreUsuario(), 150) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "Nombres: *");
            }

            if (entity.getNudocUsuario() == null) {
                throw new ZMessManager().new EmptyFieldException("Número de documento: *");
            }
            
            if(entity.getNudocUsuario().trim().equals("")){
            	throw new ZMessManager().new EmptyFieldException("Número de documento: *");
            }

            if ((entity.getNudocUsuario() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getNudocUsuario(), 15) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "Número de documento: *");
            }

            if (entity.getPasswordUsuario() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "Password: *");
            }

            if(entity.getPasswordUsuario().trim().equals("")){
                throw new ZMessManager().new EmptyFieldException(
                        "Password: *");
            }
            
            if ((entity.getPasswordUsuario() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getPasswordUsuario(), 20) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "Password: *");
            }
            
            Usuario usuario = usuarioDAO.findEntityByProperty("nudocUsuario", entity.getNudocUsuario());
            if(usuario!=null){
            	throw new Exception("Ya existe un usuario con el número de documento: "+usuario.getNudocUsuario());
            }
            
            usuario = usuarioDAO.findEntityByProperty("emailUsuario", entity.getEmailUsuario());
            if(usuario!=null){
            	throw new Exception("Ya hay un usuario registrado con el correo electrónico: "+usuario.getEmailUsuario());
            }

            usuarioDAO.save(entity);

            log.info("Usuario: "+entity.getEmailUsuario()+" guardado correctamente a las: "+new Date());
        } catch (Exception e) {
            log.error("Error guardando un usuario: ", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deleteUsuario(Usuario entity) throws Exception {
        log.debug("deleting Usuario instance");

        if (entity == null) {
            throw new ZMessManager().new NullEntityExcepcion("Usuario");
        }

        if (entity.getUsuaId() == null) {
            throw new ZMessManager().new EmptyFieldException("usuaId");
        }

        List<Actividad> actividads = null;
        List<CasoSoporte> casoSoportes = null;
        List<HistoriaDeUsuario> historiaDeUsuarios = null;
        List<ProyectoUsuarioRol> proyectoUsuarioRols = null;
        List<SeguimientoCaso> seguimientoCasos = null;

        try {
            actividads = actividadDAO.findByProperty("usuario.usuaId",
                    entity.getUsuaId());

            if (Utilities.validationsList(actividads) == true) {
                throw new ZMessManager().new DeletingException("actividads");
            }

            casoSoportes = casoSoporteDAO.findByProperty("usuario.usuaId",
                    entity.getUsuaId());

            if (Utilities.validationsList(casoSoportes) == true) {
                throw new ZMessManager().new DeletingException("casoSoportes");
            }

            historiaDeUsuarios = historiaDeUsuarioDAO.findByProperty("usuario.usuaId",
                    entity.getUsuaId());

            if (Utilities.validationsList(historiaDeUsuarios) == true) {
                throw new ZMessManager().new DeletingException(
                    "historiaDeUsuarios");
            }

            proyectoUsuarioRols = proyectoUsuarioRolDAO.findByProperty("usuario.usuaId",
                    entity.getUsuaId());

            if (Utilities.validationsList(proyectoUsuarioRols) == true) {
                throw new ZMessManager().new DeletingException(
                    "proyectoUsuarioRols");
            }

            seguimientoCasos = seguimientoCasoDAO.findByProperty("usuario.usuaId",
                    entity.getUsuaId());

            if (Utilities.validationsList(seguimientoCasos) == true) {
                throw new ZMessManager().new DeletingException(
                    "seguimientoCasos");
            }

            usuarioDAO.delete(entity);

            log.debug("delete Usuario successful");
        } catch (Exception e) {
            log.error("delete Usuario failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void updateUsuario(Usuario entity) throws Exception {
        log.debug("updating Usuario instance");

        try {
            if (entity == null) {
                throw new ZMessManager().new NullEntityExcepcion("Usuario");
            }

            if (entity.getApellidoUsuario() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "apellidoUsuario");
            }

            if ((entity.getApellidoUsuario() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getApellidoUsuario(), 150) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "apellidoUsuario");
            }

            if (entity.getEmailUsuario() == null) {
                throw new ZMessManager().new EmptyFieldException("emailUsuario");
            }

            if ((entity.getEmailUsuario() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getEmailUsuario(), 150) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "emailUsuario");
            }

            if (entity.getEstadoRegistroUsuario() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "estadoRegistroUsuario");
            }

            if ((entity.getEstadoRegistroUsuario() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getEstadoRegistroUsuario(), 1) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "estadoRegistroUsuario");
            }

            if (entity.getNombreUsuario() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "nombreUsuario");
            }

            if ((entity.getNombreUsuario() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getNombreUsuario(), 150) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "nombreUsuario");
            }

            if (entity.getNudocUsuario() == null) {
                throw new ZMessManager().new EmptyFieldException("nudocUsuario");
            }

            if ((entity.getNudocUsuario() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getNudocUsuario(), 15) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "nudocUsuario");
            }

            if (entity.getPasswordUsuario() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "passwordUsuario");
            }

            if ((entity.getPasswordUsuario() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getPasswordUsuario(), 20) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "passwordUsuario");
            }

            if (entity.getUsuaId() == null) {
                throw new ZMessManager().new EmptyFieldException("usuaId");
            }

            usuarioDAO.update(entity);

            log.debug("update Usuario successful");
        } catch (Exception e) {
            log.error("update Usuario failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = true)
    public List<UsuarioDTO> getDataUsuario() throws Exception {
        try {
            List<Usuario> usuario = usuarioDAO.findAll();

            List<UsuarioDTO> usuarioDTO = new ArrayList<UsuarioDTO>();

            for (Usuario usuarioTmp : usuario) {
                UsuarioDTO usuarioDTO2 = new UsuarioDTO();

                usuarioDTO2.setUsuaId(usuarioTmp.getUsuaId());
                usuarioDTO2.setApellidoUsuario((usuarioTmp.getApellidoUsuario() != null)
                    ? usuarioTmp.getApellidoUsuario() : null);
                usuarioDTO2.setEmailUsuario((usuarioTmp.getEmailUsuario() != null)
                    ? usuarioTmp.getEmailUsuario() : null);
                usuarioDTO2.setEstadoRegistroUsuario((usuarioTmp.getEstadoRegistroUsuario() != null)
                    ? usuarioTmp.getEstadoRegistroUsuario() : null);
                usuarioDTO2.setNombreUsuario((usuarioTmp.getNombreUsuario() != null)
                    ? usuarioTmp.getNombreUsuario() : null);
                usuarioDTO2.setNudocUsuario((usuarioTmp.getNudocUsuario() != null)
                    ? usuarioTmp.getNudocUsuario() : null);
                usuarioDTO2.setPasswordUsuario((usuarioTmp.getPasswordUsuario() != null)
                    ? usuarioTmp.getPasswordUsuario() : null);
                usuarioDTO.add(usuarioDTO2);
            }

            return usuarioDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public Usuario getUsuario(Integer usuaId) throws Exception {
        log.debug("getting Usuario instance");

        Usuario entity = null;

        try {
            entity = usuarioDAO.findById(usuaId);
        } catch (Exception e) {
            log.error("get Usuario failed", e);
            throw new ZMessManager().new FindingException("Usuario");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public List<Usuario> findPageUsuario(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        List<Usuario> entity = null;

        try {
            entity = usuarioDAO.findPage(sortColumnName, sortAscending,
                    startRow, maxResults);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("Usuario Count");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public Long findTotalNumberUsuario() throws Exception {
        Long entity = null;

        try {
            entity = usuarioDAO.count();
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("Usuario Count");
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
    public List<Usuario> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        List<Usuario> list = new ArrayList<Usuario>();
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
            list = usuarioDAO.findByCriteria(where);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
        }

        return list;
    }
}
