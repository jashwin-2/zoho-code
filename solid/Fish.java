package solid;

public class Fish implements Organism 
{
	private int count;
	
	Fish(int count)
	{
		this.count=count;
	}
	
	public void setCount(int count)
	{
		this.count=count;
	}
	
	public int getCount()
	{
		return this.count;
	}

	
}
