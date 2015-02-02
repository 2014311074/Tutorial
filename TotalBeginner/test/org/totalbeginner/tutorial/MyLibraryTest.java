package org.totalbeginner.tutorial;

import java.util.ArrayList;

import junit.framework.TestCase;

public class MyLibraryTest extends TestCase {

	private Book b1;
	private Book b2;
	private Person p1;
	private Person p2;
	private MyLibrary ml;

	//test constructors
	public void testMyLibrary() {
		MyLibrary ml = new MyLibrary("Test");
		
		assertEquals("Test", ml.name);
		
		assertTrue(ml.books instanceof ArrayList);
		assertTrue(ml.people instanceof ArrayList);
	
	}
	
	public void setup() {
		 b1 = new Book("Book1");
		 b2 = new Book("Book2");
		 
		 p1 = new Person();
		 p2 = new Person();
		 p1.setName("Zain");
		 p2.setName("Asad");
		 
		 ml = new MyLibrary("Test");
		 
		 }
	
	public void testAddBook() {
		//create test objects
		setup();
		
		//test initial size is 0
		assertEquals(0, ml.getBooks().size());
		
		ml.addBook(b1);
		ml.addBook(b2);
		
		assertEquals(2, ml.getBooks().size());
		assertEquals(0, ml.getBooks().indexOf(b1));
		assertEquals(1, ml.getBooks().indexOf(b2));
		
		ml.removeBook(b1);
		assertEquals(1, ml.getBooks().size());
		assertEquals(0, ml.getBooks().indexOf(b2));
		
		ml.removeBook(b2);
		assertEquals(0, ml.getBooks().size());
	}
		public void testCheckout(){
			//set up objects
			setup();
			
			additems();
			
			assertTrue("Book did not check out correctly",ml.checkOut(b1,p1));
			
			assertEquals("Zain", b1.getperson().getName());
			
			assertFalse("Book was already checked out",ml.checkOut(b1,p2));
			
			assertTrue("Book check in failed", ml.checkIn(b1));
			
			assertFalse("Book was already checked in", ml.checkIn(b1));
			
			assertFalse("Book was never checkout", ml.checkIn(b2));
			
			//additional test for max Books
			setup();
			p1.setMaximumBooks(1);
			additems();
			
			assertTrue("First Book did not check out", ml.checkOut(b2,p1));
			
			assertFalse("Second Book not allowed",ml.checkOut(b1,p1));
			
		}

		private void additems() {
			ml.addBook(b1);
			ml.addBook(b2);
			ml.addPerson(p1);
			ml.addPerson(p2);
		}
		
		public void testGetBooksForPerson() {
			setup();
			additems();
			assertEquals(0, ml.getBooksForPerson(p1).size());
			
			ml.checkOut(b1,p1);
			
			ArrayList<Book>testBooks = ml.getBooksForPerson(p1);
			assertEquals(1, testBooks.size());
			assertEquals(0, testBooks.indexOf(b1));
			
			ml.checkOut(b2,p1);
			testBooks = ml.getBooksForPerson(p1);
			assertEquals(2, testBooks.size());
			assertEquals(1, testBooks.indexOf(b2));
		}
		
		public void testGetAvailableBooks() {
			setup();
			additems();
			ArrayList<Book> testBooks  = ml.getAvailableBooks();
			assertEquals(2, testBooks.size());
			assertEquals(1, testBooks.indexOf(b2));
			
			ml.checkOut(b1,p1);
			
			testBooks  = ml.getAvailableBooks();
			
			assertEquals(1, testBooks.size());
			assertEquals(0, testBooks.indexOf(b2));
			
			ml.checkOut(b2,p1);
			testBooks  = ml.getAvailableBooks();
			assertEquals(0, testBooks.size());
			
		}
		
		public void testGetUnavailableBooks() {
			setup();
			additems();
			assertEquals(0, ml.getUnavailableBooks().size());
			
			ml.checkOut(b1,p1);
			
			ArrayList<Book>testBooks = ml.getUnavailableBooks();
			assertEquals(1, testBooks.size());
			assertEquals(0, testBooks.indexOf(b1));
			
			ml.checkOut(b2,p1);
			testBooks = ml.getUnavailableBooks();
			assertEquals(2, testBooks.size());
			assertEquals(1, testBooks.indexOf(b2));
		} 
		
		public void testToString() {
			setup();
			additems();
			assertEquals("Test: 2 books, actual); 2 people. " , ml.toString());
			
		}
		
	}