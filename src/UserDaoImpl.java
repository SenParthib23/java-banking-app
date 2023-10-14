import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserDaoImpl implements UserDao {

    Connection connection;

    public UserDaoImpl() throws SQLException {
        connection = ConnectionFactory.getConnection();
    }

    @Override
    public void addUser(User user) throws SQLException {
        String sql = "insert into user (name, email, balance) values (?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,user.getName());
        preparedStatement.setString(2, user.getEmail());
        preparedStatement.setLong(3, user.getBalance());
        int count = preparedStatement.executeUpdate();
        if(count > 0){
            System.out.println("Your account has been created !!!");
        }else{
            System.out.println("Oops ! Something went wrong..");
        }

    }

//    @Override
//    public void viewBalance(User user) throws SQLException {
//        String userEmail = user.getEmail();
//        String column = "balance";
//        String sql = "select" + column + "from user where email = ?";
//        PreparedStatement preparedStatement = connection.prepareStatement(sql);
//        ResultSet resultSet = preparedStatement.executeQuery();
//        preparedStatement.setString(1,userEmail);
//        resultSet.next();
//        while(resultSet != null){
////            int acc_no = resultSet.getInt(1);
////            String name = resultSet.getString(2);
////            String email = resultSet.getString(1);
//            long presentBalance = resultSet.getLong(column);
////            user = new User(email, balance);
//            System.out.println("Your available balance is: " + user.getBalance());
//        }
//    }
@Override
public void viewBalance(String email, long pin) {
        double balance = 0.0;
        String selectSQL = "SELECT balance FROM user WHERE email = ? and securitypin = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)) {
            preparedStatement.setString(1, email);
            preparedStatement.setLong(2, pin);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                balance = resultSet.getDouble("balance");
                System.out.println("Balance for user with email " + email + " is: " + balance);
            } else {
                System.out.println("Please provide correct credentials !!!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void makeDeposit(User user) throws SQLException {
        Scanner sc  = new Scanner(System.in);
        System.out.println("Enter the amount to be deposited: ");
        long deposit = Long.parseLong(sc.next());
        sc.nextLine();
        if(deposit <= 0){
            System.out.println("Enter a valid amount..");
        }else{
            String sql = "update user set balance = balance + ? where acc_no = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, deposit);
            preparedStatement.setInt(2,user.getAcc_no());
            int count = preparedStatement.executeUpdate();

            if(count > 0){
                System.out.println("Amount successfully deposited...");
                System.out.println("New balance is: " + ((user.getBalance()) + deposit));
            }else{
                System.out.println("Opps!! Something went wrong...");
            }
        }

    }

    @Override
    public void makeWithdrawal(User user) throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the amount to be withdrawn: ");
        long withdraw = Long.parseLong(sc.next());
        sc.nextLine();
        if(withdraw > user.getBalance()){
            System.out.println("Insufficient balance...");
        }else{
            String sql = "update user set balance = balance - ? where acc_no = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, withdraw);
            preparedStatement.setInt(2,user.getAcc_no());
            int count = preparedStatement.executeUpdate();

            if(count > 0){
                System.out.println("Amount debited successfully!!");
                System.out.println("Available balance: " + ((user.getBalance()) - withdraw));
            }else{
                System.out.println("Opps!! Something went wrong...");
            }
        }
    }

    @Override
    public List<User> getUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        String sql = "select * from user";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while(resultSet.next()){
            int acc_no = resultSet.getInt(1);
            String name = resultSet.getString(2);
            String email = resultSet.getString(3);
            long balance = resultSet.getLong(4);
            long securitypin = resultSet.getLong(5);
            User user = new User(acc_no, name, email, balance, securitypin);
            users.add(user);
            System.out.println("Account No: " + user.getAcc_no() + ", Name: " + user.getName() + ", Email: " + user.getEmail() + ", Balance: " + user.getBalance());
        }
        return users;
    }

    @Override
    public User getUserById() throws SQLException {
        return null;
    }
}
