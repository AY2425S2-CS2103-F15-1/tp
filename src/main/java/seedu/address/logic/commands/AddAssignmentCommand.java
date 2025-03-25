package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.NavigationMode;
import seedu.address.model.tutorial.Assignment;

/**
 * Adds a tutorial to the address book.
 */
public class AddAssignmentCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = "Usage: assignment add ASSIGNMENT_NAME i/INDEX... [d/DUE_DATE]";

    public static final String MESSAGE_SUCCESS = "New tutorial added: %1$s";
    public static final String MESSAGE_INVALID_NAME = """
                    The only valid characters are: letters (A-Z, a-z), digits (0-9), underscores (_), hyphens (-)""";
    public static final String MESSAGE_DUPLICATE_TUTORIAL = "Tutorial slot already exists";
    private static final String MESSAGE_TUTORIAL_NOT_FOUND = "Cannot find tutorial";

    private final Assignment toAdd;
    private final Index tutorialIndex;

    /**
     * Creates an {@link AddAssignmentCommand} to add the specified {@code Tutorial}
     */
    public AddAssignmentCommand(Index tutorialIndex, Assignment assignment) {
        requireNonNull(assignment);
        toAdd = assignment;
        this.tutorialIndex = tutorialIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        var ind = tutorialIndex.getOneBased();

        var tutorial = model.getFilteredTutorialList().get(ind);
        if (tutorial == null) {
            throw new CommandException(MESSAGE_TUTORIAL_NOT_FOUND);
        }

        if (tutorial.hasAssignment(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_TUTORIAL);
        }

        tutorial.addAssignment(toAdd);
        return new CommandResult(MESSAGE_SUCCESS.formatted(toAdd), NavigationMode.ASSIGNMENT);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddAssignmentCommand otherAddCommand)) {
            return false;
        }

        return toAdd.equals(otherAddCommand.toAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("toAdd", toAdd).toString();
    }
}
