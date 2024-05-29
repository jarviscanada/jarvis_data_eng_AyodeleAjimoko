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
      Customer customer  = new Customer();
      // Delete
      customer.setFirstName("John");
      customer.setLastName("Adams");
      customer.setEmail("johnadams.wh.gov");
      customer.setPhone("(898) 567-6563");
      customer.setAddress("14564 Ader St");
      customer.setCity("Vallory");
      customer.setState("MA");
      customer.setZipCode("U87 YU9");

      Customer dbCustomer = customerDAO.create(customer);
      System.out.println(dbCustomer);
      dbCustomer = customerDAO.findById(dbCustomer.getId());
      System.out.println(dbCustomer);
      dbCustomer.setEmail("johnadams@wh.gov.com");
      dbCustomer = customerDAO.update(dbCustomer);
      System.out.println(dbCustomer);
      customerDAO.delete(dbCustomer.getId());
    /* UPDATE
      Customer customer = customerDAO.findById(10000);
      System.out.println(customer.getFirstName() + " " + customer.getLastName() + " " + customer.getEmail());
      customer.setEmail("gwashingon@gmail.gov");
      customer = customerDAO.update(customer);
      System.out.println(customer.getFirstName() + " " + customer.getLastName() + " " + customer.getEmail());
       */
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
