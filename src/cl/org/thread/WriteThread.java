/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.org.thread;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Properties;

/**
 *
 * @author Jorge A
 */
public class WriteThread extends Thread {

    private Properties prop;
    private OutputStream output;
    private File archivo;
    private String entretencion;
    private String limpieza;
    private String salud;

    public WriteThread(String entretencion, String limpieza, String salud) {
        this.prop = new Properties();
        this.archivo = new File("mascota.properties");
    }

    @Override
    public void run() {
        try {
            this.output = new FileOutputStream(archivo);
            this.prop.setProperty("entretencion", entretencion);
            this.prop.setProperty("limpieza", limpieza);
            this.prop.setProperty("salud", salud);

            this.prop.store(output, null);
            
            this.output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
