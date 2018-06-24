package cl.org.thread;

import cl.org.model.Mascota;
import cl.org.main.App;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 *
 * @author Jorge A
 */
public class KillerThread extends Thread {

    private Mascota mas;
    private JButton btnA;
    private JButton btnB;
    private JButton btnC;
    private JButton btnD;
    private JButton btnE;
    private JLabel lbl;
    private App app;

    public KillerThread(Mascota mas, JButton btnA, JButton btnB, JButton btnC, JButton btnD, JButton btnE, JLabel lbl, App app) {
        this.mas = mas;
        this.btnA = btnA;
        this.btnB = btnB;
        this.btnC = btnC;
        this.btnD = btnD;
        this.btnE = btnE;
        this.lbl = lbl;
        this.app = app;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(5);

                if (mas.getEnergia() == 0 && mas.getEntretencion() == 0 && mas.getHambre() == 0 && mas.getLimpieza() == 0 && mas.getSalud() == 0) {
                    
                    app.getContentPane().setBackground(new java.awt.Color(211, 211, 211));
                    
                    ImageIcon myIcon = new ImageIcon("src\\cl\\org\\res\\Death.png");
                    lbl.setIcon(myIcon);
                    btnA.setEnabled(false);
                    btnB.setEnabled(false);
                    btnC.setEnabled(false);
                    btnD.setEnabled(false);
                    btnE.setEnabled(false);
                }

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
