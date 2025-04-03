package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ViewCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class ViewCommandParserTest {
    private ViewCommandParser parser;

    @BeforeEach
    void setUp() {
        parser = new ViewCommandParser();
    }

    @Test
    void parse_validViewCommand_returnsViewCommand() throws ParseException {
        assertParseSuccess(parser, "1", new ViewCommand(INDEX_FIRST_STUDENT));
    }

    @Test
    void parse_invalidCommand_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));
    }
}
