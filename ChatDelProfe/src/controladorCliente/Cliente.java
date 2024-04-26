package controladorCliente;

import java.io.*;
import java.net.*;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

import modeloCliente.Conexion;
import vistaCliente.VentCliente;
import vistaCliente.VentPrivada;

/**
 *
 * @author Administrador
 */
public class Cliente
{
   public static String IP_SERVER;
   VentCliente vent;
   DataInputStream entrada = null;
   DataOutputStream salida = null;
   DataInputStream entrada2 = null;
   Socket comunication = null;//para la comunicacion
   Socket comunication2 = null;//para recivir msg
   
   String nomCliente;
   
   public Cliente( ) throws IOException 
   {      
	  VentPrivada priv=new VentPrivada(this);
	 
       vent=new VentCliente(this,priv);
       conexion();
       vent.crear2();
      controlBotones controlBotones=new controlBotones(vent,priv,this);
      controladorPrivado contro =new controladorPrivado(priv, this);
     
     
   }
   
   public void conexion() throws IOException 
   {
      try {
         Conexion con= new Conexion(Cliente.IP_SERVER);
         entrada = new DataInputStream(con.getComunication().getInputStream());
         salida = new DataOutputStream(con.getComunication().getOutputStream());
         entrada2 = new DataInputStream(con.getComunication2().getInputStream());
         nomCliente = vent.pedirNick();
        vent.setNombreUser(nomCliente);         
         salida.writeUTF(nomCliente);
      } catch (IOException e) {
         vent.enConsola("\tEl servidor no esta levantado");
         vent.enConsola("\t=============================");
      }new threadCliente(entrada2, vent).start();
      
   }
   public String getNombre()
   {
      return nomCliente;
   }

   public Vector<String> pedirUsuarios()
   {
      Vector<String> users = new Vector();
      try {         
         salida.writeInt(2);
         int numUsers=entrada.readInt();
         for(int i=0;i<numUsers;i++)
            users.add(entrada.readUTF());
      } catch (IOException ex) {
        Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
      }
      return users;
   }
   public void flujo(String mens) 
   {
      try {             
         vent.enConsola("el mensaje enviado desde el cliente es :"
             + mens);
         salida.writeInt(1);
         salida.writeUTF(mens);
      } catch (IOException e) {
         vent.enConsola("error...." + e);
      }
   }
   
   public void flujo(String amigo,String mens) 
   {
      try {             
         vent.enConsola("el mensaje enviado desde el cliente es :"
             + mens);
         salida.writeInt(3);//opcion de mensage a amigo
         salida.writeUTF(amigo);
         salida.writeUTF(mens);
      } catch (IOException e) {
    	  vent.enConsola("error...." + e);
      }
   }

}
