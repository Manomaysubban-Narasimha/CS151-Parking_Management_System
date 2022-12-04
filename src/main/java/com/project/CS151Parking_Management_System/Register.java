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
    private HtmlComponent lineBreak;
    private Div div;
    private TextField licensePlate;
    private TextField licensePlateConfirm;
    private Paragraph licenseLabel;
    private Paragraph licenseLabelConfirm;
    private Paragraph passwordLabel;
    private HorizontalLayout licenseLayout;
    private HorizontalLayout confirmLicenseLayout;
    private HorizontalLayout vehicleTypeLayout, passwordLayout;
    private ComboBox vehicleType;
    private PasswordField password;
    private PasswordField passwordConfirm;
    private Paragraph passwordLabelConfirm;
    private HorizontalLayout passwordConfirmLayout;
    private Paragraph licenseNotMatch;
    private Paragraph passwordNotMatch;
    private HorizontalLayout licenseNotMatchLayout;
    private HorizontalLayout passwordNotMatchLayout;
    private Paragraph passReqsNeeded;
    private HorizontalLayout passwordReqsDisplay;
    private Button registerButton;

    public Register() {
        
        lineBreak = new HtmlComponent("br");

        getStyle().set("text-align", "center");
        div = new Div();
        setHorizontalComponentAlignment(Alignment.CENTER, div);
        div.getStyle().set("background-color", "#AFEEEE");
        div.getStyle().set("width", "50%");
        div.getStyle().set("height", "25.75em");
        div.getStyle().set("margin", "auto");
        div.getStyle().set("border-radius", "10em");
        
        div.add(lineBreak);
        licensePlate = new TextField();
        licenseLabel = new Paragraph("License Plate #");
        licenseLayout = new HorizontalLayout(licenseLabel, licensePlate);
        licenseLayout.setAlignItems(Alignment.CENTER);
        licenseLayout.getStyle().set("margin-left", "9em");
        div.add(licenseLayout);

        licensePlateConfirm = new TextField();
        licenseLabelConfirm = new Paragraph("Confirm Plate #");
        confirmLicenseLayout = new HorizontalLayout(licenseLabelConfirm, licensePlateConfirm);
        confirmLicenseLayout.setAlignItems(Alignment.CENTER);
        confirmLicenseLayout.getStyle().set("margin-left", "9em");
        div.add(confirmLicenseLayout);

        vehicleType = new ComboBox("Select your vehicle type: ");
        vehicleType.setItems("Motorcycle", "Truck", "SUV", "Electric", "Sedan", "Compact");

        vehicleTypeLayout = new HorizontalLayout(vehicleType);
        vehicleTypeLayout.setAlignItems(Alignment.CENTER);
        vehicleTypeLayout.getStyle().set("margin-left", "9em");
        div.add(vehicleTypeLayout);     

        password = new PasswordField();

        passwordLabel = new Paragraph("Enter a Password");

        passwordLayout = new HorizontalLayout(password, passwordLabel);
        passwordLayout.setAlignItems(Alignment.CENTER);
        passwordLayout.getStyle().set("margin-left", "9em");
        div.add(passwordLayout);

        passwordConfirm = new PasswordField();
        passwordLabelConfirm = new Paragraph("Confirm Password");

        passwordConfirmLayout = new HorizontalLayout(passwordConfirm, passwordLabelConfirm);
        passwordConfirmLayout.setAlignItems(Alignment.CENTER);
        passwordConfirmLayout.getStyle().set("margin-left", "9em");
        div.add(passwordConfirmLayout);

        licenseNotMatch = new Paragraph("License Numbers Don't Match");
        passwordNotMatch = new Paragraph("Passwords Don't Match");
        licenseNotMatchLayout = new HorizontalLayout(licenseNotMatch);
        licenseNotMatchLayout.setAlignItems(Alignment.CENTER);
        passwordNotMatchLayout = new HorizontalLayout(passwordNotMatch);
        passwordNotMatchLayout.setAlignItems(Alignment.CENTER);

        passReqsNeeded = new Paragraph();
        passwordReqsDisplay = new HorizontalLayout(passReqsNeeded);
        passwordReqsDisplay.setAlignItems(Alignment.CENTER);
        passwordReqsDisplay.getStyle().set("margin-left", "9em");
        passwordReqsDisplay.getStyle().set("color", "red");


        registerButton = new Button("Register");
        registerButton.addClickListener(e -> {

            pwd = new String(password.getValue());
            boolean passwordChecked = false;
            boolean licenseChecked = false;
            boolean requirement = true;

            if("".equals(password.getValue())) passwordLabel.getStyle().set("color", "red");
            else passwordLabel.getStyle().set("color", "black");

            if("".equals(licensePlate.getValue())) licenseLabel.getStyle().set("color", "red");
            else licenseLabel.getStyle().set("color", "black");

            if("".equals(licensePlateConfirm.getValue())) licenseLabelConfirm.getStyle().set("color", "red");
            else licenseLabelConfirm.getStyle().set("color", "black");

            if("".equals(passwordConfirm.getValue())) passwordLabelConfirm.getStyle().set("color", "red");
            else passwordLabelConfirm.getStyle().set("color", "black");
            
            if(!password.getValue().equals(passwordConfirm.getValue()) && !"".equals(password.getValue()) && !"".equals(passwordConfirm.getValue())){
                passwordLabel.getStyle().set("color", "red");
                passwordLabelConfirm.getStyle().set("color", "red");
                passwordNotMatchLayout.setAlignItems(Alignment.CENTER);
                passwordNotMatchLayout.getStyle().set("color", "red");
                passwordNotMatchLayout.getStyle().set("margin-left", "9em");
                div.add(passwordNotMatchLayout);
            }
            if(password.getValue().equals(passwordConfirm.getValue()) && !"".equals(password.getValue()) && !"".equals(passwordConfirm.getValue())){
                passwordLabel.getStyle().set("color", "#065535");
                passwordLabelConfirm.getStyle().set("color", "#065535");
                div.remove(passwordNotMatchLayout);
                passwordChecked = true;
            }


            if(!licensePlate.getValue().equals(licensePlateConfirm.getValue()) && !"".equals(licensePlate.getValue()) && !"".equals(licensePlateConfirm.getValue())){
                licenseLabel.getStyle().set("color", "red");
                licenseLabelConfirm.getStyle().set("color", "red");
                licenseNotMatchLayout.getStyle().set("color", "red");
                licenseNotMatchLayout.setAlignItems(Alignment.CENTER);
                licenseNotMatchLayout.getStyle().set("margin-left", "9em");
                div.add(licenseNotMatchLayout);
            }
            if(licensePlate.getValue().equals(licensePlateConfirm.getValue()) && !"".equals(licensePlate.getValue()) && !"".equals(licensePlateConfirm.getValue())){
                licenseLabel.getStyle().set("color", "#065535");
                licenseLabelConfirm.getStyle().set("color", "#065535");
                div.remove(licenseNotMatchLayout);
                
                try {
                    minCheck();
                    if(minCheck()) {
                        div.remove(passwordReqsDisplay);
                    }
                }     
                catch (Minimum8CharactersRequired e1) {
                    requirement = false;
                    passReqsNeeded.setText("Password needs at least 8 Characters");
                    div.add(passwordReqsDisplay);
                }

                try {
                    numCheck();
                }
                catch (NumberCharacterMissing e1) {
                    requirement = false;
                    passReqsNeeded.setText("Password needs a number");
                    div.add(passwordReqsDisplay);
                }

                try {
                    specialCheck();
                }
                catch (SpecialCharacterMissing e1) {
                    requirement = false;
                    passReqsNeeded.setText("Password needs a special character");
                    div.add(passwordReqsDisplay);
                }

                try {
                    uppercaseCheck();
                } 
                catch (UpperCaseCharacterMissing e1) {
                    requirement = false;
                    passReqsNeeded.setText("Password needs an Uppercase Character");
                    div.add(passwordReqsDisplay);
                }
               
                try {
                    lowercaseCheck();
                } 
                catch (LowerCaseCharacterMissing e1) {
                    requirement = false;
                    passReqsNeeded.setText("Password needs a Lowercase Character");
                    div.add(passwordReqsDisplay);
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

                        influx.postData(licensePlate.getValue(), vehicleType.getValue().toString(), "vehicleType");
                        influx.postData(licensePlate.getValue(), SHA3_256.toHexString(SHA3_256.getSHA(password.getValue())), "mydb");
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
        final int MIN_CHARS_REQUIRED = 8;
		if (pwd.length() >= MIN_CHARS_REQUIRED) {
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
