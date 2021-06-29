package railways;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Train 
{
	HashMap<Integer,Passengers> tickts=new HashMap<Integer,Passengers>();
	public int noOfSeats;
	public char name;
	public int price;
	
	Train(char a)
	{
		this.noOfSeats=50;
		this.price=50;
		this.name=a;
	}
	public int calculate(ArrayList<Passengers> list)
	{
		int ammount=0;
		for(Passengers t : list)
		{
			if(t.age>=10)
			{
				ammount+=this.price;
				t.ticketFee=this.price;
			}
			else
				t.ticketFee=0;
		}
		return ammount;
	}
	public void bookTickets(ArrayList<Passengers> list,int number)
	{
		if(number>this.noOfSeats)
			System.out.println("booking canceledt");
		int i=0;
		for(Passengers t : list)
		{
			while((tickts.containsKey(Integer.valueOf(i))==true))
				i++;
			System.out.println(" booked tickt "+i+" for "+t.name);
			tickts.put(Integer.valueOf(i), t);
			t.id=i;
			i++;
		}
		this.noOfSeats-=number;
		this.price+=(number*20);
		System.out.println("bookd succefully balence seats in - "+this.name+" "+this.noOfSeats+" currnt price "+this.price);
	}
	void showDetails()
	{
		
		for(Map.Entry<Integer,Passengers> entry : tickts.entrySet())
		{
			System.out.println("Ticket no"+entry.getKey());
			entry.getValue().putDetails();
		
		}
		
	}
}
