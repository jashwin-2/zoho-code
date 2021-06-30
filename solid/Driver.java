package solid;

import java.util.HashMap;
import java.util.Scanner;

public class Driver {

	public static void main(String[] args) {
		
			int choice=1;
			Scanner sc=new Scanner(System.in);
			System.out.println("Enter the total count of creature in pond");
			int total=Integer.parseInt(sc.nextLine());
			Pond pond1=new Pond();
			
			pond1.addOrganism("Fish", new Fish((int)(total*0.6)));
			pond1.addOrganism("Crab", new Crab((int)(total*0.2)));
			pond1.addOrganism("Snake", new Snake((int)(total*0.2)));
			
			
			HashMap<String,Organism> organism=pond1.getOrganism();
			Process process1=new Process();
			PondCalculation calc=new PondCalculation();
			
			PrintDetails printCount=new PrintDetails();
			
			
			while(choice==1)
			{
				process1.inputProcess(organism, calc,printCount);
				System.out.println("Do you want to continue yes -1 no-0");
				choice=Integer.parseInt(sc.nextLine());
				if(choice==0)
					break;
			}
			
			
		  

	}

}
