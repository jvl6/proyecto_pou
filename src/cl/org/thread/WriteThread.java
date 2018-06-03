/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.org.thread;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
    private int hambre;
    private int energia;
    private LocalDateTime local;
    private DateTimeFormatter dtf;

    public WriteThread(int entretencion, int limpieza, int salud, int hambre, int energia) {
        this.entretencion = entretencion;
        this.limpieza = limpieza;
        this.salud = salud;
        this.hambre = hambre;
        this.energia = energia;
        this.prop = new Properties();
        this.archivo = new File("mascota.properties");
        this.local = LocalDateTime.now();
        this.dtf = DateTimeFormatter.ofPattern("HH:mm");
    }

    @Override
    public void run() {
        try {
            this.output = new FileOutputStream(archivo);
            this.prop.setProperty("entretencion", Integer.toString(entretencion));
            this.prop.setProperty("horaEntretencion", local.toString());
            this.prop.setProperty("limpieza", Integer.toString(limpieza));
            this.prop.setProperty("horaLimpieza", local.toString());
            this.prop.setProperty("salud", Integer.toString(salud));
            this.prop.setProperty("horaSalud", local.toString());
            this.prop.setProperty("hambre", Integer.toString(hambre));
            this.prop.setProperty("horaHambre", local.toString());
            this.prop.setProperty("energia", Integer.toString(energia));
            this.prop.setProperty("horaEnergia", local.toString());
            this.prop.store(output, null);

            this.output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
