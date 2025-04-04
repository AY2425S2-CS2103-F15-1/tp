package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.AddAssignmentCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteAssignmentCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class AssignmentParserTest {
    private AssignmentParser parser;

    @BeforeEach
    void setUp() {
        parser = new AssignmentParser();
    }

    @Test
    void parse_validAddAssignmentCommand_returnsAddAssignmentCommand() throws ParseException {
        Command command = parser.parse("add Problem Set t/1");
        assertTrue(command instanceof AddAssignmentCommand);
    }

    @Test
    void parse_validDeleteAssignmentCommand_returnsDeleteAssignmentCommand() throws ParseException {
        Command command = parser.parse("delete Problem Set t/1");
        assertTrue(command instanceof DeleteAssignmentCommand);
    }

    @Test
    void parse_invalidCommand_throwsParseException() {
        ParseException exception = assertThrows(ParseException.class, (
        ) -> parser.parse("invalid-command something"));
        assertTrue(exception.getMessage().equals(Messages.MESSAGE_INVALID_COMMAND_FORMAT.formatted(parser.getUsage())));
    }
}
