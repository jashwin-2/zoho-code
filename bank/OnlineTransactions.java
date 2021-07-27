package bank;


import java.util.Scanner;

public class OnlineTransactions 
{
	Tables accounts;
	
	public OnlineTransactions(Tables tables) 
	{
		this.accounts=tables;
	}
	
	public void sendMoney(float ammount,int receiverAccNo,Account senderAccount)
	{
		if(accounts.containsAccount(receiverAccNo))
		{
			Account acc=accounts.getAccount(receiverAccNo);
			int count=3;
			Scanner sc=new Scanner(System.in);
			if(receiverAccNo==senderAccount.getAccNo())
			{
				System.out.println("Can not transfer to a same account enter a valid account number");
				return;
			}
			System.out.println("Enter your pin no to transfer "+ammount+" to Account Holder Name "+acc.getAccHolderName());
			do
			{
				if(senderAccount.checkPin(Integer.parseInt(sc.nextLine()))) {
					if(senderAccount.transferMoney(ammount, acc.getAccHolderName())) 
					{
						acc.receiveMoney(ammount, senderAccount.getAccHolderName());
						accounts.updateAccount(acc);
					}
					break;
				}
				else
					System.out.println("\nInvalid Pin Try again you have "+count+" attempts");
				count--;
			}while(count>=0);
		}
		else
		{
			System.out.println("Invalid Input Can not find the account");
		}
	}
	
	
}
