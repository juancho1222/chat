package controlServidor;
import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.*;

/**
 * Clase que representa un hilo de servidor para manejar la comunicación con un cliente.
 */
public class threadServidor extends Thread
{
     Socket scli=null;
     Socket scli2=null;
     DataInputStream entrada=null;
     DataOutputStream salida=null;
     DataOutputStream salida2=null;
     public static Vector<threadServidor> clientesActivos=new Vector();	
     String nameUser;
     Gestor serv;
     /**
      * Constructor de la clase ThreadServidor.
      * @param scliente Socket de comunicación con el cliente.
      * @param scliente2 Socket de comunicación con el cliente para enviar mensajes.
      * @param serv Gestor del servidor.
      */
     public threadServidor(Socket scliente,Socket scliente2,Gestor serv)
     {
        scli=scliente;
        scli2=scliente2;
        this.serv=serv;
        nameUser="";
        clientesActivos.add(this);        
        serv.vista.mostrar("cliente agregado: "+this);			
     }
     /**
      * Obtiene el nombre del usuario asociado al hilo del servidor.
      * @return Nombre del usuario.
      */
     public String getNameUser()
     {
       return nameUser;
     }
     /**
      * Establece el nombre del usuario asociado al hilo del servidor.
      * @param name Nombre del usuario.
      */
     public void setNameUser(String name)
     {
       nameUser=name;
     }
     /**
      * Método que se ejecuta cuando se inicia el hilo.
      */
     public void run()
     {
    	serv.vista.mostrar(".::Esperando Mensajes :");
    	
    	try
    	{
          entrada=new DataInputStream(scli.getInputStream());
          salida=new DataOutputStream(scli.getOutputStream());
          salida2=new DataOutputStream(scli2.getOutputStream());
          this.setNameUser(entrada.readUTF());
          enviaUserActivos();
    	}
    	catch (IOException e) {  e.printStackTrace();     }
    	
        int opcion=0,numUsers=0;
        String amigo="",mencli="";
                
    	while(true)
    	{
          try
          {
             opcion=entrada.readInt();
             switch(opcion)
             {
                case 1://envio de mensage a todos
                   mencli=entrada.readUTF();
                   serv.vista.mostrar("mensaje recibido "+mencli);
                   enviaMsg(mencli);
                   break;
                case 2://envio de lista de activos
                   numUsers=clientesActivos.size();
                   salida.writeInt(numUsers);
                   for(int i=0;i<numUsers;i++)
                      salida.writeUTF(clientesActivos.get(i).nameUser);
                   break;
                case 3: // envia mensage a uno solo
                   amigo=entrada.readUTF();//captura nombre de amigo
                   mencli=entrada.readUTF();//mensage enviado
                   enviaMsg(amigo,mencli);
                   break;
             }
          }
          catch (IOException e) {System.out.println("El cliente termino la conexion");break;}
    	}
    	serv.vista.mostrar("Se removio un usuario");
    	clientesActivos.removeElement(this);
    	try
    	{
          serv.vista.mostrar("Se desconecto un usuario");
          scli.close();
    	}	
        catch(Exception et)
        {serv.vista.mostrar("no se puede cerrar el socket");}   
     }
     /**
      * Método para enviar un mensaje a todos los clientes.
      * @param mencli Mensaje a enviar.
      */
     public void enviaMsg(String mencli2)
     {
        threadServidor user=null;
        for(int i=0;i<clientesActivos.size();i++)
        {
           serv.vista.mostrar("MENSAJE DEVUELTO:"+mencli2);
           try
            {
              user=clientesActivos.get(i);
              user.salida2.writeInt(1);//opcion de mensage 
              user.salida2.writeUTF(""+this.getNameUser()+" >"+ mencli2);              
            }catch (IOException e) {e.printStackTrace();}
        }
     }
     /**
      * Método para enviar la lista de usuarios activos a todos los clientes.
      */
     public void enviaUserActivos()
     {
        threadServidor user=null;
        for(int i=0;i<clientesActivos.size();i++)
        {           
           try
            {
              user=clientesActivos.get(i);
              if(user==this)continue;//ya se lo envie
              user.salida2.writeInt(2);//opcion de agregar 
              user.salida2.writeUTF(this.getNameUser());	
            }catch (IOException e) {e.printStackTrace();}
        }
     }
     /**
      * Método para enviar un mensaje a un usuario específico.
      * @param amigo Nombre del usuario destinatario.
      * @param mencli Mensaje a enviar.
      */
   private void enviaMsg(String amigo, String mencli) 
   {
      threadServidor user=null;
        for(int i=0;i<clientesActivos.size();i++)
        {           
           try
            {
              user=clientesActivos.get(i);
              if(user.nameUser.equals(amigo))
              {
                 user.salida2.writeInt(3);//opcion de mensage amigo   
                 user.salida2.writeUTF(this.getNameUser());
                 user.salida2.writeUTF(""+this.getNameUser()+">"+mencli);
              }
            }catch (IOException e) {e.printStackTrace();}
        }
   }
}