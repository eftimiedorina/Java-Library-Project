package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import utils.CryptoException;

public class Database {

	private List<Book> listBook;
	private int newId;

	private static final String password = "dorinaelena";

	public Database() {
		listBook = new LinkedList<Book>();

	}

	public void addBook(Book book) {
		listBook.add(book);
	}

	public void removeBook(int index) {
		listBook.remove(index);
	}

	public List<Book> getBooks() {
		return Collections.unmodifiableList(listBook);
	}

	// save and load from listBook to obj seralization
	public void saveToFile(File file) throws IOException {

		FileOutputStream fos = new FileOutputStream(file);
		ObjectOutputStream oos = new ObjectOutputStream(fos);

		Book[] books = listBook.toArray(new Book[listBook.size()]);

		oos.writeObject(books);

		oos.close();

	}

	public void loadFromFile(File file) throws IOException {
		FileInputStream fis = new FileInputStream(file);
		ObjectInputStream ois = new ObjectInputStream(fis);

		try {
			// convert books to array
			Book[] books = (Book[]) ois.readObject();

			listBook.clear();

			listBook.addAll(Arrays.asList(books));
			newId = books[books.length - 1].getId() + 1;
			Book.count = newId;
			System.out.println(listBook);

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
