package com.iu.kmi.database.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface JoinTable {
    String name();
    String joinColumn();
    String referencedColumnName();
}
