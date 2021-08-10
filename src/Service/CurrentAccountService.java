package Service;

import Controller.AccountController;
import model.AccountModel;

public class CurrentAccountService extends AccountService
{

	public CurrentAccountService(Dao dao,AccountController controller) 
	{
		super(dao,controller);

	}

	@Override
	public String deposit(float ammount, AccountModel acc) 
	{
		if(ammount>0)
		{
			acc.setAccBalance(ammount+acc.getAccBalance()-acc.getTransactionFee());
			acc.addInpassBook(ammount + " Deposited by You TransactionFee "
					+acc.getTransactionFee()+" Debitted from your Account CurrentBalance : "+acc.getAccBalance());
			update(acc);
			return "The ammount "+ammount+" added succesfully \nCurrent Balance : "+acc.getAccBalance()+" TransactionFee "
			+acc.getTransactionFee()+" Debitted from your Account";
		}
		else
		{
			return "Can not add 0 or negative ammount";
		}
	}

	@Override
	public String withdraw(float ammount, AccountModel acc) 
	{
		if(ammount>0 && acc.getAccBalance()>=ammount)
		{
			acc.setTransactions(acc.getTransactions()+1);
			acc.setAccBalance(acc.getAccBalance()-ammount-acc.getTransactionFee());
			acc.addInpassBook(ammount + " Withdrawn by You TransactionFee "
					+acc.getTransactionFee()+" Debitted from your Account  CurrentBalance : "+acc.getAccBalance());
			update(acc);
			return "The ammount "+ammount+" withdrawn succesfully \nCurrent Balance : "+acc.getAccBalance()+" TransactionFee "
			+acc.getTransactionFee()+" Debitted from your Account";
		}
		else
			if(ammount<=0)
				return "Invalid input can not withdraw 0 or negative ammount";	
			else
				return "only "+acc.getAccBalance()+" in your account Can not withdraw "+ammount;
	}

	@Override
	public boolean transferMoney(float ammount, String name, AccountModel acc)
	{
		if(!(acc.getAccBalance()>=ammount))
				return false;
		else 
		{
			acc.setTransactions(acc.getTransactions()+1);
			acc.setAccBalance(acc.getAccBalance()-ammount-acc.getTransactionFee());
			acc.addInpassBook(ammount + " Transfered To "+name+" TransactionFee "
					+acc.getTransactionFee()+" Debitted from your Account  CurrentBalance : "+acc.getAccBalance());
			update(acc);
			return true;
		}
	}

	@Override
	public float calculateInterest(int year, AccountModel acc) 
	{
		return acc.getAccBalance();
	}


}
