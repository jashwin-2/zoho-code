package parking;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Ticket 
{
	private String name;
	private String vehicleType;
	private String vehicleNo;
    private String spaceName;
    private LocalTime time1;
  
    public Ticket(Customer c,String name)
    {
    	this.name=c.getName();
    	this.vehicleType=c.getVehicle().getType();
    	this.vehicleNo=c.getVehicle().getNumber();
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
    				+this.name+"\nAlotted Space :"+this.spaceName+"\nVehicle Type :"+this.vehicleType+"\nVehicle No:"+this.vehicleNo);
    	System.out.println("---------------------------------------\n");
    }

	public String getName() {
		return name;
	}

	public String getVehicleNo() {
		return vehicleNo;
	}

	public String getSpaceName() {
		return spaceName;
	}

	public String getVehicleType() {
		return vehicleType;
	}
}
