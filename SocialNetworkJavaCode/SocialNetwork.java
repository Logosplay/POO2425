import java.util.List;
import java.util.ArrayList;

/**
 * The ContactBook class represents a collection of users.
 */
public class SocialNetwork {
    static final int DEFAULT_SIZE = 100;

    private int counter;
    private User[] users;

    /**
     * Constructs a ContactBook with a default size and initializes the counter.
     */
    public SocialNetwork() {
        counter = 0;
        users = new User[DEFAULT_SIZE];
    }

    /**
     * Checks if a contact with the given name exists in the contact book.
     *
     * @param name the name of the contact to check
     * @return true if the contact exists, false otherwise
     */
    public boolean hasUser(String name) {
        return searchIndex(name) >= 0;
    }

    public boolean isEmpty() {
        return counter == 0;
    }                          

    /**
     * Gets the number of users in the contact book.
     *
     * @return the number of users
     */
    public int getNumberOfusers() {
        return counter;

    }

    /**
     * Adds a new contact to the contact book.
     *
     * @param name  the name of the contact
     * @param phone the phone number of the contact
     * @param email the email address of the contact
     * @pre !hasContact(name)
     */
    public void registerUser(String name, String status, String email) {
        if (counter == users.length)
            resize();
        users[counter] = new User(name, status, email);
        counter++;
    }

    /**
     * Deletes a contact from the contact book.
     *
     * @param name the name of the contact to delete
     * @pre hasContact(name)
     */
    public void deleteContact(String name) {
        int index = searchIndex(name);
        for (int i = index; i < counter; i++)
            users[i] = users[i + 1];
        counter--;
    }

    /**
     * Gets the phone number of a contact with the given name.
     *
     * @param name the name of the contact
     * @return the phone number of the contact
     * @pre hasContact(name)
     */
    public String getStatus (String name) {
        return users[searchIndex(name)].getStatus();
    }

    /**
     * Gets the email address of a contact with the given name.
     *
     * @param name the name of the contact
     * @return the email address of the contact
     * @pre hasContact(name)
     */
    public String getEmail(String name) {
        return users[searchIndex(name)].getEmail();
    }

    /**
     * Changes the phone number of a contact with the given name.
     *
     * @param name  the name of the contact
     * @param phone the new phone number
     * @pre hasContact(name)
     */

    public void setStatus(String name, String status) {
        users[searchIndex(name)].setStatus(status);
    }

    /**
     * Changes the email address of a contact with the given name.
     *
     * @param name  the name of the contact
     * @param email the new email address
     * @pre hasContact(name)
     */
    public void setEmail(String name, String email) {
        users[searchIndex(name)].setEmail(email);
    }

    public void addFriend(String name1, String name2) {
        int i = searchIndex(name1);
        int j = searchIndex(name2);
    
        // If either user is not found, return
        if (i == -1 || j == -1) {
            return;
        }
    
        users[i].addFriend(users[j]);
        users[j].addFriend(users[i]);
    }



    private int searchIndex(String name) {
        int i = 0;
        int result = -1;
        boolean found = false;
        while (i < counter && !found)
            if (users[i].getName().equals(name))
                found = true;
            else
                i++;
        if (found) result = i;
        return result;
    }


    public boolean hasFriend(String name1, String name2) {
        int i = searchIndex(name1);
        int j = searchIndex(name2);
    
        // If either user is not found, return false
        if (i == -1 || j == -1) {
            return false;
        }
    
        return users[i].hasFriend(users[j]);
    }

    public List<User> getFriends(String name) {
        int i = searchIndex(name);
    
        // If user is not found, return an empty list
        if (i == -1) {
            return new ArrayList<>();
        }

        return users[i].getFriends(); // Return the friends list
    }

    public List <User> getUsers() {
        List<User> userList = new ArrayList<>();
        for (int i = 0; i < counter; i++) {
            userList.add(users[i]);
        }
        return userList;
    }

    private void resize() {
        User tmp[] = new User[2 * users.length];
        for (int i = 0; i < counter; i++)
            tmp[i] = users[i];
        users = tmp;
    }
}