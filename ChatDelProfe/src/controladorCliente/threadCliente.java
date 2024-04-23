package controladorCliente;

import java.io.DataInputStream;
import java.io.IOException;

import vistaCliente.VentCliente;

public class threadCliente extends Thread{
	   DataInputStream entrada;
	   VentCliente vcli;
	   String[] palabrasProhibidas = {
			   "abanto", "abrazafarolas", "adufe", "alcornoque", "alfeñique", "andurriasmo", "arrastracueros",
			   "artabán", "atarre", "baboso", "barrabás", "barriobajero", "bebecharcos", "bellaco", "belloto",
			   "berzotas", "besugo", "bobalicón", "bocabuzón", "bocachancla", "bocallanta", "boquimuelle",
			   "borrico", "botarate", "brasas", "cabestro", "cabezaalberca", "cabezabuque", "cachibache", "cafren",
			   "cagalindes", "cagarruta", "calambuco", "calamidad", "caldúo", "calientahielos", "calzamonas",
			   "cansalmas", "cantamañanas", "capullo", "caracaballo", "caracartón", "caraculo", "caraflema",
			   "carajaula", "carajote", "carapapa", "carapijo", "cazurro", "cebollino", "cenizo", "cenutrio",
			   "ceporro", "cernícalo", "charrán", "chiquilicuatre", "chirimbaina", "chupacables", "chupasangre",
			   "chupóptero", "cierrabares", "cipote", "comebolsas", "comechapas", "comeflores", "comestacas",
			   "cretino", "cuerpoescombro", "culopollo", "descerebrado", "desgarracalzas", "dondiego", "donnadie",
			   "echacantos", "ejarramantas", "energúmeno", "esbaratabailes", "escolimoso", "escornacabras",
			   "estulto", "fanfosquero", "fantoche", "fariseo", "filimincias", "foligoso", "fulastre", "ganapán",
			   "ganapio", "gandúl", "gañán", "gaznápiro", "gilipuertas", "giraesquinas", "gorrino", "gorrumino",
			   "guitarro", "gurriato", "habahelá", "huelegateras", "huevón", "lamecharcos", "lameculos", "lameplatos",
			   "lechuguino", "lerdo", "letrín", "lloramigas", "longanizas", "lumbreras", "maganto", "majadero",
			   "malasangre", "malasombra", "malparido", "mameluco", "mamporrero", "manegueta", "mangarrán",
			   "mangurrián", "mastuerzo", "matacandiles", "meapilas", "melón", "mendrugo", "mentecato", "mequetrefe",
			   "merluzo", "metemuertos", "metijaco", "mindundi", "morlaco", "morroestufa", "muerdesartenes",
			   "orate", "ovejo", "pagafantas", "palurdo", "pamplinas", "panarra", "panoli", "papafrita", "papanatas",
			   "papirote", "paquete", "pardillo", "parguela", "pasmarote", "pasmasuegras", "pataliebre", "patán",
			   "pavitonto", "pazguato", "pecholata", "pedorro", "peinabombillas", "peinaovejas", "pelagallos",
			   "pelagambas", "pelagatos", "pelatigres", "pelazarzas", "pelele", "pelma", "percebe", "perrocostra",
			   "perroflauta", "peterete", "petimetre", "picapleitos", "pichabrava", "pillavispas", "piltrafa",
			   "pinchauvas", "pintamonas", "piojoso", "pitañoso", "pitofloro", "plomo", "pocasluces", "pollopera",
			   "puta", "malparido", "hijueputa", "carachimba", "mamahuevo", "nahim", // Nuevas palabras
			   "quitahipos", "rastrapajo", "rebañasandías", "revientabaules", "ríeleches", "robaperas", "sabandija",
			   "sacamuelas", "sanguijuela", "sinentraero", "sinsustancia", "sonajas", "sonso", "soplagaitas",
			   "soplaguindas", "sosco", "tagarote", "tarado", "tarugo", "tiralevitas", "tocapelotas", "tocho",
			   "tolai", "tontaco", "tontucio", "tordo", "tragaldabas", "tuercebotas", "tunante", "zamacuco",
			   "zambombo", "zampabollos", "zamugo", "zángano", "zarrapastroso", "zascandil", "zopenco", "zoquete",
			   "zote", "zullenco", "zurcefrenillos"};
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


