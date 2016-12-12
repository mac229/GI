import model.ProvinceData;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maciej on 2016-12-11.
 */
public class CsvReader {

    private static final String SEPARATOR = ";";
    private static final String FILE_NAME = "data.csv";

    public static List<ProvinceData> readRecords() throws IOException {
        File file = new File(FILE_NAME);
        FileReader fr = new FileReader(file);
        InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file), "windows-1250");
        BufferedReader br = new BufferedReader(inputStreamReader);
        String line;
        while ((line = br.readLine()) != null) {
            parseLine(line);
        }
        br.close();
        fr.close();

        return new ArrayList<>();
    }

    private static void parseLine(String line) {
        String[] values = line.split(SEPARATOR);
        switch (values.length) {
            case 0:
                System.out.println("-------------------------------------------------");
                break;
            case 2:
                System.out.println(values[0] + " " + values[1]);
                break;
            case 5:
                System.out.println(values[0]);
                break;
            default:
                //throw new RuntimeException("too big");
        }
        //System.out.println(values.length);
    }
}
