package seedu.address.ui.student;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.student.Student;
import seedu.address.ui.UiPart;

/**
 * An UI component that displays information of a {@code Student}.
 */
public class StudentCard extends UiPart<Region> {

    private static final String FXML = "StudentComponents/StudentListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved
     * keywords in JavaFX. As a consequence, UI elements' variable names cannot be
     * set to such keywords or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The
     *      issue on AddressBook level 4</a>
     */

    public final Student student;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label email;
    @FXML
    private Label studentId;
    @FXML
    private Label handle;
    @FXML
    private FlowPane tutorials;

    /**
     * Creates a {@code StudentCode} with the given {@code Student} and index to
     * display.
     */
    public StudentCard(Student student, int displayedIndex) {
        super(FXML);
        this.student = student;
        id.setText(displayedIndex + "");
        name.setText(student.getName().fullName);
        studentId.setText(student.getStudentId().id);
        phone.setText(student.getPhone().value);
        handle.setText(student.getHandle().handle);
        email.setText(student.getEmail().value);
        student.getTutorials().stream().sorted(Comparator.comparing(tutorial -> tutorial.name()))
                        .forEach(tutorial -> tutorials.getChildren().add(new Label(tutorial.name())));
    }
}
