package main.java.ca.jrvs.apps.grep;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.stream.Collectors;

public class JavaGrepImpLambda implements JavaGrep{

  final Logger logger = LoggerFactory.getLogger(JavaGrep.class);
  private String regex;
  private String rootPath;
  private String outFile;
  //public JavaGrepImp(String regex, String path, String outFile) {
  //this.regex = regex;
  //  this.rootPath = path;
  //  this.outFile = outFile;
  // }


  @Override
  public void process() throws IOException {
    List<String> matchedLines = new ArrayList<>();

    File rootDir = new File("/home/centos/dev/jarvis_data_eng_Ayodele/core_java/grep/data/txt"); // replace with your root dir
    List<File> files = listFiles(rootPath);

    for (File file : files){
      try{
        List<String> lines = Files.readAllLines(Paths.get(file.getAbsolutePath()));
        for (String line : lines) {
          if (containsPattern(line)) {
            matchedLines.add(line);
          }
        }
      } catch (IOException e){
        e.printStackTrace();
      }

      try (FileWriter writer = new FileWriter("/home/centos/dev/jarvis_data_eng_Ayodele/core_java/grep/out/output.txt")) {
        for (String line : matchedLines) {
          writer.write(line + System.lineSeparator());
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

  }

  @Override
  public List<File> listFiles(String rootDir) throws IOException {
    File rootDirectory = new File(rootDir);
    return Files.walk(Paths.get(rootDir))
        .filter(Files::isRegularFile)
        .map(Path::toFile)
        .collect(Collectors.toList());
  }

  @Override
  public List<String> readLines(File inputFile) {
    if (!inputFile.isFile()) {
      throw new IllegalArgumentException("The given input is not a file");
    }
    try {
      return Files.readAllLines(inputFile.toPath());
    } catch (IOException e) {
      e.printStackTrace();
      return Collections.emptyList(); // or throw the exception
    }
  }

  @Override
  public boolean containsPattern(String line) {
    String pattern = getRegex();
    Pattern r = Pattern.compile(pattern);
    Matcher m = r.matcher(line);
    return m.find();
  }

  @Override
  public void writeToFile(List<String> lines) throws IOException {
    File outputFile = new File("/home/centos/dev/jarvis_data_eng_Ayodele/core_java/grep/out");
    try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
        Files.newOutputStream(outputFile.toPath()), StandardCharsets.UTF_8))) {
      for (String line : lines) {
        writer.write(line);
        writer.newLine(); // write a new line after each line
      }
    }
  }

  @Override
  public String getRootPath() {
    return rootPath;
  }

  @Override
  public void setRootPath(String rootPath) {
    this.rootPath = rootPath;
  }

  @Override
  public String getRegex() {
    return regex;
  }

  @Override
  public void setRegex(String regex) {
    this.regex = regex;
  }

  @Override
  public String getOutFile() {
    return outFile;
  }

  @Override
  public void setOutFile(String outFile) {
    this.outFile = outFile;
  }

  // MAIN CLASS //
  public static void main(String[] args) {
    if (args.length != 3) {
      throw new IllegalArgumentException("USAGE: JavaGrep regex rootPath outFile");
    }

    //JavaGrepImp javaGrepImp = new JavaGrepImp(".*Romeo.*Juliet.*","/home/centos/dev/jarvis_data_eng_Ayodele/core_java/grep/data/txt","/home/centos/dev/jarvis_data_eng_Ayodele/core_java/grep/out/output.txt");
    JavaGrepImp javaGrepImp = new JavaGrepImp();
    javaGrepImp.setRegex(args[0]);
    javaGrepImp.setRootPath(args[1]);
    javaGrepImp.setOutFile(args[2]);

    try{
      javaGrepImp.process();
    } catch (Exception ex) {
      javaGrepImp.logger.error("Unable to process Info", ex);
    }
  }
// MAIN CLASS
}