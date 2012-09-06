
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 浅拷贝
 * @author xqh
 *
 */
public class TestClone {
	public static void main(String[] args) {
		Employee1 original = new Employee1("张三", 5000);
		original.setHireDay(2011, 8, 29);
		
		Employee1 copy = (Employee1)original.clone();
		copy.raiseSalary(10);
		copy.setHireDay(2011, 9, 11);
		System.out.println("original = " + original);
		System.out.println("copy = " + copy);
	}
}
class Employee1 implements Cloneable{
	private String name;
	private double salary;
	private Date hireDay;
	
	public Employee1(String name, double salary) {
		this.name = name;
		this.salary = salary;
		hireDay = new Date();
	}
	
	public void setHireDay(int year, int month, int day) {
		Date newHireDay = new GregorianCalendar(year, month - 1, day).getTime();
		hireDay.setTime(newHireDay.getTime());
	}
	
	public void raiseSalary(double byPercent) {
		double raise = salary * byPercent / 100;
		salary += raise;
	}
	
	public Employee1 clone() {
		try {
			return (Employee1) super.clone(); 
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String toString() {
		return "Employee[name" + name + ", salary=" + salary + ", hireDay=" + hireDay +"]";
	}
}
