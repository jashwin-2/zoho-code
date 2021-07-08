package parking;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class Payment 
{
	private int cost;
	private long difference;
	void calculatePayment(Ticket t)
	{
		LocalTime time1=t.getTime1();
		LocalTime time2=LocalTime.now();
		difference=ChronoUnit.SECONDS.between(time1, time2);
		if(difference>10)
		{
			cost=10*10;
			cost+=(difference-10);
		}
		else
			cost=(int)difference*10;
		this.print();
			
	}
	
	void print()
	{
		System.out.println("Time duration "+difference+" \n Cost :"+cost);
	}
}
