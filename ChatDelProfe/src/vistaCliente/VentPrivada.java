package vistaCliente;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.*;

import controladorCliente.Gestor;
import controladorCliente.controladorPrivado;

/**
 * Ventana privada para chats individuales.
 * Permite a los usuarios comunicarse con un amigo específico.
 * Se utiliza en conjunto con la clase Cliente para la comunicación con el servidor.
 * @author Administrador
 */
public class VentPrivada extends JFrame {
   JTextArea panMostrar;
   public JTextField txtMensage;
   public JButton butEnviar;
   
   Gestor cliente;
   public String amigo;
   
   /**
    * Crea una nueva ventana privada para chats individuales.
    * @param cliente Cliente asociado a la ventana.
    */
   public VentPrivada(Gestor cliente)
   {
       
      super("Amigo");
      
      this.cliente=cliente;
      txtMensage = new JTextField(30);
      butEnviar = new JButton("Enviar");
      panMostrar = new JTextArea(); 
      panMostrar.setEditable(false);
      txtMensage.requestFocus();
     
      
      JPanel panAbajo = new JPanel();
             panAbajo.setLayout(new BorderLayout());
             panAbajo.add(new JLabel("  Ingrese mensaje a enviar:"),
                                BorderLayout.NORTH);
             panAbajo.add(txtMensage, BorderLayout.CENTER);
             panAbajo.add(butEnviar, BorderLayout.EAST);
      
      setLayout(new BorderLayout());
      add(new JScrollPane(panMostrar),BorderLayout.CENTER);
      add(panAbajo,BorderLayout.SOUTH);
       
      amigo="";
      
      this.addWindowListener(new WindowListener()
      {         
         public void windowClosing(WindowEvent e) {
            cerrarVentana();
         }
         public void windowClosed(WindowEvent e) {}         
         public void windowOpened(WindowEvent e) {}
         public void windowIconified(WindowEvent e) {}
         public void windowDeiconified(WindowEvent e) {}
         public void windowActivated(WindowEvent e) {}
         public void windowDeactivated(WindowEvent e) {}
        
      });
      
      setSize(300,300);
      setLocation(570,90);
      
   }
   

    /**
     * Obtiene el mensaje ingresado por el usuario.
     * @return El mensaje ingresado.
     */
    public String getMensaje() {
        return txtMensage.getText();
    }

    /**
     * Muestra un mensaje en el área de visualización.
     * @param mensaje Mensaje a mostrar.
     */
    public void mostrarMensaje(String mensaje) {
        panMostrar.append(mensaje + "\n");
    }
   

    /**
     * Limpia el campo de texto del mensaje.
     */
    public void limpiarMensaje() {
    	txtMensage.setText("");
    }
    
    /**
     * Cierra la ventana privada.
     */
    private void cerrarVentana() 
    {       
      this.setVisible(false);      
    }
    
    /**
     * Establece el nombre del amigo en el título de la ventana.
     * @param ami Nombre del amigo.
     */
    public void setAmigo(String ami)
    {      
       this.amigo=ami;
       this.setTitle(ami);      
    }
   
}