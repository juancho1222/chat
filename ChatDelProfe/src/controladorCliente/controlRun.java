package controladorCliente;

import java.io.IOException;

import vistaCliente.vistaIp;

public class controlRun {
	public controlRun() throws IOException  {
		 vistaIp pa=new vistaIp();
		 pa.pedirIp();
		 Gestor cli =new Gestor();
		
	    
	}
}
