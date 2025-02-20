package crayon.ui;

import java.util.Objects;

import crayon.Crayon;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 * MainWindow is the application's main window.
 */
public class MainWindow extends AnchorPane {

    // Icon taken from https://www.flaticon.com/free-icon/user_9131549
    private static final Image USER_IMAGE = new Image(Objects.requireNonNull(
            MainWindow.class.getResourceAsStream("/images/userProfile.png")));

    // Icon taken from https://www.flaticon.com/free-icon/crayon_732403
    private static final Image CRAYON_IMAGE = new Image(Objects.requireNonNull(
            MainWindow.class.getResourceAsStream("/images/crayonProfile.png")));

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox dialogContainer;

    @FXML
    private TextField userInput;

    @FXML
    private Button sendButton;

    private Crayon crayon;

    @FXML
    private void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets the Crayon object to be used by the MainWindow.
     *
     * @param crayon The Crayon object to be used by the MainWindow.
     */
    public void setCrayon(Crayon crayon) {
        this.crayon = crayon;
        dialogContainer.getChildren().add(DialogBox.getCrayonDialog(crayon.showWelcomeMessage(), CRAYON_IMAGE));
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (!userInput.getText().isEmpty()) {
            processInput(input);

            if (crayon.isExitCommand()) {
                crayon.saveOnExit();
                delayBeforeExit();
            }
        }
        userInput.clear();
    }

    private void processInput(String input) {
        String response = crayon.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, USER_IMAGE),
                DialogBox.getCrayonDialog(response, CRAYON_IMAGE)
        );
    }
    private void delayBeforeExit() {
        PauseTransition delay = new PauseTransition(Duration.seconds(3));
        delay.setOnFinished(event -> Platform.exit());
        delay.play();
    }
}
