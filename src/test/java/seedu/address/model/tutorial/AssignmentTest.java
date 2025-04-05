package seedu.address.model.tutorial;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class AssignmentTest {
    @Test
    public void constructor_nameLongerThan15Characters_throwsError() {
        assertThrows(IllegalArgumentException.class, (
        ) -> new Assignment("1234567890123456"));
    }

    @Test
    public void constructor_nameHasInvalidCharacters_throwsError() {
        assertThrows(IllegalArgumentException.class, (
        ) -> new Assignment("tut+"));
    }

    @Test
    public void constructor_nameIsBlank_throwsError() {
        assertThrows(IllegalArgumentException.class, (
        ) -> new Assignment(""));
    }

    @Test
    public void constructor_nameValid_success() {
        var assignmentName = "Week 1-_- Tasks";
        var a = assertDoesNotThrow((
        ) -> new Assignment(assignmentName));

        assertEquals(a.name(), assignmentName);
    }
}
