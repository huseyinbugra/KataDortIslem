package org.example.ui;

import com.vaadin.ui.*;
import org.example.button.ButtonMain;
import org.example.button.OperationButton;
import org.example.button.SimpleButton;

import java.util.Random;

public class Main extends VerticalLayout {

    private HorizontalLayout horizontalLayout;
    private int buttonNumber;
    private TextField captionField;
    private int counter;
    private String[] operationArray;
    private ButtonMain[][] btnArray;
    private int raw=6;
    private int column=6;

    public Main() {

        setMargin(true);
        setSpacing(true);

        operationArray= new String[]{"%","+","-","*","/","%","+","-","*","/","%"};
        btnArray = new ButtonMain[raw][column];
        int arrayCounter = 0;

        for (int i = 0; i <raw ; i++) {
            horizontalLayout = new HorizontalLayout();
            for (int j = 0; j <column ; j++) {
                if (i==0 || j==0){
                    arrayCounter = createOperationBtn(arrayCounter, i, j);
                }
                else if(i==raw-1 || j==column-1){
                    createZeroBtn(i, j);
                }
                else {
                    createRandomBtn(i, j);
                }
            }
            addComponent(horizontalLayout);
        }
    }

    private void createRandomBtn(int i, int j) {
        Random random = new Random();
        int number = random.nextInt(21) - 10;
        SimpleButton simpleButton = new SimpleButton();
        simpleButton.setData(number);
        simpleButton.setCaption(String.valueOf(number));
        btnArray[i][j] = simpleButton;
        simpleButton.setDescription(String.valueOf(simpleButton.getData()));
        simpleButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
            }
        });
        horizontalLayout.addComponent(simpleButton);
    }

    private void createZeroBtn(int i, int j) {
        SimpleButton simpleButton = new SimpleButton();
        simpleButton.setData(0);
        simpleButton.setCaption("0");
        btnArray[i][j] = simpleButton;
        simpleButton.setDescription(String.valueOf(simpleButton.getData()));
        simpleButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
            }
        });
        horizontalLayout.addComponent(simpleButton);
    }

    private int createOperationBtn(int arrayCounter, int i, int j) {

        OperationButton operationButton =  new OperationButton();
        operationButton.setData(operationArray[arrayCounter]);
        operationButton.setCaption(operationArray[arrayCounter]);
        arrayCounter++;
        btnArray[i][j]=operationButton;
        operationButton.setDescription(String.valueOf(operationButton.getData()));

        operationButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {

                if(i==0 && j==0){
                    double toplam = 0;
                    double mod = Double.valueOf(btnArray[raw-2][column-2].getData().toString());
                    for (int k = 1; k <column-1 ; k++) {
                        toplam += Double.valueOf(btnArray[i+k][j+k].getData().toString());
                    }
                    toplam%=mod;
                    btnArray[raw-1][column-1].setData(toplam);
                    btnArray[raw-1][column-1].setCaption(String.valueOf(toplam));
                    Notification.show(String.valueOf(btnArray[raw-1][column-1].getData()));
                }
                else if(i==0){
                    String operation = String.valueOf(operationButton.getData());
                    if(operation.equals("+")) {
                        int toplam = 0;
                        for (int k = 1; k <column-1 ; k++) {
                            toplam += (Integer)btnArray[i+k][j].getData();
                        }
                        btnArray[raw-1][j].setData(toplam);
                        btnArray[raw-1][j].setCaption(String.valueOf(toplam));
                        Notification.show(String.valueOf(btnArray[raw-1][j].getData()));
                    }
                    else if(operation.equals("-")){
                        int fark =(Integer) btnArray[i+1][j].getData();
                        for (int k = 2; k <column-1 ; k++) {
                            fark -= (Integer)btnArray[i+k][j].getData();
                        }
                        btnArray[raw-1][j].setData(fark);
                        btnArray[raw-1][j].setCaption(String.valueOf(fark));
                        Notification.show(String.valueOf(btnArray[raw-1][j].getData()));
                    }
                    else if(operation.equals("*")){
                        int carp = 1;
                        for (int k = 1; k <column-1 ; k++) {
                            carp *= (Integer)btnArray[i+k][j].getData();
                        }
                        btnArray[raw-1][j].setData(carp);
                        btnArray[raw-1][j].setCaption(String.valueOf(carp));
                        Notification.show(String.valueOf(btnArray[raw-1][j].getData()));
                    }
                    else if(operation.equals("/")){
                        double bol = Double.valueOf(btnArray[raw-2][j].getData().toString());
                        double toplam = 0;
                        for (int k = 1; k <column-1 ; k++) {
                            toplam += Double.valueOf(btnArray[i+k][j].getData().toString());
                        }
                        try {
                            toplam/=bol;
                            btnArray[raw-1][j].setData(toplam);
                            btnArray[raw-1][j].setCaption(String.valueOf(toplam));
                            Notification.show(String.valueOf(btnArray[raw-1][j].getData()));
                        }
                        catch (ArithmeticException a){
                            Notification.show("Payda sıfır olamaz. "+a);
                        }
                    }
                    else if(operation.equals("%")){
                        double toplam = 0;
                        double mod = Double.valueOf(btnArray[raw-2][j].getData().toString());
                        for (int k = 1; k <column-1 ; k++) {
                            toplam += Double.valueOf(btnArray[i+k][j].getData().toString());
                        }
                        toplam%=mod;
                        btnArray[raw-1][j].setData(toplam);
                        btnArray[raw-1][j].setCaption(String.valueOf(toplam));
                        Notification.show(String.valueOf(btnArray[raw-1][j].getData()));
                    }
                }
                else if(j==0){
                    String operation = String.valueOf(operationButton.getData());
                    if(operation.equals("+")) {
                        int toplam = 0;
                        for (int k = 1; k <raw-1 ; k++) {
                            toplam += (Integer)btnArray[i][j+k].getData();
                        }
                        btnArray[i][column-1].setData(toplam);
                        btnArray[i][column-1].setCaption(String.valueOf(toplam));
                        Notification.show(String.valueOf(btnArray[i][column-1].getData()));
                    }
                    else if(operation.equals("-")){
                            int fark =(Integer) btnArray[i][j+1].getData();
                            for (int k = 2; k <raw-1 ; k++) {
                            fark -= (Integer)btnArray[i][j+k].getData();
                        }
                        btnArray[i][column-1].setData(fark);
                        btnArray[i][column-1].setCaption(String.valueOf(fark));
                        Notification.show(String.valueOf(btnArray[i][column-1].getData()));
                    }
                    else if(operation.equals("*")){
                        int carp = 1;
                        for (int k = 1; k <raw-1 ; k++) {
                            carp *= (Integer)btnArray[i][j+k].getData();
                        }
                        btnArray[i][column-1].setData(carp);
                        btnArray[i][column-1].setCaption(String.valueOf(carp));
                        Notification.show(String.valueOf(btnArray[i][column-1].getData()));
                    }
                    else if(operation.equals("/")){
                        double bol = (Integer)btnArray[i][column-2].getData();
                        double toplam = 0;
                        for (int k = 1; k <raw-1 ; k++) {
                            toplam += Double.valueOf(btnArray[i][j+k].getData().toString());
                        }
                        try {
                            toplam/=bol;
                            btnArray[i][column-1].setData(toplam);
                            btnArray[i][column-1].setCaption(String.valueOf(toplam));
                            Notification.show(String.valueOf(btnArray[i][column-1].getData()));
                        }
                        catch (ArithmeticException a){
                            Notification.show("Payda sıfır olamaz. "+a);
                        }
                    }
                    else if(operation.equals("%")){
                        double toplam = 0;
                        double mod = Double.valueOf(btnArray[i][column-2].getData().toString());
                        for (int k = 1; k <column-1 ; k++) {
                            toplam += Double.valueOf(btnArray[i][j+k].getData().toString());
                        }
                        toplam%=mod;
                        btnArray[i][column-1].setData(toplam);
                        btnArray[i][column-1].setCaption(String.valueOf(toplam));
                        Notification.show(String.valueOf(btnArray[i][column-1].getData()));
                    }
                }
            }
        });
        horizontalLayout.addComponent(operationButton);
        return arrayCounter;
    }
}