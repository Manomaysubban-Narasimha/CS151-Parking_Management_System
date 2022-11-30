package com.project.CS151Parking_Management_System;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import com.vaadin.flow.component.HtmlComponent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("register")
public class Register extends VerticalLayout{
    private String pwd;
	private String specials = " !#$%&'()*+,-./:;<=>?@[]^_`{|}";
    public Register() {

        HtmlComponent br = new HtmlComponent("br");

        getStyle().set("text-align", "center");
        Div div = new Div();
        setHorizontalComponentAlignment(Alignment.CENTER, div);
        div.getStyle().set("background-color", "#AFEEEE");
        div.getStyle().set("width", "50%");
        div.getStyle().set("height", "25.75em");
        div.getStyle().set("margin", "auto");
        div.getStyle().set("border-radius", "10em");
        
        div.add(br);
        TextField licensePlate = new TextField();
        Paragraph licenseLabel = new Paragraph("License Plate #");
        HorizontalLayout l4 = new HorizontalLayout(licenseLabel, licensePlate);
        l4.setAlignItems(Alignment.CENTER);
        l4.getStyle().set("margin-left", "9em");
        div.add(l4);

        TextField licensePlateConfirm = new TextField();
        Paragraph licenseLabelConfirm = new Paragraph("Confirm Plate #");
        HorizontalLayout l3 = new HorizontalLayout(licenseLabelConfirm, licensePlateConfirm);
        l3.setAlignItems(Alignment.CENTER);
        l3.getStyle().set("margin-left", "9em");
        div.add(l3);

        ComboBox vehicleType = new ComboBox("Select your vehicle type: ");
        vehicleType.setItems("Motorcycle","Truck", "SUV", "Clean Air/Electric Vehicle", "Sedan/Hatchback");
        HorizontalLayout l5 = new HorizontalLayout(vehicleType);
        l5.setAlignItems(Alignment.CENTER);
        l5.getStyle().set("margin-left", "9em");
        div.add(l5);     

        PasswordField password = new PasswordField();

        Paragraph passwordLabel = new Paragraph("Enter a Password");
        HorizontalLayout l2 = new HorizontalLayout(password, passwordLabel);
        l2.setAlignItems(Alignment.CENTER);
        l2.getStyle().set("margin-left", "9em");
        div.add(l2);

        PasswordField passwordConfirm = new PasswordField();
        Paragraph passwordLabelConfirm = new Paragraph("Confirm Password");
        HorizontalLayout l = new HorizontalLayout(passwordConfirm, passwordLabelConfirm);
        l.setAlignItems(Alignment.CENTER);
        l.getStyle().set("margin-left", "9em");
        div.add(l);

        Paragraph licenseNotMatch = new Paragraph("License Numbers Don't Match");
        Paragraph passwordNotMatch = new Paragraph("Passwords Don't Match");
        HorizontalLayout l6 = new HorizontalLayout(licenseNotMatch);
        l6.setAlignItems(Alignment.CENTER);
        HorizontalLayout l7 = new HorizontalLayout(passwordNotMatch);
        l7.setAlignItems(Alignment.CENTER);

        Paragraph PassReqsNeeded = new Paragraph();
        HorizontalLayout l8 = new HorizontalLayout(PassReqsNeeded);
        l8.setAlignItems(Alignment.CENTER);
        l8.getStyle().set("margin-left", "9em");
        l8.getStyle().set("color", "red");


        Button registerButton = new Button("Register");
        registerButton.addClickListener(e -> {

            pwd = new String(password.getValue());
            boolean passwordChecked = false;
            boolean licenseChecked = false;
            boolean requirement = true;

            if(password.getValue() == "") passwordLabel.getStyle().set("color", "red");
            else passwordLabel.getStyle().set("color", "black");

            if(licensePlate.getValue() == "") licenseLabel.getStyle().set("color", "red");
            else licenseLabel.getStyle().set("color", "black");

            if(licensePlateConfirm.getValue() == "") licenseLabelConfirm.getStyle().set("color", "red");
            else licenseLabelConfirm.getStyle().set("color", "black");

            if(passwordConfirm.getValue() == "") passwordLabelConfirm.getStyle().set("color", "red");
            else passwordLabelConfirm.getStyle().set("color", "black");
            
            if(!password.getValue().equals(passwordConfirm.getValue()) && password.getValue() != "" && passwordConfirm.getValue() != ""){
                passwordLabel.getStyle().set("color", "red");
                passwordLabelConfirm.getStyle().set("color", "red");
                l7.setAlignItems(Alignment.CENTER);
                l7.getStyle().set("color", "red");
                l7.getStyle().set("margin-left", "9em");
                div.add(l7);
            }
            if(password.getValue().equals(passwordConfirm.getValue()) && password.getValue() != "" && passwordConfirm.getValue() != ""){
                passwordLabel.getStyle().set("color", "#065535");
                passwordLabelConfirm.getStyle().set("color", "#065535");
                div.remove(l7);
                passwordChecked = true;
            }


            if(!licensePlate.getValue().equals(licensePlateConfirm.getValue()) && licensePlate.getValue() != "" && licensePlateConfirm.getValue() != ""){
                licenseLabel.getStyle().set("color", "red");
                licenseLabelConfirm.getStyle().set("color", "red");
                l6.getStyle().set("color", "red");
                l6.setAlignItems(Alignment.CENTER);
                l6.getStyle().set("margin-left", "9em");
                div.add(l6);
            }
            if(licensePlate.getValue().equals(licensePlateConfirm.getValue()) && licensePlate.getValue() != "" && licensePlateConfirm.getValue() != ""){
                licenseLabel.getStyle().set("color", "#065535");
                licenseLabelConfirm.getStyle().set("color", "#065535");
                div.remove(l6);
                
                try {
                    minCheck();
                    if(minCheck()) {
                        div.remove(l8);
                    }
                }     
                catch (Minimum8CharactersRequired e1) {
                    requirement = false;
                    PassReqsNeeded.setText("Password needs at least 8 Characters");
                    div.add(l8);
                }

                try {
                    numCheck();
                }
                catch (NumberCharacterMissing e1) {
                    requirement = false;
                    PassReqsNeeded.setText("Password needs a number");
                    div.add(l8);
                }

                try {
                    specialCheck();
                }
                catch (SpecialCharacterMissing e1) {
                    requirement = false;
                    PassReqsNeeded.setText("Password needs a special character");
                    div.add(l8);
                }

                try {
                    uppercaseCheck();
                } 
                catch (UpperCaseCharacterMissing e1) {
                    requirement = false;
                    PassReqsNeeded.setText("Password needs an Uppercase Character");
                    div.add(l8);
                }
               
                try {
                    lowercaseCheck();
                } 
                catch (LowerCaseCharacterMissing e1) {
                    requirement = false;
                    PassReqsNeeded.setText("Password needs a Lowercase Character");
                    div.add(l8);
                }
                
                licenseChecked = true;
            } 

            if(passwordChecked && licenseChecked && requirement){
                InfluxHandler influx = new InfluxHandler();
                try {
                    if(influx.parseData(influx.getData("mydb"), licensePlate.getValue()).equals("Wrong License Plate")){                            
                        influx.createDB("mydb");
                        influx.createDB("keys");
                        influx.createDB("vehicleType");
                        System.out.println(vehicleType.getValue().toString());

                        influx.postData(licensePlate.getValue(), vehicleType.getValue().toString(), "vehicleType");
                        influx.postData(licensePlate.getValue(), SHA256.toHexString(SHA256.getSHA(password.getValue())), "mydb");
                        influx.postData(licensePlate.getValue(), influx.getAlphaNumericString(40), "keys");
                        Thread.sleep(2000);
                        registerButton.getUI().ifPresent(ui ->
                                ui.navigate("")
                        );
                    }
                    else{
                        registerButton.setText("That Licenseplate Is already Registered");
                    }
                } catch (IOException | InterruptedException | NoSuchAlgorithmException e1) {
                    e1.printStackTrace();
                }
            }
        
        });    
        div.add(registerButton);
		add(div);
    }

    //Password Requirement Methods:
    public boolean uppercaseCheck() throws UpperCaseCharacterMissing {
        for (int i = 0; i < pwd.length(); i++) {
            
            if (Character.isUpperCase(pwd.charAt(i))) {
                
                return true;
            }
        }
        System.out.println(pwd);
        throw new UpperCaseCharacterMissing("Missing an uppercase character");        
    }

    public boolean lowercaseCheck() throws LowerCaseCharacterMissing {
		for (int i = 0; i < pwd.length(); i++) {
			if (Character.isLowerCase(pwd.charAt(i))) {
				return true;
			}
		}
		throw new LowerCaseCharacterMissing("Missing a lowercase character");

	}

	public boolean specialCheck() throws SpecialCharacterMissing {
		for (int i = 0; i < specials.length(); i++) {
			if (pwd.contains(Character.toString(specials.charAt(i)))) {
				return true;
			}
		}
		throw new SpecialCharacterMissing("Missing a special character");
	}

	public boolean minCheck() throws Minimum8CharactersRequired {
		if (pwd.length() > 7) {
			return true;
		}
		throw new Minimum8CharactersRequired("Need to have at least 8 characters");
	}

	public boolean numCheck() throws NumberCharacterMissing {
		for (int i = 0; i < pwd.length(); i++) {
			if (Character.isDigit(pwd.charAt(i))) {
				return true;
			}
		}
		throw new NumberCharacterMissing("Need to have at least one number");
	}


}
