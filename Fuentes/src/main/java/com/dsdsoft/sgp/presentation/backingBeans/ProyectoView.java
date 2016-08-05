package com.dsdsoft.sgp.presentation.backingBeans;

import com.dsdsoft.sgp.exceptions.*;
import com.dsdsoft.sgp.modelo.*;
import com.dsdsoft.sgp.modelo.dto.ProyectoDTO;
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
public class ProyectoView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(ProyectoView.class);
    private InputText txtDescProyecto;
    private InputText txtClieId_Cliente;
    private InputText txtEsprId_EstadoProyecto;
    private InputText txtProyId;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<ProyectoDTO> data;
    private ProyectoDTO selectedProyecto;
    private Proyecto entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public ProyectoView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            ProyectoDTO proyectoDTO = (ProyectoDTO) e.getObject();

            if (txtDescProyecto == null) {
                txtDescProyecto = new InputText();
            }

            txtDescProyecto.setValue(proyectoDTO.getDescProyecto());

            if (txtClieId_Cliente == null) {
                txtClieId_Cliente = new InputText();
            }

            txtClieId_Cliente.setValue(proyectoDTO.getClieId_Cliente());

            if (txtEsprId_EstadoProyecto == null) {
                txtEsprId_EstadoProyecto = new InputText();
            }

            txtEsprId_EstadoProyecto.setValue(proyectoDTO.getEsprId_EstadoProyecto());

            if (txtProyId == null) {
                txtProyId = new InputText();
            }

            txtProyId.setValue(proyectoDTO.getProyId());

            Integer proyId = FacesUtils.checkInteger(txtProyId);
            entity = businessDelegatorView.getProyecto(proyId);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedProyecto = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedProyecto = null;

        if (txtDescProyecto != null) {
            txtDescProyecto.setValue(null);
            txtDescProyecto.setDisabled(true);
        }

        if (txtClieId_Cliente != null) {
            txtClieId_Cliente.setValue(null);
            txtClieId_Cliente.setDisabled(true);
        }

        if (txtEsprId_EstadoProyecto != null) {
            txtEsprId_EstadoProyecto.setValue(null);
            txtEsprId_EstadoProyecto.setDisabled(true);
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
            Integer proyId = FacesUtils.checkInteger(txtProyId);
            entity = (proyId != null)
                ? businessDelegatorView.getProyecto(proyId) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtDescProyecto.setDisabled(false);
            txtClieId_Cliente.setDisabled(false);
            txtEsprId_EstadoProyecto.setDisabled(false);
            txtProyId.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtDescProyecto.setValue(entity.getDescProyecto());
            txtDescProyecto.setDisabled(false);
            txtClieId_Cliente.setValue(entity.getCliente().getClieId());
            txtClieId_Cliente.setDisabled(false);
            txtEsprId_EstadoProyecto.setValue(entity.getEstadoProyecto()
                                                    .getEsprId());
            txtEsprId_EstadoProyecto.setDisabled(false);
            txtProyId.setValue(entity.getProyId());
            txtProyId.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedProyecto = (ProyectoDTO) (evt.getComponent().getAttributes()
                                             .get("selectedProyecto"));
        txtDescProyecto.setValue(selectedProyecto.getDescProyecto());
        txtDescProyecto.setDisabled(false);
        txtClieId_Cliente.setValue(selectedProyecto.getClieId_Cliente());
        txtClieId_Cliente.setDisabled(false);
        txtEsprId_EstadoProyecto.setValue(selectedProyecto.getEsprId_EstadoProyecto());
        txtEsprId_EstadoProyecto.setDisabled(false);
        txtProyId.setValue(selectedProyecto.getProyId());
        txtProyId.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedProyecto == null) && (entity == null)) {
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
            entity = new Proyecto();

            Integer proyId = FacesUtils.checkInteger(txtProyId);

            entity.setDescProyecto(FacesUtils.checkString(txtDescProyecto));
            entity.setProyId(proyId);
            entity.setCliente((FacesUtils.checkInteger(txtClieId_Cliente) != null)
                ? businessDelegatorView.getCliente(FacesUtils.checkInteger(
                        txtClieId_Cliente)) : null);
            entity.setEstadoProyecto((FacesUtils.checkInteger(
                    txtEsprId_EstadoProyecto) != null)
                ? businessDelegatorView.getEstadoProyecto(
                    FacesUtils.checkInteger(txtEsprId_EstadoProyecto)) : null);
            businessDelegatorView.saveProyecto(entity);
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
                Integer proyId = new Integer(selectedProyecto.getProyId());
                entity = businessDelegatorView.getProyecto(proyId);
            }

            entity.setDescProyecto(FacesUtils.checkString(txtDescProyecto));
            entity.setCliente((FacesUtils.checkInteger(txtClieId_Cliente) != null)
                ? businessDelegatorView.getCliente(FacesUtils.checkInteger(
                        txtClieId_Cliente)) : null);
            entity.setEstadoProyecto((FacesUtils.checkInteger(
                    txtEsprId_EstadoProyecto) != null)
                ? businessDelegatorView.getEstadoProyecto(
                    FacesUtils.checkInteger(txtEsprId_EstadoProyecto)) : null);
            businessDelegatorView.updateProyecto(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedProyecto = (ProyectoDTO) (evt.getComponent().getAttributes()
                                                 .get("selectedProyecto"));

            Integer proyId = new Integer(selectedProyecto.getProyId());
            entity = businessDelegatorView.getProyecto(proyId);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Integer proyId = FacesUtils.checkInteger(txtProyId);
            entity = businessDelegatorView.getProyecto(proyId);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteProyecto(entity);
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
            selectedProyecto = (ProyectoDTO) (evt.getComponent().getAttributes()
                                                 .get("selectedProyecto"));

            Integer proyId = new Integer(selectedProyecto.getProyId());
            entity = businessDelegatorView.getProyecto(proyId);
            businessDelegatorView.deleteProyecto(entity);
            data.remove(selectedProyecto);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(String descProyecto, Integer proyId,
        Integer clieId_Cliente, Integer esprId_EstadoProyecto)
        throws Exception {
        try {
            entity.setDescProyecto(FacesUtils.checkString(descProyecto));
            businessDelegatorView.updateProyecto(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("ProyectoView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtDescProyecto() {
        return txtDescProyecto;
    }

    public void setTxtDescProyecto(InputText txtDescProyecto) {
        this.txtDescProyecto = txtDescProyecto;
    }

    public InputText getTxtClieId_Cliente() {
        return txtClieId_Cliente;
    }

    public void setTxtClieId_Cliente(InputText txtClieId_Cliente) {
        this.txtClieId_Cliente = txtClieId_Cliente;
    }

    public InputText getTxtEsprId_EstadoProyecto() {
        return txtEsprId_EstadoProyecto;
    }

    public void setTxtEsprId_EstadoProyecto(InputText txtEsprId_EstadoProyecto) {
        this.txtEsprId_EstadoProyecto = txtEsprId_EstadoProyecto;
    }

    public InputText getTxtProyId() {
        return txtProyId;
    }

    public void setTxtProyId(InputText txtProyId) {
        this.txtProyId = txtProyId;
    }

    public List<ProyectoDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataProyecto();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<ProyectoDTO> proyectoDTO) {
        this.data = proyectoDTO;
    }

    public ProyectoDTO getSelectedProyecto() {
        return selectedProyecto;
    }

    public void setSelectedProyecto(ProyectoDTO proyecto) {
        this.selectedProyecto = proyecto;
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
