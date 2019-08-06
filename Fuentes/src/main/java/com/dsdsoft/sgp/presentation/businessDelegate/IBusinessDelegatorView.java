package com.dsdsoft.sgp.presentation.businessDelegate;

import java.util.List;

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
* @author Zathura Code Generator http://code.google.com/p/zathura
* www.zathuracode.org
*
*/
public interface IBusinessDelegatorView {
    public List<Actividad> getActividad() throws Exception;

    public void saveActividad(Actividad entity) throws Exception;

    public void deleteActividad(Actividad entity) throws Exception;

    public void updateActividad(Actividad entity) throws Exception;

    public Actividad getActividad(Integer actiId) throws Exception;

    public List<Actividad> findByCriteriaInActividad(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Actividad> findPageActividad(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberActividad() throws Exception;

    public List<ActividadDTO> getDataActividad() throws Exception;

    public List<CasoSoporte> getCasoSoporte() throws Exception;

    public void saveCasoSoporte(CasoSoporte entity) throws Exception;

    public void deleteCasoSoporte(CasoSoporte entity) throws Exception;

    public void updateCasoSoporte(CasoSoporte entity) throws Exception;

    public CasoSoporte getCasoSoporte(Integer casoId) throws Exception;

    public List<CasoSoporte> findByCriteriaInCasoSoporte(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<CasoSoporte> findPageCasoSoporte(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberCasoSoporte() throws Exception;

    public List<CasoSoporteDTO> getDataCasoSoporte() throws Exception;

    public List<Cliente> getCliente() throws Exception;

    public void saveCliente(Cliente entity) throws Exception;

    public void deleteCliente(Cliente entity) throws Exception;

    public void updateCliente(Cliente entity) throws Exception;

    public Cliente getCliente(Integer clieId) throws Exception;

    public List<Cliente> findByCriteriaInCliente(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Cliente> findPageCliente(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberCliente() throws Exception;

    public List<ClienteDTO> getDataCliente() throws Exception;

    public List<EstadoHistoriaUsuario> getEstadoHistoriaUsuario()
        throws Exception;

    public void saveEstadoHistoriaUsuario(EstadoHistoriaUsuario entity)
        throws Exception;

    public void deleteEstadoHistoriaUsuario(EstadoHistoriaUsuario entity)
        throws Exception;

    public void updateEstadoHistoriaUsuario(EstadoHistoriaUsuario entity)
        throws Exception;

    public EstadoHistoriaUsuario getEstadoHistoriaUsuario(Integer eshiId)
        throws Exception;

    public List<EstadoHistoriaUsuario> findByCriteriaInEstadoHistoriaUsuario(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception;

    public List<EstadoHistoriaUsuario> findPageEstadoHistoriaUsuario(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception;

    public Long findTotalNumberEstadoHistoriaUsuario()
        throws Exception;

    public List<EstadoHistoriaUsuarioDTO> getDataEstadoHistoriaUsuario()
        throws Exception;

    public List<EstadoProyecto> getEstadoProyecto() throws Exception;

    public void saveEstadoProyecto(EstadoProyecto entity)
        throws Exception;

    public void deleteEstadoProyecto(EstadoProyecto entity)
        throws Exception;

    public void updateEstadoProyecto(EstadoProyecto entity)
        throws Exception;

    public EstadoProyecto getEstadoProyecto(Integer esprId)
        throws Exception;

    public List<EstadoProyecto> findByCriteriaInEstadoProyecto(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception;

    public List<EstadoProyecto> findPageEstadoProyecto(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberEstadoProyecto() throws Exception;

    public List<EstadoProyectoDTO> getDataEstadoProyecto()
        throws Exception;

    public List<HistoriaDeUsuario> getHistoriaDeUsuario()
        throws Exception;

    public void saveHistoriaDeUsuario(HistoriaDeUsuario entity)
        throws Exception;

    public void deleteHistoriaDeUsuario(HistoriaDeUsuario entity)
        throws Exception;

    public void updateHistoriaDeUsuario(HistoriaDeUsuario entity)
        throws Exception;

    public HistoriaDeUsuario getHistoriaDeUsuario(Integer hiusId)
        throws Exception;

    public List<HistoriaDeUsuario> findByCriteriaInHistoriaDeUsuario(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception;

    public List<HistoriaDeUsuario> findPageHistoriaDeUsuario(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception;

    public Long findTotalNumberHistoriaDeUsuario() throws Exception;

    public List<HistoriaDeUsuarioDTO> getDataHistoriaDeUsuario()
        throws Exception;

    public List<Parametro> getParametro() throws Exception;

    public void saveParametro(Parametro entity) throws Exception;

    public void deleteParametro(Parametro entity) throws Exception;

    public void updateParametro(Parametro entity) throws Exception;

    public Parametro getParametro(Integer idPara) throws Exception;

    public List<Parametro> findByCriteriaInParametro(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Parametro> findPageParametro(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberParametro() throws Exception;

    public List<ParametroDTO> getDataParametro() throws Exception;

    public List<Proyecto> getProyecto() throws Exception;

    public void saveProyecto(Proyecto entity) throws Exception;

    public void deleteProyecto(Proyecto entity) throws Exception;

    public void updateProyecto(Proyecto entity) throws Exception;

    public Proyecto getProyecto(Integer proyId) throws Exception;

    public List<Proyecto> findByCriteriaInProyecto(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Proyecto> findPageProyecto(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberProyecto() throws Exception;

    public List<ProyectoDTO> getDataProyecto() throws Exception;

    public List<ProyectoUsuarioRol> getProyectoUsuarioRol()
        throws Exception;

    public void saveProyectoUsuarioRol(ProyectoUsuarioRol entity)
        throws Exception;

    public void deleteProyectoUsuarioRol(ProyectoUsuarioRol entity)
        throws Exception;

    public void updateProyectoUsuarioRol(ProyectoUsuarioRol entity)
        throws Exception;

    public ProyectoUsuarioRol getProyectoUsuarioRol(ProyectoUsuarioRolId id)
        throws Exception;

    public List<ProyectoUsuarioRol> findByCriteriaInProyectoUsuarioRol(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception;

    public List<ProyectoUsuarioRol> findPageProyectoUsuarioRol(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception;

    public Long findTotalNumberProyectoUsuarioRol() throws Exception;

    public List<ProyectoUsuarioRolDTO> getDataProyectoUsuarioRol()
        throws Exception;

    public List<Requerimiento> getRequerimiento() throws Exception;

    public void saveRequerimiento(Requerimiento entity)
        throws Exception;

    public void deleteRequerimiento(Requerimiento entity)
        throws Exception;

    public void updateRequerimiento(Requerimiento entity)
        throws Exception;

    public Requerimiento getRequerimiento(Integer requId)
        throws Exception;

    public List<Requerimiento> findByCriteriaInRequerimiento(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception;

    public List<Requerimiento> findPageRequerimiento(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberRequerimiento() throws Exception;

    public List<RequerimientoDTO> getDataRequerimiento()
        throws Exception;

    public List<Rol> getRol() throws Exception;

    public void saveRol(Rol entity) throws Exception;

    public void deleteRol(Rol entity) throws Exception;

    public void updateRol(Rol entity) throws Exception;

    public Rol getRol(Integer rolId) throws Exception;

    public List<Rol> findByCriteriaInRol(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Rol> findPageRol(String sortColumnName, boolean sortAscending,
        int startRow, int maxResults) throws Exception;

    public Long findTotalNumberRol() throws Exception;

    public List<RolDTO> getDataRol() throws Exception;

    public List<SeguimientoCaso> getSeguimientoCaso() throws Exception;

    public void saveSeguimientoCaso(SeguimientoCaso entity)
        throws Exception;

    public void deleteSeguimientoCaso(SeguimientoCaso entity)
        throws Exception;

    public void updateSeguimientoCaso(SeguimientoCaso entity)
        throws Exception;

    public SeguimientoCaso getSeguimientoCaso(Integer secaId)
        throws Exception;

    public List<SeguimientoCaso> findByCriteriaInSeguimientoCaso(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception;

    public List<SeguimientoCaso> findPageSeguimientoCaso(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception;

    public Long findTotalNumberSeguimientoCaso() throws Exception;

    public List<SeguimientoCasoDTO> getDataSeguimientoCaso()
        throws Exception;

    public List<TipoActividad> getTipoActividad() throws Exception;

    public void saveTipoActividad(TipoActividad entity)
        throws Exception;

    public void deleteTipoActividad(TipoActividad entity)
        throws Exception;

    public void updateTipoActividad(TipoActividad entity)
        throws Exception;

    public TipoActividad getTipoActividad(Integer tiacId)
        throws Exception;

    public List<TipoActividad> findByCriteriaInTipoActividad(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception;

    public List<TipoActividad> findPageTipoActividad(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberTipoActividad() throws Exception;

    public List<TipoActividadDTO> getDataTipoActividad()
        throws Exception;

    public List<Usuario> getUsuario() throws Exception;

    public void saveUsuario(Usuario entity) throws Exception;

    public void deleteUsuario(Usuario entity) throws Exception;

    public void updateUsuario(Usuario entity) throws Exception;

    public Usuario getUsuario(Integer usuaId) throws Exception;

    public List<Usuario> findByCriteriaInUsuario(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Usuario> findPageUsuario(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberUsuario() throws Exception;

    public List<UsuarioDTO> getDataUsuario() throws Exception;
    
    public void registrarNuevoCliente(Cliente cliente) throws Exception;
    
    public Cliente buscarClientePorNit(String nit) throws Exception;
    
    public List<Pais> getPais() throws Exception;
    
    public List<Departamento> buscarDepartamentoPorPais(Integer paisId) throws Exception;
    
    public List<Ciudad> buscarCiudadPorDepartamento(Integer depaId) throws Exception;
    
    public Ciudad getCiudad(Integer ciudId) throws Exception;
    
    public Departamento getDepartamento(Integer depaId) throws Exception;
    
    public Pais getPais(Integer paisId) throws Exception;
    
    public Usuario buscarUsuarioPorEmail(String emailUsuario) throws Exception;
    
    public List<Cliente> listaClienteOrdenadasPorEmpresa() throws Exception;
    
    public List<EstadoProyecto> listaEstadoProyectoOrdenadaPorDescripcionEstado() throws Exception;
    
    public List<Proyecto> listaProyectosDadoCliente(Integer clieId) throws Exception;
    
    public List<ProyectoDTO> listaProyectosDTODadoCliente(Integer clieId) throws Exception;

    public List<Rol> listaRolesOrdenadaPorDescripcionAscendente() throws Exception;
    
    public List<RolDTO> listaRolesDTOOrdenadaPorDescripcionAscendente() throws Exception;

    public Rol rolDeUnUsuarioEnUnProyecto(Integer usuaId, Integer proyId) throws Exception;
    
    public RolDTO rolDTODeUnUsuarioEnUnProyecto(Integer usuaId, Integer proyId) throws Exception;
    
    public void guardarOActualizarProyectoUsuarioRol(Integer usuaId, Integer proyId, Integer rolId) throws Exception;

    public List<ProyectoUsuarioRolDTO> listaProyectoUsuarioRolDadoProyecto(Integer proyId) throws Exception;
    
    public List<RequerimientoDTO> listaRequerimientosDTOPorIdProyecto(Integer proyId) throws Exception;
    
    public List<ClienteDTO> listaClientesDTOConCiudad() throws Exception;
    
    /**
     * @author Daniel Pareja Londoño
     * @version may. 28, 2019
     * @since 1.8
     * @param usuaId
     * @return
     * @throws Exception
     * @return <b>{@code }</b> Start here...
     */
    public List<ProyectoDTO> consultarProyectosClientesPorUsuario(Integer usuaId) throws Exception;
    
    /**
     * @author Daniel Pareja Londoño
     * @version may. 28, 2019
     * @since 1.8
     * @param usuaId
     * @param proyId
     * @param requId
     * @param eshiId
     * @return
     * @throws Exception
     * @return <b>{@code }</b> Start here...
     *
     */
    public List<HistoriaDeUsuarioDTO> consultarHistoriasUsuarioPorFiltros(Integer usuaId, Integer proyId,
			Integer requId, Integer eshiId) throws Exception;
    
    /**
	 * @author Daniel Pareja Londoño
	 * @version ago. 05, 2019
	 * @since 1.8
	 * @param proyecto
	 * @return
	 * @throws Exception
	 * @return <b>{@code }</b> Start here...
	 */
	ProyectoDTO toProyectoDTO(Proyecto proyecto) throws Exception;
	/**
	 * @author Daniel Pareja Londoño
	 * @version ago. 05, 2019
	 * @since 1.8
	 * @param proyectoDTO
	 * @return
	 * @throws Exception
	 * @return <b>{@code }</b> Start here...
	 */
	Proyecto toProyecto(ProyectoDTO proyectoDTO) throws Exception;
	/**
	 * @author Daniel Pareja Londoño
	 * @version ago. 05, 2019
	 * @since 1.8
	 * @param listProyecto
	 * @return
	 * @throws Exception
	 * @return <b>{@code }</b> Start here...
	 */
	List<ProyectoDTO> toProyectoDTOList(List<Proyecto> listProyecto) throws Exception;
	/**
	 * @author Daniel Pareja Londoño
	 * @version ago. 05, 2019
	 * @since 1.8
	 * @param listProyectoDTO
	 * @return
	 * @throws Exception
	 * @return <b>{@code }</b> Start here...
	 */
	List<Proyecto> toProyectoList(List<ProyectoDTO> listProyectoDTO) throws Exception;	
}
