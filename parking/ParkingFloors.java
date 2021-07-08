package parking;



import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class ParkingFloors 
{
	private int count;
	private char name;
	private SpaceAlloter alloter;
	private Map<String,Map<String,ParkingSpace>> parkingSpaces=new HashMap<>();
	public Map<String,PriorityQueue<ParkingSpace>> available=new HashMap<>();
	private Map<String,Ticket> allotedTickets=new HashMap<>();
	
	public ParkingFloors(int spaces,char name) 
	{
		this.count=1;
		this.name=name;
		this.alloter=new SpaceAlloter(this);
		
		this.available.put("Car", new PriorityQueue<ParkingSpace>(new MyComp()));
		this.spaceGenerator("Car", (int)(spaces*0.4));
		
		this.available.put("Bike", new PriorityQueue<ParkingSpace>(new MyComp()));
		this.spaceGenerator("Bike", (int)(spaces*0.4));
		
		this.available.put("Bus", new PriorityQueue<ParkingSpace>(new MyComp()));
		this.spaceGenerator("Bus", (int)(spaces*0.2));
	}
	

	public Map<String, PriorityQueue<ParkingSpace>> getAvailable() {
		return available;
	}

	
	public void spaceGenerator(String type,int size)
	{
			Map<String,ParkingSpace> tempMap=new HashMap<>();
			for(int i=0;i<size;i++,count++) { 
			ParkingSpace temp=new ParkingSpace(type,this.name+""+count,count);
			tempMap.put(temp.getName(), temp);
			this.available.get(type).add(temp);
		}
			this.parkingSpaces.put(type, tempMap);
	}
	
	
	void displayAvailable()
	{

		this.available.forEach((k,v)->System.out.println(k+" :"+v.size()));
	}
	
	public char getName() {
		return this.name;
	}

	
	public void alloteSpace(Customer customer) 
	{
		this.alloter.alloteSpace(customer);
	}
	 
	public void  remove(String name)
	{
		this.alloter.remove(name);;
		
	}


	public Map<String, Map<String, ParkingSpace>> getParkingSpaces() {
		return parkingSpaces;
	}


	public Map<String, Ticket> getAllotedTickets() {
		return allotedTickets;
	}


	
}


class MyComp implements Comparator<ParkingSpace>
{
	public int compare(ParkingSpace a,ParkingSpace b)
	{
		if(a.distance<b.distance)
			return -1;
		else
			return 1;
	}
}
