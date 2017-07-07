package com.dsdsoft.sgp.presentation.businessDelegate;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.dsdsoft.sgp.modelo.Actividad;
import com.dsdsoft.sgp.modelo.CasoSoporte;
import com.dsdsoft.sgp.modelo.Ciudad;
import com.dsdsoft.sgp.modelo.Cliente;
import com.dsdsoft.sgp.modelo.Departamento;
import com.dsdsoft.sgp.modelo.EstadoHistoriaUsuario;
import com.dsdsoft.sgp.modelo.EstadoProyecto;
import com.dsdsoft.sgp.modelo.HistoriaDeUsuario;
import com.dsdsoft.sgp.modelo.Pais;
import com.dsdsoft.sgp.modelo.Parametro;
import com.dsdsoft.sgp.modelo.Proyecto;
import com.dsdsoft.sgp.modelo.ProyectoUsuarioRol;
import com.dsdsoft.sgp.modelo.ProyectoUsuarioRolId;
import com.dsdsoft.sgp.modelo.Requerimiento;
import com.dsdsoft.sgp.modelo.Rol;
import com.dsdsoft.sgp.modelo.SeguimientoCaso;
import com.dsdsoft.sgp.modelo.TipoActividad;
import com.dsdsoft.sgp.modelo.Usuario;
import com.dsdsoft.sgp.modelo.control.IActividadLogic;
import com.dsdsoft.sgp.modelo.control.ICasoSoporteLogic;
import com.dsdsoft.sgp.modelo.control.ICiudadLogic;
import com.dsdsoft.sgp.modelo.control.IClienteLogic;
import com.dsdsoft.sgp.modelo.control.IDepartamentoLogic;
import com.dsdsoft.sgp.modelo.control.IEstadoHistoriaUsuarioLogic;
import com.dsdsoft.sgp.modelo.control.IEstadoProyectoLogic;
import com.dsdsoft.sgp.modelo.control.IHistoriaDeUsuarioLogic;
import com.dsdsoft.sgp.modelo.control.IPaisLogic;
import com.dsdsoft.sgp.modelo.control.IParametroLogic;
import com.dsdsoft.sgp.modelo.control.IProyectoLogic;
import com.dsdsoft.sgp.modelo.control.IProyectoUsuarioRolLogic;
import com.dsdsoft.sgp.modelo.control.IRequerimientoLogic;
import com.dsdsoft.sgp.modelo.control.IRolLogic;
import com.dsdsoft.sgp.modelo.control.ISeguimientoCasoLogic;
import com.dsdsoft.sgp.modelo.control.ITipoActividadLogic;
import com.dsdsoft.sgp.modelo.control.IUsuarioLogic;
import com.dsdsoft.sgp.modelo.dto.ActividadDTO;
import com.dsdsoft.sgp.modelo.dto.CasoSoporteDTO;
import com.dsdsoft.sgp.modelo.dto.ClienteDTO;
import com.dsdsoft.sgp.modelo.dto.EstadoHistoriaUsuarioDTO;
import com.dsdsoft.sgp.modelo.dto.EstadoProyectoDTO;
import com.dsdsoft.sgp.modelo.dto.HistoriaDeUsuarioDTO;
import com.dsdsoft.sgp.modelo.dto.ParametroDTO;
import com.dsdsoft.sgp.modelo.dto.ProyectoDTO;
import com.dsdsoft.sgp.modelo.dto.ProyectoUsuarioRolDTO;
import com.dsdsoft.sgp.modelo.dto.RequerimientoDTO;
import com.dsdsoft.sgp.modelo.dto.RolDTO;
import com.dsdsoft.sgp.modelo.dto.SeguimientoCasoDTO;
import com.dsdsoft.sgp.modelo.dto.TipoActividadDTO;
import com.dsdsoft.sgp.modelo.dto.UsuarioDTO;


/**
* Use a Business Delegate to reduce coupling between presentation-tier clients and business services.
* The Business Delegate hides the underlying implementation details of the business service, such as lookup and access details of the EJB architecture.
*
* The Business Delegate acts as a client-side business abstraction; it provides an abstraction for, and thus hides,
* the implementation of the business services. Using a Business Delegate reduces the coupling between presentation-tier clients and
* the system's business services. Depending on the implementation strategy, the Business Delegate may shield clients from possible
* volatility in the implementation of the business service API. Potentially, this reduces the number of changes that must be made to the
* presentation-tier client code when the business service API or its underlying implementation changes.
*
* However, interface methods in the Business Delegate may still require modification if the underlying business service API changes.
* Admittedly, though, it is more likely that changes will be made to the business service rather than to the Business Delegate.
*
* Often, developers are skeptical when a design goal such as abstracting the business layer causes additional upfront work in return
* for future gains. However, using this pattern or its strategies results in only a small amount of additional upfront work and provides
* considerable benefits. The main benefit is hiding the details of the underlying service. For example, the client can become transparent
* to naming and lookup services. The Business Delegate also handles the exceptions from the business services, such as java.rmi.Remote
* exceptions, Java Messages Service (JMS) exceptions and so on. The Business Delegate may intercept such service level exceptions and
* generate application level exceptions instead. Application level exceptions are easier to handle by the clients, and may be user friendly.
* The Business Delegate may also transparently perform any retry or recovery operations necessary in the event of a service failure without
* exposing the client to the problem until it is determined that the problem is not resolvable. These gains present a compelling reason to
* use the pattern.
*
* Another benefit is that the delegate may cache results and references to remote business services. Caching can significantly improve performance,
* because it limits unnecessary and potentially costly round trips over the network.
*
* A Business Delegate uses a component called the Lookup Service. The Lookup Service is responsible for hiding the underlying implementation
* details of the business service lookup code. The Lookup Service may be written as part of the Delegate, but we recommend that it be
* implemented as a separate component, as outlined in the Service Locator pattern (See "Service Locator" on page 368.)
*
* When the Business Delegate is used with a Session Facade, typically there is a one-to-one relationship between the two.
* This one-to-one relationship exists because logic that might have been encapsulated in a Business Delegate relating to its interaction
* with multiple business services (creating a one-to-many relationship) will often be factored back into a Session Facade.
*
* Finally, it should be noted that this pattern could be used to reduce coupling between other tiers, not simply the presentation and the
* business tiers.
*
* @author Zathura Code Generator http://code.google.com/p/zathura
* www.zathuracode.org
*
*/
@Scope("singleton")
@Service("BusinessDelegatorView")
public class BusinessDelegatorView implements IBusinessDelegatorView {
    private static final Logger log = LoggerFactory.getLogger(BusinessDelegatorView.class);
    @Autowired
    private IActividadLogic actividadLogic;
    @Autowired
    private ICasoSoporteLogic casoSoporteLogic;
    @Autowired
    private IClienteLogic clienteLogic;
    @Autowired
    private IEstadoHistoriaUsuarioLogic estadoHistoriaUsuarioLogic;
    @Autowired
    private IEstadoProyectoLogic estadoProyectoLogic;
    @Autowired
    private IHistoriaDeUsuarioLogic historiaDeUsuarioLogic;
    @Autowired
    private IParametroLogic parametroLogic;
    @Autowired
    private IProyectoLogic proyectoLogic;
    @Autowired
    private IProyectoUsuarioRolLogic proyectoUsuarioRolLogic;
    @Autowired
    private IRequerimientoLogic requerimientoLogic;
    @Autowired
    private IRolLogic rolLogic;
    @Autowired
    private ISeguimientoCasoLogic seguimientoCasoLogic;
    @Autowired
    private ITipoActividadLogic tipoActividadLogic;
    @Autowired
    private IUsuarioLogic usuarioLogic;
    @Autowired
    private IPaisLogic paisLogic;
    @Autowired
    private IDepartamentoLogic departamentoLogic;
    @Autowired
    private ICiudadLogic ciudadLogic;
    

    public List<Actividad> getActividad() throws Exception {
        return actividadLogic.getActividad();
    }

    public void saveActividad(Actividad entity) throws Exception {
        actividadLogic.saveActividad(entity);
    }

    public void deleteActividad(Actividad entity) throws Exception {
        actividadLogic.deleteActividad(entity);
    }

    public void updateActividad(Actividad entity) throws Exception {
        actividadLogic.updateActividad(entity);
    }

    public Actividad getActividad(Integer actiId) throws Exception {
        Actividad actividad = null;

        try {
            actividad = actividadLogic.getActividad(actiId);
        } catch (Exception e) {
            throw e;
        }

        return actividad;
    }

    public List<Actividad> findByCriteriaInActividad(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return actividadLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<Actividad> findPageActividad(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return actividadLogic.findPageActividad(sortColumnName, sortAscending,
            startRow, maxResults);
    }

    public Long findTotalNumberActividad() throws Exception {
        return actividadLogic.findTotalNumberActividad();
    }

    public List<ActividadDTO> getDataActividad() throws Exception {
        return actividadLogic.getDataActividad();
    }

    public List<CasoSoporte> getCasoSoporte() throws Exception {
        return casoSoporteLogic.getCasoSoporte();
    }

    public void saveCasoSoporte(CasoSoporte entity) throws Exception {
        casoSoporteLogic.saveCasoSoporte(entity);
    }

    public void deleteCasoSoporte(CasoSoporte entity) throws Exception {
        casoSoporteLogic.deleteCasoSoporte(entity);
    }

    public void updateCasoSoporte(CasoSoporte entity) throws Exception {
        casoSoporteLogic.updateCasoSoporte(entity);
    }

    public CasoSoporte getCasoSoporte(Integer casoId) throws Exception {
        CasoSoporte casoSoporte = null;

        try {
            casoSoporte = casoSoporteLogic.getCasoSoporte(casoId);
        } catch (Exception e) {
            throw e;
        }

        return casoSoporte;
    }

    public List<CasoSoporte> findByCriteriaInCasoSoporte(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return casoSoporteLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<CasoSoporte> findPageCasoSoporte(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return casoSoporteLogic.findPageCasoSoporte(sortColumnName,
            sortAscending, startRow, maxResults);
    }

    public Long findTotalNumberCasoSoporte() throws Exception {
        return casoSoporteLogic.findTotalNumberCasoSoporte();
    }

    public List<CasoSoporteDTO> getDataCasoSoporte() throws Exception {
        return casoSoporteLogic.getDataCasoSoporte();
    }

    public List<Cliente> getCliente() throws Exception {
        return clienteLogic.getCliente();
    }

    public void saveCliente(Cliente entity) throws Exception {
        clienteLogic.saveCliente(entity);
    }

    public void deleteCliente(Cliente entity) throws Exception {
        clienteLogic.deleteCliente(entity);
    }

    public void updateCliente(Cliente entity) throws Exception {
        clienteLogic.updateCliente(entity);
    }

    public Cliente getCliente(Integer clieId) throws Exception {
        Cliente cliente = null;

        try {
            cliente = clienteLogic.getCliente(clieId);
        } catch (Exception e) {
            throw e;
        }

        return cliente;
    }

    public List<Cliente> findByCriteriaInCliente(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return clienteLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<Cliente> findPageCliente(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return clienteLogic.findPageCliente(sortColumnName, sortAscending,
            startRow, maxResults);
    }

    public Long findTotalNumberCliente() throws Exception {
        return clienteLogic.findTotalNumberCliente();
    }

    public List<ClienteDTO> getDataCliente() throws Exception {
        return clienteLogic.getDataCliente();
    }

    public List<EstadoHistoriaUsuario> getEstadoHistoriaUsuario()
        throws Exception {
        return estadoHistoriaUsuarioLogic.getEstadoHistoriaUsuario();
    }

    public void saveEstadoHistoriaUsuario(EstadoHistoriaUsuario entity)
        throws Exception {
        estadoHistoriaUsuarioLogic.saveEstadoHistoriaUsuario(entity);
    }

    public void deleteEstadoHistoriaUsuario(EstadoHistoriaUsuario entity)
        throws Exception {
        estadoHistoriaUsuarioLogic.deleteEstadoHistoriaUsuario(entity);
    }

    public void updateEstadoHistoriaUsuario(EstadoHistoriaUsuario entity)
        throws Exception {
        estadoHistoriaUsuarioLogic.updateEstadoHistoriaUsuario(entity);
    }

    public EstadoHistoriaUsuario getEstadoHistoriaUsuario(Integer eshiId)
        throws Exception {
        EstadoHistoriaUsuario estadoHistoriaUsuario = null;

        try {
            estadoHistoriaUsuario = estadoHistoriaUsuarioLogic.getEstadoHistoriaUsuario(eshiId);
        } catch (Exception e) {
            throw e;
        }

        return estadoHistoriaUsuario;
    }

    public List<EstadoHistoriaUsuario> findByCriteriaInEstadoHistoriaUsuario(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception {
        return estadoHistoriaUsuarioLogic.findByCriteria(variables,
            variablesBetween, variablesBetweenDates);
    }

    public List<EstadoHistoriaUsuario> findPageEstadoHistoriaUsuario(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception {
        return estadoHistoriaUsuarioLogic.findPageEstadoHistoriaUsuario(sortColumnName,
            sortAscending, startRow, maxResults);
    }

    public Long findTotalNumberEstadoHistoriaUsuario()
        throws Exception {
        return estadoHistoriaUsuarioLogic.findTotalNumberEstadoHistoriaUsuario();
    }

    public List<EstadoHistoriaUsuarioDTO> getDataEstadoHistoriaUsuario()
        throws Exception {
        return estadoHistoriaUsuarioLogic.getDataEstadoHistoriaUsuario();
    }

    public List<EstadoProyecto> getEstadoProyecto() throws Exception {
        return estadoProyectoLogic.getEstadoProyecto();
    }

    public void saveEstadoProyecto(EstadoProyecto entity)
        throws Exception {
        estadoProyectoLogic.saveEstadoProyecto(entity);
    }

    public void deleteEstadoProyecto(EstadoProyecto entity)
        throws Exception {
        estadoProyectoLogic.deleteEstadoProyecto(entity);
    }

    public void updateEstadoProyecto(EstadoProyecto entity)
        throws Exception {
        estadoProyectoLogic.updateEstadoProyecto(entity);
    }

    public EstadoProyecto getEstadoProyecto(Integer esprId)
        throws Exception {
        EstadoProyecto estadoProyecto = null;

        try {
            estadoProyecto = estadoProyectoLogic.getEstadoProyecto(esprId);
        } catch (Exception e) {
            throw e;
        }

        return estadoProyecto;
    }

    public List<EstadoProyecto> findByCriteriaInEstadoProyecto(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception {
        return estadoProyectoLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<EstadoProyecto> findPageEstadoProyecto(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return estadoProyectoLogic.findPageEstadoProyecto(sortColumnName,
            sortAscending, startRow, maxResults);
    }

    public Long findTotalNumberEstadoProyecto() throws Exception {
        return estadoProyectoLogic.findTotalNumberEstadoProyecto();
    }

    public List<EstadoProyectoDTO> getDataEstadoProyecto()
        throws Exception {
        return estadoProyectoLogic.getDataEstadoProyecto();
    }

    public List<HistoriaDeUsuario> getHistoriaDeUsuario()
        throws Exception {
        return historiaDeUsuarioLogic.getHistoriaDeUsuario();
    }

    public void saveHistoriaDeUsuario(HistoriaDeUsuario entity)
        throws Exception {
        historiaDeUsuarioLogic.saveHistoriaDeUsuario(entity);
    }

    public void deleteHistoriaDeUsuario(HistoriaDeUsuario entity)
        throws Exception {
        historiaDeUsuarioLogic.deleteHistoriaDeUsuario(entity);
    }

    public void updateHistoriaDeUsuario(HistoriaDeUsuario entity)
        throws Exception {
        historiaDeUsuarioLogic.updateHistoriaDeUsuario(entity);
    }

    public HistoriaDeUsuario getHistoriaDeUsuario(Integer hiusId)
        throws Exception {
        HistoriaDeUsuario historiaDeUsuario = null;

        try {
            historiaDeUsuario = historiaDeUsuarioLogic.getHistoriaDeUsuario(hiusId);
        } catch (Exception e) {
            throw e;
        }

        return historiaDeUsuario;
    }

    public List<HistoriaDeUsuario> findByCriteriaInHistoriaDeUsuario(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception {
        return historiaDeUsuarioLogic.findByCriteria(variables,
            variablesBetween, variablesBetweenDates);
    }

    public List<HistoriaDeUsuario> findPageHistoriaDeUsuario(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception {
        return historiaDeUsuarioLogic.findPageHistoriaDeUsuario(sortColumnName,
            sortAscending, startRow, maxResults);
    }

    public Long findTotalNumberHistoriaDeUsuario() throws Exception {
        return historiaDeUsuarioLogic.findTotalNumberHistoriaDeUsuario();
    }

    public List<HistoriaDeUsuarioDTO> getDataHistoriaDeUsuario()
        throws Exception {
        return historiaDeUsuarioLogic.getDataHistoriaDeUsuario();
    }

    public List<Parametro> getParametro() throws Exception {
        return parametroLogic.getParametro();
    }

    public void saveParametro(Parametro entity) throws Exception {
        parametroLogic.saveParametro(entity);
    }

    public void deleteParametro(Parametro entity) throws Exception {
        parametroLogic.deleteParametro(entity);
    }

    public void updateParametro(Parametro entity) throws Exception {
        parametroLogic.updateParametro(entity);
    }

    public Parametro getParametro(Integer idPara) throws Exception {
        Parametro parametro = null;

        try {
            parametro = parametroLogic.getParametro(idPara);
        } catch (Exception e) {
            throw e;
        }

        return parametro;
    }

    public List<Parametro> findByCriteriaInParametro(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return parametroLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<Parametro> findPageParametro(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return parametroLogic.findPageParametro(sortColumnName, sortAscending,
            startRow, maxResults);
    }

    public Long findTotalNumberParametro() throws Exception {
        return parametroLogic.findTotalNumberParametro();
    }

    public List<ParametroDTO> getDataParametro() throws Exception {
        return parametroLogic.getDataParametro();
    }

    public List<Proyecto> getProyecto() throws Exception {
        return proyectoLogic.getProyecto();
    }

    public void saveProyecto(Proyecto entity) throws Exception {
        proyectoLogic.saveProyecto(entity);
    }

    public void deleteProyecto(Proyecto entity) throws Exception {
        proyectoLogic.deleteProyecto(entity);
    }

    public void updateProyecto(Proyecto entity) throws Exception {
        proyectoLogic.updateProyecto(entity);
    }

    public Proyecto getProyecto(Integer proyId) throws Exception {
        Proyecto proyecto = null;

        try {
            proyecto = proyectoLogic.getProyecto(proyId);
        } catch (Exception e) {
            throw e;
        }

        return proyecto;
    }

    public List<Proyecto> findByCriteriaInProyecto(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return proyectoLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<Proyecto> findPageProyecto(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return proyectoLogic.findPageProyecto(sortColumnName, sortAscending,
            startRow, maxResults);
    }

    public Long findTotalNumberProyecto() throws Exception {
        return proyectoLogic.findTotalNumberProyecto();
    }

    public List<ProyectoDTO> getDataProyecto() throws Exception {
        return proyectoLogic.getDataProyecto();
    }

    public List<ProyectoUsuarioRol> getProyectoUsuarioRol()
        throws Exception {
        return proyectoUsuarioRolLogic.getProyectoUsuarioRol();
    }

    public void saveProyectoUsuarioRol(ProyectoUsuarioRol entity)
        throws Exception {
        proyectoUsuarioRolLogic.saveProyectoUsuarioRol(entity);
    }

    public void deleteProyectoUsuarioRol(ProyectoUsuarioRol entity)
        throws Exception {
        proyectoUsuarioRolLogic.deleteProyectoUsuarioRol(entity);
    }

    public void updateProyectoUsuarioRol(ProyectoUsuarioRol entity)
        throws Exception {
        proyectoUsuarioRolLogic.updateProyectoUsuarioRol(entity);
    }

    public ProyectoUsuarioRol getProyectoUsuarioRol(ProyectoUsuarioRolId id)
        throws Exception {
        ProyectoUsuarioRol proyectoUsuarioRol = null;

        try {
            proyectoUsuarioRol = proyectoUsuarioRolLogic.getProyectoUsuarioRol(id);
        } catch (Exception e) {
            throw e;
        }

        return proyectoUsuarioRol;
    }

    public List<ProyectoUsuarioRol> findByCriteriaInProyectoUsuarioRol(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception {
        return proyectoUsuarioRolLogic.findByCriteria(variables,
            variablesBetween, variablesBetweenDates);
    }

    public List<ProyectoUsuarioRol> findPageProyectoUsuarioRol(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception {
        return proyectoUsuarioRolLogic.findPageProyectoUsuarioRol(sortColumnName,
            sortAscending, startRow, maxResults);
    }

    public Long findTotalNumberProyectoUsuarioRol() throws Exception {
        return proyectoUsuarioRolLogic.findTotalNumberProyectoUsuarioRol();
    }

    public List<ProyectoUsuarioRolDTO> getDataProyectoUsuarioRol()
        throws Exception {
        return proyectoUsuarioRolLogic.getDataProyectoUsuarioRol();
    }

    public List<Requerimiento> getRequerimiento() throws Exception {
        return requerimientoLogic.getRequerimiento();
    }

    public void saveRequerimiento(Requerimiento entity)
        throws Exception {
        requerimientoLogic.saveRequerimiento(entity);
    }

    public void deleteRequerimiento(Requerimiento entity)
        throws Exception {
        requerimientoLogic.deleteRequerimiento(entity);
    }

    public void updateRequerimiento(Requerimiento entity)
        throws Exception {
        requerimientoLogic.updateRequerimiento(entity);
    }

    public Requerimiento getRequerimiento(Integer requId)
        throws Exception {
        Requerimiento requerimiento = null;

        try {
            requerimiento = requerimientoLogic.getRequerimiento(requId);
        } catch (Exception e) {
            throw e;
        }

        return requerimiento;
    }

    public List<Requerimiento> findByCriteriaInRequerimiento(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception {
        return requerimientoLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<Requerimiento> findPageRequerimiento(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return requerimientoLogic.findPageRequerimiento(sortColumnName,
            sortAscending, startRow, maxResults);
    }

    public Long findTotalNumberRequerimiento() throws Exception {
        return requerimientoLogic.findTotalNumberRequerimiento();
    }

    public List<RequerimientoDTO> getDataRequerimiento()
        throws Exception {
        return requerimientoLogic.getDataRequerimiento();
    }

    public List<Rol> getRol() throws Exception {
        return rolLogic.getRol();
    }

    public void saveRol(Rol entity) throws Exception {
        rolLogic.saveRol(entity);
    }

    public void deleteRol(Rol entity) throws Exception {
        rolLogic.deleteRol(entity);
    }

    public void updateRol(Rol entity) throws Exception {
        rolLogic.updateRol(entity);
    }

    public Rol getRol(Integer rolId) throws Exception {
        Rol rol = null;

        try {
            rol = rolLogic.getRol(rolId);
        } catch (Exception e) {
            throw e;
        }

        return rol;
    }

    public List<Rol> findByCriteriaInRol(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return rolLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<Rol> findPageRol(String sortColumnName, boolean sortAscending,
        int startRow, int maxResults) throws Exception {
        return rolLogic.findPageRol(sortColumnName, sortAscending, startRow,
            maxResults);
    }

    public Long findTotalNumberRol() throws Exception {
        return rolLogic.findTotalNumberRol();
    }

    public List<RolDTO> getDataRol() throws Exception {
        return rolLogic.getDataRol();
    }

    public List<SeguimientoCaso> getSeguimientoCaso() throws Exception {
        return seguimientoCasoLogic.getSeguimientoCaso();
    }

    public void saveSeguimientoCaso(SeguimientoCaso entity)
        throws Exception {
        seguimientoCasoLogic.saveSeguimientoCaso(entity);
    }

    public void deleteSeguimientoCaso(SeguimientoCaso entity)
        throws Exception {
        seguimientoCasoLogic.deleteSeguimientoCaso(entity);
    }

    public void updateSeguimientoCaso(SeguimientoCaso entity)
        throws Exception {
        seguimientoCasoLogic.updateSeguimientoCaso(entity);
    }

    public SeguimientoCaso getSeguimientoCaso(Integer secaId)
        throws Exception {
        SeguimientoCaso seguimientoCaso = null;

        try {
            seguimientoCaso = seguimientoCasoLogic.getSeguimientoCaso(secaId);
        } catch (Exception e) {
            throw e;
        }

        return seguimientoCaso;
    }

    public List<SeguimientoCaso> findByCriteriaInSeguimientoCaso(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception {
        return seguimientoCasoLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<SeguimientoCaso> findPageSeguimientoCaso(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception {
        return seguimientoCasoLogic.findPageSeguimientoCaso(sortColumnName,
            sortAscending, startRow, maxResults);
    }

    public Long findTotalNumberSeguimientoCaso() throws Exception {
        return seguimientoCasoLogic.findTotalNumberSeguimientoCaso();
    }

    public List<SeguimientoCasoDTO> getDataSeguimientoCaso()
        throws Exception {
        return seguimientoCasoLogic.getDataSeguimientoCaso();
    }

    public List<TipoActividad> getTipoActividad() throws Exception {
        return tipoActividadLogic.getTipoActividad();
    }

    public void saveTipoActividad(TipoActividad entity)
        throws Exception {
        tipoActividadLogic.saveTipoActividad(entity);
    }

    public void deleteTipoActividad(TipoActividad entity)
        throws Exception {
        tipoActividadLogic.deleteTipoActividad(entity);
    }

    public void updateTipoActividad(TipoActividad entity)
        throws Exception {
        tipoActividadLogic.updateTipoActividad(entity);
    }

    public TipoActividad getTipoActividad(Integer tiacId)
        throws Exception {
        TipoActividad tipoActividad = null;

        try {
            tipoActividad = tipoActividadLogic.getTipoActividad(tiacId);
        } catch (Exception e) {
            throw e;
        }

        return tipoActividad;
    }

    public List<TipoActividad> findByCriteriaInTipoActividad(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception {
        return tipoActividadLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<TipoActividad> findPageTipoActividad(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return tipoActividadLogic.findPageTipoActividad(sortColumnName,
            sortAscending, startRow, maxResults);
    }

    public Long findTotalNumberTipoActividad() throws Exception {
        return tipoActividadLogic.findTotalNumberTipoActividad();
    }

    public List<TipoActividadDTO> getDataTipoActividad()
        throws Exception {
        return tipoActividadLogic.getDataTipoActividad();
    }

    public List<Usuario> getUsuario() throws Exception {
        return usuarioLogic.getUsuario();
    }

    public void saveUsuario(Usuario entity) throws Exception {
        usuarioLogic.saveUsuario(entity);
    }

    public void deleteUsuario(Usuario entity) throws Exception {
        usuarioLogic.deleteUsuario(entity);
    }

    public void updateUsuario(Usuario entity) throws Exception {
        usuarioLogic.updateUsuario(entity);
    }

    public Usuario getUsuario(Integer usuaId) throws Exception {
        Usuario usuario = null;

        try {
            usuario = usuarioLogic.getUsuario(usuaId);
        } catch (Exception e) {
            throw e;
        }

        return usuario;
    }

    public List<Usuario> findByCriteriaInUsuario(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return usuarioLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<Usuario> findPageUsuario(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return usuarioLogic.findPageUsuario(sortColumnName, sortAscending,
            startRow, maxResults);
    }

    public Long findTotalNumberUsuario() throws Exception {
        return usuarioLogic.findTotalNumberUsuario();
    }

    public List<UsuarioDTO> getDataUsuario() throws Exception {
        return usuarioLogic.getDataUsuario();
    }

	@Override
	public void registrarNuevoCliente(Cliente cliente) throws Exception {
		clienteLogic.registrarNuevoCliente(cliente);
	}

	@Override
	public Cliente buscarClientePorNit(String nit) throws Exception {
		return clienteLogic.buscarClientePorNit(nit);
	}

	@Override
	public List<Pais> getPais() throws Exception {
		return paisLogic.getPais();
	}

	@Override
	public List<Departamento> buscarDepartamentoPorPais(Integer paisId) throws Exception {
		return departamentoLogic.buscarDepartamentoPorPais(paisId);
	}

	@Override
	public List<Ciudad> buscarCiudadPorDepartamento(Integer depaId) throws Exception {
		return ciudadLogic.buscarCiudadPorDepartamento(depaId);
	}

	@Override
	public Ciudad getCiudad(Integer ciudId) throws Exception {
		return ciudadLogic.getCiudad(ciudId);
	}

	@Override
	public Departamento getDepartamento(Integer depaId) throws Exception {
		return departamentoLogic.getDepartamento(depaId);
	}

	@Override
	public Pais getPais(Integer paisId) throws Exception {
		return paisLogic.getPais(paisId);
	}

	@Override
	public Usuario buscarUsuarioPorEmail(String emailUsuario) throws Exception {
		return usuarioLogic.buscarUsuarioPorEmail(emailUsuario);
	}

	@Override
	public List<Cliente> listaClienteOrdenadasPorEmpresa() throws Exception {
		return clienteLogic.listaClienteOrdenadasPorEmpresa();
	}

	@Override
	public List<EstadoProyecto> listaEstadoProyectoOrdenadaPorDescripcionEstado() throws Exception {
		return estadoProyectoLogic.listaEstadoProyectoOrdenadaPorDescripcionEstado();
	}

	@Override
	public List<Proyecto> listaProyectosDadoCliente(Integer clieId) throws Exception {
		return proyectoLogic.listaProyectosDadoCliente(clieId);
	}

	@Override
	public List<ProyectoDTO> listaProyectosDTODadoCliente(Integer clieId) throws Exception {
		return proyectoLogic.listaProyectosDTODadoCliente(clieId);
	}

	@Override
	public List<Rol> listaRolesOrdenadaPorDescripcionAscendente() throws Exception {
		return rolLogic.listaRolesOrdenadaPorDescripcionAscendente();
	}

	@Override
	public List<RolDTO> listaRolesDTOOrdenadaPorDescripcionAscendente() throws Exception {
		return rolLogic.listaRolesDTOOrdenadaPorDescripcionAscendente();
	}

	@Override
	public Rol rolDeUnUsuarioEnUnProyecto(Integer usuaId, Integer proyId) throws Exception {
		return rolLogic.rolDeUnUsuarioEnUnProyecto(usuaId, proyId);
	}

	@Override
	public RolDTO rolDTODeUnUsuarioEnUnProyecto(Integer usuaId, Integer proyId) throws Exception {
		return rolLogic.rolDTODeUnUsuarioEnUnProyecto(usuaId, proyId);
	}

	@Override
	public void guardarOActualizarProyectoUsuarioRol(Integer usuaId, Integer proyId, Integer rolId) throws Exception {
		proyectoUsuarioRolLogic.guardarOActualizarProyectoUsuarioRol(usuaId, proyId, rolId);
	}

	@Override
	public List<ProyectoUsuarioRolDTO> listaProyectoUsuarioRolDadoProyecto(Integer proyId) throws Exception {
		return proyectoUsuarioRolLogic.listaProyectoUsuarioRolDadoProyecto(proyId);
	}
}
