package fishInPond;

import java.util.List;

public class FisherMan {
	private Basket basket;
	private Net net;
	private Pond pond;
	
	public void ifbasketFull(List<Organism> temp,List<Organism> addBasket)
	{
		int max=basket.maxCapacity-basket.capacity();
		for(int i=0;i<max;i++)
		{
			addBasket.add(temp.get(0));
			temp.remove(0);
		}
		pond.addObjects(temp.get(0).getName(), temp.size());
		temp.clear();
	}
	public FisherMan(Basket basket,Net net,Pond pond)
	{
		this.basket=basket;
		this.net=net;
		this.pond=pond;
	}
	
	void throwIntoPond(List<Organism> object)
	{
		pond.removeObjects("Fish", object.size());
		pond.removeObjects("Crab", object.size());
	}
	
	void putIntoBasket()
	{
		List<Organism> temp;
		temp=net.inNet.get("Snake");
		throwIntoPond(temp);
		temp.clear();
		
		temp=net.inNet.get("Crab");
		if(basket.capacity()+temp.size()<=basket.maxCapacity) {
			basket.crab.addAll(temp);
			temp.clear();
		}
		else {
			this.ifbasketFull(temp,basket.crab);
			basket.full=true;
			pond.addObjects("Fish", net.inNet.get("Fish").size());
		    return;
		}
		temp=net.inNet.get("Fish");
		if(basket.capacity()+temp.size()<=basket.maxCapacity) {
		basket.fish.addAll(temp);
		temp.clear();
		}
		else {
			this.ifbasketFull(temp,basket.fish);
			basket.full=true;
	    	return;
	   }
	}
	public void throwsNet()
	{
		while(!this.basket.full)
		{
			System.out.println("Fisher man throws the net into pond\n");
			if(net.throwNet(pond))
			{
				System.out.println("\nSince the net breaks all organisms go back to pond so no change in count");
				pond.printCount();
				break;
			}
			putIntoBasket();
			System.out.println("\nAfter catch Fisher man have a");
			basket.printCount();
			
			pond.doubleOrg();
			System.out.println("\nAfter catch count of organism in pond");
			pond.printCount();
			System.out.println("--------------------------------------------------------------------------------");
			if(pond.anyThingToCatch()==false) {
				System.out.println("There no Fish and Crab in pond so ");
				break;
			}
		}
		if(basket.full)
			System.out.println("Basket is full so");
		System.out.println("Fisher man return home with");
		basket.printCount();
	}

	
}
