package modeloServidor;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Propiedades {
	int envioPort; 
	int reciboPort;
	
	//Metodos getters
	public int getEnvioPort() {
		return envioPort;
	}

	public int getReciboPort() {
		return reciboPort;
	}

	//Constructor
	public Propiedades() throws IOException {
		Properties prop = new Properties();
        FileInputStream input = new FileInputStream("src/DATAServidor/IPs.properties");
        prop.load(input);
        
        envioPort = Integer.parseInt(prop.getProperty("envioIP"));
        reciboPort = Integer.parseInt(prop.getProperty("reciboIP"));
	}
}
