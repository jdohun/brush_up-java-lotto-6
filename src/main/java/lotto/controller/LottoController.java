package lotto.controller;

import lotto.domain.model.AutoLottoGenerator;
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
        buyLotteries();
    }

    private Lotteries buyLotteries() {
        final int count = get(() -> INPUT_HANDLER.inputMoneyToCountOfLotteries(INPUT_VIEW.inputMoney()));
        OUTPUT_VIEW.showCountOfLotteries(count);
        return LOTTO_GENERATOR.createLotteries(count);
    }

    private <T> T get(Supplier<T> supplier) {
        while (true) try {
            return supplier.get();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
