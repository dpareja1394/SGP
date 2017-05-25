package com.dsdsoft.sgp.presentation.backingBeans;

import com.dsdsoft.sgp.exceptions.*;
import com.dsdsoft.sgp.modelo.*;
import com.dsdsoft.sgp.modelo.dto.UsuarioDTO;
import com.dsdsoft.sgp.presentation.businessDelegate.*;
import com.dsdsoft.sgp.utilities.*;

import org.primefaces.component.calendar.*;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.password.Password;
import org.primefaces.event.RowEditEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

import java.sql.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;


/**
 * @author Zathura Code Generator http://code.google.com/p/zathura
 * www.zathuracode.org
 *
 */
@ManagedBean
@ViewScoped
public class UsuarioView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(UsuarioView.class);
    private InputText txtApellidoUsuario;
    private InputText txtEmailUsuario;
    private InputText txtEstadoRegistroUsuario;
    private InputText txtNombreUsuario;
    private InputText txtNudocUsuario;
    private InputText txtPasswordUsuario;
    private InputText txtUsuaId;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<UsuarioDTO> data;
    private UsuarioDTO selectedUsuario;
    private Usuario entity;
    private boolean showDialog;
    
    private Password pswPasswordUsuario, pswPasswordUsuarioConfirma;
    
    //DPL 20160701 cadena para saludar al usuario que inició sesión
    private String saludoUsuario;
    private String nombreProyecto = "Sistema de Gestión de Proyectos";
    
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public UsuarioView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            UsuarioDTO usuarioDTO = (UsuarioDTO) e.getObject();

            if (txtApellidoUsuario == null) {
                txtApellidoUsuario = new InputText();
            }

            txtApellidoUsuario.setValue(usuarioDTO.getApellidoUsuario());

            if (txtEmailUsuario == null) {
                txtEmailUsuario = new InputText();
            }

            txtEmailUsuario.setValue(usuarioDTO.getEmailUsuario());

            if (txtEstadoRegistroUsuario == null) {
                txtEstadoRegistroUsuario = new InputText();
            }

            txtEstadoRegistroUsuario.setValue(usuarioDTO.getEstadoRegistroUsuario());

            if (txtNombreUsuario == null) {
                txtNombreUsuario = new InputText();
            }

            txtNombreUsuario.setValue(usuarioDTO.getNombreUsuario());

            if (txtNudocUsuario == null) {
                txtNudocUsuario = new InputText();
            }

            txtNudocUsuario.setValue(usuarioDTO.getNudocUsuario());

            if (txtPasswordUsuario == null) {
                txtPasswordUsuario = new InputText();
            }

            txtPasswordUsuario.setValue(usuarioDTO.getPasswordUsuario());

            if (txtUsuaId == null) {
                txtUsuaId = new InputText();
            }

            txtUsuaId.setValue(usuarioDTO.getUsuaId());

            Integer usuaId = FacesUtils.checkInteger(txtUsuaId);
            entity = businessDelegatorView.getUsuario(usuaId);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedUsuario = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedUsuario = null;

        if (txtApellidoUsuario != null) {
            txtApellidoUsuario.setValue(null);
            txtApellidoUsuario.setDisabled(true);
        }

        if (txtEmailUsuario != null) {
            txtEmailUsuario.setValue(null);
            txtEmailUsuario.setDisabled(true);
        }

        if (txtEstadoRegistroUsuario != null) {
            txtEstadoRegistroUsuario.setValue(null);
            txtEstadoRegistroUsuario.setDisabled(true);
        }

        if (txtNombreUsuario != null) {
            txtNombreUsuario.setValue(null);
            txtNombreUsuario.setDisabled(true);
        }

        if (txtNudocUsuario != null) {
            txtNudocUsuario.setValue(null);
            txtNudocUsuario.setDisabled(true);
        }

        if (txtPasswordUsuario != null) {
            txtPasswordUsuario.setValue(null);
            txtPasswordUsuario.setDisabled(true);
        }

        if (txtUsuaId != null) {
            txtUsuaId.setValue(null);
            txtUsuaId.setDisabled(false);
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
            Integer usuaId = FacesUtils.checkInteger(txtUsuaId);
            entity = (usuaId != null)
                ? businessDelegatorView.getUsuario(usuaId) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtApellidoUsuario.setDisabled(false);
            txtEmailUsuario.setDisabled(false);
            txtEstadoRegistroUsuario.setDisabled(false);
            txtNombreUsuario.setDisabled(false);
            txtNudocUsuario.setDisabled(false);
            txtPasswordUsuario.setDisabled(false);
            txtUsuaId.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtApellidoUsuario.setValue(entity.getApellidoUsuario());
            txtApellidoUsuario.setDisabled(false);
            txtEmailUsuario.setValue(entity.getEmailUsuario());
            txtEmailUsuario.setDisabled(false);
            txtEstadoRegistroUsuario.setValue(entity.getEstadoRegistroUsuario());
            txtEstadoRegistroUsuario.setDisabled(false);
            txtNombreUsuario.setValue(entity.getNombreUsuario());
            txtNombreUsuario.setDisabled(false);
            txtNudocUsuario.setValue(entity.getNudocUsuario());
            txtNudocUsuario.setDisabled(false);
            txtPasswordUsuario.setValue(entity.getPasswordUsuario());
            txtPasswordUsuario.setDisabled(false);
            txtUsuaId.setValue(entity.getUsuaId());
            txtUsuaId.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedUsuario = (UsuarioDTO) (evt.getComponent().getAttributes()
                                           .get("selectedUsuario"));
        txtApellidoUsuario.setValue(selectedUsuario.getApellidoUsuario());
        txtApellidoUsuario.setDisabled(false);
        txtEmailUsuario.setValue(selectedUsuario.getEmailUsuario());
        txtEmailUsuario.setDisabled(false);
        txtEstadoRegistroUsuario.setValue(selectedUsuario.getEstadoRegistroUsuario());
        txtEstadoRegistroUsuario.setDisabled(false);
        txtNombreUsuario.setValue(selectedUsuario.getNombreUsuario());
        txtNombreUsuario.setDisabled(false);
        txtNudocUsuario.setValue(selectedUsuario.getNudocUsuario());
        txtNudocUsuario.setDisabled(false);
        txtPasswordUsuario.setValue(selectedUsuario.getPasswordUsuario());
        txtPasswordUsuario.setDisabled(false);
        txtUsuaId.setValue(selectedUsuario.getUsuaId());
        txtUsuaId.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedUsuario == null) && (entity == null)) {
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
            entity = new Usuario();

            Integer usuaId = FacesUtils.checkInteger(txtUsuaId);

            entity.setApellidoUsuario(FacesUtils.checkString(txtApellidoUsuario));
            entity.setEmailUsuario(FacesUtils.checkString(txtEmailUsuario));
            entity.setEstadoRegistroUsuario(FacesUtils.checkString(
                    txtEstadoRegistroUsuario));
            entity.setNombreUsuario(FacesUtils.checkString(txtNombreUsuario));
            entity.setNudocUsuario(FacesUtils.checkString(txtNudocUsuario));
            entity.setPasswordUsuario(FacesUtils.checkString(txtPasswordUsuario));
            entity.setUsuaId(usuaId);
            businessDelegatorView.saveUsuario(entity);
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
                Integer usuaId = new Integer(selectedUsuario.getUsuaId());
                entity = businessDelegatorView.getUsuario(usuaId);
            }

            entity.setApellidoUsuario(FacesUtils.checkString(txtApellidoUsuario));
            entity.setEmailUsuario(FacesUtils.checkString(txtEmailUsuario));
            entity.setEstadoRegistroUsuario(FacesUtils.checkString(
                    txtEstadoRegistroUsuario));
            entity.setNombreUsuario(FacesUtils.checkString(txtNombreUsuario));
            entity.setNudocUsuario(FacesUtils.checkString(txtNudocUsuario));
            entity.setPasswordUsuario(FacesUtils.checkString(txtPasswordUsuario));
            businessDelegatorView.updateUsuario(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedUsuario = (UsuarioDTO) (evt.getComponent().getAttributes()
                                               .get("selectedUsuario"));

            Integer usuaId = new Integer(selectedUsuario.getUsuaId());
            entity = businessDelegatorView.getUsuario(usuaId);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Integer usuaId = FacesUtils.checkInteger(txtUsuaId);
            entity = businessDelegatorView.getUsuario(usuaId);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteUsuario(entity);
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
            selectedUsuario = (UsuarioDTO) (evt.getComponent().getAttributes()
                                               .get("selectedUsuario"));

            Integer usuaId = new Integer(selectedUsuario.getUsuaId());
            entity = businessDelegatorView.getUsuario(usuaId);
            businessDelegatorView.deleteUsuario(entity);
            data.remove(selectedUsuario);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(String apellidoUsuario,
        String emailUsuario, String estadoRegistroUsuario,
        String nombreUsuario, String nudocUsuario, String passwordUsuario,
        Integer usuaId) throws Exception {
        try {
            entity.setApellidoUsuario(FacesUtils.checkString(apellidoUsuario));
            entity.setEmailUsuario(FacesUtils.checkString(emailUsuario));
            entity.setEstadoRegistroUsuario(FacesUtils.checkString(
                    estadoRegistroUsuario));
            entity.setNombreUsuario(FacesUtils.checkString(nombreUsuario));
            entity.setNudocUsuario(FacesUtils.checkString(nudocUsuario));
            entity.setPasswordUsuario(FacesUtils.checkString(passwordUsuario));
            businessDelegatorView.updateUsuario(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("UsuarioView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtApellidoUsuario() {
        return txtApellidoUsuario;
    }

    public void setTxtApellidoUsuario(InputText txtApellidoUsuario) {
        this.txtApellidoUsuario = txtApellidoUsuario;
    }

    public InputText getTxtEmailUsuario() {
        return txtEmailUsuario;
    }

    public void setTxtEmailUsuario(InputText txtEmailUsuario) {
        this.txtEmailUsuario = txtEmailUsuario;
    }

    public InputText getTxtEstadoRegistroUsuario() {
        return txtEstadoRegistroUsuario;
    }

    public void setTxtEstadoRegistroUsuario(InputText txtEstadoRegistroUsuario) {
        this.txtEstadoRegistroUsuario = txtEstadoRegistroUsuario;
    }

    public InputText getTxtNombreUsuario() {
        return txtNombreUsuario;
    }

    public void setTxtNombreUsuario(InputText txtNombreUsuario) {
        this.txtNombreUsuario = txtNombreUsuario;
    }

    public InputText getTxtNudocUsuario() {
        return txtNudocUsuario;
    }

    public void setTxtNudocUsuario(InputText txtNudocUsuario) {
        this.txtNudocUsuario = txtNudocUsuario;
    }

    public InputText getTxtPasswordUsuario() {
        return txtPasswordUsuario;
    }

    public void setTxtPasswordUsuario(InputText txtPasswordUsuario) {
        this.txtPasswordUsuario = txtPasswordUsuario;
    }

    public InputText getTxtUsuaId() {
        return txtUsuaId;
    }

    public void setTxtUsuaId(InputText txtUsuaId) {
        this.txtUsuaId = txtUsuaId;
    }

    public List<UsuarioDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataUsuario();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<UsuarioDTO> usuarioDTO) {
        this.data = usuarioDTO;
    }

    public UsuarioDTO getSelectedUsuario() {
        return selectedUsuario;
    }

    public void setSelectedUsuario(UsuarioDTO usuario) {
        this.selectedUsuario = usuario;
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

    public void setBusinessDelegatorView(
        IBusinessDelegatorView businessDelegatorView) {
        this.businessDelegatorView = businessDelegatorView;
    }

    public boolean isShowDialog() {
        return showDialog;
    }

    public void setShowDialog(boolean showDialog) {
        this.showDialog = showDialog;
    }

	public Password getPswPasswordUsuario() {
		return pswPasswordUsuario;
	}

	public void setPswPasswordUsuario(Password pswPasswordUsuario) {
		this.pswPasswordUsuario = pswPasswordUsuario;
	}

	public Password getPswPasswordUsuarioConfirma() {
		return pswPasswordUsuarioConfirma;
	}

	public void setPswPasswordUsuarioConfirma(Password pswPasswordUsuarioConfirma) {
		this.pswPasswordUsuarioConfirma = pswPasswordUsuarioConfirma;
	}
	
	//DPL 20160628 Agregado para registrar un nuevo usuario.
	public String registroNuevoUsuario(){
		try {
			String psw1, pswConfirma;
			psw1 = FacesUtils.checkString(pswPasswordUsuario);
			pswConfirma = FacesUtils.checkString(pswPasswordUsuarioConfirma);
			//DPL 20160627 Validación para que solo guarde si las contraseñas coinciden.
			if(!psw1.equals(pswConfirma)){
				FacesUtils.addErrorMessage("Las contraseñas no coinciden");
			}else{
				Usuario usuario = new Usuario();
				usuario.setUsuaId(null);
				usuario.setApellidoUsuario(FacesUtils.checkString(txtApellidoUsuario).toUpperCase());
				usuario.setEmailUsuario(FacesUtils.checkString(txtEmailUsuario).toLowerCase());
				usuario.setEstadoRegistroUsuario("A");
				usuario.setNombreUsuario(FacesUtils.checkString(txtNombreUsuario).toUpperCase());
				usuario.setNudocUsuario(FacesUtils.checkString(txtNudocUsuario));
				usuario.setPasswordUsuario(pswConfirma);
				
				businessDelegatorView.saveUsuario(usuario);
				FacesUtils.addInfoMessage("Se ha guardado el usuario con email: "+usuario.getEmailUsuario());

				String mensaje = "registrado";
				Thread.sleep(5000);
				return mensaje;
				
			}
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
		return "";
	}
	
	/**
	 * @author DPAREJA
	 * @date 25 de Mayo 2017
	 */
	public void limpiarPantallaCreacionUsuario(){
		txtApellidoUsuario.setValue(null);
		txtNombreUsuario.setValue(null);
		txtNudocUsuario.setValue(null);
		txtEmailUsuario.setValue(null);
		pswPasswordUsuario.setValue(null);
		pswPasswordUsuarioConfirma.setValue(null);
		
	}


	//DPL 20160703 Método para obtener el nombre para el saludo en la página de inicio
	public String getSaludoUsuario() {
		try {
			saludoUsuario = new String("");
			String emailUsuario = FacesUtils.getHttpSession(true).getAttribute("usuario_iniciado").toString();
			Object[] variables = { "emailUsuario", true, emailUsuario.trim(), "="};
			
			List<Usuario> usuarios = businessDelegatorView.findByCriteriaInUsuario(variables, null, null);
			String nombreCompleto = usuarios.get(0).getNombreUsuario()+" "+usuarios.get(0).getApellidoUsuario();
			saludoUsuario = nombreCompleto+", "+emailUsuario;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return saludoUsuario;
	}

	public void setSaludoUsuario(String saludoUsuario) {
		this.saludoUsuario = saludoUsuario;
	}

	public String getNombreProyecto() {
		return nombreProyecto;
	}

	public void setNombreProyecto(String nombreProyecto) {
		this.nombreProyecto = nombreProyecto;
	}
}
