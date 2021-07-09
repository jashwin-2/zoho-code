package parking;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

public class Payment 
{
	Map<String,Integer> costPerHour=new HashMap<>();
	
	
	public Payment()
	{
		this.costPerHour.put("Car", 10);
		this.costPerHour.put("Bike", 2);
		this.costPerHour.put("Bus", 20);
	}
	
	public String calculatePayment(Ticket t)
	{
		int costPerHour=this.costPerHour.get(t.getVehicleType());
		int totalCost;
		long duration;
		LocalTime time1=t.getTime1();
		LocalTime time2=LocalTime.now();
		duration=ChronoUnit.SECONDS.between(time1, time2);
		if(duration>10)
		{
			totalCost=10*costPerHour;
			totalCost+=(duration-10)*(costPerHour/2);
		}
		else
			totalCost=(int)duration*10;
		return print(totalCost,duration,time2,t);			
	}
	
	public String print( int cost,long duration,LocalTime time2,Ticket ticket)
	{
		System.out.println("------------------Recipt-----------------");
		System.out.println("\nVehicle Type : "+ticket.getVehicleType()+"\nExit Time: "+ time2.truncatedTo(ChronoUnit.SECONDS).format(DateTimeFormatter.ISO_LOCAL_TIME)+
						"\nTime duration :"+duration+"secoonds \nCost :"+cost);
		System.out.println("---------------------------------------");
		return ticket.getName()+" 	"+ticket.getVehicleNo()+"		 "+ticket.getSpaceName()+"	        "+ticket.getTime1().truncatedTo(ChronoUnit.SECONDS).format(DateTimeFormatter.ISO_LOCAL_TIME)+
				" 	 "+time2.truncatedTo(ChronoUnit.SECONDS).format(DateTimeFormatter.ISO_LOCAL_TIME)+" 	 "+cost;
	}
	
	 
}
