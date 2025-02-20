package crayon.ui;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * A dialog box to display messages.
 * The dialog box contains a label to display the message and an image view to display the image.
 */
public class DialogBox extends HBox {

    @FXML
    private Label dialog;

    @FXML
    private ImageView displayPicture;

    private DialogBox(String input, Image image) {
        loadFxml();
        setDialogContent(input, image);
    }

    /**
     * Creates a dialog box with the user's message and image.
     *
     * @param input The user's message.
     * @param image The user's image.
     * @return A dialog box with the user's message and image.
     */
    public static DialogBox getUserDialog(String input, Image image) {
        return new DialogBox(input, image);
    }

    /**
     * Creates a dialog box with Crayon's message and image.
     *
     * @param input The Crayon's message.
     * @param image The Crayon's image.
     * @return A dialog box with Crayon's message and image.
     */
    public static DialogBox getCrayonDialog(String input, Image image) {
        DialogBox crayonDialog = new DialogBox(input, image);
        crayonDialog.flip();
        return crayonDialog;
    }

    private void loadFxml() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void setDialogContent(String input, Image image) {
        dialog.setText(input);
        displayPicture.setImage(image);
    }

    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
        dialog.getStyleClass().add("reply-label");
    }

}
