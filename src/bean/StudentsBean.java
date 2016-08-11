package bean;

public class StudentsBean implements java.io.Serializable {
	private String firstName = "myName";
	private String lastName = "LastName1";
	private int age = 0;

	public StudentsBean() {
	}

	public StudentsBean(String firstName) {
		this.firstName = firstName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public int getAge() {
		return age;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "StudentsBean [firstName=" + firstName + ", lastName="
				+ lastName + ", age=" + age + "]";
	}

}