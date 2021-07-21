package trainbooking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class BookingManager 
{
	private List<Train> trains=new ArrayList<>();
	private Map<Integer,List<Ticket>> allotedTickets=new HashMap<>();
	private boolean putInWaiting;
	public BookingManager(ArrayList<Train> trains)
	{
		putInWaiting=false;
		this.trains=(ArrayList<Train>)trains.clone();
	}

	public void menu()
	{
		char ch;
		boolean choice=true;
		Scanner sc=new Scanner(System.in);
		while(choice){
			System.out.println("*************WELCOME TO  Railway Booking************\n*****************  Booking MENU ************** ");
			System.out.println("Enter your choice \n1.Book Ticket\n2.Cancel Ticket \n3.Print Occupency Chart\n4.print alloted tickets\n5.Show waiting lits\n6.Exit ");
			System.out.println("*************************************** ");
			ch = sc.nextLine().charAt(0);
			switch (ch) 
			{
			case '1':
			{
				System.out.println("Enter the Starting station");
				char start=sc.nextLine().charAt(0);
				System.out.println("Enter the Destination station");
				char end=sc.nextLine().charAt(0);
				System.out.println("Enter the no of seats");
				int count=Integer.parseInt(sc.nextLine());
				booking(start,end,count);
				break;
			}
			case '2':
			{
				System.out.println("Enter your PNR number ");
				int pnr=Integer.parseInt(sc.nextLine());
				cancelTicket(pnr);
				break;
			}
			case '3':
			{
				System.out.println("Enter the train no 1 or 2");
				int trainNo=Integer.parseInt(sc.nextLine());
				System.out.println("Enter the position "+trains.get(trainNo-1).getPath());
				char position=sc.nextLine().charAt(0);
				trains.get(trainNo-1).printOccupancy(position);
				break;
			}

			case '4':
			{
				this.printAllotedTickets();
				break;
			}
			case '5':
			{
				for(Train t : this.trains)
				{
					System.out.println("----------Waiting List of Train "+t.getName()+"---------------");
					for(WaitingList temp : t.getWaiting())
						temp.print();
					System.out.println("-----------------------------------------------------");
				}
				break;
			}
			case '6':
			{
				choice=false;
				break;
			}
			default:
			{
				System.out.println("invalid input");
			}
			}
		}
	}



	private void cancelTicket(int pnr)
	{
		Scanner sc=new Scanner(System.in);
		List<Ticket> tickets;
		List<Integer> serialNo=new ArrayList<>();
		if(allotedTickets.containsKey(pnr))
			tickets=this.allotedTickets.get(pnr);
		else
		{
			System.out.println("Invalid input cant find the ticket");
			return;
		}
		List<String> passengers=tickets.get(0).getPasengers();
		List<Integer> allotedSeats=tickets.get(0).getAllotedSeats();
		for(int i=0;i<passengers.size();i++)
			System.out.println((i+1)+" Passenger :"+passengers.get(i)+"    Seat No  : "+allotedSeats.get(i));
		System.out.println("Enter the no of tickets you want to cancel");
		int count=Integer.parseInt(sc.nextLine());
		for(int i=1;i<=count;i++)
		{
			System.out.println("Enter the serial no of "+i+" passenger");
			serialNo.add(Integer.parseInt(sc.nextLine())-1);
		}
		for(Ticket t : tickets)
		{
			List<Integer> seatNos=t.getSeatNos(serialNo);
			t.getTrain().addCanceledSeats(t.getFrom(),t.getTo(),seatNos);
			List<WaitingList> removed=new ArrayList<>();
			for(WaitingList temp : t.getTrain().getWaiting())
			{
				Train train;
				if((train=bookIsPossible(temp.getFrom(),temp.getTo(),temp.getPassenger().size(),true,true))!=null)
				{
					bookSeats(train.getAvailableSeats(temp.getFrom(),temp.getTo()),temp.getPassenger().size(),train,temp.getFrom(),temp.getTo(),null,temp.getPassenger());
					System.out.println("-----------------Ticket booked for Waiting List Customer-----------------");
					removed.add(temp);
				}
			}
			t.getTrain().removeFromWaiting(removed);

		}
		System.out.println("Ticket canceled successfully");


	}

	private Train bookIsPossible(char start, char end, int count,boolean singleTrain,boolean fromWaiting)
	{
		List<Integer> availableSeats;

		for(Train t : this.trains)
		{
			if(t.getPath().containsAll(List.of(start,end)))
			{
				boolean booked=false;
				if((t.getPath().indexOf(start)>t.getPath().indexOf(end)))
				{
					System.out.println("Train "+t.getName()+" is going from "+end+" to"+start);
					continue;
				}
				availableSeats=t.getAvailableSeats(start, end);
				//t.printOccupancy('B');
				if(count<=availableSeats.size())
				{

					booked=true;
					return t;

				}
				if(!booked) 
				{
					System.out.println("Seats not available cant book the seats");
					if(singleTrain)
						if(t.getWaiting().size()<2&&(!fromWaiting)&&singleTrain)
						{
							System.out.println("We put you in the waiting ");
							Scanner sc=new Scanner(System.in);
							List<String> passenger=new ArrayList<>();
							for(int i=1;i<=count;i++)
							{
								System.out.println("Enter the "+i+" passenger name");
								passenger.add(sc.nextLine());
							}
							t.putInWaiting(passenger, start, end);
							this.putInWaiting=true;

						}
				}
				return null;
			}

		}
		if(singleTrain)
			System.out.println("Cant find the single train for the given path ");
		return null;

	}

	private void booking(char start, char end, int count) 
	{
		Train one=null,two=null;
		Ticket currTicket=null;
		char common='0';
		if((one=bookIsPossible(start,end,count,true,false))!=null)
		{
			currTicket=bookSeats(one.getAvailableSeats(start,end),count,one,start,end,currTicket,null);
			return;
		}
		if(this.putInWaiting)
		{
			this.putInWaiting=false;
			return;
		}

		for(Train t : trains)
			if(t.getPath().contains(start))
			{
				for(int i=0;i<trains.size();i++)
					if(!trains.get(i).equals(t))
						if(trains.get(i).getPath().contains(end))
						{
							common=checkCondition(trains.get(i).getPath(),t.getPath(),end);
							if(common!='0')
							{
								break;
							}
						}
			}

		if((one=bookIsPossible(start,common,count,false,false))!=null&&(two=bookIsPossible(common,end,count,false,false))!=null)
		{
			currTicket=bookSeats(one.getAvailableSeats(start,common),count,one,start,common,currTicket,null);
			currTicket=bookSeats(two.getAvailableSeats(common,end),count,two,common,end,currTicket,null);
		}
		else
			System.out.println("Cant find the path in two trains aslo");

	}

	private char checkCondition(List<Character> second, List<Character> first,char destination) 
	{
		List<Character> temp=new ArrayList<>(second);
		temp.retainAll(first);
		for(int i=second.indexOf(temp.get(0))-1;i<second.size();i++)
		{
			if(second.get(i)==destination)
				return temp.get(0);
		}
		return '0';
	}

	private Ticket bookSeats(List<Integer> availableSeats, int count,Train train,char from,char to,Ticket currTicket,List<String> passengerNames) 
	{
		int pnr;
		Ticket ticket=null;
		List<String> passengers=new ArrayList<>();
		Scanner sc=new Scanner(System.in);
		if(currTicket==null) 
		{
			pnr=genaratePnr();
			if(passengerNames==null)
			{
				for(int i=0;i<count;i++)
				{
					System.out.println("Enter the name of the "+(i+1)+" passenger");
					passengers.add(sc.nextLine());
				}
				ticket=new Ticket(train,passengers,availableSeats.subList(0, count),from,to,pnr);
			}
			else
				ticket=new Ticket(train,passengerNames,availableSeats.subList(0, count),from,to,pnr);
			System.out.println("-----------------------------------------------");
			System.out.println("PNR no :"+pnr);
			allotedTickets.put(pnr, new ArrayList<Ticket>());
		}
		else
		{
			pnr=currTicket.getPnr();
			ticket=new Ticket(train,currTicket.getPasengers(),availableSeats.subList(0, count),from,to,pnr);

		}
		ticket.print();
		allotedTickets.get(pnr).add(ticket);
		train.removeBookedSeats(from,to,availableSeats.subList(0, count));
		return ticket;

	}

	void printAllotedTickets()
	{
		for (Map.Entry<Integer, List<Ticket>> set :allotedTickets.entrySet()) 
		{
			System.out.println("----------------------------------------------\n");
			System.out.println(set.getKey());
			List<Ticket> tickets=set.getValue();
			for(Ticket t : tickets)
				t.print();
			System.out.println("------------------------------------------------\n");
		}
	}

	private int genaratePnr() {
		int pnr;
		while(true)
		{
			pnr=(int)(Math.random()*9000)+1000;
			if(!allotedTickets.containsKey(pnr))
				break;
		}
		return pnr;
	}


}
