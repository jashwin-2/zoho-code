package parking;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Ticket 
{
	private Customer customer;
    private String spaceName;
    private LocalTime time1;
  
    public Ticket(Customer c,String name)
    {
    	this.customer=c;
    	this.spaceName=name;
    	this.time1=LocalTime.now();
    }
    
    public LocalTime getTime1() {
		return time1;
	}

	void print()
    {
    	System.out.println("--------------------Ticket-------------");
    	System.out.println("Entry Time :" +time1.truncatedTo(ChronoUnit.SECONDS).format(DateTimeFormatter.ISO_LOCAL_TIME)+"\nName : "
    				+this.customer.getName()+"\nAlotted Space :"+this.spaceName+"\nVehicle Type :"+customer.getVehecileType()+"\nVehicle No:"+customer.getVehecileNo());
    	System.out.println("---------------------------------------\n");
    }

	public String getVehicleType() {
		return customer.getVehecileType();
	}
}
