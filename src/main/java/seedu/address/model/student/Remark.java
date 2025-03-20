package seedu.address.model.student;

import java.util.Objects;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Student in the address book. Guarantees: details are present and
 * not null, field values are validated, immutable.
 */
public class Remark {

    // Identity fields
    public final String value;

    /**
     * Every field must be present and not null.
     */
    public Remark(String remark) {
        requireNonNull(remark);
        value = remark;
    }

    /**
     * Returns true if both students have the same identity and data fields. This
     * defines a stronger notion of equality between two students.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Remark)) {
            return false;
        }

        return value.equals(((Remark) other).value);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value;
    }

}
