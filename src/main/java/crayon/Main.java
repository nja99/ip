package crayon;

import java.io.IOException;

import crayon.ui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Main class for the Crayon application.
 */
public class Main extends Application {

    private final Crayon crayon = new Crayon();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();

            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setCrayon(crayon);
            stage.show();
            stage.setOnCloseRequest(event -> {
                System.out.println("Application is closing. Saving Tasks...");
                boolean saved = crayon.saveOnExit();
                if (saved) {
                    System.out.println("Tasks saved successfully");
                } else {
                    System.out.println("Failed to save tasks.");
                }
            });
        } catch (IOException e) {
            System.out.println("Application could not be started.");
        }
    }
}
