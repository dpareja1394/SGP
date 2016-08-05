package com.dsdsoft.sgp.presentation.backingBeans;

import com.dsdsoft.sgp.exceptions.*;
import com.dsdsoft.sgp.modelo.*;
import com.dsdsoft.sgp.modelo.dto.SeguimientoCasoDTO;
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
public class SeguimientoCasoView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(SeguimientoCasoView.class);
    private InputText txtDescripcionActividad;
    private InputText txtCasoId_CasoSoporte;
    private InputText txtUsuaId_Usuario;
    private InputText txtSecaId;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<SeguimientoCasoDTO> data;
    private SeguimientoCasoDTO selectedSeguimientoCaso;
    private SeguimientoCaso entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public SeguimientoCasoView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            SeguimientoCasoDTO seguimientoCasoDTO = (SeguimientoCasoDTO) e.getObject();

            if (txtDescripcionActividad == null) {
                txtDescripcionActividad = new InputText();
            }

            txtDescripcionActividad.setValue(seguimientoCasoDTO.getDescripcionActividad());

            if (txtCasoId_CasoSoporte == null) {
                txtCasoId_CasoSoporte = new InputText();
            }

            txtCasoId_CasoSoporte.setValue(seguimientoCasoDTO.getCasoId_CasoSoporte());

            if (txtUsuaId_Usuario == null) {
                txtUsuaId_Usuario = new InputText();
            }

            txtUsuaId_Usuario.setValue(seguimientoCasoDTO.getUsuaId_Usuario());

            if (txtSecaId == null) {
                txtSecaId = new InputText();
            }

            txtSecaId.setValue(seguimientoCasoDTO.getSecaId());

            Integer secaId = FacesUtils.checkInteger(txtSecaId);
            entity = businessDelegatorView.getSeguimientoCaso(secaId);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedSeguimientoCaso = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedSeguimientoCaso = null;

        if (txtDescripcionActividad != null) {
            txtDescripcionActividad.setValue(null);
            txtDescripcionActividad.setDisabled(true);
        }

        if (txtCasoId_CasoSoporte != null) {
            txtCasoId_CasoSoporte.setValue(null);
            txtCasoId_CasoSoporte.setDisabled(true);
        }

        if (txtUsuaId_Usuario != null) {
            txtUsuaId_Usuario.setValue(null);
            txtUsuaId_Usuario.setDisabled(true);
        }

        if (txtSecaId != null) {
            txtSecaId.setValue(null);
            txtSecaId.setDisabled(false);
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
            Integer secaId = FacesUtils.checkInteger(txtSecaId);
            entity = (secaId != null)
                ? businessDelegatorView.getSeguimientoCaso(secaId) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtDescripcionActividad.setDisabled(false);
            txtCasoId_CasoSoporte.setDisabled(false);
            txtUsuaId_Usuario.setDisabled(false);
            txtSecaId.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtDescripcionActividad.setValue(entity.getDescripcionActividad());
            txtDescripcionActividad.setDisabled(false);
            txtCasoId_CasoSoporte.setValue(entity.getCasoSoporte().getCasoId());
            txtCasoId_CasoSoporte.setDisabled(false);
            txtUsuaId_Usuario.setValue(entity.getUsuario().getUsuaId());
            txtUsuaId_Usuario.setDisabled(false);
            txtSecaId.setValue(entity.getSecaId());
            txtSecaId.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedSeguimientoCaso = (SeguimientoCasoDTO) (evt.getComponent()
                                                           .getAttributes()
                                                           .get("selectedSeguimientoCaso"));
        txtDescripcionActividad.setValue(selectedSeguimientoCaso.getDescripcionActividad());
        txtDescripcionActividad.setDisabled(false);
        txtCasoId_CasoSoporte.setValue(selectedSeguimientoCaso.getCasoId_CasoSoporte());
        txtCasoId_CasoSoporte.setDisabled(false);
        txtUsuaId_Usuario.setValue(selectedSeguimientoCaso.getUsuaId_Usuario());
        txtUsuaId_Usuario.setDisabled(false);
        txtSecaId.setValue(selectedSeguimientoCaso.getSecaId());
        txtSecaId.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedSeguimientoCaso == null) && (entity == null)) {
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
            entity = new SeguimientoCaso();

            Integer secaId = FacesUtils.checkInteger(txtSecaId);

            entity.setDescripcionActividad(FacesUtils.checkString(
                    txtDescripcionActividad));
            entity.setSecaId(secaId);
            entity.setCasoSoporte((FacesUtils.checkInteger(
                    txtCasoId_CasoSoporte) != null)
                ? businessDelegatorView.getCasoSoporte(FacesUtils.checkInteger(
                        txtCasoId_CasoSoporte)) : null);
            entity.setUsuario((FacesUtils.checkInteger(txtUsuaId_Usuario) != null)
                ? businessDelegatorView.getUsuario(FacesUtils.checkInteger(
                        txtUsuaId_Usuario)) : null);
            businessDelegatorView.saveSeguimientoCaso(entity);
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
                Integer secaId = new Integer(selectedSeguimientoCaso.getSecaId());
                entity = businessDelegatorView.getSeguimientoCaso(secaId);
            }

            entity.setDescripcionActividad(FacesUtils.checkString(
                    txtDescripcionActividad));
            entity.setCasoSoporte((FacesUtils.checkInteger(
                    txtCasoId_CasoSoporte) != null)
                ? businessDelegatorView.getCasoSoporte(FacesUtils.checkInteger(
                        txtCasoId_CasoSoporte)) : null);
            entity.setUsuario((FacesUtils.checkInteger(txtUsuaId_Usuario) != null)
                ? businessDelegatorView.getUsuario(FacesUtils.checkInteger(
                        txtUsuaId_Usuario)) : null);
            businessDelegatorView.updateSeguimientoCaso(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedSeguimientoCaso = (SeguimientoCasoDTO) (evt.getComponent()
                                                               .getAttributes()
                                                               .get("selectedSeguimientoCaso"));

            Integer secaId = new Integer(selectedSeguimientoCaso.getSecaId());
            entity = businessDelegatorView.getSeguimientoCaso(secaId);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Integer secaId = FacesUtils.checkInteger(txtSecaId);
            entity = businessDelegatorView.getSeguimientoCaso(secaId);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteSeguimientoCaso(entity);
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
            selectedSeguimientoCaso = (SeguimientoCasoDTO) (evt.getComponent()
                                                               .getAttributes()
                                                               .get("selectedSeguimientoCaso"));

            Integer secaId = new Integer(selectedSeguimientoCaso.getSecaId());
            entity = businessDelegatorView.getSeguimientoCaso(secaId);
            businessDelegatorView.deleteSeguimientoCaso(entity);
            data.remove(selectedSeguimientoCaso);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(String descripcionActividad,
        Integer secaId, Integer casoId_CasoSoporte, Integer usuaId_Usuario)
        throws Exception {
        try {
            entity.setDescripcionActividad(FacesUtils.checkString(
                    descripcionActividad));
            businessDelegatorView.updateSeguimientoCaso(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("SeguimientoCasoView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtDescripcionActividad() {
        return txtDescripcionActividad;
    }

    public void setTxtDescripcionActividad(InputText txtDescripcionActividad) {
        this.txtDescripcionActividad = txtDescripcionActividad;
    }

    public InputText getTxtCasoId_CasoSoporte() {
        return txtCasoId_CasoSoporte;
    }

    public void setTxtCasoId_CasoSoporte(InputText txtCasoId_CasoSoporte) {
        this.txtCasoId_CasoSoporte = txtCasoId_CasoSoporte;
    }

    public InputText getTxtUsuaId_Usuario() {
        return txtUsuaId_Usuario;
    }

    public void setTxtUsuaId_Usuario(InputText txtUsuaId_Usuario) {
        this.txtUsuaId_Usuario = txtUsuaId_Usuario;
    }

    public InputText getTxtSecaId() {
        return txtSecaId;
    }

    public void setTxtSecaId(InputText txtSecaId) {
        this.txtSecaId = txtSecaId;
    }

    public List<SeguimientoCasoDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataSeguimientoCaso();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<SeguimientoCasoDTO> seguimientoCasoDTO) {
        this.data = seguimientoCasoDTO;
    }

    public SeguimientoCasoDTO getSelectedSeguimientoCaso() {
        return selectedSeguimientoCaso;
    }

    public void setSelectedSeguimientoCaso(SeguimientoCasoDTO seguimientoCaso) {
        this.selectedSeguimientoCaso = seguimientoCaso;
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
