package vistaCliente;
	import java.awt.BorderLayout;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
	import java.awt.event.WindowEvent;
	import java.awt.event.WindowListener;
	import javax.swing.*;

import controladorCliente.Cliente;
import controladorCliente.controladorPrivado;
	/**
	 *
	 * @author Administrador
	 */
	public class VentPrivada extends JFrame {
	   JTextArea panMostrar;
	   public JTextField txtMensage;
	   public JButton butEnviar;
	   
	   Cliente cliente;
	   public String amigo;
	   
	   public VentPrivada(Cliente cliente)
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
	             panAbajo.add(new JLabel("  Ingrese mensage a enviar:"),
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
	   

	    public String getMensaje() {
	        return txtMensage.getText();
	    }

	    public void mostrarMensaje(String mensaje) {
	        panMostrar.append(mensaje + "\n");
	    }
	   

	    public void limpiarMensaje() {
	    	txtMensage.setText("");
	    }
	    private void cerrarVentana() 
	    {       
	      this.setVisible(false);      
	    }
	    public void setAmigo(String ami)
	    {      
	       this.amigo=ami;
	       this.setTitle(ami);      
	    }
	   
	}