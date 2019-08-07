package gui;

public class Utils {
	
	public static String getFileExtention(String name) {
		
		int pointIndex = name.lastIndexOf(".");
		
		// didn't find the dot
		if(pointIndex == -1) {
			return null;
		}
		
		// dot is write the end of the string (no file extentions)
		if(pointIndex == name.length()-1) {
			return null;
		}
		
		// first character of a string
		return name.substring(pointIndex+1,name.length());
	}

}
