package parking;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MultiStoryParking 
{
	private List<ParkingFloors> floors=new ArrayList<ParkingFloors>();


	public MultiStoryParking(int floors,int spaces)
	{
		for(int i=0;i<floors;i++)
			this.floors.add(new ParkingFloors(spaces,(char)(65+i)));
	}
	
	public  ParkingFloors getFloor(char name)
	{
		for(var k : this.floors)
			if(k.getName()==name)
				return k;
		return null;
			
	}
	public void displayAvailable()
	{
		System.out.println("Available spaces in each floor");
		for(ParkingFloors k: this.floors) {
			System.out.println("Floor :"+k.getName());
			k.displayAvailable();
			System.out.println();
		}

	}
	public void choose()
	{
		while(true)
		{
			this.displayAvailable();
			Scanner sc=new Scanner(System.in);
			System.out.println("Enter your chose 1.Entry 2.exit ");
			int choice=Integer.parseInt(sc.nextLine());
			switch(choice)
			{
			case 1:
			{
				boolean booked=false;
				Customer customer=new Customer();
				customer.gerDetails();
				for(ParkingFloors k: floors)
					if(k.getAvailable().get(customer.getVehecileType()).size()!=0) {
						k.alloteSpace(customer);
						booked=true;
						break;
					}
				if(!booked)
					System.out.println("Sorry cant't allot lot is full ");
				break;
			}
			case 2:
			{
				System.out.println("Enter the alloted spacee name");
				String space=sc.nextLine();
				ParkingFloors parkedFloor=this.getFloor(space.charAt(0));
				if(parkedFloor==null)
				{
					System.out.println("Invalid input");
					break;
				}
				parkedFloor.remove(space);
			}

			}

		}



	}
}
