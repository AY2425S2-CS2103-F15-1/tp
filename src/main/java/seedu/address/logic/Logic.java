package seedu.address.logic;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Path;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.AddressBookParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.student.Student;
import seedu.address.model.tutorial.Tutorial;
import seedu.address.model.tutorial.TutorialWithStudents;
import seedu.address.storage.Storage;

/**
 * The main Logic of the app.
 */
public class Logic {
  public static final String FILE_OPS_ERROR_FORMAT =
      "Could not save data due to the following error: %s";

  public static final String FILE_OPS_PERMISSION_ERROR_FORMAT =
      "Could not save data to file %s due to "
      + "insufficient permissions to write to the file or the folder.";

  private final Logger logger = LogsCenter.getLogger(Logic.class);

  private final Model model;
  private final Storage storage;
  private final AddressBookParser addressBookParser;

  /**
   * Constructs a {@code Logic} with the given {@code Model} and
   * {@code Storage}.
   */
  public Logic(Model model, Storage storage) {
    this.model = model;
    this.storage = storage;
    addressBookParser = new AddressBookParser();
  }

  /**
   * Executes the command and returns the result.
   *
   * @param commandText
   *            The command as entered by the user.
   * @return the result of the command execution.
   * @throws CommandException
   *             If an error occurs during command execution.
   * @throws ParseException
   *             If an error occurs during parsing.
   */
  public CommandResult execute(String commandText)
      throws CommandException, ParseException {
    logger.info("----------------[USER COMMAND][" + commandText + "]");

    CommandResult commandResult;
    Command command = addressBookParser.parseCommand(commandText);
    commandResult = command.execute(model);

    try {
      storage.saveAddressBook(model.getAddressBook());
    } catch (AccessDeniedException e) {
      throw new CommandException(
          String.format(FILE_OPS_PERMISSION_ERROR_FORMAT, e.getMessage()), e);
    } catch (IOException ioe) {
      throw new CommandException(
          String.format(FILE_OPS_ERROR_FORMAT, ioe.getMessage()), ioe);
    }

    return commandResult;
  }

  /**
   * Returns the AddressBook.
   *
   * @see seedu.address.model.Model#getAddressBook()
   */
  public ReadOnlyAddressBook getAddressBook() { return model.getAddressBook(); }

  /** Returns an unmodifiable view of the filtered list of students */
  public ObservableList<Student> getFilteredStudentList() {
    return model.getFilteredStudentList();
  }

  /** Returns an unmodifiable view of the filtered list of tutorials */
  public ObservableList<Tutorial> getFilteredTutorialList() {
    return model.getFilteredTutorialList();
  }

  public ObservableList<TutorialWithStudents>
  getFilteredTutorialWithStudents() {
    return model.getFilteredTutorialWithStudents();
  }

  /**
   * Returns the user prefs' address book file path.
   */
  public Path getAddressBookFilePath() {
    return model.getAddressBookFilePath();
  }

  /**
   * Returns the user prefs' GUI settings.
   */
  public GuiSettings getGuiSettings() { return model.getGuiSettings(); }

  /**
   * Set the user prefs' GUI settings.
   */
  public void setGuiSettings(GuiSettings guiSettings) {
    model.setGuiSettings(guiSettings);
  }
}
