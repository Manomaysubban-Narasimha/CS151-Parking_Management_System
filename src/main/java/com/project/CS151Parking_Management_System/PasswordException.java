package com.project.CS151Parking_Management_System;

public class PasswordException extends Exception {
	public PasswordException(String message) {
		super(message);
	}
}

class UpperCaseCharacterMissingException extends PasswordException {
	public UpperCaseCharacterMissingException(String message) {
		super(message);
    }
}

class SpecialCharacterMissingException extends PasswordException {
    public SpecialCharacterMissingException(String message) {
        super(message);
    }    
}
    
class NumberCharacterMissingException extends PasswordException {
    public NumberCharacterMissingException(String message) {
        super(message);
    }
}

class Minimum14CharactersRequiredException extends PasswordException {
    public Minimum14CharactersRequiredException(String message) {
        super(message);
    }        
}

class LowerCaseCharacterMissingException extends PasswordException {
    public LowerCaseCharacterMissingException(String message) {
        super(message);
    }      
}

class CommonPasswordException extends PasswordException
{
    public CommonPasswordException(String message)
    {
        super(message);
    }
}
