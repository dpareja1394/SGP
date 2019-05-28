package com.dsdsoft.sgp.presentation.backingBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;

import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.primefaces.event.RowEditEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dsdsoft.sgp.exceptions.ZMessManager;
import com.dsdsoft.sgp.modelo.Ciudad;
import com.dsdsoft.sgp.modelo.Cliente;
import com.dsdsoft.sgp.modelo.Departamento;
import com.dsdsoft.sgp.modelo.Pais;
import com.dsdsoft.sgp.modelo.Usuario;
import com.dsdsoft.sgp.modelo.dto.ClienteDTO;
import com.dsdsoft.sgp.modelo.dto.ProyectoDTO;
import com.dsdsoft.sgp.presentation.businessDelegate.IBusinessDelegatorView;
import com.dsdsoft.sgp.utilities.FacesUtils;

/**
 * @author Zathura Code Generator http://code.google.com/p/zathura
 *         www.zathuracode.org
 *
 */
@ManagedBean
@ViewScoped
public class ClienteView implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(ClienteView.class);
	private InputText txtCelularContacto;
	private InputText txtDireccionContacto;
	private InputText txtEnlaceWeb;
	private InputText txtNit;
	private InputText txtNombreContacto;
	private InputText txtNombreEmpresa;
	private InputText txtTelefonoContacto;
	private InputText txtClieId;
	private InputText txtEmailContacto;
	private CommandButton btnSave;
	private CommandButton btnModify;
	private CommandButton btnDelete;
	private CommandButton btnClear;
	private List<ClienteDTO> data;
	private ClienteDTO selectedCliente;
	private Cliente entity;
	private boolean showDialog;
	@ManagedProperty(value = "#{BusinessDelegatorView}")
	private IBusinessDelegatorView businessDelegatorView;

	private CommandButton btnRegistrarNuevoCliente, btnModificarCliente, btnLimpiarPantalla;
	private SelectOneMenu somPaises, somDepartamentos, somCiudades;
	private List<SelectItem> listaPaises, listaDepartamentos, listaCiudades;
	private List<ProyectoDTO> proyectosCliente;
	private ClienteDTO clienteSeleccionadoDTO;

	private String usuarioIniciado;
	
	private List<ClienteDTO> clientesConCiudad;
	
	private boolean showEditarClientes;
	
	private InputText txtNitEditar, txtNombreEmpresaEditar, txtEnlaceWebEditar,
						txtNombreContactoEditar, txtEmailContactoEditar, txtCelularContactoEditar,
						txtTelefonoContactoEditar, txtDireccionContactoEditar;
	
	private SelectOneMenu somPaisesEditar, somDepartamentosEditar, somCiudadesEditar;
	
	private Cliente clienteEditar;
	
	public ClienteView() {
		super();
		this.usuarioIniciado = FacesUtils.getHttpSession(true).getAttribute("usuario_iniciado").toString();
	}

	public void rowEventListener(RowEditEvent e) {
		try {
			ClienteDTO clienteDTO = (ClienteDTO) e.getObject();

			if (txtCelularContacto == null) {
				txtCelularContacto = new InputText();
			}

			txtCelularContacto.setValue(clienteDTO.getCelularContacto());

			if (txtDireccionContacto == null) {
				txtDireccionContacto = new InputText();
			}

			txtDireccionContacto.setValue(clienteDTO.getDireccionContacto());

			if (txtEnlaceWeb == null) {
				txtEnlaceWeb = new InputText();
			}

			txtEnlaceWeb.setValue(clienteDTO.getEnlaceWeb());

			if (txtNit == null) {
				txtNit = new InputText();
			}

			txtNit.setValue(clienteDTO.getNit());

			if (txtNombreContacto == null) {
				txtNombreContacto = new InputText();
			}

			txtNombreContacto.setValue(clienteDTO.getNombreContacto());

			if (txtNombreEmpresa == null) {
				txtNombreEmpresa = new InputText();
			}

			txtNombreEmpresa.setValue(clienteDTO.getNombreEmpresa());

			if (txtTelefonoContacto == null) {
				txtTelefonoContacto = new InputText();
			}

			txtTelefonoContacto.setValue(clienteDTO.getTelefonoContacto());

			if (txtClieId == null) {
				txtClieId = new InputText();
			}

			txtClieId.setValue(clienteDTO.getClieId());

			Integer clieId = FacesUtils.checkInteger(txtClieId);
			entity = businessDelegatorView.getCliente(clieId);

			action_modify();
		} catch (Exception ex) {
		}
	}

	public String action_new() {
		action_clear();
		selectedCliente = null;
		setShowDialog(true);

		return "";
	}

	public String action_clear() {
		entity = null;
		selectedCliente = null;

		if (txtCelularContacto != null) {
			txtCelularContacto.setValue(null);
			txtCelularContacto.setDisabled(true);
		}

		if (txtDireccionContacto != null) {
			txtDireccionContacto.setValue(null);
			txtDireccionContacto.setDisabled(true);
		}

		if (txtEnlaceWeb != null) {
			txtEnlaceWeb.setValue(null);
			txtEnlaceWeb.setDisabled(true);
		}

		if (txtNit != null) {
			txtNit.setValue(null);
			txtNit.setDisabled(true);
		}

		if (txtNombreContacto != null) {
			txtNombreContacto.setValue(null);
			txtNombreContacto.setDisabled(true);
		}

		if (txtNombreEmpresa != null) {
			txtNombreEmpresa.setValue(null);
			txtNombreEmpresa.setDisabled(true);
		}

		if (txtTelefonoContacto != null) {
			txtTelefonoContacto.setValue(null);
			txtTelefonoContacto.setDisabled(true);
		}

		if (txtClieId != null) {
			txtClieId.setValue(null);
			txtClieId.setDisabled(false);
		}

		/*
		 * if (btnSave != null) { btnSave.setDisabled(true); }
		 */

		if (btnDelete != null) {
			btnDelete.setDisabled(true);
		}

		return "";
	}

	public void listener_txtId() {
		try {
			Integer clieId = FacesUtils.checkInteger(txtClieId);
			entity = (clieId != null) ? businessDelegatorView.getCliente(clieId) : null;
		} catch (Exception e) {
			entity = null;
		}

		if (entity == null) {
			txtCelularContacto.setDisabled(false);
			txtDireccionContacto.setDisabled(false);
			txtEnlaceWeb.setDisabled(false);
			txtNit.setDisabled(false);
			txtNombreContacto.setDisabled(false);
			txtNombreEmpresa.setDisabled(false);
			txtTelefonoContacto.setDisabled(false);
			txtClieId.setDisabled(false);
			btnSave.setDisabled(false);
		} else {
			txtCelularContacto.setValue(entity.getCelularContacto());
			txtCelularContacto.setDisabled(false);
			txtDireccionContacto.setValue(entity.getDireccionContacto());
			txtDireccionContacto.setDisabled(false);
			txtEnlaceWeb.setValue(entity.getEnlaceWeb());
			txtEnlaceWeb.setDisabled(false);
			txtNit.setValue(entity.getNit());
			txtNit.setDisabled(false);
			txtNombreContacto.setValue(entity.getNombreContacto());
			txtNombreContacto.setDisabled(false);
			txtNombreEmpresa.setValue(entity.getNombreEmpresa());
			txtNombreEmpresa.setDisabled(false);
			txtTelefonoContacto.setValue(entity.getTelefonoContacto());
			txtTelefonoContacto.setDisabled(false);
			txtClieId.setValue(entity.getClieId());
			txtClieId.setDisabled(true);
			btnSave.setDisabled(false);

			if (btnDelete != null) {
				btnDelete.setDisabled(false);
			}
		}
	}

	public String action_edit(ActionEvent evt) {
		selectedCliente = (ClienteDTO) (evt.getComponent().getAttributes().get("selectedCliente"));
		txtCelularContacto.setValue(selectedCliente.getCelularContacto());
		txtCelularContacto.setDisabled(false);
		txtDireccionContacto.setValue(selectedCliente.getDireccionContacto());
		txtDireccionContacto.setDisabled(false);
		txtEnlaceWeb.setValue(selectedCliente.getEnlaceWeb());
		txtEnlaceWeb.setDisabled(false);
		txtNit.setValue(selectedCliente.getNit());
		txtNit.setDisabled(false);
		txtNombreContacto.setValue(selectedCliente.getNombreContacto());
		txtNombreContacto.setDisabled(false);
		txtNombreEmpresa.setValue(selectedCliente.getNombreEmpresa());
		txtNombreEmpresa.setDisabled(false);
		txtTelefonoContacto.setValue(selectedCliente.getTelefonoContacto());
		txtTelefonoContacto.setDisabled(false);
		txtClieId.setValue(selectedCliente.getClieId());
		txtClieId.setDisabled(true);
		btnSave.setDisabled(false);
		setShowDialog(true);

		return "";
	}

	public String action_save() {
		try {
			if ((selectedCliente == null) && (entity == null)) {
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
			entity = new Cliente();

			Integer clieId = FacesUtils.checkInteger(txtClieId);

			entity.setCelularContacto(FacesUtils.checkString(txtCelularContacto));
			entity.setClieId(clieId);
			entity.setDireccionContacto(FacesUtils.checkString(txtDireccionContacto));
			entity.setEnlaceWeb(FacesUtils.checkString(txtEnlaceWeb));
			entity.setNit(FacesUtils.checkString(txtNit));
			entity.setNombreContacto(FacesUtils.checkString(txtNombreContacto));
			entity.setNombreEmpresa(FacesUtils.checkString(txtNombreEmpresa));
			entity.setTelefonoContacto(FacesUtils.checkString(txtTelefonoContacto));
			businessDelegatorView.saveCliente(entity);
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
				Integer clieId = new Integer(selectedCliente.getClieId());
				entity = businessDelegatorView.getCliente(clieId);
			}

			entity.setCelularContacto(FacesUtils.checkString(txtCelularContacto));
			entity.setDireccionContacto(FacesUtils.checkString(txtDireccionContacto));
			entity.setEnlaceWeb(FacesUtils.checkString(txtEnlaceWeb));
			entity.setNit(FacesUtils.checkString(txtNit));
			entity.setNombreContacto(FacesUtils.checkString(txtNombreContacto));
			entity.setNombreEmpresa(FacesUtils.checkString(txtNombreEmpresa));
			entity.setTelefonoContacto(FacesUtils.checkString(txtTelefonoContacto));
			businessDelegatorView.updateCliente(entity);
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
		} catch (Exception e) {
			data = null;
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public String action_delete_datatable(ActionEvent evt) {
		try {
			selectedCliente = (ClienteDTO) (evt.getComponent().getAttributes().get("selectedCliente"));

			Integer clieId = new Integer(selectedCliente.getClieId());
			entity = businessDelegatorView.getCliente(clieId);
			action_delete();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public String action_delete_master() {
		try {
			Integer clieId = FacesUtils.checkInteger(txtClieId);
			entity = businessDelegatorView.getCliente(clieId);
			action_delete();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public void action_delete() throws Exception {
		try {
			businessDelegatorView.deleteCliente(entity);
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
			selectedCliente = (ClienteDTO) (evt.getComponent().getAttributes().get("selectedCliente"));

			Integer clieId = new Integer(selectedCliente.getClieId());
			entity = businessDelegatorView.getCliente(clieId);
			businessDelegatorView.deleteCliente(entity);
			data.remove(selectedCliente);
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
			action_clear();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public String action_modifyWitDTO(String celularContacto, Integer clieId, String direccionContacto,
			String enlaceWeb, String nit, String nombreContacto, String nombreEmpresa, String telefonoContacto)
			throws Exception {
		try {
			entity.setCelularContacto(FacesUtils.checkString(celularContacto));
			entity.setDireccionContacto(FacesUtils.checkString(direccionContacto));
			entity.setEnlaceWeb(FacesUtils.checkString(enlaceWeb));
			entity.setNit(FacesUtils.checkString(nit));
			entity.setNombreContacto(FacesUtils.checkString(nombreContacto));
			entity.setNombreEmpresa(FacesUtils.checkString(nombreEmpresa));
			entity.setTelefonoContacto(FacesUtils.checkString(telefonoContacto));
			businessDelegatorView.updateCliente(entity);
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
		} catch (Exception e) {
			// renderManager.getOnDemandRenderer("ClienteView").requestRender();
			FacesUtils.addErrorMessage(e.getMessage());
			throw e;
		}

		return "";
	}
	
	public String abrirPantallaAdministrarProyecto(ActionEvent evt) {
		try {

			ProyectoDTO proyectoAdministrar = (ProyectoDTO) (evt.getComponent().getAttributes()
					.get("proyectoAdministrar"));
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);

			session.setAttribute("proyectoAdministrar", proyectoAdministrar);

			/* Metodo para redireccionar a cualquier parte de la app **/

			// FacesUtils.resetManagedBean("preguntaForoView");

			ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
			context.redirect(context.getRequestContextPath() + "/pantallas/administrarProyecto.xhtml");
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		return "";
	}
	
	public String abrirPantallaAdministrarRequerimientos(ActionEvent evt) {
		try {

			ProyectoDTO proyectoRequerimiento = (ProyectoDTO) (evt.getComponent().getAttributes()
					.get("proyectoRequerimiento"));
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);

			session.setAttribute("proyectoRequerimiento", proyectoRequerimiento);

			/* Metodo para redireccionar a cualquier parte de la app **/

			// FacesUtils.resetManagedBean("preguntaForoView");

			ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
			context.redirect(context.getRequestContextPath() + "/pantallas/gestionarRequerimientos.xhtml");
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		return "";
	}
	
	public String abrirDialogoEditarCliente(ActionEvent actionEvent) {
		try {
			ClienteDTO clienteDTO = (ClienteDTO) (actionEvent.getComponent().getAttributes()
					.get("clienteEditar"));
			clienteEditar = businessDelegatorView.getCliente(clienteDTO.getClieId());
			
			txtNitEditar.setValue(clienteEditar.getNit());
			txtNombreEmpresaEditar.setValue(clienteEditar.getNombreEmpresa());
			txtEnlaceWebEditar.setValue(clienteEditar.getEnlaceWeb());
			txtNombreContactoEditar.setValue(clienteEditar.getNombreContacto());
			txtEmailContactoEditar.setValue(clienteEditar.getEmailContacto());
			txtCelularContactoEditar.setValue(clienteEditar.getCelularContacto());
			txtTelefonoContactoEditar.setValue(clienteEditar.getTelefonoContacto());
			txtDireccionContactoEditar.setValue(clienteEditar.getDireccionContacto());

			cargarUbicacionEditar(clienteEditar.getCiudad().getCiudId());
			
			setShowEditarClientes(true);
			return "";

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
		
		
	}

	public String cerrarDialogoEditarCliente() {
		try {
			setShowEditarClientes(false);
			return "";

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
		
		
	}

	
	/*Getters and Setters*/


	public InputText getTxtCelularContacto() {
		return txtCelularContacto;
	}

	public void setTxtCelularContacto(InputText txtCelularContacto) {
		this.txtCelularContacto = txtCelularContacto;
	}

	public InputText getTxtDireccionContacto() {
		return txtDireccionContacto;
	}

	public void setTxtDireccionContacto(InputText txtDireccionContacto) {
		this.txtDireccionContacto = txtDireccionContacto;
	}

	public InputText getTxtEnlaceWeb() {
		return txtEnlaceWeb;
	}

	public void setTxtEnlaceWeb(InputText txtEnlaceWeb) {
		this.txtEnlaceWeb = txtEnlaceWeb;
	}

	public InputText getTxtNit() {
		return txtNit;
	}

	public void setTxtNit(InputText txtNit) {
		this.txtNit = txtNit;
	}

	public InputText getTxtNombreContacto() {
		return txtNombreContacto;
	}

	public void setTxtNombreContacto(InputText txtNombreContacto) {
		this.txtNombreContacto = txtNombreContacto;
	}

	public InputText getTxtNombreEmpresa() {
		return txtNombreEmpresa;
	}

	public void setTxtNombreEmpresa(InputText txtNombreEmpresa) {
		this.txtNombreEmpresa = txtNombreEmpresa;
	}

	public InputText getTxtTelefonoContacto() {
		return txtTelefonoContacto;
	}

	public void setTxtTelefonoContacto(InputText txtTelefonoContacto) {
		this.txtTelefonoContacto = txtTelefonoContacto;
	}

	public InputText getTxtClieId() {
		return txtClieId;
	}

	public void setTxtClieId(InputText txtClieId) {
		this.txtClieId = txtClieId;
	}

	public List<ClienteDTO> getData() {
		try {
			if (data == null) {
				data = businessDelegatorView.getDataCliente();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return data;
	}

	public void setData(List<ClienteDTO> clienteDTO) {
		this.data = clienteDTO;
	}

	public ClienteDTO getSelectedCliente() {
		return selectedCliente;
	}

	public void setSelectedCliente(ClienteDTO cliente) {
		this.selectedCliente = cliente;
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

	public CommandButton getBtnRegistrarNuevoCliente() {
		return btnRegistrarNuevoCliente;
	}

	public void setBtnRegistrarNuevoCliente(CommandButton btnRegistrarNuevoCliente) {
		this.btnRegistrarNuevoCliente = btnRegistrarNuevoCliente;
	}

	public CommandButton getBtnModificarCliente() {
		return btnModificarCliente;
	}

	public void setBtnModificarCliente(CommandButton btnModificarCliente) {
		this.btnModificarCliente = btnModificarCliente;
	}

	public CommandButton getBtnLimpiarPantalla() {
		return btnLimpiarPantalla;
	}

	public void setBtnLimpiarPantalla(CommandButton btnLimpiarPantalla) {
		this.btnLimpiarPantalla = btnLimpiarPantalla;
	}

	public SelectOneMenu getSomPaises() {
		return somPaises;
	}

	public void setSomPaises(SelectOneMenu somPaises) {
		this.somPaises = somPaises;
	}

	public SelectOneMenu getSomDepartamentos() {
		return somDepartamentos;
	}

	public void setSomDepartamentos(SelectOneMenu somDepartamentos) {
		this.somDepartamentos = somDepartamentos;
	}

	public SelectOneMenu getSomCiudades() {
		return somCiudades;
	}

	public void setSomCiudades(SelectOneMenu somCiudades) {
		this.somCiudades = somCiudades;
	}

	public List<SelectItem> getListaPaises() {
		try {
			if (listaPaises == null) {
				listaPaises = new ArrayList<SelectItem>();
				listaPaises.add(new SelectItem(Integer.parseInt("0"), "SELECCIONE UN PAIS"));
				List<Pais> lista = businessDelegatorView.getPais();
				for (Pais pais : lista) {
					listaPaises.add(new SelectItem(pais.getPaisId(), pais.getNombrePais()));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaPaises;
	}

	public void setListaPaises(List<SelectItem> listaPaises) {
		this.listaPaises = listaPaises;
	}

	public List<SelectItem> getListaDepartamentos() {
		try {
			if (listaDepartamentos == null) {
				listaDepartamentos = new ArrayList<SelectItem>();
				listaDepartamentos.add(new SelectItem(Integer.parseInt("0"), "SELECCIONE UN DEPARTAMENTO"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaDepartamentos;
	}

	public void setListaDepartamentos(List<SelectItem> listaDepartamentos) {
		this.listaDepartamentos = listaDepartamentos;
	}

	public List<SelectItem> getListaCiudades() {
		try {
			if (listaCiudades == null) {
				listaCiudades = new ArrayList<SelectItem>();
				listaCiudades.add(new SelectItem(Integer.parseInt("0"), "SELECCIONE UN MUNICIPIO"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaCiudades;
	}

	public void setListaCiudades(List<SelectItem> listaCiudades) {
		this.listaCiudades = listaCiudades;
	}

	public String getUsuarioIniciado() {
		return usuarioIniciado;
	}

	public void setUsuarioIniciado(String usuarioIniciado) {
		this.usuarioIniciado = usuarioIniciado;
	}

	public InputText getTxtEmailContacto() {
		return txtEmailContacto;
	}

	public void setTxtEmailContacto(InputText txtEmailContacto) {
		this.txtEmailContacto = txtEmailContacto;
	}

	public List<ProyectoDTO> getProyectosCliente() {
		return proyectosCliente;
	}

	public void setProyectosCliente(List<ProyectoDTO> proyectosCliente) {
		this.proyectosCliente = proyectosCliente;
	}

	public ClienteDTO getClienteSeleccionadoDTO() {
		return clienteSeleccionadoDTO;
	}

	public void setClienteSeleccionadoDTO(ClienteDTO clienteSeleccionadoDTO) {
		this.clienteSeleccionadoDTO = clienteSeleccionadoDTO;
	}

	// DPL 20160703 Método para crear un nuevo cliente.
	public String registrarNuevoCliente() {
		try {
			entity = new Cliente();

			entity.setCelularContacto(txtCelularContacto.getValue().toString());
			entity.setClieId(null);
			entity.setDireccionContacto(txtDireccionContacto.getValue().toString());
			entity.setEnlaceWeb(txtEnlaceWeb.getValue().toString());
			entity.setNit(txtNit.getValue().toString());
			entity.setNombreContacto(txtNombreContacto.getValue().toString());
			entity.setNombreEmpresa(txtNombreEmpresa.getValue().toString());
			entity.setTelefonoContacto(txtTelefonoContacto.getValue().toString());
			entity.setEmailContacto(txtEmailContacto.getValue().toString());
			
			Ciudad ciudad = businessDelegatorView.getCiudad(Integer.parseInt(somCiudades.getValue().toString()));
			entity.setCiudad(ciudad);
			
			Usuario usuario = businessDelegatorView.buscarUsuarioPorEmail(usuarioIniciado);
			entity.setUsuarioByUsuarioCreacion(usuario);
			
			businessDelegatorView.registrarNuevoCliente(entity);
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYSAVED);
			limpiarRegistrarNuevoCliente();
		} catch (Exception e) {
			entity = null;
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public void limpiarRegistrarNuevoCliente() {
		entity = null;
		txtCelularContacto.setValue(null);
		txtDireccionContacto.setValue(null);
		txtEnlaceWeb.setValue(null);
		txtNit.setValue(null);
		txtNombreContacto.setValue(null);
		txtNombreEmpresa.setValue(null);
		txtTelefonoContacto.setValue(null);
		txtEmailContacto.setValue(null);
		somCiudades.setValue(null);
		somDepartamentos.setValue(null);
		somPaises.setValue(null);
		

		txtCelularContacto.setDisabled(true);
		txtDireccionContacto.setDisabled(true);
		txtEnlaceWeb.setDisabled(true);
		txtNombreContacto.setDisabled(true);
		txtNombreEmpresa.setDisabled(true);
		txtTelefonoContacto.setDisabled(true);
		txtEmailContacto.setDisabled(true);
		txtNit.setDisabled(false);
		somCiudades.setDisabled(true);
		somDepartamentos.setDisabled(true);
		somPaises.setDisabled(true);

		btnLimpiarPantalla.setDisabled(true);
		btnModificarCliente.setDisabled(true);
		btnRegistrarNuevoCliente.setDisabled(true);

		FacesUtils.addInfoMessage("Pantalla limpia");
	}

	public void listenerNit() {
		try {
			String nit = txtNit.getValue().toString().trim();
			entity = (nit != null) ? businessDelegatorView.buscarClientePorNit(nit) : null;

			if (entity == null) {
				// DPL 20160806 Poner los campos del formulario en null y
				// habilitar
				// todos los campos para editar
				txtNit.setDisabled(false);
				txtCelularContacto.setValue(null);
				txtCelularContacto.setDisabled(false);
				txtDireccionContacto.setValue(null);
				txtDireccionContacto.setDisabled(false);
				txtEnlaceWeb.setValue(null);
				txtEnlaceWeb.setDisabled(false);
				txtNombreContacto.setValue(null);
				txtNombreContacto.setDisabled(false);
				txtNombreEmpresa.setValue(null);
				txtNombreEmpresa.setDisabled(false);
				txtTelefonoContacto.setValue(null);
				txtTelefonoContacto.setDisabled(false);
				txtEmailContacto.setDisabled(false);
				txtEmailContacto.setValue(null);
				somPaises.setDisabled(false);
				btnRegistrarNuevoCliente.setDisabled(false);
				btnModificarCliente.setDisabled(true);
				btnLimpiarPantalla.setDisabled(false);

			} else {
				btnRegistrarNuevoCliente.setDisabled(true);
				btnModificarCliente.setDisabled(false);

				txtNit.setDisabled(true);
				txtNombreEmpresa.setDisabled(true);
				txtEnlaceWeb.setDisabled(false);
				txtNombreContacto.setDisabled(false);
				txtCelularContacto.setDisabled(false);
				txtTelefonoContacto.setDisabled(false);
				txtDireccionContacto.setDisabled(false);
				txtEmailContacto.setDisabled(false);
				

				txtNit.setValue(entity.getNit());
				txtNombreEmpresa.setValue(entity.getNombreEmpresa());
				txtEnlaceWeb.setValue(entity.getEnlaceWeb());
				txtNombreContacto.setValue(entity.getNombreContacto());
				txtCelularContacto.setValue(entity.getCelularContacto());
				txtTelefonoContacto.setValue(entity.getTelefonoContacto());
				txtDireccionContacto.setValue(entity.getDireccionContacto());
				txtEmailContacto.setValue(entity.getEmailContacto());

				cargarUbicacion(entity.getCiudad().getCiudId());

			}

		} catch (Exception e) {
			entity = null;
		}

		btnLimpiarPantalla.setDisabled(false);

	}

	public void actualizarCliente() {
		try {
			try {
				String nit = txtNit.getValue().toString().trim();
				entity = (nit != null) ? businessDelegatorView.buscarClientePorNit(nit) : null;
			} catch (Exception e) {
				entity = null;
			}

			entity.setCelularContacto(txtCelularContacto.getValue().toString().trim());
			entity.setDireccionContacto(txtDireccionContacto.getValue().toString().trim());
			entity.setEnlaceWeb(txtEnlaceWeb.getValue().toString().trim());

			entity.setNombreContacto(txtNombreContacto.getValue().toString().trim());

			entity.setTelefonoContacto(txtTelefonoContacto.getValue().toString().trim());
			entity.setEmailContacto(txtEmailContacto.getValue().toString().trim());
			Ciudad ciudad = businessDelegatorView.getCiudad(Integer.parseInt(somCiudades.getValue().toString()));
			entity.setCiudad(ciudad);
			
			Usuario usuario = businessDelegatorView.buscarUsuarioPorEmail(usuarioIniciado);
			entity.setUsuarioByUsuarioModificacion(usuario);
			
			businessDelegatorView.updateCliente(entity);
			FacesUtils.addInfoMessage("Se ha actualizado los datos del cliente");
			limpiarRegistrarNuevoCliente();
		} catch (Exception e) {
			entity = null;
			FacesUtils.addErrorMessage(e.getMessage());
		}

	}

	public void listenerPaisSeleccionado() {
		try {
			somDepartamentos.setDisabled(false);
			getListaDepartamentos();
			List<Departamento> lista = businessDelegatorView
					.buscarDepartamentoPorPais(Integer.parseInt(somPaises.getValue().toString()));

			for (Departamento departamento : lista) {
				listaDepartamentos.add(new SelectItem(departamento.getDepaId(), departamento.getNombreDepartamento()));
			}
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void listenerDepartamentoSeleccionado() {
		try {
			somCiudades.setDisabled(false);
			getListaCiudades();
			List<Ciudad> lista = businessDelegatorView
					.buscarCiudadPorDepartamento(Integer.parseInt(somDepartamentos.getValue().toString()));

			for (Ciudad ciudad : lista) {
				listaCiudades.add(new SelectItem(ciudad.getCiudId(), ciudad.getNombreCiudad()));
			}
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}
	
	public void listenerEditarPaisSeleccionado() {
		try {
			somDepartamentosEditar.setDisabled(false);
			getListaDepartamentos();
			List<Departamento> lista = businessDelegatorView
					.buscarDepartamentoPorPais(Integer.parseInt(somPaisesEditar.getValue().toString()));

			for (Departamento departamento : lista) {
				listaDepartamentos.add(new SelectItem(departamento.getDepaId(), departamento.getNombreDepartamento()));
			}
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void listenerEditarDepartamentoSeleccionado() {
		try {
			somCiudadesEditar.setDisabled(false);
			getListaCiudades();
			List<Ciudad> lista = businessDelegatorView
					.buscarCiudadPorDepartamento(Integer.parseInt(somDepartamentosEditar.getValue().toString()));

			for (Ciudad ciudad : lista) {
				listaCiudades.add(new SelectItem(ciudad.getCiudId(), ciudad.getNombreCiudad()));
			}
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	
	public void cargarUbicacion(Integer ciudId){
		try {
			Ciudad ciudad = businessDelegatorView.getCiudad(ciudId);
			Departamento departamento = businessDelegatorView.getDepartamento(ciudad.getDepartamento().getDepaId());
			Pais pais = businessDelegatorView.getPais(departamento.getPais().getPaisId());
			
			somPaises.setDisabled(false);
			somDepartamentos.setDisabled(false);
			somCiudades.setDisabled(false);
			listaPaises = null;
			listaDepartamentos = null;
			listaCiudades = null;
			
			getListaPaises();
			somPaises.setValue(pais.getPaisId());
			
			listenerPaisSeleccionado();
			somDepartamentos.setValue(departamento.getDepaId());
			
			listenerDepartamentoSeleccionado();
			somCiudades.setValue(ciudad.getCiudId());
			
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}
	
	public void cargarUbicacionEditar(Integer ciudId){
		try {
			Ciudad ciudad = businessDelegatorView.getCiudad(ciudId);
			Departamento departamento = businessDelegatorView.getDepartamento(ciudad.getDepartamento().getDepaId());
			Pais pais = businessDelegatorView.getPais(departamento.getPais().getPaisId());
			
			somPaisesEditar.setDisabled(false);
			somDepartamentosEditar.setDisabled(false);
			somCiudadesEditar.setDisabled(false);
			listaPaises = null;
			listaDepartamentos = null;
			listaCiudades = null;
			
			getListaPaises();
			somPaisesEditar.setValue(pais.getPaisId());
			
			listenerEditarPaisSeleccionado();
			somDepartamentosEditar.setValue(departamento.getDepaId());
			
			listenerEditarDepartamentoSeleccionado();
			somCiudadesEditar.setValue(ciudad.getCiudId());
			
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}
	
	public String cargarProyectosDadoCliente(ActionEvent evt) {
		try {
			clienteSeleccionadoDTO = (ClienteDTO) (evt.getComponent().getAttributes().get("clienteCargarProyectos"));
			this.proyectosCliente = businessDelegatorView.listaProyectosDTODadoCliente(clienteSeleccionadoDTO.getClieId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public List<ClienteDTO> getClientesConCiudad() {
		try {
			if(clientesConCiudad == null){
				clientesConCiudad = businessDelegatorView.listaClientesDTOConCiudad();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return clientesConCiudad;
	}

	public void setClientesConCiudad(List<ClienteDTO> clientesConCiudad) {
		this.clientesConCiudad = clientesConCiudad;
	}

	public boolean isShowEditarClientes() {
		return showEditarClientes;
	}

	public void setShowEditarClientes(boolean showEditarClientes) {
		this.showEditarClientes = showEditarClientes;
	}

	public InputText getTxtNitEditar() {
		return txtNitEditar;
	}

	public void setTxtNitEditar(InputText txtNitEditar) {
		this.txtNitEditar = txtNitEditar;
	}

	public InputText getTxtNombreEmpresaEditar() {
		return txtNombreEmpresaEditar;
	}

	public void setTxtNombreEmpresaEditar(InputText txtNombreEmpresaEditar) {
		this.txtNombreEmpresaEditar = txtNombreEmpresaEditar;
	}

	public InputText getTxtEnlaceWebEditar() {
		return txtEnlaceWebEditar;
	}

	public void setTxtEnlaceWebEditar(InputText txtEnlaceWebEditar) {
		this.txtEnlaceWebEditar = txtEnlaceWebEditar;
	}

	public InputText getTxtNombreContactoEditar() {
		return txtNombreContactoEditar;
	}

	public void setTxtNombreContactoEditar(InputText txtNombreContactoEditar) {
		this.txtNombreContactoEditar = txtNombreContactoEditar;
	}

	public InputText getTxtEmailContactoEditar() {
		return txtEmailContactoEditar;
	}

	public void setTxtEmailContactoEditar(InputText txtEmailContactoEditar) {
		this.txtEmailContactoEditar = txtEmailContactoEditar;
	}

	public InputText getTxtCelularContactoEditar() {
		return txtCelularContactoEditar;
	}

	public void setTxtCelularContactoEditar(InputText txtCelularContactoEditar) {
		this.txtCelularContactoEditar = txtCelularContactoEditar;
	}

	public InputText getTxtTelefonoContactoEditar() {
		return txtTelefonoContactoEditar;
	}

	public void setTxtTelefonoContactoEditar(InputText txtTelefonoContactoEditar) {
		this.txtTelefonoContactoEditar = txtTelefonoContactoEditar;
	}

	public InputText getTxtDireccionContactoEditar() {
		return txtDireccionContactoEditar;
	}

	public void setTxtDireccionContactoEditar(InputText txtDireccionContactoEditar) {
		this.txtDireccionContactoEditar = txtDireccionContactoEditar;
	}

	public SelectOneMenu getSomPaisesEditar() {
		return somPaisesEditar;
	}

	public void setSomPaisesEditar(SelectOneMenu somPaisesEditar) {
		this.somPaisesEditar = somPaisesEditar;
	}

	public SelectOneMenu getSomDepartamentosEditar() {
		return somDepartamentosEditar;
	}

	public void setSomDepartamentosEditar(SelectOneMenu somDepartamentosEditar) {
		this.somDepartamentosEditar = somDepartamentosEditar;
	}

	public SelectOneMenu getSomCiudadesEditar() {
		return somCiudadesEditar;
	}

	public void setSomCiudadesEditar(SelectOneMenu somCiudadesEditar) {
		this.somCiudadesEditar = somCiudadesEditar;
	}
}
