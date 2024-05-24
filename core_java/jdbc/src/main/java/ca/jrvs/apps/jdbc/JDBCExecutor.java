package ca.jrvs.apps.jdbc;

import ca.jrvs.apps.jdbc.util.CustomerDAO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DriverManager;
import ca.jrvs.apps.jdbc.Customer;

public class JDBCExecutor {

  public static void main(String... args) {
    DatabaseConnectionManager dcm = new DatabaseConnectionManager("localhost:5432", "hplusport",
        "postgres", "password");
    try {
      Connection connection = dcm.getConnection();
      CustomerDAO customerDAO = new CustomerDAO(connection);
      Customer customer = customerDAO.findById(1000);
      System.out.println(customer.getFirstName() + " " + customer.getLastName());
      /*
      customer.setFirstName("George");
      customer.setLastName("Washington");
      customer.setEmail("george.washington@wh.gov");
      customer.setPhone("(555) 555-6543");
      customer.setAddress("1234 Main St");
      customer.setCity("Mount Vernon");
      customer.setState("VA");
      customer.setZipCode("L7D 0FK");

      customerDAO.create(customer);

       */
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
