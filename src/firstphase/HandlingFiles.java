package firstphase;

import java.util.Arrays;
import java.util.Scanner;
import java.io.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.Path;
import java.util.regex.*;

public class HandlingFiles {

	boolean found = false;

	public void findFile(String name, File file) {
		File[] list = file.listFiles();
		if (list != null)
			for (File fil : list) {
				if (fil.isDirectory()) {
					findFile(name, fil);
				} else if (name.equalsIgnoreCase(fil.getName())) {
					System.out.println("File found in " + fil.getParentFile());
					break;
				}

				System.out.println("File retrieved is not the same as " + fil.getName());
			}
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		System.out.println(" WELCOME TO HANDLING FILES APPLICATION ");
		System.out.println("Developer: Tone Ioana Roxana");
		System.out.println("Choose your option from the following menu:");
		System.out.println("1. Return the current filenames in ascending order - enter ASC");
		System.out.println("2. Add a user specified file in the directory list - enter ADD");
		System.out.println("3. Delete a user specified file from the directory list - enter DELETE");
		System.out.println("4. Search a user specified file from the main directory - enter SEARCH");
		System.out.println("5. Close the application -> enter CLOSE");
		System.out.println(" YOUR OPTION IS ");

		Scanner scan = new Scanner(System.in);

		String chosenOption = scan.next();
		System.out.println(chosenOption);

		switch (chosenOption) {
		case "ASC":
			System.out.println(" SORTING THE FILENAMES IN ASCENDING ORDER IN THE USER SPECIFIED DIRECTORY ");
			Scanner readerAsc = new Scanner(System.in);
			boolean successAsc = false;
			System.out.println("Enter path of directory which will be sorted:");
			String dirAsc = readerAsc.nextLine();
			File folder = new File(dirAsc);
			if (folder.isDirectory()) {
				File[] fileList = folder.listFiles();
				Arrays.sort(fileList);
				for (File file : fileList) {
					System.out.println(file.getName());
				}
			}
			readerAsc.close();
			break;
		case "ADD":
			System.out.println(" ADDING A NEW FILE TO A USER SPECIFIED DIRECTORY ");
			Scanner reader = new Scanner(System.in);
			boolean success = false;
			System.out.println("Enter path of directory where the file will be created:");
			String dir = reader.nextLine();

			System.out.println("Enter the file name to be created");
			String filename = reader.nextLine();
			File f = new File(filename);
			if (f.exists()) {
				System.out.println("File already exists in the directory");
			} else {
				System.out.println("File does not exist, creating now");
				success = f.createNewFile();
				if (success) {
					System.out.printf("New file created with success: %s%n", f);
				} else {
					System.out.printf("Failed to create the new file: %s%n", f);
				}
			}
			reader.close();
			break;
		case "DELETE":
			System.out.println(" DELETING A FILE FROM A SPECIFIED DIRECTORY ");
			Scanner reader2 = new Scanner(System.in);
			System.out.println("Enter path of directory from where the file will be deleted:");
			String dir2 = reader2.nextLine();
			System.out.println("Enter file name to be removed");
			String filename2 = reader2.nextLine();
			dir2 = dir2 + "\\" + filename2;
			String path = FileSystems.getDefault().getPath(dir2).toString();
			// Files.delete(path);
			File file = new File(path);
			if (file.exists()) {
				file.delete();
				System.out.println("File removed!");
			} else {
				System.err.println("File cannot be found in '" + file + "' ('" + file.getAbsolutePath() + "')");
			}
			reader2.close();
			break;
		case "SEARCH":
			System.out.println(" SEARCHING FOR A FILE FROM A SPECIFIED DIRECTORY ");
			Scanner reader3 = new Scanner(System.in);
			System.out.println("Enter path of directory where the file will be searched:");
			String dir3 = reader3.nextLine();
			System.out.println("Enter file name which will be searched ");
			String filenameToSearch = reader3.nextLine();
			HandlingFiles ff = new HandlingFiles();
			ff.findFile(filenameToSearch, new File(dir3));
			reader3.close();
			break;
		case "CLOSE":
			System.out.println(" APPLICATION CLOSED ");
			System.exit(0);
			break;
		default:
			System.out.println("ERROR: Selection is not valid");
		}
		scan.close();

	}

}
