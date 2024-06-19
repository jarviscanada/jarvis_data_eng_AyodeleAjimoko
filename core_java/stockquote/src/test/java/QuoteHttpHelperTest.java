//package test;

import ca.jrvs.apps.QuoteHttpHelper;
import ca.jrvs.apps.Quote;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.LocalTime;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.Mockito;
import org.mockito.internal.verification.Times;
import org.mockito.junit.jupiter.MockitoExtension;
import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.extension.ExtendWith;
@ExtendWith(MockitoExtension.class)
public class QuoteHttpHelperTest {
      @Spy
      Quote testQuote;
      QuoteHttpHelper quoteHttpHelper;
      OkHttpClient client;

      @BeforeEach
      public void setup() {
        testQuote = new Quote();
        client = new OkHttpClient();
        quoteHttpHelper = new QuoteHttpHelper("3fcd93792cmsh0b019efcf6f5facp138c82jsne422d131b81b",client);
      }

      @Test
      public void testFetchQuoteInfo() throws Exception {
        // Mock the API response
       // when(client.newCall(any(Request.class)).execute()).thenReturn(response);
       // when(response.body().string()).thenReturn("{\"Global Quote\":{\"01. symbol\":\"MSFT\",\"02. open\":\"424.7400\",\"03. high\":\"428.0800\",\"04. low\":\"423.8900\",\"05. price\":\"427.8700\",\"06. volume\":\"14003034\",\"07. latest trading day\":\"2024-06-10\",\"08. previous close\":\"423.8500\",\"09. change\":\"4.0200\"}}");
//0200
        // Call the method under test
        //Timestamp fixedTimestamp = Timestamp.from(Instant.now());
        LocalDateTime localDateTime = LocalDateTime.of(LocalDate.of(2024,6,18), LocalTime.now());
        Timestamp fixedTimestamp= Timestamp.valueOf(localDateTime);

        setPrivateField(testQuote, "ticker", "MSFT");
        setPrivateField(testQuote, "open", 449.705);
        setPrivateField(testQuote, "high", 450.1400);
        setPrivateField(testQuote,"low", 444.8900);
        setPrivateField(testQuote,"price", 446.3400);
        setPrivateField(testQuote,"volume",16418220);
        setPrivateField(testQuote,"latestTradingDay", LocalDate.of(2024,6,18));
        setPrivateField(testQuote,"previousClose", 448.3700);
        setPrivateField(testQuote,"change", -2.0300);
        setPrivateField(testQuote,"changePercent", "-0.4528%");
        setPrivateField(testQuote,"timestamp",fixedTimestamp);


        Quote quote = quoteHttpHelper.fetchQuoteInfo("MSFT");


        // Verify the result
        assertEquals(getPrivateField(testQuote, "ticker"), quote.getTicker());
        assertEquals(getPrivateField(testQuote, "open"), quote.getOpen());
        assertEquals(getPrivateField(testQuote,"high"), quote.getHigh());
        assertEquals(getPrivateField(testQuote,"low"), quote.getLow());
        assertEquals(getPrivateField(testQuote, "price"), quote.getPrice());
        assertEquals(getPrivateField(testQuote, "volume"), quote.getVolume());
        assertEquals(getPrivateField(testQuote, "latestTradingDay"), quote.getLatestTradingDay());
        assertEquals(getPrivateField(testQuote, "previousClose"), quote.getPreviousClose());
        assertEquals(getPrivateField(testQuote, "change"), quote.getChange());
        assertEquals(getPrivateField(testQuote, "changePercent"), quote.getChangePercent());
        assertEquals(getPrivateField(testQuote, "timestamp"), quote.getTimestamp());
        //assertThat(quote.getTimestamp(), isWithin(5, milliseconds(), (testQuote, "timestamp"));
      }

  private void setPrivateField(Object object , String property, Object value) throws Exception {
    Field field = object.getClass().getDeclaredField(property);
    field.setAccessible(true);
    field.set(object, value);
  }

  private Object getPrivateField(Object object, String property) throws Exception {
    Field field = object.getClass().getDeclaredField(property);
    field.setAccessible(true);
    return field.get(object);
  }

  }

