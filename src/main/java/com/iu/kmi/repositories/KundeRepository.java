package com.iu.kmi.repositories;

import com.iu.kmi.entities.Kunde;
import com.iu.kmi.database.repository.Repository;

/**
 * Repository interface for the Kunde entity. Provides CRUD operations.
 *
 * @author Othman
 */
public interface KundeRepository extends Repository<Kunde, String> {
    Kunde findByName(String name);
}
