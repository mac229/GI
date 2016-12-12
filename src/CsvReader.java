import model.Area;
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

    public static List<Area> readRecords() throws IOException {
        File file = new File(FILE_NAME);
        FileReader fr = new FileReader(file);
        InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file), "windows-1250");
        BufferedReader br = new BufferedReader(inputStreamReader);
        List<Area> data = getData(br);
        br.close();
        fr.close();

        return data;
    }

    private static List<Area> getData(BufferedReader br) throws IOException {
        List<Area> result = new ArrayList<>();

        String line;
        Area area;
        while ((line = br.readLine()) != null) {
            parseLine(line);
        }

        return result;
    }

    private static void parseLine(String line) {
        String[] values = line.split(SEPARATOR);
        switch (values.length) {
            case 0:
                System.out.println("-------------------------------------------------");
                break;
            case 2:
                System.out.println(values[0] + "\t\t" + values[1]);
                break;
            case 5:
                System.out.println(getSubstring(values[0]) + "\t\t" + values[1] + "\t\t" + values[2] + "\t\t" + values[3] + "\t\t" + values[4]);
                break;
        }
    }

    private static String getSubstring(String value) {
        for (int i = value.length(); i < 16; i++) {
            value += " ";
        }

        return value;
    }
}
