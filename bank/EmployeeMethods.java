package bank;

import java.util.Scanner;

public class EmployeeMethods 
{

	public void logIn(Employee employee)
	{
		int count=3;
		do
		{
			Scanner sc=new Scanner(System.in);
			System.out.println("Enter your 4 digit pin");
			if(employee.checkPin(Integer.parseInt(sc.nextLine()))) {
				menu(employee);
				break;
			}
			else
				System.out.println("\nInvalid Pin Try again you have "+count+" attempts");
			count++;
		}while(count>=0);
	}
	
	public void menu(Employee employee)
	{
		int ch;
		boolean choice=true;
		Scanner sc=new Scanner(System.in);
		while(choice){
			ch=0;
			try {
			System.out.println("\n************* EMPLOYEE MENU ************** \nWELCOME "+employee.name);
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
				employee.setPresent(true);
				System.out.println("Checked In succesfully");
				employee.getTables().updateEmployee(employee);
				break;
			}
			case 2:
			{
				employee.setPresent(false);
				System.out.println("Checked out succesfully");
				employee.getTables().updateEmployee(employee);
				break;
			}
			case 3:
			{
				employee.editProfile();
				employee.getTables().updateEmployee(employee);
				break;
			}
			case 4:
			{
				System.out.println("Enter the Account no of customer ");
				employee.searchAccount(Integer.parseInt(sc.nextLine()));
				break;
			}
			case 5:
			{
				employee.blockAndUnblock();
				break;
			}
			case 6:
			{
				System.out.println("***************Blockd Accounts************\nAccount No		Account holder name");
				for(Account account : employee.getBlockedAccounts())
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

}
