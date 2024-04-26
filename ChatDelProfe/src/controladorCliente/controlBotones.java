package controladorCliente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



import vistaCliente.VentCliente;
import vistaCliente.VentanaAyuda;
import vistaCliente.VentPrivada;
/**
 * La clase controlBotones implementa ActionListener para manejar eventos de botones en la interfaz de cliente.
 */
public class controlBotones implements ActionListener {
    VentCliente ventanaCliente;
    VentPrivada ventanaPrivada;
    Gestor cliente;

    /**
     * Constructor de la clase controlBotones.
     * @param ventCliente Ventana principal del cliente.
     * @param ventPrivada Ventana de chat privado.
     * @param cliente Instancia del cliente.
     */
    public controlBotones(VentCliente ventCliente, VentPrivada ventPrivada, Gestor cliente) {
        this.ventanaCliente = ventCliente;
        this.cliente = cliente;
        this.ventanaPrivada = ventPrivada;
        this.ventanaCliente.txtMensage.addActionListener(this);
        this.ventanaCliente.butEnviar.addActionListener(this);
        this.ventanaCliente.butPrivado.addActionListener(this);
        this.ventanaCliente.help.setActionCommand("help");
        this.ventanaCliente.help.addActionListener(this);
        this.ventanaCliente.acercaD.setActionCommand("Acerca");
        this.ventanaCliente.acercaD.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        String comand = evt.getActionCommand();
        if (comand.compareTo("help") == 0) {
            VentanaAyuda va = new VentanaAyuda();
            va.setVisible(true);
        } else if (comand.compareTo("Acerca") == 0) {
            ventanaCliente.creditos();
        } else if (evt.getSource() == ventanaCliente.butEnviar || evt.getSource() == ventanaCliente.txtMensage) {
            String mensaje = ventanaCliente.txtMensage.getText();
            cliente.flujo(mensaje);
            ventanaCliente.txtMensage.setText("");
        } else if (evt.getSource() == ventanaCliente.butPrivado) {
            int pos = ventanaCliente.lstActivos.getSelectedIndex();
            if (pos >= 0) {
                ventanaPrivada.setAmigo(ventanaCliente.nomUsers.get(pos));
                ventanaPrivada.setVisible(true);
            }
        }
    }
}
