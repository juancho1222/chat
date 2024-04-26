package controladorCliente;

import java.io.DataInputStream;
import java.io.IOException;

import modeloCliente.Propiedades;

import vistaCliente.VentCliente;

/**
 * La clase threadCliente es responsable de manejar la comunicación del cliente con el servidor.
 * Lee mensajes del servidor y realiza acciones según el tipo de mensaje recibido.
 */
public class threadCliente extends Thread {
    /** Flujo de entrada de datos desde el servidor. */
    DataInputStream entrada;
    /** Instancia de la ventana del cliente. */
    VentCliente vcli;
    /** Instancia para acceder a las propiedades del sistema. */
    Propiedades props = new Propiedades();
    /** Array de palabras prohibidas obtenidas de las propiedades. */
    String[] palabrasProhibidas = props.getPalabrasGroseras();

    /**
     * Constructor de la clase threadCliente.
     * @param entrada Flujo de entrada de datos desde el servidor.
     * @param vcli Instancia de la ventana del cliente.
     * @throws IOException Si ocurre un error de E/S.
     */
    public threadCliente(DataInputStream entrada, VentCliente vcli) throws IOException {
        this.entrada = entrada;
        this.vcli = vcli;
    }

    /**
     * Método para ejecutar el hilo de comunicación del cliente.
     * Lee mensajes del servidor y realiza acciones según el tipo de mensaje recibido.
     */
    public void run() {
        String menser = "", amigo = "";
        int opcion = 0;
        while (true) {
            try {
                opcion = entrada.readInt();
                switch (opcion) {
                    case 1: // Mensaje enviado
                        menser = entrada.readUTF();
                        menser = menser.replaceAll(">", "> ");
                        vcli.enConsola("ECO del servidor:" + filtro(menser));
                        vcli.mostrarMsg(filtro(menser));
                        break;
                    case 2: // Se agrega
                        menser = entrada.readUTF();
                        vcli.agregarUser(filtro(menser));
                        break;
                    case 3: // Mensaje de amigo
                        amigo = entrada.readUTF();
                        menser = entrada.readUTF();
                        menser = menser.replaceAll(">", "> ");
                        vcli.mensageAmigo(amigo, filtro(menser));
                        vcli.enConsola("ECO del servidor:" + filtro(menser));
                        break;
                }
            } catch (IOException e) {
                vcli.enConsola("Error en la comunicación " + "Información para el usuario");
                break;
            }
        }
        vcli.enConsola("se desconecto el servidor");
    }

    /**
     * Método para filtrar palabras prohibidas en un mensaje.
     * @param input El mensaje a filtrar.
     * @return El mensaje filtrado.
     */
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

