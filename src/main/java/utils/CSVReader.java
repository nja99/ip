package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {

    private final String filePath;

    public CSVReader(String filePath){
        this.filePath = filePath;
    }

    private String[] readHeader() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String headerLine = br.readLine();
            return headerLine != null ? headerLine.split(",") : null;
        }
    }

    public List<String[]> readFromCSV() throws IOException {
        List<String[]> rows = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))){// Skip Header
            String line;
            while((line = br.readLine()) != null) {
                String[] values = line.split(",");
                rows.add(values);
            }
        }
        return rows;
    }
}
