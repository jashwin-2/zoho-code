package trainbooking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class BookingManager
{
	private List<Train> trains=new ArrayList<>();
	private Map<Integer,Ticket> allotedTickets=new HashMap<>();
	private boolean putInWaiting;
	public BookingManager(ArrayList<Train> trains)
	{
		putInWaiting=false;
		this.trains=trains;
	}
	
//Getting input from user
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
				System.out.println("Enter the train no ");
				for(Train temp : trains)
					System.out.println("Train No :"+temp.getName()+" Path --> "+temp.getPath());
				int trainNo=Integer.parseInt(sc.nextLine());
				trains.get(trainNo-1).printOccupancy();
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
					if(t.getWaiting().size()==0)
						System.out.println("Empty");
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




//Check for train which has required no of seats if no such train is found return null
	
	private Train bookIsPossible(char start, char end, int count,boolean singleTrain,boolean fromWaiting)
	{
		List<Integer> availableSeats;

		for(Train t : this.trains)
		{
			if(t.getPath().containsAll(List.of(start,end)))
			{
				if((t.getPath().indexOf(start)>t.getPath().indexOf(end)))
				{
					System.out.println("Train "+t.getName()+" is going from "+end+" to"+start);
					continue;
				}
				availableSeats=t.getAvailableSeats(start, end);

				if(count<=availableSeats.size())
					return t;
				
				// Adding details of the customer in waiting list 
				else 
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
			System.out.println("\nCant find the single train for the given path \n");
		return null;

	}

	// Check for all possible bookings through one,two,three... trains
	private void booking(char start, char end, int count) 
	{
		Train one=null,two=null;
		boolean singleTrain=true,fromWaiting=false;
		Ticket currTicket=null;
		char common='0';
		
		//checking for single train to reach the destination
		
		if((one=bookIsPossible(start,end,count,singleTrain,fromWaiting))!=null)
		{
			currTicket=bookSeats(one.getAvailableSeats(start,end),count,one,start,end,currTicket,null);
			currTicket.print();
			return;
		}
		if(this.putInWaiting)
		{
			this.putInWaiting=false;
			return;
		}

		//checking for two trains to reach the destination
		
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
		singleTrain=false;
		if(common!='0'&& (one=bookIsPossible(start,common,count,singleTrain,fromWaiting))!=null&&(two=bookIsPossible(common,end,count,singleTrain,fromWaiting))!=null)
		{
			currTicket=bookSeats(one.getAvailableSeats(start,common),count,one,start,common,currTicket,null);
			currTicket=bookSeats(two.getAvailableSeats(common,end),count,two,common,end,currTicket,null);
			currTicket.print();
			return ;
		}

		//checking for multiple trains to reach the destination
		
		else {
			System.out.println("Cant book the ticket in two trains also\n\nChecking for multi train paths.....\n");
			//intermediateTrains and commonStations is used to store intermediateTrains object and source and destination of that train
			
			List<Train> intermediateTrains = new ArrayList<>();
			List<List<Character>> commonStations = new ArrayList<>();
			
			//Calling the function which checks for the path through multiple trains and return true if it find a path
			
			if(chectForMultipleTrains(start,end,intermediateTrains,commonStations))
			{
				//Checking whether the train has a required no of free seats
				for(int i=0;i<intermediateTrains.size();i++)
				{

					char from=commonStations.get(i).get(0),to=commonStations.get(i).get(1);
					List<Integer> availableSeats=intermediateTrains.get(i).getAvailableSeats(from, to);

					if(count>availableSeats.size())
					{
						System.out.println("Path finded but cant book the seats ");
						return ;

					}

				}
				
				//Booking the Seats and printing the ticket
				for(int i=0;i<intermediateTrains.size();i++)
				{

					char from=commonStations.get(i).get(0),to=commonStations.get(i).get(1);
					if(from!=to)
						currTicket=bookSeats(intermediateTrains.get(i).getAvailableSeats(from,to),count,intermediateTrains.get(i)
								,from,to,currTicket,null);

				}
				currTicket.print();
			}
			else
				System.out.println("Can not book in multi trains also");
		}
	}
	
	//Check all possible Combinations of trains to reach the destination
	private boolean chectForMultipleTrains(char start, char end, List<Train> intermediateTrains, List<List<Character>> commonStations)
	{
		//Storing the trains in from and end trains based on which station(from or end) it have 
		List<Train> fromTrains=new ArrayList<>();
		List<Train> endTrains=new ArrayList<>();

		for(Train temp : trains)
		{
			if(temp.getPath().contains(start))
				fromTrains.add(temp);
			if(temp.getPath().contains(end))
				endTrains.add(temp);
		}
		ArrayList<Train> possibleTrains=new ArrayList<>(trains);
		
		//Calling find path function using all possible combinations
		for(Train i : fromTrains)
			for(Train j : endTrains)
			{
				intermediateTrains.clear();
				commonStations.clear();
				List<Character> pathOfFromTrain=i.getPath().subList(i.getPath().indexOf(start),i.getPath().size());
				List<Character> pathOfToTrain=j.getPath().subList(0, j.getPath().indexOf(end));
				intermediateTrains.add(0, i);
				commonStations.add(0,new ArrayList<>());
				
				//Used to store the checked trains object so we can check whether the train is already checked or not 
				List<Train> checked=new ArrayList<>();
				checked.add(j);
				possibleTrains.remove(j);
				if(findPath(pathOfFromTrain,pathOfToTrain,possibleTrains,intermediateTrains,commonStations,checked))
				{
					commonStations.get(0).addAll((List.of(start,commonStations.get(1).get(0))));
					intermediateTrains.add(j);
					commonStations.add(new ArrayList<>(List.of(commonStations.get(commonStations.size()-1).get(1),end)));
					return true;
				}
				checked.clear();
				possibleTrains.add(j);
				intermediateTrains.remove(0);
				commonStations.remove(0);

			}
		return false;


	}

	//Break the big problem into small problem and find the solution 
	private boolean findPath(List<Character> pathOfFromTrain, List<Character> pathOfToTrain,
			ArrayList<Train> possibleTrains, List<Train> intermediateTrains, List<List<Character>> commonStations,List<Train> checked) {
		//store the trains through which we can reach the stations which are present before the destination in end train 
		Map<Train,List<Character>> possibleIntermediateTrains=new HashMap<>();
		for(Train temp : possibleTrains)
		{
			List<Character> fromCommonStations=temp.getPath();
			List<Character> endCommonStations=temp.getPath();
			fromCommonStations.retainAll(pathOfFromTrain);
			endCommonStations.retainAll(pathOfToTrain);
			
			//If the any train has stations from both from and end trains then add it in intermediate trains 
			if(endCommonStations.size()!=0)
				if(fromCommonStations.size()!=0)
				{
					char from = 0,to = 0;
					for(Character endStation : endCommonStations)
						for(Character fromStation : fromCommonStations)
							if(temp.getPath().indexOf(endStation)>temp.getPath().indexOf(fromStation))
							{
								from=fromStation;
								to=endStation;
								break;
							}
					if(from!=0&&to!=0)
					{
						intermediateTrains.add(temp);
						commonStations.add(new ArrayList<>(List.of(from,to)));
						return true;
					}

				}
			
			//if it only has station which is present in lastTrain(through which we can reach the destination) we can add it possible intermediate trains
				else 
				{
					//stations which are present before the common station
					List<Character> stations=temp.getPath().subList(0,temp.getPath().indexOf(endCommonStations.get(0)));
					possibleIntermediateTrains.put(temp, stations);
				}

		}
		
		//Calling the function again with possibleIntermediateTrains to check whether we can reach that intermediate train
		for (Entry<Train, List<Character>> set :possibleIntermediateTrains.entrySet()) 
		{
			List<Character> path=set.getKey().getPath();
			path.retainAll(pathOfToTrain);
			
			//Check Whether the train is already present in checked trains to avoid infinite loop
			if(!checked.contains(set.getKey())&&!(intermediateTrains.contains(set.getKey())))
			{
				checked.add(set.getKey());
				List<Train> checked1=new ArrayList<>();
				checked1.add(set.getKey());
				possibleTrains.remove(set.getKey());
				if(findPath(pathOfFromTrain,set.getValue(),possibleTrains,intermediateTrains,commonStations,checked1))
				{
					intermediateTrains.add(set.getKey());
					commonStations.add(new ArrayList<>(List.of(commonStations.get(commonStations.size()-1).get(1),path.get(0))));
					return true;
				}
				possibleTrains.add(set.getKey());
				checked.clear();
			}
			else
				break;
		}

		return false;
	}

	//Check whether we can reach the destination using the given two trains if yes then return the common station
	private char checkCondition(List<Character> second, List<Character> first,char destination) 
	{
		List<Character> temp=new ArrayList<>(second);
		temp.retainAll(first);
		if(temp.size()==0)
			return '0';
		for(int i=second.indexOf(temp.get(0))-1;i<second.size();i++)
		{
			if(second.get(i)==destination)
				return temp.get(0);
		}
		return '0';
	}

	//Book the tickets based on the given arguments
	private Ticket bookSeats(List<Integer> availableSeats, int count,Train train,char from,char to,Ticket currTicket,List<String> passengerNames) 
	{
		int pnr;
		Ticket ticket=null;
		List<String> passengers=new ArrayList<>();
		Scanner sc=new Scanner(System.in);
		
		//if ticket is not created already create new ticket
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
			
			//if it this call from cancel function don't get the details from the user
			else
				ticket=new Ticket(train,passengerNames,availableSeats.subList(0, count),from,to,pnr);
			System.out.println("-----------------------------------------------");
			System.out.println("PNR no :"+pnr);
			allotedTickets.put(pnr, ticket);
			train.removeBookedSeats(from,to,availableSeats.subList(0, count));
			return ticket;

		}
		
		//if it already has a ticket just add the Ticket 
		else
		{
			currTicket.addTicket(train,availableSeats.subList(0, count),List.of(from,to));
			train.removeBookedSeats(from,to,availableSeats.subList(0, count));
			return currTicket;

		}

	}
	
	//To cancel the ticket and also book ticket for waiting List customer
	private void cancelTicket(int pnr)
	{
		Scanner sc=new Scanner(System.in);
		Ticket ticket;
		List<Integer> serialNo=new ArrayList<>();
		if(allotedTickets.containsKey(pnr))
			ticket=this.allotedTickets.get(pnr);
		else
		{
			System.out.println("Invalid input cant find the ticket");
			return;
		}
		List<String> passengers=ticket.getPasengers();
		List<Integer> allotedSeats=ticket.getAllotedSeats().get(ticket.getAllotedSeats().keySet().toArray()[0]);
		for(int i=0;i<passengers.size();i++)
			System.out.println((i+1)+" Passenger :"+passengers.get(i)+"    Seat No  : "+allotedSeats.get(i));
		System.out.println("Enter the no of tickets you want to cancel");
		int count=Integer.parseInt(sc.nextLine());
		for(int i=1;i<=count;i++)
		{
			System.out.println("Enter the serial no of "+i+" passenger");
			serialNo.add(Integer.parseInt(sc.nextLine())-1);
		}

		//Getting the seat no of the passengers who are canceled the the ticket
		Map<Train, List<Integer>> seatNos=ticket.getSeatNos(serialNo);
		Map<Train,List<Character>> fromTo=ticket.getTickets();
		
		
		for (Entry<Train, List<Integer>> set :seatNos.entrySet()) 
		{
			char from=fromTo.get(set.getKey()).get(0);
			char to=fromTo.get(set.getKey()).get(1);
			
			//Adding the canceled seats in the available seats of the train
			set.getKey().addCanceledSeats(from,to,set.getValue());
			List<WaitingList> removed=new ArrayList<>();
			
			//Booking the tickets for waiting list passengers if booking is possible after canceled the of Booked tickets
			for(WaitingList temp : set.getKey().getWaiting())
			{
				Train train;
				boolean singleTrain=true,fromWaiting=true;
				if((train=bookIsPossible(temp.getFrom(),temp.getTo(),temp.getPassenger().size(),singleTrain,fromWaiting))!=null)
				{
					Ticket currTicket=null;
					System.out.println("-----------------Ticket booked for Waiting List Customer-----------------");
					currTicket=bookSeats(train.getAvailableSeats(temp.getFrom(),temp.getTo()),temp.getPassenger().size(),train,temp.getFrom(),temp.getTo(),currTicket,temp.getPassenger());
					currTicket.print();
					removed.add(temp);
				}
			}
			set.getKey().removeFromWaiting(removed);

		}
		System.out.println("Ticket canceled successfully");
		if(ticket.getPasengers().size()==0)
			allotedTickets.remove(pnr);


	}

	//Print the allotted tickets
	void printAllotedTickets()
	{
		if(allotedTickets.size()==0) 
		{
			System.out.println("----------------------------------\nEmpty\n----------------------------------\n");
			return;
		}
		for (Entry<Integer, Ticket> set :allotedTickets.entrySet()) 
		{
			System.out.println("----------------------------------------------\n");
			System.out.println("PNR NO :"+set.getKey());
			set.getValue().print();
			System.out.println("------------------------------------------------\n");
		}
	}

	//Generates PNR number
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
