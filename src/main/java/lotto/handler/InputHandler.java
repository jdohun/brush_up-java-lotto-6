package lotto.handler;

import lotto.util.stringValidator.StringValidator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class InputHandler {
    private final Pattern INTEGER_PATTERN = Pattern.compile("^\\d+$");

    private InputHandler() {
    }

    public static InputHandler getInstance() {
        return Holder.INPUT_HANDLER;
    }

    public int parseInputMoneyAsInteger(String inputMoney) {
        StringValidator.isNotNull(inputMoney);
        StringValidator.isNotEmpty(inputMoney);
        StringValidator.hasNotSurroundingWhiteSpace(inputMoney);
        return parseInputAsInteger(inputMoney);
    }

    private int parseInputAsInteger(String input) {
        Matcher matcher = INTEGER_PATTERN.matcher(input);
        if (!matcher.matches()){
            throw new IllegalArgumentException(InputHandlerErrorMessage.ERROR_NOT_INTEGER.getMessage());
        }
        return Integer.parseInt(input);
    }

    private static final class Holder {
        private static final InputHandler INPUT_HANDLER = new InputHandler();
    }
}
