package bank;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class Account
{
	static int id;
	private String accHolderName;
	private int accNo;
	protected int transaction;
	private long mobileNo;
	protected float accBalance;
	protected List<String> passbook=new ArrayList<>();
	protected float interest;
	private int pin;
	protected int transactionFee;
	protected String type;
	public Account(String name,long mobileNo,float balance,int pin)
	{
		this.transaction=0;
		this.accHolderName=name;
		this.mobileNo=mobileNo;
		this.accBalance=balance;
		passbook.add(balance + " initial deposite Current Balance : "+accBalance);
		this.accNo=++id;
		this.pin=pin;
		this.transactionFee=0;
	}

	public void setTransaction(int transaction) {
		this.transaction = transaction;
	}

	void printInfo()
	{
		System.out.println("Transactions completeted in this Month: "+transaction+"\nAccount Type : "+type+"\nAccount holder Name :"
								+this.accHolderName+"\nAcc no:"+this.accNo+"\nBalance :"+this.accBalance);
	}

	public float getAccBalance() {
		return accBalance;
	}

	public String getAccHolderName() {
		return accHolderName;
	}

	public int getAccNo() {
		return accNo;
	}
	protected void editProfile() 
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("What you want to change\n1.Name\n2.Mobile No\n3.Pin");
		int ch = Integer.parseInt(sc.nextLine());
		if(ch==1)
		{
			System.out.println("Enter the new Name");
			this.accHolderName=sc.nextLine();
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
					System.out.println("Enter the new 4 digit Pin");
					this.pin=Integer.parseInt(sc.nextLine());
					break;
				}
				else
					System.out.println("Invalid pin try again you have "+count--+" more attempts");
			}
		}
		this.printInfo();
	}

	public boolean checkPin(int pin) 
	{
		if(pin==this.pin)
			return true;
		return false;
	}
	
	public void receiveMoney(double ammount,String name)
	{
		this.accBalance+=ammount;
		System.out.println("The ammount "+ammount+" added to receiver's accoount succesfully");
		passbook.add(ammount + " received from Acc holder name : "+name+" Current Balance :"+accBalance);
	}

	public void printPassbook()
	{
		System.out.println("*****************Recent Transactions********************");
		for(String str : passbook)
			System.out.println(str);
	}
	
	
	public String getType() {
		return type;
	}

	abstract public void deposite(float ammount);
	abstract public void withdraw(float ammount);
	abstract public boolean transferMoney(float ammount,String name);

	protected abstract void calculateInterest(int parseInt);
	
}
