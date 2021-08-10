package View;

import java.util.Scanner;

import Controller.EmployeeController;
import bank.EmployeeMethods;
import model.AccountModel;
import model.EmployeeModel;

public class EmployeeView {
	EmployeeController controller;
	public EmployeeView(EmployeeController employeeController)
	{
		this.controller=employeeController;
	}
	public void Login()
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter your Employee Id");
		String id=sc.nextLine();
		if(!controller.containEmployee(id))
		{
			System.out.println("Id not found try Again");
			return;
		}
		int count1=3;
		EmployeeModel emp=controller.getEmployee(id);
		do
		{
			System.out.println("Enter your pin");
			if(controller.checkPin(emp,Integer.parseInt(sc.nextLine()))) {
				menu(emp);
				break;
			}
			else
				System.out.println("\nInvalid Pin Try again you have "+count1+" attempts");
			count1--;
		}	while(count1>=0);
	}
	public void menu(EmployeeModel employee)
	{
		int ch;
		boolean choice=true;
		Scanner sc=new Scanner(System.in);
		while(choice){
			ch=0;
			try {
				System.out.println("\n************* EMPLOYEE MENU ************** \nWELCOME "+employee.getName());
				System.out.println("Enter your choice \n1.Check in\n2.Check out\n3.Edit profile\n4.search Account\n5.Block/unBlock an account\n6.Print Blocked accounts details\n7.exit");
				System.out.println("*************************************** ");
				ch = Integer.parseInt(sc.nextLine());
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
			switch(ch)
			{
			case 1:
			{
				controller.checkIn(employee);
				System.out.println("Checked In succesfully");
				break;
			}
			case 2:
			{
				controller.checkOut(employee);
				System.out.println("Checked out succesfully");
				break;
			}
			case 3:
			{
				controller.editProfile(employee);
				break;
			}
			case 4:
			{
				System.out.println("Enter the Account no of customer ");
				int accNo=Integer.parseInt(sc.nextLine());
				if(controller.containsAccount(accNo))
					printAccountDetails(controller.searchAccount(accNo));
				else
					System.out.println("Accoun not found");
				break;
			}
			case 5:
			{
				controller.blockAndUnblock();
				break;
			}
			case 6:
			{
				System.out.println("***************Blockd Accounts************\nAccount No		Account holder name");
				for(AccountModel account : controller.getBlockedAccounts())
					System.out.println(account.getAccNo()+" 			"+account.getAccHolderName());
				break;
			}
			case 7:
			{
				choice=false;
				break;
			}
			default :
			{
				System.out.println("Invalid input");
			}
			}
		}
	}
	public void printAccountDetails(AccountModel account) 
	{
		System.out.println("\n---------------------Account Details---------------------");
		System.out.println("Transactions completeted in this Month: "+account.getTransactions()+"\nAccount Type : "+account.getType()+"\nAccount holder Name :"
				+account.getAccHolderName()+"\nAcc no:"+account.getAccNo()+"\nBalance :"+account.getAccBalance()+"\nMobileNo :"+account.getMobileNo());
		System.out.println("----------------------------------------------------------");	
	}
	
	public void editProfile(EmployeeModel obj) 
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("What you want to change\n1.Name\n2.Mobile No\n3.Pin");
		int ch = Integer.parseInt(sc.nextLine());
		if(ch==1)
		{
			System.out.println("Enter the new Name");
			controller.setName(obj,sc.nextLine());
			System.out.println("Name changed Succesfully");
		}
		else if(ch==2)

		{
			System.out.println("Enter the new mobile No");
			controller.setMobileNo(obj,Long.parseLong(sc.nextLine()));
			System.out.println("Mobile no changed Succesfully");
		}
		else
		{
			int count=3;
			while(count>=0)
			{
				System.out.println("Enter your old Pin");
				if(controller.checkPin(obj,Integer.parseInt(sc.nextLine())))
				{
					System.out.println("Enter the new minimum 4 digit Pin");
					int pin=Integer.parseInt(sc.nextLine());
					controller.setPin(obj, pin);
					break;
				}
				else if(count!=0)
					System.out.println("Invalid pin try again you have "+count--+" more attempts");
			}
		}
	}
	public int blockAndUnblock()
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("1.Block 2.Unblock \nEnter your choice");
		int choice=Integer.parseInt(sc.nextLine());
		System.out.println("Enter the Account no of customer ");
		int accNo=Integer.parseInt(sc.nextLine());
		if(!controller.containsAccount(accNo))
		{
			System.out.println("Invalid input account not found");
		}
		else if(choice==1&&!controller.getStatus(accNo)||choice==2&&controller.getStatus(accNo))
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
				controller.setStatus(accNo, false);
				System.out.println("Account blocked successfully");
			}
			else
			{
				controller.setStatus(accNo, true);
				System.out.println("Account unblocked successfully");
			}

		}
		return accNo;
	}

}
