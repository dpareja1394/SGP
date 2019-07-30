package com.dsdsoft.sgp.presentation.backingBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtextarea.InputTextarea;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dsdsoft.sgp.modelo.Proyecto;
import com.dsdsoft.sgp.modelo.Requerimiento;
import com.dsdsoft.sgp.modelo.Usuario;
import com.dsdsoft.sgp.modelo.dto.EstadoHistoriaUsuarioDTO;
import com.dsdsoft.sgp.modelo.dto.HistoriaDeUsuarioDTO;
import com.dsdsoft.sgp.modelo.dto.ProyectoDTO;
import com.dsdsoft.sgp.modelo.dto.RequerimientoDTO;
import com.dsdsoft.sgp.presentation.businessDelegate.IBusinessDelegatorView;
import com.dsdsoft.sgp.utilities.FacesUtils;

/**
 * @author Zathura Code Generator http://code.google.com/p/zathura
 *         www.zathuracode.org
 *
 */
@ManagedBean
@ViewScoped
public class HistoriasUsuarioView implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(HistoriasUsuarioView.class);

	@ManagedProperty(value = "#{BusinessDelegatorView}")
	private IBusinessDelegatorView businessDelegatorView;

	private String usuarioIniciado;

	private List<SelectItem> siProyectos, siEstadoHU;
	private SelectOneMenu somProyectos, somEstadoHU;
	private InputTextarea txtRequisito;
	private CommandButton btnBuscarRequisito;
	private List<RequerimientoDTO> requisitos;
	private String nombreProyecto;
	private Requerimiento requisito;
	private boolean showRequisitos;
	private Usuario usuario;
	
	private List<HistoriaDeUsuarioDTO> historiasUsuario;

	
	public HistoriasUsuarioView() {
		super();
		usuarioIniciado = FacesUtils.getHttpSession(true).getAttribute("usuario_iniciado").toString();
	}

	/* Métodos y acciones */

	public void listenerProyecto() {
		try {
			Proyecto proyecto = businessDelegatorView.getProyecto(FacesUtils.checkInteger(somProyectos));
			if (proyecto != null) {
				btnBuscarRequisito.setDisabled(false);
				nombreProyecto = proyecto.getDescProyecto();
			} else {
				btnBuscarRequisito.setDisabled(true);
				FacesUtils.addErrorMessage("Debe seleccionar un Proyecto");
			}

		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void abrirDialogoRequisitos() {
		setShowRequisitos(true);
	}

	public void seleccionarRequisito(Integer requId) {
		try {
			this.requisito = businessDelegatorView.getRequerimiento(requId);
			txtRequisito.setValue(this.requisito.getNombreRequerimiento());
			setShowRequisitos(false);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}
	
	public void consultarHistoriasUsuario() {
		try {
			Usuario usuario = businessDelegatorView.buscarUsuarioPorEmail(usuarioIniciado);
			this.historiasUsuario = businessDelegatorView.consultarHistoriasUsuarioPorFiltros(usuario.getUsuaId(), FacesUtils.checkInteger(somProyectos),
					requisito==null?-1:requisito.getRequId(), FacesUtils.checkInteger(somEstadoHU));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}
	
	public void limpiar() {
		setHistoriasUsuario(null);
		getHistoriasUsuario();
		somProyectos.setValue(-1);
		requisito = null;
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
	 * @return El/La requisito
	 *
	 */
	public Requerimiento getRequisito() {
		return requisito;
	}


	/**
	 *
	 * @param requisito El/La requisito a setear
	 * @author Daniel Pareja Londoño
	 * @version may. 28, 2019
	 * @since 1.8
	 *
	 */
	public void setRequisito(Requerimiento requisito) {
		this.requisito = requisito;
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
			if(historiasUsuario == null) {
				Usuario usuario = businessDelegatorView.buscarUsuarioPorEmail(usuarioIniciado);
				historiasUsuario = businessDelegatorView.consultarHistoriasUsuarioPorFiltros(usuario.getUsuaId(), -1, -1, -1);
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
