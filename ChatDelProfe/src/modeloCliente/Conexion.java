package modeloCliente;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import controladorCliente.Cliente;
import modeloServidor.Propiedades;

public class Conexion {
	
	private Socket comunication;
	private Socket comunication2;
	
	public Socket getComunication() {
		return comunication;
	}

	public Socket getComunication2() {
		return comunication2;
	}

	public Propiedades getProp() {
		return prop;
	}

	private Propiedades prop;

	public Conexion(String IPCliente) throws IOException {
		prop= new Propiedades(); 
		
		comunication = new Socket(IPCliente,prop.getEnvioPort() );
        comunication2 = new Socket(IPCliente,prop.getReciboPort());
	}
}
