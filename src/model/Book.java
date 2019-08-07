package model;

import java.io.Serializable;

public class Book implements Serializable{
	
	private static final long serialVersionUID = -5241706179179670833L;
	public static int count = 0;
	private int id;
	private String ratio;
	private String author;
	private String title;
	private Status status;
	private String reader;

	public Book() {
	}

	public Book(String ratio, String author, String title, Status status, String reader) {
		super();
		this.ratio = ratio;
		this.author = author;
		this.title = title;
		this.status = status;
		this.reader = reader;
		
		this.id = count;
		count++;
	}
	

	public Book(int id, String ratio, String author, String title, Status status, String reader) {
		super();
		count = id;
		this.id = count;
		this.ratio = ratio;
		this.author = author;
		this.title = title;
		this.status = status;
		this.reader = reader;
		
		count++;
		
	
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setRatio(String ratio) {
		this.ratio = ratio;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public void setReader(String reader) {
		this.reader = reader;
	}

	public String getRatio() {
		return ratio;
	}

	public String getAuthor() {
		return author;
	}

	public String getTitle() {
		return title;
	}

	public Status getStatus() {
		return status;
	}

	public String getReader() {
		return reader;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", ratio=" + ratio + ", author=" + author + ", title=" + title + ", status=" + status
				+ ", reader=" + reader + "]";
	}
	
}
