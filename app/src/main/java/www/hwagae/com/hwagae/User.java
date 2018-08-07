package www.hwagae.com.hwagae;

public class User {

    public String userName;
    public String email;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)

    }

    public User(String userName, String email) {
        this.userName = userName;
        this.email = email;
    }

    public User(String userName) {
        this.userName = userName;
    }

}
