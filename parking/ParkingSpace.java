package parking;

public class ParkingSpace 
{
	private String type,name;
	private Ticket ticket;
	public int distance;
	boolean free;
	public ParkingSpace(String type,String name,int distance) 
	{
		this.name=name;
		this.type=type;
		this.free=true;
		this.distance=distance;
	}
	public void alloteSpace(Customer customer) 
	{
		this.free=false;
		
	}
	public String getName() {
		return name;
	}
	public void remove() {
		this.free=true;
		System.out.println("Your Vehicle removed suucesfully\n");
	}
	public Ticket getTicket() {
		return ticket;
	}
	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}
}
