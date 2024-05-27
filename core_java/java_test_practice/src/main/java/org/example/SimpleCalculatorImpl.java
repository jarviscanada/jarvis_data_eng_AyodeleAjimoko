package org.example;
import static java.lang.Math.pow;
import static java.lang.Math.abs;



public class SimpleCalculatorImpl implements SimpleCalculator {
  @Override
  public int add(int x, int y) {
    return x + y;
  }

  @Override
  public int subtract(int x, int y) {
    // TODO Auto-generated method stub
    return x -y;
  }

  @Override
  public int multiply(int x, int y) {
    // TODO Auto-generated method stub
    return x*y;
  }

  @Override
  public double divide(int x, int y) {
    // TODO Auto-generated method stub
    return x/y;
  }

  @Override
  public int power(int x, int y) {
    // TODO Auto-generated method stub
    return (int) Math.pow(x,y);
  }

  @Override
  public double abs(double x) {
    // TODO Auto-generated method stub
    return Math.abs(x);
  }
}
