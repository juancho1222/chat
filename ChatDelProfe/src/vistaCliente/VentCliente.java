package vistaCliente;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import java.util.Vector;
import javax.swing.*;
import javax.swing.border.Border;

import controladorCliente.Gestor;
import controladorCliente.controlBotones;

import javax.swing.JOptionPane.*;

/**
 * Ventana del cliente del chat.
 * Permite a los usuarios del chat enviar y recibir mensajes, ver usuarios activos y abrir ventanas de chat privado.
 * También proporciona opciones de ayuda y créditos.
 * La ventana se utiliza en conjunto con la clase Cliente para la comunicación con el servidor.
 * @author Administrador
 */
public class VentCliente extends JFrame  {
     String mensajeCliente;
     JTextArea panMostrar;
     public JTextField txtMensage;
     public JButton butEnviar;
     JLabel lblNomUser;
     public JList lstActivos;
     public JButton butPrivado;
     Gestor cliente;	
      
      JMenuBar barraMenu;
      JMenu JMAyuda;
      public JMenuItem help;
      JMenu JMAcerca;
      public JMenuItem acercaD;
 
      VentPrivada vp;
      
      JOptionPane AcercaDe;
     
     public Vector<String> nomUsers;
    
     /** 
      * Crea una nueva instancia de la ventana del cliente.
      * @param cliente Cliente asociado a la ventana.
      * @param ventPrivada Ventana privada para chats individuales.
      * @throws IOException Si hay un error de entrada/salida.
      */
     public VentCliente(Gestor cliente, VentPrivada ventPrivada) throws IOException {
             super("Cliente Chat");
             this.cliente=cliente;
             this.vp=ventPrivada;
             txtMensage = new JTextField(30);
             butEnviar = new JButton("Enviar");
             lblNomUser = new JLabel("Usuario <<  >>");
             lblNomUser.setHorizontalAlignment(JLabel.CENTER);
             panMostrar = new JTextArea();             
             panMostrar.setColumns(25);
            
             lstActivos=new JList();             
             butPrivado=new JButton("Privado");
             
             
             barraMenu=new JMenuBar();
             JMAyuda=new JMenu("Ayuda");
             help=new JMenuItem("Ayuda");
             
             
             JMAcerca=new JMenu("Acerca de");
             acercaD=new JMenuItem("Creditos");
             
             
             JMAyuda.add(help);
             JMAcerca.add(acercaD);
             barraMenu.add(JMAcerca);
             barraMenu.add(JMAyuda);            
             
             
             panMostrar.setEditable(false);            
             panMostrar.setForeground(Color.BLUE);
             panMostrar.setBorder(javax.swing.BorderFactory.createMatteBorder(3,3,3,3,new Color(25,10,80)));		

             JPanel panAbajo = new JPanel();
             panAbajo.setLayout(new BorderLayout());
                panAbajo.add(new JLabel("  Ingrese mensage a enviar:"),BorderLayout.NORTH);
                panAbajo.add(txtMensage, BorderLayout.CENTER);
                panAbajo.add(butEnviar, BorderLayout.EAST);
             JPanel panRight = new JPanel();
             panRight.setLayout(new BorderLayout());
                panRight.add(lblNomUser, BorderLayout.NORTH);
                panRight.add(new JScrollPane(panMostrar), BorderLayout.CENTER);
                panRight.add(panAbajo,BorderLayout.SOUTH);
             JPanel panLeft=new JPanel();
             panLeft.setLayout(new BorderLayout());
               panLeft.add(new JScrollPane(this.lstActivos),BorderLayout.CENTER);
               panLeft.add(this.butPrivado,BorderLayout.NORTH);
             JSplitPane sldCentral=new JSplitPane();  
             sldCentral.setDividerLocation(100);
             sldCentral.setDividerSize(7);
             sldCentral.setOneTouchExpandable(true);
               sldCentral.setLeftComponent(panLeft);
               sldCentral.setRightComponent(panRight);
             
             
             setLayout(new BorderLayout());
             add(sldCentral, BorderLayout.CENTER);   
             add(barraMenu,BorderLayout.NORTH);
             
             txtMensage.requestFocus();//pedir el focus	
            
     }
     
     /**
      * Crea la interfaz de usuario.
      * @throws IOException Si hay un error de entrada/salida.
      */
     public void crear2() throws IOException {   
    	 
             nomUsers=new Vector();

             ponerActivos(cliente.pedirUsuarios());
             
            
                  
             setSize(450, 430);
             setLocation(120, 90);
             setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);				
             setVisible(true);
     }
       
     /**
      * Establece el nombre del usuario en la interfaz.
      * @param user Nombre de usuario a establecer.
      */
     public void setNombreUser(String user)
     {
        lblNomUser.setText("Usuario " + user);
     }
     
     /**
      * Muestra un mensaje en el área de visualización.
      * @param msg Mensaje a mostrar.
      */
     public void mostrarMsg(String msg)
     {
        this.panMostrar.append(msg+"\n");
     }
     
     /**
      * Muestra usuarios activos en la lista.
      * @param datos Vector de usuarios activos.
      */
     public void ponerActivos(Vector datos)
     {
        nomUsers=datos;
        ponerDatosList(this.lstActivos,nomUsers);
     }
     
     /**
      * Agrega un usuario a la lista de usuarios activos.
      * @param user Usuario a agregar.
      */
     public void agregarUser(String user)
     {
        nomUsers.add(user);
        ponerDatosList(this.lstActivos,nomUsers);
     }
     
     /**
      * Retira un usuario de la lista de usuarios activos.
      * @param user Usuario a retirar.
      */
     public void retirraUser(String user)
     {        
        nomUsers.remove(user);
        ponerDatosList(this.lstActivos,nomUsers);
     }
     
     /**
      * Rellena la lista con datos.
      * @param list Lista a llenar.
      * @param datos Datos para llenar la lista.
      */
    private void ponerDatosList(JList list,final Vector datos)
    {
        list.setModel(new AbstractListModel() {            
         
            public int getSize() { return datos.size(); }
           
            public Object getElementAt(int i) { return datos.get(i); }
        });
    }
    
     /**
      * Muestra un mensaje de un amigo en una ventana privada.
      * @param amigo Nombre del amigo.
      * @param msg Mensaje recibido.
      */
     public void mensageAmigo(String amigo,String msg)
     {
    	 vp.setAmigo(amigo);           
         vp.mostrarMensaje(msg);        
         vp.setVisible(true);
     }
     
     /**
      * Imprime un mensaje en la consola.
      * @param mensaje Mensaje a imprimir.
      */
     public void enConsola(Object mensaje) {
 		System.out.println(mensaje);
 	}
     
     /**
      * Muestra los créditos.
      */
     public void creditos() {
    	 JOptionPane.showMessageDialog(this,"Jos� Valdez/Javier Vargas","Desarrollado por",JOptionPane.INFORMATION_MESSAGE);
     }
     
     /**
      * Solicita al usuario que introduzca un apodo (nick).
      * @return Apodo introducido por el usuario.
     */
  public String pedirNick() {
    	 return JOptionPane.showInputDialog("Introducir Nick :");
     }
} 
