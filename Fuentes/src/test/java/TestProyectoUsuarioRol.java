import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.dsdsoft.sgp.dataaccess.dao.IProyectoUsuarioRolDAO;
import com.dsdsoft.sgp.modelo.Ciudad;
import com.dsdsoft.sgp.modelo.Cliente;
import com.dsdsoft.sgp.modelo.Usuario;
import com.dsdsoft.sgp.modelo.control.ClienteLogic;
import com.dsdsoft.sgp.modelo.control.ICiudadLogic;
import com.dsdsoft.sgp.modelo.control.IClienteLogic;
import com.dsdsoft.sgp.modelo.control.IUsuarioLogic;
import com.dsdsoft.sgp.modelo.dto.ProyectoUsuarioRolDTO;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@TransactionConfiguration(defaultRollback = false)
@Transactional
public class TestProyectoUsuarioRol {
	private static final Logger log = LoggerFactory.getLogger(ClienteLogic.class);
	
	@Autowired
	IProyectoUsuarioRolDAO proyectoUsuarioRolDAO;
	
	
	@Test
	public void consultar(){
		try {
			List<ProyectoUsuarioRolDTO> lista = proyectoUsuarioRolDAO.listaProyectoUsuarioRolDadoProyecto(5);
			for (ProyectoUsuarioRolDTO proyectoUsuarioRolDTO : lista) {
				log.info(proyectoUsuarioRolDTO.getEmailUsuario()+", "+proyectoUsuarioRolDTO.getNombreCompletoUsuario());
			}
			log.info("Ha terminado de leer la lista");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

}
