package ca.jrvs.apps;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class QuoteHttpHelper {

  private String apiKey;
  private OkHttpClient client;

  public QuoteHttpHelper(String apiKey, OkHttpClient client) {
    this.apiKey = apiKey;
    this.client = client;
  }

  /**
   * Fetch latest quote data from Alpha Vantage endpoint
   * @param symbol
   * @return Quote with latest data
   * @throws IllegalArgumentException - if no data was found for the given symbol
   */
  public Quote fetchQuoteInfo(String symbol) throws IllegalArgumentException {

    Request request = new Request.Builder()
        .url("https://alpha-vantage.p.rapidapi.com/query?function=GLOBAL_QUOTE&symbol=MSFT&datatype=json")
        .get()
        .addHeader("x-rapidapi-key", "3fcd93792cmsh0b019efcf6f5facp138c82jsne422d131b81b")
        .addHeader("x-rapidapi-host", "alpha-vantage.p.rapidapi.com")
        .build();


    try {
      Response response = client.newCall(request).execute();
      if(response.body() ==null){
        throw new IllegalArgumentException("Response Body is Null");
      }
      String responseBody = response.body().string();
      Quote responseQuote = parseJsonResponse(responseBody);
      return responseQuote;
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to retrieve data from API");
    }
  }



  private Quote parseJsonResponse(String responseBody) throws JsonProcessingException {
    Quote quote = null;
    try {
      ObjectMapper mapper = new ObjectMapper();
    //quote = mapper.readValue(responseBody, Quote.class);
      JsonNode root = mapper.readTree(responseBody);
      JsonNode globalQuote = root.get("Global Quote");

      if (globalQuote == null) {
        System.out.println("Global Quote node is null!");
        return null;
      }

      quote = new Quote();
      quote.setTicker(globalQuote.get("01. symbol").asText());
      quote.setOpen(globalQuote.get("02. open").asDouble());
      quote.setHigh(globalQuote.get("03. high").asDouble());
      quote.setLow(globalQuote.get("04. low").asDouble());
      quote.setPrice(globalQuote.get("05. price").asDouble());
      quote.setVolume(globalQuote.get("06. volume").asInt());
      String latestTradingDay = globalQuote.get("07. latest trading day").asText();
      LocalDate date = LocalDate.parse(latestTradingDay);
      quote.setLatestTradingDay(date);
      quote.setPreviousClose(globalQuote.get("08. previous close").asDouble());
      quote.setChange(globalQuote.get("09. change").asDouble());
      quote.setChangePercent(globalQuote.get("10. change percent").asText());
      LocalDateTime localDateTime = LocalDateTime.of(date, LocalTime.now());
      Timestamp timestamp = Timestamp.valueOf(localDateTime);
      quote.setTimestamp(timestamp);



    } catch(JsonMappingException e){
      e.printStackTrace();
    } catch (JsonProcessingException e){
      e.printStackTrace();
      throw e;
    }
    return quote;
  }

  public String getApiKey() {
    return apiKey;
  }

  public void setApiKey(String apiKey) {
    this.apiKey = apiKey;
  }

  public OkHttpClient getClient() {
    return client;
  }

  public void setClient(OkHttpClient client) {
    this.client = client;
  }
}
