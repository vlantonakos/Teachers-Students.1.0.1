
public class Student {
	private String id;
	private String name;
	private String last_name;
	private int grade;
	
	public Student(String id, String name, String lastName, int grade) {
        this.id = id;
        this.name = name;
        this.last_name = lastName;
        this.grade = grade;
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}
	
	
}
