package com.dsdsoft.sgp.presentation.backingBeans;

import com.dsdsoft.sgp.exceptions.*;
import com.dsdsoft.sgp.modelo.*;
import com.dsdsoft.sgp.modelo.dto.HistoriaDeUsuarioDTO;
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
public class HistoriaDeUsuarioView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(HistoriaDeUsuarioView.class);
    private InputText txtDetalleHistoria;
    private InputText txtTituloHistoria;
    private InputText txtEshiId_EstadoHistoriaUsuario;
    private InputText txtRequId_Requerimiento;
    private InputText txtUsuaId_Usuario;
    private InputText txtHiusId;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<HistoriaDeUsuarioDTO> data;
    private HistoriaDeUsuarioDTO selectedHistoriaDeUsuario;
    private HistoriaDeUsuario entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public HistoriaDeUsuarioView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            HistoriaDeUsuarioDTO historiaDeUsuarioDTO = (HistoriaDeUsuarioDTO) e.getObject();

            if (txtDetalleHistoria == null) {
                txtDetalleHistoria = new InputText();
            }

            txtDetalleHistoria.setValue(historiaDeUsuarioDTO.getDetalleHistoria());

            if (txtTituloHistoria == null) {
                txtTituloHistoria = new InputText();
            }

            txtTituloHistoria.setValue(historiaDeUsuarioDTO.getTituloHistoria());

            if (txtEshiId_EstadoHistoriaUsuario == null) {
                txtEshiId_EstadoHistoriaUsuario = new InputText();
            }

            txtEshiId_EstadoHistoriaUsuario.setValue(historiaDeUsuarioDTO.getEshiId_EstadoHistoriaUsuario());

            if (txtRequId_Requerimiento == null) {
                txtRequId_Requerimiento = new InputText();
            }

            txtRequId_Requerimiento.setValue(historiaDeUsuarioDTO.getRequId_Requerimiento());

            if (txtUsuaId_Usuario == null) {
                txtUsuaId_Usuario = new InputText();
            }

            txtUsuaId_Usuario.setValue(historiaDeUsuarioDTO.getUsuaId_Usuario());

            if (txtHiusId == null) {
                txtHiusId = new InputText();
            }

            txtHiusId.setValue(historiaDeUsuarioDTO.getHiusId());

            Integer hiusId = FacesUtils.checkInteger(txtHiusId);
            entity = businessDelegatorView.getHistoriaDeUsuario(hiusId);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedHistoriaDeUsuario = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedHistoriaDeUsuario = null;

        if (txtDetalleHistoria != null) {
            txtDetalleHistoria.setValue(null);
            txtDetalleHistoria.setDisabled(true);
        }

        if (txtTituloHistoria != null) {
            txtTituloHistoria.setValue(null);
            txtTituloHistoria.setDisabled(true);
        }

        if (txtEshiId_EstadoHistoriaUsuario != null) {
            txtEshiId_EstadoHistoriaUsuario.setValue(null);
            txtEshiId_EstadoHistoriaUsuario.setDisabled(true);
        }

        if (txtRequId_Requerimiento != null) {
            txtRequId_Requerimiento.setValue(null);
            txtRequId_Requerimiento.setDisabled(true);
        }

        if (txtUsuaId_Usuario != null) {
            txtUsuaId_Usuario.setValue(null);
            txtUsuaId_Usuario.setDisabled(true);
        }

        if (txtHiusId != null) {
            txtHiusId.setValue(null);
            txtHiusId.setDisabled(false);
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
            Integer hiusId = FacesUtils.checkInteger(txtHiusId);
            entity = (hiusId != null)
                ? businessDelegatorView.getHistoriaDeUsuario(hiusId) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtDetalleHistoria.setDisabled(false);
            txtTituloHistoria.setDisabled(false);
            txtEshiId_EstadoHistoriaUsuario.setDisabled(false);
            txtRequId_Requerimiento.setDisabled(false);
            txtUsuaId_Usuario.setDisabled(false);
            txtHiusId.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtDetalleHistoria.setValue(entity.getDetalleHistoria());
            txtDetalleHistoria.setDisabled(false);
            txtTituloHistoria.setValue(entity.getTituloHistoria());
            txtTituloHistoria.setDisabled(false);
            txtEshiId_EstadoHistoriaUsuario.setValue(entity.getEstadoHistoriaUsuario()
                                                           .getEshiId());
            txtEshiId_EstadoHistoriaUsuario.setDisabled(false);
            txtRequId_Requerimiento.setValue(entity.getRequerimiento()
                                                   .getRequId());
            txtRequId_Requerimiento.setDisabled(false);
            txtUsuaId_Usuario.setValue(entity.getUsuario().getUsuaId());
            txtUsuaId_Usuario.setDisabled(false);
            txtHiusId.setValue(entity.getHiusId());
            txtHiusId.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedHistoriaDeUsuario = (HistoriaDeUsuarioDTO) (evt.getComponent()
                                                               .getAttributes()
                                                               .get("selectedHistoriaDeUsuario"));
        txtDetalleHistoria.setValue(selectedHistoriaDeUsuario.getDetalleHistoria());
        txtDetalleHistoria.setDisabled(false);
        txtTituloHistoria.setValue(selectedHistoriaDeUsuario.getTituloHistoria());
        txtTituloHistoria.setDisabled(false);
        txtEshiId_EstadoHistoriaUsuario.setValue(selectedHistoriaDeUsuario.getEshiId_EstadoHistoriaUsuario());
        txtEshiId_EstadoHistoriaUsuario.setDisabled(false);
        txtRequId_Requerimiento.setValue(selectedHistoriaDeUsuario.getRequId_Requerimiento());
        txtRequId_Requerimiento.setDisabled(false);
        txtUsuaId_Usuario.setValue(selectedHistoriaDeUsuario.getUsuaId_Usuario());
        txtUsuaId_Usuario.setDisabled(false);
        txtHiusId.setValue(selectedHistoriaDeUsuario.getHiusId());
        txtHiusId.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedHistoriaDeUsuario == null) && (entity == null)) {
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
            entity = new HistoriaDeUsuario();

            Integer hiusId = FacesUtils.checkInteger(txtHiusId);

            entity.setDetalleHistoria(FacesUtils.checkString(txtDetalleHistoria));
            entity.setHiusId(hiusId);
            entity.setTituloHistoria(FacesUtils.checkString(txtTituloHistoria));
            entity.setEstadoHistoriaUsuario((FacesUtils.checkInteger(
                    txtEshiId_EstadoHistoriaUsuario) != null)
                ? businessDelegatorView.getEstadoHistoriaUsuario(
                    FacesUtils.checkInteger(txtEshiId_EstadoHistoriaUsuario))
                : null);
            entity.setRequerimiento((FacesUtils.checkInteger(
                    txtRequId_Requerimiento) != null)
                ? businessDelegatorView.getRequerimiento(
                    FacesUtils.checkInteger(txtRequId_Requerimiento)) : null);
            entity.setUsuario((FacesUtils.checkInteger(txtUsuaId_Usuario) != null)
                ? businessDelegatorView.getUsuario(FacesUtils.checkInteger(
                        txtUsuaId_Usuario)) : null);
            businessDelegatorView.saveHistoriaDeUsuario(entity);
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
                Integer hiusId = new Integer(selectedHistoriaDeUsuario.getHiusId());
                entity = businessDelegatorView.getHistoriaDeUsuario(hiusId);
            }

            entity.setDetalleHistoria(FacesUtils.checkString(txtDetalleHistoria));
            entity.setTituloHistoria(FacesUtils.checkString(txtTituloHistoria));
            entity.setEstadoHistoriaUsuario((FacesUtils.checkInteger(
                    txtEshiId_EstadoHistoriaUsuario) != null)
                ? businessDelegatorView.getEstadoHistoriaUsuario(
                    FacesUtils.checkInteger(txtEshiId_EstadoHistoriaUsuario))
                : null);
            entity.setRequerimiento((FacesUtils.checkInteger(
                    txtRequId_Requerimiento) != null)
                ? businessDelegatorView.getRequerimiento(
                    FacesUtils.checkInteger(txtRequId_Requerimiento)) : null);
            entity.setUsuario((FacesUtils.checkInteger(txtUsuaId_Usuario) != null)
                ? businessDelegatorView.getUsuario(FacesUtils.checkInteger(
                        txtUsuaId_Usuario)) : null);
            businessDelegatorView.updateHistoriaDeUsuario(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedHistoriaDeUsuario = (HistoriaDeUsuarioDTO) (evt.getComponent()
                                                                   .getAttributes()
                                                                   .get("selectedHistoriaDeUsuario"));

            Integer hiusId = new Integer(selectedHistoriaDeUsuario.getHiusId());
            entity = businessDelegatorView.getHistoriaDeUsuario(hiusId);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Integer hiusId = FacesUtils.checkInteger(txtHiusId);
            entity = businessDelegatorView.getHistoriaDeUsuario(hiusId);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteHistoriaDeUsuario(entity);
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
            selectedHistoriaDeUsuario = (HistoriaDeUsuarioDTO) (evt.getComponent()
                                                                   .getAttributes()
                                                                   .get("selectedHistoriaDeUsuario"));

            Integer hiusId = new Integer(selectedHistoriaDeUsuario.getHiusId());
            entity = businessDelegatorView.getHistoriaDeUsuario(hiusId);
            businessDelegatorView.deleteHistoriaDeUsuario(entity);
            data.remove(selectedHistoriaDeUsuario);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(String detalleHistoria, Integer hiusId,
        String tituloHistoria, Integer eshiId_EstadoHistoriaUsuario,
        Integer requId_Requerimiento, Integer usuaId_Usuario)
        throws Exception {
        try {
            entity.setDetalleHistoria(FacesUtils.checkString(detalleHistoria));
            entity.setTituloHistoria(FacesUtils.checkString(tituloHistoria));
            businessDelegatorView.updateHistoriaDeUsuario(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("HistoriaDeUsuarioView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtDetalleHistoria() {
        return txtDetalleHistoria;
    }

    public void setTxtDetalleHistoria(InputText txtDetalleHistoria) {
        this.txtDetalleHistoria = txtDetalleHistoria;
    }

    public InputText getTxtTituloHistoria() {
        return txtTituloHistoria;
    }

    public void setTxtTituloHistoria(InputText txtTituloHistoria) {
        this.txtTituloHistoria = txtTituloHistoria;
    }

    public InputText getTxtEshiId_EstadoHistoriaUsuario() {
        return txtEshiId_EstadoHistoriaUsuario;
    }

    public void setTxtEshiId_EstadoHistoriaUsuario(
        InputText txtEshiId_EstadoHistoriaUsuario) {
        this.txtEshiId_EstadoHistoriaUsuario = txtEshiId_EstadoHistoriaUsuario;
    }

    public InputText getTxtRequId_Requerimiento() {
        return txtRequId_Requerimiento;
    }

    public void setTxtRequId_Requerimiento(InputText txtRequId_Requerimiento) {
        this.txtRequId_Requerimiento = txtRequId_Requerimiento;
    }

    public InputText getTxtUsuaId_Usuario() {
        return txtUsuaId_Usuario;
    }

    public void setTxtUsuaId_Usuario(InputText txtUsuaId_Usuario) {
        this.txtUsuaId_Usuario = txtUsuaId_Usuario;
    }

    public InputText getTxtHiusId() {
        return txtHiusId;
    }

    public void setTxtHiusId(InputText txtHiusId) {
        this.txtHiusId = txtHiusId;
    }

    public List<HistoriaDeUsuarioDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataHistoriaDeUsuario();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<HistoriaDeUsuarioDTO> historiaDeUsuarioDTO) {
        this.data = historiaDeUsuarioDTO;
    }

    public HistoriaDeUsuarioDTO getSelectedHistoriaDeUsuario() {
        return selectedHistoriaDeUsuario;
    }

    public void setSelectedHistoriaDeUsuario(
        HistoriaDeUsuarioDTO historiaDeUsuario) {
        this.selectedHistoriaDeUsuario = historiaDeUsuario;
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
