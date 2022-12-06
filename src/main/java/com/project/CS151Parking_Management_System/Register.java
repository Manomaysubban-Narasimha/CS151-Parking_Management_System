package com.project.CS151Parking_Management_System;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.Set;

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

/**
 * References:
 * https://nordpass.com/most-common-passwords-list/
 * https://www.geeksforgeeks.org/inner-class-java/
 * 
 */
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
    private boolean passwordChecked;
    private boolean licenseChecked;
    private boolean requirement;

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
        setUpLicenseFields();
        setUpVehicleSelection();  
        setUpPasswordFields();
        setUpNotMatch();
        setUpPasswordCheck();


        registerButton = new Button("Register");
        registerButton.addClickListener(e -> {

            pwd = new String(password.getValue());
            requirement = true;
            afterRegisterClicked();

        
        });    
        div.add(registerButton);
		add(div);
    }

    private void afterRegisterClicked()
    {
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
                
                requirement = true;
                new PasswordChecker().checkPassword();

                licenseChecked = true;
            } 

            if(passwordChecked && licenseChecked && requirement){
                InfluxHandler influx = new InfluxHandler();
                try {
                    if(influx.parseData(influx.getData("mydb"), licensePlate.getValue()).equals("Wrong License Plate")){                            
                        influx.createDB("mydb");
                        influx.createDB("keys");
                        influx.createDB("vehicleType");

                        SecurePasswordHasher secureHasher = SecurePasswordHasher.getInstance();
                        influx.postData(licensePlate.getValue(), vehicleType.getValue().toString(), "vehicleType");
                        influx.postData(licensePlate.getValue(), secureHasher.getHashedPassword(password.getValue()), "mydb");
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
    }

    private void setUpPasswordCheck()
    {
        passReqsNeeded = new Paragraph();
        passwordReqsDisplay = new HorizontalLayout(passReqsNeeded);
        passwordReqsDisplay.setAlignItems(Alignment.CENTER);
        passwordReqsDisplay.getStyle().set("margin-left", "9em");
        passwordReqsDisplay.getStyle().set("color", "red");
    }

    private void setUpPasswordFields()
    {
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
    }

    private void setUpVehicleSelection()
    {
        vehicleType = new ComboBox("Select your vehicle type: ");
        vehicleType.setItems("Motorcycle", "Truck", "SUV", "Electric", "Sedan", "Compact");

        vehicleTypeLayout = new HorizontalLayout(vehicleType);
        vehicleTypeLayout.setAlignItems(Alignment.CENTER);
        vehicleTypeLayout.getStyle().set("margin-left", "9em");
        div.add(vehicleTypeLayout);   
    }

    private void setUpLicenseFields()
    {
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
    }

    private void setUpNotMatch()
    {
        licenseNotMatch = new Paragraph("License Numbers Don't Match");
        passwordNotMatch = new Paragraph("Passwords Don't Match");
        licenseNotMatchLayout = new HorizontalLayout(licenseNotMatch);
        licenseNotMatchLayout.setAlignItems(Alignment.CENTER);
        passwordNotMatchLayout = new HorizontalLayout(passwordNotMatch);
        passwordNotMatchLayout.setAlignItems(Alignment.CENTER);
    }

    //Password Requirement Methods:
    private class PasswordChecker
    {
        private Set<String> commonPasswords;

        public PasswordChecker()
        {
            commonPasswords = new HashSet<>();
            commonPasswords.add("password");
            commonPasswords.add("123456");
            commonPasswords.add("123456789");
            commonPasswords.add("guest");
            commonPasswords.add("qwerty");
            commonPasswords.add("12345678");
            commonPasswords.add("111111");
            commonPasswords.add("12345");
            commonPasswords.add("col123456");
            commonPasswords.add("123123");
            commonPasswords.add("1234567");
            commonPasswords.add("1234");
            commonPasswords.add("1234567890");
            commonPasswords.add("000000");
            commonPasswords.add("555555");
            commonPasswords.add("666666");
            commonPasswords.add("123321");
            commonPasswords.add("654321");
            commonPasswords.add("7777777");
            commonPasswords.add("123");
            commonPasswords.add("D1lakiss");
            commonPasswords.add("777777");
            commonPasswords.add("110110jp");
            commonPasswords.add("1111");
            commonPasswords.add("987654321");
            commonPasswords.add("121212");
            commonPasswords.add("Gizli");
            commonPasswords.add("abc123");
            commonPasswords.add("112233");
            commonPasswords.add("azerty");
            commonPasswords.add("159753");
            commonPasswords.add("1q2w3e4r");
            commonPasswords.add("54321");
            commonPasswords.add("pass@123");
            commonPasswords.add("222222");
            commonPasswords.add("qwertyuiop");
            commonPasswords.add("qwerty123");
            commonPasswords.add("qazwsx");
            commonPasswords.add("vip");
            commonPasswords.add("asdasd");
            commonPasswords.add("123qwe");
            commonPasswords.add("123654");
            commonPasswords.add("iloveyou");
            commonPasswords.add("a1b2c3");
            commonPasswords.add("999999");
            commonPasswords.add("Groupd2013");
            commonPasswords.add("1q2w3e");
            commonPasswords.add("usr");
            commonPasswords.add("Liman1000");
            commonPasswords.add("1111111");
            commonPasswords.add("333333");
            commonPasswords.add("123123123");
            commonPasswords.add("9136668099");
            commonPasswords.add("11111111");
            commonPasswords.add("1qaz2wsx");
            commonPasswords.add("password1");
            commonPasswords.add("mar20lt");
            commonPasswords.add("gfhjkm");
            commonPasswords.add("159357");
            commonPasswords.add("abcd1234");
            commonPasswords.add("131313");
            commonPasswords.add("789456");
            commonPasswords.add("luzit2000");
            commonPasswords.add("aaaaaa");
            commonPasswords.add("zxcvbnm");
            commonPasswords.add("asdfghjkl");
            commonPasswords.add("1234qwer");
            commonPasswords.add("88888888");
            commonPasswords.add("dragon");
            commonPasswords.add("987654");
            commonPasswords.add("888888");
            commonPasswords.add("qwe123");
            commonPasswords.add("football");
            commonPasswords.add("3601");
            commonPasswords.add("asdfgh");
            commonPasswords.add("master");
            commonPasswords.add("samsung");
            commonPasswords.add("12345678910");
            commonPasswords.add("killer");
            commonPasswords.add("1237895");
            commonPasswords.add("1234561");
            commonPasswords.add("12344321");
            commonPasswords.add("daniel");
            commonPasswords.add("hello");
            commonPasswords.add("444444");
            commonPasswords.add("101010");
            commonPasswords.add("qazwsxedc");
            commonPasswords.add("789456123");
            commonPasswords.add("super123");
            commonPasswords.add("qwer1234");
            commonPasswords.add("123456789a");
            commonPasswords.add("823477aA");
            commonPasswords.add("147258369");
            commonPasswords.add("unknown");
            commonPasswords.add("98765");
            commonPasswords.add("q1w2e3r4");
            commonPasswords.add("232323");
            commonPasswords.add("102030");
            commonPasswords.add("12341234");
            commonPasswords.add("147258");
        }
        private void checkPassword()
        {
            if(!checkCommon()) return;
            if(!checkMinCharsReq()) return;
            if(!checkNumExists()) return;
            if(!checkSpecialCharExists()) return;
            if(!checkUpperCaseExists()) return;
            checkLowerCaseExists();
        }

        private boolean checkCommon() 
        {
            try
            {
                if(commonPasswords.contains(pwd))
                    throw new CommonPasswordException("Password is amongst the top 100 commonly used passwords");
                div.remove(passwordReqsDisplay);
            }
            catch(CommonPasswordException commonPasswordWarning)
            {
                requirement = false;
                passReqsNeeded.setText(commonPasswordWarning.getMessage());
                div.add(passwordReqsDisplay);
            }
            return requirement;
        }

        private boolean checkLowerCaseExists()
        {
            try {
                lowercaseCheck();
            } 
            catch (LowerCaseCharacterMissingException lowercaseMissing) {
                requirement = false;
                passReqsNeeded.setText(lowercaseMissing.getMessage());
                div.add(passwordReqsDisplay);
            }
            return requirement;
        }

        private boolean checkUpperCaseExists()
        {
            try {
                uppercaseCheck();
            } 
            catch (UpperCaseCharacterMissingException uppercaseCharMissing) {
                requirement = false;
                passReqsNeeded.setText(uppercaseCharMissing.getMessage());
                div.add(passwordReqsDisplay);
            }
            return requirement;
        }

        private boolean checkSpecialCharExists()
        {
            try {
                specialCheck();
            }
            catch (SpecialCharacterMissingException specialCharacterMissing) {
                requirement = false;
                passReqsNeeded.setText(specialCharacterMissing.getMessage());
                div.add(passwordReqsDisplay);
            }
            return requirement;
        }

        private boolean checkNumExists()
        {
            try {
                numCheck();
            }
            catch (NumberCharacterMissingException numberCharacterMissing) {
                requirement = false;
                passReqsNeeded.setText(numberCharacterMissing.getMessage());
                div.add(passwordReqsDisplay);
            }
            return requirement;
        }

        private boolean checkMinCharsReq()
        {
            try {
                minCheck();
                div.remove(passwordReqsDisplay);
            }     
            catch (Minimum14CharactersRequiredException minimum14CharsMissing) {
                requirement = false;
                passReqsNeeded.setText(minimum14CharsMissing.getMessage());
                div.add(passwordReqsDisplay);
            }
            return requirement;
        }

        private void uppercaseCheck() throws UpperCaseCharacterMissingException {
            for (int i = 0; i < pwd.length(); i++)
                if (Character.isUpperCase(pwd.charAt(i))) 
                    return;
            throw new UpperCaseCharacterMissingException("Missing uppercase characters");        
        }
    
        private void lowercaseCheck() throws LowerCaseCharacterMissingException {
            for (int i = 0; i < pwd.length(); i++)
                if (Character.isLowerCase(pwd.charAt(i)))
                    return;
            throw new LowerCaseCharacterMissingException("Missing lowercase characters");
        }
    
        private void specialCheck() throws SpecialCharacterMissingException {
            for (int i = 0; i < specials.length(); i++)
                if (pwd.contains(Character.toString(specials.charAt(i))))
                    return;
            throw new SpecialCharacterMissingException("Missing special characters");
        }
    
        private void minCheck() throws Minimum14CharactersRequiredException {
            final int MIN_CHARS_REQUIRED = 14;
            if (pwd.length() >= MIN_CHARS_REQUIRED)
                return;
            throw new Minimum14CharactersRequiredException("Need to have at least 14 characters");
        }
    
        private void numCheck() throws NumberCharacterMissingException {
            for (int i = 0; i < pwd.length(); i++)
                if (Character.isDigit(pwd.charAt(i)))
                    return;
            throw new NumberCharacterMissingException("Need to have at least one number");
        }
    }
   
}
