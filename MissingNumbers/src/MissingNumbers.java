import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

public class MissingNumbers {
	
	public static void main( String[] args ) {
		
		ArrayList<Integer> numList = getListFromFile("numbers.txt");
		System.out.println("numList size: " + numList.size());
		System.out.println("numList: " + numList);
		Collections.sort(numList);
		System.out.println("Sorting numList...");
		System.out.println("numList: " + numList);
		System.out.println();
		
		ArrayList<Integer> misList = getMissingNumbers(numList);
		System.out.println("Adding missing numbers to misList...");
		System.out.println("misList size: " + misList.size());
		System.out.println("misList: " + misList);
		System.out.println();
		
		ArrayList<String> ansList = getConsecutiveNumbers(misList);
		System.out.println("Adding consecutive numbers to ansList...");
		System.out.println("ansList size: " + ansList.size());
		System.out.println("ansList: " + ansList);
		System.out.println();
		
		System.out.println(toString(ansList));
		
	}
	
	public static ArrayList<Integer> getListFromFile( String filename ) {
		ArrayList<Integer> numList = new ArrayList<Integer>();
		Scanner input = null;
		
		try {
			input = new Scanner(new java.io.File(filename));
		}
		catch (java.io.FileNotFoundException e) {
			System.out.println("Couldn't open '" + filename + "': " + e);
			System.exit(1);
		}
		while ( input.hasNextInt() ) {
			int number = input.nextInt();
			numList.add(number);
		}
		input.close();
		return numList;
	}
	
	public static ArrayList<Integer> getMissingNumbers( ArrayList<Integer> numList ) {
		ArrayList<Integer> misList = new ArrayList<Integer>();
		int counter = 1;
		
		for ( int num : numList ) {
			while ( num != counter ) {
				misList.add(counter);
				counter++;
			}
			counter++;
		}
		return misList;
	}
	
	public static ArrayList<String> getConsecutiveNumbers( ArrayList<Integer> misList ) {
		ArrayList<String> ansList = new ArrayList<String>();
		Integer minNum = 0;
		Integer maxNum = 0;
		
		minNum = misList.get(0);
		for ( int i=1; i<misList.size(); i++ ) {
			if (misList.get(i) - misList.get(i-1) > 1 ) {
				maxNum = misList.get(i-1);
				if ( maxNum > minNum ) {
					ansList.add(minNum + "-" + maxNum);
				}
				else {
					ansList.add(minNum.toString());
				}
				minNum = misList.get(i);
			}
			if ( i == misList.size()-1 ) {
				maxNum = misList.get(i);
				if ( maxNum > minNum ) {
					ansList.add(minNum + "-" + maxNum);
				}
				else {
					ansList.add(minNum.toString());
				}
			}
		}
		return ansList;
	}
	
	public static String toString( ArrayList<String> ansList ) {
		String out = "";
		System.out.print("De volgende getallen ontbreken: ");
		for ( int i=0; i<ansList.size(); i++ ) {
			if ( i == ansList.size()-1 ) {
				out += " en " + ansList.get(i);
			}
			else if ( i == ansList.size()-2 ) {
				out += ansList.get(i);
			}
			else {
				out += ansList.get(i) + ", ";
			}
		}
		return out;
	}

}
