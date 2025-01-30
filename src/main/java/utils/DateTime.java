package utils;

import exceptions.CrayonInvalidDateTimeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTime {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

    private DateTime(){} // Private constructor to prevent instantiation

    public static LocalDateTime stringToDateTime(String dateTimeString, boolean checkBefore) throws CrayonInvalidDateTimeException {
        try {
            LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, FORMATTER);

            if (dateTime.isBefore(LocalDateTime.now()) && checkBefore) {
                throw new CrayonInvalidDateTimeException("The deadline must be in the future.");
            }

            return dateTime;
        } catch (DateTimeParseException e) {
            throw new CrayonInvalidDateTimeException("Use d/M/yyyy HHmm to convert the date/time string to a valid date.");
        }
    }

    public static LocalDateTime parseStoredDateTime(String storedDateTime) throws CrayonInvalidDateTimeException {
        try {
            return LocalDateTime.parse(storedDateTime);
        } catch (DateTimeParseException e) {
            throw new CrayonInvalidDateTimeException("Invalid stored date-time format: " + storedDateTime);
        }
    }

    public static String dateTimeToString(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a"));
    }

}
