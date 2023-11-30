package lotto.handler;

import lotto.util.stringValidator.StringValidator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class InputHandler {
    private final int LOTTO_PRICE = 1_000;
    private final Pattern INTEGER_PATTERN = Pattern.compile("^\\d+$");

    private InputHandler() {
    }

    public static InputHandler getInstance() {
        return Holder.INPUT_HANDLER;
    }

    public int inputMoneyToCountOfLotteries(String inputMoney) {
        StringValidator.isNotNull(inputMoney);
        StringValidator.isNotEmpty(inputMoney);
        StringValidator.hasNotSurroundingWhiteSpace(inputMoney);
        final int money =  parseInputAsInteger(inputMoney);
        return calculateNumberOfLotteries(money);
    }

    private int parseInputAsInteger(String input) {
        Matcher matcher = INTEGER_PATTERN.matcher(input);
        if (!matcher.matches()){
            throw new IllegalArgumentException();
        }
        return Integer.parseInt(input);
    }

    private int calculateNumberOfLotteries(int money) {
        if (money % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException();
        }
        return money / LOTTO_PRICE;
    }

    private static final class Holder {
        private static final InputHandler INPUT_HANDLER = new InputHandler();
    }
}
