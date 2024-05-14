package sample;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;

public class GroupFileStorage {
	public static void saveGroupToCSV(Group group) throws IOException{
		List<Student> students = group.getGroup();
		String separator = ",";
		File groupFile = new File(group.getGroupName()+".csv");
		
		groupFile.createNewFile();
		
		try(PrintWriter pw = new PrintWriter(groupFile)){
			pw.println("name"+separator+"lastName"+separator+"gender");
			for(int i = 0; i < students.size(); i++) {
					pw.println(students.get(i).getName()+separator+students.get(i).getLastName()+separator+students.get(i).getGender());

			}
		}

	}
	
	public static Gender defineGender(String stringGender) {
		if(stringGender.equals("MALE")) {
			return Gender.MALE;
		}
		if(stringGender.equals("FEMALE")) {
			return Gender.FEMALE;
		}
		return null;
	}
	
	public static Group loadGroupFromCSV(File file) throws IOException{
		Group group = new Group();
		group.setGroupName(file.getName().replace(".csv", ""));
		
		String tmpLine = "";
		String[] studentData;
		
		try(Scanner sc = new Scanner(file)){
			
			for(int i = 0; sc.hasNextLine(); i++) {
				if(i == 0) {
					tmpLine = sc.nextLine()+System.lineSeparator();
					tmpLine = "";
				}
				tmpLine = sc.nextLine()+System.lineSeparator();
				studentData = tmpLine.split(",");
				try {
					group.addStudent(new Student(studentData[0], studentData[1], defineGender(studentData[2].replace("\n", ""))));
				} catch (GroupOverflowException e) {
					e.printStackTrace();
				}
			}
		}
		
		return group;
	}
	
	public static File findFileByGroupName(String groupName, File workFolder) throws IOException {
		File[] files = workFolder.listFiles();
		String extension = ".csv";
		
		for(int i = 0; i < files.length; i++) {
			if(files[i].getName().equals(groupName+extension)) {
				return files[i];
			}
		}
		throw new FileNotFoundException("File "+groupName+" not found");
	}
}
