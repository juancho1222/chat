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
 * La clase Cliente representa al cliente que se conecta al servidor.
 */
public class Cliente {
    public static String IP_SERVER;
    VentCliente vent;
    DataInputStream entrada = null;
    DataOutputStream salida = null;
    DataInputStream entrada2 = null;
    Socket comunication = null; // para la comunicacion
    Socket comunication2 = null; // para recivir msg

    String nomCliente;

    /**
     * Constructor de la clase Cliente.
     * @throws IOException Si ocurre un error de E/S.
     */
    public Cliente() throws IOException {
        VentPrivada priv = new VentPrivada(this);
        vent = new VentCliente(this, priv);
        conexion();
        vent.crear2();
        controlBotones controlBotones = new controlBotones(vent, priv, this);
        controladorPrivado contro = new controladorPrivado(priv, this);
    }

    /**
     * Método para establecer la conexión con el servidor.
     * @throws IOException Si ocurre un error de E/S.
     */
    public void conexion() throws IOException {
        try {
            Conexion con = new Conexion(Cliente.IP_SERVER);
            entrada = new DataInputStream(con.getComunication().getInputStream());
            salida = new DataOutputStream(con.getComunication().getOutputStream());
            entrada2 = new DataInputStream(con.getComunication2().getInputStream());
            nomCliente = vent.pedirNick();
            vent.setNombreUser(nomCliente);
            salida.writeUTF(nomCliente);
        } catch (IOException e) {
            vent.enConsola("\tEl servidor no esta levantado");
            vent.enConsola("\t=============================");
        }
        new threadCliente(entrada2, vent).start();
    }

    /**
     * Método para obtener el nombre del cliente.
     * @return El nombre del cliente.
     */
    public String getNombre() {
        return nomCliente;
    }

    /**
     * Método para obtener la lista de usuarios conectados.
     * @return Vector que contiene los nombres de los usuarios conectados.
     */
    public Vector<String> pedirUsuarios() {
        Vector<String> users = new Vector<>();
        try {
            salida.writeInt(2);
            int numUsers = entrada.readInt();
            for (int i = 0; i < numUsers; i++)
                users.add(entrada.readUTF());
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return users;
    }

    /**
     * Método para enviar un mensaje al servidor.
     * @param mens El mensaje a enviar.
     */
    public void flujo(String mens) {
        try {
            vent.enConsola("el mensaje enviado desde el cliente es :" + mens);
            salida.writeInt(1);
            salida.writeUTF(mens);
        } catch (IOException e) {
            vent.enConsola("error...." + e);
        }
    }

    /**
     * Método para enviar un mensaje privado a otro usuario.
     * @param amigo El nombre del usuario al que se enviará el mensaje.
     * @param mens El mensaje a enviar.
     */
    public void flujo(String amigo, String mens) {
        try {
            vent.enConsola("el mensaje enviado desde el cliente es :" + mens);
            salida.writeInt(3); // Opción de mensaje a amigo
            salida.writeUTF(amigo);
            salida.writeUTF(mens);
        } catch (IOException e) {
            vent.enConsola("error...." + e);
        }
    }
}
