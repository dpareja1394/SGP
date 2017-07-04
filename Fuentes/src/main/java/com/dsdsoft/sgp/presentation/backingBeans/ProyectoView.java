package com.dsdsoft.sgp.presentation.backingBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;

import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.inputtextarea.InputTextarea;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.primefaces.event.RowEditEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dsdsoft.sgp.exceptions.ZMessManager;
import com.dsdsoft.sgp.modelo.Cliente;
import com.dsdsoft.sgp.modelo.EstadoProyecto;
import com.dsdsoft.sgp.modelo.Proyecto;
import com.dsdsoft.sgp.modelo.Rol;
import com.dsdsoft.sgp.modelo.Usuario;
import com.dsdsoft.sgp.modelo.dto.ClienteDTO;
import com.dsdsoft.sgp.modelo.dto.ProyectoDTO;
import com.dsdsoft.sgp.modelo.dto.UsuarioDTO;
import com.dsdsoft.sgp.presentation.businessDelegate.IBusinessDelegatorView;
import com.dsdsoft.sgp.utilities.FacesUtils;

/**
 * @author Zathura Code Generator http://code.google.com/p/zathura
 *         www.zathuracode.org
 *
 */
@ManagedBean
@ViewScoped
public class ProyectoView implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(ProyectoView.class);
	private InputText txtDescProyecto;
	private InputText txtClieId_Cliente;
	private InputText txtEsprId_EstadoProyecto;
	private InputText txtProyId;
	private CommandButton btnSave;
	private CommandButton btnModify;
	private CommandButton btnDelete;
	private CommandButton btnClear;
	private List<ProyectoDTO> data;
	private ProyectoDTO selectedProyecto;
	private Proyecto entity;
	private boolean showDialog;
	@ManagedProperty(value = "#{BusinessDelegatorView}")
	private IBusinessDelegatorView businessDelegatorView;

	private CommandButton btnGuardarNuevoProyecto, btnLimpiarPantallaNuevoProyecto;
	private SelectOneMenu somEstadoProyecto;
	private Cliente clienteProyecto;
	private List<SelectItem> listEstadosProyecto;
	private boolean showClientesProyectos;
	private List<ClienteDTO> listaClientesOrdenada;
	private InputText txtNombreClienteProyecto;
	private InputTextarea txtDescripcionProyecto;

	private InputText txtNombreProyecto;

	private List<ProyectoDTO> listProyectosDelCliente;
	private String usuarioIniciado;

	private String nombreProyectoAdministrar;

	private boolean showUsuarios;
	private List<UsuarioDTO> listaUsuariosOrdenada;
	private InputText txtNombreUsuario;
	private UsuarioDTO usuarioProyecto;
	private ProyectoDTO proyectoAdministrar;
	
	private SelectOneMenu somRolesProyecto;
	private List<SelectItem> listRolesProyecto;

	public ProyectoView() {
		super();
		this.usuarioIniciado = FacesUtils.getHttpSession(true).getAttribute("usuario_iniciado").toString();
	}

	public void rowEventListener(RowEditEvent e) {
		try {
			ProyectoDTO proyectoDTO = (ProyectoDTO) e.getObject();

			if (txtDescProyecto == null) {
				txtDescProyecto = new InputText();
			}

			txtDescProyecto.setValue(proyectoDTO.getDescProyecto());

			if (txtClieId_Cliente == null) {
				txtClieId_Cliente = new InputText();
			}

			txtClieId_Cliente.setValue(proyectoDTO.getClieId_Cliente());

			if (txtEsprId_EstadoProyecto == null) {
				txtEsprId_EstadoProyecto = new InputText();
			}

			txtEsprId_EstadoProyecto.setValue(proyectoDTO.getEsprId_EstadoProyecto());

			if (txtProyId == null) {
				txtProyId = new InputText();
			}

			txtProyId.setValue(proyectoDTO.getProyId());

			Integer proyId = FacesUtils.checkInteger(txtProyId);
			entity = businessDelegatorView.getProyecto(proyId);

			action_modify();
		} catch (Exception ex) {
		}
	}

	public String action_new() {
		action_clear();
		selectedProyecto = null;
		setShowDialog(true);

		return "";
	}

	public String action_clear() {
		entity = null;
		selectedProyecto = null;

		if (txtDescProyecto != null) {
			txtDescProyecto.setValue(null);
			txtDescProyecto.setDisabled(true);
		}

		if (txtClieId_Cliente != null) {
			txtClieId_Cliente.setValue(null);
			txtClieId_Cliente.setDisabled(true);
		}

		if (txtEsprId_EstadoProyecto != null) {
			txtEsprId_EstadoProyecto.setValue(null);
			txtEsprId_EstadoProyecto.setDisabled(true);
		}

		if (txtProyId != null) {
			txtProyId.setValue(null);
			txtProyId.setDisabled(false);
		}

		if (btnSave != null) {
			btnSave.setDisabled(true);
		}

		if (btnDelete != null) {
			btnDelete.setDisabled(true);
		}

		return "";
	}

	public void listener_txtId() {
		try {
			Integer proyId = FacesUtils.checkInteger(txtProyId);
			entity = (proyId != null) ? businessDelegatorView.getProyecto(proyId) : null;
		} catch (Exception e) {
			entity = null;
		}

		if (entity == null) {
			txtDescProyecto.setDisabled(false);
			txtClieId_Cliente.setDisabled(false);
			txtEsprId_EstadoProyecto.setDisabled(false);
			txtProyId.setDisabled(false);
			btnSave.setDisabled(false);
		} else {
			txtDescProyecto.setValue(entity.getDescProyecto());
			txtDescProyecto.setDisabled(false);
			txtClieId_Cliente.setValue(entity.getCliente().getClieId());
			txtClieId_Cliente.setDisabled(false);
			txtEsprId_EstadoProyecto.setValue(entity.getEstadoProyecto().getEsprId());
			txtEsprId_EstadoProyecto.setDisabled(false);
			txtProyId.setValue(entity.getProyId());
			txtProyId.setDisabled(true);
			btnSave.setDisabled(false);

			if (btnDelete != null) {
				btnDelete.setDisabled(false);
			}
		}
	}

	public String action_edit(ActionEvent evt) {
		selectedProyecto = (ProyectoDTO) (evt.getComponent().getAttributes().get("selectedProyecto"));
		txtDescProyecto.setValue(selectedProyecto.getDescProyecto());
		txtDescProyecto.setDisabled(false);
		txtClieId_Cliente.setValue(selectedProyecto.getClieId_Cliente());
		txtClieId_Cliente.setDisabled(false);
		txtEsprId_EstadoProyecto.setValue(selectedProyecto.getEsprId_EstadoProyecto());
		txtEsprId_EstadoProyecto.setDisabled(false);
		txtProyId.setValue(selectedProyecto.getProyId());
		txtProyId.setDisabled(true);
		btnSave.setDisabled(false);
		setShowDialog(true);

		return "";
	}

	public String action_save() {
		try {
			if ((selectedProyecto == null) && (entity == null)) {
				action_create();
			} else {
				action_modify();
			}

			data = null;
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public String action_create() {
		try {
			entity = new Proyecto();

			Integer proyId = FacesUtils.checkInteger(txtProyId);

			entity.setDescProyecto(FacesUtils.checkString(txtDescProyecto));
			entity.setProyId(proyId);
			entity.setCliente((FacesUtils.checkInteger(txtClieId_Cliente) != null)
					? businessDelegatorView.getCliente(FacesUtils.checkInteger(txtClieId_Cliente)) : null);
			entity.setEstadoProyecto((FacesUtils.checkInteger(txtEsprId_EstadoProyecto) != null)
					? businessDelegatorView.getEstadoProyecto(FacesUtils.checkInteger(txtEsprId_EstadoProyecto))
					: null);
			businessDelegatorView.saveProyecto(entity);
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYSAVED);
			action_clear();
		} catch (Exception e) {
			entity = null;
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public String action_modify() {
		try {
			if (entity == null) {
				Integer proyId = new Integer(selectedProyecto.getProyId());
				entity = businessDelegatorView.getProyecto(proyId);
			}

			entity.setDescProyecto(FacesUtils.checkString(txtDescProyecto));
			entity.setCliente((FacesUtils.checkInteger(txtClieId_Cliente) != null)
					? businessDelegatorView.getCliente(FacesUtils.checkInteger(txtClieId_Cliente)) : null);
			entity.setEstadoProyecto((FacesUtils.checkInteger(txtEsprId_EstadoProyecto) != null)
					? businessDelegatorView.getEstadoProyecto(FacesUtils.checkInteger(txtEsprId_EstadoProyecto))
					: null);
			businessDelegatorView.updateProyecto(entity);
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
		} catch (Exception e) {
			data = null;
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public String action_delete_datatable(ActionEvent evt) {
		try {
			selectedProyecto = (ProyectoDTO) (evt.getComponent().getAttributes().get("selectedProyecto"));

			Integer proyId = new Integer(selectedProyecto.getProyId());
			entity = businessDelegatorView.getProyecto(proyId);
			action_delete();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public String action_delete_master() {
		try {
			Integer proyId = FacesUtils.checkInteger(txtProyId);
			entity = businessDelegatorView.getProyecto(proyId);
			action_delete();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public void action_delete() throws Exception {
		try {
			businessDelegatorView.deleteProyecto(entity);
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
			action_clear();
			data = null;
		} catch (Exception e) {
			throw e;
		}
	}

	public String action_closeDialog() {
		setShowDialog(false);
		action_clear();

		return "";
	}

	public String actionDeleteDataTableEditable(ActionEvent evt) {
		try {
			selectedProyecto = (ProyectoDTO) (evt.getComponent().getAttributes().get("selectedProyecto"));

			Integer proyId = new Integer(selectedProyecto.getProyId());
			entity = businessDelegatorView.getProyecto(proyId);
			businessDelegatorView.deleteProyecto(entity);
			data.remove(selectedProyecto);
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
			action_clear();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public String action_modifyWitDTO(String descProyecto, Integer proyId, Integer clieId_Cliente,
			Integer esprId_EstadoProyecto) throws Exception {
		try {
			entity.setDescProyecto(FacesUtils.checkString(descProyecto));
			businessDelegatorView.updateProyecto(entity);
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
		} catch (Exception e) {
			// renderManager.getOnDemandRenderer("ProyectoView").requestRender();
			FacesUtils.addErrorMessage(e.getMessage());
			throw e;
		}

		return "";
	}

	public String abrirDialogoBuscarCliente() {
		setShowClientesProyectos(true);
		return "";
	}

	public String abrirDialogoBuscarUsuario() {
		setShowUsuarios(true);
		return "";
	}

	public String seleccionarCliente(ActionEvent actionEvent) {
		try {

			ClienteDTO clienteDTO = (ClienteDTO) (actionEvent.getComponent().getAttributes()
					.get("clienteSeleccionado"));

			txtNombreClienteProyecto.setValue(clienteDTO.getNombreEmpresa());
			clienteProyecto = businessDelegatorView.getCliente(clienteDTO.getClieId());
			listProyectosDelCliente = businessDelegatorView.listaProyectosDTODadoCliente(clienteDTO.getClieId());
			setShowClientesProyectos(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public String seleccionarUsuario(ActionEvent actionEvent) {
		try {

			usuarioProyecto = (UsuarioDTO) (actionEvent.getComponent().getAttributes()
					.get("usuarioSeleccionado"));
			txtNombreUsuario.setValue(
					usuarioProyecto.getNombreUsuario().toUpperCase() + " " + usuarioProyecto.getApellidoUsuario().toUpperCase());
			somRolesProyecto.setDisabled(false);
			Rol rol = businessDelegatorView.rolDeUnUsuarioEnUnProyecto(usuarioProyecto.getUsuaId(), proyectoAdministrar.getProyId());
			somRolesProyecto.setValue(rol.getRolId());
			
			setShowUsuarios(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public void guardarNuevoProyecto() {
		try {
			EstadoProyecto estadoProyecto = businessDelegatorView
					.getEstadoProyecto(Integer.parseInt(somEstadoProyecto.getValue().toString()));
			Proyecto proyecto = new Proyecto();
			proyecto.setCliente(clienteProyecto);
			proyecto.setDescProyecto(FacesUtils.checkString(txtDescripcionProyecto));
			proyecto.setEstadoProyecto(estadoProyecto);

			Usuario usuario = businessDelegatorView.buscarUsuarioPorEmail(usuarioIniciado);
			proyecto.setUsuarioByUsuarioCreacion(usuario);

			businessDelegatorView.saveProyecto(proyecto);
			listProyectosDelCliente = businessDelegatorView.listaProyectosDTODadoCliente(clienteProyecto.getClieId());
			FacesUtils
					.addInfoMessage("Se ha guardado el proyecto para el cliente " + clienteProyecto.getNombreEmpresa());

		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void limpiarNuevoProyecto() {
		somEstadoProyecto.setValue(null);
		txtDescripcionProyecto.setValue(null);
		txtNombreClienteProyecto.setValue(null);
		clienteProyecto = null;
		listProyectosDelCliente = null;
		listaClientesOrdenada = null;
	}

	public InputText getTxtDescProyecto() {
		return txtDescProyecto;
	}

	public void setTxtDescProyecto(InputText txtDescProyecto) {
		this.txtDescProyecto = txtDescProyecto;
	}

	public InputText getTxtClieId_Cliente() {
		return txtClieId_Cliente;
	}

	public void setTxtClieId_Cliente(InputText txtClieId_Cliente) {
		this.txtClieId_Cliente = txtClieId_Cliente;
	}

	public InputText getTxtEsprId_EstadoProyecto() {
		return txtEsprId_EstadoProyecto;
	}

	public void setTxtEsprId_EstadoProyecto(InputText txtEsprId_EstadoProyecto) {
		this.txtEsprId_EstadoProyecto = txtEsprId_EstadoProyecto;
	}

	public InputText getTxtProyId() {
		return txtProyId;
	}

	public void setTxtProyId(InputText txtProyId) {
		this.txtProyId = txtProyId;
	}

	public List<ProyectoDTO> getData() {
		try {
			if (data == null) {
				data = businessDelegatorView.getDataProyecto();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return data;
	}

	public void setData(List<ProyectoDTO> proyectoDTO) {
		this.data = proyectoDTO;
	}

	public ProyectoDTO getSelectedProyecto() {
		return selectedProyecto;
	}

	public void setSelectedProyecto(ProyectoDTO proyecto) {
		this.selectedProyecto = proyecto;
	}

	public CommandButton getBtnSave() {
		return btnSave;
	}

	public void setBtnSave(CommandButton btnSave) {
		this.btnSave = btnSave;
	}

	public CommandButton getBtnModify() {
		return btnModify;
	}

	public void setBtnModify(CommandButton btnModify) {
		this.btnModify = btnModify;
	}

	public CommandButton getBtnDelete() {
		return btnDelete;
	}

	public void setBtnDelete(CommandButton btnDelete) {
		this.btnDelete = btnDelete;
	}

	public CommandButton getBtnClear() {
		return btnClear;
	}

	public void setBtnClear(CommandButton btnClear) {
		this.btnClear = btnClear;
	}

	public TimeZone getTimeZone() {
		return java.util.TimeZone.getDefault();
	}

	public IBusinessDelegatorView getBusinessDelegatorView() {
		return businessDelegatorView;
	}

	public void setBusinessDelegatorView(IBusinessDelegatorView businessDelegatorView) {
		this.businessDelegatorView = businessDelegatorView;
	}

	public boolean isShowDialog() {
		return showDialog;
	}

	public void setShowDialog(boolean showDialog) {
		this.showDialog = showDialog;
	}

	public CommandButton getBtnGuardarNuevoProyecto() {
		return btnGuardarNuevoProyecto;
	}

	public void setBtnGuardarNuevoProyecto(CommandButton btnGuardarNuevoProyecto) {
		this.btnGuardarNuevoProyecto = btnGuardarNuevoProyecto;
	}

	public CommandButton getBtnLimpiarPantallaNuevoProyecto() {
		return btnLimpiarPantallaNuevoProyecto;
	}

	public void setBtnLimpiarPantallaNuevoProyecto(CommandButton btnLimpiarPantallaNuevoProyecto) {
		this.btnLimpiarPantallaNuevoProyecto = btnLimpiarPantallaNuevoProyecto;
	}

	public SelectOneMenu getSomEstadoProyecto() {
		return somEstadoProyecto;
	}

	public void setSomEstadoProyecto(SelectOneMenu somEstadoProyecto) {
		this.somEstadoProyecto = somEstadoProyecto;
	}

	public Cliente getClienteProyecto() {
		return clienteProyecto;
	}

	public void setClienteProyecto(Cliente clienteProyecto) {
		this.clienteProyecto = clienteProyecto;
	}

	public List<SelectItem> getListEstadosProyecto() {
		try {
			if (listEstadosProyecto == null) {
				listEstadosProyecto = new ArrayList<SelectItem>();
				List<EstadoProyecto> listaEstadoProyecto = businessDelegatorView
						.listaEstadoProyectoOrdenadaPorDescripcionEstado();
				for (EstadoProyecto estadoProyecto : listaEstadoProyecto) {
					listEstadosProyecto
							.add(new SelectItem(estadoProyecto.getEsprId(), estadoProyecto.getDescripcionEstado()));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listEstadosProyecto;
	}

	public void setListEstadosProyecto(List<SelectItem> listEstadosProyecto) {
		this.listEstadosProyecto = listEstadosProyecto;
	}

	public boolean isShowClientesProyectos() {
		return showClientesProyectos;
	}

	public void setShowClientesProyectos(boolean showClientesProyectos) {
		this.showClientesProyectos = showClientesProyectos;
	}

	public List<ClienteDTO> getListaClientesOrdenada() {
		try {
			if (listaClientesOrdenada == null) {
				listaClientesOrdenada = businessDelegatorView.getDataCliente();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaClientesOrdenada;
	}

	public void setListaClientesOrdenada(List<ClienteDTO> listaClientesOrdenada) {
		this.listaClientesOrdenada = listaClientesOrdenada;
	}

	public InputText getTxtNombreClienteProyecto() {
		return txtNombreClienteProyecto;
	}

	public void setTxtNombreClienteProyecto(InputText txtNombreClienteProyecto) {
		this.txtNombreClienteProyecto = txtNombreClienteProyecto;
	}

	public InputTextarea getTxtDescripcionProyecto() {
		return txtDescripcionProyecto;
	}

	public void setTxtDescripcionProyecto(InputTextarea txtDescripcionProyecto) {
		this.txtDescripcionProyecto = txtDescripcionProyecto;
	}

	public List<ProyectoDTO> getListProyectosDelCliente() {
		return listProyectosDelCliente;
	}

	public void setListProyectosDelCliente(List<ProyectoDTO> listProyectosDelCliente) {
		this.listProyectosDelCliente = listProyectosDelCliente;
	}

	public String getUsuarioIniciado() {
		return usuarioIniciado;
	}

	public void setUsuarioIniciado(String usuarioIniciado) {
		this.usuarioIniciado = usuarioIniciado;
	}

	public InputText getTxtNombreProyecto() {
		return txtNombreProyecto;
	}

	public void setTxtNombreProyecto(InputText txtNombreProyecto) {
		this.txtNombreProyecto = txtNombreProyecto;
	}

	public String getNombreProyectoAdministrar() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		proyectoAdministrar = (ProyectoDTO) session.getAttribute("proyectoAdministrar");
		nombreProyectoAdministrar = proyectoAdministrar.getDescProyecto();
		return nombreProyectoAdministrar;
	}

	public void setNombreProyectoAdministrar(String nombreProyectoAdministrar) {
		this.nombreProyectoAdministrar = nombreProyectoAdministrar;
	}

	public boolean isShowUsuarios() {
		return showUsuarios;
	}

	public void setShowUsuarios(boolean showUsuarios) {
		this.showUsuarios = showUsuarios;
	}

	public List<UsuarioDTO> getListaUsuariosOrdenada() {
		try {
			if(listaUsuariosOrdenada == null){
				listaUsuariosOrdenada = businessDelegatorView.getDataUsuario();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaUsuariosOrdenada;
	}

	public void setListaUsuariosOrdenada(List<UsuarioDTO> listaUsuariosOrdenada) {
		this.listaUsuariosOrdenada = listaUsuariosOrdenada;
	}

	public InputText getTxtNombreUsuario() {
		return txtNombreUsuario;
	}

	public void setTxtNombreUsuario(InputText txtNombreUsuario) {
		this.txtNombreUsuario = txtNombreUsuario;
	}

	public SelectOneMenu getSomRolesProyecto() {
		return somRolesProyecto;
	}

	public void setSomRolesProyecto(SelectOneMenu somRolesProyecto) {
		this.somRolesProyecto = somRolesProyecto;
	}

	public List<SelectItem> getListRolesProyecto() {
		try {
			if(listRolesProyecto == null){
				listRolesProyecto = new ArrayList<SelectItem>();
				List<Rol> roles = businessDelegatorView.listaRolesOrdenadaPorDescripcionAscendente();
				for (Rol rol : roles) {
					listRolesProyecto.add(new SelectItem(rol.getRolId(), rol.getDescRol()));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listRolesProyecto;
	}

	public void setListRolesProyecto(List<SelectItem> listRolesProyecto) {
		this.listRolesProyecto = listRolesProyecto;
	}

}
