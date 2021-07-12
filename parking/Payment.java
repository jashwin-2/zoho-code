package parking;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;



public class Payment 
{
	Map<String,Integer> costPerHour=new HashMap<>();
	int totalCost;
	long duration;
	int discount;
	boolean coupen;
	SimpleDateFormat timeFormat;
	Scanner sc=new Scanner(System.in);
	public Payment()
	{
		costPerHour.put("Car", 10);
		costPerHour.put("Bike", 2);
		costPerHour.put("Bus", 20);
		timeFormat=new SimpleDateFormat("HH:mm:ss");
	}

	public String calculateAmmount(Ticket t)
	{
		int costPerHour=this.costPerHour.get(t.getVehicleType());
		String exitTime = null;
		discount=0;
		coupen=false;
		totalCost=0;
		Date time1,time2=null;
		try {
			time1 = timeFormat.parse(t.getEntryTime());
			System.out.println("Enter the current time (hh:mm:ss)");
			exitTime=sc.nextLine();
			time2=timeFormat.parse(exitTime);
			duration=Math.abs(time1.getTime()-time2.getTime());
			duration=(duration/(60*60*1000))%24;
		}
		catch (ParseException e) {
			e.printStackTrace();
		}

		System.out.println("Do you have a coupen yes-1 no-0");
		if(Integer.parseInt(sc.nextLine())==1)
			coupen=true;

		if(duration>10)
		{
			totalCost=10*costPerHour;
			totalCost+=(duration-10)*(costPerHour/2);
			if(coupen) {
				discount=(int)((10*costPerHour)*0.5+(totalCost-(10*costPerHour))*0.10);

			}
		}
		else 
		{
			totalCost=(int)duration*costPerHour;
			if(coupen)
				discount=(int)(totalCost*0.5);
		}
		return print(exitTime,t);			
	}

	public String calculateAmmount(Ticket ticket,int duration)
	{
		totalCost=duration*24*costPerHour.get(ticket.getVehicleType());
		ticket.setReservationCost(totalCost);
		this.print(ticket, duration);
		return ticket.getName()+" 	"+ticket.getVehicleNo()+"		 "+ticket.getSpaceName()+"	        "+ticket.getEntryTime()+
				"       "+"Reseved for "+duration+"days "+totalCost;
	}

	public String print(String exitTime,Ticket ticket)
	{
		System.out.println("------------------Recipt-----------------");
		System.out.println("\nVehicle Type  : "+ticket.getVehicleType()+"\nExit Time     :"+ exitTime+
				"\nTime duration :"+duration+" Hours \nTotal Cost    :"+totalCost+
				"\nDiscount      :"+discount+"\n--------------------"+"\nFinal Cost    :"+(totalCost-=discount));
		System.out.println("---------------------------------------");
		return ticket.getName()+" 	"+ticket.getVehicleNo()+"		 "+ticket.getSpaceName()+"	        "+ticket.getEntryTime()+
				" 	 "+exitTime+" 	 "+totalCost;
	}

	public void print(Ticket ticket,int duration)
	{
		System.out.println("------------------Recipt-----------------");
		System.out.println("\nVehicle Type  : "+ticket.getVehicleType()+
				"\nTime duration :"+duration+" days\nPer Day cost  :"+costPerHour.get(ticket.getVehicleType())*24+ "\nTotal Cost    :"+totalCost);
		System.out.println("-----------------------------------------");
	}

	public void calculateRefund(Ticket ticket)
	{
		String exitTime=null;
		Date entry = null,exit = null;
		System.out.println("Enter the current time (hh:mm:ss)");
		exitTime=sc.nextLine();
		try {
		exit=timeFormat.parse(exitTime);
		entry=timeFormat.parse( ticket.getEntryTime());
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
		duration=Math.abs(entry.getTime()-exit.getTime());
		duration=(duration/(60*60*1000))%24;
		int durationCost=(int) (duration*costPerHour.get(ticket.getVehicleType()));
		this.printRefundRecipt(ticket,duration,exitTime,durationCost);
	}

	private void printRefundRecipt(Ticket ticket, long duration2, String exitTime,int durationCost) 
	{
		System.out.println("------------------Refund Recipt-----------------");
		System.out.println("\nVehicle Type  : "+ticket.getVehicleType()+"\nExit Time     :"+ exitTime+
				"\nTime duration :"+duration+" Houres \nTotal Cost    :"+totalCost+
				"\nDuration Cost :"+durationCost+"\n--------------------------"+"\nRefund Ammount   :"+(totalCost-durationCost));
		System.out.println("------------------------------------------------");

	}
}
