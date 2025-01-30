import enums.TaskType;
import exceptions.CrayonInvalidFormatException;
import tasks.Task;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static tasks.Deadline.createDeadlineFromCSV;
import static tasks.Event.createEventFromCSV;
import static tasks.ToDo.createToDoFromCSV;

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

    public List<Task> readFromCSV() throws IOException {
        List<Task> tasks = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))){
            String headerLine = br.readLine();  // Skip Header
            if(headerLine == null) {
                return tasks;
            }

            String line;
            while((line = br.readLine()) != null){
                String[] values = line.split(",");
                TaskType taskType = TaskType.fromString(values[0]);
                try {
                    Task task = switch(taskType) {
                        case TODO -> createToDoFromCSV(values);
                        case EVENT -> createEventFromCSV(values);
                        case DEADLINE -> createDeadlineFromCSV(values);
                    };
                    tasks.add(task);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        return tasks;
    }


}
