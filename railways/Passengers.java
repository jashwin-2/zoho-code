package railways;

import java.util.Scanner;

public class Passengers 
{
	
	public int id;
	public int age;
	public String name="";
	public int ticketFee;
	@SuppressWarnings("resource")
	public void getDetails(int i)
	{
		
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter "+ i +" person name");
		this.name=sc.nextLine();
		System.out.println("Enter "+ i +" person age");
		this.age=sc.nextInt();
		
	}
	public void putDetails() 
	{
		System.out.println("age -"+this.age);
		System.out.println("name -"+this.name);
		System.out.println("Fee -"+this.ticketFee+"\n");
		
	}
}
