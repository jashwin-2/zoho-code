package Service;
import Controller.AccountController;
import model.AccountModel;

public class SavingAccountService extends AccountService
{


	public SavingAccountService(Dao dao,AccountController controller) 
	{
		super(dao,controller);	
	}

	@Override
	public String deposit(float ammount,AccountModel acc) 
	{
		if(ammount>0)
		{
			acc.setAccBalance(ammount+acc.getAccBalance());
			acc.addInpassBook(ammount + " Deposited by You Current Balance :"+acc.getAccBalance());
			update(acc);
			return "The ammount "+ammount+" added succesfully \nCurrent Balance : "+acc.getAccBalance();
		}
		else
		{
			return "Can not add 0 or negative ammount";
		}

	}

	@Override
	public String withdraw(float ammount,AccountModel acc) 
	{
		if(acc.getTransactions()<acc.getTransactionLimit())
			if(ammount>0 && acc.getAccBalance()>=ammount)
			{
				acc.setTransactions(acc.getTransactions()+1);
				acc.setAccBalance(acc.getAccBalance()-ammount);
				acc.addInpassBook(ammount + " Withdrawn by You Current Balance :"+acc.getAccBalance());
				update(acc);
				return "The ammount "+ammount+" withdrawn succesfully \nCurrent Balance : "+acc.getAccBalance();
			}
			else
			{
				if(ammount<=0)
					return "Invalid input can not withdraw 0 or negative ammount";	
				else
					return "only "+acc.getAccBalance()+" in your account Can not withdraw "+ammount;
			}
		else
			if(controller.continueWithFeeView(acc.getAfterLimitFee())==1)
				return continueWithFee(ammount,acc);
			else
				return "Transaction Cancelled";
	}

	@Override
	public boolean transferMoney(float ammount,String name,AccountModel acc)
	{
		if(!(acc.getAccBalance()>=ammount))
				return false;
		else
			if(acc.getTransactions()<acc.getTransactionLimit())
			{
				acc.setTransactions(acc.getTransactions()+1);
				acc.setAccBalance(ammount-acc.getAccBalance());
				acc.addInpassBook(ammount + " Transfered To "+name+" Current Balance :"+acc.getAccBalance());
				update(acc);
				return true;
			}
			else
				if(controller.continueWithFeeView(acc.getAfterLimitFee())==1)
					return continueWithFee(ammount,name,acc);
				else
					return false;
	}
	private boolean continueWithFee(float ammount, String name, AccountModel acc) 
	{
		float fee=(float) (ammount*(acc.getAfterLimitFee()/100));
		if(!(acc.getAccBalance()>=ammount+fee))
		{
			controller.print("You don not have a enough balance total ammount with fee ="+(float)(ammount+fee)+" you only have "+acc.getAccBalance());
	        return false;	
		}
		acc.setAccBalance(acc.getAccBalance()-(ammount+fee));;
		acc.setTransactions(acc.getTransactions()+1);
		acc.addInpassBook(ammount + " Transfered To "+name+" With a fee of "+fee +" Current Balance :"+acc.getAccBalance());
		controller.print(ammount + " Transfered To "+name+" With a fee of "+fee +" Current Balance :"+acc.getAccBalance());
		update(acc);
		return true;
	}

	private String continueWithFee(float ammount,AccountModel acc) 
	{

		float fee=(ammount*(acc.getAfterLimitFee()/100));
		if(!(acc.getAccBalance()>=ammount+fee))
			return "You don not have a enough balance total ammount with fee ="+(float)(ammount+fee)+" you only have "+acc.getAccBalance();
		acc.setAccBalance(acc.getAccBalance()-(ammount+fee));;
		acc.setTransactions(acc.getTransactions()+1);
		acc.addInpassBook(ammount + " Withdrawn by You with a fee of "+fee+" Current Balance : "+acc.getAccBalance());
		update(acc);
		return ammount + " Withdrawn by You with a fee of "+fee+" Current Balance : "+acc.getAccBalance();

	}

	public float calculateInterest(int year,AccountModel acc)
	{
		float interestAmmount;
		interestAmmount =((acc.getAccBalance()*acc.getInterest()*year)/100)+acc.getAccBalance();
		return interestAmmount;
	}


}
