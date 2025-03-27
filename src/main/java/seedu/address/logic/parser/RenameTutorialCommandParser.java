package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.RenameTutorialCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new RenameTutorialCommand object
 */
public class RenameTutorialCommandParser implements Parser<RenameTutorialCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the
     * RenameTutorialCommand and returns an RenameTutorialCommand object for
     * execution.
     *
     * @throws ParseException
     *             if the user input does not conform the expected format
     */
    public RenameTutorialCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME);

        Index index;
        String newName = "";

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, RenameTutorialCommand.MESSAGE_USAGE),
                            pe);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME);

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            newName = argMultimap.getValue(PREFIX_NAME).get();
            if (newName.isEmpty()) {
                throw new ParseException(
                                String.format(MESSAGE_INVALID_COMMAND_FORMAT, RenameTutorialCommand.MESSAGE_USAGE));
            }
        }

        return new RenameTutorialCommand(index, newName);
    }

}
