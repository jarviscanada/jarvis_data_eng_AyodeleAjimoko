package ca.jrvs.apps;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import okhttp3.OkHttpClient;

public class Main {
    public static void main(String[] args) {

        OkHttpClient client = new OkHttpClient();
        QuoteHttpHelper sampleQuote = new QuoteHttpHelper("3fcd93792cmsh0b019efcf6f5facp138c82jsne422d131b81b", client);
        //sampleQuote.setClient(new OkHttpClient());
        Quote outputQuote = new Quote();
        System.out.println(sampleQuote.getClient());
        outputQuote = (sampleQuote.fetchQuoteInfo("MSFT"));
        System.out.println(outputQuote.getVolume());

        DatabaseConnectionManager dcm = new DatabaseConnectionManager("localhost:5432", "stock_quote", "postgres", "password");
        System.out.println("Hello Stock Quote App!");
        try{
            Connection connection = dcm.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM quote");
            while(resultSet.next()){
                System.out.println(resultSet.getInt(1));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }

    }
}