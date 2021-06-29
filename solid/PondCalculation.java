package solid;

import java.util.HashMap;

public class PondCalculation 
{
	public int calculation(int fishCount,int crabCount,int snakeCount,HashMap<String,Integer> organism)
	{
		if(crabCount>20)
		{
			return 0;
		}
		
		organism.replace("Fish", organism.get("Fish")-fishCount);
		organism.replace("Crab", organism.get("Crab")-crabCount);
		
		organism.replace("Fish", organism.get("Fish")-snakeCount);
		organism.replace("Crab", organism.get("Crab")-snakeCount);
		
		organism.replace("Snake", organism.get("Snake")-snakeCount);
		
		return 1;
		
	}
	
	public void doubleThePond(HashMap<String,Integer> organism)
	{
		organism.replace("Fish", organism.get("Fish")*2);
		organism.replace("Crab", organism.get("Crab")*2);
		organism.replace("Snake", organism.get("Snake")*2);
	}
}
