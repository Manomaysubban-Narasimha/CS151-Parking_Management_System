package com.project.CS151Parking_Management_System;

import java.io.IOException;

import com.vaadin.flow.component.HtmlComponent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("login")
public class Login extends VerticalLayout{
    public Login() {
        HtmlComponent br = new HtmlComponent("br");

        getStyle().set("text-align", "center");
        Div div = new Div();
        setHorizontalComponentAlignment(Alignment.CENTER, div);
        div.getStyle().set("background-color", "#AFEEEE");
        div.getStyle().set("width", "50%");
        div.getStyle().set("height", "12.75em");
        div.getStyle().set("margin", "auto");
        div.getStyle().set("border-radius", "10em");
        
        div.add(br);
        TextField licensePlate = new TextField();
        Paragraph licenseLabel = new Paragraph("License Plate #");
        HorizontalLayout l4 = new HorizontalLayout(licenseLabel, licensePlate);
        l4.setAlignItems(Alignment.CENTER);
        l4.getStyle().set("margin-left", "9em");
        div.add(l4);

        

        PasswordField password = new PasswordField();
        Paragraph passwordLabel = new Paragraph("Password");
        HorizontalLayout l3 = new HorizontalLayout(password, passwordLabel);
        l3.setAlignItems(Alignment.CENTER);
        l3.getStyle().set("margin-left", "9em");
        div.add(l3);

        Button loginButton = new Button("Login");
        Paragraph statusText = new Paragraph("Please Enter Your Information");
        statusText.getStyle().set("color", "#1D3F6E");
        loginButton.addClickListener(e -> {
            InfluxHandler influx = new InfluxHandler();
            try {
                String dataMydb = influx.getData("mydb");
                String dataKey = influx.getData("keys");
                String passwordText = influx.parseData(dataMydb, licensePlate.getValue(), false);
                String key = influx.parseData(dataKey, licensePlate.getValue(), false);
                if(passwordText.equals("Wrong License Plate"))
                    statusText.setText("We dont recognize that licenseplate");
                else if(password.getValue().equals(passwordText)){
                    statusText.setText("Successful");
                        loginButton.getUI().ifPresent(ui ->
                            ui.navigate("homePage/" + licensePlate.getValue() + "/" + key)
                        );
                }
                else 
                    statusText.setText("Wrong");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        div.add(loginButton);
        div.add(statusText);
        add(div);

    }
}
