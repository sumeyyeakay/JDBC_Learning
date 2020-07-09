import java.sql.*;
import java.util.ArrayList;

public class Database
{

    Connection connection = null;
    DbHelper dbHelper = new DbHelper();

    public  void Select()  throws SQLException
    {

        //JDBC de sql sorgusu olusturmak;
        Statement statement = null;
        //sorgu sonucunda gelen data ResultSet
        ResultSet resultSet;
        try{
            //baglanti olusturmak icin
            connection = dbHelper.getConnection();
            System.out.println("SQL Baglantisi Olustu");

            //sql cumlesini gostermek icin gerekli adimlar
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select Code,Name,Continent,Region from country");
            //gelen datayi ekrana bastirmak icin;

            ArrayList<Country> countries = new ArrayList<>();
            while (resultSet.next()) {
                //nesne seklinde datalari atmak icin
                countries.add(new Country(
                        resultSet.getString("Code"),
                        resultSet.getString("Name"),
                        resultSet.getString("Continent"),
                        resultSet.getString("Region")));
            }
            System.out.println(countries.size());
        } catch (SQLException exception) {
            dbHelper.showErrorMessage(exception);
        }
        finally {
            //baglanti kapatmak icin
            connection.close();
        }
    }

    public  void Insert()  throws SQLException
    {
        //JDBC de insert islemi yapmak icin: PreparedStatement
        PreparedStatement statement = null;
        //sorgu sonucunda gelen data ResultSet
        ResultSet resultSet;

        try{
            //baglanti olusturmak icin
            connection = dbHelper.getConnection();
            String sql = "insert into city" +
                    "(Name , CountryCode, District,Population) values(?,?,?,?)";
            //cumleyi hazirla demek:
            statement = connection.prepareStatement(sql);
            statement.setString(1,"Izmir");
            statement.setString(2,"TUR");
            statement.setString(3,"Turkey");
            statement.setInt(4,50000);
            //etkilenen kayit sayisi gelir
            int result = statement.executeUpdate();
            System.out.println("Insert Islemi Yapildi : " + result);
        } catch (SQLException exception) {
            dbHelper.showErrorMessage(exception);
        }
        finally {
            //baglanti kapatmak icin
            statement.close();
            connection.close();
        }


    }

    public void Update() throws SQLException
    {
        PreparedStatement statement = null;
        try{
            //baglanti olusturmak icin
            connection = dbHelper.getConnection();
            String sql = "update  city set population = 100000 where id = ? ";
            //cumleyi hazirla demek:
            statement = connection.prepareStatement(sql);
            statement.setInt(1,12000);
            int result = statement.executeUpdate();
            System.out.println("Kayit Guncellendi ");
        } catch (SQLException exception) {
            dbHelper.showErrorMessage(exception);
        }
        finally {
            //baglanti kapatmak icin
            statement.close();
            connection.close();
        }
    }
    public void Delete() throws SQLException
    {
        PreparedStatement statement = null;
        try{
            //baglanti olusturmak icin
            connection = dbHelper.getConnection();
            String sql = "delete from city where id = ? ";
            //cumleyi hazirla demek:
            statement = connection.prepareStatement(sql);
            statement.setInt(1,12000);
            int result = statement.executeUpdate();
            System.out.println("Kayit Silindi ");
        } catch (SQLException exception) {
            dbHelper.showErrorMessage(exception);
        }
        finally {
            //baglanti kapatmak icin
            statement.close();
            connection.close();
        }
    }
}
