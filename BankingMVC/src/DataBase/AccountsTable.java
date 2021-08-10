package DataBase;

import java.util.ArrayList;
import java.util.List;
import model.AccountModel;
import model.CurrentAccountModel;
import model.SavingAccountModel;



public class AccountsTable 
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


	public AccountModel getAccountModel(int accNo)
	{
		int index=this.accNo.indexOf(accNo);
		if(type.get(index).equals("Saving Account"))
			return  new SavingAccountModel(this.accNo.get(index), this.accHolderName.get(index),mobileNo.get(index) , 
					accBalance.get(index), pin.get(index), passbook.get(index),status.get(index),transactions.get(index));
		else
			return  new CurrentAccountModel(this.accNo.get(index), this.accHolderName.get(index),mobileNo.get(index) , 
					accBalance.get(index), pin.get(index), passbook.get(index),status.get(index),transactions.get(index));
	}

	public void addAccountModel(AccountModel AccountModel)
	{
		accNo.add(AccountModel.getAccNo());
		accHolderName.add(AccountModel.getAccHolderName());
		mobileNo.add(AccountModel.getMobileNo());
		accBalance.add(AccountModel.getAccBalance());
		pin.add(AccountModel.getPin());
		type.add(AccountModel.getType());
		passbook.add(AccountModel.getPassbook());
		status.add(AccountModel.getStatus());
		transactions.add(AccountModel.getTransactions());
	}

	public void update(AccountModel AccountModel)
	{
		int index=accNo.indexOf(AccountModel.getAccNo());
		accNo.set(index,AccountModel.getAccNo());
		accHolderName.set(index,AccountModel.getAccHolderName());
		mobileNo.set(index,AccountModel.getMobileNo());
		accBalance.set(index,AccountModel.getAccBalance());
		pin.set(index,AccountModel.getPin());
		type.set(index,AccountModel.getType());
		passbook.set(index,AccountModel.getPassbook());
		status.set(index,AccountModel.getStatus());
		transactions.set(index,AccountModel.getTransactions());
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

	public boolean containsAccountModel(int accNo)
	{
		return this.accNo.contains(accNo);
		
	}
	
	public void printDetails()
	{
		float total=0;
		System.out.println("Name	AccountModelno	AccountModel Type		balance");
		for(int i=0;i<accNo.size();i++)
		{
			System.out.println(accHolderName.get(i)+"	"+accNo.get(i)+" 		"+type.get(i)+"		"+accBalance.get(i));
			total+=accBalance.get(i);
		}
		System.out.println("---------------------------------------------------------");
		System.out.println("					Total :"+total);
	}

	public List<AccountModel> getBlockList() 
	{
		List<AccountModel> blocked=new ArrayList<>();
		for(int i=0;i<accNo.size();i++)
			if(!status.get(i))
				blocked.add(getAccountModel(accNo.get(i)));
		return blocked;
	}

	public void setStatus(int accNo, boolean status) 
	{
		this.status.set(this.accNo.indexOf(accNo), status);
		
	}

	public List<AccountModel> getAccounts() {
		List<AccountModel> accounts=new ArrayList<>();
		for(int i=0;i<accNo.size();i++)
				accounts.add(getAccountModel(accNo.get(i)));
		return accounts;
	}

	public int getPin(int accNo)
	{
		return pin.get(this.accNo.indexOf(accNo));
	}
}
