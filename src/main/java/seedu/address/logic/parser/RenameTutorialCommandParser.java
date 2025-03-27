package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.DeleteTutorialCommand;
import seedu.address.logic.commands.RenameTutorialCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tutorial.Tutorial;

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

        String[] tutorialNames = args.split(" ");
        if (tutorialNames.length != 2) {
            throw new ParseException(
                            String.format(MESSAGE_INVALID_COMMAND_FORMAT, RenameTutorialCommand.MESSAGE_USAGE));
        }

        String oldTutorialName = tutorialNames[0];
        String newTutorialName = tutorialNames[1];

        if (oldTutorialName.isEmpty() || newTutorialName.isEmpty()) {
            throw new ParseException(MESSAGE_INVALID_COMMAND_FORMAT.formatted(RenameTutorialCommand.MESSAGE_USAGE));
        }

        if (!Tutorial.isValidName(oldTutorialName) || !Tutorial.isValidName(newTutorialName)) {
            throw new ParseException(
                            MESSAGE_INVALID_COMMAND_FORMAT.formatted(DeleteTutorialCommand.MESSAGE_INVALID_NAME));
        }

        return new RenameTutorialCommand(new Tutorial(oldTutorialName), newTutorialName);
    }

}
