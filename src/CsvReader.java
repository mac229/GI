import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Maciej on 2016-12-11.
 */
public class CsvReader {

    public static final String SEPARATOR = ";";
    private static final String FILE_NAME = "data.csv";

    public static List<Data> readRecords() throws FileNotFoundException {
        try (Scanner scanner = new Scanner(new File(FILE_NAME))) {
            while (scanner.hasNext()) {
                System.out.println(scanner.nextLine());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return new ArrayList<>();
    }
}
