package trainbooking;

import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) 
	{
		ArrayList<Train> trains=new ArrayList<>();
		trains.add(new Train(1,List.of('A','B','C','D','E')));
		trains.add(new Train(2,List.of('X','Y','C')));
		BookingManager book=new BookingManager(trains);
		book.menu();
	}

}
