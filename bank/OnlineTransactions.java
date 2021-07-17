package bank;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class OnlineTransactions 
{
	Map<Integer, Account> accounts=new HashMap<>();
	
	public OnlineTransactions(Map<Integer, Account> accounts) 
	{
		this.accounts=accounts;
	}
	
	public void sendMoney(float ammount,int receiverAccNo,Account senderAccount)
	{
		if(accounts.containsKey(receiverAccNo))
		{
			Account acc=accounts.get(receiverAccNo);
			int count=3;
			Scanner sc=new Scanner(System.in);
			if(receiverAccNo==senderAccount.getAccNo())
			{
				System.out.println("Can not transfer to a same account enter a valid account number");
				return;
			}
			System.out.println("Enter your pin no to transfer "+ammount+" to Account Holder Name "+acc.getAccHolderName());
			while(count>=0)
			{
				if(senderAccount.checkPin(Integer.parseInt(sc.nextLine()))) {
					if(senderAccount.transferMoney(ammount, acc.getAccHolderName()))
						acc.receiveMoney(ammount, senderAccount.getAccHolderName());
					break;
				}
				else
					System.out.println("\nInvalid Pin Try again you have "+count--+" attempts");
			}
		}
		else
		{
			System.out.println("Invalid Input Can not find the account");
		}
	}
	
	
}
