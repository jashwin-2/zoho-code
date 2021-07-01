package fishInPond;

import java.util.Scanner;

public class driver {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the number of objects in pond");
		int total=Integer.parseInt(sc.nextLine());
		FlyWeight fly=new FlyWeight();
		Pond pond=new Pond(total,fly);
		System.out.println("Enter the capacity of Basket");
		int capacity=Integer.parseInt(sc.nextLine());
		Basket basket=new Basket(capacity);
		Net net=new Net();
		FisherMan man=new FisherMan(basket,net,pond);
		man.throwsNet();
	}

}
