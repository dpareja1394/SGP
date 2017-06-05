import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.dsdsoft.sgp.modelo.Ciudad;
import com.dsdsoft.sgp.modelo.Cliente;
import com.dsdsoft.sgp.modelo.control.ClienteLogic;
import com.dsdsoft.sgp.modelo.control.ICiudadLogic;
import com.dsdsoft.sgp.modelo.control.IClienteLogic;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@TransactionConfiguration(defaultRollback = false)
@Transactional
public class TestClientes {
	private static final Logger log = LoggerFactory.getLogger(ClienteLogic.class);
	
	@Autowired
	IClienteLogic clienteLogic;
	
	@Autowired
	ICiudadLogic ciudadLogic;
	
	@Test
	public void test() {
		String url = "https://www.laurl.com.co";
		String urlSinHttps = "www.laurl.com.co";
		log.info(url.trim().substring(0, 4));
		log.info(urlSinHttps.trim().substring(0, 4));
	}
	
	@Test
	public void save(){
		try {
			Cliente cliente = new Cliente();
			cliente.setCelularContacto("3002302020");
			
			Ciudad ciudad = ciudadLogic.getCiudad(1);
			cliente.setCiudad(ciudad);
			
			cliente.setDireccionContacto("Calle carrera");
			cliente.setEnlaceWeb("www.prueba1.com");
			cliente.setNit("94091304648-1");
			cliente.setNombreContacto("Prueba Contacto");
			cliente.setNombreEmpresa("La Empresa");
			
			clienteLogic.registrarNuevoCliente(cliente);
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

}
