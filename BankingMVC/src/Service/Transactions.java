package Service;
import Controller.AccountController;
import model.AccountModel;


public class Transactions 
{
	public String sendMoney(float ammount,int receiverAccNo,AccountModel senderAccount,AccountService service,AccountController controller)
	{
		if(ammount<=0)
			return "Invalid input can't transfer negative or 0 ammount";
		if(service.contains(receiverAccNo))
		{
			AccountModel acc=service.getAccount(receiverAccNo);
			if(receiverAccNo==senderAccount.getAccNo())
				return "Can not transfer to a same account enter a valid account number";
			if(controller.enterPin(senderAccount))
				if(service.transferMoney(ammount, acc.getAccHolderName(),senderAccount)) 
				{
					service.receiveMoney(ammount, senderAccount.getAccHolderName(),service.getAccount(receiverAccNo));
					service.update(senderAccount);
					return "Ammount transfered Succesfully";
				}	
				else
					return "Transaction failed";
		}
		return "Invalid Input Can not find the account";
	}

}
