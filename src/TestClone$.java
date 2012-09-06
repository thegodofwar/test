
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 深拷贝
 * @author xqh
 *
 */
public class TestClone$ {
	public static void main(String[] args) {
		Employee original = new Employee("张三", 5000);
		original.setHireDay(2011, 8, 29);
		
		Employee copy = original.clone();
		copy.raiseSalary(10);
		copy.setHireDay(2011, 9, 11);
		System.out.println("original = " + original);
		System.out.println("copy = " + copy);
	}
}

class Employee implements Cloneable {
	private String name;
	private Double salary;
	private Date hireDay;
	
	public Employee(String name, double salary) {
		this.name = name;
		this.salary = salary;
		hireDay = new Date();
	}
	
	public Employee clone() {
		Employee cloned = null;
		try {
			cloned = (Employee)super.clone();
			cloned.hireDay = (Date)hireDay.clone(); // 实现对hireDay深拷贝
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return cloned;
	}
	
	public void setHireDay(int year, int month, int day) {
		Date newHireDay = new GregorianCalendar(year, month - 1, day).getTime();
		hireDay.setTime(newHireDay.getTime());
	}
	
	public void raiseSalary(double byPercent) {
		double raise = salary * byPercent / 100;
		salary += raise;
	}
	
	public String toString() {
		return "Employee[name" + name + ", salary=" + salary + ", hireDay=" + hireDay +"]";
	}
}