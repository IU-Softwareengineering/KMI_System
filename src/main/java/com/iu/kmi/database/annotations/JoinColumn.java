package com.iu.kmi.database.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface JoinColumn {
    String name();
    String referencedColumnName();
}
