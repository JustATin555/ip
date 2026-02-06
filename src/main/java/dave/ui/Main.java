package dave.ui;

import java.io.IOException;
import java.nio.file.Path;

import dave.data.Tasklist;
import dave.storage.DiskStore;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI built using FXML.
 */
public class Main extends Application {

    private final DiskStore diskstore;
    private final Tasklist tasklist;

    /**
     * Constructs a new main application for JavaFX.
     */
    public Main() {
        this.diskstore = new DiskStore(Path.of("tasks.txt"));
        this.tasklist = new Tasklist(diskstore.load());
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/Gui.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<Gui>getController().setControllers(diskstore, tasklist);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
