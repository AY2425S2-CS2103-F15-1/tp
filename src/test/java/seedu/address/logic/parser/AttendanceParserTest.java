package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.ListAttendanceCommand;
import seedu.address.logic.commands.MarkAttendanceCommand;
import seedu.address.logic.commands.UnmarkAttendanceCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class AttendanceParserTest {
    private AttendanceParser parser;

    @BeforeEach
    void setUp() {
        parser = new AttendanceParser();
    }

    @Test
    void parse_validMarkAttendanceCommand_returnsMarkAttendanceCommand() throws ParseException {
        Command command = parser.parse("mark w/3 i/1");
        assertTrue(command instanceof MarkAttendanceCommand);
    }

    @Test
    void parse_validListAttendanceCommand_returnsListAttendanceCommand() throws ParseException {
        Command command = parser.parse("list");
        assertTrue(command instanceof ListAttendanceCommand);
    }

    @Test
    void parse_validUnmarkAttendanceCommand_returnsUnmarkAttendanceCommand() throws ParseException {
        Command command = parser.parse("unmark w/3 i/1");
        assertTrue(command instanceof UnmarkAttendanceCommand);
    }

    @Test
    void parse_invalidCommand_throwsParseException() {
        ParseException exception = assertThrows(ParseException.class, (
        ) -> parser.parse("invalid-command something"));
        assertTrue(exception.getMessage().equals(Messages.MESSAGE_INVALID_COMMAND_FORMAT.formatted(parser.getUsage())));
    }
}
