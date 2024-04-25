package controlServidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import modeloServidor.Conexion;
import vistaServidor.WServidor;

public class Gestor {
	WServidor vista = new WServidor();
	
	public Gestor() {
		runServer();
	}
	public void runServer()
	   {
	      boolean listening=true;
	      try{
	         Conexion con=new Conexion();
	         vista.mostrar(".::Servidor activo :");
	         while(listening)
	         {
	            Socket sock=null,sock2=null;
	            try {
	               vista.mostrar("Esperando Usuarios");
	               sock=con.getServ().accept();
	               sock2=con.getServ2().accept();
	            } catch (IOException e)
	            {
	               vista.mostrar("Accept failed: " + con.getServ() + ", " + e.getMessage());
	               continue;
	            }
	            threadServidor user=new threadServidor(sock,sock2,this);            
		    user.start();
	         }
	         
	      }catch(IOException e){
	         vista.mostrar("error :"+e);
	      }
	   }
}
