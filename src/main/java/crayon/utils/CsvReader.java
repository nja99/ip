package crayon.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a CSV reader to read from a CSV file.
 * The CSV file is expected to have a header and content.
 */
public class CsvReader {

    private final String filePath;

    /**
     * Constructs a CSVReader.
     *
     * @param filePath The file path of the CSV file.
     */
    public CsvReader(String filePath) {
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
