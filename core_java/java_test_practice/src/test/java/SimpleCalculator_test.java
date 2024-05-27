
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.example.SimpleCalculator;
import org.example.SimpleCalculatorImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;


public class SimpleCalculator_test {
  SimpleCalculator calculator;

  @BeforeEach
  void init() {
    calculator = new SimpleCalculatorImpl();
  }

  @Test
  public void test_add() {
    int expected = 2;
    int actual = calculator.add(1, 1);
    assertEquals(expected, actual);
  }

  @Test
  public void test_subtract() {
    int expected = 1;
    int actual = calculator.subtract(3,2);
    assertEquals(expected, actual);
  }

  @Test
  public void test_multiply() {
    int expected = 6;
    int actual = calculator.multiply(3,2);
    assertEquals(expected, actual);
  }

  @Test
  public void test_divide() {
    //write your test here
    int expected = 2;
    int actual = (int) calculator.divide(6,3);
    assertEquals(expected, actual);
  }
}
