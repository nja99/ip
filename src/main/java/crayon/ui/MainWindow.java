package crayon.ui;

import java.util.Objects;

import crayon.Crayon;
import crayon.exceptions.CrayonException;
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
    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox dialogContainer;

    @FXML
    private TextField userInput;

    @FXML
    private Button sendButton;

    private Crayon crayon;

    private final Image userImage = new Image(Objects.requireNonNull(
            this.getClass().getResourceAsStream("/images/userProfile.png")));
    private final Image crayonImage = new Image(Objects.requireNonNull(
            this.getClass().getResourceAsStream("/images/crayonProfile.png")));

    @FXML
    private void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setCrayon(Crayon crayon) {
        this.crayon = crayon;
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (!userInput.getText().isEmpty()) {
            String response = crayon.getResponse(input);
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getCrayonDialog(response, crayonImage)
            );
        }
        userInput.clear();
    }
}
