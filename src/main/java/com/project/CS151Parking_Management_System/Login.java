package com.project.CS151Parking_Management_System;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

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

    private HtmlComponent lineBreak;
    private Div div;
    private TextField licensePlate;
    private Paragraph licenseLabel;
    private HorizontalLayout licenseLayout;
    private PasswordField password;
    private Paragraph passwordLabel;
    private  HorizontalLayout passwordLayout;
    private Button loginButton;
    private Paragraph statusText;
    

    public Login() {
        lineBreak = new HtmlComponent("br");

        getStyle().set("text-align", "center");
        div = new Div();
        setHorizontalComponentAlignment(Alignment.CENTER, div);
        div.getStyle().set("background-color", "#AFEEEE");
        div.getStyle().set("width", "50%");
        div.getStyle().set("height", "12.75em");
        div.getStyle().set("margin", "auto");
        div.getStyle().set("border-radius", "10em");
        
        div.add(lineBreak);
        licensePlate = new TextField();
        licenseLabel = new Paragraph("License Plate #");
        licenseLayout = new HorizontalLayout(licenseLabel, licensePlate);
        licenseLayout.setAlignItems(Alignment.CENTER);
        licenseLayout.getStyle().set("margin-left", "9em");
        div.add(licenseLayout);

        

        password = new PasswordField();
        passwordLabel = new Paragraph("Password");
        passwordLayout = new HorizontalLayout(password, passwordLabel);
        passwordLayout.setAlignItems(Alignment.CENTER);
        passwordLayout.getStyle().set("margin-left", "9em");
        div.add(passwordLayout);

        loginButton = new Button("Login");
        statusText = new Paragraph("Please Enter Your Information");
        statusText.getStyle().set("color", "#1D3F6E");
        loginButton.addClickListener(e -> {
            InfluxHandler influx = new InfluxHandler();
            try {

                String passwordText = influx.parseData(influx.getData("mydb"), licensePlate.getValue());
                String key = influx.parseData(influx.getData("keys"), licensePlate.getValue());
                SecurePasswordHasher encrypter = new SecurePasswordHasher();

                if(passwordText.equals("Wrong License Plate."))
                    statusText.setText("We don't recognize that license plate.");
                else if(encrypter.passwordsMatch(password.getValue(), passwordText)){
                    statusText.setText("Successful");
                        loginButton.getUI().ifPresent(ui ->
                            ui.navigate("homePage/" + licensePlate.getValue() + "/" + key)
                        );
                }
                else{ 
                    statusText.setText("Wrong");
                }
            } catch (IOException | NoSuchAlgorithmException e1) {
                e1.printStackTrace();
            }
        });
        div.add(loginButton);
        div.add(statusText);
        add(div);
    }
}
