package controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import gui.FormEvent;
import model.Book;
import model.Database;
import model.Status;

// for adding stuff to the database

public class Controller {
	Database db = new Database();
	
	// to comunicate with gui
	public List<Book> getBooks(){
		return db.getBooks();
	}
	public void removeBook(int index) {
		db.removeBook(index);
	}
	
	public void addBook(FormEvent ev) {
		String ratio = ev.getRatio();
		String author = ev.getAuthor();
		String title = ev.getTitle();
		String status = ev.getStatus();
		String reader = ev.getReader();
		
		Status st = null;
		
		if(status.equals("SHELF")) {
			st = Status.SHELF;
		} else if(status.equals("OUTSIDE")) {
			st = Status.OUTSIDE;
		} else if(status.equals("BORROWED")) {
			st = Status.BORROWED;
		}
		
		Book book =  new Book(ratio,author,title,st,reader);
		db.addBook(book);
	}
	
	public void saveToFile(File file) throws IOException {
		db.saveToFile(file);
	}
	
	public void loadFromFile(File file) throws IOException {
		db.loadFromFile(file);
	}

}
