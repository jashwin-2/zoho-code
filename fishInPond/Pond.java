package fishInPond;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Pond {

		public HashMap<String,List<Organism>> organism=new HashMap<>();
		private FlyWeight fly;
		
	
		public Pond(int total,FlyWeight fly)
		{
			this.fly=fly;
			this.organism.put("Fish", new ArrayList<Organism>());
			this.addObjects("Fish", (int)(total*0.6));
			this.organism.put("Crab", new ArrayList<Organism>());
			this.addObjects("Crab", (int)(total*0.2));
			this.organism.put("Snake", new ArrayList<Organism>());
			this.addObjects("Snake", (int)(total*0.2));
		}
		
		public void addObjects(String name,int count)
		{
			List<Organism> temp=this.organism.get(name);
			for(int i=0;i<count;i++)
				temp.add(fly.generate(name));
			
		}
		
		void printCount()
		{
			organism.forEach((k,v)->System.out.println(v.size()+" "+k+"s in Pond"));
		}
		
		void removeObjects(String name,int count,List<Organism> inNet)
		{
			if(count>this.organism.get(name).size())
				System.out.println("\nCan't catch "+count+" "+name+"s only "+organism.get(name).size()+" in Pond");
			else {
				List<Organism> temp=this.organism.get(name);
				for(int i=0;i<count;i++) {
					inNet.add(temp.get(0));
					temp.remove(0);
				}
				
			}
		}
		
		void removeObjects(String name,int count)
		{
			List<Organism> temp=this.organism.get(name);
			if(count>=temp.size())
			{
				temp.clear();
				return;
			}
			for(int i=0;i<count;i++) 
				temp.remove(0);
		}
		
		void doubleOrg()
		{
			for(List<Organism> k : organism.values())
			{
				if(k.size()!=0) 
				this.addObjects(k.get(0).getName(), k.size());
			}
		}

		public boolean anyThingToCatch() {
			if(this.organism.get("Fish").size()==0&&this.organism.get("Crab").size()==0)
				return false;
			return true;
		}
}
