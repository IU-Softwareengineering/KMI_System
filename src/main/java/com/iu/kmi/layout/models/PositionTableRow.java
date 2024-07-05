package com.iu.kmi.layout.models;

import com.iu.kmi.entities.AngebotsPosition;

public class PositionTableRow {
    private AngebotsPosition position;

    private ModelState State;

    public AngebotsPosition getPosition() {
        return position;
    }

    public void setPosition(AngebotsPosition position) {
        this.position = position;
    }

    public ModelState getState() {
        return State;
    }

    public void setState(ModelState state) {
        State = state;
    }

    public PositionTableRow(AngebotsPosition position, ModelState state) {
        this.position = position;
        State = state;
    }
}
