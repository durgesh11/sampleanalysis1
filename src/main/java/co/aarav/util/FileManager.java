/**
*
* @author  : Durgesh Mudras
* @Date    : 16-10-2019
* @version : 1.0.0
* 
*/
package co.aarav.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.io.IOUtils;

public class FileManager {

	public void copyDir(File source, File target) throws FileNotFoundException, IOException {
		
		File[] files = source.listFiles();
		File newFile = null;
		target.mkdirs();
		if (files != null) {
			for (int i = 0; i < files.length; i++) {
				newFile = new File(target.getAbsolutePath() + System.getProperty("file.separator") + files[i].getName());
				if (files[i].isDirectory()) {
					copyDir(files[i], newFile);
				}
				else {
					copyFile(files[i], newFile);
				}
			}
		}
	}
	
	public void copyFile(File file, File target) throws FileNotFoundException, IOException {
		
		BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
		BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(target, true));
		int bytes = 0;
		while ((bytes = in.read()) != -1) {
			out.write(bytes);
		}
		in.close();
		out.close();
	}

	public void deleteDir(File dir) {

		File[] files = dir.listFiles();
		if (files != null) {
			for (int i = 0; i < files.length; i++) {
				if (files[i].isDirectory()) {
					deleteDir(files[i]);
				}
				else {
					files[i].delete();
				}
			}
			dir.delete();
		}
	}
	
	public ArrayList<File> searchFile(File dir, String find) {

		File[] files = dir.listFiles();
		ArrayList<File> matches = new ArrayList<File> ();
		if (files != null) {
			for (int i = 0; i < files.length; i++) {
				if (files[i].getName().equalsIgnoreCase(find)) { 
					matches.add(files[i]);
				}
				if (files[i].isDirectory()) {
					matches.addAll(searchFile(files[i], find)); 
				}
			}
		}
		return matches;
	}
	
	public void listDir(File dir) {

		File[] files = dir.listFiles();
		if (files != null) {
			for (int i = 0; i < files.length; i++) {
				System.out.print(files[i].getAbsolutePath());
				if (files[i].isDirectory()) {
					System.out.print(" (directory)\n");
					listDir(files[i]); 
				}
				else {
					System.out.print(" (file)\n");
				}
			}
		}
	}
	
	public long getDirSize(File dir) {
		
		long size = 0;
		File[] files = dir.listFiles();
		if (files != null) {
			for (int i = 0; i < files.length; i++) {
				if (files[i].isDirectory()) {
					size += getDirSize(files[i]);
				}
				else {
					size += files[i].length();
				}
			}
		}
		return size;
	}
	
	public static String readFileAsString(String filePath){
		
		String fileAsString = null; 
		try {
		FileInputStream fisTargetFile = new FileInputStream(filePath);
		fileAsString= IOUtils.toString(fisTargetFile, "UTF-8");
		}
		catch(FileNotFoundException fnfe)
		{
			fnfe.printStackTrace();
		} 
		catch (IOException e) {			
			e.printStackTrace();
		}
		
		
		
		return fileAsString;
	}
}
