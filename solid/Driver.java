package solid;

import java.util.HashMap;
import java.util.Scanner;

public class Driver {

	public static void main(String[] args) {
		
			int choice=1;
			Scanner sc=new Scanner(System.in);
			System.out.println("Enter the total count of creature in pond");
			int total=Integer.parseInt(sc.nextLine());
			Pond pond1=new Pond(total);
			HashMap<String,Integer> organism=pond1.getOrganism();
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
