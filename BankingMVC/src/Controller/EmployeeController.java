package Controller;
import java.util.List;

import Service.Dao;
import Service.EmployeeService;
import View.EmployeeView;
import model.AccountModel;
import model.EmployeeModel;

public class EmployeeController 
{
	EmployeeView view;
	EmployeeService service;
	public EmployeeController(Dao dao)
	{
		view =new EmployeeView(this); 
		service=new EmployeeService(dao);
	}
	public void checkIn(EmployeeModel obj) 
	{
		obj.setPresent(true);
		service.update(obj);
	}
	
	public void checkOut(EmployeeModel obj) 
	{
		obj.setPresent(false);
		service.update(obj);
	}
	public boolean checkPin(EmployeeModel obj, int pin)
	{
		return obj.getPin()==pin;
	}
	public void setMobileNo(EmployeeModel obj, long number) 
	{
		obj.setMobileNo(number);
		service.update(obj);
	}
	
	public void setName(EmployeeModel obj, String name) 
	{
		obj.setName(name);
		service.update(obj);
	}
	
	public void setPin(EmployeeModel obj,int pin) 
	{
		obj.setMobileNo(pin);
		service.update(obj);
	}
	public void editProfile(EmployeeModel obj)
	{
		view.editProfile(obj);
	}
	public AccountModel searchAccount(int no)
	{
		return service.findAccount(no);
	}
	public void blockAndUnblock()
	{
		view.blockAndUnblock();
	}
	public boolean containEmployee(String empId)
	{
		return service.containsEmployee(empId);
	}
	public boolean containsAccount(int accNo) 
	{
		return service.containsAccount(accNo);
	}
	public boolean getStatus(int accNo)
	{
		AccountModel acc=service.findAccount(accNo);
		return acc.getStatus();
	}
	public void setStatus(int accNo,boolean cho)
	{
		AccountModel acc=service.findAccount(accNo);
		acc.setStatus(cho);
		service.update(acc);
	}
	public List<AccountModel> getBlockedAccounts() 
	{
		return service.getBlockedAccounts();
	}
	public EmployeeModel getEmployee(String id) {
		return service.getEmployee(id);
	}
	public void logIn() 
	{
		view.Login();
	}	
}
