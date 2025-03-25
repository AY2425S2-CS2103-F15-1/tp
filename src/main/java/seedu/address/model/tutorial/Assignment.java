package seedu.address.model.tutorial;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

public record Assignment(String name, Optional<LocalDateTime> dueDate) {
}
