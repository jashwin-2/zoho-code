package bank;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in); 
		System.out.println("Enter the bank name");
		String name=sc.nextLine();
		Bank bank=new Bank(name);
		bank.bankMenu();
	}

}
