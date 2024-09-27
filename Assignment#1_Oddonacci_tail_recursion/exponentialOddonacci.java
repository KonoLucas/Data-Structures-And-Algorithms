import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class exponentialOddonacci {

	public static int Oddonacci(int n) {
		if(n <= 3) {return 1;};
		
		return Oddonacci (n-1)+Oddonacci (n-2)+Oddonacci (n-3);
	}

public static void main(String[] args) {
		
		
		int number[]= {5,10,15,20,25,26,27,30};
		long resultTime[] = new long [number.length];
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
			writer.println("Calculate the Oddonacci by for recursion.(Exponential run time.)");
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
		
		// on average run time function is t=10n^4−500n^3+10000n^2−20000n+5000
		// So it O(n^4)
		
		

	}

}
