import java.util.Scanner;

public class Main{

    public static final String NETWORK_EMPTY = "Empty social network.";
    public static final String REGISTER_SUCCESS = "Successfully registered.";
    public static final String REGISTER_ERROR = "Already registered.";
    public static final String FRIENDSHIP_EXIST = "Friendship already exists.";
    public static final String FRIENDSHIP_NOTEXIST = "Non-existent friendship.";
    public static final String NO_FRIENDS = "No registered friends.";
    public static final String REGISTER_NOTEXIST = "Not registered.";
    public static final String REGISTER_EXIST = "Already registered.";
    public static final String FRIENDSHIP_SUCCESS = "Friendship created.";
    public static final String FRIENDSHIP_ERROR = "Invalid friendship.";
    public static final String STATUS_UPDATE = "Status updated.";
    public static final String QUIT_MSG = "Bye.";
    public static final String COMMAND_ERROR = "There was an error.";

    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        SocialNetwork sNetwork = new SocialNetwork();
        String comm;

        do {
            comm = getCommand(in);
            switch (comm) {
                case "REGISTERED":
                    registeredUser(in, sNetwork);
                    break;
                case "REGISTER" :
                    registerUser(in, sNetwork);
                    break;
                case "CHECKFRIENDSHIP":
                    checkFriend(in, sNetwork);
                    break;
                case "ADDFRIEND":
                    addFriend(in, sNetwork);
                    break;
                case "FRIENDS":
                    listFriends(in, sNetwork);
                    break;
                case "NEWSTATUS":
                    setStatus(in, sNetwork);
                    break;
                case "CHECKSTATUS":
                    checkStatus(in, sNetwork);
                    break;
                case "USERS":
                    listUsers(in, sNetwork);
                    break;
                case "QUIT":
                    System.out.println(QUIT_MSG);
                    break;
                default:
                    System.out.println(COMMAND_ERROR);
                    break;
            }
        } while (!comm.equals("Q"));
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

    private static void registeredUser(Scanner in, SocialNetwork sNetwork){
        String name = in.nextLine();
        
        if (sNetwork.hasUser(name) == true){
            System.out.println(REGISTER_EXIST);
        }
        else{
            System.out.println(REGISTER_NOTEXIST);
        }

    }

    private static void registerUser (Scanner in, SocialNetwork sNetwork){
        String name, status, email;

        name = in.nextLine();
        email = in.nextLine();
        status = in.nextLine();

        if (!sNetwork.hasUser(name)){
            sNetwork.registerUser(name, email, status);
            System.out.println(REGISTER_SUCCESS);
        }
        else{
            System.out.println(REGISTER_ERROR);
        }

    }

    private static void checkFriend(Scanner in, SocialNetwork sNetwork){
        String name1, name2;

        name1 = in.nextLine();
        name2 = in.nextLine();

        if (sNetwork.hasUser(name1) && sNetwork.hasUser(name2)){
            if (sNetwork.hasFriend(name1, name2)){
                System.out.println(FRIENDSHIP_EXIST);
            }
            else{
                System.out.println(FRIENDSHIP_NOTEXIST);
            }
        }
        else{
            System.out.println(FRIENDSHIP_NOTEXIST);
        }
    }

    private static void addFriend(Scanner in, SocialNetwork sNetwork){
        String name1, name2;

        name1 = in.nextLine();
        name2 = in.nextLine();

        if (sNetwork.hasUser(name1) && sNetwork.hasUser(name2)){
            if(name1.equals(name2)){
                System.out.println(FRIENDSHIP_ERROR);
            }
            else  if (!sNetwork.hasFriend(name1, name2)){
                    sNetwork.addFriend(name1, name2);
                    System.out.println(FRIENDSHIP_SUCCESS);
                }
                else{
                    System.out.println(FRIENDSHIP_EXIST);
                }
        }
        else{
            System.out.println(REGISTER_NOTEXIST);
        }
    }

    public static void listFriends(Scanner in, SocialNetwork sNetwork){
        String name = in.nextLine();

        if (sNetwork.hasUser(name)){
            if (sNetwork.getFriends(name).isEmpty()){
                System.out.println(NO_FRIENDS);
            }
            else{
                System.out.println("Friends list:");
                for (User friend : sNetwork.getFriends(name)) {
                    System.out.println(friend.getName() + "; " + friend.getEmail());
                }
            }
        }

        else{
            System.out.println(REGISTER_NOTEXIST);
        }
    }

    public static void setStatus(Scanner in, SocialNetwork sNetwork){
        String name, status;

        name = in.nextLine();
        status = in.nextLine();

        if (sNetwork.hasUser(name)){
            sNetwork.setStatus(name, status);
            System.out.println(STATUS_UPDATE);
        }
        else{
            System.out.println(REGISTER_NOTEXIST);
        }
    }

    public static void checkStatus(Scanner in, SocialNetwork sNetwork){
        String name = in.nextLine();

        if (sNetwork.hasUser(name)){
            System.out.println(sNetwork.getStatus(name));
        }
        else{
            System.out.println(REGISTER_NOTEXIST);
        }
    }

    public static void listUsers(Scanner in, SocialNetwork sNetwork){
        if (sNetwork.isEmpty()){
            System.out.println(NETWORK_EMPTY);
        }
        else{
            System.out.println("List of registered users:");
            for (User user : sNetwork.getUsers()) {
                System.out.println(user.getName() + "; " + user.getEmail());
            }
        }
    }
}