package crayon.ui;

import java.util.Objects;

import crayon.Crayon;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {

    private static final Image USER_IMAGE = new Image(Objects.requireNonNull(
            MainWindow.class.getResourceAsStream("/images/userProfile.png")));
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

    public void setCrayon(Crayon crayon) {
        this.crayon = crayon;
        dialogContainer.getChildren().add(DialogBox.getCrayonDialog(crayon.showWelcomeMessage(), CRAYON_IMAGE));
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (!userInput.getText().isEmpty()) {
            String response = crayon.getResponse(input);
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, USER_IMAGE),
                    DialogBox.getCrayonDialog(response, CRAYON_IMAGE)
            );
        }
        userInput.clear();
    }
}
