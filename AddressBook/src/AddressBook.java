import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Scanner;
import java.io.PrintWriter;

public class AddressBook {
	
	private ArrayList<Address> book;
	private ArrayList<Address> oldData;
	private ArrayList<Address> newData;
	
	public AddressBook() {
		book = new ArrayList<>();
		oldData = new ArrayList<>();
		newData = new ArrayList<>();
	}
	
	public boolean isValid( int input ) {
		if ( input < 1 || input > 9 ) {
			return false;
		}
		return true;
	}
	
	public boolean containsEntry( String entry ) {
		Iterator<Address> i = book.iterator();
		while ( i.hasNext() ) {
			Address a = i.next();
			if ( entry.length() == 1 && a.lastName.startsWith(entry) ) {
				return true;
			}
			else if ( entry.length() > 1 ) {
				String[] split = entry.split(" ");
				if ( a.firstName.equals(split[0]) && a.lastName.equals(split[1]) ) {
					return true;
				}
			}
		}
		return false;
	}
	
	public void loadFromFiles( String[] filenames ) {
		System.out.println();
		for ( String file : filenames ) {
			Scanner input = null;
			
			try {
				input = new Scanner(new java.io.File(file));
			}
			catch (java.io.FileNotFoundException e) {
				System.out.println("Couldn't open '" + file + "': " + e);
				System.exit(1);
			}
			
			while ( input.hasNext() ) {
				Address a = new Address();
				a.firstName = input.next();
				a.lastName = input.next();
				a.number = input.next();
				a.address = input.next();
				a.email = input.next();
				book.add(a);
			}
			input.close();
			System.out.println("All entries from file '" + file + "' have been added to the database.");
		}
	}
	
	public void saveToFile( String filename ) {
		PrintWriter fileout = null;
		
		try {
			fileout = new PrintWriter(filename);
		}
		catch ( java.io.FileNotFoundException e ) {
			System.out.println("Couldn't create '" + filename + "': " + e);
			System.exit(1);
		}
		
		for ( Address a : book ) {
			fileout.println(a.firstName + " " + a.lastName + " " + a.number + " " + a.address + " " + a.email);
		}
		fileout.close();
		System.out.println("\nAll database entries have been copied to file '" + filename + "'.");
	}
	
	public void saveToMultipleFiles( String[] filenames, String[] letters ) {
		System.out.println();
		for ( int i=0; i<filenames.length; i++ ) {
			PrintWriter fileout = null;
	
			try {
				fileout = new PrintWriter(filenames[i]);
			}
			catch ( java.io.FileNotFoundException e ) {
				System.out.println("Couldn't create '" + filenames[i] + "': " + e);
				System.exit(1);
			}	
		
			String[] split = letters[i].split("-");
			for ( char j=split[0].charAt(0); j<=split[1].charAt(0); j++ ) {
				Iterator<Address> k = book.iterator();
				while ( k.hasNext() ) {
					Address a = k.next();
					if ( a.lastName.startsWith(""+j) ) {
						fileout.println(a.firstName + " " + a.lastName + " " + a.number + " " + a.address + " " + a.email);
					}
				}
			}
			fileout.close();
		}
		System.out.println("The entries have been copied to the files.");
	}
	
	public void moveEntry( String oldFile, String firstName, String lastName, String newFile ) {
		copyOldData(oldFile);
		copyNewData(newFile);
		copyEntry(firstName,lastName);
		saveOldData(oldFile);
		saveNewData(newFile);
	}
	
	public void copyOldData( String oldFile ) {
		Scanner input = null;
			
		try {
			input = new Scanner(new java.io.File(oldFile));
		}
		catch (java.io.FileNotFoundException e) {
			System.out.println("Couldn't open '" + oldFile + "': " + e);
			System.exit(1);
		}
			
		while ( input.hasNext() ) {
			Address a = new Address();
			a.firstName = input.next();
			a.lastName = input.next();
			a.number = input.next();
			a.address = input.next();
			a.email = input.next();
			oldData.add(a);
		}
		input.close();
	}
	
	public void copyNewData( String newFile ) {
		Scanner input = null;
			
		try {
			input = new Scanner(new java.io.File(newFile));
		}
		catch (java.io.FileNotFoundException e) {
			System.out.println("Couldn't open '" + newFile + "': " + e);
			System.exit(1);
		}
			
		while ( input.hasNext() ) {
			Address a = new Address();
			a.firstName = input.next();
			a.lastName = input.next();
			a.number = input.next();
			a.address = input.next();
			a.email = input.next();
			newData.add(a);
		}
		input.close();
	}
	
	public void copyEntry( String firstName, String lastName ) {
		Iterator<Address> i = oldData.iterator();
		while ( i.hasNext() ) {
			Address a = i.next();
			if ( a.firstName.equals(firstName) && a.lastName.equals(lastName) ) {
				newData.add(a);
				oldData.remove(a);
				System.out.println("\nEntry '" + firstName + " " + lastName + "' has been copied.");
				return;
			}
		}
	}
	
	public void saveOldData ( String oldFile ) {
		Collections.sort(oldData, AddressBook.lastNameComparator);
		PrintWriter fileout = null;
		
		try {
			fileout = new PrintWriter(oldFile);
		}
		catch ( java.io.FileNotFoundException e ) {
			System.out.println("Couldn't create '" + oldFile + "': " + e);
			System.exit(1);
		}
		
		for (  Address a : oldData ) {
			fileout.println(a.firstName + " " + a.lastName + " " + a.number + " " + a.address + " " + a.email);
		}
		fileout.close();
	}
	
	public void saveNewData ( String newFile ) {
		Collections.sort(newData, AddressBook.lastNameComparator);
		PrintWriter fileout = null;
		
		try {
			fileout = new PrintWriter(newFile);
		}
		catch ( java.io.FileNotFoundException e ) {
			System.out.println("Couldn't create '" + newFile + "': " + e);
			System.exit(1);
		}
		
		for (  Address a : newData ) {
			fileout.println(a.firstName + " " + a.lastName + " " + a.number + " " + a.address + " " + a.email);
		}
		fileout.close();
	}
	
	public void addEntry( String firstName, String lastName, String number, String address, String email ) {
		Address a = new Address();
		a.firstName = firstName;
		a.lastName = lastName;
		a.number = number;
		a.address = address;
		a.email = email;
		book.add(a);
		System.out.println("\nAn entry with the name '" + firstName + " " + lastName + "' has been created.");
	}
	
	public void removeEntry( String firstName, String lastName ) {
		if ( ! containsEntry(firstName + " " + lastName) ) {
			System.out.println("\nNo entry with the name '" + firstName + " " + lastName + "' has been found.");
			return;
		}
		Iterator<Address> i = book.iterator();
		while ( i.hasNext() ) {
			Address a = i.next();
			if ( a.firstName.equals(firstName) && a.lastName.equals(lastName) ) {
				i.remove();
				System.out.println("\nAn entry with the name '" + firstName + " " + lastName + "' has been removed.");
			}
		}
	}
	
	public void editEntry( String firstName, String lastName,  String newFirstName, String newLastName, String newNumber, String newAddress, String newEmail ) {
		if ( ! containsEntry(firstName + " " + lastName) ) {
			System.out.println("\nNo entry with the name '" + firstName + " " + lastName + "' has been found.");
			return;
		}
		Iterator<Address> i = book.iterator();
		while ( i.hasNext() ) {
			Address a = i.next();
			if ( a.firstName.equals(firstName) && a.lastName.equals(lastName) ) {
				a.firstName = newFirstName;
				a.lastName = newLastName;
				a.number = newNumber;
				a.address = newAddress;
				a.email = newEmail;
				System.out.println("\nAn entry with the name '" + firstName + " " + lastName + "' has been altered.");
			}
		}
	}
	
	public void sortByField( String field ) {
		if ( field.equals("firstName")) {
			Collections.sort(book, AddressBook.firstNameComparator);
		}
		else if ( field.equals("lastName")) {
			Collections.sort(book, AddressBook.lastNameComparator);
		}
		else if ( field.equals("number")) {
			Collections.sort(book, AddressBook.numberComparator);
		}
		else if ( field.equals("address")) {
			Collections.sort(book, AddressBook.addressComparator);
		}
		else {
			Collections.sort(book, AddressBook.emailComparator);
		}
	}
	
	public static Comparator<Address> firstNameComparator = new Comparator<Address>() {
		public int compare( Address address1, Address address2 ) {
			int res = address1.firstName.compareTo(address2.firstName);
			if ( res != 0 )
				return res;
			return address1.lastName.compareTo(address2.lastName);
		}
	};
	
	public static Comparator<Address> lastNameComparator = new Comparator<Address>() {
		public int compare( Address address1, Address address2 ) {
			int res = address1.lastName.compareTo(address2.lastName);
			if ( res != 0 )
				return res;
			return address1.firstName.compareTo(address2.firstName);
		}
	};
	
	public static Comparator<Address> numberComparator = new Comparator<Address>() {
		public int compare( Address address1, Address address2 ) {
			return address1.number.compareTo(address2.number);
		}
	};
	
	public static Comparator<Address> addressComparator = new Comparator<Address>() {
		public int compare( Address address1, Address address2 ) {
			return address1.address.compareTo(address2.address);
		}
	};
	
	public static Comparator<Address> emailComparator = new Comparator<Address>() {
		public int compare( Address address1, Address address2 ) {
			return address1.email.compareTo(address2.email);
		}
	};
	
	public void findEntry( String letter ) {
		if ( ! containsEntry(letter) ) {
			System.out.println("\nNo entry which's last name starts with the letter '" + letter + "' has been found.\n");
			return;
		}
		Iterator<Address> i = book.iterator();
		System.out.println("\nThe following entries have been found: ");
		while ( i.hasNext() ) {
			Address a = i.next();
			if ( a.lastName.startsWith(letter) ) {
				System.out.println(a.firstName + " " + a.lastName + " " + a.number + " " + a.address + " " + a.email);
			}
		}
		System.out.println();
	}
	
	public String toString() {
		String out = "";
		out += "\nThe database now contains the following entries: \n";
		for ( int i=0; i<book.size(); i++ )
			out += book.get(i).firstName + " " + book.get(i).lastName + " " + book.get(i).number + " " + book.get(i).address + " " + book.get(i).email + "\n";
		return out;
	}
	
}
