package controladorCliente;

import java.io.DataInputStream;
import java.io.IOException;

import modeloCliente.Propiedades;

import vistaCliente.VentCliente;

public class threadCliente extends Thread{
	   DataInputStream entrada;
	   VentCliente vcli;
	   Propiedades props=new Propiedades();
	   String[] palabrasProhibidas = props.getPalabrasGroseras();
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
	                  menser=menser.replaceAll(">", "> ");
	                  
	                  vcli.enConsola("ECO del servidor:"+filtro(menser));
	                  vcli.mostrarMsg(filtro(menser));            
	                  break;
	               case 2://se agrega
	                  menser=entrada.readUTF();
	                  
	                  vcli.agregarUser(filtro(menser) );                  
	                  break;
	               case 3://mensage de amigo
	                  amigo=entrada.readUTF();
	                  menser=entrada.readUTF();
	                  menser=menser.replaceAll(">", "> ");
	                  vcli.mensageAmigo(amigo,filtro(menser));
	                  vcli.enConsola("ECO del servidor:"+filtro(menser));
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

	   public String filtro(String input) {
		// Dividir la cadena en palabras
		   String[] palabras = input.split(" ");

		   // Recorrer cada palabra y comparar con la lista de palabras prohibidas
		   for (int i = 0; i < palabras.length; i++) {
		       for (String palabraProhibida : palabrasProhibidas) {
		           if (palabras[i].equalsIgnoreCase(palabraProhibida)) {
		               // Si hay coincidencia, reemplazar los caracteres por asteriscos
		               palabras[i] = "*".repeat(palabras[i].length());
		               break; // Salir del bucle una vez reemplazado
		           }
		       }
		   }
		   // Construir el resultado como un solo string
		   StringBuilder resultado = new StringBuilder();
		   for (String palabra : palabras) {
		       resultado.append(palabra).append(" ");
		   }
		   return resultado.toString();
		}  
	}


