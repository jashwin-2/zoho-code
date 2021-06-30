package solid;

public class Crab implements Organism 
{
		private int count;
		
		Crab(int count)
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
