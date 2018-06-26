/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.org.thread;

import java.awt.Color;
import javax.swing.JProgressBar;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author zdk
 */
class ColorListener implements ChangeListener {

    @Override
    public void stateChanged(ChangeEvent e) {
        int r = 255;
        int g = 0;

        JProgressBar pb = (JProgressBar) e.getSource();

        int cont = 0;

        while (cont <= pb.getValue()) {
            g += 2;
            r -= 2;
            pb.setForeground(new Color(r, g, 0));
            cont++;
        }
    }
}

public class ColorThread extends Thread {

    private JProgressBar pb;

    public ColorThread(JProgressBar pb) {
        this.pb = pb;
    }

    @Override
    public void run() {
        pb.addChangeListener(new ColorListener());
    }

}
