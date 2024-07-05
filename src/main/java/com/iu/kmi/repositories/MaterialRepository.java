package com.iu.kmi.repositories;

import com.iu.kmi.database.orm.query.FindByIdQuery;
import com.iu.kmi.database.repository.Repository;
import com.iu.kmi.entities.Material;

public interface MaterialRepository extends Repository<Material,String>{
    @Override
    FindByIdQuery<Material> findById(String s);
}