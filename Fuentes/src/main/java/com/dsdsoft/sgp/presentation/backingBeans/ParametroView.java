package com.dsdsoft.sgp.presentation.backingBeans;

import com.dsdsoft.sgp.exceptions.*;
import com.dsdsoft.sgp.modelo.*;
import com.dsdsoft.sgp.modelo.dto.ParametroDTO;
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
public class ParametroView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(ParametroView.class);
    private InputText txtDescripcionParametro;
    private InputText txtNombreCorto;
    private InputText txtValorParametro;
    private InputText txtIdPara;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<ParametroDTO> data;
    private ParametroDTO selectedParametro;
    private Parametro entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public ParametroView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            ParametroDTO parametroDTO = (ParametroDTO) e.getObject();

            if (txtDescripcionParametro == null) {
                txtDescripcionParametro = new InputText();
            }

            txtDescripcionParametro.setValue(parametroDTO.getDescripcionParametro());

            if (txtNombreCorto == null) {
                txtNombreCorto = new InputText();
            }

            txtNombreCorto.setValue(parametroDTO.getNombreCorto());

            if (txtValorParametro == null) {
                txtValorParametro = new InputText();
            }

            txtValorParametro.setValue(parametroDTO.getValorParametro());

            if (txtIdPara == null) {
                txtIdPara = new InputText();
            }

            txtIdPara.setValue(parametroDTO.getIdPara());

            Integer idPara = FacesUtils.checkInteger(txtIdPara);
            entity = businessDelegatorView.getParametro(idPara);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedParametro = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedParametro = null;

        if (txtDescripcionParametro != null) {
            txtDescripcionParametro.setValue(null);
            txtDescripcionParametro.setDisabled(true);
        }

        if (txtNombreCorto != null) {
            txtNombreCorto.setValue(null);
            txtNombreCorto.setDisabled(true);
        }

        if (txtValorParametro != null) {
            txtValorParametro.setValue(null);
            txtValorParametro.setDisabled(true);
        }

        if (txtIdPara != null) {
            txtIdPara.setValue(null);
            txtIdPara.setDisabled(false);
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
            Integer idPara = FacesUtils.checkInteger(txtIdPara);
            entity = (idPara != null)
                ? businessDelegatorView.getParametro(idPara) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtDescripcionParametro.setDisabled(false);
            txtNombreCorto.setDisabled(false);
            txtValorParametro.setDisabled(false);
            txtIdPara.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtDescripcionParametro.setValue(entity.getDescripcionParametro());
            txtDescripcionParametro.setDisabled(false);
            txtNombreCorto.setValue(entity.getNombreCorto());
            txtNombreCorto.setDisabled(false);
            txtValorParametro.setValue(entity.getValorParametro());
            txtValorParametro.setDisabled(false);
            txtIdPara.setValue(entity.getIdPara());
            txtIdPara.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedParametro = (ParametroDTO) (evt.getComponent().getAttributes()
                                               .get("selectedParametro"));
        txtDescripcionParametro.setValue(selectedParametro.getDescripcionParametro());
        txtDescripcionParametro.setDisabled(false);
        txtNombreCorto.setValue(selectedParametro.getNombreCorto());
        txtNombreCorto.setDisabled(false);
        txtValorParametro.setValue(selectedParametro.getValorParametro());
        txtValorParametro.setDisabled(false);
        txtIdPara.setValue(selectedParametro.getIdPara());
        txtIdPara.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedParametro == null) && (entity == null)) {
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
            entity = new Parametro();

            Integer idPara = FacesUtils.checkInteger(txtIdPara);

            entity.setDescripcionParametro(FacesUtils.checkString(
                    txtDescripcionParametro));
            entity.setIdPara(idPara);
            entity.setNombreCorto(FacesUtils.checkString(txtNombreCorto));
            entity.setValorParametro(FacesUtils.checkString(txtValorParametro));
            businessDelegatorView.saveParametro(entity);
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
                Integer idPara = new Integer(selectedParametro.getIdPara());
                entity = businessDelegatorView.getParametro(idPara);
            }

            entity.setDescripcionParametro(FacesUtils.checkString(
                    txtDescripcionParametro));
            entity.setNombreCorto(FacesUtils.checkString(txtNombreCorto));
            entity.setValorParametro(FacesUtils.checkString(txtValorParametro));
            businessDelegatorView.updateParametro(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedParametro = (ParametroDTO) (evt.getComponent()
                                                   .getAttributes()
                                                   .get("selectedParametro"));

            Integer idPara = new Integer(selectedParametro.getIdPara());
            entity = businessDelegatorView.getParametro(idPara);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Integer idPara = FacesUtils.checkInteger(txtIdPara);
            entity = businessDelegatorView.getParametro(idPara);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteParametro(entity);
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
            selectedParametro = (ParametroDTO) (evt.getComponent()
                                                   .getAttributes()
                                                   .get("selectedParametro"));

            Integer idPara = new Integer(selectedParametro.getIdPara());
            entity = businessDelegatorView.getParametro(idPara);
            businessDelegatorView.deleteParametro(entity);
            data.remove(selectedParametro);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(String descripcionParametro,
        Integer idPara, String nombreCorto, String valorParametro)
        throws Exception {
        try {
            entity.setDescripcionParametro(FacesUtils.checkString(
                    descripcionParametro));
            entity.setNombreCorto(FacesUtils.checkString(nombreCorto));
            entity.setValorParametro(FacesUtils.checkString(valorParametro));
            businessDelegatorView.updateParametro(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("ParametroView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtDescripcionParametro() {
        return txtDescripcionParametro;
    }

    public void setTxtDescripcionParametro(InputText txtDescripcionParametro) {
        this.txtDescripcionParametro = txtDescripcionParametro;
    }

    public InputText getTxtNombreCorto() {
        return txtNombreCorto;
    }

    public void setTxtNombreCorto(InputText txtNombreCorto) {
        this.txtNombreCorto = txtNombreCorto;
    }

    public InputText getTxtValorParametro() {
        return txtValorParametro;
    }

    public void setTxtValorParametro(InputText txtValorParametro) {
        this.txtValorParametro = txtValorParametro;
    }

    public InputText getTxtIdPara() {
        return txtIdPara;
    }

    public void setTxtIdPara(InputText txtIdPara) {
        this.txtIdPara = txtIdPara;
    }

    public List<ParametroDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataParametro();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<ParametroDTO> parametroDTO) {
        this.data = parametroDTO;
    }

    public ParametroDTO getSelectedParametro() {
        return selectedParametro;
    }

    public void setSelectedParametro(ParametroDTO parametro) {
        this.selectedParametro = parametro;
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
