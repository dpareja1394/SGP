package com.dsdsoft.sgp.presentation.backingBeans;

import com.dsdsoft.sgp.exceptions.*;
import com.dsdsoft.sgp.modelo.*;
import com.dsdsoft.sgp.modelo.dto.ClienteDTO;
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
public class ClienteView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(ClienteView.class);
    private InputText txtCelularContacto;
    private InputText txtDireccionContacto;
    private InputText txtEnlaceWeb;
    private InputText txtNit;
    private InputText txtNombreContacto;
    private InputText txtNombreEmpresa;
    private InputText txtTelefonoContacto;
    private InputText txtClieId;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<ClienteDTO> data;
    private ClienteDTO selectedCliente;
    private Cliente entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public ClienteView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            ClienteDTO clienteDTO = (ClienteDTO) e.getObject();

            if (txtCelularContacto == null) {
                txtCelularContacto = new InputText();
            }

            txtCelularContacto.setValue(clienteDTO.getCelularContacto());

            if (txtDireccionContacto == null) {
                txtDireccionContacto = new InputText();
            }

            txtDireccionContacto.setValue(clienteDTO.getDireccionContacto());

            if (txtEnlaceWeb == null) {
                txtEnlaceWeb = new InputText();
            }

            txtEnlaceWeb.setValue(clienteDTO.getEnlaceWeb());

            if (txtNit == null) {
                txtNit = new InputText();
            }

            txtNit.setValue(clienteDTO.getNit());

            if (txtNombreContacto == null) {
                txtNombreContacto = new InputText();
            }

            txtNombreContacto.setValue(clienteDTO.getNombreContacto());

            if (txtNombreEmpresa == null) {
                txtNombreEmpresa = new InputText();
            }

            txtNombreEmpresa.setValue(clienteDTO.getNombreEmpresa());

            if (txtTelefonoContacto == null) {
                txtTelefonoContacto = new InputText();
            }

            txtTelefonoContacto.setValue(clienteDTO.getTelefonoContacto());

            if (txtClieId == null) {
                txtClieId = new InputText();
            }

            txtClieId.setValue(clienteDTO.getClieId());

            Integer clieId = FacesUtils.checkInteger(txtClieId);
            entity = businessDelegatorView.getCliente(clieId);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedCliente = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedCliente = null;

        if (txtCelularContacto != null) {
            txtCelularContacto.setValue(null);
            txtCelularContacto.setDisabled(true);
        }

        if (txtDireccionContacto != null) {
            txtDireccionContacto.setValue(null);
            txtDireccionContacto.setDisabled(true);
        }

        if (txtEnlaceWeb != null) {
            txtEnlaceWeb.setValue(null);
            txtEnlaceWeb.setDisabled(true);
        }

        if (txtNit != null) {
            txtNit.setValue(null);
            txtNit.setDisabled(true);
        }

        if (txtNombreContacto != null) {
            txtNombreContacto.setValue(null);
            txtNombreContacto.setDisabled(true);
        }

        if (txtNombreEmpresa != null) {
            txtNombreEmpresa.setValue(null);
            txtNombreEmpresa.setDisabled(true);
        }

        if (txtTelefonoContacto != null) {
            txtTelefonoContacto.setValue(null);
            txtTelefonoContacto.setDisabled(true);
        }

        if (txtClieId != null) {
            txtClieId.setValue(null);
            txtClieId.setDisabled(false);
        }

        /*if (btnSave != null) {
            btnSave.setDisabled(true);
        }*/

        if (btnDelete != null) {
            btnDelete.setDisabled(true);
        }

        return "";
    }

    public void listener_txtId() {
        try {
            Integer clieId = FacesUtils.checkInteger(txtClieId);
            entity = (clieId != null)
                ? businessDelegatorView.getCliente(clieId) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtCelularContacto.setDisabled(false);
            txtDireccionContacto.setDisabled(false);
            txtEnlaceWeb.setDisabled(false);
            txtNit.setDisabled(false);
            txtNombreContacto.setDisabled(false);
            txtNombreEmpresa.setDisabled(false);
            txtTelefonoContacto.setDisabled(false);
            txtClieId.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtCelularContacto.setValue(entity.getCelularContacto());
            txtCelularContacto.setDisabled(false);
            txtDireccionContacto.setValue(entity.getDireccionContacto());
            txtDireccionContacto.setDisabled(false);
            txtEnlaceWeb.setValue(entity.getEnlaceWeb());
            txtEnlaceWeb.setDisabled(false);
            txtNit.setValue(entity.getNit());
            txtNit.setDisabled(false);
            txtNombreContacto.setValue(entity.getNombreContacto());
            txtNombreContacto.setDisabled(false);
            txtNombreEmpresa.setValue(entity.getNombreEmpresa());
            txtNombreEmpresa.setDisabled(false);
            txtTelefonoContacto.setValue(entity.getTelefonoContacto());
            txtTelefonoContacto.setDisabled(false);
            txtClieId.setValue(entity.getClieId());
            txtClieId.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedCliente = (ClienteDTO) (evt.getComponent().getAttributes()
                                           .get("selectedCliente"));
        txtCelularContacto.setValue(selectedCliente.getCelularContacto());
        txtCelularContacto.setDisabled(false);
        txtDireccionContacto.setValue(selectedCliente.getDireccionContacto());
        txtDireccionContacto.setDisabled(false);
        txtEnlaceWeb.setValue(selectedCliente.getEnlaceWeb());
        txtEnlaceWeb.setDisabled(false);
        txtNit.setValue(selectedCliente.getNit());
        txtNit.setDisabled(false);
        txtNombreContacto.setValue(selectedCliente.getNombreContacto());
        txtNombreContacto.setDisabled(false);
        txtNombreEmpresa.setValue(selectedCliente.getNombreEmpresa());
        txtNombreEmpresa.setDisabled(false);
        txtTelefonoContacto.setValue(selectedCliente.getTelefonoContacto());
        txtTelefonoContacto.setDisabled(false);
        txtClieId.setValue(selectedCliente.getClieId());
        txtClieId.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedCliente == null) && (entity == null)) {
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
            entity = new Cliente();

            Integer clieId = FacesUtils.checkInteger(txtClieId);

            entity.setCelularContacto(FacesUtils.checkString(txtCelularContacto));
            entity.setClieId(clieId);
            entity.setDireccionContacto(FacesUtils.checkString(
                    txtDireccionContacto));
            entity.setEnlaceWeb(FacesUtils.checkString(txtEnlaceWeb));
            entity.setNit(FacesUtils.checkString(txtNit));
            entity.setNombreContacto(FacesUtils.checkString(txtNombreContacto));
            entity.setNombreEmpresa(FacesUtils.checkString(txtNombreEmpresa));
            entity.setTelefonoContacto(FacesUtils.checkString(
                    txtTelefonoContacto));
            businessDelegatorView.saveCliente(entity);
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
                Integer clieId = new Integer(selectedCliente.getClieId());
                entity = businessDelegatorView.getCliente(clieId);
            }

            entity.setCelularContacto(FacesUtils.checkString(txtCelularContacto));
            entity.setDireccionContacto(FacesUtils.checkString(
                    txtDireccionContacto));
            entity.setEnlaceWeb(FacesUtils.checkString(txtEnlaceWeb));
            entity.setNit(FacesUtils.checkString(txtNit));
            entity.setNombreContacto(FacesUtils.checkString(txtNombreContacto));
            entity.setNombreEmpresa(FacesUtils.checkString(txtNombreEmpresa));
            entity.setTelefonoContacto(FacesUtils.checkString(
                    txtTelefonoContacto));
            businessDelegatorView.updateCliente(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedCliente = (ClienteDTO) (evt.getComponent().getAttributes()
                                               .get("selectedCliente"));

            Integer clieId = new Integer(selectedCliente.getClieId());
            entity = businessDelegatorView.getCliente(clieId);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Integer clieId = FacesUtils.checkInteger(txtClieId);
            entity = businessDelegatorView.getCliente(clieId);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteCliente(entity);
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
            selectedCliente = (ClienteDTO) (evt.getComponent().getAttributes()
                                               .get("selectedCliente"));

            Integer clieId = new Integer(selectedCliente.getClieId());
            entity = businessDelegatorView.getCliente(clieId);
            businessDelegatorView.deleteCliente(entity);
            data.remove(selectedCliente);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(String celularContacto, Integer clieId,
        String direccionContacto, String enlaceWeb, String nit,
        String nombreContacto, String nombreEmpresa, String telefonoContacto)
        throws Exception {
        try {
            entity.setCelularContacto(FacesUtils.checkString(celularContacto));
            entity.setDireccionContacto(FacesUtils.checkString(
                    direccionContacto));
            entity.setEnlaceWeb(FacesUtils.checkString(enlaceWeb));
            entity.setNit(FacesUtils.checkString(nit));
            entity.setNombreContacto(FacesUtils.checkString(nombreContacto));
            entity.setNombreEmpresa(FacesUtils.checkString(nombreEmpresa));
            entity.setTelefonoContacto(FacesUtils.checkString(telefonoContacto));
            businessDelegatorView.updateCliente(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("ClienteView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtCelularContacto() {
        return txtCelularContacto;
    }

    public void setTxtCelularContacto(InputText txtCelularContacto) {
        this.txtCelularContacto = txtCelularContacto;
    }

    public InputText getTxtDireccionContacto() {
        return txtDireccionContacto;
    }

    public void setTxtDireccionContacto(InputText txtDireccionContacto) {
        this.txtDireccionContacto = txtDireccionContacto;
    }

    public InputText getTxtEnlaceWeb() {
        return txtEnlaceWeb;
    }

    public void setTxtEnlaceWeb(InputText txtEnlaceWeb) {
        this.txtEnlaceWeb = txtEnlaceWeb;
    }

    public InputText getTxtNit() {
        return txtNit;
    }

    public void setTxtNit(InputText txtNit) {
        this.txtNit = txtNit;
    }

    public InputText getTxtNombreContacto() {
        return txtNombreContacto;
    }

    public void setTxtNombreContacto(InputText txtNombreContacto) {
        this.txtNombreContacto = txtNombreContacto;
    }

    public InputText getTxtNombreEmpresa() {
        return txtNombreEmpresa;
    }

    public void setTxtNombreEmpresa(InputText txtNombreEmpresa) {
        this.txtNombreEmpresa = txtNombreEmpresa;
    }

    public InputText getTxtTelefonoContacto() {
        return txtTelefonoContacto;
    }

    public void setTxtTelefonoContacto(InputText txtTelefonoContacto) {
        this.txtTelefonoContacto = txtTelefonoContacto;
    }

    public InputText getTxtClieId() {
        return txtClieId;
    }

    public void setTxtClieId(InputText txtClieId) {
        this.txtClieId = txtClieId;
    }

    public List<ClienteDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataCliente();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<ClienteDTO> clienteDTO) {
        this.data = clienteDTO;
    }

    public ClienteDTO getSelectedCliente() {
        return selectedCliente;
    }

    public void setSelectedCliente(ClienteDTO cliente) {
        this.selectedCliente = cliente;
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
    
    //DPL 20160703 Método para crear un nuevo cliente.
    public String registrarNuevoCliente() {
        try {
            entity = new Cliente();

            entity.setCelularContacto(FacesUtils.checkString(txtCelularContacto));
            entity.setClieId(null);
            entity.setDireccionContacto(FacesUtils.checkString(
                    txtDireccionContacto));
            entity.setEnlaceWeb(FacesUtils.checkString(txtEnlaceWeb));
            entity.setNit(FacesUtils.checkString(txtNit));
            entity.setNombreContacto(FacesUtils.checkString(txtNombreContacto));
            entity.setNombreEmpresa(FacesUtils.checkString(txtNombreEmpresa));
            entity.setTelefonoContacto(FacesUtils.checkString(
                    txtTelefonoContacto));
            businessDelegatorView.saveCliente(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYSAVED);
            action_clear();
        } catch (Exception e) {
            entity = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    
}
