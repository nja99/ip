package crayon.utils;

import crayon.exceptions.CrayonInvalidDateTimeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a utility class for date and time operations.
 */
public class DateTime {

    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a");

    private DateTime(){} // Private constructor to prevent instantiation

    /**
     * Converts a date and time string to a LocalDateTime object.
     *
     * @param dateTimeString The date and time string to be converted.
     * @param checkBefore Whether to check if the date and time is before the current date and time.
     * @return The LocalDateTime object.
     * @throws CrayonInvalidDateTimeException If the date and time string is in an invalid format or if the date and time
     * is before the current date and time.
     */
    public static LocalDateTime stringToDateTime(String dateTimeString, boolean checkBefore) throws CrayonInvalidDateTimeException {
        try {
            LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, INPUT_FORMATTER);

            if (dateTime.isBefore(LocalDateTime.now()) && checkBefore) {
                throw new CrayonInvalidDateTimeException("The deadline must be in the future.\n");
            }

            return dateTime;
        } catch (DateTimeParseException e) {
            throw new CrayonInvalidDateTimeException("Use d/M/yyyy HHmm to convert the date/time string to a valid date.\n");
        }
    }

    /**
     * Converts a stored LocalDateTime string to a LocalDateTime object.
     *
     * @param storedDateTime The stored LocalDateTime string to be converted.
     * @return The LocalDateTime object.
     */
    public static LocalDateTime parseStoredDateTime(String storedDateTime) throws CrayonInvalidDateTimeException {
        try {
            return LocalDateTime.parse(storedDateTime);
        } catch (DateTimeParseException e) {
            throw new CrayonInvalidDateTimeException("Invalid stored date-time format: " + storedDateTime);
        }
    }

    /**
     * Converts a LocalDateTime object to a string for printing.
     *
     * @param dateTime The LocalDateTime object to be converted.
     * @return The string for printing
     */
    public static String dateTimeToString(LocalDateTime dateTime) {
        return dateTime.format(OUTPUT_FORMATTER);
    }

}
