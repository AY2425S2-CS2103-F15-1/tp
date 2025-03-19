package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.student.Student;
import seedu.address.model.tutorial.Tutorial;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Student> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /** {@code Predicate} that always evaluate to true */
    Predicate<Tutorial> PREDICATE_SHOW_ALL_TUTORIALS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns true if a student with the same identity as {@code student} exists in
     * the address book.
     */
    boolean hasStudent(Student student);

    /**
     * Deletes the given student. The student must exist in the address book.
     */
    void deleteStudent(Student target);

    /**
     * Adds the given student. {@code student} must not already exist in the address
     * book.
     */
    void addStudent(Student student);

    /**
     * Replaces the given student {@code target} with {@code editedStudent}.
     * {@code target} must exist in the address book. The student identity of
     * {@code editedStudent} must not be the same as another existing student in the
     * address book.
     */
    void setStudent(Student target, Student editedStudent);

    /**
     * Adds a tutorial slot
     */
    void addTutorial(Tutorial tutorial);

    /**
     * Deletes a tutorial slot
     */
    void deleteTutorial(Tutorial tutorial);

    /**
     * Checks whether tutorial exists in the address book
     */
    boolean hasTutorial(Tutorial tutorial);

    /** Returns an unmodifiable view of the filtered student list */
    ObservableList<Student> getFilteredStudentList();

    /**
     * Updates the filter of the filtered student list to filter by the given
     * {@code predicate}.
     *
     * @throws NullPointerException
     *             if {@code predicate} is null.
     */
    void updateFilteredStudentList(Predicate<Student> predicate);

    /**
     * Updates the filter of the filtered student list to filter by the given
     * {@code predicate} for tutorial group(s).
     *
     * @throws NullPointerException
     *             if {@code predicate} is null.
     */
    void updateFilteredStudentsByTutorialList(Predicate<Tutorial> predicate);

    /** Returns an unmodifiable view of the filtered tutorial list */
    ObservableList<Tutorial> getFilteredTutorialList();

    /**
     * Updates the filter of the filtered tutorial list to filter by the given
     * {@code predicate}.
     *
     * @throws NullPointerException
     *             if {@code predicate} is null.
     */
    void updateFilteredTutorialList(Predicate<Tutorial> predicate);
}
