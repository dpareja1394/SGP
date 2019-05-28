package com.dsdsoft.sgp.presentation.backingBeans;

import com.dsdsoft.sgp.exceptions.*;
import com.dsdsoft.sgp.modelo.*;
import com.dsdsoft.sgp.modelo.dto.CasoSoporteDTO;
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
public class CasoSoporteView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(CasoSoporteView.class);
    private InputText txtDescripcionCaso;
    private InputText txtTituloCaso;
    private InputText txtProyId_Proyecto;
    private InputText txtUsuaId_Usuario;
    private InputText txtCasoId;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<CasoSoporteDTO> data;
    private CasoSoporteDTO selectedCasoSoporte;
    private CasoSoporte entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public CasoSoporteView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            CasoSoporteDTO casoSoporteDTO = (CasoSoporteDTO) e.getObject();

            if (txtDescripcionCaso == null) {
                txtDescripcionCaso = new InputText();
            }

            txtDescripcionCaso.setValue(casoSoporteDTO.getDescripcionCaso());

            if (txtTituloCaso == null) {
                txtTituloCaso = new InputText();
            }

            txtTituloCaso.setValue(casoSoporteDTO.getTituloCaso());

            if (txtProyId_Proyecto == null) {
                txtProyId_Proyecto = new InputText();
            }

            txtProyId_Proyecto.setValue(casoSoporteDTO.getProyId_Proyecto());

            if (txtUsuaId_Usuario == null) {
                txtUsuaId_Usuario = new InputText();
            }

            txtUsuaId_Usuario.setValue(casoSoporteDTO.getUsuaId_Usuario());

            if (txtCasoId == null) {
                txtCasoId = new InputText();
            }

            txtCasoId.setValue(casoSoporteDTO.getCasoId());

            Integer casoId = FacesUtils.checkInteger(txtCasoId);
            entity = businessDelegatorView.getCasoSoporte(casoId);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedCasoSoporte = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedCasoSoporte = null;

        if (txtDescripcionCaso != null) {
            txtDescripcionCaso.setValue(null);
            txtDescripcionCaso.setDisabled(true);
        }

        if (txtTituloCaso != null) {
            txtTituloCaso.setValue(null);
            txtTituloCaso.setDisabled(true);
        }

        if (txtProyId_Proyecto != null) {
            txtProyId_Proyecto.setValue(null);
            txtProyId_Proyecto.setDisabled(true);
        }

        if (txtUsuaId_Usuario != null) {
            txtUsuaId_Usuario.setValue(null);
            txtUsuaId_Usuario.setDisabled(true);
        }

        if (txtCasoId != null) {
            txtCasoId.setValue(null);
            txtCasoId.setDisabled(false);
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
            Integer casoId = FacesUtils.checkInteger(txtCasoId);
            entity = (casoId != null)
                ? businessDelegatorView.getCasoSoporte(casoId) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtDescripcionCaso.setDisabled(false);
            txtTituloCaso.setDisabled(false);
            txtProyId_Proyecto.setDisabled(false);
            txtUsuaId_Usuario.setDisabled(false);
            txtCasoId.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtDescripcionCaso.setValue(entity.getDescripcionCaso());
            txtDescripcionCaso.setDisabled(false);
            txtTituloCaso.setValue(entity.getTituloCaso());
            txtTituloCaso.setDisabled(false);
            txtProyId_Proyecto.setValue(entity.getProyecto().getProyId());
            txtProyId_Proyecto.setDisabled(false);
            txtUsuaId_Usuario.setValue(entity.getUsuario().getUsuaId());
            txtUsuaId_Usuario.setDisabled(false);
            txtCasoId.setValue(entity.getCasoId());
            txtCasoId.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedCasoSoporte = (CasoSoporteDTO) (evt.getComponent()
                                                   .getAttributes()
                                                   .get("selectedCasoSoporte"));
        txtDescripcionCaso.setValue(selectedCasoSoporte.getDescripcionCaso());
        txtDescripcionCaso.setDisabled(false);
        txtTituloCaso.setValue(selectedCasoSoporte.getTituloCaso());
        txtTituloCaso.setDisabled(false);
        txtProyId_Proyecto.setValue(selectedCasoSoporte.getProyId_Proyecto());
        txtProyId_Proyecto.setDisabled(false);
        txtUsuaId_Usuario.setValue(selectedCasoSoporte.getUsuaId_Usuario());
        txtUsuaId_Usuario.setDisabled(false);
        txtCasoId.setValue(selectedCasoSoporte.getCasoId());
        txtCasoId.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedCasoSoporte == null) && (entity == null)) {
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
            entity = new CasoSoporte();

            Integer casoId = FacesUtils.checkInteger(txtCasoId);

            entity.setCasoId(casoId);
            entity.setDescripcionCaso(FacesUtils.checkString(txtDescripcionCaso));
            entity.setTituloCaso(FacesUtils.checkString(txtTituloCaso));
            entity.setProyecto((FacesUtils.checkInteger(txtProyId_Proyecto) != null)
                ? businessDelegatorView.getProyecto(FacesUtils.checkInteger(
                        txtProyId_Proyecto)) : null);
            entity.setUsuario((FacesUtils.checkInteger(txtUsuaId_Usuario) != null)
                ? businessDelegatorView.getUsuario(FacesUtils.checkInteger(
                        txtUsuaId_Usuario)) : null);
            businessDelegatorView.saveCasoSoporte(entity);
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
                Integer casoId = new Integer(selectedCasoSoporte.getCasoId());
                entity = businessDelegatorView.getCasoSoporte(casoId);
            }

            entity.setDescripcionCaso(FacesUtils.checkString(txtDescripcionCaso));
            entity.setTituloCaso(FacesUtils.checkString(txtTituloCaso));
            entity.setProyecto((FacesUtils.checkInteger(txtProyId_Proyecto) != null)
                ? businessDelegatorView.getProyecto(FacesUtils.checkInteger(
                        txtProyId_Proyecto)) : null);
            entity.setUsuario((FacesUtils.checkInteger(txtUsuaId_Usuario) != null)
                ? businessDelegatorView.getUsuario(FacesUtils.checkInteger(
                        txtUsuaId_Usuario)) : null);
            businessDelegatorView.updateCasoSoporte(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedCasoSoporte = (CasoSoporteDTO) (evt.getComponent()
                                                       .getAttributes()
                                                       .get("selectedCasoSoporte"));

            Integer casoId = new Integer(selectedCasoSoporte.getCasoId());
            entity = businessDelegatorView.getCasoSoporte(casoId);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Integer casoId = FacesUtils.checkInteger(txtCasoId);
            entity = businessDelegatorView.getCasoSoporte(casoId);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteCasoSoporte(entity);
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
            selectedCasoSoporte = (CasoSoporteDTO) (evt.getComponent()
                                                       .getAttributes()
                                                       .get("selectedCasoSoporte"));

            Integer casoId = new Integer(selectedCasoSoporte.getCasoId());
            entity = businessDelegatorView.getCasoSoporte(casoId);
            businessDelegatorView.deleteCasoSoporte(entity);
            data.remove(selectedCasoSoporte);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(Integer casoId, String descripcionCaso,
        String tituloCaso, Integer proyId_Proyecto, Integer usuaId_Usuario)
        throws Exception {
        try {
            entity.setDescripcionCaso(FacesUtils.checkString(descripcionCaso));
            entity.setTituloCaso(FacesUtils.checkString(tituloCaso));
            businessDelegatorView.updateCasoSoporte(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("CasoSoporteView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtDescripcionCaso() {
        return txtDescripcionCaso;
    }

    public void setTxtDescripcionCaso(InputText txtDescripcionCaso) {
        this.txtDescripcionCaso = txtDescripcionCaso;
    }

    public InputText getTxtTituloCaso() {
        return txtTituloCaso;
    }

    public void setTxtTituloCaso(InputText txtTituloCaso) {
        this.txtTituloCaso = txtTituloCaso;
    }

    public InputText getTxtProyId_Proyecto() {
        return txtProyId_Proyecto;
    }

    public void setTxtProyId_Proyecto(InputText txtProyId_Proyecto) {
        this.txtProyId_Proyecto = txtProyId_Proyecto;
    }

    public InputText getTxtUsuaId_Usuario() {
        return txtUsuaId_Usuario;
    }

    public void setTxtUsuaId_Usuario(InputText txtUsuaId_Usuario) {
        this.txtUsuaId_Usuario = txtUsuaId_Usuario;
    }

    public InputText getTxtCasoId() {
        return txtCasoId;
    }

    public void setTxtCasoId(InputText txtCasoId) {
        this.txtCasoId = txtCasoId;
    }

    public List<CasoSoporteDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataCasoSoporte();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<CasoSoporteDTO> casoSoporteDTO) {
        this.data = casoSoporteDTO;
    }

    public CasoSoporteDTO getSelectedCasoSoporte() {
        return selectedCasoSoporte;
    }

    public void setSelectedCasoSoporte(CasoSoporteDTO casoSoporte) {
        this.selectedCasoSoporte = casoSoporte;
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
