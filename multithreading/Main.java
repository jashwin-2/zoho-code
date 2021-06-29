package multithreading;


class Data
{
	public int a;
	boolean flag=true;
	synchronized public void set(int val)
	{
		while(flag!=true) {
			try{wait();}catch(Exception e) {};}
		a=val;
		flag=false;
		notify();
	}
	synchronized public int get()
	{
		int b=0;
		while(flag!=false) {
			try{wait();}catch(Exception e) {};}
		b=a;
		flag=true;
		notify();
		return b;
	}
}
class Producer extends Thread
{
	public Data d;
	public int count;
	Producer(Data d)
	{
		this.d=d;
		count=1;
	}
	public void run()
	{
		while(true)
		{
			
			d.set(count);
			System.out.println("produces "+count);
			count++;
		}
	}
}
class Consumer extends Thread
{
	public Data d;
	public int k;
	Consumer(Data d)
	{
		this.d=d;
		k=0;
		
	}
	public void run()
	{
		while(true)
		{
			k=d.get();
			System.out.println("consumer "+k);
	
		}
	}
}
class Main
{
	public static void main(String[] args)
	{
		Data d=new Data();
		Consumer c=new Consumer(d);
		Producer p=new Producer(d);
		c.start();
		p.start();
	}
}
	