package sample;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class Group {
	private String groupName;
	private int groupLimit;
	private List<Student> group;
	
	public Group() {
		this.groupLimit = 10;
		this.group= new ArrayList<>();
		
	}
	
	public Group(String groupName, int groupLimit, List<Student> group) {
		super();
		this.groupName = groupName;
		this.groupLimit = groupLimit;
		this.group = group;
	}

	public Group(String groupName, int groupLimit) {
		super();
		this.groupName = groupName;
		this.groupLimit = groupLimit;
		this.group = new ArrayList<>(groupLimit);
	}


	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}


	public int getGroupLimit() {
		return groupLimit;
	}


	public void setGroupLimit(int groupLimit) {
		this.groupLimit = groupLimit;
	}


	public List<Student> getGroup() {
		return group;
	}


	public void setGroup(List<Student> group) {
		this.group = group;
	}


	@Override
	public int hashCode() {
		return Objects.hash(group, groupLimit, groupName);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Group other = (Group) obj;
		return Objects.equals(group, other.group) && groupLimit == other.groupLimit
				&& Objects.equals(groupName, other.groupName);
	}


	@Override
	public String toString() {
		return "Group [groupName=" + groupName + ", groupLimit=" + groupLimit + ", group=" + group + "]";
	}

	public void sortStudentsByLastName() {
		group.sort(new StudentLastNameComparator());
	}
	
	
	
	public void addStudent(Student student) throws GroupOverflowException {
		if(this.group.size() == groupLimit) {
			throw new GroupOverflowException("No free space for a student");
		} else{
			group.add(student);
			System.out.println("The student "+student.getName()+" was added to a group "+this.getGroupName());
		}

	}
	
	public Student searchStudentByLastName(String lastName) throws StudentNotFoundException{
		for(Student s : group) {
			if(s.getLastName().equals(lastName)) {
				return s;
			}
		}
		throw new StudentNotFoundException("No such a student within this group");
	}
	
	public boolean removeStudentByID(int id) {
		for(Student s: group) {
			if(s.getId() == id) {
				group.remove(s.getId());
				return true;
			}
		}
		return false;
	}
	
	public boolean isDuplicatedStudentAddedToCurrentGroup() {
		for(int i = 0; i < group.size(); i++) {
			for(int j = 0; j < group.size(); j++) {
				if(group.get(i).equals(group.get(i)) && i != j) {
					System.out.println(group.get(i).toString()+" with index "+i+" duplicates student with index "+j+" "+group.get(i).toString());
					return true;
				}
			}
		}
		
		return false;
	}


}
