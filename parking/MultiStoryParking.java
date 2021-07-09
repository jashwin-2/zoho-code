package parking;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MultiStoryParking 
{
	private List<ParkingFloors> floors=new ArrayList<ParkingFloors>();
	private List<String> availableTypes=new ArrayList<>();


	public MultiStoryParking(int floors,int spaces)
	{
		for(int i=0;i<floors;i++)
			this.floors.add(new ParkingFloors(spaces,(char)(65+i)));
		availableTypes.add("Car");
		availableTypes.add("Bike");
		availableTypes.add("Bus");
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
			Scanner sc=new Scanner(System.in);
			System.out.println("Enter your chose 1.Entry 2.exit 3.View Transaction");
			String choice=sc.nextLine();
			switch(choice)
			{
			case "1":
			{
				boolean booked=false;
				this.displayAvailable();
				Customer customer=new Customer();
				customer.gerDetails(availableTypes);
				for(ParkingFloors k: floors)
					if(k.getAvailable().get(customer.getVehicle().getType()).size()!=0) {
						k.alloteSpace(customer);
						booked=true;
						break;
					}
				if(!booked)
					System.out.println("Sorry cant't allot space, "+customer.getVehicle().getType()+" spaces are full ");
				this.displayAvailable();
				break;
			}
			case "2":
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
				break;
			}

			case "3":
			{
				System.out.println("1.All Transactions 2.Vehicle Type Transation");
				int choose=Integer.parseInt(sc.nextLine());
				if(choose==1) {
					for(ParkingFloors k : floors)
					{
						System.out.println("Floor :"+k.getName());
						k.printTransactions();
						System.out.println();
					}
					break;
				}
				else
				{
					System.out.println("Enter the Vehicle type");
					String type=sc.nextLine();
					if(availableTypes.contains(type))
					{
						for(ParkingFloors k : floors)
						{
							System.out.println("Floor :"+k.getName());
							k.printTransactions(type);
							System.out.println();
						}
					}
					else
						System.out.println("Invalid input");
					break;
				}
			}
			default :
			{
				System.out.println("Invalid Input");
				break;
			}
			}

		}



	}
}
