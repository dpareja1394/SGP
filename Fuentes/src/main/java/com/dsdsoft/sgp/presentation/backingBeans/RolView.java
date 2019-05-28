package com.dsdsoft.sgp.presentation.backingBeans;

import com.dsdsoft.sgp.exceptions.*;
import com.dsdsoft.sgp.modelo.*;
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


/**
 * @author Zathura Code Generator http://code.google.com/p/zathura
 * www.zathuracode.org
 *
 */
@ManagedBean
@ViewScoped
public class RolView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(RolView.class);
    private InputText txtDescRol;
    private InputText txtNombreCorto;
    private InputText txtRolId;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<RolDTO> data;
    private RolDTO selectedRol;
    private Rol entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public RolView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            RolDTO rolDTO = (RolDTO) e.getObject();

            if (txtDescRol == null) {
                txtDescRol = new InputText();
            }

            txtDescRol.setValue(rolDTO.getDescRol());

            if (txtNombreCorto == null) {
                txtNombreCorto = new InputText();
            }

            txtNombreCorto.setValue(rolDTO.getNombreCorto());

            if (txtRolId == null) {
                txtRolId = new InputText();
            }

            txtRolId.setValue(rolDTO.getRolId());

            Integer rolId = FacesUtils.checkInteger(txtRolId);
            entity = businessDelegatorView.getRol(rolId);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedRol = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedRol = null;

        if (txtDescRol != null) {
            txtDescRol.setValue(null);
            txtDescRol.setDisabled(true);
        }

        if (txtNombreCorto != null) {
            txtNombreCorto.setValue(null);
            txtNombreCorto.setDisabled(true);
        }

        if (txtRolId != null) {
            txtRolId.setValue(null);
            txtRolId.setDisabled(false);
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
            Integer rolId = FacesUtils.checkInteger(txtRolId);
            entity = (rolId != null) ? businessDelegatorView.getRol(rolId) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtDescRol.setDisabled(false);
            txtNombreCorto.setDisabled(false);
            txtRolId.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtDescRol.setValue(entity.getDescRol());
            txtDescRol.setDisabled(false);
            txtNombreCorto.setValue(entity.getNombreCorto());
            txtNombreCorto.setDisabled(false);
            txtRolId.setValue(entity.getRolId());
            txtRolId.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedRol = (RolDTO) (evt.getComponent().getAttributes()
                                   .get("selectedRol"));
        txtDescRol.setValue(selectedRol.getDescRol());
        txtDescRol.setDisabled(false);
        txtNombreCorto.setValue(selectedRol.getNombreCorto());
        txtNombreCorto.setDisabled(false);
        txtRolId.setValue(selectedRol.getRolId());
        txtRolId.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedRol == null) && (entity == null)) {
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
            entity = new Rol();

            Integer rolId = FacesUtils.checkInteger(txtRolId);

            entity.setDescRol(FacesUtils.checkString(txtDescRol));
            entity.setNombreCorto(FacesUtils.checkString(txtNombreCorto));
            entity.setRolId(rolId);
            businessDelegatorView.saveRol(entity);
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
                Integer rolId = new Integer(selectedRol.getRolId());
                entity = businessDelegatorView.getRol(rolId);
            }

            entity.setDescRol(FacesUtils.checkString(txtDescRol));
            entity.setNombreCorto(FacesUtils.checkString(txtNombreCorto));
            businessDelegatorView.updateRol(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedRol = (RolDTO) (evt.getComponent().getAttributes()
                                       .get("selectedRol"));

            Integer rolId = new Integer(selectedRol.getRolId());
            entity = businessDelegatorView.getRol(rolId);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Integer rolId = FacesUtils.checkInteger(txtRolId);
            entity = businessDelegatorView.getRol(rolId);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteRol(entity);
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
            selectedRol = (RolDTO) (evt.getComponent().getAttributes()
                                       .get("selectedRol"));

            Integer rolId = new Integer(selectedRol.getRolId());
            entity = businessDelegatorView.getRol(rolId);
            businessDelegatorView.deleteRol(entity);
            data.remove(selectedRol);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(String descRol, String nombreCorto,
        Integer rolId) throws Exception {
        try {
            entity.setDescRol(FacesUtils.checkString(descRol));
            entity.setNombreCorto(FacesUtils.checkString(nombreCorto));
            businessDelegatorView.updateRol(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("RolView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtDescRol() {
        return txtDescRol;
    }

    public void setTxtDescRol(InputText txtDescRol) {
        this.txtDescRol = txtDescRol;
    }

    public InputText getTxtNombreCorto() {
        return txtNombreCorto;
    }

    public void setTxtNombreCorto(InputText txtNombreCorto) {
        this.txtNombreCorto = txtNombreCorto;
    }

    public InputText getTxtRolId() {
        return txtRolId;
    }

    public void setTxtRolId(InputText txtRolId) {
        this.txtRolId = txtRolId;
    }

    public List<RolDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataRol();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<RolDTO> rolDTO) {
        this.data = rolDTO;
    }

    public RolDTO getSelectedRol() {
        return selectedRol;
    }

    public void setSelectedRol(RolDTO rol) {
        this.selectedRol = rol;
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
}
