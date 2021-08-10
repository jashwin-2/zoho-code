package Controller;

import Service.AccountService;
import Service.Dao;
import Service.SavingAccountService;
import Service.Transactions;
import View.AccountView;
import model.AccountModel;

public class AccountController 
{

	private AccountView view;
	private AccountService service;
	private ServiceFactory factory;
	public Transactions transaction;
	public AccountController(Dao dao)
	{
		this.view=new AccountView(this);
		this.factory=new ServiceFactory(dao);
		this.transaction=new Transactions();
		service=new SavingAccountService(dao, this);
	}
	public void printdetails(AccountModel account) 
	{
		view.printDetails(account);
	}
	public String deposite(float ammount,AccountModel acc)
	{
		return service.deposit(ammount,acc);
	}
	
	public String withdraw(float ammount,AccountModel acc)
	{
		return service.withdraw(ammount, acc);
	}
	
	public float calculateInterest(int year,AccountModel acc)
	{
		return service.calculateInterest(year, acc);
	}
	
	public void login()
	{
		view.login();
	}
	public boolean checkPin(int pin,int accNo)
	{
		this.setService(accNo);
		return service.checkPin(pin,accNo);
	}
	public AccountModel getAccount(int accNo) 
	{
		return service.getAccount(accNo);
	}
	public boolean containsAccount(int id) 
	{
		return service.contains(id);
	}
	private void setService(int id)
	{
		service=factory.getService(id,this);
	}
	public int continueWithFeeView(float fee)
	{
		return view.continueWithFee(fee);
	}
	public void setMobileNo(AccountModel obj, long number) 
	{
		obj.setMobileNo(number);
		service.update(obj);
	}
	
	public void setName(AccountModel obj, String name) 
	{
		obj.setAccHolderName(name);
		service.update(obj);
	}
	
	public void setPin(AccountModel obj,int pin) 
	{
		obj.setMobileNo(pin);
		service.update(obj);
	}
	public void editProfile(AccountModel obj)
	{
		view.editProfile(obj);
	}
	public void printPassbook(AccountModel acc) 
	{
		view.printPassBook(acc.getPassbook());
	}
	public String sendMoney(float ammount, int receiverAccountNo, AccountModel account) 
	{
		return transaction.sendMoney(ammount, receiverAccountNo, account, service, this);
	}
	public boolean enterPin(AccountModel senderAccount) 
	{
		return view.enterPin(senderAccount);
	}
	public void print(String string)
	{
		view.print(string);
	}
	public void createAccount() 
	{
		view.createAccount();
	}
	public void createAccount(String name, long mobileNo, float ammount, int pin, int type)
	{
		service.createAccount(name,mobileNo,ammount,pin,type);
	}

}
