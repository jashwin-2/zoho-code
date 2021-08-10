package View;

import java.util.List;
import java.util.Scanner;

import Controller.AccountController;
import model.AccountModel;

public class AccountView 
{
	private AccountController controller;
	public AccountView(AccountController controller)
	{
		this.controller=controller;	
	}
	public void printMenu(AccountModel account)
	{
		int ch;
		boolean choice=true;
		Scanner sc=new Scanner(System.in);
		while(choice){
			ch=0;
			System.out.println("\n************* CUSTOMER MENU ************** \nWELCOME "+account.getAccHolderName()+"\nYour Balance current balance: "
					+account.getAccBalance());
			try {
				System.out.println("Enter your choice \n1.view Profile\n2.Deposite\n3.Withdraw\n4.Edit profile\n5.Calculate interest\n6.Money Transfer to account\n7.Print Passbook\n8.exit");
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
				controller.printdetails(account);
				break;
			}
			case 2:
			{
				System.out.println("Enter the ammount you want to deposite");
				float ammount=Float.parseFloat(sc.nextLine());
				System.out.println(controller.deposite(ammount,account));
				break;
			}
			case 3:
			{
				System.out.println("Enter the ammount you want to Withdraw");
				System.out.println(controller.withdraw(Float.parseFloat(sc.nextLine()),account));
				break;
			}
			case 4:
			{
				controller.editProfile(account);
				break;
			}
			case 5:
			{
				System.out.println("Enter the year you want to calculate the interest");
				int year=Integer.parseInt(sc.nextLine());
				System.out.println("Interest rate for "+account.getType()+" is "+account.getInterest());
				System.out.println("After "+year+" years your balance will be "+controller.calculateInterest(year, account));
				break;
			}
			case 6:
			{
				System.out.println("Enter the recivers Account No");
				int receiverAccountNo=Integer.parseInt(sc.nextLine());
				System.out.println("Enter the ammount you want to transfer");
				float ammount=Float.parseFloat(sc.nextLine());
				System.out.println(controller.sendMoney(ammount, receiverAccountNo, account));
				break;
			}
			case 7:
			{
				controller.printPassbook(account);
				break;
			}
			case 8:
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
	public void printDetails(AccountModel account) 
	{
		System.out.println("\n---------------------Account Details---------------------");
		System.out.println("Transactions completeted in this Month: "+account.getTransactions()+"\nAccount Type : "+account.getType()+"\nAccount holder Name :"
				+account.getAccHolderName()+"\nAcc no:"+account.getAccNo()+"\nBalance :"+account.getAccBalance()+"\nMobileNo :"+account.getMobileNo());
		System.out.println("----------------------------------------------------------");
	}
	public void login() 
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter your Account Number");
		int id=Integer.parseInt(sc.nextLine());
		if(!controller.containsAccount(id))
		{
			System.out.println("Id not found try Again");
			return;
		}
		int count=3;
		do
		{
			System.out.println("Enter your pin");
			if(controller.checkPin(Integer.parseInt(sc.nextLine()),id)) 
			{
				AccountModel acc=controller.getAccount(id);
				if(acc.getStatus()==false)
				{
					System.out.println("\nYour Account has been Blocked Please contact the bank to unblock your account");
					controller.printdetails(acc);
					return;
				}
				printMenu(acc);
				return;
			}
			else if(count!=0)
				System.out.println("\nInvalid Pin Try again you have "+count+" attempts");
			count--;
		}while(count>=0);
		return;

	}
	public int continueWithFee(float afterLimitFee) 
	{
		System.out.println("You crossed your monthly transaction limit\nDo you Want to continue the transaction with a fee of "
				+afterLimitFee+"%\n1.Yes  2.No ");
		Scanner sc=new Scanner(System.in);
		return Integer.parseInt(sc.nextLine());
	}
	public void editProfile(AccountModel obj)
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
				if(controller.checkPin(Integer.parseInt(sc.nextLine()),obj.getAccNo()))
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
	public void printPassBook(List<String> passbook) 
	{
		System.out.println("*****************Recent Transactions********************");
		for(String str : passbook)
			System.out.println(str);
	}
	public boolean enterPin(AccountModel senderAccount) 
	{
		int count=3;
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter your pin number to transfer money ");
		do
		{
			if(controller.checkPin(Integer.parseInt(sc.nextLine()),senderAccount.getAccNo()))
				return true;
			else
				System.out.println("\nInvalid Pin Try again you have "+count+" attempts");
			count--;
		}while(count>=0);
		return false;
	}
	public void print(String string) 
	{
		System.out.println(string);
	}
	public void createAccount()
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the type of Account \n1.Saving Account\n2.Current Account");
		int type=Integer.parseInt(sc.nextLine());
		System.out.println("Enter your name: ");
		String name=sc.nextLine();
		System.out.println("Enter your Mobile No: ");
		long mobileNo=Long.parseLong(sc.nextLine());
		System.out.println("Create a minimum 4 digit Pin");
		int pin;
		while(true)
		{
			pin=Integer.parseInt(sc.nextLine());
			if(String.valueOf(pin).length()<4)
			{
				System.out.println("Invalid input enter atleast 4 numbers");
				continue;
			}break;
		}
		System.out.println("Enter ammount for initial deposit: ");
		float ammount=Float.parseFloat(sc.nextLine());
		controller.createAccount(name, mobileNo, ammount,pin,type);
		}

}
