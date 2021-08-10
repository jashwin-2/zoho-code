package model;

import java.util.List;

import bank.Account;


public class EmployeeModel 
{
	static int count;
	protected String name;
	private String empId;
	protected long mobileNo;
	protected int pin;
	protected String designation;
	protected int salary;
	private boolean present;
	//protected Tables tables;
	
	private List<Account> blockedAccounts;
	
	public EmployeeModel(String name,long mobileNo,int pin)
	{
		this.salary=37000;
		this.present=false;
		this.name=name;
		this.mobileNo=mobileNo;
		this.pin=pin;
		this.designation="Employee";
		this.empId="EMP"+(++count);
	}

	public EmployeeModel(String empId, String name, Long mobileNo, Integer pin,boolean present)
	{
		this.salary=37000;
		this.present=present;
		this.name=name;
		this.mobileNo=mobileNo;
		this.pin=pin;
		this.designation="Employee";
		this.empId=empId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmpId() {
		return empId;
	}

	
	public long getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(long mobileNo) {
		this.mobileNo = mobileNo;
	}

	public int getPin() {
		return pin;
	}

	public void setPin(int pin) {
		this.pin = pin;
	}

	public String getDesignation() {
		return designation;
	}

	
	public int getSalary() {
		return salary;
	}


	public boolean isPresent() {
		return present;
	}

	public void setPresent(boolean present) {
		this.present = present;
	}

	public List<Account> getBlockedAccounts() {
		return blockedAccounts;
	}

	public void setBlockedAccounts(List<Account> blockedAccounts) {
		this.blockedAccounts = blockedAccounts;
	}
	
}
