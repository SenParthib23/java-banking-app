import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    void addUser(User user) throws SQLException;
    void viewBalance(String email, long pin) throws SQLException;

    void makeDeposit(User user) throws SQLException;
    void makeWithdrawal(User user) throws SQLException;

    List<User> getUsers() throws SQLException;
    User getUserById() throws SQLException;
}
