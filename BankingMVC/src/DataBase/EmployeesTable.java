package DataBase;

import java.util.ArrayList;
import java.util.List;

import model.EmployeeModel;
import model.ManagerModel;

public class EmployeesTable 
{
	private List<String> name=new ArrayList<>();
	private List<String> empId=new ArrayList<>();
	private List<Long> mobileNo=new ArrayList<>();
	private List<Integer> pin=new ArrayList<>();
	private List<String> designation=new ArrayList<>();
	private List<Boolean> present=new ArrayList<>();
	private List<Integer> salary=new ArrayList<>();

	public void addEmployeeModel(EmployeeModel emp)
	{
		name.add(emp.getName());
		empId.add(emp.getEmpId());
		mobileNo.add(emp.getMobileNo());
		pin.add(emp.getPin());
		designation.add(emp.getDesignation());
		present.add(emp.isPresent());
		salary.add(emp.getSalary());
	}

	public EmployeeModel getEmployeeModel(String empId)
	{
		int index=this.empId.indexOf(empId);
		return new EmployeeModel(this.empId.get(index),name.get(index),mobileNo.get(index),pin.get(index),present.get(index));
	}

	public void update(EmployeeModel emp)
	{
		int index=empId.indexOf(emp.getEmpId());
		name.set(index,emp.getName());
		mobileNo.set(index,emp.getMobileNo());
		pin.set(index,emp.getPin());
		present.set(index,emp.isPresent());
	}

	public boolean containsEmployeeModel(String empId)
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

	public List<EmployeeModel> getEmployeeList()
	{
		List<EmployeeModel> employees=new ArrayList<>();
		for (int i=0;i<empId.size();i++)
		{
			if(this.designation.get(i).equals("Employee"))
				employees.add(getEmployeeModel(empId.get(i)));
			else
				employees.add(getManager());
		}
		return employees;
	}
	
	public ManagerModel getManager()
	{
		int index=this.empId.indexOf("EMP1");
		return new ManagerModel(this.empId.get(index),name.get(index),mobileNo.get(index),pin.get(index),present.get(index));
	}
}
