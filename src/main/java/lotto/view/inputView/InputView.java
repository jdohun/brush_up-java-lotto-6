package lotto.view.inputView;

import camp.nextstep.edu.missionutils.Console;

public final class InputView {
    private InputView() {
    }

    public static final InputView getInstance() {
        return Holder.INPUT_VIEW;
    }

    public String inputMoney() {
        System.out.println(InputMessage.INPUT_MONEY.getMessage());
        return Console.readLine();
    }

    public String inputWinningNumbers() {
        System.out.println(InputMessage.INPUT_WINNING_NUMBERS.getMessage());
        return Console.readLine();
    }

    public String inputBonusNumber() {
        System.out.println(InputMessage.INPUT_BONUS_NUMBER.getMessage());
        return Console.readLine();
    }

    private static final class Holder {
        private static final InputView INPUT_VIEW = new InputView();
    }
}
