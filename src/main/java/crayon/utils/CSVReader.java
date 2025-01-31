package crayon.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a CSV reader to read from a CSV file.
 */
public class CSVReader {

    private final String filePath;

    /**
     * Constructs a CSVReader.
     *
     * @param filePath The file path of the CSV file.
     */
    public CSVReader(String filePath){
        this.filePath = filePath;
    }

    /**
     * Reads the header of the CSV file.
     *
     * @return The header of the CSV file.
     * @throws IOException If an I/O error occurs.
     */
    public String[] readHeader() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String headerLine = br.readLine();
            return headerLine != null ? headerLine.split(",") : null;
        }
    }

    /**
     * Reads the content of the CSV file.
     *
     * @return The content of the CSV file.
     * @throws IOException If an I/O error occurs.
     */
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
