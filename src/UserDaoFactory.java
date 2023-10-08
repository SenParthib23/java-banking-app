import java.sql.SQLException;

public class UserDaoFactory {
    private static UserDao userDao;
    private UserDaoFactory(){

    }
    public static  UserDao getUserDao() throws SQLException{
        if(userDao == null){
            userDao = new UserDaoImpl();
        }
        return userDao;
    }
}
