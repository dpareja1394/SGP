package com.dsdsoft.sgp.presentation.backingBeans;

import com.dsdsoft.sgp.exceptions.*;
import com.dsdsoft.sgp.modelo.*;
import com.dsdsoft.sgp.modelo.dto.RequerimientoDTO;
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
public class RequerimientoView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(RequerimientoView.class);
    private InputText txtDescripcionRequerimiento;
    private InputText txtNombreRequerimiento;
    private InputText txtProyId_Proyecto;
    private InputText txtRequId;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<RequerimientoDTO> data;
    private RequerimientoDTO selectedRequerimiento;
    private Requerimiento entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public RequerimientoView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            RequerimientoDTO requerimientoDTO = (RequerimientoDTO) e.getObject();

            if (txtDescripcionRequerimiento == null) {
                txtDescripcionRequerimiento = new InputText();
            }

            txtDescripcionRequerimiento.setValue(requerimientoDTO.getDescripcionRequerimiento());

            if (txtNombreRequerimiento == null) {
                txtNombreRequerimiento = new InputText();
            }

            txtNombreRequerimiento.setValue(requerimientoDTO.getNombreRequerimiento());

            if (txtProyId_Proyecto == null) {
                txtProyId_Proyecto = new InputText();
            }

            txtProyId_Proyecto.setValue(requerimientoDTO.getProyId_Proyecto());

            if (txtRequId == null) {
                txtRequId = new InputText();
            }

            txtRequId.setValue(requerimientoDTO.getRequId());

            Integer requId = FacesUtils.checkInteger(txtRequId);
            entity = businessDelegatorView.getRequerimiento(requId);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedRequerimiento = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedRequerimiento = null;

        if (txtDescripcionRequerimiento != null) {
            txtDescripcionRequerimiento.setValue(null);
            txtDescripcionRequerimiento.setDisabled(true);
        }

        if (txtNombreRequerimiento != null) {
            txtNombreRequerimiento.setValue(null);
            txtNombreRequerimiento.setDisabled(true);
        }

        if (txtProyId_Proyecto != null) {
            txtProyId_Proyecto.setValue(null);
            txtProyId_Proyecto.setDisabled(true);
        }

        if (txtRequId != null) {
            txtRequId.setValue(null);
            txtRequId.setDisabled(false);
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
            Integer requId = FacesUtils.checkInteger(txtRequId);
            entity = (requId != null)
                ? businessDelegatorView.getRequerimiento(requId) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtDescripcionRequerimiento.setDisabled(false);
            txtNombreRequerimiento.setDisabled(false);
            txtProyId_Proyecto.setDisabled(false);
            txtRequId.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtDescripcionRequerimiento.setValue(entity.getDescripcionRequerimiento());
            txtDescripcionRequerimiento.setDisabled(false);
            txtNombreRequerimiento.setValue(entity.getNombreRequerimiento());
            txtNombreRequerimiento.setDisabled(false);
            txtProyId_Proyecto.setValue(entity.getProyecto().getProyId());
            txtProyId_Proyecto.setDisabled(false);
            txtRequId.setValue(entity.getRequId());
            txtRequId.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedRequerimiento = (RequerimientoDTO) (evt.getComponent()
                                                       .getAttributes()
                                                       .get("selectedRequerimiento"));
        txtDescripcionRequerimiento.setValue(selectedRequerimiento.getDescripcionRequerimiento());
        txtDescripcionRequerimiento.setDisabled(false);
        txtNombreRequerimiento.setValue(selectedRequerimiento.getNombreRequerimiento());
        txtNombreRequerimiento.setDisabled(false);
        txtProyId_Proyecto.setValue(selectedRequerimiento.getProyId_Proyecto());
        txtProyId_Proyecto.setDisabled(false);
        txtRequId.setValue(selectedRequerimiento.getRequId());
        txtRequId.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedRequerimiento == null) && (entity == null)) {
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
            entity = new Requerimiento();

            Integer requId = FacesUtils.checkInteger(txtRequId);

            entity.setDescripcionRequerimiento(FacesUtils.checkString(
                    txtDescripcionRequerimiento));
            entity.setNombreRequerimiento(FacesUtils.checkString(
                    txtNombreRequerimiento));
            entity.setRequId(requId);
            entity.setProyecto((FacesUtils.checkInteger(txtProyId_Proyecto) != null)
                ? businessDelegatorView.getProyecto(FacesUtils.checkInteger(
                        txtProyId_Proyecto)) : null);
            businessDelegatorView.saveRequerimiento(entity);
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
                Integer requId = new Integer(selectedRequerimiento.getRequId());
                entity = businessDelegatorView.getRequerimiento(requId);
            }

            entity.setDescripcionRequerimiento(FacesUtils.checkString(
                    txtDescripcionRequerimiento));
            entity.setNombreRequerimiento(FacesUtils.checkString(
                    txtNombreRequerimiento));
            entity.setProyecto((FacesUtils.checkInteger(txtProyId_Proyecto) != null)
                ? businessDelegatorView.getProyecto(FacesUtils.checkInteger(
                        txtProyId_Proyecto)) : null);
            businessDelegatorView.updateRequerimiento(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedRequerimiento = (RequerimientoDTO) (evt.getComponent()
                                                           .getAttributes()
                                                           .get("selectedRequerimiento"));

            Integer requId = new Integer(selectedRequerimiento.getRequId());
            entity = businessDelegatorView.getRequerimiento(requId);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Integer requId = FacesUtils.checkInteger(txtRequId);
            entity = businessDelegatorView.getRequerimiento(requId);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteRequerimiento(entity);
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
            selectedRequerimiento = (RequerimientoDTO) (evt.getComponent()
                                                           .getAttributes()
                                                           .get("selectedRequerimiento"));

            Integer requId = new Integer(selectedRequerimiento.getRequId());
            entity = businessDelegatorView.getRequerimiento(requId);
            businessDelegatorView.deleteRequerimiento(entity);
            data.remove(selectedRequerimiento);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(String descripcionRequerimiento,
        String nombreRequerimiento, Integer requId, Integer proyId_Proyecto)
        throws Exception {
        try {
            entity.setDescripcionRequerimiento(FacesUtils.checkString(
                    descripcionRequerimiento));
            entity.setNombreRequerimiento(FacesUtils.checkString(
                    nombreRequerimiento));
            businessDelegatorView.updateRequerimiento(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("RequerimientoView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtDescripcionRequerimiento() {
        return txtDescripcionRequerimiento;
    }

    public void setTxtDescripcionRequerimiento(
        InputText txtDescripcionRequerimiento) {
        this.txtDescripcionRequerimiento = txtDescripcionRequerimiento;
    }

    public InputText getTxtNombreRequerimiento() {
        return txtNombreRequerimiento;
    }

    public void setTxtNombreRequerimiento(InputText txtNombreRequerimiento) {
        this.txtNombreRequerimiento = txtNombreRequerimiento;
    }

    public InputText getTxtProyId_Proyecto() {
        return txtProyId_Proyecto;
    }

    public void setTxtProyId_Proyecto(InputText txtProyId_Proyecto) {
        this.txtProyId_Proyecto = txtProyId_Proyecto;
    }

    public InputText getTxtRequId() {
        return txtRequId;
    }

    public void setTxtRequId(InputText txtRequId) {
        this.txtRequId = txtRequId;
    }

    public List<RequerimientoDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataRequerimiento();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<RequerimientoDTO> requerimientoDTO) {
        this.data = requerimientoDTO;
    }

    public RequerimientoDTO getSelectedRequerimiento() {
        return selectedRequerimiento;
    }

    public void setSelectedRequerimiento(RequerimientoDTO requerimiento) {
        this.selectedRequerimiento = requerimiento;
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
