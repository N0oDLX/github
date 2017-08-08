import java.util.Scanner;

public class AddressBookDriver {

	public static void main(String[] args) {
	
		AddressBook db = new AddressBook();
		Scanner input = new Scanner(System.in);
		
		int choice;
		String file, oldFile, newFile, firstName, lastName, letter, number, address, email, field, newFirstName, newLastName, newNumber, newAddress, newEmail;
		
		do {
			System.out.println("1) Load from file(s)");
			System.out.println("2) Save to single file");
			System.out.println("3) Save to multiple files");
			System.out.println("4) Add an entry");
			System.out.println("5) Remove an entry");
			System.out.println("6) Edit an existing entry");
			System.out.println("7) Sort the address book");
			System.out.println("8) Search for a specific entry");
			System.out.println("9) Move an entry between files");
			System.out.println("0) Quit");
			System.out.print("\nPlease choose what you'd like to do with the database: ");
			choice = input.nextInt();
			input.nextLine();
			
			while ( !db.isValid(choice) ) {
				System.out.print("Please choose a valid option: ");
				choice = input.nextInt();
				input.nextLine();
			}
			
			if ( choice == 1 ) {
				System.out.print("Enter the name of the files you wish to load into the database: ");
				file = input.nextLine();
				String[] filenames = file.split(" ");
				db.loadFromFiles(filenames);
				System.out.println(db.toString());
			}
			else if ( choice == 2 ) {
				System.out.print("Enter the name of the save file: ");
				file = input.next();
				db.saveToFile(file);
				System.out.println();
			}
			else if ( choice == 3 ) {
				System.out.print("Enter the names of the save files: ");
				file = input.nextLine();
				System.out.println();
				String[] filenames = file.split(" ");
				String[] letters = new String[filenames.length];
				for ( int i=0; i<filenames.length; i++ ) {
					System.out.print("A-Z for file '" + filenames[i] + "': ");
					letter = input.next();
					letters[i] = letter;
				}
				db.saveToMultipleFiles(filenames, letters);
				System.out.println();
			}
			else if ( choice == 4 ) {
				System.out.print("Enter a name, number, address and email: ");
				firstName = input.next();
				lastName = input.next();
				number = input.next();
				address = input.next();
				email = input.next();
				db.addEntry(firstName, lastName, number, address, email);
				System.out.println(db.toString());
			}
			else if ( choice == 5 ) {
				System.out.print("Enter the name of the entry that you want to remove: ");
				firstName = input.next();
				lastName = input.next();
				db.removeEntry(firstName, lastName);
				System.out.println(db.toString());
			}
			else if ( choice == 6 ) {
				System.out.print("Enter the name of the entry that you want to edit: ");
				firstName = input.next();
				lastName = input.next();
				System.out.print("Enter the new name, number, address and email of the entry: ");
				newFirstName = input.next();
				newLastName = input.next();
				newNumber = input.next();
				newAddress = input.next();
				newEmail = input.next();
				db.editEntry(firstName, lastName, newFirstName, newLastName, newNumber, newAddress, newEmail);
				System.out.println(db.toString());
			}
			else if ( choice == 7 ) {
				System.out.print("By which field would you like to sort the database? ");
				field = input.next();
				db.sortByField(field);
				System.out.println(db.toString());
			}
			else if ( choice == 8 ) {
				System.out.print("Find an entry by entering the first letter of the last name: ");
				letter = input.next();
				db.findEntry(letter);
			}
			else if ( choice == 9 ) {
				System.out.print("Enter the names of the source file and the first and last name of the entry: ");
				oldFile = input.next();
				firstName = input.next();
				lastName = input.next();
				System.out.print("Enter the name of the destination file: ");
				newFile = input.next();
				db.moveEntry(oldFile, firstName, lastName, newFile);
				System.out.println();
			}
			else if ( choice == 0 ) {
				System.exit(1);
			}
			
		} while ( choice != 0 );
		input.close();
	}
}
