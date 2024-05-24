package ca.jrvs.apps.jdbc;

import ca.jrvs.apps.jdbc.util.CustomerDAO;
import ca.jrvs.apps.jdbc.util.DataTransferObject;
import java.sql.Connection;
import java.sql.SQLException;

public class Customer implements DataTransferObject {
  private long id;
  private String firstName;
  private String lastName;
  private String email;
  private String phone;
  private String address;
  private String city;
  private String state;
  private String zipCode;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getZipCode() {
    return zipCode;
  }

  public void setZipCode(String zipCode) {
    this.zipCode = zipCode;
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