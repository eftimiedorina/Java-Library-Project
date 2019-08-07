package gui;

import java.io.File;

import javax.swing.filechooser.FileFilter;

// create my own filter
public class BookFileFilter extends FileFilter {

	@Override
	public boolean accept(File file) {
		
		String name = file.getName();
		
		// check the extention
		String extention = Utils.getFileExtention(name);
		
		if(extention == null) {
			return false;
		}
		
		if(extention.equals("bok")) {
			return true;
		}
		return false;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "Book databaes files (*.bok)";
	}

}
