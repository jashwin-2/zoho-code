package trainbooking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Ticket
{
	private int pnr;
	//private List<>
	private Map<Train,List<Character>> tickets=new LinkedHashMap<>();
	private List<String> pasengers=new ArrayList<>();
	private  Map<Train,List<Integer>> allotedSeats=new HashMap<>();
	
	

	Ticket(Train train,List<String> passengers,List<Integer> allotedSeats,char from,char to,int pnr)
	{
		this.pnr=pnr;
		this.putTickets(train,List.of(from,to));
		this.putInAllotedSeats(train,allotedSeats);
		this.pasengers=passengers;
	}
	
	private void putInAllotedSeats(Train train,List<Integer> allotedSeats) 
	{
		this.allotedSeats.put(train, allotedSeats);
	}

	public void print()
	{
		for (Entry<Train, List<Character>> set :tickets.entrySet()) 
		{
			System.out.println("From  :"+set.getValue().get(0)+"\nTo  :"+set.getValue().get(1)+"\nTrain name :"+set.getKey().getName());
			for(int i=0;i<pasengers.size();i++)
				System.out.println("Passenger :"+pasengers.get(i)+"    Seat No  : "+allotedSeats.get(set.getKey()).get(i));
			System.out.println("-----------------------------------------------");
		}
		
		//System.out.println(pasengers+" "+allotedSeats+" "+pasengers.size());
		
	}

	public List<String> getPasengers() {
		return pasengers;
	}

	public int getPnr() {
		return pnr;
	}

	public Map<Train, List<Integer>> getSeatNos(List<Integer> serialNo) 
	{
		List<Integer> seatNos=new ArrayList<>();
		List<Integer> seatsAfterCancel=new ArrayList<>();
		List<String> passengersAfterCancel=new ArrayList<>();
		Map<Train,List<Integer>> removedSeats=new HashMap<>();
		
		for(int i=0;i<pasengers.size();i++)
			if(!serialNo.contains(i))
				passengersAfterCancel.add(pasengers.get(i));
		this.pasengers=passengersAfterCancel;
		
		for (Entry<Train, List<Character>> set :tickets.entrySet()) 
		{
			List<Integer> seats=allotedSeats.get(set.getKey());
		for(Integer i : serialNo)
			seatNos.add(seats.get(i));
		for(int i=0;i<seats.size();i++)
			if(!serialNo.contains(i))
				seatsAfterCancel.add(seats.get(i));
		allotedSeats.replace(set.getKey(), seatsAfterCancel);
		removedSeats.put(set.getKey(), seatNos);
		}
		return removedSeats;
	}

	public void addTicket(Train train,List<Integer> seats,List<Character> fromTo)
	{
		tickets.put(train, fromTo);
		allotedSeats.put(train, seats);
	}
	

	public Map<Train, List<Integer>> getAllotedSeats() {
		return allotedSeats;
	}

	public Map<Train, List<Character>> getTickets() {
		return tickets;
	}

	public void putTickets(Train train,List<Character> fromTo) {
		this.tickets.put(train, fromTo);
	}
}
