package bank;

import java.util.ArrayList;
import java.util.List;


public class TableAccount 
{
	private List<String> accHolderName=new ArrayList<>();
	private List<Integer> accNo=new ArrayList<>();
	private List<Integer> transactions=new ArrayList<>();
	private List<Long> mobileNo=new ArrayList<>();
	private List<Float> accBalance=new ArrayList<>();
	private List<List<String>> passbook=new ArrayList<>();
	private List<Integer> pin=new ArrayList<>();
	private List<String> type=new ArrayList<>();
	private List<Boolean> status=new ArrayList<>();


	public Account getAccount(int accNo)
	{
		int index=this.accNo.indexOf(accNo);
		if(type.get(index).equals("Saving Account"))
			return  new SavingAccount(this.accNo.get(index), this.accHolderName.get(index),mobileNo.get(index) , 
					accBalance.get(index), pin.get(index), passbook.get(index),status.get(index),transactions.get(index));
		else
			return  new CurrentAccount(this.accNo.get(index), this.accHolderName.get(index),mobileNo.get(index) , 
					accBalance.get(index), pin.get(index), passbook.get(index),status.get(index),transactions.get(index));
	}

	public void addAccount(Account account)
	{
		accNo.add(account.getAccNo());
		accHolderName.add(account.getAccHolderName());
		mobileNo.add(account.getMobileNo());
		accBalance.add(account.getAccBalance());
		pin.add(account.getPin());
		type.add(account.getType());
		passbook.add(account.getPassbook());
		status.add(account.getStatus());
		transactions.add(account.transactions);
	}

	public void update(Account account)
	{
		int index=accNo.indexOf(account.getAccNo());
		accNo.set(index,account.getAccNo());
		accHolderName.set(index,account.getAccHolderName());
		mobileNo.set(index,account.getMobileNo());
		accBalance.set(index,account.getAccBalance());
		pin.set(index,account.getPin());
		type.set(index,account.getType());
		passbook.set(index,account.getPassbook());
		status.set(index,account.getStatus());
		transactions.set(index,account.transactions);
	}

	public void remove(int accNo)
	{
		int index=this.accNo.indexOf(accNo);
		this.accNo.remove(index);
		accHolderName.remove(index);
		mobileNo.remove(index);
		accBalance.remove(index);
		pin.remove(index);
		type.remove(index);
		passbook.remove(index);
		status.remove(index);
		transactions.remove(index);
	}

	public boolean containsAccount(int accNo)
	{
		return this.accNo.contains(accNo);
		
	}
	
	public void printDetails()
	{
		float total=0;
		System.out.println("Name	Accountno	Account Type		balance");
		for(int i=0;i<accNo.size();i++)
		{
			System.out.println(accHolderName.get(i)+"	"+accNo.get(i)+" 		"+type.get(i)+"		"+accBalance.get(i));
			total+=accBalance.get(i);
		}
		System.out.println("---------------------------------------------------------");
		System.out.println("					Total :"+total);
	}

	public List<Account> getBlockList() 
	{
		List<Account> blocked=new ArrayList<>();
		for(int i=0;i<accNo.size();i++)
			if(!status.get(i))
				blocked.add(getAccount(accNo.get(i)));
		return blocked;
	}

	public void setStatus(int accNo, boolean status) 
	{
		this.status.set(this.accNo.indexOf(accNo), status);
		
	}
}