package bank;

public class CurrentAccount extends Account 
{
	public CurrentAccount(String name,long mobileNo,float balance,int pin)
	{
		super(name,mobileNo,balance,pin);
		interest=(float) 0;
		transactionFee=2;
		type="Current Account";
		super.printInfo();
	}

	@Override
	public void deposite(float ammount) 
	{
		if(ammount>0)
		{
			this.accBalance+=ammount;
			System.out.println("The ammount "+ammount+" added succesfully And Transaction Fee "+transactionFee+
					" debited from  your account");
			accBalance-=transactionFee;
			passbook.add(ammount + " Deposited by You with a Transaction Fee :"+transactionFee+" Current Balance :"+accBalance);
			System.out.println("Current Balance : "+accBalance);
		}
		else
		{
			System.out.println("Can not add 0 or negative ammount");
		}

	}

	@Override
	public void withdraw(float ammount) 
	{
		if(ammount>0 && accBalance>=ammount)
		{
			if(accBalance>=(ammount+transactionFee))
			{
			transaction++;
			this.accBalance-=(ammount+transactionFee);
			System.out.println("The ammount "+ammount+" withdrawn succesfully And Transaction Fee "+transactionFee
					+" debited from  your account");
			passbook.add(ammount + " WithDrawn by You wiht a Transaction Fee :"+transactionFee+" Current Balance :"+accBalance);
			System.out.println("Current Balance : "+accBalance);
			}
			else
				System.out.println("only "+accBalance+" in your account Can not withdraw ammount "+(ammount+transactionFee)+" with fee of "+transactionFee);
		}
		else
		{
			if(ammount<=0)
				System.out.println("Invalid input can not withdraw 0 or negative ammount");	
			else
				System.out.println("only "+accBalance+" in your account Can not withdraw "+ammount);
		}
	}
	public boolean transferMoney(float ammount,String name)
	{
		if(ammount>0 && accBalance>=ammount)
		{
			transaction++;
			this.accBalance-=ammount;
			accBalance-=transactionFee;
			passbook.add(ammount + " Transfered to "+name+" with a Transaction Fee :"+transactionFee+" Current Balance :"+accBalance);
			System.out.println(ammount + " Transfered to "+name+" with a Transaction Fee :"+transactionFee+" Current Balance :"+accBalance);
			return true;
		}
		else
		{
			if(ammount<=0)
				System.out.println("Invalid input can not withdraw 0 or negative ammount");	
			else
				System.out.println("only "+accBalance+" in your account Can not withdraw "+ammount);
			return false;
		}

	}
	public void calculateInterest(int year)
	{
		System.out.println("Interest rate for "+type+" is "+interest+" so the ammount will be same");
	}

}
