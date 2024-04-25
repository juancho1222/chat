package modeloServidor;


import java.io.IOException;
import java.net.ServerSocket;


public class Conexion {
	private ServerSocket serv; //para comunicacion
    private ServerSocket serv2; //para enviar mensajes
    private Propiedades prop;
    public Conexion() throws IOException {
    	prop= new Propiedades();    
    	
        serv = new ServerSocket(prop.getEnvioPort());
        serv2 = new ServerSocket(prop.getReciboPort());

    }
    //GETTERS Y SETTERS
	public ServerSocket getServ() {
		return serv;
	}
	public void setServ(ServerSocket serv) {
		this.serv = serv;
	}
	public ServerSocket getServ2() {
		return serv2;
	}
	public void setServ2(ServerSocket serv2) {
		this.serv2 = serv2;
	}
    
    
}
