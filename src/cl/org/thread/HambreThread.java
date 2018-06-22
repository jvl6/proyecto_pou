/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.org.thread;

import cl.org.model.Mascota;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.swing.JProgressBar;

/**
 *
 * @author zdk
 */
public class HambreThread extends Thread {

    private Date timeProp;
    private Date localDate;
    private SimpleDateFormat df;
    private Mascota mascota;
    private JProgressBar pb;
    private long localMS;
    private long propMS;

    public HambreThread(String timeProp, Mascota mascota, JProgressBar pb) throws ParseException {
        this.df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        this.mascota = mascota;
        this.timeProp = df.parse(timeProp);
        this.pb = pb;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(5);

                //lms = Local milliseconds.
                // tms = timeProp milliseconds.
                this.localMS = System.currentTimeMillis();
                this.propMS = this.timeProp.getTime();
                long dif = this.localMS - this.propMS;

                System.out.println(dif);

                if (this.localMS - this.propMS >= TimeUnit.MINUTES.toMillis(20)) {
                    this.mascota.setHambre(this.mascota.getHambre()- 1);
                    this.pb.setValue(this.pb.getValue() - 1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
