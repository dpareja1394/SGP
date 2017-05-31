import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dsdsoft.sgp.modelo.control.ClienteLogic;

public class TestClientes {
	private static final Logger log = LoggerFactory.getLogger(ClienteLogic.class);
	
	@Test
	public void test() {
		String url = "https://www.laurl.com.co";
		String urlSinHttps = "www.laurl.com.co";
		log.info(url.trim().substring(0, 4));
		log.info(urlSinHttps.trim().substring(0, 4));
	}

}
