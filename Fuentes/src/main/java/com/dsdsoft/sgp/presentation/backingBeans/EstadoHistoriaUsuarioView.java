package com.dsdsoft.sgp.presentation.backingBeans;

import com.dsdsoft.sgp.exceptions.*;
import com.dsdsoft.sgp.modelo.*;
import com.dsdsoft.sgp.modelo.dto.EstadoHistoriaUsuarioDTO;
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
public class EstadoHistoriaUsuarioView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(EstadoHistoriaUsuarioView.class);
    private InputText txtDescripcionEstado;
    private InputText txtNombreCorto;
    private InputText txtEshiId;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<EstadoHistoriaUsuarioDTO> data;
    private EstadoHistoriaUsuarioDTO selectedEstadoHistoriaUsuario;
    private EstadoHistoriaUsuario entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public EstadoHistoriaUsuarioView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            EstadoHistoriaUsuarioDTO estadoHistoriaUsuarioDTO = (EstadoHistoriaUsuarioDTO) e.getObject();

            if (txtDescripcionEstado == null) {
                txtDescripcionEstado = new InputText();
            }

            txtDescripcionEstado.setValue(estadoHistoriaUsuarioDTO.getDescripcionEstado());

            if (txtNombreCorto == null) {
                txtNombreCorto = new InputText();
            }

            txtNombreCorto.setValue(estadoHistoriaUsuarioDTO.getNombreCorto());

            if (txtEshiId == null) {
                txtEshiId = new InputText();
            }

            txtEshiId.setValue(estadoHistoriaUsuarioDTO.getEshiId());

            Integer eshiId = FacesUtils.checkInteger(txtEshiId);
            entity = businessDelegatorView.getEstadoHistoriaUsuario(eshiId);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedEstadoHistoriaUsuario = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedEstadoHistoriaUsuario = null;

        if (txtDescripcionEstado != null) {
            txtDescripcionEstado.setValue(null);
            txtDescripcionEstado.setDisabled(true);
        }

        if (txtNombreCorto != null) {
            txtNombreCorto.setValue(null);
            txtNombreCorto.setDisabled(true);
        }

        if (txtEshiId != null) {
            txtEshiId.setValue(null);
            txtEshiId.setDisabled(false);
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
            Integer eshiId = FacesUtils.checkInteger(txtEshiId);
            entity = (eshiId != null)
                ? businessDelegatorView.getEstadoHistoriaUsuario(eshiId) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtDescripcionEstado.setDisabled(false);
            txtNombreCorto.setDisabled(false);
            txtEshiId.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtDescripcionEstado.setValue(entity.getDescripcionEstado());
            txtDescripcionEstado.setDisabled(false);
            txtNombreCorto.setValue(entity.getNombreCorto());
            txtNombreCorto.setDisabled(false);
            txtEshiId.setValue(entity.getEshiId());
            txtEshiId.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedEstadoHistoriaUsuario = (EstadoHistoriaUsuarioDTO) (evt.getComponent()
                                                                       .getAttributes()
                                                                       .get("selectedEstadoHistoriaUsuario"));
        txtDescripcionEstado.setValue(selectedEstadoHistoriaUsuario.getDescripcionEstado());
        txtDescripcionEstado.setDisabled(false);
        txtNombreCorto.setValue(selectedEstadoHistoriaUsuario.getNombreCorto());
        txtNombreCorto.setDisabled(false);
        txtEshiId.setValue(selectedEstadoHistoriaUsuario.getEshiId());
        txtEshiId.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedEstadoHistoriaUsuario == null) && (entity == null)) {
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
            entity = new EstadoHistoriaUsuario();

            Integer eshiId = FacesUtils.checkInteger(txtEshiId);

            entity.setDescripcionEstado(FacesUtils.checkString(
                    txtDescripcionEstado));
            entity.setEshiId(eshiId);
            entity.setNombreCorto(FacesUtils.checkString(txtNombreCorto));
            businessDelegatorView.saveEstadoHistoriaUsuario(entity);
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
                Integer eshiId = new Integer(selectedEstadoHistoriaUsuario.getEshiId());
                entity = businessDelegatorView.getEstadoHistoriaUsuario(eshiId);
            }

            entity.setDescripcionEstado(FacesUtils.checkString(
                    txtDescripcionEstado));
            entity.setNombreCorto(FacesUtils.checkString(txtNombreCorto));
            businessDelegatorView.updateEstadoHistoriaUsuario(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedEstadoHistoriaUsuario = (EstadoHistoriaUsuarioDTO) (evt.getComponent()
                                                                           .getAttributes()
                                                                           .get("selectedEstadoHistoriaUsuario"));

            Integer eshiId = new Integer(selectedEstadoHistoriaUsuario.getEshiId());
            entity = businessDelegatorView.getEstadoHistoriaUsuario(eshiId);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Integer eshiId = FacesUtils.checkInteger(txtEshiId);
            entity = businessDelegatorView.getEstadoHistoriaUsuario(eshiId);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteEstadoHistoriaUsuario(entity);
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
            selectedEstadoHistoriaUsuario = (EstadoHistoriaUsuarioDTO) (evt.getComponent()
                                                                           .getAttributes()
                                                                           .get("selectedEstadoHistoriaUsuario"));

            Integer eshiId = new Integer(selectedEstadoHistoriaUsuario.getEshiId());
            entity = businessDelegatorView.getEstadoHistoriaUsuario(eshiId);
            businessDelegatorView.deleteEstadoHistoriaUsuario(entity);
            data.remove(selectedEstadoHistoriaUsuario);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(String descripcionEstado, Integer eshiId,
        String nombreCorto) throws Exception {
        try {
            entity.setDescripcionEstado(FacesUtils.checkString(
                    descripcionEstado));
            entity.setNombreCorto(FacesUtils.checkString(nombreCorto));
            businessDelegatorView.updateEstadoHistoriaUsuario(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("EstadoHistoriaUsuarioView").requestRender();
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

    public InputText getTxtEshiId() {
        return txtEshiId;
    }

    public void setTxtEshiId(InputText txtEshiId) {
        this.txtEshiId = txtEshiId;
    }

    public List<EstadoHistoriaUsuarioDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataEstadoHistoriaUsuario();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<EstadoHistoriaUsuarioDTO> estadoHistoriaUsuarioDTO) {
        this.data = estadoHistoriaUsuarioDTO;
    }

    public EstadoHistoriaUsuarioDTO getSelectedEstadoHistoriaUsuario() {
        return selectedEstadoHistoriaUsuario;
    }

    public void setSelectedEstadoHistoriaUsuario(
        EstadoHistoriaUsuarioDTO estadoHistoriaUsuario) {
        this.selectedEstadoHistoriaUsuario = estadoHistoriaUsuario;
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
