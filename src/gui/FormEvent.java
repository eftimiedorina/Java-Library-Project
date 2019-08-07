package gui;
import java.util.EventObject;

import model.Database;

public class FormEvent extends EventObject {
	
	// store that information temporarily
	private String ratio;
	private String author;
	private String title;
	private String status;
	private String reader;

	public FormEvent(Object source) {
		super(source);
	}
	
	public FormEvent(Object source,String ratio, String author,String title,String status, String reader) {
		super(source);
		this.ratio = ratio;
		this.author = author;
		this.title = title;
		this.status = status;
		this.reader = reader;
		
	}
	
	public String getRatio() {
		return ratio;
	}

	public void setRatio(String ratio) {
		this.ratio = ratio;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getReader() {
		return reader;
	}

	public void setReader(String reader) {
		this.reader = reader;
	}
	
	

}
