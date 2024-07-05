package com.iu.kmi.layout.models;

import com.iu.kmi.entities.Angebot;

public class AngebotModel {
    public String displayText;
    public Angebot value;
    public String placeholder = null;

    public AngebotModel(String displayText, Angebot value) {
        this.displayText = displayText;
        this.value = value;
    }

    public AngebotModel(String displayText, String value) {
        this.displayText = displayText;
        this.placeholder = value;
    }

    @Override
    public String toString() {
        return displayText;
    }
}
