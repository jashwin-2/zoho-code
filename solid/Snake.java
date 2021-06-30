package solid;

public class Snake implements Organism
{
	private int count;
	
	Snake(int count)
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
