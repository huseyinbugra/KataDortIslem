package org.example.button;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.themes.ValoTheme;

public class SimpleButton extends ButtonMain {

    private int Number;

    public SimpleButton() {
        setWidth(80, Unit.PIXELS);
        addStyleName(ValoTheme.BUTTON_FRIENDLY);
    }

    public int getNumber() {
        return Number;
    }

    public void setNumber(int number) {
        Number = number;
    }

}