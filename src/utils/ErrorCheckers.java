package utils;

/**
 * A util class of methods used in multiple places that throw excpetions.
 */

public class ErrorCheckers {
    /**
     * Checks if the object given is null and throws an exception with the given message if it is
     *
     * @param o       the object being checked
     * @param message the message included in the exception
     * @throws IllegalArgumentException if the given object is null
     */
    public static void checkNull(Object o, String message) throws IllegalArgumentException {
        if (o == null)
            throw new IllegalArgumentException(message);
    }
}
