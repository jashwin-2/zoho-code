package solid;

import java.util.HashMap;
import java.util.Scanner;

public class Process 
{
public int fishCount,crabCount,snakeCount,choice;
	
	public void inputProcess(HashMap<String,Organism> organismInPond,PondCalculation calc,PrintDetails printCount)
	{
		Scanner s=new Scanner(System.in);
		System.out.println("Enter the count of Fish");
		fishCount=Integer.parseInt(s.nextLine());
		System.out.println("Enter the count of Crab");
		crabCount=Integer.parseInt(s.nextLine());
		System.out.println("Enter the count of Snake");
		snakeCount=Integer.parseInt(s.nextLine());

		int cho=calc.calculation(fishCount,crabCount,snakeCount,organismInPond);
		if(cho==0)
			fishCount=crabCount=snakeCount=0;
		if(fishCount!=0)
		{
			calc.doubleThePond(organismInPond);
		}
		printCount.printOragnism(organismInPond);
		printCount.finalCount(fishCount, crabCount);
		
	}

}
