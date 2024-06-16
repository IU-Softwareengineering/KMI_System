package com.iu.kmi;

import com.iu.kmi.database.DatabaseConnection;
import com.iu.kmi.database.repository.RepositoryProxy;
import com.iu.kmi.entities.Debitor;
import com.iu.kmi.test.DebitorRepository;

import java.sql.SQLException;

public class KmiSystem {

    public static void main(String[] args) throws SQLException, ReflectiveOperationException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("Hello World!");

        System.out.println(DatabaseConnection.getConnection().getClientInfo());

        DebitorRepository debitorRepository = RepositoryProxy.newInstance(DebitorRepository.class);
        Debitor debitor = debitorRepository.findById("1").findOne();
    }
}
