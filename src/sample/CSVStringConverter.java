package sample;

public class CSVStringConverter implements StringConverter{
	
	public CSVStringConverter() {
		
	}

	@Override
	public String toStringRepresentation(Student student) {
		String str = "";
		String separator = ",";
		str = str.concat("name"+separator+"lastName"+separator+"gender"+"\n");
		str = str.concat(student.getName()+separator+student.getLastName()+separator+student.getGender());
		return str;
	}

	@Override
	public Student fromStringRepresentation(String str) {
		Student student = new Student();
		String[] tmpArray = str.split("\n");
		String[] studentData = tmpArray[1].split(",");
		student.setName(studentData[0]);
		student.setLastName(studentData[1]);
		if(studentData[2].equals("MALE")) {
			student.setGender(Gender.MALE);
		}
		if(studentData[2].equals("FEMALE")) {
			student.setGender(Gender.FEMALE);
		}
		return student;
	}

}
