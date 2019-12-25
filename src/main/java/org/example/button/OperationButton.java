package org.example.button;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.themes.ValoTheme;

public class OperationButton extends ButtonMain {

    public OperationButton(){
        setWidth(80, Unit.PIXELS);
        addStyleName(ValoTheme.BUTTON_DANGER);
    }
}
