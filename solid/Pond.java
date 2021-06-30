package solid;

import java.util.HashMap;

public class Pond 
{
	private HashMap<String,Organism> organismInPond=new HashMap<>();
	
	public void addOrganism(String name,Organism a)
	{
		this.organismInPond.put(name, a);
	}
	
	public HashMap<String,Organism> getOrganism()
	{
		return this.organismInPond;
	}
}
