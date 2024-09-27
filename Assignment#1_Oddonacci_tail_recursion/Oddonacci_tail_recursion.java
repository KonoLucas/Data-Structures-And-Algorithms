
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class Oddonacci_tail_recursion {
	
	public static long OddonacciTailRecursive(int n, long nMinusOne, long nMinusTwo, long nMinusThree) {
	    if (n <= 3) return 1;
	    if (n == 4) return nMinusOne + nMinusTwo + nMinusThree;
	    return OddonacciTailRecursive(n - 1, nMinusOne + nMinusTwo + nMinusThree, nMinusOne, nMinusTwo);
	}

	public static long OddonacciTailRecursive(int n) {
	    return OddonacciTailRecursive(n, 1, 1, 1);
	}
	

			
	

	public static void main(String[] args) {
		
		
		int number[]= {30};
		long resultTime[] = new long[number.length];
		long resultNumber[] = new long[number.length];
		
		for(int i=0; i<number.length;i++) {
			long startTime = System.nanoTime();
			long result=OddonacciTailRecursive(number[i]);
			long endTime = System.nanoTime();
			long runtime = endTime - startTime;
			System.out.println("input: "+number[i]+"--Output: "+result);
			System.out.println("run time is:"+runtime);
			resultNumber[i]=result;
			resultTime[i]=runtime;
				
		}
		
		
		try {
			FileWriter fw = new FileWriter("OddoOut.txt", true);
			PrintWriter writer = new PrintWriter(fw);
			writer.println("***********************************************************************************************************************");
			writer.println("Calculate the Oddonacci by tail-recursive version.");
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
