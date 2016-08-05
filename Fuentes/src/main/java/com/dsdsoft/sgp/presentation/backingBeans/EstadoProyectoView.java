package com.dsdsoft.sgp.presentation.backingBeans;

import com.dsdsoft.sgp.exceptions.*;
import com.dsdsoft.sgp.modelo.*;
import com.dsdsoft.sgp.modelo.dto.EstadoProyectoDTO;
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
public class EstadoProyectoView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(EstadoProyectoView.class);
    private InputText txtDescripcionEstado;
    private InputText txtNombreCorto;
    private InputText txtEsprId;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<EstadoProyectoDTO> data;
    private EstadoProyectoDTO selectedEstadoProyecto;
    private EstadoProyecto entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public EstadoProyectoView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            EstadoProyectoDTO estadoProyectoDTO = (EstadoProyectoDTO) e.getObject();

            if (txtDescripcionEstado == null) {
                txtDescripcionEstado = new InputText();
            }

            txtDescripcionEstado.setValue(estadoProyectoDTO.getDescripcionEstado());

            if (txtNombreCorto == null) {
                txtNombreCorto = new InputText();
            }

            txtNombreCorto.setValue(estadoProyectoDTO.getNombreCorto());

            if (txtEsprId == null) {
                txtEsprId = new InputText();
            }

            txtEsprId.setValue(estadoProyectoDTO.getEsprId());

            Integer esprId = FacesUtils.checkInteger(txtEsprId);
            entity = businessDelegatorView.getEstadoProyecto(esprId);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedEstadoProyecto = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedEstadoProyecto = null;

        if (txtDescripcionEstado != null) {
            txtDescripcionEstado.setValue(null);
            txtDescripcionEstado.setDisabled(true);
        }

        if (txtNombreCorto != null) {
            txtNombreCorto.setValue(null);
            txtNombreCorto.setDisabled(true);
        }

        if (txtEsprId != null) {
            txtEsprId.setValue(null);
            txtEsprId.setDisabled(false);
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
            Integer esprId = FacesUtils.checkInteger(txtEsprId);
            entity = (esprId != null)
                ? businessDelegatorView.getEstadoProyecto(esprId) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtDescripcionEstado.setDisabled(false);
            txtNombreCorto.setDisabled(false);
            txtEsprId.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtDescripcionEstado.setValue(entity.getDescripcionEstado());
            txtDescripcionEstado.setDisabled(false);
            txtNombreCorto.setValue(entity.getNombreCorto());
            txtNombreCorto.setDisabled(false);
            txtEsprId.setValue(entity.getEsprId());
            txtEsprId.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedEstadoProyecto = (EstadoProyectoDTO) (evt.getComponent()
                                                         .getAttributes()
                                                         .get("selectedEstadoProyecto"));
        txtDescripcionEstado.setValue(selectedEstadoProyecto.getDescripcionEstado());
        txtDescripcionEstado.setDisabled(false);
        txtNombreCorto.setValue(selectedEstadoProyecto.getNombreCorto());
        txtNombreCorto.setDisabled(false);
        txtEsprId.setValue(selectedEstadoProyecto.getEsprId());
        txtEsprId.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedEstadoProyecto == null) && (entity == null)) {
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
            entity = new EstadoProyecto();

            Integer esprId = FacesUtils.checkInteger(txtEsprId);

            entity.setDescripcionEstado(FacesUtils.checkString(
                    txtDescripcionEstado));
            entity.setEsprId(esprId);
            entity.setNombreCorto(FacesUtils.checkString(txtNombreCorto));
            businessDelegatorView.saveEstadoProyecto(entity);
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
                Integer esprId = new Integer(selectedEstadoProyecto.getEsprId());
                entity = businessDelegatorView.getEstadoProyecto(esprId);
            }

            entity.setDescripcionEstado(FacesUtils.checkString(
                    txtDescripcionEstado));
            entity.setNombreCorto(FacesUtils.checkString(txtNombreCorto));
            businessDelegatorView.updateEstadoProyecto(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedEstadoProyecto = (EstadoProyectoDTO) (evt.getComponent()
                                                             .getAttributes()
                                                             .get("selectedEstadoProyecto"));

            Integer esprId = new Integer(selectedEstadoProyecto.getEsprId());
            entity = businessDelegatorView.getEstadoProyecto(esprId);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Integer esprId = FacesUtils.checkInteger(txtEsprId);
            entity = businessDelegatorView.getEstadoProyecto(esprId);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteEstadoProyecto(entity);
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
            selectedEstadoProyecto = (EstadoProyectoDTO) (evt.getComponent()
                                                             .getAttributes()
                                                             .get("selectedEstadoProyecto"));

            Integer esprId = new Integer(selectedEstadoProyecto.getEsprId());
            entity = businessDelegatorView.getEstadoProyecto(esprId);
            businessDelegatorView.deleteEstadoProyecto(entity);
            data.remove(selectedEstadoProyecto);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(String descripcionEstado, Integer esprId,
        String nombreCorto) throws Exception {
        try {
            entity.setDescripcionEstado(FacesUtils.checkString(
                    descripcionEstado));
            entity.setNombreCorto(FacesUtils.checkString(nombreCorto));
            businessDelegatorView.updateEstadoProyecto(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("EstadoProyectoView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtDescripcionEstado() {
        return txtDescripcionEstado;
    }

    public void setTxtDescripcionEstado(InputText txtDescripcionEstado) {
        this.txtDescripcionEstado = txtDescripcionEstado;
    }

    public InputText getTxtNombreCorto() {
        return txtNombreCorto;
    }

    public void setTxtNombreCorto(InputText txtNombreCorto) {
        this.txtNombreCorto = txtNombreCorto;
    }

    public InputText getTxtEsprId() {
        return txtEsprId;
    }

    public void setTxtEsprId(InputText txtEsprId) {
        this.txtEsprId = txtEsprId;
    }

    public List<EstadoProyectoDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataEstadoProyecto();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<EstadoProyectoDTO> estadoProyectoDTO) {
        this.data = estadoProyectoDTO;
    }

    public EstadoProyectoDTO getSelectedEstadoProyecto() {
        return selectedEstadoProyecto;
    }

    public void setSelectedEstadoProyecto(EstadoProyectoDTO estadoProyecto) {
        this.selectedEstadoProyecto = estadoProyecto;
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
