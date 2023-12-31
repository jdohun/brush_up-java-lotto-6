package lotto.controller;

import camp.nextstep.edu.missionutils.Console;
import lotto.domain.generator.AutoLottoGenerator;
import lotto.domain.model.lotteries.Lotteries;
import lotto.domain.model.lotto.Lotto;
import lotto.domain.model.lottoNumber.LottoNumber;
import lotto.domain.model.winningLotto.WinningLotto;
import lotto.domain.model.winningResult.WinningResult;
import lotto.handler.InputHandler;
import lotto.view.inputView.InputView;
import lotto.view.outputView.OutputView;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class LottoController {
    private static final InputView INPUT_VIEW = InputView.getInstance();
    private static final InputHandler INPUT_HANDLER = InputHandler.getInstance();
    private static final OutputView OUTPUT_VIEW = OutputView.getInstance();
    private static final AutoLottoGenerator LOTTO_GENERATOR = AutoLottoGenerator.getInstance();

    public void run() {
        try {
            final int money = getMoney();
            Lotteries usersLotteries = buyLotteries(money);
            OUTPUT_VIEW.showLotteriesInfo(usersLotteries);

            WinningLotto winningLotto = defineWinningLotto();
            Map<WinningResult, Integer> winningStatistics = winningLotto.calculateWinningStatistics(usersLotteries);
            OUTPUT_VIEW.showWinningStatistics(winningStatistics);

            double rateOfReturn = WinningResult.calculateRateOfReturn(money, winningStatistics);
            OUTPUT_VIEW.showRateOfReturn(rateOfReturn);
        } finally {
            Console.close();
        }
    }

    private int getMoney() {
        return repeatUntilNoException(() -> INPUT_HANDLER.parseInputMoney(INPUT_VIEW.inputMoney()));
    }

    private Lotteries buyLotteries(final int money) {
        return LOTTO_GENERATOR.createLotteries(money);
    }

    private WinningLotto defineWinningLotto() {
        final Lotto winningNumbers = repeatUntilNoException(() -> announceWinningNumbers());
        return repeatUntilNoException(() -> new WinningLotto(winningNumbers, repeatUntilNoException(() -> announceBonusNumber())));
    }

    private Lotto announceWinningNumbers() {
        List<Integer> WinningNumbers = repeatUntilNoException(() -> INPUT_HANDLER.parseWinningNumbers(INPUT_VIEW.inputWinningNumbers()));
        return new Lotto(WinningNumbers);
    }

    private LottoNumber announceBonusNumber() {
        int inputBonusNumber = repeatUntilNoException(() -> INPUT_HANDLER.parseBonusNumber(INPUT_VIEW.inputBonusNumber()));
        return new LottoNumber(inputBonusNumber);
    }

    private <T> T repeatUntilNoException(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
