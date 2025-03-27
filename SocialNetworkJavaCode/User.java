import java.util.ArrayList;
import java.util.List;

/**
 * The User class represents a user with a name, 
 * email address, status, and a list of friends.
 */
public class User {
    private String name;
    private String email;
    private String status;
    private List<User> friends;

    /**
     * Constructs a User object with the specified name, 
     * email, and status.
     *
     * @param name the name of the user
     * @param email the email of the user
     * @param status the status of the user
     */
    public User(String name, String email, String status) {
        this.name = name;
        this.email = email;
        this.status = status;
        this.friends = new ArrayList<>(); // Initialize friends list
    }

    /**
     * Gets the name of the user.
     *
     * @return the name of the user
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the status of the user.
     *
     * @return the status of the user
     */
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Gets the email address of the user.
     *
     * @return the email address of the user
     */
    public String getEmail() {
        return email;
    }

    /**
     * Changes the email address of the user.
     *
     * @param email the new email address
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Adds a friend to the user's friend list.
     *
     * @param friend the User object to be added as a friend
     * @return true if the friend was added, false if already a friend
     */
    public boolean addFriend(User friend) {
        if (!friends.contains(friend)) {
            friends.add(friend);
            return true; // Successfully added
        }
        return false; // Already a friend
    }

    /**
     * Removes a friend from the user's friend list.
     *
     * @param friend the User object to be removed from friends
     * @return true if the friend was removed, false if not found
     */
    public boolean removeFriend(User friend) {
        return friends.remove(friend);
    }

    /**
     * Gets a list of the user's friends.
     *
     * @return a list of the user's friends
     */
    public List<User> getFriends() {
        return new ArrayList<>(friends); // Return a copy to prevent external modification
    }

    /**
     * Checks if two users are friends.
     *
     * @param friend the User object to check
     * @return true if they are friends, false otherwise
     */
    public boolean hasFriend(User friend) {
        return friends.contains(friend);
    }

    /**
     * Compares this User with another User for equality 
     * based on their names.
     *
     * @param otherUser the other user to compare
     * @return true if the names are equal, false otherwise
     */
    public boolean equals(User otherUser) {
        return this.name.equals(otherUser.getName());
    }
}