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
	int totalCost,floorTotal;
	long duration;
	int discount;
	boolean coupen;
	Coupen coupenManager;
	SimpleDateFormat timeFormat;
	Scanner sc=new Scanner(System.in);
	public Payment(Coupen coupen)
	{
		floorTotal=0;
		this.coupenManager=coupen;
		costPerHour.put("Car", 10);
		costPerHour.put("Bike", 2);
		costPerHour.put("Bus", 20);
		timeFormat=new SimpleDateFormat("HH:mm:ss");
	}

	public int getFloorTotal() {
		return floorTotal;
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

		while(true)
		{
			System.out.println("Do you have a coupen yes-1 no-0");
			if(Integer.parseInt(sc.nextLine())==1)
			{
				System.out.println("Enter the coupen code");
				if(coupenManager.isValid(Integer.parseInt(sc.nextLine())))
				{
					System.out.println("Coupen Applied succesfully");
					coupen=true;
					break;
				}
				else {
					System.out.println("Invaild code");
				    continue;
				}
			}
			break;
		}
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
		totalCost-=discount;
		floorTotal+=totalCost;
		return print(exitTime,t);			
	}

	public String calculateAmmount(Ticket ticket,int duration)
	{
		totalCost=duration*24*costPerHour.get(ticket.getVehicleType());
		ticket.setReservationCost(totalCost);
		this.print(ticket, duration);
		floorTotal+=totalCost;
		return ticket.getName()+" 	"+ticket.getVehicleNo()+"		 "+ticket.getSpaceName()+"	        "+ticket.getEntryTime()+
				"    "+"Reseved for "+duration+"days     +"+totalCost;
	}

	public String print(String exitTime,Ticket ticket)
	{
		System.out.println("------------------Recipt-----------------");
		System.out.println("\nVehicle Type  : "+ticket.getVehicleType()+"\nExit Time     :"+ exitTime+
				"\nTime duration :"+duration+" Hours \nTotal Cost    :"+(totalCost+discount)+
				"\nDiscount      :"+discount+"\n--------------------"+"\nFinal Cost    :"+totalCost);
		System.out.println("---------------------------------------");
		return ticket.getName()+" 	"+ticket.getVehicleNo()+"		 "+ticket.getSpaceName()+"	        "+ticket.getEntryTime()+
				" 	 "+exitTime+" 	 +"+totalCost;
	}

	public void print(Ticket ticket,int duration)
	{
		System.out.println("------------------Recipt-----------------");
		System.out.println("\nVehicle Type  : "+ticket.getVehicleType()+
				"\nTime duration :"+duration+" days\nPer Day cost  :"+costPerHour.get(ticket.getVehicleType())*24+ "\nTotal Cost    :"+totalCost);
		System.out.println("-----------------------------------------");
	}

	public String calculateRefund(Ticket ticket)
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
		floorTotal-=totalCost;
		return ticket.getName()+" 	"+ticket.getVehicleNo()+"		 "+ticket.getSpaceName()+"	        "+ticket.getEntryTime()+
				"    "+"Canceledat "+exitTime+"  -"+totalCost;
	}

	private void printRefundRecipt(Ticket ticket, long duration2, String exitTime,int durationCost) 
	{
		System.out.println("------------------Refund Recipt-----------------");
		System.out.println("\nVehicle Type  : "+ticket.getVehicleType()+"\nExit Time     :"+ exitTime+
				"\nTime duration :"+duration+" Houres \nTotal Cost    :"+totalCost+
				"\nDuration Cost :"+durationCost+"\n--------------------------"+"\nRefund Ammount   :"+(totalCost-=durationCost));
		System.out.println("------------------------------------------------");

	}
}
