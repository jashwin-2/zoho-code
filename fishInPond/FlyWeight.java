package fishInPond;

import java.util.HashMap;

public class FlyWeight {

	private HashMap<String,Organism> objects=new HashMap<>();

	public Organism generate(String name) {
		if(objects.containsKey(name))
			return objects.get(name);
		else {
			Organism temp=new Organism(name);
			objects.put(name, temp);
			return temp;
		}
	}
	
	

}
