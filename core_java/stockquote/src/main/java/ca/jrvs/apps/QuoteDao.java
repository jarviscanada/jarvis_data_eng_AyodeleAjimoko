package ca.jrvs.apps;

import java.sql.Connection;
import java.util.Optional;

public class QuoteDao implements CrudDao{

  private Connection c;

  @Override
  public Object save(Object entity) throws IllegalArgumentException {
    return null;
  }

  @Override
  public Optional findById(Object o) throws IllegalArgumentException {
    return Optional.empty();
  }

  @Override
  public Iterable findAll() {
    return null;
  }

  @Override
  public void deleteById(Object o) throws IllegalArgumentException {

  }

  @Override
  public void deleteAll() {

  }
}
