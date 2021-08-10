package model;

import java.util.List;

public class SavingAccountModel extends AccountModel
{
	public SavingAccountModel(String name,long mobileNo,float balance,int pin)
	{
		super(name,mobileNo,balance,pin);
		interest=(float) 4.5;
		afterLimitFee=(float) 1.5;
		type="Saving Account";
		this.transactionLimit=1;
		//super.printInfo();
	}
	
	public int getTransactionLimit() {
		return transactionLimit;
	}

	public float getAfterLimitFee() {
		return afterLimitFee;
	}

	public SavingAccountModel(int accNo,String name,long mobileNo,float balance,int pin,List<String> passbook,Boolean status, Integer transactions)
	{
		super(accNo,name,mobileNo,balance,pin,passbook,status);
		interest=(float) 4.5;
		afterLimitFee=(float) 1.5;
		type="Saving Account";
		this.transactionLimit=1;
		this.transactions=transactions;
	}
}
