package parking;

import java.util.Scanner;

public class Customer {
	private String name;
	private String vehecileType;
	private String vehecileNo;
	public void gerDetails() {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter your name ");
	    this.name=sc.nextLine();
	    System.out.println("Enter your vehicle type ");
	    this.vehecileType=sc.nextLine();
	    System.out.println("Enter your vehicle no ");
	    this.vehecileNo=sc.nextLine();
	}
	public String getName() {
		return name;
	}
	public String getVehecileType() {
		return vehecileType;
	}
	public String getVehecileNo() {
		return vehecileNo;
	}

}
