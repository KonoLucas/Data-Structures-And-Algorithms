
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class linerOddonacci {
	
	
	public static long Oddonacci(int n) {
		if(n <= 3) {return 1;}
		
		long[]Oddonaccilist = new long[n];
		Oddonaccilist[0]=Oddonaccilist[1]=Oddonaccilist[2]=1;
		for(int i=3; i<n ;i++) {
			Oddonaccilist[i]=Oddonaccilist[i-1]+Oddonaccilist[i-2]+Oddonaccilist[i-3];			
		}
		
		return Oddonaccilist[n-1];		
		
	}

	public static void main(String[] args) {
		
		
		int number[]= {10,20,30,40,50,60,};
		long resultTime[] = new long[number.length];
		long resultNumber[] = new long[number.length];
		
		for(int i=0; i<number.length;i++) {
			long startTime = System.nanoTime();
			long result=Oddonacci(number[i]);
			long endTime = System.nanoTime();
			long runtime = endTime - startTime;
			System.out.println("input: "+number[i]+"  Output: "+result);
			System.out.println("run time is:"+runtime);
			resultNumber[i]=result;
			resultTime[i]=runtime;
				
		}
		
		
		try {
			FileWriter fw = new FileWriter("OddoOut.txt", true);
			PrintWriter writer = new PrintWriter(fw);
			writer.println("***********************************************************************************************************************");
			writer.println("Calculate the Oddonacci by for loop.(Liner run time.)");
			for(int i=0; i<number.length;i++) {
				writer.println("Input: "+number[i] + "  Output: "+ resultNumber[i]+"  Run time: "+resultTime[i]);
				
			}
			writer.println("***********************************************************************************************************************");
			writer.println();
			writer.close();
			
		} catch (IOException e) {
			System.out.println("An error occurred while writing to the file.");
			e.printStackTrace();
		}
		
		
		
		

	}

}
