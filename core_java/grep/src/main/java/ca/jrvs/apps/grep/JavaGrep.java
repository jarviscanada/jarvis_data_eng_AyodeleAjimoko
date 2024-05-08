package main.java.ca.jrvs.apps.grep;
import java.io.File;
import java.util.List;
import java.io.IOException;

public interface JavaGrep {
/**
 * Top Search Workflow
 * @throws IOException
 */
  void process() throws IOException;

  /**
   *   Traverse a given directory and return all files
   * @param rootDir Input Directory
   * @return files under the RootDir
    */
  List<File> listFiles(String rootDir) throws IOException;

  /**
   * Read a file and return all the lines
   * Explain Filereader, BufferedReader, and character encoding
   * @param inputFile to be read
   * @return lines
   * @throws IllegalArgumentException if a givne input file is not a file
   */

  List<String> readLines(File inputFile);

  /**
   * check if a lien contains the regex pattern (passed by user)
   * @param line
   * @return true if there is a match
   */

  boolean containsPattern(String line);

  /**
   * write lines to a file
   *
   * Explore: FileOutputStream, OutPutStreamWriter, and BufferedWriter
   * @param lines matched line
   * @throws IOException if write failed
   *
   */
  void writeToFile(List<String> lines) throws IOException;

  String getRootPath();

  void setRootPath(String rootPath);

  String getRegex();

  void setRegex(String regex);

  String getOutFile();

  void setOutFile(String outFile);


}
