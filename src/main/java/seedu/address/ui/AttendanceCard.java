package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.attendance.Attendance;
import seedu.address.model.student.Student;

/**
 * An UI component that displays information of a {@code Tutorial}.
 */
public class AttendanceCard extends UiPart<Region> {

    private static final String FXML = "AttendanceListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved
     * keywords in JavaFX. As a consequence, UI elements' variable names cannot be
     * set to such keywords or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The
     *      issue on AddressBook level 4</a>
     */

    public final Attendance attendance;
    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label studentName;
    @FXML
    private Label tutorialName;
    @FXML
    private FlowPane attendances;

    /**
     * Creates an {@code AttendanceCard} with the given {@code Attendance} and index
     * to display.
     */
    public AttendanceCard(Attendance attendance, int displayedIndex) {
        super(FXML);
        this.attendance = attendance;
        id.setText(displayedIndex + ". ");
        studentName.setText(attendance.student().getName().toString());
        tutorialName.setText(attendance.tutorial().name());
        attendance.attendances().forEach(a -> attendances.getChildren().add(new Label(a.toString() + " ")));
    }
}
