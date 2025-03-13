import java.util.Scanner;


public class Main {
    // Constants defining the commands
    public static final String ADD_CONTACT = "AC";
    public static final String REMOVE_CONTACT = "RC";
    public static final String GET_PHONE = "GP";
    public static final String GET_EMAIL = "GE";
    public static final String GET_NAME = "GN";
    public static final String SET_PHONE = "SP";
    public static final String SET_EMAIL = "SE";
    public static final String EXISTS_PHONE = "EP";
    public static final String LIST_CONTACTS = "LC";
    public static final String QUIT = "Q";

    // Constants defining messages for the user
    public static final String CONTACT_EXISTS = "Contact already exists.";
    public static final String NAME_NOT_EXIST = "Contact does not exist.";
    public static final String PHONE_NOT_EXIST = "Phone number does not exist.";
    public static final String CONTACT_ADDED = "Contact added.";
    public static final String CONTACT_REMOVED = "Contact removed.";
    public static final String CONTACT_UPDATED = "Contact updated.";
    public static final String BOOK_EMPTY = "Contact book empty.";
    public static final String PHONE_SHARE = "There are contacts that share phone numbers.";
    public static final String PHONE_NOT_SHARE = "All contacts have different phone numbers.";
    public static final String QUIT_MSG = "Goodbye!";
    public static final String COMMAND_ERROR = "Unknown command.";

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ContactBook cBook = new ContactBook();
        String comm;

        do {
            comm = getCommand(in);
            switch (comm) {
                case ADD_CONTACT:
                    addContact(in, cBook);
                    break;
                case REMOVE_CONTACT:
                    deleteContact(in, cBook);
                    break;
                case GET_PHONE:
                    getPhone(in, cBook);
                    break;
                case GET_EMAIL:
                    getEmail(in, cBook);
                    break;
                case GET_NAME:
                    getName(in, cBook);
                    break;
                case SET_PHONE:
                    setPhone(in, cBook);
                    break;
                case SET_EMAIL:
                    setEmail(in, cBook);
                    break;
                case EXISTS_PHONE:
                    existsPhone(in, cBook);
                    break;
                case LIST_CONTACTS:
                    listAllContacts(cBook);
                    break;
                case QUIT:
                    System.out.println(QUIT_MSG);
                    break;
                default:
                    System.out.println(COMMAND_ERROR);
                    break;
            }
        } while (!comm.equals(QUIT));
        in.close();
    }


    /**
     * Reads a command from the user.
     *
     * @param in Scanner for reading user input
     * @return the command entered by the user
     */
    private static String getCommand(Scanner in) {
        String input;

        input = in.nextLine().toUpperCase();
        return input;
    }

    /**
     * Adds a new contact to the contact book based on user input.
     *
     * @param in Scanner for reading user input
     * @param cBook the ContactBook to add the contact to
     */
    private static void addContact(Scanner in, ContactBook cBook) {
        String name, email;
        int phone;

        name = in.nextLine();
        phone = in.nextInt();
        in.nextLine();
        email = in.nextLine();
        if (!cBook.hasContact(name)) {
            cBook.addContact(name, phone, email);
            System.out.println(CONTACT_ADDED);
        } else System.out.println(CONTACT_EXISTS);
    }

    /**
     * Deletes a contact from the contact book based on user input.
     *
     * @param in Scanner for reading user input
     * @param cBook the ContactBook to remove the contact from
     */
	private static void deleteContact(Scanner in, ContactBook cBook) {
		String name;
		name = in.nextLine();
		if (cBook.hasContact(name)) {
			cBook.deleteContact(name);
			System.out.println(CONTACT_REMOVED);
		}
		else System.out.println(NAME_NOT_EXIST);
	}

    /**
     * Gets the phone number of a contact from the contact book 
     * based on user input.
     *
     * @param in Scanner for reading user input
     * @param cBook the ContactBook to consult
     */
	private static void getPhone(Scanner in, ContactBook cBook) {
		String name;
		name = in.nextLine();
		if (cBook.hasContact(name)) {
			System.out.println(cBook.getPhone(name));
		}
		else System.out.println(NAME_NOT_EXIST);
	}

    /**
     * Gets the email of a contact from the contact book based on user input.
     *
     * @param in Scanner for reading user input
     * @param cBook the ContactBook to consult
     */
	private static void getEmail(Scanner in, ContactBook cBook) {
		String name;
		name = in.nextLine();
		if (cBook.hasContact(name)) {
			System.out.println(cBook.getEmail(name));
		}
		else System.out.println(NAME_NOT_EXIST);
	}

    /**
    * Retrieves the name of a contact from the contact book based on the provided phone number.
    *
    * @param in Scanner for reading user input
    * @param cBook the ContactBook to consult
    *
    * @return void. Prints the name of the contact if found, otherwise prints a message indicating that the phone number does not exist.
    */
    private static void getName(Scanner in, ContactBook cBook) {
        int phone;
        
        phone = in.nextInt();
        in.nextLine();
        
        if (cBook.hasPhone(phone)) {
            System.out.println(cBook.getName(phone)); // Fixed method name
        } else {
            System.out.println(PHONE_NOT_EXIST);
        }
    }

    /**
     * Changes the phone of a contact from the contact book based on user input.
     *
     * @param in Scanner for reading user input
     * @param cBook the ContactBook to update
     */
	private static void setPhone(Scanner in, ContactBook cBook) {
		String name;
		int phone;
		name = in.nextLine();
		phone = in.nextInt(); in.nextLine();
		if (cBook.hasContact(name)) {
			cBook.setPhone(name,phone);
			System.out.println(CONTACT_UPDATED);
		}
		else System.out.println(NAME_NOT_EXIST);
	}

   /**
     * Changes the email from the contact book based on user input.
     *
     * @param in Scanner for reading user input
     * @param cBook the ContactBook to update
     */
	private static void setEmail(Scanner in, ContactBook cBook) {
		String name;
		String email;
		name = in.nextLine();
		email = in.nextLine();
		if (cBook.hasContact(name)) {
			cBook.setEmail(name,email);
			System.out.println(CONTACT_UPDATED);
		}
		else System.out.println(NAME_NOT_EXIST);
	}
    
    private static void existsPhone(Scanner in, ContactBook cBook) {

        if (cBook.sharesPhone()) {
            System.out.println(PHONE_SHARE);
        } else {
            System.out.println(PHONE_NOT_SHARE);
        }
    }

	 /**
     * Lists all contacts from the contact book.
     *
     * @param cBook the ContactBook to list
     */
	private static void listAllContacts(ContactBook cBook) {
		if (cBook.getNumberOfContacts() != 0) {
			ContactIterator it = cBook.iterator();
			while( it.hasNext() ) {
				Contact c = it.next();
				System.out.println(c.getName() + "; " + c.getEmail() + "; " + c.getPhone());
			}
		}
		else System.out.println(BOOK_EMPTY);
	}
}
