package main.java.ca.jrvs.apps.practice;
import java.util.regex.*;


public class RegexExcImp implements main.java.ca.jrvs.apps.practice.RegexExc {

  public boolean matchJpeg(String filename) {
    // Regular expression to match filenames ending with ".jpg" or ".jpeg" (case-insensitive)
    String regex = "(?i).*\\.(jpg|jpeg)$";

    // Create a Pattern object
    Pattern pattern = Pattern.compile(regex);

    // Create a Matcher object
    Matcher matcher = pattern.matcher(filename);

    // Check if the filename matches the pattern
    return matcher.matches();
  }


  public boolean matchIp(String ip) {
    // Regular expression to match IP address format
    String regex = "^(?:[0-9]{1,3}\\.){3}[0-9]{1,3}$";

    // Check if the IP matches the format
    if (!ip.matches(regex)) {
      return false; // IP format is not valid
    }

    // Split the IP address into segments
    String[] segments = ip.split("\\.");

    // Check if each segment falls within the valid range
    for (String segment : segments) {
      int value;
      try {
        value = Integer.parseInt(segment);
      } catch (NumberFormatException e) {
        return false; // Segment is not a valid integer
      }
    }
    return true;
  }

  public boolean isEmptyLine(String line){
    // Regular expression to match empty or whitespace-only lines
    String regex = "^\\s*$";

    // Check if the line matches the pattern
    return line.matches(regex);
  }




}