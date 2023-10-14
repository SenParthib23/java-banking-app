import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws SQLException, SQLException, IOException {
        User user = new User();
        Account account = new Account();

        UserDao userDao = UserDaoFactory.getUserDao();
        Scanner sc = new Scanner(System.in);

        boolean flag = true;
        while(true){
            System.out.println("Select from the below options...");
            System.out.println("1) Register");
            System.out.println("2) Login");
            System.out.print("Enter Your Choice: ");
            int num = Integer.parseInt(sc.next());
            switch (num){
                case 1:{
                    System.out.println("Enter Name: ");
                    String name = sc.next();
                    System.out.println("Enter Email: ");
                    String email = sc.next();
                    System.out.println("Create a password: ");
                    String password = sc.next();
                    account.registerUser(name, email, password);
                    break;
                }
                case 2:{
                    System.out.println("Enter email: ");
                    String email = sc.next();
                    System.out.println("Enter password: ");
                    String password = sc.next();
                    account.loginUser(email, password);
                    if(account.loginUser(email,password)){
                        boolean flag2 = true;
                        while(flag2){
                            System.out.println("********** Choose from out Banking Services ***********");
                            System.out.println("1. Open Account");
                            System.out.println("2. Check Balance");
                            System.out.println("3. Deposit Money");
                            System.out.println("4. Withdraw Money");
                            System.out.println("5. Logout");
                            System.out.print("Enter Your Choice: ");
                            int input = sc.nextInt();
                            switch (input){
                                case 1:{
                                    System.out.println("Enter Name: ");
                                    String username = sc.next();
                                    System.out.println("Enter Email: ");
                                    String useremail = sc.next();
                                    System.out.println("Make an initial deposit: ");
                                    long deposit = Long.parseLong(sc.next());
                                    user.setName(username);
                                    user.setEmail(useremail);
                                    user.setBalance(deposit);
                                    userDao.addUser(user);
                                    break;
                                }
                                case 2:{
                                    System.out.println("Enter your email: ");
                                    String useremail = sc.next();
                                    System.out.println("Enter your security pin: ");
                                    long userpin = Long.parseLong(sc.next());
                                    user.setEmail(useremail);
                                    user.setSecuritypin(userpin);
                                    userDao.viewBalance(useremail, userpin);
                                    break;
                                }
                                case 3:{
                                    System.out.println("Enter Your Account Number: ");
                                    int number = sc.nextInt();
                                    user.setAcc_no(number);
                                    userDao.makeDeposit(user);
                                    break;
                                }
                                case 4:{
                                    System.out.println("Enter Your Account Number: ");
                                    int number = sc.nextInt();
                                    user.setAcc_no(number);
                                    userDao.makeWithdrawal(user);
                                    break;
                                }
                                case 6:{
                                    flag2 = false;
                                    break;
                                }
                            }
                        }
                    }else{
                        break;
                    }
                    break;
                }
            }
        }

    }
}