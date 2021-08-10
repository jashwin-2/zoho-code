package Service;

import java.util.List;

import model.AccountModel;
import model.EmployeeModel;

public class EmployeeService 
{
	Dao dao;
	public EmployeeService(Dao dao)
	{
		this.dao=dao;
	}
	public void update(EmployeeModel obj)
	{
		dao.updateEmployeeModel(obj);
	}
	public AccountModel findAccount(int no) 
	{
		return dao.getAccount(no);
		
	}
	public boolean containsAccount(int accNo)
	{
		return dao.containsAccountModel(accNo);
	}
	public List<AccountModel> getBlockedAccounts() 
	{
	
		return dao.getBlockList();
	}
	public boolean containsEmployee(String empId)
	{
		return dao.containsEmployeeModel(empId);
	}
	public void update(AccountModel acc)
	{
		dao.updateAccountModel(acc);
	}
	public EmployeeModel getEmployee(String id) {
		return dao.getEmployeeModel(id);
	}
}
