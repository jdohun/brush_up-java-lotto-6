package lotto.controller;

import camp.nextstep.edu.missionutils.Console;
import lotto.domain.model.AutoLottoGenerator.AutoLottoGenerator;
import lotto.domain.model.Lotteries;
import lotto.handler.InputHandler;
import lotto.view.inputView.InputView;
import lotto.view.outputView.OutputView;

import java.util.function.Supplier;

public class LottoController {
    private final InputView INPUT_VIEW = InputView.getInstance();
    private final InputHandler INPUT_HANDLER = InputHandler.getInstance();
    private final OutputView OUTPUT_VIEW = OutputView.getInstance();
    private final AutoLottoGenerator LOTTO_GENERATOR = AutoLottoGenerator.getInstance();

    public void run() {
        try {
            final int money = getMoney();
            Lotteries usersLotteries = buyLotteries(money);
            OUTPUT_VIEW.showCountOfLotteries(usersLotteries.getSize());
//            OUTPUT_VIEW.showLotteries(usersLotteries);


        } finally {
            Console.close();
        }
    }

    private int getMoney() {
        return repeatUntilNoException(() -> INPUT_HANDLER.parseInputMoneyAsInteger(INPUT_VIEW.inputMoney()));
    }

    private Lotteries buyLotteries(final int money) {
        return repeatUntilNoException(() -> LOTTO_GENERATOR.createLotteries(money));
    }

    private <T> T repeatUntilNoException(Supplier<T> supplier) {
        while (true) try {
            return supplier.get();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
