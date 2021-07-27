package bank;

import java.util.List;

public class Tables 
{
	TableAccount accounts;
	
	public Tables()
	{
		accounts=new TableAccount();
	}
	
	public Account getAccount(int accNo)
	{
		return accounts.getAccount(accNo);
	}
	
	public void addAccount(Account acc)
	{
		accounts.addAccount(acc);
	}
	
	public void updateAccount(Account acc)
	{
		accounts.update(acc);
	}
	
	public boolean containsAccount(int accNo)
	{
		return accounts.containsAccount(accNo);
	}
	
	public void removeAccount(int accNo)
	{
		accounts.remove(accNo);
	}

	public List<Account> getBlockList()
	{
		return accounts.getBlockList();
	}
	
	public void printDetails() 
	{
		accounts.printDetails();
	}
	
	public void setStatus(int accNo,boolean status)
	{
		accounts.setStatus(accNo,status);
	}
}
