package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.ListSubmissionCommand;
import seedu.address.logic.commands.SetSubmissionCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class SubmissionParserTest {
    private SubmissionParser parser;

    @BeforeEach
    void setUp() {
        parser = new SubmissionParser();
    }

    @Test
    void parse_validSetSubmissionCommand_returnsSetSubmissionCommand() throws ParseException {
        Command command = parser.parse("set submitted t/CS2103-T1 a/HOMEWORK s/A0545648B");
        assertTrue(command instanceof SetSubmissionCommand);
    }

    @Test
    void parse_validListSubmissionCommand_returnsListSubmissionCommand() throws ParseException {
        Command command = parser.parse("list");
        assertTrue(command instanceof ListSubmissionCommand);
    }

    @Test
    void parse_invalidCommand_throwsParseException() {
        ParseException exception = assertThrows(ParseException.class, (
        ) -> parser.parse("invalid-command something"));
        assertTrue(exception.getMessage().equals(Messages.MESSAGE_INVALID_COMMAND_FORMAT.formatted(parser.getUsage())));
    }
}
