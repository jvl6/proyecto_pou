/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.org.main;

import cl.org.model.Mascota;
import cl.org.thread.EnergiaThread;
import cl.org.thread.EntretencionThread;
import cl.org.thread.HambreThread;
import cl.org.thread.LimpiezaThread;
import cl.org.thread.SaludThread;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 *
 * @author Jorge A
 */
public class App extends javax.swing.JFrame {

    private Mascota pou;
    private Properties prop;

    public App() {
        initComponents();
        initPou();
        System.out.println(pou);
    }

    private void initPou() {
        File save = new File("mascota.properties");
        boolean existe = save.exists();
        this.prop = new Properties();
        InputStream input;

        try {
            if (existe) {
                // Cuando hay un save
                input = new FileInputStream(save);
                prop.load(input);

                int entretencion = Integer.parseInt(prop.getProperty("entretencion"));
                int limpieza = Integer.parseInt(prop.getProperty("limpieza"));
                int salud = Integer.parseInt(prop.getProperty("salud"));
                int hambre = Integer.parseInt(prop.getProperty("hambre"));
                int energia = Integer.parseInt(prop.getProperty("energia"));
                String fecha = prop.getProperty("horaSalud");

                this.pou = new Mascota(entretencion, limpieza, salud, hambre, energia);

                pbEnergia.setValue(energia);
                lblPorcEnergia.setText(energia + "%");
                pbEntretencion.setValue(entretencion);
                lblPorcEntrete.setText(entretencion + "%");
                pbHambre.setValue(hambre);
                lblPorcHambre.setText(hambre + "%");
                pbLimpieza.setValue(limpieza);
                lblPorcLimpieza.setText(limpieza + "%");
                pbSalud.setValue(salud);
                lblPorcSalud.setText(salud + "%");

                SaludThread st = new SaludThread(fecha, pou, pbSalud);
                st.start();
                HambreThread at = new HambreThread(fecha, pou, pbHambre);
                at.start();
                EntretencionThread ett = new EntretencionThread(fecha, pou, pbEntretencion);
                ett.start();
                EnergiaThread egt = new EnergiaThread(fecha, pou, pbEnergia);
                egt.start();
                LimpiezaThread lmt = new LimpiezaThread(fecha, pou, pbLimpieza);
                lmt.start();

            } else {
                // Primer Run
                this.pou = new Mascota(80, 80, 50, 20, 80);

                OutputStream output = new FileOutputStream(save);
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                String fecha = sdf.format(new Date());
                prop.setProperty("entretencion", Integer.toString(this.pou.getEntretencion()));
                prop.setProperty("horaEntretencion", fecha);
                prop.setProperty("limpieza", Integer.toString(this.pou.getLimpieza()));
                prop.setProperty("horaLimpieza", fecha);
                prop.setProperty("salud", Integer.toString(this.pou.getSalud()));
                prop.setProperty("horaSalud", fecha);
                prop.setProperty("hambre", Integer.toString(this.pou.getHambre()));
                prop.setProperty("horaHambre", fecha);
                prop.setProperty("energia", Integer.toString(this.pou.getEnergia()));
                prop.setProperty("horaEnergia", fecha);
                prop.store(output, null);
                output.close();

                pbEnergia.setValue(80);
                pbEntretencion.setValue(80);
                pbHambre.setValue(20);
                pbLimpieza.setValue(80);
                pbSalud.setValue(50);

                lblPorcEnergia.setText(80 + "%");
                lblPorcEntrete.setText(80 + "%");
                lblPorcHambre.setText(20 + "%");
                lblPorcLimpieza.setText(80 + "%");
                lblPorcSalud.setText(50 + "%");

                save = new File("mascota.properties");
                input = new FileInputStream(save);
                prop.load(input);
                String fechaProp = prop.getProperty("horaSalud");

                SaludThread st = new SaludThread(fechaProp, pou, pbSalud);
                st.start();
                HambreThread at = new HambreThread(fechaProp, pou, pbHambre);
                at.start();
                EntretencionThread ett = new EntretencionThread(fechaProp, pou, pbEntretencion);
                ett.start();
                EnergiaThread egt = new EnergiaThread(fechaProp, pou, pbEnergia);
                egt.start();
                LimpiezaThread lmt = new LimpiezaThread(fechaProp, pou, pbLimpieza);
                lmt.start();
            }
        } catch (Exception e) {
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pbSalud = new javax.swing.JProgressBar();
        pbLimpieza = new javax.swing.JProgressBar();
        pbEntretencion = new javax.swing.JProgressBar();
        ImgPou = new javax.swing.JLabel();
        btnAlimentar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnJugar = new javax.swing.JButton();
        pbEnergia = new javax.swing.JProgressBar();
        pbHambre = new javax.swing.JProgressBar();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblPorcSalud = new javax.swing.JLabel();
        lblPorcLimpieza = new javax.swing.JLabel();
        lblPorcEntrete = new javax.swing.JLabel();
        lblPorcHambre = new javax.swing.JLabel();
        lblPorcEnergia = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        ImgPou.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cl/org/res/Pou.png"))); // NOI18N

        btnAlimentar.setText("Alimentar");
        btnAlimentar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlimentarActionPerformed(evt);
            }
        });

        btnLimpiar.setText("Limpiar");

        btnJugar.setText("Jugar");

        jLabel1.setText("Salud:");

        jLabel2.setText("Limpieza:");

        jLabel3.setText("Entretención:");

        jLabel4.setText("Hambre:");

        jLabel5.setText("Energía:");

        lblPorcSalud.setText("...");

        lblPorcLimpieza.setText("...");

        lblPorcEntrete.setText("...");

        lblPorcHambre.setText("...");

        lblPorcEnergia.setText("...");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAlimentar)
                        .addGap(136, 136, 136)
                        .addComponent(btnLimpiar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 173, Short.MAX_VALUE)
                        .addComponent(btnJugar)
                        .addGap(41, 41, 41))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(pbSalud, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblPorcSalud)
                                .addGap(31, 31, 31))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(pbLimpieza, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(pbEntretencion, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblPorcEntrete)
                                    .addComponent(lblPorcLimpieza))
                                .addGap(31, 31, 31))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(155, 155, 155)
                                .addComponent(ImgPou))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5))
                                .addGap(45, 45, 45)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(pbEnergia, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(lblPorcEnergia))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(pbHambre, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(lblPorcHambre)))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pbSalud, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(lblPorcSalud))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pbLimpieza, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(lblPorcLimpieza))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pbEntretencion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(lblPorcEntrete))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pbHambre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(lblPorcHambre))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pbEnergia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(lblPorcEnergia))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 84, Short.MAX_VALUE)
                .addComponent(ImgPou)
                .addGap(78, 78, 78)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAlimentar)
                    .addComponent(btnLimpiar)
                    .addComponent(btnJugar))
                .addGap(30, 30, 30))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAlimentarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlimentarActionPerformed
        this.pou.setHambre(this.pou.getHambre() + 20);
        this.pbHambre.setValue(this.pou.getHambre());
    }//GEN-LAST:event_btnAlimentarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(App.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(App.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(App.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(App.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new App().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ImgPou;
    private javax.swing.JButton btnAlimentar;
    private javax.swing.JButton btnJugar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel lblPorcEnergia;
    private javax.swing.JLabel lblPorcEntrete;
    private javax.swing.JLabel lblPorcHambre;
    private javax.swing.JLabel lblPorcLimpieza;
    private javax.swing.JLabel lblPorcSalud;
    private javax.swing.JProgressBar pbEnergia;
    private javax.swing.JProgressBar pbEntretencion;
    private javax.swing.JProgressBar pbHambre;
    private javax.swing.JProgressBar pbLimpieza;
    private javax.swing.JProgressBar pbSalud;
    // End of variables declaration//GEN-END:variables
}
