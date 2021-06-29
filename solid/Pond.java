package solid;

import java.util.HashMap;

public class Pond 
{
	private HashMap<String,Integer> organism=new HashMap<>();
	
	public Pond(int total)
	{
		this.organism.put("Fish", (int)(total*0.6));
		this.organism.put("Crab", (int)(total*0.2));
		this.organism.put("Snake", (int)(total*0.2));
	}
	
	public void addOrganism(String name,int count)
	{
		this.organism.put(name,Integer.valueOf(count));
	}
	
	public HashMap<String,Integer> getOrganism()
	{
		return this.organism;
	}
}
