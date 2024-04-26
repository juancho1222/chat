package modeloCliente;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Propiedades {
	int envioPort; 
	int reciboPort;
	String words;
	String[] palabrasGroseras;
	
    
	//Metodos getters
	public int getEnvioPort() {
		return envioPort;
	}

	public int getReciboPort() {
		return reciboPort;
	}
	
	public String[] getPalabrasGroseras() {
		return palabrasGroseras;
	}


		//Constructor
		public Propiedades() throws IOException {	
			Properties prop = new Properties();
		    FileInputStream input = new FileInputStream("src/DATACliente/Cliente.properties");
			prop.load(input);
			
			words = prop.getProperty("badWords");
			palabrasGroseras=words.split(",");
			
	        envioPort = Integer.parseInt(prop.getProperty("envioIP"));
	        reciboPort = Integer.parseInt(prop.getProperty("reciboIP"));
		}
}
