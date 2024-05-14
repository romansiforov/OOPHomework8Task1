package sample;

import java.util.Scanner;

public class StudentScanner {
	private	Student student = new Student();
		
		
		public StudentScanner() {
			
		}

		public Student getStudent() {
			return student;
		}

		public void setStudent(Student student) {
			this.student = student;
		}
		
		private Student initStudent() {
			try (Scanner sc = new Scanner(System.in)) {
				System.out.println("Input student's name");
				student.setName(sc.nextLine());
				System.out.println("Input student's last name");
				student.setLastName(sc.nextLine());
				System.out.println("Select student's gemder: 0 - male, 1 - female");
				switch(sc.nextInt()) {
					case 0:
						student.setGender(Gender.MALE);
						break;
					case 1:
						student.setGender(Gender.FEMALE);
						break;
					default:
						System.out.println("Incorrect input. The gender is not defined");
						break;
				
				}
			}
			return student;
		}
		
		public void addStudentToGroup(Group group) {
			try {
				group.addStudent(initStudent());
			} catch(GroupOverflowException e) {
				e.printStackTrace();
			}
		}

		@Override
		public String toString() {
			return "StudentScanner [student=" + student + "]";
		}
		
		
}
