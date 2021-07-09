package parking;

public class ParkingManager {
	private ParkingFloors floor;
	private Payment payment;
	public ParkingManager(ParkingFloors floor)
	{
		payment=new Payment();
		this.floor=floor;
	}
	
	public void alloteSpace(Customer customer) 
	{
		ParkingSpace alloted=floor.available.get(customer.getVehicle().getType()).poll();
		alloted.alloteSpace(customer.getVehicle());
		Ticket ticket=new Ticket(customer,alloted.getName());
		ticket.print();
		floor.getAllotedTickets().put(alloted.getName(), ticket);	
	}
	 
	public void  remove(String name)
	{
		String type;
		ParkingSpace allotedSpace;
		Ticket allotedTicket;
		if(!floor.getAllotedTickets().containsKey(name)) {
			System.out.println("Invalid input the space entered is not alloted");
			return;
		}
		
		allotedTicket=floor.getAllotedTickets().get(name);
		type=allotedTicket.getVehicleType();
		allotedSpace=floor.getParkingSpaces().get(type).get(name);
		floor.getTransaction().get(type).add(payment.calculatePayment(allotedTicket));
		floor.available.get(type).add(allotedSpace);
		allotedSpace.remove();
		floor.getAllotedTickets().remove(name);
			
	}

}
