package bank;

import java.util.Scanner;

public class CustomerMethods 
{
	OnlineTransactions transaction;
	public CustomerMethods(OnlineTransactions onlineTransaction)
	{
		this.transaction=onlineTransaction;
	}
	public void login(Account acc)
	{
		int count=3;
		Scanner sc=new Scanner(System.in);
		while(count>=0)
		{
			System.out.println("Enter your pin");
			if(acc.checkPin(Integer.parseInt(sc.nextLine()))) {
				if(acc.getStatus()==false)
				{
					System.out.println("\nYour Account has been Blocked Please contact the bank to unblock your account\n");
					acc.printInfo();
					break;
				}
				printMenu(acc);
				break;
			}
			else
				System.out.println("\nInvalid Pin Try again you have "+count--+" attempts");
		}
	}
	public Account create()
	{
		Scanner sc=new Scanner(System.in);
		Account acc;
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
		if(type==1)
			acc=new SavingAccount(name, mobileNo, ammount,pin);
		else
			acc=new CurrentAccount(name, mobileNo, ammount,pin);
		return acc;
	}

	public void printMenu(Account account)
	{
		int ch;
		boolean choice=true;
		Scanner sc=new Scanner(System.in);
		while(choice){
			System.out.println("\n************* CUSTOMER MENU ************** \nWELCOME "+account.getAccHolderName()+"\nYour Balance current balance: "
							+account.getAccBalance());
			System.out.println("Enter your choice \n1.view Profile\n2.Deposite\n3.Withdraw\n4.Edit profile\n5.Calculate interest\n6.Money Transfer to account\n7.Print Passbook\n8.exit");
			System.out.println("*************************************** ");
			ch = Integer.parseInt(sc.nextLine());
			switch(ch)
			{
			case 1:
			{
				account.printInfo();
				break;
			}
			case 2:
			{
				System.out.println("Enter the ammount you want to deposite");
				account.deposite(Float.parseFloat(sc.nextLine()));
				break;
			}
			case 3:
			{
				System.out.println("Enter the ammount you want to Withdraw");
				account.withdraw(Float.parseFloat(sc.nextLine()));
				break;
			}
			case 4:
			{
				account.editProfile();
				break;
			}
			case 5:
			{
				System.out.println("Enter the year you want to calculate the interest");
				account.calculateInterest(Integer.parseInt(sc.nextLine()));
				break;
			}
			case 6:
			{
				System.out.println("Enter the recivers Account No");
				int receiverAccountNo=Integer.parseInt(sc.nextLine());
				System.out.println("Enter the ammount you want to transfer");
				float ammount=Float.parseFloat(sc.nextLine());
				transaction.sendMoney(ammount, receiverAccountNo, account);
				break;
			}
			case 7:
			{
				account.printPassbook();
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
}


