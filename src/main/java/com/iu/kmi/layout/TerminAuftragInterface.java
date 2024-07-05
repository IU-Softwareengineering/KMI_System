/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.iu.kmi.layout;

import com.iu.kmi.database.orm.query.FindAllQuery;
import com.iu.kmi.database.orm.query.FindByIdQuery;
import com.iu.kmi.database.repository.RepositoryProxy;

import com.iu.kmi.entities.Angebot;
import com.iu.kmi.repositories.AngebotRepository;

import com.iu.kmi.entities.Kunde;
import com.iu.kmi.repositories.KundeRepository;

import com.iu.kmi.entities.Kundenauftrag;
import com.iu.kmi.repositories.KundenauftragRepository;

import java.time.LocalDateTime;

/**
 *
 * @author Julian H.
 */
public class TerminAuftragInterface extends javax.swing.JFrame {

    /**
     * Creates new form KundenauftragInterface
     */
    public TerminAuftragInterface() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabelTitelKundenauftrag = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        auftragsDatumTextField = new javax.swing.JTextField();
        terminAuftragTextField = new javax.swing.JTextField();
        kundenNummerTextField = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lieferDatumTextField = new javax.swing.JTextField();
        angebotsNummerTextField = new javax.swing.JTextField();
        statusTextField = new javax.swing.JTextField();
        abbrechenButton = new javax.swing.JButton();
        speichernButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new java.awt.BorderLayout());

        jLabelTitelKundenauftrag.setFont(new java.awt.Font("Liberation Sans", 1, 24)); // NOI18N
        jLabelTitelKundenauftrag.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTitelKundenauftrag.setText("Terminauftrag");
        jPanel1.add(jLabelTitelKundenauftrag, java.awt.BorderLayout.PAGE_START);

        jLabel2.setText("Auftragsdatum");
        jDesktopPane1.add(jLabel2);
        jLabel2.setBounds(30, 130, 110, 16);

        jLabel3.setText("Terminauftragnummer");
        jDesktopPane1.add(jLabel3);
        jLabel3.setBounds(30, 50, 160, 16);

        jLabel4.setText("Kundennummer");
        jDesktopPane1.add(jLabel4);
        jLabel4.setBounds(30, 90, 110, 16);

        auftragsDatumTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                auftragsDatumTextFieldActionPerformed(evt);
            }
        });
        jDesktopPane1.add(auftragsDatumTextField);
        auftragsDatumTextField.setBounds(200, 130, 260, 22);

        terminAuftragTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                terminAuftragTextFieldActionPerformed(evt);
            }
        });
        jDesktopPane1.add(terminAuftragTextField);
        terminAuftragTextField.setBounds(200, 50, 260, 22);
        jDesktopPane1.add(kundenNummerTextField);
        kundenNummerTextField.setBounds(200, 90, 260, 22);

        jLabel9.setText("Angebotsnummer");
        jDesktopPane1.add(jLabel9);
        jLabel9.setBounds(30, 210, 140, 16);

        jLabel10.setText("Lieferdatum");
        jDesktopPane1.add(jLabel10);
        jLabel10.setBounds(30, 170, 110, 16);
        jDesktopPane1.add(lieferDatumTextField);
        lieferDatumTextField.setBounds(200, 170, 260, 22);
        jDesktopPane1.add(angebotsNummerTextField);
        angebotsNummerTextField.setBounds(200, 210, 260, 22);
        jDesktopPane1.add(statusTextField);
        statusTextField.setBounds(200, 250, 260, 22);

        abbrechenButton.setText("Abbrechen");
        abbrechenButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                abbrechenButtonActionPerformed(evt);
            }
        });
        jDesktopPane1.add(abbrechenButton);
        abbrechenButton.setBounds(550, 370, 100, 23);

        speichernButton.setText("Speichern");
        speichernButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                speichernButtonActionPerformed(evt);
            }
        });
        jDesktopPane1.add(speichernButton);
        speichernButton.setBounds(660, 370, 120, 23);

        jLabel1.setText("Status");
        jDesktopPane1.add(jLabel1);
        jLabel1.setBounds(30, 250, 32, 16);

        jLabel13.setFont(new java.awt.Font("Liberation Sans", 1, 24)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jDesktopPane1.add(jLabel13);
        jLabel13.setBounds(-6, 0, 790, 30);

        jScrollPane1.setViewportView(jDesktopPane1);

        jPanel1.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 788, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 788, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 840, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 840, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void auftragsDatumTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_auftragsDatumTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_auftragsDatumTextFieldActionPerformed

    private void terminAuftragTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_terminAuftragTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_terminAuftragTextFieldActionPerformed

    private void emptyAllJTextFields(){ 
        terminAuftragTextField.setText("");
        kundenNummerTextField.setText("");
        auftragsDatumTextField.setText("");  
        lieferDatumTextField.setText("");
        angebotsNummerTextField.setText("");
        statusTextField.setText("");
    }
    
    private void abbrechenButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_abbrechenButtonActionPerformed
        emptyAllJTextFields();
    }//GEN-LAST:event_abbrechenButtonActionPerformed

    private void speichernButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_speichernButtonActionPerformed
        // abspeichern eines Terminauftrags
        LocalDateTime auftragsdatum;
        try{
        auftragsdatum = LocalDateTime.parse(auftragsDatumTextField.getText());
        }catch (Exception e){
            System.out.println(e);
            auftragsdatum = LocalDateTime.now();
        }
        LocalDateTime lieferdatum;
        try{
            lieferdatum = LocalDateTime.parse(lieferDatumTextField.getText());
        }catch(Exception e){
            System.out.println(e);
            lieferdatum = LocalDateTime.now();
        }
        Kunde kunde;
        KundeRepository kundeRepo = RepositoryProxy.newInstance(KundeRepository.class);
        FindByIdQuery<Kunde> findKunde;
        try{
            findKunde = kundeRepo.findById(kundenNummerTextField.getText());
            kunde = findKunde.findOne();
        }catch(Exception e){
            System.out.println(e);
            kunde = null;
        }
        kundeRepo.insert(kunde);
        
        Angebot angebot;
        AngebotRepository angebotRepo = RepositoryProxy.newInstance(AngebotRepository.class);
        FindByIdQuery<Angebot> findAngebot;
        try{
            findAngebot = angebotRepo.findById(angebotsNummerTextField.getText());
            angebot = findAngebot.findOne();
        }catch(Exception e){
            System.out.println(e);
            angebot = null;
        }
        angebotRepo.insert(angebot);
        
        Kundenauftrag auftrag;
        auftrag = new Kundenauftrag( 
                terminAuftragTextField.getText(),
                kunde,
                auftragsdatum,
                lieferdatum,
                angebot, 
                statusTextField.getText()
        );
        KundenauftragRepository kundenRepo = RepositoryProxy.newInstance(KundenauftragRepository.class);
        try{
            kundenRepo.insert(auftrag);
        }catch(Exception e){
            System.out.println(e.toString());
            emptyAllJTextFields();
            return;
        }
        emptyAllJTextFields();
    }//GEN-LAST:event_speichernButtonActionPerformed

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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LagerbestandInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LagerbestandInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LagerbestandInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LagerbestandInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TerminAuftragInterface().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton abbrechenButton;
    private javax.swing.JTextField angebotsNummerTextField;
    private javax.swing.JTextField auftragsDatumTextField;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelTitelKundenauftrag;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField kundenNummerTextField;
    private javax.swing.JTextField lieferDatumTextField;
    private javax.swing.JButton speichernButton;
    private javax.swing.JTextField statusTextField;
    private javax.swing.JTextField terminAuftragTextField;
    // End of variables declaration//GEN-END:variables
}
