package com.iu.kmi.layout.models;

import com.iu.kmi.entities.Kunde;

public class AngebotKundenModel {
    public String displayText;
    public Kunde value;

    public AngebotKundenModel(String displayText, Kunde value) {
        this.displayText = displayText;
        this.value = value;
    }

    @Override
    public String toString() {
        return displayText;
    }
}
