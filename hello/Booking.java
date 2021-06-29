package hello;
import java.util.*;
public class Booking
{
	public static void booktaxi(int id,char pp,char dp,int g,List<Taxi> freetaxi)
	{
		Taxi book=null;
		int min=999;
		int dist=0;
		int nextfr=0;
		char nextfp='z';
		int earning=0;
		String tripdet="";
		Taxi bo=new Taxi();
		for(Taxi t : freetaxi)
		{
			book=t;
			int dis=Math.abs((dp-'0')-(pp-'0'))*15;
			earning=(dis-5)+100;
			int dpt=g + dis/15;
			nextfr=dpt;
			nextfp=dp;
			tripdet=id+"   "+id+"     "+pp+"     " +dp+"  "+g+"  "+dpt+"  "+earning;
						
		}
		book.setdetail(true,nextfp,nextfr,book.totalearnings+earning,tripdet);
		System.out.println("taxi"+book.id+"bookrd");
		
		
		
	}
	public static List<Taxi> getfree(List <Taxi> taxis,char pp,char dp,int pt)
	{
		List<Taxi> ftaxis=new ArrayList<Taxi>();
		for(Taxi t : taxis)
		{
			if(t.freetime<=pp&&(Math.abs((t.currentspot-'0')-(pp-'0'))<=pp-t.freetime))
				ftaxis.add(t);
					
		}
		return ftaxis;
	}
	public static List<Taxi> CreateTaxis(int n)
	{
		List<Taxi> taxis=new ArrayList<Taxi>();
		for(int i=1;i<=n;i++)
		{
			Taxi t=new Taxi();
			taxis.add(t);
		}
		return taxis;
	}
	public static void main(String[] args)
	{
		List<Taxi> taxis=CreateTaxis(4);
		Scanner s=new Scanner(System.in);
		int id=0;
		while(true) 
		{
			System.out.println("0 book");
			System.out.println("1 details");
			int ch=s.nextInt();
			switch(ch)
			{
			case 0:
			{
				System.out.println("entt th pickupoint");
				char pp=s.next().charAt(0);
				System.out.println("entt th dropupoint");
				char dp=s.next().charAt(0);
				System.out.println("entt th pickupoint tim");
				int t=s.nextInt();
				List<Taxi> freetaxi=getfree(taxis,pp,dp,t);
				Collections.sort(freetaxi,(a,b)->a.totalearnings-b.totalearnings);
				booktaxi(id,pp,dp,t,freetaxi);
				id++;
				break;
			}
			case 1:{
				for(Taxi g : taxis)
					g.printtaxi();
				
			}}
		}
	}
}