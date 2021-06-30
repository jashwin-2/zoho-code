package solid;

import java.util.HashMap;


public class PondCalculation 
{
	Organism temp;
	public int calculation(int fishCount,int crabCount,int snakeCount,HashMap<String,Organism> organismInPond)
	{
		if(crabCount>20)
		{
			return 0;
		}
		temp=organismInPond.get("Snake");
		temp.setCount(temp.getCount()-snakeCount);
		
		temp=organismInPond.get("Fish");
		temp.setCount(temp.getCount()-fishCount-snakeCount);
		
		temp=organismInPond.get("Crab");
		temp.setCount(temp.getCount()-crabCount-snakeCount);
		
		return 1;
		
	}
	
	public void doubleThePond(HashMap<String,Organism> organismInPond)
	{
		temp=organismInPond.get("Snake");
		temp.setCount(temp.getCount()*2);
		
		temp=organismInPond.get("Fish");
		temp.setCount(temp.getCount()*2);
		
		temp=organismInPond.get("Crab");
		temp.setCount(temp.getCount()*2);
	}
}
