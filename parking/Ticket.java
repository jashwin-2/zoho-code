package parking;

import java.util.Scanner;

public class Ticket 
{
	private String customerName;
	private String vehicleType;
	private String vehicleNo;
    private String spaceName;
    private String entryTime;
    private int reservationCost;
  
    public Ticket(Customer c,String name)
    {
    	this.customerName=c.getName();
    	this.vehicleType=c.getVehicle().getType();
    	this.vehicleNo=c.getVehicle().getNumber();
    	this.spaceName=name;
    	this.setEntryTime();
    }
    
    public String getEntryTime() {
		return entryTime;
	}

	public void setEntryTime() {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the current time (hh:mm:ss)");
		entryTime=sc.nextLine();
	}

	void print()
    {
    	System.out.println("--------------------Ticket-------------");
    	System.out.println("Entry Time   :" +entryTime+"\nName         :"
    				+this.customerName+"\nAlotted Space:"+this.spaceName+"\nVehicle Type :"+this.vehicleType+"\nVehicle No   :"+this.vehicleNo);
    	System.out.println("---------------------------------------\n");
    }

	public String getName() {
		return this.customerName;
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

	public int getReservationCost() {
		return reservationCost;
	}

	public void setReservationCost(int reservationCost) {
		this.reservationCost = reservationCost;
	}
}
