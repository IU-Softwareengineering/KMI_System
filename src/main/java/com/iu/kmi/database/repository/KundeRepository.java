package com.iu.kmi.database.repository;

import com.iu.kmi.entities.Kunde;

public interface KundeRepository extends Repository<Kunde,String>{
    public Kunde findByName(String name);
}
