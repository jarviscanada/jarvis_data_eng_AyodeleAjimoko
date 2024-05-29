package ca.jrvs.apps.jdbc;
import ca.jrvs.apps.jdbc.util.CustomerDAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnectionManager {
  private final String url;
  private final Properties properties;


  public DatabaseConnectionManager(String host, String databaseName, String username, String password) {
    this.url = "jdbc:postgresql://" + host + "/" + databaseName;
    this.properties = new Properties();
    this.properties.setProperty("user", username);
    this.properties.setProperty("password", password);
  }

  public Connection getConnection() throws SQLException {
    return DriverManager.getConnection(this.url, this.properties);
  }


  public static class JDBCExecutor {

    public static void main(String... args) {
      DatabaseConnectionManager dcm = new DatabaseConnectionManager("localhost:5432", "hplusport",
          "postgres", "password");
      try {
        Connection connection = dcm.getConnection();
        CustomerDAO customerDAO = new CustomerDAO(connection);
        Customer customer = new Customer();
        customer.setFirstName("George");
        customer.setLastName("Washington");
        customer.setEmail("george.washington@wh.gov");
        customer.setPhone("(555) 555-6543");
        customer.setAddress("1234 Main St");
        customer.setCity("Mount Vernon");
        customer.setState("VA");
        customer.setZipCode("L7D 0FK");

        customerDAO.create(customer);
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }
}
