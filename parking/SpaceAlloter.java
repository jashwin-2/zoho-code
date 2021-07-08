package parking;

public class SpaceAlloter 
{
	private ParkingFloors floor;
	private Payment payment;
	public SpaceAlloter(ParkingFloors floor)
	{
		payment=new Payment();
		this.floor=floor;
	}
	
	public void alloteSpace(Customer customer) 
	{
		ParkingSpace alloted=floor.available.get(customer.getVehecileType()).poll();
		alloted.alloteSpace(customer);
		Ticket ticket=new Ticket(customer,alloted.getName());
		alloted.setTicket(ticket);
		ticket.print();
		floor.getAllotedTickets().put(alloted.getName(), ticket);	
	}
	 
	public void  remove(String name)
	{
		String type;
		ParkingSpace alloted;
		if(!floor.getAllotedTickets().containsKey(name)) {
			System.out.println("Invalid input the space entered is not alloted");
			return;
		}
		type=floor.getAllotedTickets().get(name).getVehicleType();
		alloted=floor.getParkingSpaces().get(type).get(name);
		payment.calculatePayment(alloted.getTicket());
		floor.available.get(type).add(alloted);
		alloted.remove();
		floor.getAllotedTickets().remove(name);
		
		
	}
}
