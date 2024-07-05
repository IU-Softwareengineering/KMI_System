/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.iu.kmi.layout;

import com.iu.kmi.database.repository.RepositoryProxy;
import com.iu.kmi.entities.*;
import com.iu.kmi.layout.models.*;
import com.iu.kmi.repositories.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.List;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author testg
 */
public class AngebotInterface extends javax.swing.JFrame {

    private static final String DATE_PATTERN = "\\d{2}\\.\\d{2}\\.\\d{4}";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private AngebotRepository angebotRepository;
    private MaterialRepository materialRepository;
    private KundeRepository kundeRepository;
    private KundenanfrageRepository kundenanfrageRepository;
    private KonditionRepository konditionRepository;
    private List<AngebotsPosition> positions;
    private Kundenanfrage kundenAnfrage;
    private AngebotsPositionRepository angebotsPositionRepository;
    private AnfragePositionRepository anfragePositionRepository;
    private ExecutorService executor;
    private List<Angebot> angebote;

    /**
     * Creates new form AngebotInterface
     */
    public AngebotInterface() throws ReflectiveOperationException, SQLException {
        initComponents();
        this.initRepository();
        this.fillDate();
        this.initTable();

        executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        try {
            this.initRepository();
            this.loadDataAsync();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }

    private void initRepository() {
        this.angebotRepository = RepositoryProxy.newInstance(AngebotRepository.class);
        this.materialRepository = RepositoryProxy.newInstance(MaterialRepository.class);
        this.kundeRepository = RepositoryProxy.newInstance(KundeRepository.class);
        this.kundenanfrageRepository = RepositoryProxy.newInstance(KundenanfrageRepository.class);
        this.konditionRepository = RepositoryProxy.newInstance(KonditionRepository.class);
        this.anfragePositionRepository = RepositoryProxy.newInstance(AnfragePositionRepository.class);
        this.angebotsPositionRepository = RepositoryProxy.newInstance(AngebotsPositionRepository.class);
    }

    private void loadDataAsync() throws ExecutionException, InterruptedException, ReflectiveOperationException, SQLException {
        CompletableFuture<Void> fillKundenFuture = CompletableFuture.runAsync(() -> {
            try {
                fillKundenSelect();
            } catch (ReflectiveOperationException | SQLException e) {
                e.printStackTrace();
            }
        }, executor);

        CompletableFuture<Void> fillStatusFuture = CompletableFuture.runAsync(this::fillStatusSelect, executor);

        CompletableFuture<Void> fillAngebotFuture = CompletableFuture.runAsync(() -> {
            try {
                fillAngebotSelect();
            } catch (ReflectiveOperationException | SQLException e) {
                e.printStackTrace();
            }
        }, executor);

        CompletableFuture<Void> fillKonditionenFuture = CompletableFuture.runAsync(() -> {
            try {
                fillKonditionenSelect();
            } catch (ReflectiveOperationException | SQLException e) {
                e.printStackTrace();
            }
        }, executor);


        CompletableFuture<Void> allTasks = CompletableFuture.allOf(
                fillKundenFuture, fillStatusFuture, fillAngebotFuture,
                fillKonditionenFuture
        );

        allTasks.get();
    }

    private void fillStatusSelect(){
        select_status.removeAllItems();
        select_status.addItem(new AngebotStatusModel("", ""));
        select_status.addItem(new AngebotStatusModel("Offen", "Offen"));
        select_status.addItem(new AngebotStatusModel("Akzeptiert", "Akzeptiert"));
        select_status.addItem(new AngebotStatusModel("Abgelehnt", "Abgelehnt"));
    }

    private void fillAngebotSelect() throws ReflectiveOperationException, SQLException {
        select_angebot.removeAllItems();
        select_angebot.addItem(new AngebotModel("", ""));
        this.angebote = angebotRepository.findAll().execute();
        angebote.forEach(angebot -> select_angebot.addItem(new AngebotModel(angebot.getAngebotNr(), angebot)));
    }

    private void fillKundenSelect() throws ReflectiveOperationException, SQLException {
        List<Kunde> kunden = kundeRepository.findAll().execute();
        kunden.forEach(kunde -> select_kunde.addItem(new AngebotKundenModel(kunde.getVorname() + " " + kunde.getName(), kunde)));
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
        String sql = "SELECT ap.anfrageposition_nr,ap.artikel_nr,ap.kundenanfrage_nr,ap.menge FROM anfrageposition ap JOIN kundenanfrage ka ON ap.kundenanfrage_nr = ka.kundenanfrage_nr WHERE ka.kundenanfrage_nr = ?;";
        List<AnfragePosition> anfragePositions = anfragePositionRepository.executeCustomQueryList(sql, this.kundenAnfrage.getKundenanfrageNr());

        AngebotPositionTableModel model = (AngebotPositionTableModel) table_positionen.getModel();
        anfragePositions.forEach(model::addFromAnfragePosition);
    }

    private void loadAngebotsPositionenByAngebot(String angebotNr) throws ReflectiveOperationException, SQLException {
        List<AngebotsPosition> positions = angebotsPositionRepository.findAll().where("angebotNr", angebotNr).execute();
        AngebotPositionTableModel model = (AngebotPositionTableModel) table_positionen.getModel();
        AngebotKonditionModel konditionModel = (AngebotKonditionModel) select_kondition.getModel().getSelectedItem();
        if (konditionModel != null) {
            model.setKondition(konditionModel.value);
        }

        positions.forEach(model::addPosition);
    }

    private void loadAngebot(Angebot angebot) throws ReflectiveOperationException, SQLException {
        this.kundenAnfrage = null;
        textbox_angebotsid.setText(angebot.getAngebotNr());
        textbox_angebotsdatum.setText(angebot.getAngebotsdatum().format(DATE_FORMATTER));
        textbox_gueltig.setText(angebot.getGueltigBis().format(DATE_FORMATTER));
        select_kondition.setSelectedItem(new AngebotKonditionModel(angebot.getKonditionNr(), false));

        for (int i = 1; i < select_kondition.getItemCount(); i++) {
            AngebotKonditionModel item = select_kondition.getItemAt(i);
            if (item.value.getKonditionNr().equals(angebot.getKonditionNr().getKonditionNr())) {
                select_kondition.setSelectedItem(item);
                break;
            }
        }

        for (int i = 1; i < select_status.getItemCount(); i++) {
            AngebotStatusModel item = select_status.getItemAt(i);
            if (item.value.equals(angebot.getStatus())) {
                select_status.setSelectedItem(item);
                break;
            }
        }

        for (int i = 0; i < select_kunde.getItemCount(); i++) {
            AngebotKundenModel item = select_kunde.getItemAt(i);
            if (item.value.getName().equals(angebot.getKundeNr().getName())) {
                select_kunde.setSelectedItem(item);
                break;
            }
        }

        AngebotPositionTableModel tableModel = (AngebotPositionTableModel) table_positionen.getModel();
        tableModel.clearPositions();
        this.loadAngebotsPositionenByAngebot(angebot.getAngebotNr());
    }

    private void handlePositionsSave(Angebot angebot) {
        AngebotPositionTableModel model = ((AngebotPositionTableModel)table_positionen.getModel());
        List<PositionTableRow> rows = model.getPositions();

        Integer rowIndex = 0;
        for (PositionTableRow row : rows) {
            AngebotsPosition position = row.getPosition();

            switch (row.getState()) {
                case Created -> {
                    System.out.println("handling created " + position.getAngebotspositionNr());
                    if (position.getAngebotspositionNr() == null || position.getAngebotspositionNr().isEmpty()){
                        JOptionPane.showMessageDialog(this, "Angebotspositions-Nummer fehlt bei Position " + rowIndex, "Fehler", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    Angebot positionAngebot = position.getAngebotNr();
                    if (positionAngebot == null) {
                        position.setAngebotNr(angebot);
                    }

                    Material material = position.getArtikelNr();
                    if (material == null) {
                        JOptionPane.showMessageDialog(this, "Material fehlt bei Position " + rowIndex, "Fehler", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    if (position.getMenge() == 0)  {
                        JOptionPane.showMessageDialog(this, "Die Menge des Materials ist 0 bei Position " + rowIndex, "Fehler", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    // inserting position
                    angebotsPositionRepository.insert(position);
                }
                case Modified -> {
                    System.out.println("handling modified " + position.getAngebotspositionNr());
                    angebotsPositionRepository.update(position);

                }
                case Deleted -> angebotsPositionRepository.delete(position.getAngebotspositionNr());
            }
            rowIndex++;
        };
    }

    private void initTable() {
        AngebotPositionTableModel angebotPositionTableModel = new AngebotPositionTableModel(this.materialRepository);
        table_positionen.setModel(angebotPositionTableModel);
    }

    private void fillDate(){
        textbox_angebotsdatum.setText(LocalDate.now().format(DATE_FORMATTER));
    }
    
    private void fillAngebotspositionen() {
        this.loadAngebotsPositionen();
    }

    public void kundenAnfrageSuchen(String name){
        if(name.isEmpty()){
            JOptionPane.showMessageDialog(this, "Bitte geben Sie einen Namen ein oder wählen sie einen Kunden aus.", "Fehler", JOptionPane.ERROR_MESSAGE);
            return;
        }
        this.kundenAnfrage = kundenanfrageRepository.findByKunde_name(name);
        if (kundenAnfrage == null) {
            JOptionPane.showMessageDialog(this, "Für diesen Kunden existiert keine Kundenanfrage.", "Fehler", JOptionPane.ERROR_MESSAGE);
            return;
        }
        select_angebot.setSelectedItem(select_angebot.getItemAt(0));
        textbox_angebotsid.setText("");
        textbox_gueltig.setText("");
        select_kondition.setSelectedItem(select_kondition.getItemAt(0));
        select_status.setSelectedItem(select_status.getItemAt(0));
        Kunde anfrageKunde = kundenAnfrage.getKunde();
        AngebotPositionTableModel tableModel = (AngebotPositionTableModel) table_positionen.getModel();
        tableModel.clearPositions();
        this.fillAngebotspositionen();

        for (int i = 0; i < select_kunde.getItemCount(); i++) {
            AngebotKundenModel item = select_kunde.getItemAt(i);
            if (item.value.getName().equals(anfrageKunde.getName())) {
                select_kunde.setSelectedItem(item);
                break;
            }
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
        select_status = new javax.swing.JComboBox<>();
        select_kondition = new javax.swing.JComboBox<>();
        select_kunde = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        textbox_anfrage = new javax.swing.JTextField();
        button_abbrechen = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_positionen = new javax.swing.JTable();
        angebotsposition_erstellen_button = new javax.swing.JButton();
        angebotsposition_loeschen_button = new javax.swing.JButton();
        select_angebot = new javax.swing.JComboBox<>();
        button_angebotladen = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(11, 0), new java.awt.Dimension(11, 0), new java.awt.Dimension(11, 32767));
        button_kond = new javax.swing.JButton();

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

        button_angebotladen.setText("Angebot laden");
        button_angebotladen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_angebotladenActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(51, 51, 51));
        jLabel10.setText("Angebot");

        button_kond.setText("Konditionen");
        button_kond.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_kondActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(427, 427, 427))
            .addGroup(layout.createSequentialGroup()
                .addGap(383, 383, 383)
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(angebotsposition_erstellen_button)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(angebotsposition_loeschen_button))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(127, 127, 127)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(textbox_anfrage, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(select_angebot, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(button_angebotladen, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(textbox_gueltig, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(206, 206, 206)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(textbox_angebotsdatum, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(select_kunde, javax.swing.GroupLayout.Alignment.LEADING, 0, 140, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(textbox_angebotsid, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(button_kundesuchen, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(select_kondition, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(select_status, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(127, 127, 127)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(button_speichern, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(button_abbrechen, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane1))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(button_kond)
                .addGap(39, 39, 39))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(select_angebot, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button_angebotladen, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(button_kundesuchen, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(textbox_anfrage))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(select_kunde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textbox_angebotsid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textbox_angebotsdatum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(select_kondition, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button_kond))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textbox_gueltig, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(select_status, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(angebotsposition_loeschen_button)
                    .addComponent(angebotsposition_erstellen_button))
                .addGap(106, 106, 106)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(button_speichern, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button_abbrechen, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12))
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

    private void button_kundesuchenActionPerformed(java.awt.event.ActionEvent evt) {
        String searchName = null;
        if (textbox_anfrage.getText() != null && !textbox_anfrage.getText().isEmpty()) {
            searchName = textbox_anfrage.getText();
        } else if (select_kunde.getSelectedItem() != null) {
            searchName = ((AngebotKundenModel) select_kunde.getSelectedItem()).value.getName();
        } else {
            JOptionPane.showMessageDialog(this, "Bitte geben Sie einen Namen ein oder wählen Sie einen Kunden aus.", "Fehler", JOptionPane.ERROR_MESSAGE);
            return;
        }
       this.kundenAnfrageSuchen(searchName);

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

        if (this.kundenAnfrage == null && ((AngebotKundenModel) select_kunde.getSelectedItem()).value == null) {
            JOptionPane.showMessageDialog(this, "Bitte geben Sie 'Kunde' an", "Fehler", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if(this.kundenAnfrage != null){
            angebot.setKundeNr(this.kundenAnfrage.getKunde());
        } else {
            angebot.setKundeNr(((AngebotKundenModel) select_kunde.getSelectedItem()).value);
        }

        String gueltigBisText = textbox_gueltig.getText();
        LocalDateTime gueltigBisDateTime = null;

        try {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
            gueltigBisDateTime = LocalDateTime.parse(gueltigBisText, dateTimeFormatter);
        } catch (DateTimeParseException e) {
            try {
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                LocalDate gueltigBisDate = LocalDate.parse(gueltigBisText, dateFormatter);
                gueltigBisDateTime = gueltigBisDate.atStartOfDay();
            } catch (DateTimeParseException e2) {
                // If both parsing attempts fail, show an error message
                e2.printStackTrace();
                JOptionPane.showMessageDialog(this, "'Gültig bis' ist fehlerhaft", "Fehler", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        if (gueltigBisDateTime != null) {
            angebot.setGueltigBis(gueltigBisDateTime);
        }


        String angebotsDatumText = textbox_angebotsdatum.getText();
        if (angebotsDatumText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Bitte geben Sie 'Angebotsdatum' an", "Fehler", JOptionPane.ERROR_MESSAGE);
            return;
        }

        LocalDateTime angebotsDatum = null;

        try {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
            angebotsDatum = LocalDateTime.parse(angebotsDatumText, dateTimeFormatter);
        } catch (DateTimeParseException e) {
            try {
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                LocalDate angebotsDatumDate = LocalDate.parse(angebotsDatumText, dateFormatter);
                angebotsDatum = angebotsDatumDate.atStartOfDay();
            } catch (DateTimeParseException e2) {
                e2.printStackTrace();
                JOptionPane.showMessageDialog(this, "'Angebotsdatum' ist fehlerhaft", "Fehler", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        if (angebotsDatum != null) {
            angebot.setAngebotsdatum(angebotsDatum);
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

        angebot.setWaehrung("EUR");
        System.out.println(angebot);
        Optional<Angebot> tempAngebot = this.angebote.stream().filter(a -> a.getAngebotNr().equals(angebot.getAngebotNr())).findFirst();

        try {
            this.handlePositionsSave(angebot);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Fehler beim Speichern der Angebotspositionen", "Fehler", JOptionPane.ERROR_MESSAGE);
        }

        if(tempAngebot.isPresent()){
            System.out.println("UPDATE");
            this.angebotRepository.update(angebot);
        } else {
            System.out.println("INSERT");
            this.angebotRepository.insert(angebot);
        }
    }//GEN-LAST:event_button_speichernActionPerformed

    private void textbox_angebotsidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textbox_angebotsidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textbox_angebotsidActionPerformed

    private void select_statusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_select_statusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_select_statusActionPerformed

    private void select_konditionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_select_konditionActionPerformed
        AngebotKonditionModel selection = (AngebotKonditionModel) select_kondition.getSelectedItem();
        AngebotPositionTableModel tableModel = (AngebotPositionTableModel) table_positionen.getModel();
        if (selection != null) {
            tableModel.setKondition(selection.value);
        }
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
        AngebotPositionTableModel tableModel = (AngebotPositionTableModel) table_positionen.getModel();
        tableModel.addPosition();

    }//GEN-LAST:event_angebotsposition_erstellen_buttonActionPerformed

    private void angebotsposition_loeschen_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_angebotsposition_loeschen_buttonActionPerformed
        int selectedPosition = this.table_positionen.getSelectedRow();
        if (selectedPosition == -1) {
            return;
        }
        AngebotPositionTableModel tableModel = (AngebotPositionTableModel) table_positionen.getModel();
        tableModel.deletePositionByRow(selectedPosition);
    }//GEN-LAST:event_angebotsposition_loeschen_buttonActionPerformed

    private void button_angebotladenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_angebotladenActionPerformed
        AngebotModel selectedAngebot = (AngebotModel) select_angebot.getSelectedItem();
        if (selectedAngebot == null) {
            JOptionPane.showMessageDialog(this, "Bitte wählen Sie ein Angebot aus", "Fehler", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Angebot angebot = selectedAngebot.value;
        try {
            this.loadAngebot(angebot);
        } catch (ReflectiveOperationException | SQLException e) {
            throw new RuntimeException(e);
        }
    }//GEN-LAST:event_button_angebotladenActionPerformed

    private void button_kondActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_kondActionPerformed
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    displayKonditionPopup();
                } catch (ReflectiveOperationException e) {
                    throw new RuntimeException(e);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }//GEN-LAST:event_button_kondActionPerformed

    public void onKonditionPopupClosed() {
        try {
            this.fillKonditionenSelect();
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void displayKonditionPopup() throws ReflectiveOperationException, SQLException {
        AngebotKonditionPopup angebotKonditionPopup = new AngebotKonditionPopup(this);
        angebotKonditionPopup.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        angebotKonditionPopup.pack();
        angebotKonditionPopup.setVisible(true);
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
    private javax.swing.JButton button_angebotladen;
    private javax.swing.JButton button_kond;
    private javax.swing.JButton button_kundesuchen;
    private javax.swing.JButton button_speichern;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<AngebotModel> select_angebot;
    private javax.swing.JComboBox<AngebotKonditionModel> select_kondition;
    private javax.swing.JComboBox<AngebotKundenModel> select_kunde;
    private javax.swing.JComboBox<AngebotStatusModel> select_status;
    private javax.swing.JTable table_positionen;
    private javax.swing.JTextField textbox_anfrage;
    private javax.swing.JTextField textbox_angebotsdatum;
    private javax.swing.JTextField textbox_angebotsid;
    private javax.swing.JTextField textbox_gueltig;
    // End of variables declaration//GEN-END:variables
}
