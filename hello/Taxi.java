package hello;

import java.util.*;
public class Taxi
{
	static int taxicount =0;
	int id;
	boolean booked;
	char currentspot;
	int freetime,totalearnings;
	List<String> trips;
	public Taxi() 
	{
		booked=false;
		currentspot='A';
		totalearnings=0;
		trips=new ArrayList<String>();
		taxicount++;
		id=taxicount;
	}
	public void setdetail(boolean booked,char currentspot,int freetime,int totalearnings,String tripDtail)
	{
		this.booked=booked;
		this.currentspot=currentspot;
		this.totalearnings=totalearnings;
		this.trips.add(tripDtail);
	}
	public void printtaxi()
	{
		 System.out.println("Taxi - "+ this.id + " Total Earnings - " + this.totalearnings);
	        System.out.println("TaxiID    BookingID    CustomerID    From    To    PickupTime    DropTime    Amount");
	        for(String trip : trips)
	        {
	            System.out.println(id + "          " + trip);
	        }
	        System.out.println("--------------------------------------------------------------------------------------");
	}

     
}