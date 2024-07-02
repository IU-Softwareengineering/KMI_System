package com.iu.kmi.repositories;

import com.iu.kmi.database.repository.Repository;
import com.iu.kmi.entities.Lager;
import com.iu.kmi.entities.Lagerbestand;
import com.iu.kmi.entities.Material;

import java.util.Map;

public interface LagerbestandRepository extends Repository<Lagerbestand, Map<Material, Lager>> {

}