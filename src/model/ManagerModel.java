package model;



public class ManagerModel extends EmployeeModel
{
	public ManagerModel(String name,long mobileNo,int pin)
	{
		super(name,mobileNo,pin);
		designation="Manager";
		this.salary=55000;
	}
	
	public ManagerModel(String empId, String name, Long mobileNo, Integer pin,boolean present) 
	{
		super(empId,name,mobileNo,pin,present);
		designation="Manager";
		this.salary=55000;
	}
}
