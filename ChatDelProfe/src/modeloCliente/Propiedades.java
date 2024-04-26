package modeloCliente;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Clase para leer las propiedades del cliente desde un archivo de propiedades.
 */
public class Propiedades {
    int envioPort; 
    int reciboPort;
    String words;
    String[] palabrasGroseras;
    
    /**
     * Obtiene el puerto de envío.
     * @return Puerto de envío.
     */
    public int getEnvioPort() {
        return envioPort;
    }

    /**
     * Obtiene el puerto de recepción.
     * @return Puerto de recepción.
     */
    public int getReciboPort() {
        return reciboPort;
    }
    
    /**
     * Obtiene las palabras groseras.
     * @return Array de palabras groseras.
     */
    public String[] getPalabrasGroseras() {
        return palabrasGroseras;
    }

    /**
     * Constructor para inicializar las propiedades desde un archivo de propiedades.
     * @throws IOException Si hay un error de entrada/salida.
     */
    public Propiedades() throws IOException {    
        Properties prop = new Properties();
        FileInputStream input = new FileInputStream("src/DATACliente/Cliente.properties");
        prop.load(input);
        
        words = prop.getProperty("badWords");
        palabrasGroseras = words.split(",");
        
        envioPort = Integer.parseInt(prop.getProperty("envioIP"));
        reciboPort = Integer.parseInt(prop.getProperty("reciboIP"));
    }
}