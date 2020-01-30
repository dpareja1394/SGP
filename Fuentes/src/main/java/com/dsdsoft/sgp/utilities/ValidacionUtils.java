/**
 *
 * @author Daniel Pareja Londoño
 * @version ene. 29, 2020
 * @since 1.8
 *
 */
package com.dsdsoft.sgp.utilities;

import java.util.List;

/**
 *
 * @author Daniel Pareja Londoño
 * @version ene. 29, 2020
 * @since 1.8
 *
 */
public class ValidacionUtils {
	/**
	 * Validación si un objeto cualquiera es nulo
	 *
	 * @author Daniel Pareja Londoño
	 * @version ene. 29, 2020
	 * @since 1.8
	 * @param object
	 * @return
	 * @return <b>{@code }</b> Start here...
	 *
	 */
	public static boolean esNulo(Object object) {
		return object == null;
	}

	/**
	 * Validación si una cadena es vacía
	 * 
	 * @author Daniel Pareja Londoño
	 * @version ene. 29, 2020
	 * @since 1.8
	 * @param cadena
	 * @return
	 * @return <b>{@code }</b> Start here...
	 *
	 */
	public static boolean cadenaVacia(String cadena) {
		return cadena.trim().equals("");
	}

	/**
	 * Validación si una cadena es nula o vacía
	 *
	 * @author Daniel Pareja Londoño
	 * @version ene. 29, 2020
	 * @since 1.8
	 * @param cadena
	 * @return
	 * @return <b>{@code }</b> Start here...
	 *
	 */
	public static boolean cadenaNulaOVacia(String cadena) {
		return esNulo(cadena) || cadenaVacia(cadena);
	}

	/**
	 * Validar si una lista está vacía
	 * 
	 * @author Daniel Pareja Londoño
	 * @version ene. 29, 2020
	 * @since 1.8
	 * @param lista
	 * @return
	 * @return <b>{@code }</b> Start here...
	 *
	 */
	public static boolean listaVacia(List<Object> lista) {
		return lista.isEmpty();
	}

	/**
	 * Validar si una lista es nula o es vacía
	 *
	 * @author Daniel Pareja Londoño
	 * @version ene. 29, 2020
	 * @since 1.8
	 * @param lista
	 * @return
	 * @return <b>{@code }</b> Start here...
	 *
	 */
	public static boolean listaNulaOVacia(List<Object> lista) {
		return esNulo(lista) || listaVacia(lista);
	}

	public static boolean cadenaSuperaTamano(String cadena, Integer tamano) {
		return cadena.trim().length() > tamano;
	}

}
