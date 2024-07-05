package com.iu.kmi.layout.models;

public class RechnungModel {
    public String displayText;
    public String value;
    public String placeholder = null;

    public RechnungModel(String displayText, String value) {
        this.displayText = displayText;
        this.value = value;
    }

    public RechnungModel(String displayText, String value, String placeholder) {
        this.displayText = displayText;
        this.value = value;
        this.placeholder = placeholder;
    }

    @Override
    public String toString() {
        return displayText;
    }
}
