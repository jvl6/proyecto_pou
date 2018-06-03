/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.org.thread;

import cl.org.model.Mascota;
import java.text.ParseException;
import java.util.Date;
import java.time.LocalDateTime;
import java.text.SimpleDateFormat;


/**
 *
 * @author zdk
 */
public class SaludThread extends Thread {

    private LocalDateTime local;
    private Date timeProp;
    private Date localDate;
    private SimpleDateFormat df;
    private Mascota mascota;

    public SaludThread(String timeProp, Mascota mascota) throws ParseException {
        this.mascota = mascota;
        this.df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss:SSS");
        this.timeProp = df.parse(timeProp);
    }

    @Override
    public void run() {
        while (true) {
            try {
                this.local = LocalDateTime.now();
                this.localDate = df.parse(local.toString());
                long lms = localDate.getTime();
                long tms = this.timeProp.getTime();
                
                if (lms - tms > 1200000) {
                    this.mascota.setSalud(this.mascota.getSalud() - 1);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
