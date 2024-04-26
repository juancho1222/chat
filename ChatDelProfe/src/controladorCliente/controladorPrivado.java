package controladorCliente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vistaCliente.VentPrivada;

/**
 * La clase controladorPrivado implementa ActionListener para manejar eventos de la ventana de chat privado.
 */
public class controladorPrivado implements ActionListener {
    VentPrivada ventPriv;
    Gestor cliente;

    /**
     * Constructor de la clase controladorPrivado.
     * @param ventPriv Ventana de chat privado.
     * @param cliente Instancia del cliente.
     */
    public controladorPrivado(VentPrivada ventPriv, Gestor cliente) {
        this.ventPriv = ventPriv;
        this.cliente = cliente;
        this.ventPriv.txtMensage.addActionListener(this);
        this.ventPriv.butEnviar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String mensaje = ventPriv.txtMensage.getText();
        ventPriv.mostrarMensaje(cliente.getNombre() + ">" + mensaje);
        cliente.flujo(ventPriv.amigo, mensaje);
        ventPriv.txtMensage.setText("");
    }
}
