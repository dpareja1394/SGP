package com.dsdsoft.sgp.presentation.backingBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import org.primefaces.component.calendar.Calendar;
import org.primefaces.component.inputtextarea.InputTextarea;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dsdsoft.sgp.modelo.TipoActividad;
import com.dsdsoft.sgp.modelo.Usuario;
import com.dsdsoft.sgp.presentation.businessDelegate.IBusinessDelegatorView;
import com.dsdsoft.sgp.utilities.FacesUtils;

/**
 * @author Daniel Pareja Londoño
 * @version jul. 30, 2019
 * @since 1.8
 */
@ManagedBean
@ViewScoped
public class RegistroActividadesView implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(RegistroActividadesView.class);

	@ManagedProperty(value = "#{BusinessDelegatorView}")
	private IBusinessDelegatorView businessDelegatorView;

	private String usuarioIniciado;

	private Usuario usuario;
	
	/**
	 * Atributos entidad {Actividad}
	 */
	
	private SelectOneMenu somTipoActividad;
	private List<SelectItem> siTipoActividad;
	private InputTextarea txtDescripcionActividad;
	private Calendar calFechaHoraInicio, calFechaHoraFin;

	/**
	 * @author Daniel Pareja Londoño
	 * @version jul. 30, 2019
	 * @since 1.8
	 * @return <b>{@code }</b> Start here...
	 */
	public RegistroActividadesView() {
		super();
		usuarioIniciado = FacesUtils.getHttpSession(true).getAttribute("usuario_iniciado").toString();
	}

	/* Métodos y acciones */

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version may. 28, 2019
	 * @since 1.8
	 * @return El/La businessDelegatorView
	 *
	 */
	public IBusinessDelegatorView getBusinessDelegatorView() {
		return businessDelegatorView;
	}

	/**
	 *
	 * @param businessDelegatorView El/La businessDelegatorView a setear
	 * @author Daniel Pareja Londoño
	 * @version may. 28, 2019
	 * @since 1.8
	 *
	 */
	public void setBusinessDelegatorView(IBusinessDelegatorView businessDelegatorView) {
		this.businessDelegatorView = businessDelegatorView;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version may. 28, 2019
	 * @since 1.8
	 * @return El/La usuarioIniciado
	 *
	 */
	public String getUsuarioIniciado() {
		return usuarioIniciado;
	}

	/**
	 *
	 * @param usuarioIniciado El/La usuarioIniciado a setear
	 * @author Daniel Pareja Londoño
	 * @version may. 28, 2019
	 * @since 1.8
	 *
	 */
	public void setUsuarioIniciado(String usuarioIniciado) {
		this.usuarioIniciado = usuarioIniciado;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version may. 28, 2019
	 * @since 1.8
	 * @return El/La usuario
	 *
	 */
	public Usuario getUsuario() {
		return usuario;
	}

	/**
	 *
	 * @param usuario El/La usuario a setear
	 * @author Daniel Pareja Londoño
	 * @version may. 28, 2019
	 * @since 1.8
	 *
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version jul. 30, 2019
	 * @since 1.8
	 * @return El/La somTipoActividad
	 *
	 */
	public SelectOneMenu getSomTipoActividad() {
		return somTipoActividad;
	}

	/**
	 *
	 * @param somTipoActividad El/La somTipoActividad a setear
	 * @author Daniel Pareja Londoño
	 * @version jul. 30, 2019
	 * @since 1.8
	 *
	 */
	public void setSomTipoActividad(SelectOneMenu somTipoActividad) {
		this.somTipoActividad = somTipoActividad;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version jul. 30, 2019
	 * @since 1.8
	 * @return El/La siTipoActividad
	 *
	 */
	public List<SelectItem> getSiTipoActividad() {
		try {
			if(siTipoActividad == null) {
				siTipoActividad = new ArrayList<SelectItem>();
				List<TipoActividad> tiposActividad = businessDelegatorView.getTipoActividad();
				for (TipoActividad tipoActividad : tiposActividad) {
					siTipoActividad.add(new SelectItem(tipoActividad.getTiacId(), tipoActividad.getDescripcionTiact()));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return siTipoActividad;
	}

	/**
	 *
	 * @param siTipoActividad El/La siTipoActividad a setear
	 * @author Daniel Pareja Londoño
	 * @version jul. 30, 2019
	 * @since 1.8
	 *
	 */
	public void setSiTipoActividad(List<SelectItem> siTipoActividad) {
		this.siTipoActividad = siTipoActividad;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version jul. 30, 2019
	 * @since 1.8
	 * @return El/La txtDescripcionActividad
	 *
	 */
	public InputTextarea getTxtDescripcionActividad() {
		return txtDescripcionActividad;
	}

	/**
	 *
	 * @param txtDescripcionActividad El/La txtDescripcionActividad a setear
	 * @author Daniel Pareja Londoño
	 * @version jul. 30, 2019
	 * @since 1.8
	 *
	 */
	public void setTxtDescripcionActividad(InputTextarea txtDescripcionActividad) {
		this.txtDescripcionActividad = txtDescripcionActividad;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version jul. 30, 2019
	 * @since 1.8
	 * @return El/La calFechaHoraInicio
	 *
	 */
	public Calendar getCalFechaHoraInicio() {
		return calFechaHoraInicio;
	}

	/**
	 *
	 * @param calFechaHoraInicio El/La calFechaHoraInicio a setear
	 * @author Daniel Pareja Londoño
	 * @version jul. 30, 2019
	 * @since 1.8
	 *
	 */
	public void setCalFechaHoraInicio(Calendar calFechaHoraInicio) {
		this.calFechaHoraInicio = calFechaHoraInicio;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version jul. 30, 2019
	 * @since 1.8
	 * @return El/La calFechaHoraFin
	 *
	 */
	public Calendar getCalFechaHoraFin() {
		return calFechaHoraFin;
	}

	/**
	 *
	 * @param calFechaHoraFin El/La calFechaHoraFin a setear
	 * @author Daniel Pareja Londoño
	 * @version jul. 30, 2019
	 * @since 1.8
	 *
	 */
	public void setCalFechaHoraFin(Calendar calFechaHoraFin) {
		this.calFechaHoraFin = calFechaHoraFin;
	}

}
