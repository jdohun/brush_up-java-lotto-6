package lotto.handler;

import lotto.domain.model.AutoLottoGenerator.AutoLottoGenerator;
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

    public int parseInputMoneyAsInteger(final String inputMoney) {
        StringValidator.isNotNull(inputMoney);
        StringValidator.isNotEmpty(inputMoney);
        StringValidator.hasNotSurroundingWhiteSpace(inputMoney);
        final int money = parseInputAsInteger(inputMoney);
        validateMoneyDivisibleByLottoPrice(money);
        return money;
    }

    private void validateMoneyDivisibleByLottoPrice(final int money) {
        if (money % AutoLottoGenerator.LOTTO_PRICE != 0) {
            throw new IllegalArgumentException(InputHandlerErrorMessage.ERROR_NOT_DIVISIBLE_BY_LOTTO_PRICE.getMessage());
        }
    }

    private int parseInputAsInteger(String input) {
        Matcher matcher = INTEGER_PATTERN.matcher(input);
        if (!matcher.matches()) {
            throw new IllegalArgumentException(InputHandlerErrorMessage.ERROR_NOT_INTEGER.getMessage());
        }
        return Integer.parseInt(input);
    }

    private static final class Holder {
        private static final InputHandler INPUT_HANDLER = new InputHandler();
    }
}
