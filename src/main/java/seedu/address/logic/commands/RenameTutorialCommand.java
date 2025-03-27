package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TUTORIALS;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.NavigationMode;
import seedu.address.model.student.Student;
import seedu.address.model.tutorial.Tutorial;
import seedu.address.model.uniquelist.exceptions.DuplicateItemException;
import seedu.address.model.uniquelist.exceptions.ItemNotFoundException;

/**
 * Edits the details of an existing student in the address book.
 */
public class RenameTutorialCommand extends Command {

    public static final String COMMAND_WORD = "rename";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Renames a tutorial. "
                    + "Parameters: INDEX (must be a positive integer) " + "n/NEW_NAME";

    public static final String MESSAGE_RENAME_TUTORIAL_SUCCESS = "Renamed Tutorial: %1$s";
    public static final String MESSAGE_INVALID_TUTORIAL = "Invalid tutorial name supplied.";
    public static final String MESSAGE_DUPLICATE_TUTORIAL = "This tutorial already exists in the address book.";

    private final Index index;
    private final String newName;

    /**
     * @param index
     *            of the tutorial in the filtered tutorial list to edit
     * @param newName
     *            name to rename the tutorial with
     */
    public RenameTutorialCommand(Index index, String newName) {
        requireNonNull(index);
        requireNonNull(newName);

        this.index = index;
        this.newName = newName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {

        requireNonNull(model);
        List<Tutorial> lastShownList = model.getFilteredTutorialList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Tutorial tutorialToRename = lastShownList.get(index.getZeroBased());
        if (!Tutorial.isValidName(newName)) {
            throw new CommandException(MESSAGE_INVALID_TUTORIAL);
        }
        Tutorial renamedTutorial = new Tutorial(newName);
        tutorialToRename.attendances().forEach(renamedTutorial::addAttendance);

        if (!tutorialToRename.hasSameIdentity(renamedTutorial) && model.hasTutorial(renamedTutorial)) {
            throw new CommandException(MESSAGE_DUPLICATE_TUTORIAL);
        }

        List<Student> studentsInRenamedTutorial = model.getStudentsInTutorial(tutorialToRename);

        for (Student studentToEdit : studentsInRenamedTutorial) {

            Student editedStudent = studentToEdit.clone();
            assert studentToEdit.hashCode() == editedStudent.hashCode();

            Set<Tutorial> tutorials = new HashSet<>(studentToEdit.getTutorials());
            tutorials.add(renamedTutorial);
            editedStudent.setTutorials(tutorials);

            try {
                model.setStudent(studentToEdit, editedStudent);
            } catch (DuplicateItemException | ItemNotFoundException e) {
                throw new IllegalStateException(Messages.MESSAGE_UNKNOWN_ERROR);
            }

        }

        model.deleteTutorialFromStudents(tutorialToRename);

        model.addTutorial(renamedTutorial);
        model.deleteTutorial(tutorialToRename);

        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_PERSONS);
        model.updateFilteredTutorialList(PREDICATE_SHOW_ALL_TUTORIALS);

        return new CommandResult(String.format(MESSAGE_RENAME_TUTORIAL_SUCCESS, renamedTutorial.name()),
                        NavigationMode.UNCHANGED);
    }
}
