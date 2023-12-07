package lotto.util.inputStringValidator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static lotto.util.inputStringValidator.InputStringValidatorErrorMessage.*;

public final class InputStringValidator {
    private static final Pattern FORMAT_INVALID_STRING_HAS_SURROUNDING_WHITE_SPACE = Pattern.compile("^\\S(.*\\S)?$");

    private InputStringValidator() {
    }

    public static void isNotNull(String input) {
        if (null == input) {
            throw new IllegalArgumentException(ERROR_NULL.getMessage());
        }
    }

    public static void isNotEmpty(String input) {
        if (input.trim().isEmpty()) {
            throw new IllegalArgumentException(ERROR_EMPTY.getMessage());
        }
    }

    public static void hasNotSurroundingWhiteSpace(String input) {
        Matcher matcher = FORMAT_INVALID_STRING_HAS_SURROUNDING_WHITE_SPACE.matcher(input);
        if (!matcher.matches()) {
            throw new IllegalArgumentException(ERROR_HAS_SURROUNDING_WHITE_SPACE.getMessage());
        }
    }
}
