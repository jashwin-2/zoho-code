package Service;

import Controller.AccountController;
import model.AccountModel;
import model.CurrentAccountModel;
import model.SavingAccountModel;

public abstract class AccountService {

	private Dao dao;
	protected AccountController controller;

	public AccountService(Dao dao,AccountController controller) 
	{
		this.dao=dao;
		this.controller=controller;
	}

	public boolean checkPin(int pin, int accNo)
	{
		return dao.checkPin(pin,accNo);
	}

	public  AccountModel getAccount(int accNo)
	{
		return dao.getAccount(accNo);
	}

	public void receiveMoney(float ammount,String name,AccountModel acc)
	{
		acc.setAccBalance(acc.getAccBalance()+ammount);
		update(acc);
		acc.addInpassBook(ammount + " received from Acc holder name : "+name+" Current Balance :"+acc.getAccBalance());
	}
	public void createAccount(String name, long mobileNo, float ammount, int pin, int type)
	{
		if(type==1)
		{
			AccountModel acc=new SavingAccountModel(name,mobileNo,ammount,pin);
			controller.printdetails(acc);
			dao.addAccountModel(acc);
		}
		else
		{
			AccountModel acc=new CurrentAccountModel(name,mobileNo,ammount,pin);
			controller.printdetails(acc);
			dao.addAccountModel(acc);
		}
	}
	abstract public String deposit(float ammount,AccountModel acc);
	abstract public String withdraw(float ammount,AccountModel acc);
	abstract public boolean transferMoney(float ammount, String name, AccountModel acc);
	abstract public float calculateInterest(int year,AccountModel acc);

	public void update(AccountModel obj) 
	{
		dao.updateAccountModel(obj);

	}

	public boolean contains(int id) 
	{
		return dao.containsAccountModel(id);
	}







}
