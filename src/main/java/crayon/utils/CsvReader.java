package crayon.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvReader {

    private final String filePath;

    public CsvReader(String filePath) {
        this.filePath = filePath;
    }

    public String[] readHeader() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String headerLine = br.readLine();
            return headerLine != null ? headerLine.split(",") : null;
        }
    }

    public List<String[]> readFromCsv() throws IOException {
        List<String[]> rows = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) { // Skip Header
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                rows.add(values);
            }
        }
        return rows;
    }
}
