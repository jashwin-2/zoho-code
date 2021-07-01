package fishInPond;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Net {
	public HashMap<String,List<Organism>> inNet=new HashMap<>();
	
	public Net()
	{
		this.inNet.put("Fish", new ArrayList<Organism>());
		this.inNet.put("Crab", new ArrayList<Organism>());
		this.inNet.put("Snake", new ArrayList<Organism>());
	}
	
	public boolean throwNet(Pond pond)
	{
		Scanner s=new Scanner(System.in);
		System.out.println("No of Fish got in Net");
		int fishCount=Integer.parseInt(s.nextLine());
		System.out.println("No of Crab got in Net");
		int crabCount=Integer.parseInt(s.nextLine());
		if(crabCount>20&&pond.organism.get("Crab").size()>=crabCount)
		{
			System.out.println("\nHe catch "+crabCount+" crabs in net so the net breaks so");
			return true;
		}
		
		System.out.println("No of Snake got in Net");
		int snakeCount=Integer.parseInt(s.nextLine());
		pond.removeObjects("Snake", snakeCount, inNet.get("Snake"));
		pond.removeObjects("Fish", fishCount, inNet.get("Fish"));
		pond.removeObjects("Crab", crabCount, inNet.get("Crab"));
		return false;
	}
	
}
