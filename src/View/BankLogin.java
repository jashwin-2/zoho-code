package View;

import java.util.Scanner;
import Controller.*;
import Service.Dao;
import bank.ManagerMethods;
public class BankLogin 
{
	private ManagerController managerController;
	EmployeeController employeeController;
	AccountController accountController;
	public BankLogin(Dao dao)
	{
		this.managerController = new ManagerController(dao);
		employeeController=new EmployeeController(dao);
		accountController=new AccountController(dao);
	}
	public void bankMenu()
	{
		char ch;
		boolean choice=true;
		Scanner sc=new Scanner(System.in);
		while(choice){
			System.out.println("*************WELCOME TO ABC Bank************\n*****************  BANK MENU ************** ");
			System.out.println("Enter your choice \nLogin As\n1.Manager\n2.Employee \n3.Customer\nWant to create account press 4\n5.Exit ");
			System.out.println("*************************************** ");
			ch = sc.nextLine().charAt(0);
			switch (ch) 
			{
			case '1':
			{
				managerController.logIn();
				break;
			}
			case '2':
			{
				employeeController.logIn();
				break;
			}
			case '3':
			{
				accountController.login();
				break;
			}
			case '4':
			{
				
				accountController.createAccount();
				break;
			}
			case '5':
			{
				choice=false;
				break;
			}
			default:
			{
				System.out.println("invalid input");
			}
			}
		}
	}
	public void createManager() 
	{
		 managerController.createManager();
		 Scanner sc=new Scanner(System.in);
			System.out.println("Do you want add employees 1-yes 0-no");
			if(Integer.parseInt(sc.nextLine())==1)
			{
				System.out.println("Enter the count of employees you want to add ");
				int count=Integer.parseInt(sc.nextLine());
				for(int i=1;i<=count;i++)
				{
					System.out.println("\nCreating Employee "+i+" profile");
					managerController.createEmployee();
				}
			}
	}
}
