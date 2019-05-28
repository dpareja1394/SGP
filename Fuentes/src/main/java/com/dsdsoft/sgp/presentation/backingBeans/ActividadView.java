package com.dsdsoft.sgp.presentation.backingBeans;

import com.dsdsoft.sgp.exceptions.*;
import com.dsdsoft.sgp.modelo.*;
import com.dsdsoft.sgp.modelo.dto.ActividadDTO;
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
public class ActividadView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(ActividadView.class);
    private InputText txtDescripcionActividad;
    private InputText txtTiacId_TipoActividad;
    private InputText txtUsuaId_Usuario;
    private InputText txtActiId;
    private Calendar txtFechaHoraFin;
    private Calendar txtFechaHoraInicio;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<ActividadDTO> data;
    private ActividadDTO selectedActividad;
    private Actividad entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public ActividadView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            ActividadDTO actividadDTO = (ActividadDTO) e.getObject();

            if (txtDescripcionActividad == null) {
                txtDescripcionActividad = new InputText();
            }

            txtDescripcionActividad.setValue(actividadDTO.getDescripcionActividad());

            if (txtTiacId_TipoActividad == null) {
                txtTiacId_TipoActividad = new InputText();
            }

            txtTiacId_TipoActividad.setValue(actividadDTO.getTiacId_TipoActividad());

            if (txtUsuaId_Usuario == null) {
                txtUsuaId_Usuario = new InputText();
            }

            txtUsuaId_Usuario.setValue(actividadDTO.getUsuaId_Usuario());

            if (txtActiId == null) {
                txtActiId = new InputText();
            }

            txtActiId.setValue(actividadDTO.getActiId());

            if (txtFechaHoraFin == null) {
                txtFechaHoraFin = new Calendar();
            }

            txtFechaHoraFin.setValue(actividadDTO.getFechaHoraFin());

            if (txtFechaHoraInicio == null) {
                txtFechaHoraInicio = new Calendar();
            }

            txtFechaHoraInicio.setValue(actividadDTO.getFechaHoraInicio());

            Integer actiId = FacesUtils.checkInteger(txtActiId);
            entity = businessDelegatorView.getActividad(actiId);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedActividad = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedActividad = null;

        if (txtDescripcionActividad != null) {
            txtDescripcionActividad.setValue(null);
            txtDescripcionActividad.setDisabled(true);
        }

        if (txtTiacId_TipoActividad != null) {
            txtTiacId_TipoActividad.setValue(null);
            txtTiacId_TipoActividad.setDisabled(true);
        }

        if (txtUsuaId_Usuario != null) {
            txtUsuaId_Usuario.setValue(null);
            txtUsuaId_Usuario.setDisabled(true);
        }

        if (txtFechaHoraFin != null) {
            txtFechaHoraFin.setValue(null);
            txtFechaHoraFin.setDisabled(true);
        }

        if (txtFechaHoraInicio != null) {
            txtFechaHoraInicio.setValue(null);
            txtFechaHoraInicio.setDisabled(true);
        }

        if (txtActiId != null) {
            txtActiId.setValue(null);
            txtActiId.setDisabled(false);
        }

        if (btnSave != null) {
            btnSave.setDisabled(true);
        }

        if (btnDelete != null) {
            btnDelete.setDisabled(true);
        }

        return "";
    }

    public void listener_txtFechaHoraFin() {
        Date inputDate = (Date) txtFechaHoraFin.getValue();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        FacesContext.getCurrentInstance()
                    .addMessage("",
            new FacesMessage("Selected Date " + dateFormat.format(inputDate)));
    }

    public void listener_txtFechaHoraInicio() {
        Date inputDate = (Date) txtFechaHoraInicio.getValue();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        FacesContext.getCurrentInstance()
                    .addMessage("",
            new FacesMessage("Selected Date " + dateFormat.format(inputDate)));
    }

    public void listener_txtId() {
        try {
            Integer actiId = FacesUtils.checkInteger(txtActiId);
            entity = (actiId != null)
                ? businessDelegatorView.getActividad(actiId) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtDescripcionActividad.setDisabled(false);
            txtTiacId_TipoActividad.setDisabled(false);
            txtUsuaId_Usuario.setDisabled(false);
            txtFechaHoraFin.setDisabled(false);
            txtFechaHoraInicio.setDisabled(false);
            txtActiId.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtDescripcionActividad.setValue(entity.getDescripcionActividad());
            txtDescripcionActividad.setDisabled(false);
            txtFechaHoraFin.setValue(entity.getFechaHoraFin());
            txtFechaHoraFin.setDisabled(false);
            txtFechaHoraInicio.setValue(entity.getFechaHoraInicio());
            txtFechaHoraInicio.setDisabled(false);
            txtTiacId_TipoActividad.setValue(entity.getTipoActividad()
                                                   .getTiacId());
            txtTiacId_TipoActividad.setDisabled(false);
            txtUsuaId_Usuario.setValue(entity.getUsuario().getUsuaId());
            txtUsuaId_Usuario.setDisabled(false);
            txtActiId.setValue(entity.getActiId());
            txtActiId.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedActividad = (ActividadDTO) (evt.getComponent().getAttributes()
                                               .get("selectedActividad"));
        txtDescripcionActividad.setValue(selectedActividad.getDescripcionActividad());
        txtDescripcionActividad.setDisabled(false);
        txtFechaHoraFin.setValue(selectedActividad.getFechaHoraFin());
        txtFechaHoraFin.setDisabled(false);
        txtFechaHoraInicio.setValue(selectedActividad.getFechaHoraInicio());
        txtFechaHoraInicio.setDisabled(false);
        txtTiacId_TipoActividad.setValue(selectedActividad.getTiacId_TipoActividad());
        txtTiacId_TipoActividad.setDisabled(false);
        txtUsuaId_Usuario.setValue(selectedActividad.getUsuaId_Usuario());
        txtUsuaId_Usuario.setDisabled(false);
        txtActiId.setValue(selectedActividad.getActiId());
        txtActiId.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedActividad == null) && (entity == null)) {
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
            entity = new Actividad();

            Integer actiId = FacesUtils.checkInteger(txtActiId);

            entity.setActiId(actiId);
            entity.setDescripcionActividad(FacesUtils.checkString(
                    txtDescripcionActividad));
            entity.setFechaHoraFin(FacesUtils.checkDate(txtFechaHoraFin));
            entity.setFechaHoraInicio(FacesUtils.checkDate(txtFechaHoraInicio));
            entity.setTipoActividad((FacesUtils.checkInteger(
                    txtTiacId_TipoActividad) != null)
                ? businessDelegatorView.getTipoActividad(
                    FacesUtils.checkInteger(txtTiacId_TipoActividad)) : null);
            entity.setUsuario((FacesUtils.checkInteger(txtUsuaId_Usuario) != null)
                ? businessDelegatorView.getUsuario(FacesUtils.checkInteger(
                        txtUsuaId_Usuario)) : null);
            businessDelegatorView.saveActividad(entity);
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
                Integer actiId = new Integer(selectedActividad.getActiId());
                entity = businessDelegatorView.getActividad(actiId);
            }

            entity.setDescripcionActividad(FacesUtils.checkString(
                    txtDescripcionActividad));
            entity.setFechaHoraFin(FacesUtils.checkDate(txtFechaHoraFin));
            entity.setFechaHoraInicio(FacesUtils.checkDate(txtFechaHoraInicio));
            entity.setTipoActividad((FacesUtils.checkInteger(
                    txtTiacId_TipoActividad) != null)
                ? businessDelegatorView.getTipoActividad(
                    FacesUtils.checkInteger(txtTiacId_TipoActividad)) : null);
            entity.setUsuario((FacesUtils.checkInteger(txtUsuaId_Usuario) != null)
                ? businessDelegatorView.getUsuario(FacesUtils.checkInteger(
                        txtUsuaId_Usuario)) : null);
            businessDelegatorView.updateActividad(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedActividad = (ActividadDTO) (evt.getComponent()
                                                   .getAttributes()
                                                   .get("selectedActividad"));

            Integer actiId = new Integer(selectedActividad.getActiId());
            entity = businessDelegatorView.getActividad(actiId);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Integer actiId = FacesUtils.checkInteger(txtActiId);
            entity = businessDelegatorView.getActividad(actiId);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteActividad(entity);
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
            selectedActividad = (ActividadDTO) (evt.getComponent()
                                                   .getAttributes()
                                                   .get("selectedActividad"));

            Integer actiId = new Integer(selectedActividad.getActiId());
            entity = businessDelegatorView.getActividad(actiId);
            businessDelegatorView.deleteActividad(entity);
            data.remove(selectedActividad);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(Integer actiId,
        String descripcionActividad, Date fechaHoraFin, Date fechaHoraInicio,
        Integer tiacId_TipoActividad, Integer usuaId_Usuario)
        throws Exception {
        try {
            entity.setDescripcionActividad(FacesUtils.checkString(
                    descripcionActividad));
            entity.setFechaHoraFin(FacesUtils.checkDate(fechaHoraFin));
            entity.setFechaHoraInicio(FacesUtils.checkDate(fechaHoraInicio));
            businessDelegatorView.updateActividad(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("ActividadView").requestRender();
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

    public InputText getTxtTiacId_TipoActividad() {
        return txtTiacId_TipoActividad;
    }

    public void setTxtTiacId_TipoActividad(InputText txtTiacId_TipoActividad) {
        this.txtTiacId_TipoActividad = txtTiacId_TipoActividad;
    }

    public InputText getTxtUsuaId_Usuario() {
        return txtUsuaId_Usuario;
    }

    public void setTxtUsuaId_Usuario(InputText txtUsuaId_Usuario) {
        this.txtUsuaId_Usuario = txtUsuaId_Usuario;
    }

    public Calendar getTxtFechaHoraFin() {
        return txtFechaHoraFin;
    }

    public void setTxtFechaHoraFin(Calendar txtFechaHoraFin) {
        this.txtFechaHoraFin = txtFechaHoraFin;
    }

    public Calendar getTxtFechaHoraInicio() {
        return txtFechaHoraInicio;
    }

    public void setTxtFechaHoraInicio(Calendar txtFechaHoraInicio) {
        this.txtFechaHoraInicio = txtFechaHoraInicio;
    }

    public InputText getTxtActiId() {
        return txtActiId;
    }

    public void setTxtActiId(InputText txtActiId) {
        this.txtActiId = txtActiId;
    }

    public List<ActividadDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataActividad();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<ActividadDTO> actividadDTO) {
        this.data = actividadDTO;
    }

    public ActividadDTO getSelectedActividad() {
        return selectedActividad;
    }

    public void setSelectedActividad(ActividadDTO actividad) {
        this.selectedActividad = actividad;
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
