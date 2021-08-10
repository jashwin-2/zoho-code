package Controller;

import Service.AccountService;
import Service.CurrentAccountService;
import Service.Dao;
import Service.SavingAccountService;

public class ServiceFactory 
{

	private Dao dao;

	public ServiceFactory(Dao dao)
	{
		this.dao=dao;
	}

	public AccountService getService(int id,AccountController controller) 
	{
		String type=dao.getAccountType(id);
		if(type.equals("Saving Account"))
			return new SavingAccountService(dao,controller);
		else
			return new CurrentAccountService(dao,controller);
	}

	
}
