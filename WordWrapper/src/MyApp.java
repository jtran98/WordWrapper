import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class MyApp {
    public static void main(String[] args) throws IOException {
    	wordWrapperTwo();
    }
    /**
     * Injects command code for new lines
     */
    public static void wordWrapperOne() {
    	try {
            boolean firstLine = true;
            //values are number of characters
            final int lengthForWordWrap = 90;
            final int overflowThreshold = 180;
            //what to insert for wordwrapping/signifying an overflow
            final String newLineCommand = "@b";
            final String overflowWarning = "!OF!";
            
            //Change this to the file location of the script you want to edit
            File scriptFile = new File("E:\\Program Files\\Programming\\Repositories\\WordWrapper\\WordWrapper\\src\\script.txt");
            //Change this to the file location you want for the file output (creating an empty txt file at this location is good enough)
            FileWriter fileWriter = new FileWriter("E:\\Program Files\\Programming\\Repositories\\WordWrapper\\WordWrapper\\src\\newScript.txt");
            
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
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    /**
     * Injects spaces for new lines
     */
    public static void wordWrapperTwo() {
    	try {       
            //values are number of characters
            final int LENGTH_FOR_WORDWRAP = 60;
            final int MAX_NUMBER_OF_WORDWRAPS_PER_LINE = 2;
            final int OVERFLOW_THRESHOLD = 180;
            //what to insert for wordwrapping/signifying an overflow
            final String OVERFLOW_WARNING = "!OF!";
            //for file location stuff
            final String FILE_INPUT_PATH = "E:\\Program Files\\Programming\\Repositories\\WordWrapper\\WordWrapper\\src\\";
            final String FILE_OUTPUT_PATH = "E:\\Program Files\\Programming\\Repositories\\WordWrapper\\WordWrapper\\src\\";
            final String FILE_NAME = "kyo_001_01";
            final String FILE_EXTENSION = ".txt";
            
            //Change this to the file location of the script you want to edit
            File scriptFile = new File(FILE_INPUT_PATH + FILE_NAME + FILE_EXTENSION);
            //Change this to the file location you want for the file output (creating an empty txt file at this location is good enough)
            File outputFile = new File(FILE_OUTPUT_PATH + "!" + FILE_NAME + FILE_EXTENSION);
            FileWriter fileWriter = new FileWriter(outputFile);
            
            Scanner scanner = new Scanner(scriptFile, "UTF-8");
            while (scanner.hasNextLine()) {
            	StringBuilder stringBuilder = new StringBuilder(scanner.nextLine());
                if(stringBuilder.length() > OVERFLOW_THRESHOLD) {
                    //Overflow message
                    fileWriter.append(OVERFLOW_WARNING);
                }
                if(stringBuilder.length() > LENGTH_FOR_WORDWRAP) {
                    //Finds first whitespace index before the wordwrap limit, as to not create a newline in the middle of a word
                    int index = LENGTH_FOR_WORDWRAP-1;
                    int numOfSpaces = 0;
                    while(stringBuilder.charAt(index)!=' ' &&  index > 0) {
                        index--;
                        numOfSpaces++;
                    }
                    for(int i = 0; i < numOfSpaces; i++) {
                    	stringBuilder.insert(index, " ");
                    }
                    
                    //repeat up until you hit the max number of allowable wordwraps for a given line
                    for(int i = 1; i < MAX_NUMBER_OF_WORDWRAPS_PER_LINE; i++) {
                    	if(stringBuilder.length() - (LENGTH_FOR_WORDWRAP * i) > LENGTH_FOR_WORDWRAP) {
                    		index = (LENGTH_FOR_WORDWRAP * (i+1)) - 1;
                    		numOfSpaces = 0;
                    		while(stringBuilder.charAt(index)!=' ' &&  index > 0) {
                                index--;
                                numOfSpaces++;
                            }
                    		for(int j = 0; j < numOfSpaces; j++) {
                            	stringBuilder.insert(index, " ");
                            }
                    	}
                    }
                }
                //needed, otherwise output file will always start off with a '?'
                if(firstLine){
                    stringBuilder = new StringBuilder(stringBuilder.substring(1,stringBuilder.length()));
                	firstLine = false;
                }
                //adds modified line to new script file
                fileWriter.append(stringBuilder+"\n");
            }
            fileWriter.close();
            scanner.close();
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
