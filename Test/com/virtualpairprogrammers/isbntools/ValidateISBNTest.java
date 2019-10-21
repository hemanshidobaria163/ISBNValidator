package com.virtualpairprogrammers.isbntools;

import static org.junit.Assert.*;

import org.junit.Test;

public class ValidateISBNTest {

	@Test
	public void checkAvalid10digitISBN() {
		ValidateISBN validator = new ValidateISBN();
		boolean result = validator.checkISBN("0140449116");
		assertTrue("First value", result);
		result = validator.checkISBN("0140177396");
		assertTrue("Second Value", result);
	}
	@Test
	public void checkAvaild13digitISBN() {
		ValidateISBN validator = new ValidateISBN();
		boolean result = validator.checkISBN("9862376443567");
		assertTrue("First value", result);
		result = validator.checkISBN("9781986260787");
		assertTrue("Second Value", result);
	}
	@Test
	public void TenDigitISBNNumbersEndingInAnXAreValid() {
		ValidateISBN validator = new ValidateISBN();
		boolean result = validator.checkISBN("198626078X");
		assertTrue( result);
		
	}
	
	@Test
	public void checkAnInvalid13digitISBN() {
		ValidateISBN validator = new ValidateISBN();
		boolean result = validator.checkISBN("9781986260780");
		assertFalse(result);
	}
	
	@Test(expected = NumberFormatException.class)
	public void nineDegitISBNAreNotAllowed() {
		ValidateISBN validator = new ValidateISBN();
		validator.checkISBN("123456789");
	}
	@Test(expected = NumberFormatException.class)
	public void nonNumericISBNAreNotAllowed() {
		ValidateISBN validator = new ValidateISBN();
		validator.checkISBN("HelloWorld");
	}
	

}