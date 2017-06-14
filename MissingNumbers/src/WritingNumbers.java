import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

public class WritingNumbers {

	public static void main(String[] args) throws Exception {
		
		PrintWriter fileout = new PrintWriter("numbers.txt");
		ArrayList<Integer> arr1 = new ArrayList<Integer>();
		ArrayList<Integer> arr2 = new ArrayList<Integer>();
		
		for ( int i=0; i<1000; i++ ) {
			arr1.add(i+1);
		}
		
		System.out.println("arr1 size: " + arr1.size());
		System.out.println(arr1);
		System.out.println();
		
		arr1.set(28, 0);
		arr1.set(43, 0);
		arr1.set(44, 0);
		arr1.set(45, 0);
		arr1.set(46, 0);
		arr1.set(310, 0);
		arr1.set(720, 0);
		arr1.set(721, 0);
		
		for ( int i=0; i<1000; i++ ) {
			if ( arr1.get(i) != 0 ) {
				arr2.add(arr1.get(i));
			}
		}
		
		System.out.println("arr2 size: " + arr2.size());
		System.out.println(arr2);
		Collections.shuffle(arr2);
		System.out.println("sorting arr2...");
		System.out.println(arr2);
		
		for ( int a : arr2 ) {
			fileout.println(a);
		}
		fileout.close();
	}
}
