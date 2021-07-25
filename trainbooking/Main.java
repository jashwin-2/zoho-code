package trainbooking;

import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) 
	{
		ArrayList<Train> trains=new ArrayList<>();
		trains.add(new Train(1,List.of('A','B','C','D','E')));
		trains.add(new Train(2,List.of('X','Y','C')));
		trains.add(new Train(3,List.of('J','E','K','L','M')));
		trains.add(new Train(4,List.of('N','O','L','P','Q','R')));
		trains.add(new Train(5,List.of('S','T','R','U')));
		trains.add(new Train(6,List.of('V','U','W')));
		BookingManager book=new BookingManager(trains);
		book.menu();
	}

}
