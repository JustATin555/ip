package dave.ui;

import java.util.List;

import dave.commands.Command;
import dave.commands.InvalidCommand;
import dave.commands.Parser;
import dave.data.Task;
import dave.data.Tasklist;
import dave.gui.DialogBox;
import dave.storage.DiskStore;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for the main GUI.
 */
public class Gui extends AnchorPane implements Ui {
    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private final Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    private DiskStore diskstore;
    private Tasklist tasklist;

    public void setControllers(DiskStore ds, Tasklist ts) {
        this.diskstore = ds;
        this.tasklist = ts;
    }

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * GUI initialization handled by JavaFX.
     */
    @Override
    public void start() {
    }

    /**
     * GUI getNextCommand handled by JavaFX.
     */
    @Override
    public Command getNextCommand() {
        return new InvalidCommand("Do not use Gui.getNextCommand(); use Gui.handleUserInput() instead");
    }

    @Override
    public void display(String message) {
        dialogContainer
                .getChildren()
                .addAll(DialogBox.getDukeDialog(message, dukeImage));
    }

    @Override
    public void display(String message, List<Task> tasks) {
        int size = tasks.size();

        String[] labelled = new String[size];

        for (int i = 0; i < size; i++) {
            labelled[i] = String.format("%d. %s", i + 1, tasks.get(i));
        }

        dialogContainer
                .getChildren()
                .addAll(DialogBox
                        .getDukeDialog(
                                String.format("%s\n%s", message, String.join("\n", labelled)),
                                dukeImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        dialogContainer.getChildren().addAll(DialogBox.getUserDialog(input, userImage));

        Parser.parseCommand(input).execute(this, diskstore, tasklist);

        userInput.clear();
    }
}
