/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.iu.kmi.layout;

import java.awt.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
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
import com.iu.kmi.repositories.LieferungspositionRepository;
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
    private LieferungspositionRepository    lieferungspositionRepository;
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
        setLieferungsID();

    }

    public void initRepositories(){
        this.auftragRespository = RepositoryProxy.newInstance(AuftragRespository.class);
        this.auftragspositionRepository = RepositoryProxy.newInstance(AuftragspositionRepository.class);
        this.rechnungRepository = RepositoryProxy.newInstance(RechnungRepository.class);
        this.lagerbestandRepository = RepositoryProxy.newInstance(LagerbestandRepository.class);
        this.lieferungRepository = RepositoryProxy.newInstance(LieferungRepository.class);
        this.materialRepository = RepositoryProxy.newInstance(MaterialRepository.class);
        this.lieferungspositionRepository = RepositoryProxy.newInstance(LieferungspositionRepository.class);
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

    private void setLieferungsID() {
        // TODO: falsche Id ab Ende LIE-3 statt LIE-5
        textfieldLieferungNr.setText("");
        try{
            textfieldLieferungNr.setText(generateLieferungNr());
        }
        catch(ReflectiveOperationException e){
            throw new RuntimeException(e);
        }
        catch(SQLException e){
            throw new RuntimeException(e);
        }

    }
    private String generateLieferungNr() throws ReflectiveOperationException, SQLException{
        try {
            List<Lieferung> lieferungList = lieferungRepository.findAll().execute();
            if (!lieferungList.isEmpty()) {
                lieferungList.forEach(lieferung -> System.out.println(lieferung.getLieferungNr()));
                Lieferung lastLieferung = lieferungList.getLast();
                String lastLieferungNr = lastLieferung.getLieferungNr();
                String prefix = lastLieferungNr.substring(0, 4); // "LIE-"
                int number = Integer.parseInt(lastLieferungNr.substring(4));
                return prefix + (number + 1);
            }
        } catch (ReflectiveOperationException | SQLException e) {
            handleException(e);
        }
        return "LIE-0";
    }

    private void loadAllData(final String auftragsNr) throws ReflectiveOperationException, SQLException {
        List<AuftragsPosition> auftragsPositions = fetchAllAuftragsposition(auftragsNr);

        DefaultTableModel defaultTableModel = (DefaultTableModel) tablePositionen.getModel();
        defaultTableModel.setRowCount(0);

        rowNumber = new AtomicInteger(1);

        auftragsPositions.forEach(auftragsPosition -> {


                        int auftragsAnzahl = auftragsPosition.getMenge();
                        int lagerbestand = 0;
                        try{
                            lagerbestand = getLagetbestandForMaterial(auftragsPosition.getArtikelNr());
                        }
                        catch(ReflectiveOperationException e){
                            throw new RuntimeException(e);
                        }
                        catch(SQLException e){
                            throw new RuntimeException(e);
                        }
                        String availability = calculateAvailibility(auftragsAnzahl, lagerbestand);

                        Object[] newRow = { rowNumber.getAndIncrement(), auftragsPosition.getArtikelNr().getArtikelNr(),
                                auftragsPosition.getArtikelNr().getName(), auftragsAnzahl, availability };
                        defaultTableModel.addRow(newRow);

                });

        defaultTableModel.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(final TableModelEvent e) {
                if (e.getType() == TableModelEvent.UPDATE && e.getColumn() == 3) {
                    int row = e.getFirstRow();
                    System.out.println(defaultTableModel.getValueAt(row, 3).toString());
                    int menge = (int) defaultTableModel.getValueAt(row, 3);
                    try{
                        Material material = fetchMaterial( (String) defaultTableModel.getValueAt(row, 1));
                        int lagerbestand = getLagetbestandForMaterial(material);
                        String availibilty = calculateAvailibility(menge, lagerbestand);
                        defaultTableModel.setValueAt(availibilty, row, 4);
                    }
                    catch(ReflectiveOperationException ex){
                        throw new RuntimeException(ex);
                    }
                    catch(SQLException ex){
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
    }


    private String calculateAvailibility(int angefordert, int lagerbestand) {
        if (angefordert <= lagerbestand) {
            return "verfügbar";
        } else if (lagerbestand == 0) {
            return "nicht verfügbar";
        } else {
            return lagerbestand + " von " + angefordert + " verfügbar";
        }
    }

    private int getLagetbestandForMaterial(Material material) throws ReflectiveOperationException, SQLException{
        List<Lagerbestand> lagerbestandList = this.lagerbestandRepository.findAll().execute();
        Map<String, Integer> lagerbestandMap = lagerbestandList.stream()
                .collect(Collectors.toMap(lb -> lb.getArtikelNummer().getArtikelNr(), Lagerbestand::getMenge));
        int lagerbestand = lagerbestandMap.getOrDefault(material.getArtikelNr(), 0);

        return lagerbestand;
    }

    private List<AuftragsPosition> fetchAllAuftragsposition(String auftragsNr) throws ReflectiveOperationException, SQLException {
        List<AuftragsPosition> auftragsPositions = this.auftragspositionRepository.findAll().execute();
        return auftragsPositions.stream()
                .filter(a -> Objects.equals(a.getAuftragsNr().getAuftragNr(), auftragsNr))
                .collect(Collectors.toList());
    }

    private Auftrag fetchAuftrag(String auftragNr) throws ReflectiveOperationException, SQLException{
        return this.auftragRespository.findById(auftragNr).findOne();
    }

    private Rechnung fetchRechnung(String rechnungNr) throws ReflectiveOperationException, SQLException{
        return this.rechnungRepository.findById(rechnungNr).findOne();
    }

    private Lieferung fetchLieferung(String lieferungNr) throws ReflectiveOperationException, SQLException{
        return this.lieferungRepository.findById(lieferungNr).findOne();
    }

    private Material fetchMaterial(String materialNr) throws ReflectiveOperationException, SQLException{
        return this.materialRepository.findById(materialNr).findOne();
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
        textfieldLieferungNr = new javax.swing.JTextField();
        buttonAbbrechen = new javax.swing.JButton();
        buttonSpeichern = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablePositionen = new javax.swing.JTable();
        selectRechnung = new javax.swing.JComboBox<>();
        buttonLieferschein = new javax.swing.JButton();

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

        textfieldLieferungNr.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        textfieldLieferungNr.setForeground(new java.awt.Color(51, 51, 51));
        textfieldLieferungNr.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        textfieldLieferungNr.setAutoscrolls(false);
        textfieldLieferungNr.setText("Test");
        textfieldLieferungNr.setEnabled(false);


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

        buttonLieferschein.setBackground(new java.awt.Color(255, 255, 255));
        buttonLieferschein.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        buttonLieferschein.setForeground(new java.awt.Color(0, 0, 0));
        buttonLieferschein.setText("Lieferschein");
        buttonLieferschein.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonLieferscheinActionPerformed(evt);
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
                                                .addComponent(textfieldLieferungNr, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                                                .addGap(240, 240, 240)
                                                .addComponent(buttonLieferschein, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(100, 100, 100)
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
                                        .addComponent(textfieldLieferungNr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                                                        .addComponent(buttonLieferschein, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void buttonLieferscheinActionPerformed(java.awt.event.ActionEvent evt) {
        String[][] data = {
                {"1", "1001", "2001", "2024-07-03", "1", "A001", "Artikel 1", "10", "Verfügbar"},
                {"2", "1002", "2002", "2024-07-03", "2", "A002", "Artikel 2", "20", "Nicht verfügbar"}
        };

        // Popup-Fenster anzeigen
        showLieferscheinPopup(data);
    }

    private void showLieferscheinPopup(String[][] data) {
        // Popup-Fenster erstellen
        JDialog dialog = new JDialog(this, "Lieferschein", true);
        dialog.setSize(600, 400);
        dialog.setLayout(new BorderLayout());

        // Textbereich erstellen
        JTextArea textArea = new JTextArea();
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        textArea.setEditable(false);

        // Daten in Textbereich einfügen
        StringBuilder sb = new StringBuilder();
        sb.append("Lieferung Details\n\n");
        String[] headers = {"Lieferung ID", "Rechnungs ID", "Auftrags ID", "Lieferungsdatum", "Lieferposition", "Artikelnummer", "Artikelname", "Menge", "Verfügbarkeit"};
        for (String header : headers) {
            sb.append(header).append("\t");
        }
        sb.append("\n");

        for (String[] row : data) {
            for (String cell : row) {
                sb.append(cell).append("\t");
            }
            sb.append("\n");
        }

        textArea.setText(sb.toString());

        // Textbereich in ScrollPane einfügen und zum Dialog hinzufügen
        JScrollPane scrollPane = new JScrollPane(textArea);
        dialog.add(scrollPane, BorderLayout.CENTER);

        // Dialog sichtbar machen
        dialog.setVisible(true);
    }

    private void buttonSpeichernActionPerformed(java.awt.event.ActionEvent evt) {
        Lieferung lieferung = new Lieferung();

        String lieferID = getValidatedLieferID();
        if (lieferID == null) return;
        lieferung.setLieferungNr(lieferID);

        Auftrag auftrag = getValidatedAuftrag();
        if (auftrag == null) return;
        lieferung.setAuftrag(auftrag);

        Rechnung rechnung = getValidatedRechnung();
        if (rechnung == null) return;
        lieferung.setRechnung(rechnung);

        if (!setAndValidateLieferdatum(auftrag)) return;

        lieferungRepository.insert(lieferung);

        if (!validateAndInsertLieferpositionen(lieferung)) return;

        JOptionPane.showMessageDialog(this, "Die Lieferung wurde erfolgreich gespeichert.", "Erfolg", JOptionPane.INFORMATION_MESSAGE);

        // Maske zurücksetzen

        // Methoden und Hilfsfunktionen
        // TODO: existierenden Lieferung anpassen -> Bearbeitungsmodus

        // TODO: Lieferschein erzeugen
    }
    private String getValidatedLieferID() {
        String lieferID = textfieldLieferungNr.getText().trim();
        if (lieferID.isEmpty()) {
            showErrorMessage("Bitte geben Sie eine Lieferungs-ID an.");
            return null;
        }
        if (existLieferung(lieferID)) {
            showErrorMessage("Die Lieferungs-ID existiert bereits. Bitte wählen Sie eine andere ID.");
            return null;
        }
        return lieferID;
    }

    private Auftrag getValidatedAuftrag() {
        try {
            Auftrag auftrag = fetchAuftrag(selectAuftrag.getSelectedItem().toString());
            if (auftrag == null) {
                showErrorMessage("Auftrag nicht gefunden.");
            }
            return auftrag;
        } catch (ReflectiveOperationException | SQLException e) {
            handleException(e);
            return null;
        }
    }

    private Rechnung getValidatedRechnung() {
        try {
            Rechnung rechnung = fetchRechnung(selectRechnung.getSelectedItem().toString());
            if (rechnung == null) {
                showErrorMessage("Rechnung nicht gefunden.");
            }
            return rechnung;
        } catch (ReflectiveOperationException | SQLException e) {
            handleException(e);
            return null;
        }
    }

    private boolean setAndValidateLieferdatum(Auftrag auftrag) {
        String lieferdatumText = textboxLieferdatum.getText().trim();
        if (lieferdatumText.isEmpty()) {
            showErrorMessage("Bitte geben Sie das Lieferdatum an.");
            return false;
        }
        try {
            LocalDate lieferdatum = LocalDate.parse(lieferdatumText, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
            auftrag.setLieferdatum(lieferdatum.atStartOfDay());
            auftragRespository.update(auftrag);
            return true;
        } catch (DateTimeParseException e) {
            showErrorMessage("Das Lieferdatum ist fehlerhaft. Geben Sie das Datum im Format dd.MM.yyyy an.");
            return false;
        }
    }

    private boolean validateAndInsertLieferpositionen(Lieferung lieferung) {
        DefaultTableModel defaultTableModel = (DefaultTableModel) tablePositionen.getModel();
        for (int row = 0; row < defaultTableModel.getRowCount(); row++) {
            Lieferungsposition lieferungsposition = new Lieferungsposition();
            try {
                Material material = fetchMaterial((String) defaultTableModel.getValueAt(row, 1));
                lieferungsposition.setArtikel_nr(material);
            } catch (ReflectiveOperationException | SQLException e) {
                handleException(e);
                return false;
            }

            int menge = (int) defaultTableModel.getValueAt(row, 3);
            lieferungsposition.setMenge(menge);
            lieferungsposition.setLieferung_nr(lieferung);

            String lieferungspositionNr = generateLieferungspositionNr();
            lieferungsposition.setLieferungsposition_nr(lieferungspositionNr);

            lieferungspositionRepository.insert(lieferungsposition);
        }
        return true;
    }

    private String generateLieferungspositionNr() {
        try {
            List<Lieferungsposition> lieferungspositionen = lieferungspositionRepository.findAll().execute();
            if (!lieferungspositionen.isEmpty()) {
                Lieferungsposition lastLieferungposition = lieferungspositionen.get(lieferungspositionen.size() - 1);
                String lastLieferungspositionNr = lastLieferungposition.getLieferungsposition_nr();
                String prefix = lastLieferungspositionNr.substring(0, 3); // "LP-"
                int number = Integer.parseInt(lastLieferungspositionNr.substring(3));
                return prefix + (number + 1);
            }
        } catch (ReflectiveOperationException | SQLException e) {
            handleException(e);
        }
        return "LP-0";
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
    private javax.swing.JButton buttonLieferschein;
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
    private javax.swing.JTextField     textfieldLieferungNr;
    // End of variables declaration
}
