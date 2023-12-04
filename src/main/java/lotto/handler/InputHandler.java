package lotto.handler;

import lotto.domain.generator.AutoLottoGenerator;
import lotto.util.inputStringValidator.InputStringValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class InputHandler {
    private final Pattern INTEGER_PATTERN = Pattern.compile("^\\d+$");
    private final Pattern WINNING_NUMBER_PATTERN = Pattern.compile("^\\d{1,2}(,\\d{1,2}){5}");
    private final String WINNING_NUMBER_DELIMITER = ",";

    private InputHandler() {
    }

    public static InputHandler getInstance() {
        return Holder.INPUT_HANDLER;
    }

    public int parseInputMoney(final String inputMoney) {
        validateInput(inputMoney);
        final int money = parseInputAsInteger(inputMoney);
        validateMoneyDivisibleByLottoPrice(money);
        return money;
    }

    private void validateInput(String input) {
        InputStringValidator.isNotNull(input);
        InputStringValidator.isNotEmpty(input);
        InputStringValidator.hasNotSurroundingWhiteSpace(input);
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

    public List<Integer> parseWinningNumbers(String inputWinningNumbers) {
        validateInput(inputWinningNumbers);
        validateWinningNumbersFormat(inputWinningNumbers);
        String[] splitNumbers = inputWinningNumbers.split(WINNING_NUMBER_DELIMITER);

        List<Integer> winningNumbers = new ArrayList<>();
        for (String splitNumber : splitNumbers) {
            winningNumbers.add(parseInputAsInteger(splitNumber.trim()));
        }

        return winningNumbers;
    }

    private void validateWinningNumbersFormat(String inputWinningNumbers) {
        Matcher matcher = WINNING_NUMBER_PATTERN.matcher(inputWinningNumbers);
        if (!matcher.find()) {
            throw new IllegalArgumentException(InputHandlerErrorMessage.ERROR_INVALID_WINNING_NUMBERS_FORMAT.getMessage());
        }
    }

    public int parseBonusNumber(String inputBonusNumber) {
        validateInput(inputBonusNumber);
        return parseInputAsInteger(inputBonusNumber);
    }

    private static final class Holder {
        private static final InputHandler INPUT_HANDLER = new InputHandler();
    }
}
