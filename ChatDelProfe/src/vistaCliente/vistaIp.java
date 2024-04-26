package vistaCliente;

import javax.swing.JOptionPane;

import controladorCliente.Gestor;
/**
 * Clase de la vista para solicitar la dirección IP del servidor.
 * Permite al usuario ingresar la dirección IP del servidor.
 */
public class vistaIp {
	/**
	 * Método para solicitar la dirección IP al usuario.
	 * La dirección IP ingresada se asigna a la variable estática IP_SERVER de la clase Cliente.
	 */
	 public void pedirIp() {
		 Gestor.IP_SERVER = JOptionPane.showInputDialog("Introducir IP_SERVER :","localhost");
     }
}
