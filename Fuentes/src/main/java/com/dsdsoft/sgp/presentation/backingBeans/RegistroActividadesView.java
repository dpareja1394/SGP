package com.dsdsoft.sgp.presentation.backingBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import org.primefaces.component.calendar.Calendar;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.inputtextarea.InputTextarea;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dsdsoft.sgp.modelo.Actividad;
import com.dsdsoft.sgp.modelo.HistoriaDeUsuario;
import com.dsdsoft.sgp.modelo.TipoActividad;
import com.dsdsoft.sgp.modelo.Usuario;
import com.dsdsoft.sgp.modelo.dto.EstadoHistoriaUsuarioDTO;
import com.dsdsoft.sgp.modelo.dto.HistoriaDeUsuarioDTO;
import com.dsdsoft.sgp.modelo.dto.ProyectoDTO;
import com.dsdsoft.sgp.modelo.dto.RequerimientoDTO;
import com.dsdsoft.sgp.presentation.businessDelegate.IBusinessDelegatorView;
import com.dsdsoft.sgp.utilities.FacesUtils;

/**
 * @author Daniel Pareja Londoño
 * @version jul. 30, 2019
 * @since 1.8
 */
@ManagedBean
@ViewScoped
public class RegistroActividadesView implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(RegistroActividadesView.class);

	@ManagedProperty(value = "#{BusinessDelegatorView}")
	private IBusinessDelegatorView businessDelegatorView;

	private String usuarioIniciado;

	private Usuario usuario;

	/**
	 * Atributos entidad {Actividad}
	 */

	private SelectOneMenu somTipoActividad;
	private List<SelectItem> siTipoActividad;
	private InputTextarea txtDescripcionActividad;
	private Calendar calFechaHoraInicio, calFechaHoraFin;
	private HistoriaDeUsuario historiaDeUsuario;
	private InputText txtHistoriaUsuario;
	private boolean showDialogHistoriasUsuarios;

	/*
	 * Atributos para búsqueda de Historias de Usuario dentro de la pantalla
	 */
	private List<SelectItem> siProyectos, siEstadoHU;
	private SelectOneMenu somProyectos, somEstadoHU;
	private InputTextarea txtRequisito;
	private CommandButton btnBuscarRequisito;
	private List<RequerimientoDTO> requisitos;
	private String nombreProyecto;
	private boolean showRequisitos;
	private List<HistoriaDeUsuarioDTO> historiasUsuario;

	/**
	 * @author Daniel Pareja Londoño
	 * @version jul. 30, 2019
	 * @since 1.8
	 * @return <b>{@code }</b> Start here...
	 */
	public RegistroActividadesView() {
		super();
		usuarioIniciado = FacesUtils.getHttpSession(true).getAttribute("usuario_iniciado").toString();
	}

	/* Métodos y acciones */

	public void guardarNueva() {
		try {

			TipoActividad tipoActividad = businessDelegatorView
					.getTipoActividad(FacesUtils.checkInteger(somTipoActividad));

			Actividad actividad = new Actividad();
			actividad.setTipoActividad(tipoActividad);
			actividad.setDescripcionActividad(FacesUtils.checkString(txtDescripcionActividad));
			actividad.setFechaHoraInicio(FacesUtils.checkDate(calFechaHoraInicio));
			actividad.setFechaHoraFin(FacesUtils.checkDate(calFechaHoraFin));

			usuario = businessDelegatorView.buscarUsuarioPorEmail(usuarioIniciado);

			actividad.setUsuario(usuario);
			// TODO Pendiente agregar la historia de usuario

			businessDelegatorView.saveActividad(actividad);

			FacesUtils.addInfoMessage("Se ha guardado la actividad");
			limpiar();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void limpiar() {
		somTipoActividad.setValue(null);
		txtDescripcionActividad.setValue(null);
		calFechaHoraInicio.setValue(null);
		calFechaHoraFin.setValue(null);
		historiaDeUsuario = null;
	}

	public void abrirDialogoHistoriasUsuario() {
		setShowDialogHistoriasUsuarios(true);
	}

	public void abrirDialogoRequisitos() {
		setShowRequisitos(true);
	}

	public void consultarHistoriasUsuario() {
		try {
			Usuario usuario = businessDelegatorView.buscarUsuarioPorEmail(usuarioIniciado);
			this.historiasUsuario = businessDelegatorView.consultarHistoriasUsuarioPorFiltros(usuario.getUsuaId(),
					FacesUtils.checkInteger(somProyectos), -1, FacesUtils.checkInteger(somEstadoHU));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void limpiarBusquedaHistoriaUsuario() {
		setHistoriasUsuario(null);
		getHistoriasUsuario();
		somProyectos.setValue(-1);
		somEstadoHU.setValue(-1);
		txtRequisito.setValue(null);
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version may. 28, 2019
	 * @since 1.8
	 * @return El/La businessDelegatorView
	 *
	 */
	public IBusinessDelegatorView getBusinessDelegatorView() {
		return businessDelegatorView;
	}

	/**
	 *
	 * @param businessDelegatorView El/La businessDelegatorView a setear
	 * @author Daniel Pareja Londoño
	 * @version may. 28, 2019
	 * @since 1.8
	 *
	 */
	public void setBusinessDelegatorView(IBusinessDelegatorView businessDelegatorView) {
		this.businessDelegatorView = businessDelegatorView;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version may. 28, 2019
	 * @since 1.8
	 * @return El/La usuarioIniciado
	 *
	 */
	public String getUsuarioIniciado() {
		return usuarioIniciado;
	}

	/**
	 *
	 * @param usuarioIniciado El/La usuarioIniciado a setear
	 * @author Daniel Pareja Londoño
	 * @version may. 28, 2019
	 * @since 1.8
	 *
	 */
	public void setUsuarioIniciado(String usuarioIniciado) {
		this.usuarioIniciado = usuarioIniciado;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version jul. 30, 2019
	 * @since 1.8
	 * @return El/La somTipoActividad
	 *
	 */
	public SelectOneMenu getSomTipoActividad() {
		return somTipoActividad;
	}

	/**
	 *
	 * @param somTipoActividad El/La somTipoActividad a setear
	 * @author Daniel Pareja Londoño
	 * @version jul. 30, 2019
	 * @since 1.8
	 *
	 */
	public void setSomTipoActividad(SelectOneMenu somTipoActividad) {
		this.somTipoActividad = somTipoActividad;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version jul. 30, 2019
	 * @since 1.8
	 * @return El/La siTipoActividad
	 *
	 */
	public List<SelectItem> getSiTipoActividad() {
		try {
			if (siTipoActividad == null) {
				siTipoActividad = new ArrayList<SelectItem>();
				List<TipoActividad> tiposActividad = businessDelegatorView.getTipoActividad();
				for (TipoActividad tipoActividad : tiposActividad) {
					siTipoActividad.add(new SelectItem(tipoActividad.getTiacId(), tipoActividad.getDescripcionTiact()));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return siTipoActividad;
	}

	/**
	 *
	 * @param siTipoActividad El/La siTipoActividad a setear
	 * @author Daniel Pareja Londoño
	 * @version jul. 30, 2019
	 * @since 1.8
	 *
	 */
	public void setSiTipoActividad(List<SelectItem> siTipoActividad) {
		this.siTipoActividad = siTipoActividad;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version jul. 30, 2019
	 * @since 1.8
	 * @return El/La txtDescripcionActividad
	 *
	 */
	public InputTextarea getTxtDescripcionActividad() {
		return txtDescripcionActividad;
	}

	/**
	 *
	 * @param txtDescripcionActividad El/La txtDescripcionActividad a setear
	 * @author Daniel Pareja Londoño
	 * @version jul. 30, 2019
	 * @since 1.8
	 *
	 */
	public void setTxtDescripcionActividad(InputTextarea txtDescripcionActividad) {
		this.txtDescripcionActividad = txtDescripcionActividad;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version jul. 30, 2019
	 * @since 1.8
	 * @return El/La calFechaHoraInicio
	 *
	 */
	public Calendar getCalFechaHoraInicio() {
		return calFechaHoraInicio;
	}

	/**
	 *
	 * @param calFechaHoraInicio El/La calFechaHoraInicio a setear
	 * @author Daniel Pareja Londoño
	 * @version jul. 30, 2019
	 * @since 1.8
	 *
	 */
	public void setCalFechaHoraInicio(Calendar calFechaHoraInicio) {
		this.calFechaHoraInicio = calFechaHoraInicio;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version jul. 30, 2019
	 * @since 1.8
	 * @return El/La calFechaHoraFin
	 *
	 */
	public Calendar getCalFechaHoraFin() {
		return calFechaHoraFin;
	}

	/**
	 *
	 * @param calFechaHoraFin El/La calFechaHoraFin a setear
	 * @author Daniel Pareja Londoño
	 * @version jul. 30, 2019
	 * @since 1.8
	 *
	 */
	public void setCalFechaHoraFin(Calendar calFechaHoraFin) {
		this.calFechaHoraFin = calFechaHoraFin;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version jul. 30, 2019
	 * @since 1.8
	 * @return El/La historiaDeUsuario
	 *
	 */
	public HistoriaDeUsuario getHistoriaDeUsuario() {
		return historiaDeUsuario;
	}

	/**
	 *
	 * @param historiaDeUsuario El/La historiaDeUsuario a setear
	 * @author Daniel Pareja Londoño
	 * @version jul. 30, 2019
	 * @since 1.8
	 *
	 */
	public void setHistoriaDeUsuario(HistoriaDeUsuario historiaDeUsuario) {
		this.historiaDeUsuario = historiaDeUsuario;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version ago. 08, 2019
	 * @since 1.8
	 * @return El/La txtHistoriaUsuario
	 *
	 */
	public InputText getTxtHistoriaUsuario() {
		return txtHistoriaUsuario;
	}

	/**
	 *
	 * @param txtHistoriaUsuario El/La txtHistoriaUsuario a setear
	 * @author Daniel Pareja Londoño
	 * @version ago. 08, 2019
	 * @since 1.8
	 *
	 */
	public void setTxtHistoriaUsuario(InputText txtHistoriaUsuario) {
		this.txtHistoriaUsuario = txtHistoriaUsuario;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version ago. 09, 2019
	 * @since 1.8
	 * @return El/La showDialogHistoriasUsuarios
	 *
	 */
	public boolean isShowDialogHistoriasUsuarios() {
		return showDialogHistoriasUsuarios;
	}

	/**
	 *
	 * @param showDialogHistoriasUsuarios El/La showDialogHistoriasUsuarios a setear
	 * @author Daniel Pareja Londoño
	 * @version ago. 09, 2019
	 * @since 1.8
	 *
	 */
	public void setShowDialogHistoriasUsuarios(boolean showDialogHistoriasUsuarios) {
		this.showDialogHistoriasUsuarios = showDialogHistoriasUsuarios;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version may. 28, 2019
	 * @since 1.8
	 * @return El/La siProyectos
	 *
	 */
	public List<SelectItem> getSiProyectos() {
		try {
			if (siProyectos == null) {
				siProyectos = new ArrayList<SelectItem>();
				siProyectos.add(new SelectItem(-1, "Seleccione un Proyecto"));

				usuario = businessDelegatorView.buscarUsuarioPorEmail(usuarioIniciado);

				List<ProyectoDTO> proyectosUsuario = businessDelegatorView
						.consultarProyectosClientesPorUsuario(usuario.getUsuaId());
				for (ProyectoDTO proyectoDTO : proyectosUsuario) {
					siProyectos.add(new SelectItem(proyectoDTO.getProyId(),
							proyectoDTO.getDescProyecto().toUpperCase() + " - " + proyectoDTO.getNombreEmpresa()));
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			FacesUtils.addErrorMessage(e.getMessage());
		}
		return siProyectos;
	}

	/**
	 *
	 * @param siProyectos El/La siProyectos a setear
	 * @author Daniel Pareja Londoño
	 * @version may. 28, 2019
	 * @since 1.8
	 *
	 */
	public void setSiProyectos(List<SelectItem> siProyectos) {
		this.siProyectos = siProyectos;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version may. 28, 2019
	 * @since 1.8
	 * @return El/La siEstadoHU
	 *
	 */
	public List<SelectItem> getSiEstadoHU() {
		try {
			if (siEstadoHU == null) {
				siEstadoHU = new ArrayList<SelectItem>();
				siEstadoHU.add(new SelectItem(-1, "Seleccione un Estado"));

				List<EstadoHistoriaUsuarioDTO> estados = businessDelegatorView.getDataEstadoHistoriaUsuario();
				for (EstadoHistoriaUsuarioDTO estadoHistoriaUsuarioDTO : estados) {
					siEstadoHU.add(new SelectItem(estadoHistoriaUsuarioDTO.getEshiId(),
							estadoHistoriaUsuarioDTO.getDescripcionEstado()));
				}

			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return siEstadoHU;
	}

	/**
	 *
	 * @param siEstadoHU El/La siEstadoHU a setear
	 * @author Daniel Pareja Londoño
	 * @version may. 28, 2019
	 * @since 1.8
	 *
	 */
	public void setSiEstadoHU(List<SelectItem> siEstadoHU) {
		this.siEstadoHU = siEstadoHU;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version may. 28, 2019
	 * @since 1.8
	 * @return El/La somProyectos
	 *
	 */
	public SelectOneMenu getSomProyectos() {
		return somProyectos;
	}

	/**
	 *
	 * @param somProyectos El/La somProyectos a setear
	 * @author Daniel Pareja Londoño
	 * @version may. 28, 2019
	 * @since 1.8
	 *
	 */
	public void setSomProyectos(SelectOneMenu somProyectos) {
		this.somProyectos = somProyectos;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version may. 28, 2019
	 * @since 1.8
	 * @return El/La somEstadoHU
	 *
	 */
	public SelectOneMenu getSomEstadoHU() {
		return somEstadoHU;
	}

	/**
	 *
	 * @param somEstadoHU El/La somEstadoHU a setear
	 * @author Daniel Pareja Londoño
	 * @version may. 28, 2019
	 * @since 1.8
	 *
	 */
	public void setSomEstadoHU(SelectOneMenu somEstadoHU) {
		this.somEstadoHU = somEstadoHU;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version may. 28, 2019
	 * @since 1.8
	 * @return El/La txtRequisito
	 *
	 */
	public InputTextarea getTxtRequisito() {
		return txtRequisito;
	}

	/**
	 *
	 * @param txtRequisito El/La txtRequisito a setear
	 * @author Daniel Pareja Londoño
	 * @version may. 28, 2019
	 * @since 1.8
	 *
	 */
	public void setTxtRequisito(InputTextarea txtRequisito) {
		this.txtRequisito = txtRequisito;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version may. 28, 2019
	 * @since 1.8
	 * @return El/La btnBuscarRequisito
	 *
	 */
	public CommandButton getBtnBuscarRequisito() {
		return btnBuscarRequisito;
	}

	/**
	 *
	 * @param btnBuscarRequisito El/La btnBuscarRequisito a setear
	 * @author Daniel Pareja Londoño
	 * @version may. 28, 2019
	 * @since 1.8
	 *
	 */
	public void setBtnBuscarRequisito(CommandButton btnBuscarRequisito) {
		this.btnBuscarRequisito = btnBuscarRequisito;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version may. 28, 2019
	 * @since 1.8
	 * @return El/La requisitos
	 *
	 */
	public List<RequerimientoDTO> getRequisitos() {
		try {
			if (requisitos == null) {
				requisitos = businessDelegatorView
						.listaRequerimientosDTOPorIdProyecto(FacesUtils.checkInteger(somProyectos));
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			FacesUtils.addErrorMessage(e.getMessage());
		}
		return requisitos;
	}

	/**
	 *
	 * @param requisitos El/La requisitos a setear
	 * @author Daniel Pareja Londoño
	 * @version may. 28, 2019
	 * @since 1.8
	 *
	 */
	public void setRequisitos(List<RequerimientoDTO> requisitos) {
		this.requisitos = requisitos;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version may. 28, 2019
	 * @since 1.8
	 * @return El/La nombreProyecto
	 *
	 */
	public String getNombreProyecto() {
		return nombreProyecto;
	}

	/**
	 *
	 * @param nombreProyecto El/La nombreProyecto a setear
	 * @author Daniel Pareja Londoño
	 * @version may. 28, 2019
	 * @since 1.8
	 *
	 */
	public void setNombreProyecto(String nombreProyecto) {
		this.nombreProyecto = nombreProyecto;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version may. 28, 2019
	 * @since 1.8
	 * @return El/La showRequisitos
	 *
	 */
	public boolean isShowRequisitos() {
		return showRequisitos;
	}

	/**
	 *
	 * @param showRequisitos El/La showRequisitos a setear
	 * @author Daniel Pareja Londoño
	 * @version may. 28, 2019
	 * @since 1.8
	 *
	 */
	public void setShowRequisitos(boolean showRequisitos) {
		this.showRequisitos = showRequisitos;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version may. 28, 2019
	 * @since 1.8
	 * @return El/La usuario
	 *
	 */
	public Usuario getUsuario() {
		return usuario;
	}

	/**
	 *
	 * @param usuario El/La usuario a setear
	 * @author Daniel Pareja Londoño
	 * @version may. 28, 2019
	 * @since 1.8
	 *
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version may. 28, 2019
	 * @since 1.8
	 * @return El/La historiasUsuario
	 *
	 */
	public List<HistoriaDeUsuarioDTO> getHistoriasUsuario() {
		try {
			if (historiasUsuario == null) {
				Usuario usuario = businessDelegatorView.buscarUsuarioPorEmail(usuarioIniciado);
				historiasUsuario = businessDelegatorView.consultarHistoriasUsuarioPorFiltros(usuario.getUsuaId(), -1,
						-1, -1);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return historiasUsuario;
	}

	/**
	 *
	 * @param historiasUsuario El/La historiasUsuario a setear
	 * @author Daniel Pareja Londoño
	 * @version may. 28, 2019
	 * @since 1.8
	 *
	 */
	public void setHistoriasUsuario(List<HistoriaDeUsuarioDTO> historiasUsuario) {
		this.historiasUsuario = historiasUsuario;
	}

}
