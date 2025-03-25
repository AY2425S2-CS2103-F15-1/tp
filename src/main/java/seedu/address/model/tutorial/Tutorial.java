package seedu.address.model.tutorial;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import seedu.address.model.uniquelist.Identifiable;

/**
 * Represents a tutorial
 */
public record Tutorial(String name, List<Assignment> assignments) implements Identifiable<Tutorial> {

    public Tutorial(String name) {
        this(name, new ArrayList<>());
    }

    /**
     * Creates a new {@code Tutorial} object
     *
     * @param name
     *            name of the tutorial
     * @param assignments
     *            list of assignments
     */
    public Tutorial {
        Objects.requireNonNull(name);
        if (!isValidName(name)) {
            throw new IllegalArgumentException("Tutorial name is invalid.");
        }
    }

    /**
     * Adds assignments to tutorial
     *
     * @param assignment
     *            Assignment to add
     */
    public void addAssignment(Assignment assignment) {
        Objects.requireNonNull(assignment);
        assignments.add(assignment);
    }

    public boolean hasAssignment(Assignment assignment) {
        return assignments.contains(assignment);
    }

    /**
     * Checks whether the given name is valid
     */
    public static boolean isValidName(String name) {
        final var pattern = "[a-zA-Z0-9_-]+";

        return name.matches(pattern);
    }

    @Override
    public boolean hasSameIdentity(Tutorial other) {
        if (other == null) {
            return false;
        }

        return this.name.equals(other.name);
    }

    @Override
    public String toString() {
        return this.name;
    }
}
