CREATE OR REPLACE VIEW v_auftragsposition_menge AS
SELECT
    artikel_nr,
    terminauftrag_nr,
    COUNT(*) AS menge
FROM 
    auftragsposition
GROUP BY 
    artikel_nr, terminauftrag_nr;

CREATE OR REPLACE VIEW v_anfrageposition_menge AS
SELECT
    artikel_nr,
    kundenanfrage_nr,
    COUNT(*) AS menge
FROM 
    anfrageposition
GROUP BY 
    artikel_nr, kundenanfrage_nr;

CREATE OR REPLACE VIEW v_angebotsposition_menge AS
SELECT
    artikel_nr,
    angebot_nr,
    COUNT(*) AS menge
FROM 
    angebotsposition
GROUP BY 
    artikel_nr, angebot_nr;

CREATE OR REPLACE VIEW v_rechnungsposition_menge AS
SELECT
    artikel_nr,
    rechnung_nr,
    COUNT(*) AS menge
FROM 
    rechnungsposition
GROUP BY 
    artikel_nr, rechnung_nr;
