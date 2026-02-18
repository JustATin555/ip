package dave.commands;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import dave.data.Deadline;
import dave.data.Task;
import dave.data.Tasklist;
import dave.storage.DiskStore;
import dave.ui.Ui;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DeadlineCommandTest {
    private DeadlineCommand deadlineCommand;
    private StubUi ui;
    private StubDiskStore ds;
    private StubTasklist tl;

    @BeforeEach
    public void setUp() {
        ui = new StubUi();
        ds = new StubDiskStore();
        tl = new StubTasklist();
    }

    @Test
    public void deadlineCommand_instantiate_success() {
        new DeadlineCommand("Submit assignment", LocalDateTime.now());
    }

    @Test
    public void execute_validInput_storesTaskInTasklist() {
        String description = "Submit assignment";
        LocalDateTime deadline = LocalDateTime.now();
        deadlineCommand = new DeadlineCommand(description, deadline);

        deadlineCommand.execute(ui, ds, tl);

        assert tl.wasStoreCalled(description, deadline);
    }

    @Test
    public void execute_validInput_saveTaskToDisk() {
        String description = "Finish project";
        LocalDateTime deadline = LocalDateTime.of(2025, 12, 31, 23, 59);
        deadlineCommand = new DeadlineCommand(description, deadline);

        deadlineCommand.execute(ui, ds, tl);

        assert ds.wasSaveCalled();
    }

    @Test
    public void execute_validInput_displaysTask() {
        String description = "Complete report";
        LocalDateTime deadline = LocalDateTime.now();
        deadlineCommand = new DeadlineCommand(description, deadline);

        deadlineCommand.execute(ui, ds, tl);

        assert ui.getLastDisplayOutput().contains("Alright, we'll both try to remember this task:");
    }
}

class StubUi implements Ui {
    private String lastOutput = "";

    @Override
    public void display(String message) {
        lastOutput = message;
    }

    @Override
    public void display(String message, List<Task> tasks) {
        lastOutput = message;
    }

    @Override
    public void start() {
    }

    @Override
    public Command getNextCommand() {
        return new InvalidCommand("");
    }

    public String getLastDisplayOutput() {
        return lastOutput;
    }
}

class StubDiskStore extends DiskStore {
    private boolean saveCalled = false;

    public StubDiskStore() {
        super(Path.of("dummy/path"));
    }

    @Override
    public void save(Task task) {
        saveCalled = true;
    }

    public boolean wasSaveCalled() {
        return saveCalled;
    }
}

class StubTasklist extends Tasklist {
    private String lastStoredDescription = "";
    private LocalDateTime lastStoredDeadline;

    public StubTasklist() {
        super(new ArrayList<>());
    }

    @Override
    public Task store(String description, LocalDateTime deadline) {
        lastStoredDescription = description;
        lastStoredDeadline = deadline;
        return new Deadline(description, deadline);
    }

    public boolean wasStoreCalled(String description, LocalDateTime deadline) {
        return lastStoredDescription.equals(description) && lastStoredDeadline.equals(deadline);
    }
}
