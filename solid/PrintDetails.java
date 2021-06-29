package solid;

import java.util.HashMap;

public class PrintDetails 
{

	public void printOragnism(HashMap<String,Integer> organism)
	{
		System.out.println("Remaining Organism in pond");
		organism.forEach((k,v)->System.out.println("Name -"+k+" Count -"+v));
	}

	public void finalCount(int fishCount,int crabCount)
	{
		System.out.println();
		System.out.println("No of Organism caught by Fisher man ");
		System.out.println("No of Fish caught by Fisher man "+fishCount);
		System.out.println("No of crab caught by Fisher man "+crabCount);
		System.out.println("No of snake caught by Fisher man "+0);
		System.out.println();
		
	}
}
