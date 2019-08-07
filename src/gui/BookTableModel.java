package gui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Book;

public class BookTableModel extends AbstractTableModel {

	private List<Book> db;

	private String[] colNames = { "ID", "Ratio", "Author", "Title", "Status", "Reader" };

	public BookTableModel() {

	}

	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return colNames[column];
	}

	public void setData(List<Book> db) {
		this.db = db;
	}

	@Override
	public int getColumnCount() {
		return 6;
	}

	@Override
	public int getRowCount() {
		return db.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		Book book = db.get(row);

		// return the field from book obj
		switch (col) {
		case 0:
			return book.getId();
		case 1:
			return book.getRatio();
		case 2:
			return book.getAuthor();
		case 3:
			return book.getTitle();
		case 4:
			return book.getStatus();
		case 5:
			return book.getReader();
		}
		return null;
	}

}
