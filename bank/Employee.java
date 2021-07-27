package bank;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Employee 
{
	static int count;
	protected String name;
	private String empId;
	protected long mobileNo;
	public long getMobileNo() {
		return mobileNo;
	}
	protected int pin;
	protected String designation;
	protected int salary;
	private boolean present;
	protected Tables accounts;
	//private Map<Integer,Account> accounts=new HashMap<>();
	private List<Account> blockedAccounts;
	public Employee(String name,long mobileNo,int pin, Tables accounts)
	{
		this.salary=37000;
		this.accounts=accounts;
		this.present=false;
		this.name=name;
		this.mobileNo=mobileNo;
		this.pin=pin;
		this.designation="Employee";
		this.empId="EMP"+(++count);
		blockedAccounts=accounts.getBlockList();
	}

	public boolean checkPin(int pin)
	{
		if(this.pin==pin)
			return true;
		return false;
	}



	public void searchAccount(int accNo) 
	{
		if(accounts.containsAccount(accNo))
		{
			accounts.getAccount(accNo).printInfo();
			accounts.getAccount(accNo).printPassbook();
		}
			else
			System.out.println("Invalid Account number can not find the account");

	}

	public String getName() {
		return name;
	}

	public void editProfile() 
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("What you want to change\n1.Name\n2.Mobile No\n3.Pin");
		int ch = Integer.parseInt(sc.nextLine());
		if(ch==1)
		{
			System.out.println("Enter the new Name");
			this.name=sc.nextLine();
			System.out.println("Name changed Succesfully");
		}
		else if(ch==2)
		{
			System.out.println("Enter the new mobile No");
			this.mobileNo=Long.parseLong(sc.nextLine());
			System.out.println("Mobile no changed Succesfully");
		}
		else
		{
			int count=3;
			while(count>=0)
			{
				System.out.println("Enter your old Pin");
				if(checkPin(Integer.parseInt(sc.nextLine())))
				{
					System.out.println("Enter the new minimum 4 digit Pin");
					this.pin=Integer.parseInt(sc.nextLine());
					break;
				}
				else
					System.out.println("Invalid pin try again you have "+count--+" more attempts");
			}
		}
		this.printDetails();
	}


	public Tables getAccounts() {
		return accounts;
	}

	void printDetails()
	{
		System.out.println("\n------------------Employee Details-------------");
		System.out.println("Employee Id :"+empId);
		System.out.println("Name :"+name);
		System.out.println("Designation :"+designation);
		System.out.println("Mobile No :"+mobileNo);
		System.out.println("--------------------------------------------------");
	}
	public void blockAndUnblock()
	{
		Scanner sc=new Scanner(System.in);
		blockedAccounts=accounts.getBlockList();
		System.out.println("1.Block 2.Unblock \nEnter your choice");
		int choice=Integer.parseInt(sc.nextLine());
		System.out.println("Enter the Account no of customer ");
		int accNo=Integer.parseInt(sc.nextLine());
		if(!accounts.containsAccount(accNo))
		{
			System.out.println("Invalid input account not found");
		}
		else if(choice==1&&!accounts.getAccount(accNo).getStatus()||choice==2&&accounts.getAccount(accNo).getStatus())
		{
			if(choice==1)
				System.out.println("Account already blocked");
			else
				System.out.println("Account already in active state");
		}
		else
		{
			if(choice==1)
			{
				accounts.setStatus(accNo, false);
				blockedAccounts.add(accounts.getAccount(accNo));
				System.out.println("Account blocked successfully");
			}
			else
			{
				accounts.setStatus(accNo, true);
				blockedAccounts.remove(accounts.getAccount(accNo));
				System.out.println("Account unblocked successfully");
			}
		}
	}

	public List<Account> getBlockedAccounts() {
		return blockedAccounts;
	}

	public boolean isPresent() {
		return present;
	}


	public void setPresent(boolean present) {
		this.present = present;
	}
	public String getEmpId() {
		return empId;
	}

}