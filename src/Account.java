import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Account {
    String name;
    String email;
    String password;

    Map<String, String> userCredentials = new HashMap<>();
    void registerUser(String name, String email, String password){
        if(!userCredentials.containsKey(email)){
            userCredentials.put(email, password);
            System.out.println("Registration successful. You can now log in.");
        }else{
            System.out.println("Try using different email...");
        }
    }
    public boolean loginUser(String name, String password) throws SQLException {
        if (userCredentials.containsKey(name) && userCredentials.get(name).equals(password)) {
            System.out.println("Login successful. Welcome, " + name + "!");
            return true;
        } else {
            System.out.println("Invalid username or password. Please try again.");
            return false;
        }
    }
}
