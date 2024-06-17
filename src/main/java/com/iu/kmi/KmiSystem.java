package com.iu.kmi;

import java.sql.SQLException;

public class KmiSystem {

    public static void main(String[] args) throws SQLException, ReflectiveOperationException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("Hello World!");
        


    }
}
