package parking;

import java.util.ArrayList;
import java.util.List;

public class Coupen 
{
	List<Integer> allotedCoupens=new ArrayList<>();

	public int coupenCodeGenerator()
	{
		int code;
		while(true)
		{
			code=(int)(Math.random()*9000)+1000;
			if(!allotedCoupens.contains(code))
				break;
		}
		allotedCoupens.add(code);
		return code;
	}

	public void allotIfValid(List<String> enteredVehicles,String vehicleNo)
	{
		int code;
		if(enteredVehicles==null||!enteredVehicles.contains(vehicleNo))
		{
			code=this.coupenCodeGenerator();
			this.printCoupen(code);
			enteredVehicles.add(vehicleNo);
		}
	}

	private void printCoupen(int code) 
	{
		System.out.println("**************COUPEN****************\n50% discount on first 10 hour after 10% discount\nYour coupen Code -->"+
		        	code+"\n************************************");
	}
	
	public boolean isValid(int code)
	{
		if(allotedCoupens.contains(code))
			return true;
		return false;
	}
}
