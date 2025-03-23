package seedu.address.model.student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_HANDLE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TUTORIAL_2;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalStudents.ALICE;
import static seedu.address.testutil.TypicalStudents.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.StudentBuilder;

public class StudentTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Student student = new StudentBuilder().build();
        assertThrows(UnsupportedOperationException.class, (
        ) -> student.getTutorials().remove(0));
    }

    @Test
    public void isSamePerson() {
        // same object -> ok
        assertTrue(ALICE.isSamePerson(ALICE));

        // null -> fail
        assertFalse(ALICE.isSamePerson(null));

        // same name, all other attributes different -> ok
        Student editedAlice = new StudentBuilder(ALICE).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                        .withHandle(VALID_HANDLE_BOB).withTutorials(VALID_TUTORIAL_2).build();
        assertTrue(ALICE.isSamePerson(editedAlice));

        // different name, same student ID -> ok
        Student editedAliceWithSameId = new StudentBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertTrue(ALICE.isSamePerson(editedAliceWithSameId));

        // different name, different student ID, same phone -> ok
        Student editedAliceWithSamePhone = new StudentBuilder(ALICE).withName(VALID_NAME_BOB)
                        .withStudentId(VALID_ID_BOB).build();
        assertTrue(ALICE.isSamePerson(editedAliceWithSamePhone));

        // different name, different student ID, different phone, same email -> ok
        Student editedAliceWithSameEmail = new StudentBuilder(ALICE).withName(VALID_NAME_BOB)
                        .withStudentId(VALID_ID_BOB).withPhone(VALID_PHONE_BOB).build();
        assertTrue(ALICE.isSamePerson(editedAliceWithSameEmail));

        // different name, different student ID, different phone, different email, same
        // handle -> ok
        Student editedAliceWithSameHandle = new StudentBuilder(ALICE).withName(VALID_NAME_BOB)
                        .withStudentId(VALID_ID_BOB).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).build();
        assertTrue(ALICE.isSamePerson(editedAliceWithSameHandle));

        // completely different student -> fail
        assertFalse(ALICE.isSamePerson(BOB));
    }

    @Test
    public void equals() {
        // same values -> ok
        Student aliceCopy = new StudentBuilder(ALICE).build();
        assertEquals(ALICE, aliceCopy);

        // same object -> ok
        assertEquals(ALICE, ALICE);

        // null -> fail
        assertNotNull(ALICE);

        // different type -> fail
        assertNotEquals(ALICE, 5);

        // different student -> fail
        assertNotEquals(ALICE, BOB);

        // different name -> fail
        Student editedAlice = new StudentBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertNotEquals(ALICE, editedAlice);

        // different phone -> fail
        editedAlice = new StudentBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertNotEquals(ALICE, editedAlice);

        // different email -> fail
        editedAlice = new StudentBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertNotEquals(ALICE, editedAlice);

        // different address -> fail
        editedAlice = new StudentBuilder(ALICE).withHandle(VALID_HANDLE_BOB).build();
        assertNotEquals(ALICE, editedAlice);
    }

    @Test
    public void toStringMethod() {
        String expected = Student.class.getCanonicalName() + "{name=" + ALICE.getName() + ", studentId="
                        + ALICE.getStudentId() + ", phone=" + ALICE.getPhone() + ", email=" + ALICE.getEmail()
                        + ", handle=" + ALICE.getHandle() + ", tutorials=" + ALICE.getTutorials() + "}";
        assertEquals(expected, ALICE.toString());
    }
}
