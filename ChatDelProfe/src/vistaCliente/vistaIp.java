package vistaCliente;

import javax.swing.JOptionPane;

import controladorCliente.Cliente;

public class vistaIp {
	 public void pedirIp() {
    	 Cliente.IP_SERVER = JOptionPane.showInputDialog("Introducir IP_SERVER :","localhost");
     }
}
