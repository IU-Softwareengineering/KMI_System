package com.iu.kmi.repositories;

import com.iu.kmi.database.repository.Repository;
import com.iu.kmi.entities.Kunde;
import com.iu.kmi.entities.Kundenanfrage;

public interface KundenanfrageRepository extends Repository<Kundenanfrage, String> {
    Kundenanfrage findByKunde_name(String name);
}
