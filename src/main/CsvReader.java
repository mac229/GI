package main;

import model.Area;
import model.Province;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maciej on 2016-12-11.
 */
public class CsvReader {

    private static final String SEPARATOR = ";";
    private static final String FILE_NAME = "data.csv";

    public static List<Area> readData() {
        try {
            return readRecords();
        } catch (IOException e) {
            e.printStackTrace();
        }

        throw new RuntimeException("Fatal error");
    }

    private static List<Area> readRecords() throws IOException {
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
        Area area = null;
        while ((line = br.readLine()) != null) {
            String[] values = line.split(SEPARATOR);
            printLine(values);
            switch (values.length) {
                case 0:
                    result.add(area);
                    break;
                case 2:
                    area = parseArea(values);
                    break;
                case 5:
                    if (!values[0].equals("Rok")) {
                        Province province = parseProvince(values);
                        area.addProvinceData(province);
                    }
                    break;
            }
        }
        return result;
    }

    private static Province parseProvince(String[] values) {
        return new Province(values[0], values[1], values[2], values[3], values[4]);
    }

    private static Area parseArea(String[] values) {
        return new Area(values[0], values[1]);
    }

    private static void printLine(String[] values) {
        switch (values.length) {
            case 0:
                System.out.println("-------------------------------------------------");
                break;
            case 2:
                System.out.println(values[0] + "\t\t" + values[1]);
                break;
            case 5:
                if (!values[0].equals("Rok")) {
                    System.out.println(getSubstring(values[0]) + "\t\t" + values[1] + "\t\t" + values[2] + "\t\t" + values[3] + "\t\t" + values[4]);
                }
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
