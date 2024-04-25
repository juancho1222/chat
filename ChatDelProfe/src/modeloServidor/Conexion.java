package modeloServidor;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.Properties;

public class Conexion {
	private ServerSocket serv; //para comunicacion
    private ServerSocket serv2; //para enviar mensajes
    public Conexion() throws IOException {
    	 Properties prop = new Properties();
         FileInputStream input = new FileInputStream("src/DATA/IPs.properties");
         prop.load(input);
         
         int envioPort = Integer.parseInt(prop.getProperty("envioIP"));
         int reciboPort = Integer.parseInt(prop.getProperty("reciboIP"));
         
         serv = new ServerSocket(envioPort);
         serv2 = new ServerSocket(reciboPort);

         input.close();
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
