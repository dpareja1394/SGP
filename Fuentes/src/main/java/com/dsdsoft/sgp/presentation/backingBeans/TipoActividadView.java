package com.dsdsoft.sgp.presentation.backingBeans;

import com.dsdsoft.sgp.exceptions.*;
import com.dsdsoft.sgp.modelo.*;
import com.dsdsoft.sgp.modelo.dto.TipoActividadDTO;
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
public class TipoActividadView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(TipoActividadView.class);
    private InputText txtDescripcionTiact;
    private InputText txtNombreCorto;
    private InputText txtTiacId;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<TipoActividadDTO> data;
    private TipoActividadDTO selectedTipoActividad;
    private TipoActividad entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public TipoActividadView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            TipoActividadDTO tipoActividadDTO = (TipoActividadDTO) e.getObject();

            if (txtDescripcionTiact == null) {
                txtDescripcionTiact = new InputText();
            }

            txtDescripcionTiact.setValue(tipoActividadDTO.getDescripcionTiact());

            if (txtNombreCorto == null) {
                txtNombreCorto = new InputText();
            }

            txtNombreCorto.setValue(tipoActividadDTO.getNombreCorto());

            if (txtTiacId == null) {
                txtTiacId = new InputText();
            }

            txtTiacId.setValue(tipoActividadDTO.getTiacId());

            Integer tiacId = FacesUtils.checkInteger(txtTiacId);
            entity = businessDelegatorView.getTipoActividad(tiacId);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedTipoActividad = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedTipoActividad = null;

        if (txtDescripcionTiact != null) {
            txtDescripcionTiact.setValue(null);
            txtDescripcionTiact.setDisabled(true);
        }

        if (txtNombreCorto != null) {
            txtNombreCorto.setValue(null);
            txtNombreCorto.setDisabled(true);
        }

        if (txtTiacId != null) {
            txtTiacId.setValue(null);
            txtTiacId.setDisabled(false);
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
            Integer tiacId = FacesUtils.checkInteger(txtTiacId);
            entity = (tiacId != null)
                ? businessDelegatorView.getTipoActividad(tiacId) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtDescripcionTiact.setDisabled(false);
            txtNombreCorto.setDisabled(false);
            txtTiacId.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtDescripcionTiact.setValue(entity.getDescripcionTiact());
            txtDescripcionTiact.setDisabled(false);
            txtNombreCorto.setValue(entity.getNombreCorto());
            txtNombreCorto.setDisabled(false);
            txtTiacId.setValue(entity.getTiacId());
            txtTiacId.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedTipoActividad = (TipoActividadDTO) (evt.getComponent()
                                                       .getAttributes()
                                                       .get("selectedTipoActividad"));
        txtDescripcionTiact.setValue(selectedTipoActividad.getDescripcionTiact());
        txtDescripcionTiact.setDisabled(false);
        txtNombreCorto.setValue(selectedTipoActividad.getNombreCorto());
        txtNombreCorto.setDisabled(false);
        txtTiacId.setValue(selectedTipoActividad.getTiacId());
        txtTiacId.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedTipoActividad == null) && (entity == null)) {
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
            entity = new TipoActividad();

            Integer tiacId = FacesUtils.checkInteger(txtTiacId);

            entity.setDescripcionTiact(FacesUtils.checkString(
                    txtDescripcionTiact));
            entity.setNombreCorto(FacesUtils.checkString(txtNombreCorto));
            entity.setTiacId(tiacId);
            businessDelegatorView.saveTipoActividad(entity);
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
                Integer tiacId = new Integer(selectedTipoActividad.getTiacId());
                entity = businessDelegatorView.getTipoActividad(tiacId);
            }

            entity.setDescripcionTiact(FacesUtils.checkString(
                    txtDescripcionTiact));
            entity.setNombreCorto(FacesUtils.checkString(txtNombreCorto));
            businessDelegatorView.updateTipoActividad(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedTipoActividad = (TipoActividadDTO) (evt.getComponent()
                                                           .getAttributes()
                                                           .get("selectedTipoActividad"));

            Integer tiacId = new Integer(selectedTipoActividad.getTiacId());
            entity = businessDelegatorView.getTipoActividad(tiacId);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Integer tiacId = FacesUtils.checkInteger(txtTiacId);
            entity = businessDelegatorView.getTipoActividad(tiacId);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteTipoActividad(entity);
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
            selectedTipoActividad = (TipoActividadDTO) (evt.getComponent()
                                                           .getAttributes()
                                                           .get("selectedTipoActividad"));

            Integer tiacId = new Integer(selectedTipoActividad.getTiacId());
            entity = businessDelegatorView.getTipoActividad(tiacId);
            businessDelegatorView.deleteTipoActividad(entity);
            data.remove(selectedTipoActividad);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(String descripcionTiact,
        String nombreCorto, Integer tiacId) throws Exception {
        try {
            entity.setDescripcionTiact(FacesUtils.checkString(descripcionTiact));
            entity.setNombreCorto(FacesUtils.checkString(nombreCorto));
            businessDelegatorView.updateTipoActividad(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("TipoActividadView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtDescripcionTiact() {
        return txtDescripcionTiact;
    }

    public void setTxtDescripcionTiact(InputText txtDescripcionTiact) {
        this.txtDescripcionTiact = txtDescripcionTiact;
    }

    public InputText getTxtNombreCorto() {
        return txtNombreCorto;
    }

    public void setTxtNombreCorto(InputText txtNombreCorto) {
        this.txtNombreCorto = txtNombreCorto;
    }

    public InputText getTxtTiacId() {
        return txtTiacId;
    }

    public void setTxtTiacId(InputText txtTiacId) {
        this.txtTiacId = txtTiacId;
    }

    public List<TipoActividadDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataTipoActividad();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<TipoActividadDTO> tipoActividadDTO) {
        this.data = tipoActividadDTO;
    }

    public TipoActividadDTO getSelectedTipoActividad() {
        return selectedTipoActividad;
    }

    public void setSelectedTipoActividad(TipoActividadDTO tipoActividad) {
        this.selectedTipoActividad = tipoActividad;
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
    
    public void estoNoHaceNada(){
    	log.info("Entró al método que no hace nada");
    }
}
