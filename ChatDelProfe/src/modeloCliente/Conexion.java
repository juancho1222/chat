package modeloCliente;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import controladorCliente.Cliente;
import modeloServidor.Propiedades;

/**
 * Clase para establecer la conexión del cliente.
 */
public class Conexion {
    
    private Socket comunication;
    private Socket comunication2;
    private Propiedades prop;

    /**
     * Obtiene el socket de comunicación.
     * @return Socket de comunicación.
     */
    public Socket getComunication() {
        return comunication;
    }

    /**
     * Obtiene el socket de comunicación para recibir mensajes.
     * @return Socket de comunicación para recibir mensajes.
     */
    public Socket getComunication2() {
        return comunication2;
    }

    /**
     * Obtiene las propiedades de la conexión.
     * @return Propiedades de la conexión.
     */
    public Propiedades getProp() {
        return prop;
    }

    /**
     * Constructor para establecer la conexión del cliente.
     * @param IPCliente Dirección IP del cliente.
     * @throws IOException Si hay un error de entrada/salida.
     */
    public Conexion(String IPCliente) throws IOException {
        prop = new Propiedades(); 
        
        comunication = new Socket(IPCliente, prop.getEnvioPort());
        comunication2 = new Socket(IPCliente, prop.getReciboPort());
    }
}
