

-- DROP DATABASE dev1_db;
-- CREATE DATABASE dev1_db;
-- USE dev1_db;

CREATE TABLE adresse (
  adresse_nr VARCHAR(20) PRIMARY KEY,
  strasse VARCHAR(50),
  hausnummer VARCHAR(5),
  postleitzahl VARCHAR(10),
  stadt VARCHAR(20),
  land VARCHAR(20)
);

CREATE TABLE kunde (
  kunde_nr VARCHAR(20) PRIMARY KEY,
  name VARCHAR(20),
  vorname VARCHAR(20),
  email VARCHAR(50),
  telefon VARCHAR(20),
  adresse_nr VARCHAR(20),
  FOREIGN KEY (adresse_nr) REFERENCES adresse(adresse_nr)
);

CREATE TABLE kundenanfrage (
  kundenanfrage_nr VARCHAR(20) PRIMARY KEY,
  kunde_nr VARCHAR(20),
  anfragedatum DATETIME,
  anfragebeschreibung VARCHAR(255),
  prioritaet VARCHAR(20),
  status VARCHAR(20),
  FOREIGN KEY (kunde_nr) REFERENCES kunde(kunde_nr)
);

CREATE TABLE kondition (
  kondition_nr VARCHAR(20) PRIMARY KEY,
  name VARCHAR(20),
  zahlungsbedingungen VARCHAR(255),
  lieferbedingungen VARCHAR(255),
  rabatt DECIMAL
);

CREATE TABLE angebot (
  angebot_nr VARCHAR(20) PRIMARY KEY,
  kunde_nr VARCHAR(20),
  angebotsdatum DATETIME,
  gueltig_bis DATETIME,
  waehrung VARCHAR(20),
  status VARCHAR(20),
  kondition_nr VARCHAR(20),
  FOREIGN KEY (kunde_nr) REFERENCES kunde(kunde_nr),
  FOREIGN KEY (kondition_nr) REFERENCES kondition(kondition_nr)
);

CREATE TABLE auftrag (
  auftrag_nr VARCHAR(20) PRIMARY KEY,
  kunde_nr VARCHAR(20),
  auftragsdatum DATETIME,
  lieferdatum DATETIME,
  angebot_nr VARCHAR(20),
  status VARCHAR(20),
  FOREIGN KEY (kunde_nr) REFERENCES kunde(kunde_nr),
  FOREIGN KEY (angebot_nr) REFERENCES angebot(angebot_nr)
);

CREATE TABLE material (
  artikel_nr VARCHAR(20) PRIMARY KEY,
  name VARCHAR(50),
  beschreibung VARCHAR(255),
  lieferant VARCHAR(50),
  einkaufspreis DECIMAL,
  verkaufspreis DECIMAL
);

CREATE TABLE debitor (
  debitor_nr VARCHAR(20) PRIMARY KEY,
  name VARCHAR(20),
  vorname VARCHAR(20),
  firma VARCHAR(20),
  kontonr VARCHAR(20),
  adresse_nr VARCHAR(20),
  FOREIGN KEY (adresse_nr) REFERENCES adresse(adresse_nr)
);

CREATE TABLE rechnung (
  rechnung_nr VARCHAR(20) PRIMARY KEY,
  debitor_nr VARCHAR(20),
  FOREIGN KEY (debitor_nr) REFERENCES debitor(debitor_nr)
);

CREATE TABLE lager (
  lager_nr VARCHAR(20) PRIMARY KEY,
  name VARCHAR(20),
  adresse_nr VARCHAR(20),
  FOREIGN KEY (adresse_nr) REFERENCES adresse(adresse_nr)
);

CREATE TABLE lagerbestand (
  artikel_nr VARCHAR(20),
  lager_nr VARCHAR(20),
  menge INT,
  PRIMARY KEY (artikel_nr, lager_nr),
  FOREIGN KEY (artikel_nr) REFERENCES material(artikel_nr),
  FOREIGN KEY (lager_nr) REFERENCES lager(lager_nr)
);

CREATE TABLE angebotsposition (
  angebotsposition_nr VARCHAR(20) PRIMARY KEY,
  artikel_nr VARCHAR(20),
  einzelpreis DECIMAL,
  FOREIGN KEY (artikel_nr) REFERENCES material(artikel_nr)
);

CREATE TABLE auftragsposition (
  auftragsposition_nr VARCHAR(20) PRIMARY KEY,
  artikel_nr VARCHAR(20),
  einzelpreis DECIMAL,
  FOREIGN KEY (artikel_nr) REFERENCES material(artikel_nr)
);

CREATE TABLE rechnungsposition (
  rechnungsposition_nr VARCHAR(20) PRIMARY KEY,
  artikel_nr VARCHAR(20),
  einzelpreis DECIMAL,
  rechnung_nr VARCHAR(20),
  FOREIGN KEY (artikel_nr) REFERENCES material(artikel_nr),
  FOREIGN KEY (rechnung_nr) REFERENCES rechnung(rechnung_nr)
);



