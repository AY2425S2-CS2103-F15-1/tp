package seedu.address.commons.core;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class LogsFormatter extends Formatter {
    // ANSI escape code
    public static final String ANSI_RESET = "\033[0m";
    public static final String ANSI_BLACK = "\033[30m";
    public static final String ANSI_RED = "\033[31m";
    public static final String ANSI_GREEN = "\033[32m";
    public static final String ANSI_YELLOW = "\033[33m";
    public static final String ANSI_BLUE = "\033[34m";
    public static final String ANSI_PURPLE = "\033[35m";
    public static final String ANSI_CYAN = "\033[36m";
    public static final String ANSI_WHITE = "\033[37m";

    // Here you can configure the format of the output and
    // its color by using the ANSI escape codes defined above.

    // format is called for every console log message
    @Override
    public String format(LogRecord record) {
        // This example will print date/time, class, and log level in yellow,
        // followed by the log message and it's parameters in white .
        StringBuilder builder = new StringBuilder();
        builder.append(ANSI_WHITE);
        builder.append("[");
        builder.append(ANSI_GREEN);
        builder.append(record.getLevel().getName());
        builder.append(ANSI_WHITE);
        builder.append("] ");
        builder.append(ANSI_RESET);

        builder.append(calcTime(record.getMillis()));

        builder.append(ANSI_WHITE);
        builder.append(" ");

        builder.append(record.getSourceClassName());
        builder.append(" ");
        builder.append(ANSI_RESET);

        builder.append(record.getMessage());

        Object[] params = record.getParameters();

        if (params != null) {
            builder.append("\t");
            for (int i = 0; i < params.length; i++) {
                builder.append(params[i]);
                if (i < params.length - 1)
                    builder.append(", ");
            }
        }

        builder.append(ANSI_RESET);
        builder.append("\n");
        return builder.toString();
    }

    private String calcTime(long millisecs) {
        return new SimpleDateFormat("HH:mm:ss").format(new Date(millisecs));
    }
}
