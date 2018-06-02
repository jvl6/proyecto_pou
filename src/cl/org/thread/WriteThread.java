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
    private int entretencion;
    private int limpieza;
    private int salud;

    public WriteThread(int entretencion, int limpieza, int salud) {
        this.prop = new Properties();
        this.archivo = new File("mascota.properties");
    }

    @Override
    public void run() {
        try {
            this.output = new FileOutputStream(archivo);
            this.prop.setProperty("entretencion", Integer.toString(entretencion));
            this.prop.setProperty("limpieza", Integer.toString(limpieza));
            this.prop.setProperty("salud", Integer.toString(salud));

            this.prop.store(output, null);

            this.output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
