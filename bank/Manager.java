package bank;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;

public class Manager extends Employee
{
	//private Map<String,Employee> employees=new HashMap<>();
	public Manager(String name,long mobileNo,int pin, Tables accounts)
	{
		super(name,mobileNo,pin,accounts);
		designation="Manager";
		this.salary=55000;
	}
	
	public Manager(String empId, String name, Long mobileNo, Integer pin, Tables table,boolean present) 
	{
		super(empId,name,mobileNo,pin,table,present);
		designation="Manager";
		this.salary=55000;
	}
	
	public void searchEmployee(String id) 
	{
		if(tables.containsEmployee(id))
			tables.getEmployee(id).printDetails();
		else
			System.out.println("Invalid Account number can not find the account");

	}
	public void changeManager() 
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("\nCratinng manager profile");
		System.out.println("Enter the Manager name");
		this.name=sc.nextLine();
		System.out.println("Enter the Manager mobile no");
		this.mobileNo=Long.parseLong(sc.nextLine());  
		System.out.println("Create a 4 digit pin");
		int pin;
		while(true)
		{
			pin=Integer.parseInt(sc.nextLine());
			if(String.valueOf(pin).length()<4)
			{
				System.out.println("Invalid input enter atleast 4 numbers");
				continue;
			}
			this.pin=pin;
			break;
		}
		System.out.println("Manager changed successfully");
	}

}

