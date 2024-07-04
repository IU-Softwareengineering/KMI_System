package com.iu.kmi.layout.models;

import com.iu.kmi.entities.Kondition;

public class AngebotKonditionModel {
    public Kondition value;
    private boolean isPlaceholder;

    public AngebotKonditionModel(Kondition value, boolean isPlaceholder) {
        this.value = value;
        this.isPlaceholder = isPlaceholder;
    }

    public boolean isPlaceholder() {
        return isPlaceholder;
    }

    @Override
    public String toString() {
        return this.isPlaceholder ? "" : this.value.getName();
    }
}
