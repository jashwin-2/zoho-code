package fishInPond;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Basket {
	 int maxCapacity;
	boolean full=false;
	public List<Organism> fish;
	public List<Organism> crab;
	public Basket(int capacity)
	{
		this.maxCapacity=capacity;
		this.fish=new ArrayList<Organism>();
		this.crab=new ArrayList<Organism>();
		
	}
	
	public int capacity()
	{
		
		return fish.size()+crab.size();
	}
	
	public void printCount()
	{
		System.out.println("Basket of \nFish	"+fish.size()+"\n"+"Crabs	"+crab.size()+"\n-----------"+"\nTotal	="+this.capacity());
		if(this.capacity()==this.maxCapacity)
			this.full=true;
	}
}
