import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class NewlineInserter {
    public static void main(String[] args) throws IOException {
        try {
            
            //values are number of characters
            final int lengthForWordWrap = 90;
            final int overflowThreshold = 180;
            //what to insert for wordwrapping/signifying an overflow
            final String newLineCommand = "@b";
            final String overflowWarning = "!OF!";
            
            //Change this to the file location of the script you want to edit
            File scriptFile = new File("E:\\Program Files\\Programming\\Repositories\\eclipse\\WordWrapper\\src\\script.txt");
            //Change this to the file location you want for the file output (creating an empty txt file at this location is good enough)
            FileWriter fileWriter = new FileWriter("E:\\Program Files\\Programming\\Repositories\\eclipse\\WordWrapper\\src\\newScript.txt");
            
            Scanner scanner = new Scanner(scriptFile, "UTF-8");
            while (scanner.hasNextLine()) {
            	StringBuilder stringBuilder = new StringBuilder(scanner.nextLine());
                if(stringBuilder.length() > overflowThreshold) {
                    //Overflow message
                    fileWriter.append(overflowWarning);
                }
                if(stringBuilder.length() > lengthForWordWrap) {
                    //Finds first whitespace index before the wordwrap limit, as to not create a newline in the middle of a word
                    int index = lengthForWordWrap-1;
                    while(stringBuilder.charAt(index)!=' ' &&  index > 0) {
                        index--;
                    }
                    stringBuilder.insert(index, newLineCommand);
                }
                //adds modified line to new script file
                fileWriter.append(stringBuilder+"\n");
                System.out.println(stringBuilder);
            }
            fileWriter.close();
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
