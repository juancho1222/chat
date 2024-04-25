package vistaServidor;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class WServidor extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	JTextArea txaMostrar;
	/**
	 * Create the frame.
	 */
	public WServidor() {
		super("Consola servidor");
	      txaMostrar=new JTextArea();      
	    
	      this.setContentPane(new JScrollPane(txaMostrar));
	      setSize(350,350);
	      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      setVisible(true); 
	}
	
	public void mostrar(String msg)
	   {
	      txaMostrar.append(msg+"\n");
	   }
}
