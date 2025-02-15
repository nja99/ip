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
            AnchorPane ap = loadMainWindow();
            setScene(stage, ap);
            setCloseRequestHandler(stage);
            stage.show();
        } catch (IOException e) {
            System.out.println("Application could not be started.");
        }
    }

    private AnchorPane loadMainWindow() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
        AnchorPane ap = fxmlLoader.load();
        fxmlLoader.<MainWindow>getController().setCrayon(crayon);

        return ap;
    }

    private void setScene(Stage stage, AnchorPane ap) {
        Scene scene = new Scene(ap);
        stage.setScene(scene);
    }

    private void setCloseRequestHandler(Stage stage) {
        stage.setOnCloseRequest(event -> {
            crayon.saveOnExit();
        });
    }
}
