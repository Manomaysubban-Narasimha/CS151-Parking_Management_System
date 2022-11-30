package com.project.CS151Parking_Management_System;

public class PasswordException extends Exception {
	public PasswordException(String s) {
		super(s);
	}
}

class UpperCaseCharacterMissing extends PasswordException {
	String password;
	public UpperCaseCharacterMissing(String password) {
		super(password);
		this.password = password;
    }
}

class SpecialCharacterMissing extends PasswordException {
    String password;
    public SpecialCharacterMissing(String password) {
        super(password);
        this.password = password;
    }    
}
    
class NumberCharacterMissing extends PasswordException {
    String password;
    public NumberCharacterMissing(String password) {
        super(password);
        this.password = password;
    }
        
}

class Minimum8CharactersRequired extends PasswordException {
    String password;
    public Minimum8CharactersRequired(String password) {
        super(password);
        this.password = password;
    }        
}

class LowerCaseCharacterMissing extends PasswordException {
    String password;
    public LowerCaseCharacterMissing(String password) {
        super(password);
        this.password = password;
    }      
}
