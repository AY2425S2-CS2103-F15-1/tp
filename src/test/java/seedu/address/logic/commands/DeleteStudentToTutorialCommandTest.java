package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static seedu.address.logic.parser.ParserUtil.parseIndex;
import static seedu.address.testutil.TypicalAddressBook.T1;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.testutil.TypicalAddressBook;

public class DeleteStudentToTutorialCommandTest {
    private static final Model modelStub = new ModelManager(TypicalAddressBook.getTypicalAddressBook(),
                    new UserPrefs());

    @Test
    public void execute_duplicateIndices_success() throws ParseException {
        // EP: Duplicate student
        var index = parseIndex("1");

        assertDoesNotThrow((
        ) -> new DeleteStudentFromTutorialCommand(List.of(index), T1).execute(modelStub));
    }
}
