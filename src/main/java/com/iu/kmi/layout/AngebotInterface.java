/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.iu.kmi.layout;

import com.iu.kmi.database.repository.RepositoryProxy;
import com.iu.kmi.entities.*;
import com.iu.kmi.layout.models.AngebotKonditionModel;
import com.iu.kmi.layout.models.AngebotStatusModel;
import com.iu.kmi.layout.models.PositionTableModel;
import com.iu.kmi.repositories.*;

import javax.sound.midi.SysexMessage;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

/**
 *
 * @author testg
 */
public class AngebotInterface extends javax.swing.JFrame {

    private static final String DATE_PATTERN = "\\d{2}\\.\\d{2}\\.\\d{4}";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private AngebotRepository angebotRepository;
    private KundeRepository kundeRepository;
    private KundenanfrageRepository kundenanfrageRepository;
    private KonditionRepository konditionRepository;
    private PositionTableModel[] positionTableData;
    private Kundenanfrage kundenAnfrage;
    private AngebotsPositionRepository angebotsPositionRepository;
    private AnfragePositionRepository anfragePositionRepository;

    /**
     * Creates new form AngebotInterface
     */
    public AngebotInterface() throws ReflectiveOperationException, SQLException {
        initComponents();
        this.initRepository();
        this.fillKundenSelect();
        this.fillKonditionenSelect();
        this.fillDate();
    }

    private void initRepository() {
        this.angebotRepository = RepositoryProxy.newInstance(AngebotRepository.class);
        this.kundeRepository = RepositoryProxy.newInstance(KundeRepository.class);
        this.kundenanfrageRepository = RepositoryProxy.newInstance(KundenanfrageRepository.class);
        this.konditionRepository = RepositoryProxy.newInstance(KonditionRepository.class);
        this.anfragePositionRepository = RepositoryProxy.newInstance(AnfragePositionRepository.class);
    }

    private void fillKundenSelect() throws ReflectiveOperationException, SQLException {
        List<Kunde> kunden = kundeRepository.findAll().execute();
        kunden.forEach(kunde -> select_kunde.addItem(kunde.getVorname() + " " + kunde.getName()));
    }

    private void fillKonditionenSelect() throws ReflectiveOperationException, SQLException {
        select_kondition.removeAllItems();
        select_kondition.addItem(new AngebotKonditionModel(null, true));
        List<Kondition> konditionen = konditionRepository.findAll().execute();
        konditionen.forEach(kondition -> {
            select_kondition.addItem(new AngebotKonditionModel(kondition, false));
        });
    }

    private void loadAngebotsPositionen(){
        String sql = "SELECT ap.anfrageposition_nr,ap.artikel_nr,ap.kundenanfrage_nr FROM anfrageposition ap JOIN kundenanfrage ka ON ap.kundenanfrage_nr = ka.kundenanfrage_nr WHERE ka.kundenanfrage_nr = ?;";
        List<AnfragePosition> anfragePositions = anfragePositionRepository.executeCustomQueryList(sql, this.kundenAnfrage.getKundenanfrageNr());

        Map<String, List<AnfragePosition>> grouped = groupAnfragePositionsByArtikelNr(anfragePositions);
        this.positionTableData = new PositionTableModel[grouped.size()];
        int i = 0;
        for (Map.Entry<String, List<AnfragePosition>> entry : grouped.entrySet()) {
            this.positionTableData[i] = PositionTableModel.convert(entry.getValue());
            i++;
        }



    }

    public static Map<String, List<AnfragePosition>> groupAnfragePositionsByArtikelNr(List<AnfragePosition> anfragePositions) {
        Map<String, List<AnfragePosition>> groupedAnfragePositions = new HashMap<>();

        for (AnfragePosition ap : anfragePositions) {
            String artikelNr = ap.getArtikelNr().getArtikelNr();
            if (!groupedAnfragePositions.containsKey(artikelNr)) {
                groupedAnfragePositions.put(artikelNr, new ArrayList<>());
            }
            groupedAnfragePositions.get(artikelNr).add(ap);
        }

        return groupedAnfragePositions;
    }

    private void fillDate(){
        textbox_angebotsdatum.setText(LocalDate.now().format(DATE_FORMATTER));
    }
    
    private void fillAngebotspositionen() {
        this.loadAngebotsPositionen();
        DefaultTableModel model = new DefaultTableModel(
                new Object[][]{},
                new String[]{"Nummer", "Name", "Einzelpreis", "Menge", "Gesamtpreis"}
        );

        table_positionen.setModel(model);

        // Fülle das Modell mit den Daten aus positionTableData
        for (PositionTableModel position : positionTableData) {
            model.addRow(new Object[]{
                    position.nummer,
                    position.name,
                    position.einzelpreis,
                    position.menge,
                    position.gesamtpreis
            });
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

        textbox_angebotsdatum = new javax.swing.JTextField();
        button_kundesuchen = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        textbox_gueltig = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        button_speichern = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        textbox_angebotsid = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        select_status = new javax.swing.JComboBox<AngebotStatusModel>();
        select_kondition = new javax.swing.JComboBox<>();
        select_kunde = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        textbox_anfrage = new javax.swing.JTextField();
        button_abbrechen = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_positionen = new javax.swing.JTable();
        angebotsposition_erstellen_button = new javax.swing.JButton();
        angebotsposition_loeschen_button = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        textbox_angebotsdatum.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        textbox_angebotsdatum.setForeground(new java.awt.Color(51, 51, 51));
        textbox_angebotsdatum.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        textbox_angebotsdatum.setAutoscrolls(false);
        textbox_angebotsdatum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textbox_angebotsdatumActionPerformed(evt);
            }
        });

        button_kundesuchen.setBackground(new java.awt.Color(244, 67, 54));
        button_kundesuchen.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        button_kundesuchen.setForeground(new java.awt.Color(255, 255, 255));
        button_kundesuchen.setText("Kunde Suchen");
        button_kundesuchen.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        button_kundesuchen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_kundesuchenActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(51, 51, 51));
        jLabel5.setText("Gültig bis");

        textbox_gueltig.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        textbox_gueltig.setForeground(new java.awt.Color(51, 51, 51));
        textbox_gueltig.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        textbox_gueltig.setAutoscrolls(false);
        textbox_gueltig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textbox_gueltigActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setText("Anfrage");

        jLabel1.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Angebot");

        jLabel6.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 51, 51));
        jLabel6.setText("Status");

        button_speichern.setBackground(new java.awt.Color(58, 132, 233));
        button_speichern.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        button_speichern.setForeground(new java.awt.Color(255, 255, 255));
        button_speichern.setText("Speichern");
        button_speichern.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_speichernActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 51, 51));
        jLabel7.setText("Angebots-ID");

        jLabel4.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setText("Angebotsdatum");

        textbox_angebotsid.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        textbox_angebotsid.setForeground(new java.awt.Color(51, 51, 51));
        textbox_angebotsid.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        textbox_angebotsid.setAutoscrolls(false);
        textbox_angebotsid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textbox_angebotsidActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(51, 51, 51));
        jLabel8.setText("Kondition");

        select_status.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        select_status.setForeground(new java.awt.Color(51, 51, 51));
        AngebotStatusModel[] statusArray = new AngebotStatusModel[]{
                new AngebotStatusModel("test", "test")
        };

        // Create a JComboBox and set its model
        // JComboBox<AngebotStatusModel> selectStatus = new JComboBox<>();
        select_status.setModel(new DefaultComboBoxModel<>(statusArray));

        select_status.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        select_status.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                select_statusActionPerformed(evt);
            }
        });

        select_kondition.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        select_kondition.setForeground(new java.awt.Color(51, 51, 51));
        select_kondition.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                select_konditionActionPerformed(evt);
            }
        });

        select_kunde.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                try {
                    select_kundeComponentShown(evt);
                } catch (ReflectiveOperationException e) {
                    throw new RuntimeException(e);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        select_kunde.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                select_kundeActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(51, 51, 51));
        jLabel9.setText("Kunde");

        textbox_anfrage.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        textbox_anfrage.setForeground(new java.awt.Color(51, 51, 51));
        textbox_anfrage.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        textbox_anfrage.setAutoscrolls(false);
        textbox_anfrage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textbox_anfrageActionPerformed(evt);
            }
        });

        button_abbrechen.setBackground(new java.awt.Color(244, 67, 54));
        button_abbrechen.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        button_abbrechen.setForeground(new java.awt.Color(255, 255, 255));
        button_abbrechen.setText("Abbrechen");
        button_abbrechen.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        button_abbrechen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_abbrechenActionPerformed(evt);
            }
        });

        table_positionen.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Nummer", "Name", "Einzelpreis", "Menge", "Gesamtpreis"
            }
        ));
        jScrollPane1.setViewportView(table_positionen);

        angebotsposition_erstellen_button.setText("Erstellen");
        angebotsposition_erstellen_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                angebotsposition_erstellen_buttonActionPerformed(evt);
            }
        });

        angebotsposition_loeschen_button.setText("Löschen");
        angebotsposition_loeschen_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                angebotsposition_loeschen_buttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel4))
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(textbox_gueltig, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(select_kondition, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(select_status, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(textbox_angebotsdatum)
                                    .addComponent(select_kunde, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(textbox_angebotsid, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(14, 14, 14))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(textbox_anfrage)
                        .addGap(18, 18, 18)
                        .addComponent(button_kundesuchen, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(309, 309, 309))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(button_speichern, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(242, 242, 242)
                .addComponent(button_abbrechen, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(400, 400, 400))
            .addGroup(layout.createSequentialGroup()
                .addGap(134, 134, 134)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(angebotsposition_loeschen_button)
                    .addComponent(angebotsposition_erstellen_button))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(338, 338, 338)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textbox_anfrage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(button_kundesuchen, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(select_kunde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textbox_angebotsdatum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textbox_gueltig, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(textbox_angebotsid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(select_kondition, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(select_status, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(angebotsposition_erstellen_button)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(angebotsposition_loeschen_button)
                        .addGap(92, 92, 92)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(button_speichern, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button_abbrechen, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(61, 61, 61))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void textbox_angebotsdatumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textbox_angebotsdatumActionPerformed
        // Prozess - Datumsvalidierung
        String dateString = textbox_angebotsdatum.getText().trim();

        // Überprüfen, ob die Eingabe dem erwarteten Datumsformat entspricht
        if (!dateString.matches(DATE_PATTERN)) {
            JOptionPane.showMessageDialog(this, "Das Datum entspricht nicht dem erwarteten Format.", "Fehler", JOptionPane.ERROR_MESSAGE);
            return; // Beende die Methode, da das Datum ungültig ist
        }

        // Versuchen, das Datum zu parsen
        try {
            LocalDate.parse(dateString, DATE_FORMATTER);
            // Wenn das Parsen erfolgreich ist, kannst du hier weiterverarbeiten
        }
        catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(this, "Ungültiges Datum.", "Fehler", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_textbox_angebotsdatumActionPerformed

    private void button_kundesuchenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_kundesuchenActionPerformed
       this.kundenSuchen(textbox_anfrage.getText());

    }//GEN-LAST:event_button_kundesuchenActionPerformed

    public void kundenSuchen(String name){
        try {
            this.kundenAnfrage = kundenanfrageRepository.findByKunde_name(name);

        } catch (Exception e) {
            return;
        }
        if (kundenAnfrage == null) {
            JOptionPane.showMessageDialog(this, "Kunde nicht gefunden.", "Fehler", JOptionPane.ERROR_MESSAGE);
            return;
        }
        select_kunde.removeAllItems();
        Kunde anfrageKunde = kundenAnfrage.getKunde();
        this.fillAngebotspositionen();


        select_kunde.addItem(anfrageKunde.getVorname() + " " + anfrageKunde.getName());

    }

    private void textbox_gueltigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textbox_gueltigActionPerformed
        // Prozess - Datumsvalidierung mit aktueller Zeit
        String dateString = textbox_gueltig.getText().trim();
        LocalDate today = LocalDate.now();

        // Überprüfen des Datumsformats
        if (!dateString.matches(DATE_PATTERN)) {
            JOptionPane.showMessageDialog(this, "Das Datum entspricht nicht dem erwarteten Format (TT.MM.JJJJ).", "Fehler", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Versuchen, das Datum zu parsen
        try {
            LocalDate parsedDate = LocalDate.parse(dateString, DATE_FORMATTER);

            // Überprüfen, ob das Datum in der Vergangenheit liegt oder das heutige Datum ist
            if (parsedDate.isBefore(today) || parsedDate.isEqual(today)) {
                JOptionPane.showMessageDialog(this, "Das Datum darf nicht in der Vergangenheit liegen oder heute sein.", "Fehler", JOptionPane.ERROR_MESSAGE);
                return; // Beende die Methode, da das Datum ungültig ist
            }

            // TODO Weiter handeln

        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(this, "Das Datum entspricht nicht dem erwarteten Format (TT.MM.JJJJ).", "Fehler", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_textbox_gueltigActionPerformed

    private void button_speichernActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_speichernActionPerformed
        Angebot angebot = new Angebot();

        String angebotID = textbox_angebotsid.getText();
        if (angebotID.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Bitte geben Sie 'Angebots-ID' an", "Fehler", JOptionPane.ERROR_MESSAGE);
            return;
        }
        angebot.setAngebotNr(angebotID);

        if (this.kundenAnfrage == null) {
            JOptionPane.showMessageDialog(this, "Bitte geben Sie 'Kunde' an", "Fehler", JOptionPane.ERROR_MESSAGE);
            return;
        }
        angebot.setKundeNr(kundenAnfrage.getKunde());

        DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String gueltigBisText = textbox_gueltig.getText();
        if (gueltigBisText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Bitte geben Sie 'Gültig bis' an", "Fehler", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            LocalDate gueltigBis = LocalDate.parse(gueltigBisText, dtFormatter);
            angebot.setGueltigBis(gueltigBis);
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(this, "'Gültig bis' ist fehlerhaft", "Fehler", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String angebotsDatumText = textbox_angebotsdatum.getText();
        if (angebotsDatumText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Bitte geben Sie 'Angebotsdatum' an", "Fehler", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            LocalDate angebotsDatum = LocalDate.parse(angebotsDatumText, dtFormatter);
            angebot.setAngebotsdatum(angebotsDatum);
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(this, "'Angebotsdatum' ist fehlerhaft", "Fehler", JOptionPane.ERROR_MESSAGE);
            return;
        }

        AngebotKonditionModel kondition = (AngebotKonditionModel) select_kondition.getSelectedItem();
        if (kondition == null || kondition.isPlaceholder()) {
            JOptionPane.showMessageDialog(this, "Bitte geben Sie 'Kondition' an", "Fehler", JOptionPane.ERROR_MESSAGE);
            return;
        }
        angebot.setKonditionNr(kondition.value);

        AngebotStatusModel status = (AngebotStatusModel) select_status.getSelectedItem();
        if (status == null) {
            JOptionPane.showMessageDialog(this, "Bitte geben Sie 'Status' an", "Fehler", JOptionPane.ERROR_MESSAGE);
            return;
        }
        angebot.setStatus(status.value);

        System.out.println(angebot);
        //this.angebotRepository.insert(angebot);

        // angebotspositionen fetchen, handlen & in db schreiben

    }//GEN-LAST:event_button_speichernActionPerformed

    private void textbox_angebotsidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textbox_angebotsidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textbox_angebotsidActionPerformed

    private void select_statusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_select_statusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_select_statusActionPerformed

    private void select_konditionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_select_konditionActionPerformed
        /*
        AngebotKonditionModel selection = (AngebotKonditionModel) select_kondition.getSelectedItem();
        if (selection != null && !selection.isPlaceholder()) {
            System.out.println("kondition selected " + select_kondition.getSelectedItem().toString());
        }
        */
    }//GEN-LAST:event_select_konditionActionPerformed

    private void textbox_anfrageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textbox_anfrageActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textbox_anfrageActionPerformed

    private void button_abbrechenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_abbrechenActionPerformed
        dispose();
    }//GEN-LAST:event_button_abbrechenActionPerformed

    private void select_kundeComponentShown(java.awt.event.ComponentEvent evt) throws ReflectiveOperationException, SQLException {//GEN-FIRST:event_select_kundeComponentShown


    }//GEN-LAST:event_select_kundeComponentShown

    private void select_kundeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_select_kundeActionPerformed
    }//GEN-LAST:event_select_kundeActionPerformed

    private void angebotsposition_erstellen_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_angebotsposition_erstellen_buttonActionPerformed
        DefaultTableModel model = ((DefaultTableModel)table_positionen.getModel());
        PositionTableModel testModel = new PositionTableModel();
        Object[] t = PositionTableModel.toArray(testModel);
        model.addRow(t);
    }//GEN-LAST:event_angebotsposition_erstellen_buttonActionPerformed

    private void angebotsposition_loeschen_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_angebotsposition_loeschen_buttonActionPerformed
        // TODO add your handling code here:
        int selectedPosition = this.table_positionen.getSelectedRow();
        if (selectedPosition == -1) {
            return;
        }
        DefaultTableModel model = ((DefaultTableModel)table_positionen.getModel());
        model.removeRow(selectedPosition);
    }//GEN-LAST:event_angebotsposition_loeschen_buttonActionPerformed

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
            java.util.logging.Logger.getLogger(AngebotInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AngebotInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AngebotInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AngebotInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new AngebotInterface().setVisible(true);
                } catch (ReflectiveOperationException e) {
                    throw new RuntimeException(e);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton angebotsposition_erstellen_button;
    private javax.swing.JButton angebotsposition_loeschen_button;
    private javax.swing.JButton button_abbrechen;
    private javax.swing.JButton button_kundesuchen;
    private javax.swing.JButton button_speichern;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<AngebotKonditionModel> select_kondition;
    private javax.swing.JComboBox<String> select_kunde;
    private javax.swing.JComboBox<AngebotStatusModel> select_status;
    private javax.swing.JTable table_positionen;
    private javax.swing.JTextField textbox_anfrage;
    private javax.swing.JTextField textbox_angebotsdatum;
    private javax.swing.JTextField textbox_angebotsid;
    private javax.swing.JTextField textbox_gueltig;
    // End of variables declaration//GEN-END:variables
}
