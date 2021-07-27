package bank;

import java.util.ArrayList;
import java.util.List;

public class TableEmployee 
{
	private List<String> name=new ArrayList<>();
	private List<String> empId=new ArrayList<>();
	private List<Long> mobileNo=new ArrayList<>();
	private List<Integer> pin=new ArrayList<>();
	private List<String> designation=new ArrayList<>();
	private List<Boolean> present=new ArrayList<>();
	private List<Integer> salary=new ArrayList<>();

	public void addEmployee(Employee emp)
	{
		name.add(emp.getName());
		empId.add(emp.getEmpId());
		mobileNo.add(emp.getMobileNo());
		pin.add(emp.getPin());
		designation.add(emp.getDesignation());
		present.add(emp.isPresent());
		salary.add(emp.getSalary());
	}

	public Employee getEmployee(String empId,Tables table)
	{
		int index=this.empId.indexOf(empId);
		System.out.println(empId+" "+index+" "+name.get(index));
		return new Employee(this.empId.get(index),name.get(index),mobileNo.get(index),pin.get(index),table,present.get(index));
	}

	public void update(Employee emp)
	{
		int index=empId.indexOf(emp.getEmpId());
		name.set(index,emp.getName());
		mobileNo.set(index,emp.getMobileNo());
		pin.set(index,emp.getPin());
		present.set(index,emp.isPresent());
	}

	public boolean containsEmployee(String empId)
	{
		return this.empId.contains(empId);
	}

	public void remove(String empId) 
	{
		int index=this.empId.indexOf(empId);
		name.remove(index);
		this.empId.remove(index);
		mobileNo.remove(index);
		pin.remove(index);
		designation.remove(index);
		present.remove(index);

	}

	public void printAttendende()
	{
		System.out.println("Employee Id	   Name		Status");
		for (int i=0;i<empId.size();i++)
		{

			System.out.print(empId.get(i)+"	           "+name.get(i)+"		");
			if(present.get(i)==true)
				System.out.println("Present");
			else
				System.out.println("Absent");
		}
	}
	public void printDetails() 
	{
		System.out.println("Name	Employee Id		Designation		Salary		Mobile No");
		for(int i=0;i<empId.size();i++)
		{
			System.out.println(name.get(i)+"	"+empId.get(i)+" 			"+designation.get(i)+" 		"+salary.get(i)
					+"		"+mobileNo.get(i));
		}
	}

	public Manager getManager(Tables tables)
	{
		int index=empId.indexOf("EMP1");
			return new Manager(this.empId.get(index),name.get(index),mobileNo.get(index),pin.get(index),tables,present.get(index));
	}
}
