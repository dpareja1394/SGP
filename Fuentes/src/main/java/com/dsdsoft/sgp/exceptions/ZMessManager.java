package com.dsdsoft.sgp.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Zathura Code Generator http://code.google.com/p/zathura/
 * www.zathuracode.org
 * 
 */
public class ZMessManager extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	private static final Logger log = LoggerFactory.getLogger(ZMessManager.class);

	public final static String ALL = "TODO(S) ";
	public final static String ENTCHILD = "Tablas relacionadas(menores)";
	public final static String FOREIGNDATA = "Datos de clases externos ";
	public static String ENTITY_SUCCESFULLYSAVED = "Creado exitosamente";
	public static String ENTITY_SUCCESFULLYDELETED = "Eliminado exitosamente";
	public static String ENTITY_SUCCESFULLYMODIFIED = "Modificado exitosamente";
	public static String ENTITY_WITHSAMEKEY = "Hay otra entidad con el mismo ID";
	public static String ENTITY_NOENTITYTOUPDATE = "Ninguna entidad se encontro, con el id especificado";

	public ZMessManager() {
	}

	public ZMessManager(String exception) {
		super(exception);
	}

	public class NotValidFieldException extends ZMessManager {
		private static final long serialVersionUID = 1L;

		public NotValidFieldException(String info) {
			super("El valor para el campo: \"" + info + "\" no es valido");
		}
	}
	
	public class NullEntityExcepcion extends ZMessManager {
		private static final long serialVersionUID = 1L;

		public NullEntityExcepcion(String info) {
			super("La entidad" + info + " no puede estar vacia o nula");
		}
	}

	public class EmptyFieldException extends ZMessManager {
		private static final long serialVersionUID = 1L;

		public EmptyFieldException(String info) {
			super("El valor para el campo: \"" + info
					+ "\" no puede estar vacio o nulo");
		}
	}

	public class NotValidFormatException extends ZMessManager {
		private static final long serialVersionUID = 1L;

		public NotValidFormatException(String info) {
			super("El tipo de dato o la longitud para el campo: \"" + info
					+ "\" no es valido");
		}
	}

	public class DeletingException extends ZMessManager {
		private static final long serialVersionUID = 1L;

		public DeletingException(String info) {
			super("La entidad que intenta borrar "
					+ "tiene informacion relacionada con otras entidades, "
					+ "porfavor antes de volver a intentarlo, "
					+ "compruebe los datos de la entidad, \"" + info+"\"");
		}
	}
	
	public class ForeignException extends ZMessManager {
		private static final long serialVersionUID = 1L;

		public ForeignException(String info) {
			super("No se encontro informacion relacionada con \"" + info+ "\"");
		}
	}	

	public class GettingException extends ZMessManager {
		private static final long serialVersionUID = 1L;

		public GettingException(String info) {
			super("Ocurrio una excepcion obteniendo " + info);
		}
	}

	public class FindingException extends ZMessManager {
		private static final long serialVersionUID = 1L;

		public FindingException(String info) {
			super("Ocurrio una excepcion tratando de encontrar " + info);
		}
	}

}
