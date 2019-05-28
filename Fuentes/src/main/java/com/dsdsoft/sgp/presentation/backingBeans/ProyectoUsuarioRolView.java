package com.dsdsoft.sgp.presentation.backingBeans;

import com.dsdsoft.sgp.exceptions.*;
import com.dsdsoft.sgp.modelo.*;
import com.dsdsoft.sgp.modelo.dto.ProyectoUsuarioRolDTO;
import com.dsdsoft.sgp.modelo.dto.RolDTO;
import com.dsdsoft.sgp.presentation.businessDelegate.*;
import com.dsdsoft.sgp.utilities.*;

import org.primefaces.component.calendar.*;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;

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
import javax.faces.model.SelectItem;


/**
 * @author Zathura Code Generator http://code.google.com/p/zathura
 * www.zathuracode.org
 *
 */
@ManagedBean
@ViewScoped
public class ProyectoUsuarioRolView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(ProyectoUsuarioRolView.class);
    private InputText txtProyId_Proyecto;
    private InputText txtRolId_Rol;
    private InputText txtUsuaId_Usuario;
    private InputText txtUsuaId;
    private InputText txtProyId;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<ProyectoUsuarioRolDTO> data;
    private ProyectoUsuarioRolDTO selectedProyectoUsuarioRol;
    private ProyectoUsuarioRol entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;
    
    private List<SelectItem> listaRoles;

    public ProyectoUsuarioRolView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            ProyectoUsuarioRolDTO proyectoUsuarioRolDTO = (ProyectoUsuarioRolDTO) e.getObject();

            if (txtProyId_Proyecto == null) {
                txtProyId_Proyecto = new InputText();
            }

            txtProyId_Proyecto.setValue(proyectoUsuarioRolDTO.getProyId_Proyecto());

            if (txtRolId_Rol == null) {
                txtRolId_Rol = new InputText();
            }

            txtRolId_Rol.setValue(proyectoUsuarioRolDTO.getRolId_Rol());

            if (txtUsuaId_Usuario == null) {
                txtUsuaId_Usuario = new InputText();
            }

            txtUsuaId_Usuario.setValue(proyectoUsuarioRolDTO.getUsuaId_Usuario());

            if (txtUsuaId == null) {
                txtUsuaId = new InputText();
            }

            txtUsuaId.setValue(proyectoUsuarioRolDTO.getUsuaId());

            if (txtProyId == null) {
                txtProyId = new InputText();
            }

            txtProyId.setValue(proyectoUsuarioRolDTO.getProyId());

            ProyectoUsuarioRolId id = new ProyectoUsuarioRolId();
            id.setUsuaId((((txtUsuaId.getValue()) == null) ||
                (txtUsuaId.getValue()).equals("")) ? null
                                                   : FacesUtils.checkLong(
                    txtUsuaId));
            id.setProyId((((txtProyId.getValue()) == null) ||
                (txtProyId.getValue()).equals("")) ? null
                                                   : FacesUtils.checkLong(
                    txtProyId));
            entity = businessDelegatorView.getProyectoUsuarioRol(id);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedProyectoUsuarioRol = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedProyectoUsuarioRol = null;

        if (txtProyId_Proyecto != null) {
            txtProyId_Proyecto.setValue(null);
            txtProyId_Proyecto.setDisabled(true);
        }

        if (txtRolId_Rol != null) {
            txtRolId_Rol.setValue(null);
            txtRolId_Rol.setDisabled(true);
        }

        if (txtUsuaId_Usuario != null) {
            txtUsuaId_Usuario.setValue(null);
            txtUsuaId_Usuario.setDisabled(true);
        }

        if (txtUsuaId != null) {
            txtUsuaId.setValue(null);
            txtUsuaId.setDisabled(false);
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
            ProyectoUsuarioRolId id = new ProyectoUsuarioRolId();
            id.setUsuaId((((txtUsuaId.getValue()) == null) ||
                (txtUsuaId.getValue()).equals("")) ? null
                                                   : FacesUtils.checkLong(
                    txtUsuaId));
            id.setProyId((((txtProyId.getValue()) == null) ||
                (txtProyId.getValue()).equals("")) ? null
                                                   : FacesUtils.checkLong(
                    txtProyId));
            entity = (id != null)
                ? businessDelegatorView.getProyectoUsuarioRol(id) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtProyId_Proyecto.setDisabled(false);
            txtRolId_Rol.setDisabled(false);
            txtUsuaId_Usuario.setDisabled(false);
            txtUsuaId.setDisabled(false);
            txtProyId.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtProyId_Proyecto.setValue(entity.getProyecto().getProyId());
            txtProyId_Proyecto.setDisabled(false);
            txtRolId_Rol.setValue(entity.getRol().getRolId());
            txtRolId_Rol.setDisabled(false);
            txtUsuaId_Usuario.setValue(entity.getUsuario().getUsuaId());
            txtUsuaId_Usuario.setDisabled(false);
            txtUsuaId.setValue(entity.getId().getUsuaId());
            txtUsuaId.setDisabled(true);
            txtProyId.setValue(entity.getId().getProyId());
            txtProyId.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedProyectoUsuarioRol = (ProyectoUsuarioRolDTO) (evt.getComponent()
                                                                 .getAttributes()
                                                                 .get("selectedProyectoUsuarioRol"));
        txtProyId_Proyecto.setValue(selectedProyectoUsuarioRol.getProyId_Proyecto());
        txtProyId_Proyecto.setDisabled(false);
        txtRolId_Rol.setValue(selectedProyectoUsuarioRol.getRolId_Rol());
        txtRolId_Rol.setDisabled(false);
        txtUsuaId_Usuario.setValue(selectedProyectoUsuarioRol.getUsuaId_Usuario());
        txtUsuaId_Usuario.setDisabled(false);
        txtUsuaId.setValue(selectedProyectoUsuarioRol.getUsuaId());
        txtUsuaId.setDisabled(true);
        txtProyId.setValue(selectedProyectoUsuarioRol.getProyId());
        txtProyId.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedProyectoUsuarioRol == null) && (entity == null)) {
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
            entity = new ProyectoUsuarioRol();

            ProyectoUsuarioRolId id = new ProyectoUsuarioRolId();
            id.setUsuaId((((txtUsuaId.getValue()) == null) ||
                (txtUsuaId.getValue()).equals("")) ? null
                                                   : FacesUtils.checkLong(
                    txtUsuaId));
            id.setProyId((((txtProyId.getValue()) == null) ||
                (txtProyId.getValue()).equals("")) ? null
                                                   : FacesUtils.checkLong(
                    txtProyId));

            entity.setId(id);
//            entity.setProyecto(businessDelegatorView.getProyecto(
//                    entity.getId().getProyId()));
//            entity.setUsuario(businessDelegatorView.getUsuario(
//                    entity.getId().getUsuaId()));
            entity.setRol((FacesUtils.checkInteger(txtRolId_Rol) != null)
                ? businessDelegatorView.getRol(FacesUtils.checkInteger(
                        txtRolId_Rol)) : null);
            businessDelegatorView.saveProyectoUsuarioRol(entity);
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
                ProyectoUsuarioRolId id = new ProyectoUsuarioRolId();
                id.setUsuaId(selectedProyectoUsuarioRol.getUsuaId());
                id.setProyId(selectedProyectoUsuarioRol.getProyId());
                entity = businessDelegatorView.getProyectoUsuarioRol(id);
            }

            entity.setRol((FacesUtils.checkInteger(txtRolId_Rol) != null)
                ? businessDelegatorView.getRol(FacesUtils.checkInteger(
                        txtRolId_Rol)) : null);
            businessDelegatorView.updateProyectoUsuarioRol(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedProyectoUsuarioRol = (ProyectoUsuarioRolDTO) (evt.getComponent()
                                                                     .getAttributes()
                                                                     .get("selectedProyectoUsuarioRol"));

            ProyectoUsuarioRolId id = new ProyectoUsuarioRolId();
            id.setUsuaId(selectedProyectoUsuarioRol.getUsuaId());
            id.setProyId(selectedProyectoUsuarioRol.getProyId());
            entity = businessDelegatorView.getProyectoUsuarioRol(id);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            ProyectoUsuarioRolId id = new ProyectoUsuarioRolId();
            id.setUsuaId((((txtUsuaId.getValue()) == null) ||
                (txtUsuaId.getValue()).equals("")) ? null
                                                   : FacesUtils.checkLong(
                    txtUsuaId));
            id.setProyId((((txtProyId.getValue()) == null) ||
                (txtProyId.getValue()).equals("")) ? null
                                                   : FacesUtils.checkLong(
                    txtProyId));
            entity = businessDelegatorView.getProyectoUsuarioRol(id);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteProyectoUsuarioRol(entity);
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
            selectedProyectoUsuarioRol = (ProyectoUsuarioRolDTO) (evt.getComponent()
                                                                     .getAttributes()
                                                                     .get("selectedProyectoUsuarioRol"));

            ProyectoUsuarioRolId id = new ProyectoUsuarioRolId();
            id.setUsuaId(selectedProyectoUsuarioRol.getUsuaId());
            id.setProyId(selectedProyectoUsuarioRol.getProyId());
            entity = businessDelegatorView.getProyectoUsuarioRol(id);
            businessDelegatorView.deleteProyectoUsuarioRol(entity);
            data.remove(selectedProyectoUsuarioRol);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(Long usuaId, Long proyId,
        Integer proyId_Proyecto, Integer rolId_Rol, Integer usuaId_Usuario)
        throws Exception {
        try {
            businessDelegatorView.updateProyectoUsuarioRol(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("ProyectoUsuarioRolView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtProyId_Proyecto() {
        return txtProyId_Proyecto;
    }

    public void setTxtProyId_Proyecto(InputText txtProyId_Proyecto) {
        this.txtProyId_Proyecto = txtProyId_Proyecto;
    }

    public InputText getTxtRolId_Rol() {
        return txtRolId_Rol;
    }

    public void setTxtRolId_Rol(InputText txtRolId_Rol) {
        this.txtRolId_Rol = txtRolId_Rol;
    }

    public InputText getTxtUsuaId_Usuario() {
        return txtUsuaId_Usuario;
    }

    public void setTxtUsuaId_Usuario(InputText txtUsuaId_Usuario) {
        this.txtUsuaId_Usuario = txtUsuaId_Usuario;
    }

    public InputText getTxtUsuaId() {
        return txtUsuaId;
    }

    public void setTxtUsuaId(InputText txtUsuaId) {
        this.txtUsuaId = txtUsuaId;
    }

    public InputText getTxtProyId() {
        return txtProyId;
    }

    public void setTxtProyId(InputText txtProyId) {
        this.txtProyId = txtProyId;
    }

    public List<ProyectoUsuarioRolDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataProyectoUsuarioRol();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<ProyectoUsuarioRolDTO> proyectoUsuarioRolDTO) {
        this.data = proyectoUsuarioRolDTO;
    }

    public ProyectoUsuarioRolDTO getSelectedProyectoUsuarioRol() {
        return selectedProyectoUsuarioRol;
    }

    public void setSelectedProyectoUsuarioRol(
        ProyectoUsuarioRolDTO proyectoUsuarioRol) {
        this.selectedProyectoUsuarioRol = proyectoUsuarioRol;
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

	public List<SelectItem> getListaRoles() {
		try {
			if(listaRoles == null){
				listaRoles = new ArrayList<SelectItem>();
				List<RolDTO> listaRolesDto = businessDelegatorView.listaRolesDTOOrdenadaPorDescripcionAscendente();
				for (RolDTO rolDTO : listaRolesDto) {
					listaRoles.add(new SelectItem(rolDTO.getRolId(), rolDTO.getDescRol()));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaRoles;
	}

	public void setListaRoles(List<SelectItem> listaRoles) {
		this.listaRoles = listaRoles;
	}
}
