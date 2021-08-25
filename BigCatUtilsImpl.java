package bigcat;

import java.io.PrintWriter;
import java.util.Scanner;



public class BigCatUtilsImpl {

	public static BigCat readFromScanner(Scanner inFile) {
		
	
		try {
			
	        String name = inFile.nextLine();
	        String line = inFile.nextLine();
	        String [] arr = line.split("\\s+");
	        int weight = Integer.parseInt(arr[1]);
	        BigCatBreed breed = BigCatBreed.valueOf(arr[0].toUpperCase());
	        return new BigCat(name, breed, weight);
			}
			catch (Throwable t) {
				return null;
			}
		
	
	}
	
	public static void writeToFile(PrintWriter printWriter, BigCat bigCat) 
	{
		String name = bigCat.getName();
		BigCatBreed breed = bigCat.getCatBreed();
		int weight = bigCat.getWeight();
		
		printWriter.println(name + "\n" + breed + " " + weight);
		
	}
}