package com.iu.kmi.layout.models;

public class AngebotStatusModel {
    public String displayText;
    public String value;

    public AngebotStatusModel(String displayText, String value) {
        this.displayText = displayText;
        this.value = value;
    }

    @Override
    public String toString() {
        return displayText;
    }
}
