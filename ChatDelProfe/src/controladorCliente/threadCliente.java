package controladorCliente;

import java.io.DataInputStream;
import java.io.IOException;

import vistaCliente.VentCliente;

public class threadCliente extends Thread{
	   DataInputStream entrada;
	   VentCliente vcli;
	   public threadCliente (DataInputStream entrada,VentCliente vcli) throws IOException
	   {
	      this.entrada=entrada;
	      this.vcli=vcli;
	   }
	   public void run()
	   {
	      String menser="",amigo="";
	      int opcion=0;
	      while(true)
	      {         
	         try{
	            opcion=entrada.readInt();
	            switch(opcion)
	            {
	               case 1://mensage enviado
	                  menser=entrada.readUTF();
	                  vcli.enConsola("ECO del servidor:"+menser);
	                  vcli.mostrarMsg(menser);            
	                  break;
	               case 2://se agrega
	                  menser=entrada.readUTF();
	                  vcli.agregarUser(menser);                  
	                  break;
	               case 3://mensage de amigo
	                  amigo=entrada.readUTF();
	                  menser=entrada.readUTF();
	                  vcli.mensageAmigo(amigo,menser);
	                  vcli.enConsola("ECO del servidor:"+menser);
	                  break;
	            }
	         }
	         catch (IOException e){
	        	 vcli.enConsola("Error en la comunicaci�n "+"Informaci�n para el usuario");
	            break;
	         }
	      }
	      vcli.enConsola("se desconecto el servidor");
	   }

	   
	}


