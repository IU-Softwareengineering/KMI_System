USE dev1_db;

CREATE TABLE IF NOT EXISTS adresse (
    adresse_nr VARCHAR(255) NOT NULL,
    strasse VARCHAR(255),
    hausnummer VARCHAR(255),
    postleitzahl VARCHAR(255),
    stadt VARCHAR(255),
    land VARCHAR(255),
    PRIMARY KEY (adresse_nr)
);

CREATE TABLE IF NOT EXISTS kunde (
    kunde_nr VARCHAR(255) NOT NULL,
    name VARCHAR(255),
    vorname VARCHAR(255),
    email VARCHAR(255),
    telefon VARCHAR(255),
    adresse_nr VARCHAR(255),
    PRIMARY KEY (kunde_nr),
    FOREIGN KEY (adresse_nr) REFERENCES adresse(adresse_nr)
);

CREATE TABLE IF NOT EXISTS kundenanfrage (
    kundenanfrage_nr VARCHAR(255) NOT NULL,
    kunde_nr VARCHAR(255),
    anfragedatum DATETIME,
    anfragebeschreibung TEXT,
    prioritaet VARCHAR(255),
    status VARCHAR(255),
    PRIMARY KEY (kundenanfrage_nr),
    FOREIGN KEY (kunde_nr) REFERENCES kunde(kunde_nr)
);

CREATE TABLE IF NOT EXISTS material (
    artikel_nr VARCHAR(255) NOT NULL,
    name VARCHAR(255),
    beschreibung TEXT,
    lieferant VARCHAR(255),
    einkaufspreis DECIMAL(10, 2),
    verkaufspreis DECIMAL(10, 2),
    PRIMARY KEY (artikel_nr)
);

CREATE TABLE IF NOT EXISTS angebotsposition (
    angebotsposition_nr VARCHAR(255) NOT NULL,
    artikel_nr VARCHAR(255),
    angebot_nr VARCHAR(255),
    menge INT,
    PRIMARY KEY (angebotsposition_nr),
    FOREIGN KEY (artikel_nr) REFERENCES material(artikel_nr)
);

CREATE TABLE IF NOT EXISTS anfrageposition (
    anfrageposition_nr VARCHAR(255) NOT NULL,
    artikel_nr VARCHAR(255),
    kundenanfrage_nr VARCHAR(255),
    menge INT,
    PRIMARY KEY (anfrageposition_nr),
    FOREIGN KEY (artikel_nr) REFERENCES material(artikel_nr),
    FOREIGN KEY (kundenanfrage_nr) REFERENCES kundenanfrage(kundenanfrage_nr)
);

CREATE TABLE IF NOT EXISTS auftragsposition (
    auftragsposition_nr VARCHAR(255) NOT NULL,
    artikel_nr VARCHAR(255),
    terminauftrag_nr VARCHAR(255),
    menge INT,
    PRIMARY KEY (auftragsposition_nr),
    FOREIGN KEY (artikel_nr) REFERENCES material(artikel_nr)
);

CREATE TABLE IF NOT EXISTS rechnungsposition (
    rechnungsposition_nr VARCHAR(255) NOT NULL,
    artikel_nr VARCHAR(255),
    rechnung_nr VARCHAR(255),
    menge INT,
    PRIMARY KEY (rechnungsposition_nr),
    FOREIGN KEY (artikel_nr) REFERENCES material(artikel_nr)
);

CREATE TABLE IF NOT EXISTS kondition (
    kondition_nr VARCHAR(255) NOT NULL,
    name VARCHAR(255),
    zahlungsbedingungen TEXT,
    lieferbedingungen TEXT,
    rabatt DECIMAL(5, 2),
    PRIMARY KEY (kondition_nr)
);

CREATE TABLE IF NOT EXISTS angebot (
    angebot_nr VARCHAR(255) NOT NULL,
    kunde_nr VARCHAR(255),
    angebotsdatum DATETIME,
    gueltig_bis DATETIME,
    waehrung VARCHAR(255),
    status VARCHAR(255),
    kondition_nr VARCHAR(255),
    PRIMARY KEY (angebot_nr),
    FOREIGN KEY (kunde_nr) REFERENCES kunde(kunde_nr),
    FOREIGN KEY (kondition_nr) REFERENCES kondition(kondition_nr)
);


CREATE TABLE IF NOT EXISTS terminauftrag (
    terminauftrag_nr VARCHAR(255) NOT NULL,
    kunde_nr VARCHAR(255),
    auftragsdatum DATETIME,
    lieferdatum DATETIME,
    angebot_nr VARCHAR(255),
    status VARCHAR(255),
    PRIMARY KEY (terminauftrag_nr),
    FOREIGN KEY (kunde_nr) REFERENCES kunde(kunde_nr),
    FOREIGN KEY (angebot_nr) REFERENCES angebot(angebot_nr)
);

CREATE TABLE IF NOT EXISTS lager (
    lager_nr VARCHAR(255) NOT NULL,
    name VARCHAR(255),
    adresse_nr VARCHAR(255),
    PRIMARY KEY (lager_nr),
    FOREIGN KEY (adresse_nr) REFERENCES adresse(adresse_nr)
);

CREATE TABLE IF NOT EXISTS lagerbestand (
    artikel_nr VARCHAR(255) NOT NULL,
    lager_nr VARCHAR(255) NOT NULL,
    menge INT,
    PRIMARY KEY (artikel_nr, lager_nr),
    FOREIGN KEY (artikel_nr) REFERENCES material(artikel_nr),
    FOREIGN KEY (lager_nr) REFERENCES lager(lager_nr)
);

CREATE TABLE IF NOT EXISTS debitor (
    debitor_nr VARCHAR(255) NOT NULL,
    name VARCHAR(255),
    vorname VARCHAR(255),
    firma VARCHAR(255),
    kontonr VARCHAR(255),
    adresse_nr VARCHAR(255),
    PRIMARY KEY (debitor_nr),
    FOREIGN KEY (adresse_nr) REFERENCES adresse(adresse_nr)
);

CREATE TABLE IF NOT EXISTS rechnung (
    rechnung_nr VARCHAR(255) NOT NULL,
    debitor_nr VARCHAR(255),
    rechnungstag DATETIME,
    faelligkeitsdatum DATETIME,
    status VARCHAR(255),
    PRIMARY KEY (rechnung_nr),
    FOREIGN KEY (debitor_nr) REFERENCES debitor(debitor_nr)
);

CREATE TABLE IF NOT EXISTS offener_posten (
    offener_posten_nr VARCHAR(255) NOT NULL,
    rechnung_nr VARCHAR(255),
    debitor_nr VARCHAR(255),
    status VARCHAR(255),
    gesamtbetrag DECIMAL(10, 2),
    PRIMARY KEY (offener_posten_nr),
    FOREIGN KEY (rechnung_nr) REFERENCES rechnung(rechnung_nr),
    FOREIGN KEY (debitor_nr) REFERENCES debitor(debitor_nr)
);

CREATE TABLE IF NOT EXISTS zahlung (
    zahlung_nr VARCHAR(255) NOT NULL,
    offener_posten_nr VARCHAR(255),
    debitor_nr VARCHAR(255),
    betrag DECIMAL(10, 2),
    zahlungsdatum DATETIME,
    PRIMARY KEY (zahlung_nr),
    FOREIGN KEY (offener_posten_nr) REFERENCES offener_posten(offener_posten_nr),
    FOREIGN KEY (debitor_nr) REFERENCES debitor(debitor_nr)
);

CREATE TABLE IF NOT EXISTS lieferung (
    lieferung_nr VARCHAR(255) NOT NULL,
    terminauftrag_nr VARCHAR(255),
    rechnung_nr VARCHAR(255),
    PRIMARY KEY (lieferung_nr),
    FOREIGN KEY (terminauftrag_nr) REFERENCES terminauftrag(terminauftrag_nr),
    FOREIGN KEY (rechnung_nr) REFERENCES rechnung(rechnung_nr)
);

CREATE TABLE IF NOT EXISTS lieferungsposition (
    lieferungsposition_nr VARCHAR(255) NOT NULL,
    lieferung_nr VARCHAR(255),
    artikel_nr VARCHAR(255),
    menge INT,
    PRIMARY KEY (lieferungsposition_nr),
    FOREIGN KEY (lieferung_nr) REFERENCES lieferung(lieferung_nr),
    FOREIGN KEY (artikel_nr) REFERENCES material(artikel_nr)
);


