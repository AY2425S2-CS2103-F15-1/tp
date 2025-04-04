package seedu.address.ui.actions;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.testfx.api.FxAssert;
import org.testfx.framework.junit5.ApplicationTest;

import javafx.scene.Node;
import javafx.stage.Stage;
import seedu.address.MainApp;

public class AddStudentTest extends ApplicationTest {
    @Override
    public void start(Stage stage) {
        MainApp mainApp = new MainApp();
        try {
            mainApp.init();
        } catch (Exception e) {
            assertTrue(false);
        }
        mainApp.start(stage);
    }

    @Test
    public void commandTextFieldExists() {
        FxAssert.verifyThat("#commandTextField", Node::isVisible);
    }
}
