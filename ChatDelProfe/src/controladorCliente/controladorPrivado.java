package controladorCliente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vistaCliente.VentPrivada;
public class controladorPrivado implements ActionListener {
	VentPrivada ventPriv;	
	Cliente cliente;
public controladorPrivado(VentPrivada ventPriv, Cliente cliente) {
this.ventPriv=ventPriv;
this.cliente=cliente;
	this.ventPriv.txtMensage.addActionListener(this);
this.ventPriv.butEnviar.addActionListener(this);
}
public void actionPerformed(ActionEvent e) 
{
   String mensaje = ventPriv.txtMensage.getText();              
   ventPriv.mostrarMensaje(cliente.getNombre()+">"+mensaje);
   cliente.flujo(ventPriv.amigo,mensaje);
   ventPriv.txtMensage.setText("");
}
}
