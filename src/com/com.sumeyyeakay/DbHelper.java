import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbHelper
{
    private String userName= "root";
    private String password = "1234";

    private String dbUrl = "jdbc:mysql://localhost:3306/world?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    //turkiye saati hatasi aldigimiz icin koda ?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    //kismini ekledik

    public Connection getConnection() throws SQLException
    {
        return DriverManager.getConnection(dbUrl,userName,password);
    }

    //hatayi yonetmek icin;
    public void showErrorMessage(SQLException exception) {
        System.out.println("Error : " + exception.getMessage());
        System.out.println("Error Code : " + exception.getErrorCode());
    }
}
