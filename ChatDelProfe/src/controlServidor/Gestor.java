package controlServidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import modeloServidor.Conexion;
import vistaServidor.WServidor;

/**
 * La clase Gestor es responsable de manejar la comunicación entre el servidor y los clientes.
 * Inicia el servidor y espera la conexión de usuarios.
 */
public class Gestor {
    /** Instancia de la vista del servidor. */
    WServidor vista = new WServidor();

    /**
     * Constructor de la clase Gestor.
     * Inicia el servidor.
     */
    public Gestor() {
        runServer();
    }

    /**
     * Método para ejecutar el servidor y esperar conexiones de usuarios.
     */
    public void runServer() {
        boolean listening = true;
        try {
            Conexion con = new Conexion();
            vista.mostrar(".::Servidor activo :");

            while (listening) {
                Socket sock = null, sock2 = null;
                try {
                    vista.mostrar("Esperando Usuarios");
                    sock = con.getServ().accept();
                    sock2 = con.getServ2().accept();
                } catch (IOException e) {
                    vista.mostrar("Accept failed: " + con.getServ() + ", " + e.getMessage());
                    continue;
                }

                // Inicia un nuevo hilo para manejar la conexión del usuario
                threadServidor user = new threadServidor(sock, sock2, this);
                user.start();
            }
        } catch (IOException e) {
            vista.mostrar("error :" + e);
        }
    }
}
