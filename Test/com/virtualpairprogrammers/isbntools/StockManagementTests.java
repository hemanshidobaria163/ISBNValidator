package com.virtualpairprogrammers.isbntools;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.internal.verification.NoMoreInteractions;

import static org.mockito.Mockito.*;

public class StockManagementTests {

	ExternalISBNDataService testWebService;
	ExternalISBNDataService testDataService;
	StockManager stockManager;
	
	
	@Before
	public void setUp() {
		ExternalISBNDataService testWebService  = mock(ExternalISBNDataService.class);
		ExternalISBNDataService testDataService  = mock(ExternalISBNDataService.class);

		StockManager stockManager = new StockManager();
		stockManager.setDataService(testDataService);
		stockManager.setWebService(testWebService);
	}
	

	@Test
	public void testCanGetACoreectLocatorCode() {
		
		when(testWebService.lookup(anyString())).thenReturn(new Book("0923464576", "Of Mice and Men", "J Something"));
		
		when(testDataService.lookup(anyString())).thenReturn(null);
		
		String isbn = "0923464576";
		String locatorCode = stockManager.getLocatorCode(isbn);
		assertEquals("4576J4", locatorCode);
	}
	
	@Test
	public void databaseIsUsedIfDataIsPresent() {

		when(testDataService.lookup("0140177396")).thenReturn(new Book("0140177396","abc","abc"));
		
		String isbn = "0140177396";
		String locatorCode = stockManager.getLocatorCode(isbn);
		
		verify(testDataService).lookup("0140177396");
		verify(testWebService, never()).lookup(anyString());
		
	}
	
	@Test
	public void webserviceIsUsedIfDataIsNotPresentInDatabase() {
		
		when(testDataService.lookup("0140177396")).thenReturn(null);
		when(testWebService.lookup("0140177396")).thenReturn(new Book("0140177396","abc","abc"));
		
		String isbn = "0140177396";
		String locatorCode = stockManager.getLocatorCode(isbn);
		
		verify(testDataService).lookup("0140177396");
		verify(testWebService).lookup("0140177396");
	}

}
