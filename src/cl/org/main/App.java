package cl.org.main;

import cl.org.model.Mascota;
import cl.org.thread.EnergiaThread;
import cl.org.thread.EntretencionThread;
import cl.org.thread.HambreThread;
import cl.org.thread.KillerThread;
import cl.org.thread.LimpiezaThread;
import cl.org.thread.SaludThread;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

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
        shutdownHook();
        System.out.println(pou);
        setLocationRelativeTo(null);
    }

    private void shutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                try {
                    OutputStream output = new FileOutputStream(new File("mascota.properties"));
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                    String fecha = sdf.format(new Date());
                    prop.setProperty("entretencion", Integer.toString(pou.getEntretencion()));
                    prop.setProperty("horaEntretencion", fecha);
                    prop.setProperty("limpieza", Integer.toString(pou.getLimpieza()));
                    prop.setProperty("horaLimpieza", fecha);
                    prop.setProperty("salud", Integer.toString(pou.getSalud()));
                    prop.setProperty("horaSalud", fecha);
                    prop.setProperty("hambre", Integer.toString(pou.getHambre()));
                    prop.setProperty("horaHambre", fecha);
                    prop.setProperty("energia", Integer.toString(pou.getEnergia()));
                    prop.setProperty("horaEnergia", fecha);
                    prop.store(output, null);
                    output.close();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
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

                String fechaEntre = prop.getProperty("horaEntretencion");
                String fechaLimpi = prop.getProperty("horaLimpieza");
                String fechaSalud = prop.getProperty("horaSalud");
                String fechaHamb = prop.getProperty("horaHambre");
                String fechaEnerg = prop.getProperty("horaEnergia");

                this.pou = new Mascota(entretencion, limpieza, salud, hambre, energia);

                pbEnergia.setValue(energia);
                pbEntretencion.setValue(entretencion);
                pbHambre.setValue(hambre);
                pbLimpieza.setValue(limpieza);
                pbSalud.setValue(salud);

                lblPorcEnergia.setText(pbEnergia.getValue() + "%");
                lblPorcEntrete.setText(pbEntretencion.getValue() + "%");
                lblPorcHambre.setText(pbHambre.getValue() + "%");
                lblPorcLimpieza.setText(pbLimpieza.getValue() + "%");
                lblPorcSalud.setText(pbSalud.getValue() + "%");

                SaludThread st = new SaludThread(fechaSalud, pou, pbSalud, lblPorcSalud);
                st.start();
                HambreThread at = new HambreThread(fechaHamb, pou, pbHambre, lblPorcHambre);
                at.start();
                EntretencionThread ett = new EntretencionThread(fechaEntre, pou, pbEntretencion, lblPorcEntrete);
                ett.start();
                EnergiaThread egt = new EnergiaThread(fechaEnerg, pou, pbEnergia, lblPorcEnergia);
                egt.start();
                LimpiezaThread lmt = new LimpiezaThread(fechaLimpi, pou, pbLimpieza, lblPorcLimpieza);
                lmt.start();
                
                KillerThread kil = new KillerThread(pou, btnAlimentar, btnDormir, btnJugar, btnLimpiar, btnMedicina, ImgPou, App.this);
                kil.start();

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

                lblPorcEnergia.setText(pbEnergia.getValue() + "%");
                lblPorcEntrete.setText(pbEntretencion.getValue() + "%");
                lblPorcHambre.setText(pbHambre.getValue() + "%");
                lblPorcLimpieza.setText(pbLimpieza.getValue() + "%");
                lblPorcSalud.setText(pbSalud.getValue() + "%");

                save = new File("mascota.properties");
                input = new FileInputStream(save);
                prop.load(input);

                String fechaEntre = prop.getProperty("horaEntretencion");
                String fechaLimpi = prop.getProperty("horaLimpieza");
                String fechaSalud = prop.getProperty("horaSalud");
                String fechaHamb = prop.getProperty("horaHambre");
                String fechaEnerg = prop.getProperty("horaEnergia");

                SaludThread st = new SaludThread(fechaSalud, pou, pbSalud, lblPorcSalud);
                st.start();
                HambreThread at = new HambreThread(fechaHamb, pou, pbHambre, lblPorcHambre);
                at.start();
                EntretencionThread ett = new EntretencionThread(fechaEntre, pou, pbEntretencion, lblPorcEntrete);
                ett.start();
                EnergiaThread egt = new EnergiaThread(fechaEnerg, pou, pbEnergia, lblPorcEnergia);
                egt.start();
                LimpiezaThread lmt = new LimpiezaThread(fechaLimpi, pou, pbLimpieza, lblPorcLimpieza);
                lmt.start();

                KillerThread kil = new KillerThread(pou, btnAlimentar, btnDormir, btnJugar, btnLimpiar, btnMedicina, ImgPou, App.this);
                kil.start();

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

        frameCheat = new javax.swing.JFrame();
        pnlCheat = new javax.swing.JPanel();
        txtCheatTextArea = new javax.swing.JTextField();
        btnAceptarCheat = new javax.swing.JButton();
        pbSalud = new javax.swing.JProgressBar();
        pbLimpieza = new javax.swing.JProgressBar();
        pbEntretencion = new javax.swing.JProgressBar();
        ImgPou = new javax.swing.JLabel();
        btnAlimentar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnJugar = new javax.swing.JButton();
        pbEnergia = new javax.swing.JProgressBar();
        pbHambre = new javax.swing.JProgressBar();
        lblSalud = new javax.swing.JLabel();
        lblLimpieza = new javax.swing.JLabel();
        lblEntretencion = new javax.swing.JLabel();
        lblHambre = new javax.swing.JLabel();
        lblEnergia = new javax.swing.JLabel();
        lblPorcSalud = new javax.swing.JLabel();
        lblPorcLimpieza = new javax.swing.JLabel();
        lblPorcEntrete = new javax.swing.JLabel();
        lblPorcHambre = new javax.swing.JLabel();
        lblPorcEnergia = new javax.swing.JLabel();
        btnDormir = new javax.swing.JButton();
        btnMedicina = new javax.swing.JButton();
        mebPrograma = new javax.swing.JMenuBar();
        menArchivo = new javax.swing.JMenu();
        meiCheatArea = new javax.swing.JMenuItem();
        meiSalir = new javax.swing.JMenuItem();
        menAyuda = new javax.swing.JMenu();
        meiAcercaDe = new javax.swing.JMenuItem();

        frameCheat.setResizable(false);

        pnlCheat.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnAceptarCheat.setText("Aceptar");
        btnAceptarCheat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarCheatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlCheatLayout = new javax.swing.GroupLayout(pnlCheat);
        pnlCheat.setLayout(pnlCheatLayout);
        pnlCheatLayout.setHorizontalGroup(
            pnlCheatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCheatLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtCheatTextArea, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAceptarCheat)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlCheatLayout.setVerticalGroup(
            pnlCheatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCheatLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlCheatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCheatTextArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAceptarCheat))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout frameCheatLayout = new javax.swing.GroupLayout(frameCheat.getContentPane());
        frameCheat.getContentPane().setLayout(frameCheatLayout);
        frameCheatLayout.setHorizontalGroup(
            frameCheatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frameCheatLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlCheat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        frameCheatLayout.setVerticalGroup(
            frameCheatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frameCheatLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlCheat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Proyecto Pou v1.5b");

        ImgPou.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cl/org/res/Pou.png"))); // NOI18N
        ImgPou.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ImgPouMouseClicked(evt);
            }
        });

        btnAlimentar.setText("Alimentar");
        btnAlimentar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlimentarActionPerformed(evt);
            }
        });

        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        btnJugar.setText("Jugar");
        btnJugar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnJugarActionPerformed(evt);
            }
        });

        lblSalud.setText("Salud:");

        lblLimpieza.setText("Limpieza:");

        lblEntretencion.setText("Entretención:");

        lblHambre.setText("Hambre:");

        lblEnergia.setText("Energía:");

        lblPorcSalud.setText("...");

        lblPorcLimpieza.setText("...");

        lblPorcEntrete.setText("...");

        lblPorcHambre.setText("...");

        lblPorcEnergia.setText("...");

        btnDormir.setText("Dormir");
        btnDormir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDormirActionPerformed(evt);
            }
        });

        btnMedicina.setText("Medicina");
        btnMedicina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMedicinaActionPerformed(evt);
            }
        });

        menArchivo.setText("Archivo");

        meiCheatArea.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        meiCheatArea.setText("Cheat Area");
        meiCheatArea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                meiCheatAreaActionPerformed(evt);
            }
        });
        menArchivo.add(meiCheatArea);

        meiSalir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        meiSalir.setText("Salir");
        meiSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                meiSalirActionPerformed(evt);
            }
        });
        menArchivo.add(meiSalir);

        mebPrograma.add(menArchivo);

        menAyuda.setText("Ayuda");

        meiAcercaDe.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        meiAcercaDe.setText("Acerca de...");
        meiAcercaDe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                meiAcercaDeActionPerformed(evt);
            }
        });
        menAyuda.add(meiAcercaDe);

        mebPrograma.add(menAyuda);

        setJMenuBar(mebPrograma);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(btnAlimentar)
                        .addGap(18, 18, 18)
                        .addComponent(btnDormir)
                        .addGap(18, 18, 18)
                        .addComponent(btnLimpiar)
                        .addGap(18, 18, 18)
                        .addComponent(btnMedicina)
                        .addGap(18, 18, 18)
                        .addComponent(btnJugar))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblSalud)
                            .addComponent(lblLimpieza)
                            .addComponent(lblEntretencion))
                        .addGap(10, 10, 10)
                        .addComponent(pbEntretencion, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblPorcEntrete))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(155, 155, 155)
                        .addComponent(ImgPou))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblHambre)
                            .addComponent(lblEnergia))
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(pbHambre, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(pbEnergia, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblPorcHambre)
                                    .addComponent(lblPorcEnergia)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(pbSalud, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblPorcSalud))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(pbLimpieza, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblPorcLimpieza)))))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pbSalud, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSalud)
                    .addComponent(lblPorcSalud))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pbLimpieza, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLimpieza)
                    .addComponent(lblPorcLimpieza))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pbEntretencion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEntretencion)
                    .addComponent(lblPorcEntrete))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pbHambre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblHambre)
                    .addComponent(lblPorcHambre))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pbEnergia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEnergia)
                    .addComponent(lblPorcEnergia))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                .addComponent(ImgPou)
                .addGap(78, 78, 78)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAlimentar)
                    .addComponent(btnLimpiar)
                    .addComponent(btnJugar)
                    .addComponent(btnDormir)
                    .addComponent(btnMedicina))
                .addGap(30, 30, 30))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAlimentarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlimentarActionPerformed
        getContentPane().setBackground(new java.awt.Color(255, 69, 0));
        ImageIcon myIcon = new ImageIcon("src\\cl\\org\\res\\Pou.png");
        ImgPou.setIcon(myIcon);

        if (pbHambre.getValue() >= 100) {
            playSoundNo();
        } else {
            pou.setHambre(pou.getHambre() + 10);
            pbHambre.setValue(pou.getHambre());
            lblPorcHambre.setText(pbHambre.getValue() + "%");
            playSoundAlimentar();
        }
    }//GEN-LAST:event_btnAlimentarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        getContentPane().setBackground(new java.awt.Color(128, 0, 0));
        ImageIcon myIcon = new ImageIcon("src\\cl\\org\\res\\Pou.png");
        ImgPou.setIcon(myIcon);

        if (pbLimpieza.getValue() >= 100) {
            playSoundNo();
        } else {
            pou.setLimpieza(pou.getLimpieza() + 10);
            pbLimpieza.setValue(pou.getLimpieza());
            lblPorcLimpieza.setText(pbLimpieza.getValue() + "%");
            playSoundLimpiar();
        }
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnJugarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnJugarActionPerformed
        getContentPane().setBackground(new java.awt.Color(0, 100, 0));
        ImageIcon myIcon = new ImageIcon("src\\cl\\org\\res\\Pou.png");
        ImgPou.setIcon(myIcon);

        if (pbEntretencion.getValue() >= 100) {
            playSoundNo();
        } else {
            pou.setEntretencion(pou.getEntretencion() + 5);
            pbEntretencion.setValue(pou.getEntretencion());
            lblPorcEntrete.setText(pbEntretencion.getValue() + "%");
            playSoundLimpiar();
        }
    }//GEN-LAST:event_btnJugarActionPerformed

    private void ImgPouMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ImgPouMouseClicked
        if (evt.getClickCount() == 2) {
            playSoundHmmm();
        }
    }//GEN-LAST:event_ImgPouMouseClicked

    private void btnDormirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDormirActionPerformed
        if (pbEnergia.getValue() >= 100) {
            playSoundNo();
        } else {
            getContentPane().setBackground(new java.awt.Color(116, 76, 41));
            ImageIcon myIcon = new ImageIcon("src\\cl\\org\\res\\Pou_Sleeping.png");
            ImgPou.setIcon(myIcon);

            pou.setEnergia(pou.getEnergia() + 99);
            pbEnergia.setValue(pou.getEnergia());
            lblPorcEnergia.setText(pbEnergia.getValue() + "%");

            playSoundSuccess();
        }

    }//GEN-LAST:event_btnDormirActionPerformed

    private void btnMedicinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMedicinaActionPerformed
        getContentPane().setBackground(new java.awt.Color(25, 25, 112));
        ImageIcon myIcon = new ImageIcon("src\\cl\\org\\res\\Pou.png");
        ImgPou.setIcon(myIcon);

        if (pbSalud.getValue() >= 100) {
            playSoundNo();
        } else {
            pou.setSalud(pou.getSalud() + 5);
            pbSalud.setValue(pou.getSalud());
            lblPorcSalud.setText(pbSalud.getValue() + "%");
            playSoundSuccess();
        }
    }//GEN-LAST:event_btnMedicinaActionPerformed

    private void btnAceptarCheatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarCheatActionPerformed
        if (txtCheatTextArea.getText().equals("sleepwithfishes")) {

            getContentPane().setBackground(new java.awt.Color(211, 211, 211));
            ImageIcon myIcon = new ImageIcon("src\\cl\\org\\res\\Death.png");
            ImgPou.setIcon(myIcon);

            playSoundGameOver();

            this.pou = new Mascota(0, 0, 0, 0, 0);

            pbEnergia.setValue(0);
            pbEntretencion.setValue(0);
            pbHambre.setValue(0);
            pbLimpieza.setValue(0);
            pbSalud.setValue(0);

            lblPorcEnergia.setText(pbEnergia.getValue() + "%");
            lblPorcEntrete.setText(pbEntretencion.getValue() + "%");
            lblPorcHambre.setText(pbHambre.getValue() + "%");
            lblPorcLimpieza.setText(pbLimpieza.getValue() + "%");
            lblPorcSalud.setText(pbSalud.getValue() + "%");

            btnAlimentar.setEnabled(false);
            btnDormir.setEnabled(false);
            btnJugar.setEnabled(false);
            btnLimpiar.setEnabled(false);
            btnMedicina.setEnabled(false);

            txtCheatTextArea.setText("");
            txtCheatTextArea.requestFocus();

        }

        if (txtCheatTextArea.getText().equals("pleasegivemeasecondchance")) {
            getContentPane().setBackground(new java.awt.Color(245, 245, 245));
            ImageIcon myIcon = new ImageIcon("src\\cl\\org\\res\\Pou.png");
            ImgPou.setIcon(myIcon);

            playSoundSuccess();

            this.pou = new Mascota(80, 80, 50, 20, 80);

            pbEnergia.setValue(80);
            pbEntretencion.setValue(80);
            pbHambre.setValue(20);
            pbLimpieza.setValue(80);
            pbSalud.setValue(50);

            lblPorcEnergia.setText(pbEnergia.getValue() + "%");
            lblPorcEntrete.setText(pbEntretencion.getValue() + "%");
            lblPorcHambre.setText(pbHambre.getValue() + "%");
            lblPorcLimpieza.setText(pbLimpieza.getValue() + "%");
            lblPorcSalud.setText(pbSalud.getValue() + "%");

            btnAlimentar.setEnabled(true);
            btnDormir.setEnabled(true);
            btnJugar.setEnabled(true);
            btnLimpiar.setEnabled(true);
            btnMedicina.setEnabled(true);

            txtCheatTextArea.setText("");
            txtCheatTextArea.requestFocus();

        }

    }//GEN-LAST:event_btnAceptarCheatActionPerformed

    private void meiCheatAreaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_meiCheatAreaActionPerformed
        frameCheat.setVisible(true);
        frameCheat.setBounds(0, 0, 420, 100);
        frameCheat.setLocationRelativeTo(null);
        frameCheat.setTitle("Cheat Code Central");
    }//GEN-LAST:event_meiCheatAreaActionPerformed

    private void meiAcercaDeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_meiAcercaDeActionPerformed
        String titVentana = "Información";
        String vntMensaje = "Software Creado por Javier Vergara y Jorge Anjel.";

        int tipoa = JOptionPane.INFORMATION_MESSAGE;

        JOptionPane.showMessageDialog(this, vntMensaje, titVentana, tipoa);

    }//GEN-LAST:event_meiAcercaDeActionPerformed

    private void meiSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_meiSalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_meiSalirActionPerformed

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
    private javax.swing.JButton btnAceptarCheat;
    private javax.swing.JButton btnAlimentar;
    private javax.swing.JButton btnDormir;
    private javax.swing.JButton btnJugar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnMedicina;
    private javax.swing.JFrame frameCheat;
    private javax.swing.JLabel lblEnergia;
    private javax.swing.JLabel lblEntretencion;
    private javax.swing.JLabel lblHambre;
    private javax.swing.JLabel lblLimpieza;
    private javax.swing.JLabel lblPorcEnergia;
    private javax.swing.JLabel lblPorcEntrete;
    private javax.swing.JLabel lblPorcHambre;
    private javax.swing.JLabel lblPorcLimpieza;
    private javax.swing.JLabel lblPorcSalud;
    private javax.swing.JLabel lblSalud;
    private javax.swing.JMenuBar mebPrograma;
    private javax.swing.JMenuItem meiAcercaDe;
    private javax.swing.JMenuItem meiCheatArea;
    private javax.swing.JMenuItem meiSalir;
    private javax.swing.JMenu menArchivo;
    private javax.swing.JMenu menAyuda;
    private javax.swing.JProgressBar pbEnergia;
    private javax.swing.JProgressBar pbEntretencion;
    private javax.swing.JProgressBar pbHambre;
    private javax.swing.JProgressBar pbLimpieza;
    private javax.swing.JProgressBar pbSalud;
    private javax.swing.JPanel pnlCheat;
    private javax.swing.JTextField txtCheatTextArea;
    // End of variables declaration//GEN-END:variables

    public void playSoundAlimentar() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("src\\cl\\org\\res\\alimentar.wav").getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex) {
            System.out.println("Hay un error con el sonido, contacte a janjell.");
            ex.printStackTrace();
        }
    }

    public void playSoundNo() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("src\\cl\\org\\res\\no.wav").getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex) {
            System.out.println("Hay un error con el sonido, contacte a janjell.");
            ex.printStackTrace();
        }
    }

    public void playSoundLimpiar() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("src\\cl\\org\\res\\limpiar.wav").getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex) {
            System.out.println("Hay un error con el sonido, contacte a janjell.");
            ex.printStackTrace();
        }
    }

    public void playSoundHmmm() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("src\\cl\\org\\res\\hmmm.wav").getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex) {
            System.out.println("Hay un error con el sonido, contacte a janjell.");
            ex.printStackTrace();
        }
    }

    public void playSoundSuccess() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("src\\cl\\org\\res\\success.wav").getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex) {
            System.out.println("Hay un error con el sonido, contacte a janjell.");
            ex.printStackTrace();
        }
    }

    public void playSoundGameOver() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("src\\cl\\org\\res\\game_over.wav").getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex) {
            System.out.println("Hay un error con el sonido, contacte a janjell.");
            ex.printStackTrace();
        }
    }

}
