package vistaCliente;

import java.net.URL;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JViewport;

public class VentanaAyuda extends JFrame{
    
    /** Creates a new instance of VentanaAyuda */
     JScrollPane panelPrincipal;
    JEditorPane html;
    public VentanaAyuda() {
        super("Ventana de Ayuda :");
        setSize(600,700);
        setLocation(450,0);
        panelPrincipal=new JScrollPane();
        
        try{ 
            URL url=getClass().getResource("index.html");
            html=new JEditorPane(url);
            html.setEditable(false);
            setVisible(true);
             
        }catch(Exception e){
            e.getMessage();
        }
        
        JViewport jv=panelPrincipal.getViewport();
        jv.add(html);
        
        setContentPane(panelPrincipal);
    }
    
}
