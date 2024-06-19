package ca.jrvs.apps;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Map;
import sun.util.calendar.LocalGregorianCalendar;

public class Quote {
  private String ticker; //id
  private double open;
  private double high;
  private double low;
  private double price;
  private int volume;
  private LocalDate latestTradingDay;
  private double previousClose;
  private double change;
  private String changePercent;
  private Timestamp timestamp; //time when the info was pulled

  @JsonIgnore
  private Map<String, Object> globalQuote;


  public String getTicker() {
    return ticker;
  }

  public void setTicker(String ticker) {
    this.ticker = ticker;
  }

  public double getOpen() {
    return open;
  }

  public void setOpen(double open) {
    this.open = open;
  }

  public double getHigh() {
    return high;
  }

  public void setHigh(double high) {
    this.high = high;
  }

  public double getLow() {
    return low;
  }

  public void setLow(double low) {
    this.low = low;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public int getVolume() {
    return volume;
  }

  public void setVolume(int volume) {
    this.volume = volume;
  }

  public LocalDate getLatestTradingDay() {
    return latestTradingDay;
  }

  public void setLatestTradingDay(LocalDate latestTradingDay) {
    this.latestTradingDay = latestTradingDay;
  }

  public double getPreviousClose() {
    return previousClose;
  }

  public void setPreviousClose(double previousClose) {
    this.previousClose = previousClose;
  }

  public double getChange() {
    return change;
  }

  public void setChange(double change) {
    this.change = change;
  }

  public String getChangePercent() {
    return changePercent;
  }

  public void setChangePercent(String changePercent) {
    this.changePercent = changePercent;
  }

  public Timestamp getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Timestamp timestamp) {
    this.timestamp = timestamp;
  }
}
