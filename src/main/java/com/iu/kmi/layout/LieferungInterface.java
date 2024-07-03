/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.iu.kmi.layout;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.iu.kmi.database.repository.RepositoryProxy;
import com.iu.kmi.entities.Auftrag;
import com.iu.kmi.entities.AuftragsPosition;
import com.iu.kmi.entities.Lagerbestand;
import com.iu.kmi.entities.Lieferung;
import com.iu.kmi.entities.Lieferungsposition;
import com.iu.kmi.entities.Material;
import com.iu.kmi.entities.Rechnung;
import com.iu.kmi.repositories.AuftragRespository;
import com.iu.kmi.repositories.AuftragspositionRepository;
import com.iu.kmi.repositories.LagerbestandRepository;
import com.iu.kmi.repositories.LieferungRepository;
import com.iu.kmi.repositories.MaterialRepository;
import com.iu.kmi.repositories.RechnungRepository;

/**
 *
 * @author Julian Treichel
 * @since 02.07.2024
 */
public class LieferungInterface extends javax.swing.JFrame {

    private static final String             DATE_PATTERN   = "\\d{2}\\.\\d{2}\\.\\d{4}";
    private static final DateTimeFormatter  DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private AuftragRespository              auftragRespository;
    private AuftragspositionRepository      auftragspositionRepository;
    private RechnungRepository              rechnungRepository;
    private LagerbestandRepository          lagerbestandRepository;
    private LieferungRepository             lieferungRepository;
    private MaterialRepository              materialRepository;
    private AtomicInteger                   rowNumber = new AtomicInteger(1);

    /**
     * Creates new form LieferungInterface2
     */
    public LieferungInterface() throws ReflectiveOperationException, SQLException{

        initComponents();
        initRepositories();
        fillAuftragsSelect();
        fillRechnungsSelect();
        fillDate();

    }

    public void initRepositories(){
        this.auftragRespository = RepositoryProxy.newInstance(AuftragRespository.class);
        this.auftragspositionRepository = RepositoryProxy.newInstance(AuftragspositionRepository.class);
        this.rechnungRepository = RepositoryProxy.newInstance(RechnungRepository.class);
        this.lagerbestandRepository = RepositoryProxy.newInstance(LagerbestandRepository.class);
        this.lieferungRepository = RepositoryProxy.newInstance(LieferungRepository.class);
        this.materialRepository = RepositoryProxy.newInstance(MaterialRepository.class);
    }

    public void fillAuftragsSelect() throws ReflectiveOperationException, SQLException{
        List<Auftrag> auftragList = auftragRespository.findAll().execute();
        auftragList.forEach(auftrag -> selectAuftrag.addItem(auftrag.getAuftragNr()));
    }

    public void fillRechnungsSelect() throws ReflectiveOperationException, SQLException{
        List<Rechnung> rechnungList = rechnungRepository.findAll().execute();
        rechnungList.forEach(rechnung -> selectRechnung.addItem(rechnung.getRechnungNr()));
    }

    private void fillDate() throws ReflectiveOperationException, SQLException{

        textboxLieferdatum.setText(fetchAuftrag(selectAuftrag.getSelectedItem().toString()).getLieferdatum().format(DATE_FORMATTER));
    }

    private void loadAllData(final String auftragsNr) throws ReflectiveOperationException, SQLException {
        List<AuftragsPosition> auftragsPositions = fetchAllAuftragsposition(auftragsNr);
        List<Lagerbestand> lagerbestandList = this.lagerbestandRepository.findAll().execute();
        Map<String, Integer> lagerbestandMap = lagerbestandList.stream()
                .collect(Collectors.toMap(lb -> lb.getArtikelNummer().getArtikelNr(), Lagerbestand::getMenge));

        DefaultTableModel defaultTableModel = (DefaultTableModel) tablePositionen.getModel();
        defaultTableModel.setRowCount(0);

        rowNumber = new AtomicInteger(1);

        auftragsPositions.stream()
                .collect(Collectors.groupingBy(
                        item -> item.getArtikelNr().getArtikelNr(),
                        Collectors.counting()
                ))
                .forEach((k, v) -> {
                    Optional<Material> optionalMaterial = auftragsPositions.stream()
                            .filter(auftragsPosition -> auftragsPosition.getArtikelNr().getArtikelNr().equals(k))
                            .map(AuftragsPosition::getArtikelNr)
                            .findFirst();

                    optionalMaterial.ifPresent(material -> {
                        int auftragsAnzahl = v.intValue();
                        int lagerbestand = lagerbestandMap.getOrDefault(material.getArtikelNr(), 0);
                        String availability = getAvailability(auftragsAnzahl, lagerbestand);

                        Object[] newRow = { rowNumber.getAndIncrement(), k,
                                material.getName(), auftragsAnzahl, availability };
                        defaultTableModel.addRow(newRow);
                    });
                });
    }


    private String getAvailability(int angefordert, int lagerbestand) {
        if (angefordert <= lagerbestand) {
            return "verfügbar";
        } else if (lagerbestand == 0) {
            return "nicht verfügbar";
        } else {
            return lagerbestand + " von " + angefordert + " verfügbar";
        }
    }

    private List<AuftragsPosition> fetchAllAuftragsposition(String auftragsNr) throws ReflectiveOperationException, SQLException {
        List<AuftragsPosition> auftragsPositions = this.auftragspositionRepository.findAll().execute();
        return auftragsPositions.stream()
                .filter(a -> Objects.equals(a.getAuftragsNr().getAuftragNr(), auftragsNr))
                .collect(Collectors.toList());
    }

    private Auftrag fetchAuftrag(String auftragNr) throws ReflectiveOperationException, SQLException{
        Auftrag auftrag = this.auftragRespository.findById(auftragNr).findOne();
        return auftrag;
    }

    private Rechnung fetchRechnung(String rechnungNr) throws ReflectiveOperationException, SQLException{
        Rechnung rechnung = this.rechnungRepository.findById(rechnungNr).findOne();
        return rechnung;
    }

    private Lieferung fetchLieferung(String lieferungNr) throws ReflectiveOperationException, SQLException{
        Lieferung lieferung = this.lieferungRepository.findById(lieferungNr).findOne();
        return lieferung;
    }

    private Material fetchMaterial(String materialNr) throws ReflectiveOperationException, SQLException{
        Material material = this.materialRepository.findById(materialNr).findOne();
        return material;
    }

    private boolean existLieferung(String lieferungNr){

        try{
            return fetchLieferung(lieferungNr) != null;
        }
        catch(ReflectiveOperationException e){
            handleException(e);
        }
        catch(SQLException e){
            handleException(e);
        }
        return true;
    }

    private void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Fehler", JOptionPane.ERROR_MESSAGE);
    }

    private void handleException(Exception e) {
        throw new RuntimeException(e);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        labelLieferungsdatum = new javax.swing.JLabel();
        buttonLieferpositionErstellen = new javax.swing.JButton();
        buttonLieferpositionLöschen = new javax.swing.JButton();
        textboxLieferdatum = new javax.swing.JTextField();
        labelRechnung = new javax.swing.JLabel();
        selectAuftrag = new javax.swing.JComboBox<>();
        labelLieferung = new javax.swing.JLabel();
        labelAuftrag = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        textboxLieferung = new javax.swing.JTextField();
        buttonAbbrechen = new javax.swing.JButton();
        buttonSpeichern = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablePositionen = new javax.swing.JTable();
        selectRechnung = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        labelLieferungsdatum.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        labelLieferungsdatum.setForeground(new java.awt.Color(51, 51, 51));
        labelLieferungsdatum.setText("Lieferungsdatum");

        buttonLieferpositionErstellen.setText("Erstellen");
        buttonLieferpositionErstellen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonLieferpositionErstellenActionPerformed(evt);
            }
        });

        buttonLieferpositionLöschen.setText("Löschen");
        buttonLieferpositionLöschen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonLieferpositionLöschenActionPerformed(evt);
            }
        });

        textboxLieferdatum.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        textboxLieferdatum.setForeground(new java.awt.Color(51, 51, 51));
        textboxLieferdatum.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        textboxLieferdatum.setAutoscrolls(false);
        textboxLieferdatum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textboxLieferdatumActionPerformed(evt);
            }
        });

        labelRechnung.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        labelRechnung.setForeground(new java.awt.Color(51, 51, 51));
        labelRechnung.setText("Rechnungs-ID");

        selectAuftrag.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                selectAuftragComponentShown(evt);
            }
        });
        selectAuftrag.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try{
                    selectAuftragActionPerformed(evt);
                }
                catch(ReflectiveOperationException e){
                    throw new RuntimeException(e);
                }
                catch(SQLException e){
                    throw new RuntimeException(e);
                }
            }
        });

        labelLieferung.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        labelLieferung.setForeground(new java.awt.Color(51, 51, 51));
        labelLieferung.setText("Lieferungs-ID");

        labelAuftrag.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        labelAuftrag.setForeground(new java.awt.Color(51, 51, 51));
        labelAuftrag.setText("Auftrags-ID");

        jLabel1.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Lieferung");

        textboxLieferung.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        textboxLieferung.setForeground(new java.awt.Color(51, 51, 51));
        textboxLieferung.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        textboxLieferung.setAutoscrolls(false);
        textboxLieferung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textboxLieferungActionPerformed(evt);
            }
        });

        buttonAbbrechen.setBackground(new java.awt.Color(244, 67, 54));
        buttonAbbrechen.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        buttonAbbrechen.setForeground(new java.awt.Color(255, 255, 255));
        buttonAbbrechen.setText("Abbrechen");
        buttonAbbrechen.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        buttonAbbrechen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAbbrechenActionPerformed(evt);
            }
        });

        buttonSpeichern.setBackground(new java.awt.Color(58, 132, 233));
        buttonSpeichern.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        buttonSpeichern.setForeground(new java.awt.Color(255, 255, 255));
        buttonSpeichern.setText("Speichern");
        buttonSpeichern.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSpeichernActionPerformed(evt);
            }
        });

        tablePositionen.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {
                },
                new String [] {
                        "Lieferposition", "Artikelnummer", "Artikelname", "Menge", "Verfügbarkeit"
                }
        ));
        jScrollPane1.setViewportView(tablePositionen);

        selectRechnung.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                selectRechnungComponentShown(evt);
            }
        });
        selectRechnung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectRechnungActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 688, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(buttonLieferpositionErstellen)
                                        .addComponent(buttonLieferpositionLöschen))
                                .addGap(71, 71, 71))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(63, 63, 63)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(labelAuftrag, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(labelLieferung, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(textboxLieferung, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(26, 26, 26)
                                                .addComponent(labelRechnung, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(selectRechnung, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(selectAuftrag, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(26, 26, 26)
                                                .addComponent(labelLieferungsdatum)
                                                .addGap(29, 29, 29)
                                                .addComponent(textboxLieferdatum, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(275, 275, 275))
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(338, 338, 338)
                                                .addComponent(jLabel1))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(318, 318, 318)
                                                .addComponent(buttonSpeichern, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(37, 37, 37)
                                                .addComponent(buttonAbbrechen, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(57, 57, 57)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(labelLieferung, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(textboxLieferung, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(labelRechnung, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(selectRechnung, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(labelAuftrag, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(selectAuftrag, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(labelLieferungsdatum, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(textboxLieferdatum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(buttonLieferpositionErstellen)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(buttonLieferpositionLöschen)
                                                .addGap(221, 221, 221))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(34, 34, 34)
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(53, 53, 53)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(buttonSpeichern, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(buttonAbbrechen, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addContainerGap(77, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>

    private void buttonLieferpositionErstellenActionPerformed(java.awt.event.ActionEvent evt) {

        DefaultTableModel defaultTableModel = ((DefaultTableModel)tablePositionen.getModel());

        Object[] newRow = { rowNumber.getAndIncrement(), "",
                "", "", "" };
        defaultTableModel.addRow(newRow);
    }

    private void buttonLieferpositionLöschenActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        int selectedPosition = this.tablePositionen.getSelectedRow();
        if (selectedPosition == -1) {
            return;
        }
        DefaultTableModel model = ((DefaultTableModel)tablePositionen.getModel());
        model.removeRow(selectedPosition);
    }

    private void textboxLieferdatumActionPerformed(java.awt.event.ActionEvent evt) {
        // Prozess - Datumsvalidierung
        String dateString = textboxLieferdatum.getText().trim();

        // Versuchen, das Datum zu parsen
        try {
            LocalDate date = LocalDate.parse(dateString, DATE_FORMATTER);
            // Wenn das Parsen erfolgreich ist, kannst du hier weiterverarbeiten
            // Zum Beispiel: Speichern des Datums oder Ausführen weiterer Operationen
        } catch (DateTimeParseException e) {
            // Fehler bei der Parsen des Datums
            showErrorMessage("Ungültiges Datum. Das Datum entspricht nicht dem erwarteten Format.");
        }
    }

    private void selectAuftragComponentShown(java.awt.event.ComponentEvent evt) {

    }

    private void selectAuftragActionPerformed(java.awt.event.ActionEvent evt)
            throws ReflectiveOperationException, SQLException{
        loadAllData(selectAuftrag.getSelectedItem().toString());
        fillDate();

    }

    private void textboxLieferungActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void buttonAbbrechenActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void buttonSpeichernActionPerformed(java.awt.event.ActionEvent evt) {
        // Lieferung anlegen
        Lieferung lieferung = new Lieferung();

        // Lieferungsnummer auslesen & validieren
        String lieferID = textboxLieferung.getText().trim();
        if (lieferID.isEmpty()) {
            showErrorMessage("Bitte geben Sie eine Lieferungs-ID an.");
            return;
        } else if (existLieferung(lieferID)) {
            showErrorMessage("Die Lieferungs-ID existiert bereits. Bitte wählen Sie eine andere ID.");
            return;
        }
        lieferung.setLieferungNr(lieferID);

        // Auftragsnummer auslesen & validieren
        Optional<Auftrag> optionalAuftrag = Optional.empty();
        try {
            optionalAuftrag = Optional.ofNullable(fetchAuftrag(selectAuftrag.getSelectedItem().toString()));
        } catch (ReflectiveOperationException | SQLException e) {
            handleException(e);
        }
        if (optionalAuftrag.isEmpty()) {
            showErrorMessage("Auftrag nicht gefunden.");
            return;
        }
        lieferung.setAuftrag(optionalAuftrag.get());

        // Rechnungsnummer auslesen & validieren
        Optional<Rechnung> optionalRechnung = Optional.empty();
        try {
            optionalRechnung = Optional.ofNullable(fetchRechnung(selectRechnung.getSelectedItem().toString()));
        } catch (ReflectiveOperationException | SQLException e) {
            handleException(e);
        }
        if (optionalRechnung.isEmpty()) {
            showErrorMessage("Rechnung nicht gefunden.");
            return;
        }
        lieferung.setRechnung(optionalRechnung.get());

        // Lieferdatum auslesen & validieren
        String lieferdatumText = textboxLieferdatum.getText().trim();
        if (lieferdatumText.isEmpty()) {
            showErrorMessage("Bitte geben Sie das Lieferdatum an.");
            return;
        }
        try {
            LocalDate lieferdatum = LocalDate.parse(lieferdatumText, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
            Auftrag auftrag = fetchAuftrag(selectAuftrag.getSelectedItem().toString());
            auftrag.setLieferdatum(lieferdatum.atStartOfDay());
            auftragRespository.update(auftrag);
        } catch (DateTimeParseException e) {
            showErrorMessage("Das Lieferdatum ist fehlerhaft. Geben Sie das Datum im Format dd.MM.yyyy an.");
            return;
        }
        catch(ReflectiveOperationException e){
            throw new RuntimeException(e);
        }
        catch(SQLException e){
            throw new RuntimeException(e);
        }

        lieferungRepository.insert(lieferung);

        // TODO: Lieferpositionen auslesen & validieren & abspeichern




        JOptionPane.showMessageDialog(this, "Die Lieferung wurde erfolgreich gespeichert.", "Erfolg", JOptionPane.INFORMATION_MESSAGE);

        // Maske zurücksetzen

        // Methoden und Hilfsfunktionen
        // TODO: existierenden Lieferung anpassen -> Bearbeitungsmodus

        // TODO: Lieferschein erzeugen
    }

    private void selectRechnungComponentShown(java.awt.event.ComponentEvent evt) {
        // TODO add your handling code here:
    }

    private void selectRechnungActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

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
            java.util.logging.Logger.getLogger(LieferungInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LieferungInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LieferungInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LieferungInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try{
                    new LieferungInterface().setVisible(true);
                }
                catch(ReflectiveOperationException e){
                    throw new RuntimeException(e);
                }
                catch(SQLException e){
                    throw new RuntimeException(e);
                }
            }
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JButton buttonAbbrechen;
    private javax.swing.JButton buttonLieferpositionErstellen;
    private javax.swing.JButton buttonLieferpositionLöschen;
    private javax.swing.JButton buttonSpeichern;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelAuftrag;
    private javax.swing.JLabel labelLieferung;
    private javax.swing.JLabel labelLieferungsdatum;
    private javax.swing.JLabel labelRechnung;
    private javax.swing.JComboBox<String> selectAuftrag;
    private javax.swing.JComboBox<String> selectRechnung;
    private javax.swing.JTable tablePositionen;
    private javax.swing.JTextField textboxLieferdatum;
    private javax.swing.JTextField textboxLieferung;
    // End of variables declaration
}
