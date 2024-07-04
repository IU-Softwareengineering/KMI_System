USE dev1_db;

-- Testdaten für Tabelle 'adresse'
INSERT INTO `adresse` (`adresse_nr`, `strasse`, `hausnummer`, `postleitzahl`, `stadt`, `land`) VALUES
('A-0', 'Hauptstraße', '1', '12345', 'Musterstadt', 'Deutschland'),
('A-1', 'Nebenstraße', '2', '67890', 'Beispielstadt', 'Deutschland'),
('A-2', 'Bahnhofstraße', '3', '11111', 'Beispieldorf', 'Deutschland'),
('A-3', 'Marktplatz', '4', '22222', 'Musterdorf', 'Deutschland'),
('A-4', 'Ringstraße', '5', '33333', 'Beispielhausen', 'Deutschland');

-- Testdaten für Tabelle 'kunde'
INSERT INTO `kunde` (`kunde_nr`, `name`, `vorname`, `email`, `telefon`, `adresse_nr`) VALUES
('K-0', 'Muster', 'Max', 'max.muster@example.com', '0123456789', 'A-0'),
('K-1', 'Beispiel', 'Erika', 'erika.beispiel@example.com', '9876543210', 'A-1'),
('K-2', 'Mustermann', 'Peter', 'peter.mustermann@example.com', '1234567890', 'A-2'),
('K-3', 'Beispielmann', 'Julia', 'julia.beispielmann@example.com', '0987654321', 'A-3'),
('K-4', 'Test', 'Thomas', 'thomas.test@example.com', '1122334455', 'A-4');

-- Testdaten für Tabelle 'material'
INSERT INTO `material` (`artikel_nr`, `name`, `beschreibung`, `lieferant`, `einkaufspreis`, `verkaufspreis`) VALUES
('M-0', 'Produkt A', 'Beschreibung A', 'Lieferant A', 10.0, 15.0),
('M-1', 'Produkt B', 'Beschreibung B', 'Lieferant B', 20.0, 25.0),
('M-2', 'Produkt C', 'Beschreibung C', 'Lieferant C', 30.0, 35.0),
('M-3', 'Produkt D', 'Beschreibung D', 'Lieferant D', 40.0, 45.0),
('M-4', 'Produkt E', 'Beschreibung E', 'Lieferant E', 50.0, 55.0);

-- Testdaten für Tabelle 'kundenanfrage'
INSERT INTO `kundenanfrage` (`kundenanfrage_nr`, `kunde_nr`, `anfragedatum`, `anfragebeschreibung`, `prioritaet`, `status`) VALUES
('KA-0', 'K-0', '2023-07-01 10:00:00', 'Anfrage für Produkt A', 'Hoch', 'Offen'),
('KA-1', 'K-1', '2023-07-02 11:00:00', 'Anfrage für Produkt B', 'Niedrig', 'In Bearbeitung'),
('KA-2', 'K-2', '2023-07-03 12:00:00', 'Anfrage für Produkt C', 'Mittel', 'Abgeschlossen'),
('KA-3', 'K-3', '2023-07-04 13:00:00', 'Anfrage für Produkt D', 'Hoch', 'Offen'),
('KA-4', 'K-4', '2023-07-05 14:00:00', 'Anfrage für Produkt E', 'Niedrig', 'In Bearbeitung');

-- Testdaten für Tabelle 'anfrageposition'
INSERT INTO `anfrageposition` (`anfrageposition_nr`, `artikel_nr`, `kundenanfrage_nr`, `menge`) VALUES
('AP-0', 'M-0', 'KA-0', 5),
('AP-1', 'M-1', 'KA-1', 3),
('AP-2', 'M-2', 'KA-2', 7),
('AP-3', 'M-3', 'KA-3', 2),
('AP-4', 'M-4', 'KA-4', 10);

-- Testdaten für Tabelle 'kondition'
INSERT INTO `kondition` (`kondition_nr`, `name`, `zahlungsbedingungen`, `lieferbedingungen`, `rabatt`) VALUES
('KON-0', 'Standardkondition', '30 Tage netto', 'Lieferung frei Haus', 0.0),
('KON-1', 'Sonderkondition', '14 Tage 2% Skonto', 'Lieferung ab Werk', 5.0),
('KON-2', 'Rabattkondition', '7 Tage 5% Skonto', 'Selbstabholung', 10.0),
('KON-3', 'Expresskondition', 'Sofortzahlung', 'Expresslieferung', 0.0),
('KON-4', 'Langzeitkondition', '60 Tage netto', 'Lieferung frei Haus', 2.0);

-- Testdaten für Tabelle 'angebot'
INSERT INTO `angebot` (`angebot_nr`, `kunde_nr`, `angebotsdatum`, `gueltig_bis`, `waehrung`, `status`, `kondition_nr`) VALUES
('AN-0', 'K-0', '2023-07-03 12:00:00', '2023-08-03 12:00:00', 'EUR', 'Offen', 'KON-0'),
('AN-1', 'K-1', '2023-07-04 13:00:00', '2023-08-04 13:00:00', 'EUR', 'Akzeptiert', 'KON-1'),
('AN-2', 'K-2', '2023-07-05 14:00:00', '2023-08-05 14:00:00', 'EUR', 'Abgelehnt', 'KON-2'),
('AN-3', 'K-3', '2023-07-06 15:00:00', '2023-08-06 15:00:00', 'EUR', 'Offen', 'KON-3'),
('AN-4', 'K-4', '2023-07-07 16:00:00', '2023-08-07 16:00:00', 'EUR', 'Akzeptiert', 'KON-4');

-- Testdaten für Tabelle 'angebotsposition'
INSERT INTO `angebotsposition` (`angebotsposition_nr`, `artikel_nr`, `angebot_nr`, `menge`) VALUES
('APOS-0', 'M-0', 'AN-0', 10),
('APOS-1', 'M-1', 'AN-1', 5),
('APOS-2', 'M-2', 'AN-2', 8),
('APOS-3', 'M-3', 'AN-3', 4),
('APOS-4', 'M-4', 'AN-4', 6);

-- Testdaten für Tabelle 'terminauftrag'
INSERT INTO `terminauftrag` (`terminauftrag_nr`, `kunde_nr`, `auftragsdatum`, `lieferdatum`, `angebot_nr`, `status`) VALUES
('TA-0', 'K-0', '2023-07-05 14:00:00', '2023-07-20 10:00:00', 'AN-0', 'In Bearbeitung'),
('TA-1', 'K-1', '2023-07-06 15:00:00', '2023-07-21 11:00:00', 'AN-1', 'Abgeschlossen'),
('TA-2', 'K-2', '2023-07-07 16:00:00', '2023-07-22 12:00:00', 'AN-2', 'In Bearbeitung'),
('TA-3', 'K-3', '2023-07-08 17:00:00', '2023-07-23 13:00:00', 'AN-3', 'Abgeschlossen'),
('TA-4', 'K-4', '2023-07-09 18:00:00', '2023-07-24 14:00:00', 'AN-4', 'In Bearbeitung');

-- Testdaten für Tabelle 'auftragsposition'
INSERT INTO `auftragsposition` (`auftragsposition_nr`, `artikel_nr`, `terminauftrag_nr`, `menge`) VALUES
('AUP-0', 'M-0', 'TA-0', 7),
('AUP-1', 'M-1', 'TA-1', 4),
('AUP-2', 'M-2', 'TA-2', 9),
('AUP-3', 'M-3', 'TA-3', 6),
('AUP-4', 'M-4', 'TA-4', 5);

-- Testdaten für Tabelle 'lager'
INSERT INTO `lager` (`lager_nr`, `name`, `adresse_nr`) VALUES
('L-0', 'Hauptlager', 'A-0'),
('L-1', 'Nebenlager', 'A-1'),
('L-2', 'Zentrallager', 'A-2'),
('L-3', 'Nordlager', 'A-3'),
('L-4', 'Südlager', 'A-4');

-- Testdaten für Tabelle 'lagerbestand'
INSERT INTO `lagerbestand` (`artikel_nr`, `lager_nr`, `menge`) VALUES
('M-0', 'L-0', 100),
('M-1', 'L-1', 50),
('M-2', 'L-2', 70),
('M-3', 'L-3', 60),
('M-4', 'L-4', 80);

-- Testdaten für Tabelle 'debitor'
INSERT INTO `debitor` (`debitor_nr`, `name`, `vorname`, `firma`, `kontonr`, `adresse_nr`) VALUES
('D-0', 'Schmidt', 'Hans', 'Schmidt GmbH', 'DE1234567890', 'A-0'),
('D-1', 'Meier', 'Anna', 'Meier AG', 'DE0987654321', 'A-1'),
('D-2', 'Schneider', 'Peter', 'Schneider KG', 'DE1122334455', 'A-2'),
('D-3', 'Fischer', 'Julia', 'Fischer OHG', 'DE5566778899', 'A-3'),
('D-4', 'Weber', 'Thomas', 'Weber & Co.', 'DE6677889900', 'A-4');

-- Testdaten für Tabelle 'rechnung'
INSERT INTO `rechnung` (`rechnung_nr`, `debitor_nr`, `rechnungstag`, `faelligkeitsdatum`, `status`) VALUES
('R-0', 'D-0', '2023-07-07 16:00:00', '2023-08-07 16:00:00', 'Offen'),
('R-1', 'D-1', '2023-07-08 17:00:00', '2023-08-08 17:00:00', 'Bezahlt'),
('R-2', 'D-2', '2023-07-09 18:00:00', '2023-08-09 18:00:00', 'Offen'),
('R-3', 'D-3', '2023-07-10 19:00:00', '2023-08-10 19:00:00', 'Bezahlt'),
('R-4', 'D-4', '2023-07-11 20:00:00', '2023-08-11 20:00:00', 'Offen');

-- Testdaten für Tabelle 'rechnungsposition'
INSERT INTO `rechnungsposition` (`rechnungsposition_nr`, `artikel_nr`, `rechnung_nr`, `menge`) VALUES
('RP-0', 'M-0', 'R-0', 5),
('RP-1', 'M-1', 'R-1', 3),
('RP-2', 'M-2', 'R-2', 7),
('RP-3', 'M-3', 'R-3', 2),
('RP-4', 'M-4', 'R-4', 4);

-- Testdaten für Tabelle 'offener_posten'
INSERT INTO `offener_posten` (`offener_posten_nr`, `rechnung_nr`, `debitor_nr`, `status`, `gesamtbetrag`) VALUES
('OP-0', 'R-0', 'D-0', 'Offen', 75.0),
('OP-1', 'R-1', 'D-1', 'Bezahlt', 75.0),
('OP-2', 'R-2', 'D-2', 'Offen', 105.0),
('OP-3', 'R-3', 'D-3', 'Bezahlt', 90.0),
('OP-4', 'R-4', 'D-4', 'Offen', 110.0);

-- Testdaten für Tabelle 'zahlung'
INSERT INTO `zahlung` (`zahlung_nr`, `offener_posten_nr`, `debitor_nr`, `betrag`, `zahlungsdatum`) VALUES
('Z-0', 'OP-1', 'D-1', 75.0, '2023-07-09 18:00:00'),
('Z-1', 'OP-3', 'D-3', 90.0, '2023-07-10 19:00:00');

-- Testdaten für Tabelle 'lieferung'
INSERT INTO `lieferung` (`lieferung_nr`, `terminauftrag_nr`, `rechnung_nr`) VALUES
('LIE-0', 'TA-0', 'R-0'),
('LIE-1', 'TA-1', 'R-1'),
('LIE-2', 'TA-2', 'R-2'),
('LIE-3', 'TA-3', 'R-3'),
('LIE-4', 'TA-4', 'R-4');

-- Testdaten für Tabelle 'lieferungsposition'
INSERT INTO `lieferungsposition` (`lieferungsposition_nr`, `lieferung_nr`, `artikel_nr`, `menge`) VALUES
('LP-0', 'LIE-0', 'M-0', 7),
('LP-1', 'LIE-1', 'M-1', 4),
('LP-2', 'LIE-2', 'M-2', 9),
('LP-3', 'LIE-3', 'M-3', 6),
('LP-4', 'LIE-4', 'M-4', 5);
