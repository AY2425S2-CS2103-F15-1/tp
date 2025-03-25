package seedu.address.model.tutorial;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Object that represents an {@code Assignment}
 *
 * @param name
 *            name of assignment
 * @param dueDate
 *            due date of assignment
 */
public record Assignment(String name, Optional<LocalDateTime> dueDate) {
}
