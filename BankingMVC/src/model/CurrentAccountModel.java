package model;

import java.util.List;

public class CurrentAccountModel extends AccountModel
{
	public CurrentAccountModel(String name,long mobileNo,float balance,int pin)
	{
		super(name,mobileNo,balance,pin);
		interest=(float) 0;
		transactionFee=2;
		type="Current Account";
		//super.printInfo();
	}

	public CurrentAccountModel(int accNo,String name,long mobileNo,float balance,int pin,List<String> passbook,Boolean status,int transactions)
	{
		super(accNo,name,mobileNo,balance,pin,passbook,status);
		interest=(float) 0;
		transactionFee=2;
		type="Current Account";
		this.transactions=transactions;
	}
	
}
