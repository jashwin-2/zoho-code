package railways;
import java.util.ArrayList;
import java.util.Scanner;



public class Booking 
{
	static Scanner s=new Scanner(System.in);
	public static Train chosseTrain(ArrayList<Train> Trains)
	{
		Train curr=null;
		for(Train c : Trains)
		{
			System.out.println("Train Name "+c.name+" Per ticket price "+c.price);
		}
		
		System.out.println("Enter the train name");
		char nameOfTrain=s.next().charAt(0);
		for(Train t : Trains)
		{
			if(nameOfTrain==t.name)
			{
				curr=t;
				break;
			}
		}
		return curr;
		
	}
	public static void book(Train Curr,int noOfpass)
	{
		ArrayList<Passengers> list=new ArrayList<Passengers>();
		for(int i=0;i<noOfpass;i++)
		{
			Passengers t=new Passengers();
			t.getDetails(i+1);
			list.add(t);
		}
		int ammount=Curr.calculate(list);
		System.out.println("SNo	Name		age	 fee ");
		int i=0;
		for(Passengers t : list)
		{
			System.out.println(++i+"	"+t.name+"	 	"+t.age+"	"+t.ticketFee);
		}	
		System.out.println("total ammoun  ="+ammount);
		s.nextLine();
		System.out.println("Want to book enter 1 for confimation");
		int con=Integer.parseInt(s.nextLine());
		if(con==0) {
			System.out.println("canceld");
			return;}
		Curr.bookTickets(list,noOfpass);
	}
		
	public static void cancel(int[] id,Train curr,int n)
	{
		int total=0,count=0;
		System.out.println("id	Name		age	 fee ");
		for(int i=0;i<n;i++)
		{
			if(curr.tickts.get(id[i])!=null)
			{
				count++;
				Passengers temp=curr.tickts.get(id[i]);
				total+=temp.ticketFee;
				curr.tickts.remove(id[i]);
				System.out.println(temp.id+"	"+temp.name+"	 	"+temp.age+"	"+temp.ticketFee);
			}
			else
				System.out.println("Enter seat no "+id[i]+" not booked so cant canceled");
		}
		curr.price-=(count*20);
		System.out.println("Total refund				"+total);
		System.out.println("Balanc Tickt in train "+curr.name+"  "+(curr.noOfSeats+count)+" current price "+curr.price);
	
	}
		
	public static ArrayList<Train> createTrains(int numberOfTrains)
	{
		ArrayList<Train> temp=new ArrayList<Train>();
		int count=65;
		for(int i=0;i<numberOfTrains;i++)
		{
			Train a=new Train((char)count++);
			temp.add(a);	
		}
		return temp;
	}
	public static void main(String[] args) 
	{
    	
    	int ch;
		System.out.println("Enter the no of trains");
		Scanner sc=new Scanner(System.in);
	    int numberOfTrains=Integer.parseInt(sc.nextLine());
	    ArrayList<Train> Trains=createTrains(numberOfTrains);
	    while(true)
	    {
	    System.out.println("Enter your choice 1.book 2.cancel 3.viewdetails");
	    ch=Integer.parseInt(sc.nextLine());
	    switch(ch)
	    {
	    	case 1:
	    	{
	    		
	    		Train curr=chosseTrain(Trains);
	    		if(curr==null)
	    		{
	    			System.out.println("Train not found");
	    		    break;
	    		}
	    		System.out.println("Enter No of tiketts");
	    		int noOfPass=Integer.parseInt(sc.nextLine());
	    		book(curr,noOfPass);
	    		break;
	    	}
	    	case 2:
	    	{
	    		Train curr=chosseTrain(Trains);
	    		if(curr==null)
	    		{
	    			System.out.println("Train not found");
	    		    break;
	    		}
	    		System.out.println("No of tickets want to cancel");
	    		int nocancel=Integer.parseInt(sc.nextLine());
	    		int[] id=new int[nocancel];
	    		for(int i=0;i<nocancel;i++)
	    		{
	    			System.out.println("Enter the "+(i+1)+" Ticket no");
	    			id[i]=Integer.parseInt(sc.nextLine());
	    		}
	    		cancel(id,curr,nocancel);
	    		break;
	    		
	    		
	    	}
	    	case 3:
	    	{
	    		Train curr=chosseTrain(Trains);
	    		if(curr==null)
	    		{
	    			System.out.println("Train not found");
	    		    break;
	    		}
	    		if(curr.noOfSeats==50)
	    			System.out.println("No seat is booked in this train");
	    		curr.showDetails();
	    		break;
	    	}
	    	default:
	    		{
	    			break;
	    		}
	    	
	      }
	   }
    }

}
