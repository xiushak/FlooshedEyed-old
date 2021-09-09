package utils;

public class ErrorCheckers {
    public static void checkNull(Object o, String message) throws IllegalArgumentException {
        if (o == null)
            throw new IllegalArgumentException(message);
    }
}
