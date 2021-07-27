package bank;

import java.util.List;
import java.util.Scanner;

public class SavingAccount extends Account{

	private int transactionLimit;
	private float afterLimitFee;
	public SavingAccount(String name,long mobileNo,float balance,int pin)
	{
		super(name,mobileNo,balance,pin);
		interest=(float) 4.5;
		afterLimitFee=(float) 1.5;
		type="Saving Account";
		this.transactionLimit=1;
		super.printInfo();
	}
	
	public SavingAccount(int accNo,String name,long mobileNo,float balance,int pin,List<String> passbook,Boolean status, Integer transactions)
	{
		super(accNo,name,mobileNo,balance,pin,passbook,status);
		interest=(float) 4.5;
		afterLimitFee=(float) 1.5;
		type="Saving Account";
		this.transactionLimit=1;
		this.transactions=transactions;
	}

	public void setTransactionLimit(int transactionLimit) {
		this.transactionLimit = transactionLimit;
	}

	@Override
	public void deposite(float ammount) 
	{
		if(ammount>0)
		{
			this.accBalance+=ammount;
			System.out.println("The ammount "+ammount+" added succesfully");
			System.out.println("Current Balance : "+accBalance);
			passbook.add(ammount + " Deposited by You Current Balance :"+accBalance);
		}
		else
		{
			System.out.println("Can not add 0 or negative ammount");
		}

	}

	@Override
	public void withdraw(float ammount) 
	{
		if(transactions<transactionLimit)
		{
			if(ammount>0 && accBalance>=ammount)
			{
				transactions++;
				this.accBalance-=ammount;
				System.out.println("The ammount "+ammount+" withdrawn succesfully");
				System.out.println("Current Balance : "+accBalance);
				passbook.add(ammount + " Withdrawn by You Current Balance :"+accBalance);
			}
			else
			{
				if(ammount<=0)
					System.out.println("Invalid input can not withdraw 0 or negative ammount");	
				else
					System.out.println("only "+accBalance+" in your account Can not withdraw "+ammount);
			}
		}
			else
		{
			System.out.println("You crossed your monthly transaction limit\nDo you Want to continue the transaction with a fee of "
					+afterLimitFee+"%\n1.Yes  2.No ");
			Scanner sc=new Scanner(System.in);
			if(Integer.parseInt(sc.nextLine())==1)
				continueWithFee(ammount);
		}
	}

	@Override
	public boolean transferMoney(float ammount,String name)
	{
		if(!(ammount>0 && accBalance>=ammount))
		{
			if(ammount<=0)
				System.out.println("Invalid input can not withdraw 0 or negative ammount");	
			else
				System.out.println("only "+accBalance+" in your account Can not withdraw "+ammount);
			return false;
		}
		else
		{
			if(transactions<transactionLimit)
			{
				transactions++;
				this.accBalance-=ammount;
				System.out.println("Current Balance : "+accBalance);
				passbook.add(ammount + " Transfered To "+name+" Current Balance :"+accBalance);
				return true;
			}
			else
			{
				System.out.println("You crossed your monthly transaction limit\nDo you Want to continue the transaction with a fee of "
						+afterLimitFee+"%\n1.Yes  2.No ");
				Scanner sc=new Scanner(System.in);
				if(Integer.parseInt(sc.nextLine())==1)
					return continueWithFee(ammount,name);
				else
					return false;
			}
		}
	}
	private boolean continueWithFee(float ammount, String name) 
	{
		transactions++;
		float fee=(float) (ammount*(afterLimitFee/100));
		if(!(accBalance>=ammount+fee))
		{
			System.out.println("You don not have a enough balance total ammount with fee ="+(float)(ammount+fee)+" you only have "+accBalance);
			return false;
		}
		this.accBalance-=(ammount+fee);
		System.out.println(ammount + " Transfered To "+name+" With a fee of "+fee +" Current Balance :"+accBalance);
		passbook.add(ammount + " Transfered To "+name+" With a fee of "+fee +" Current Balance :"+accBalance);
		return true;
	}

	private void continueWithFee(float ammount) 
	{
		transactions++;
		float fee=(ammount*(afterLimitFee/100));
		if(!(accBalance>=ammount+fee))
			System.out.println("You don not have a enough balance total ammount with fee ="+(float)(ammount+fee)+" you only have "+accBalance);
		this.accBalance-=(ammount+fee);
		System.out.println(ammount + " Withdrawn by You with a fee of "+fee+" Current Balance : "+accBalance);
		passbook.add(ammount + " Withdrawn by You with a fee of "+fee+" Current Balance : "+accBalance);

	}

	public void printInfo()
	{
		int remaining=0;
		if((transactionLimit-transactions)>0)
			remaining=(transactionLimit-transactions);
		System.out.println("-------------------Transaction Details-------------------");
		System.out.println("Transaction limit for a month is: "+transactionLimit+"\nRemaining :"+remaining);
		System.out.println("---------------------------------------------------------");
		super.printInfo();
	}
	
	public void calculateInterest(int year)
	{
		float interestAmmount;
		System.out.println("Interest rate for "+type+" is "+interest);
		interestAmmount =((accBalance*interest*year)/100)+accBalance;
		System.out.printf("Account balance after %d years will be %.2f ",year,interestAmmount);
	}


}
