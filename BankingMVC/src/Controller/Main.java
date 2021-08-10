package Controller;
import Service.Dao;
import View.BankLogin;




public class Main {

	public static void main(String[] args) {
		Dao dao=Dao.getInstance();
		BankLogin bank=new BankLogin(dao);
		bank.createManager();
		bank.bankMenu();
	}

}
